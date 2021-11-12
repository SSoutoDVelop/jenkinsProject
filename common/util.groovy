def executeMSBuild(file, buildParameters) {
    def params = ""
    for (p in buildParameters) {
        if (!p.key.equals("DeploymentUnits")) {
            params += "/p:${p.key}=\"${p.value}\" "
        }
    }
    return "msbuild.exe ${params} /tv:4.0 /verbosity:normal \"${file}\""
}

def getMSBuildParams(dictionary) {
    def params = ""
    for (p in dictionary) {
        params += "/p:${p.key}=\"${p.value}\" "
    }
    return params
}

def executeGXInstall(gxProgramDir) {
    return "\"${gxProgramDir}\\GeneXus.exe\" /install"
}

def extractGXModule(moduleVersion) {
    def moduleRoot = "%APPDATA%\\GeneXus\\GeneXus\\16\\Modules_v1.0"
    def moduleName = "GeneXus"
    def moduleGUID = "4f454e73-7d8f-4a0f-908a-1a355f3634a5"
    def moduleFile = "${moduleRoot}\\${moduleName}_${moduleGUID}\\${moduleVersion}\\${moduleName}_${moduleVersion}.opc"
    def moduleExtractFolder = "${moduleRoot}\\${moduleName}_${moduleGUID}\\${moduleVersion}"
    return "\"C:\\Program Files\\7-Zip\\7z.exe\" x \"${moduleFile}\" -o\"${moduleExtractFolder}\" -y"
}

def extractGXSecurityModule(moduleVersion) {
    def moduleRoot = "%APPDATA%\\GeneXus\\GeneXus\\16\\Modules_v1.0"
    def moduleName = "GeneXusSecurity"
    def moduleGUID = "9c06daaa-8132-41f6-a0f7-3afc9b0ab999"
    def moduleFile = "${moduleRoot}\\${moduleName}_${moduleGUID}\\${moduleVersion}\\${moduleName}_${moduleVersion}.opc"
    def moduleExtractFolder = "${moduleRoot}\\${moduleName}_${moduleGUID}\\${moduleVersion}"
    return "\"C:\\Program Files\\7-Zip\\7z.exe\" x \"${moduleFile}\" -o\"${moduleExtractFolder}\" -y"
}

def getJobStatus(folderName, jobName){
    def request = httpRequest "http://dvjenkins.dvelop.intra:8080/job/${folderName}/job/${jobName}/lastBuild/api/json"
    def requestJson = new groovy.json.JsonSlurper().parseText(request.getContent())
    return requestJson["result"]
}

def copyFile(source, target) {
    return "copy \"${source}\" \"${target}\""
}

def createFolder(target) {
    return "mkdir \"${target}\""   
}

def copyFolder(source, target) {
    return "xcopy \"${source}\" \"${target}\" /E /Y"
}

def deleteFolder(target) {
    return "rmdir \"${target}\" /s /q"
}

def azureUpload(azureToken, azureZipDeployUrl, webappPackage) {
    return "\"C:\\Program Files\\curl\\bin\\curl.exe\" -X POST -u ${azureToken} --data-binary @\"${webappPackage}.zip\" ${azureZipDeployUrl}"
}

def azureToken(clientId, clientSecret, tenantId) {
    def response = [:]
    def params  = "grant_type=client_credentials"
    params += "&client_id=${clientId}"
    params += "&client_secret=${clientSecret}"
    params += "&resource=https://management.azure.com/"

    def postData = params.getBytes("UTF-8")
    def postDataLength = postData.length

    def url = "https://login.microsoftonline.com/${tenantId}/oauth2/token"

    def conn = new URL(url).openConnection();
    conn.setDoOutput(true)
    conn.setInstanceFollowRedirects(false)
    conn.setRequestMethod("POST")
    conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded")
    conn.setRequestProperty("charset", "UTF-8")
    conn.setRequestProperty("Content-Length", Integer.toString(postDataLength))
    conn.setUseCaches(false)
    conn.getOutputStream().write(postData)
    
    def status = conn.getResponseCode()
    if (status >= 200 && status < 300) {
        response = new groovy.json.JsonSlurper().parseText(conn.getInputStream().getText())
    }

    return response
}

def azureStartVM(virtualMachine) {
    def clientId = "ed7ab38d-8440-4512-851b-92337bc9b826"
    def clientSecret = "Vo3SGanW0DgEZ9x8BTtE96RFj4iUP_zRju"
    def tenantId = "3ea70b79-aa8a-4f84-b672-4a48b29725b4"
    def token = azureToken(clientId, clientSecret, tenantId)
    
    def subscriptionId = "d83b0aff-31d5-48ba-9747-a8af036c5fe2"
    def resourceGroupName = "RG-DVelop"
    return azureStartVM(subscriptionId, resourceGroupName, virtualMachine, token)
}

def azureStopVM(virtualMachine) {
    def clientId = "ed7ab38d-8440-4512-851b-92337bc9b826"
    def clientSecret = "Vo3SGanW0DgEZ9x8BTtE96RFj4iUP_zRju"
    def tenantId = "3ea70b79-aa8a-4f84-b672-4a48b29725b4"
    def token = azureToken(clientId, clientSecret, tenantId)
    
    def subscriptionId = "d83b0aff-31d5-48ba-9747-a8af036c5fe2"
    def resourceGroupName = "RG-DVelop"
    return azureStopVM(subscriptionId, resourceGroupName, virtualMachine, token)
}

def azureStartVM(resourceGroup, virtualMachine) {
    def clientId = "c69fc444-1d0d-4bc3-a281-1e4bd099b366"
    def clientSecret = "40YHYo2li5b51v~tDt70XDwNXy6BmZiaVm"
    def tenantId = "c3b2b954-7937-49de-bdb0-c751129bc362"
    def token = azureToken(clientId, clientSecret, tenantId)
    
    def subscriptionId = "dff929d4-cd3d-4843-ba3b-86e9870f144b"
    return azureStartVM(subscriptionId, resourceGroup, virtualMachine, token)
}

def azureStopVM(resourceGroup, virtualMachine) {
    def clientId = "c69fc444-1d0d-4bc3-a281-1e4bd099b366"
    def clientSecret = "40YHYo2li5b51v~tDt70XDwNXy6BmZiaVm"
    def tenantId = "c3b2b954-7937-49de-bdb0-c751129bc362"
    def token = azureToken(clientId, clientSecret, tenantId)
    
    def subscriptionId = "dff929d4-cd3d-4843-ba3b-86e9870f144b"
    return azureStopVM(subscriptionId, resourceGroup, virtualMachine, token)
}

def azureStartVM(subscriptionId, resourceGroup, virtualMachine, token) {
    return azureChangeVMStatus(subscriptionId, resourceGroup, virtualMachine, token, "start")
}

def azureStopVM(subscriptionId, resourceGroup, virtualMachine, token) {
    return azureChangeVMStatus(subscriptionId, resourceGroup, virtualMachine, token, "deallocate")
}

def azureChangeVMStatus(subscriptionId, resourceGroup, virtualMachine, token, action) {
    
    def postData = "".getBytes("UTF-8")
    def postDataLength = postData.length

    def url = "https://management.azure.com/subscriptions/${subscriptionId}/resourceGroups/${resourceGroup}/providers/Microsoft.Compute/virtualMachines/${virtualMachine}/${action}?api-version=2021-03-01"

    def conn = new URL(url).openConnection()
    conn.setDoOutput(true)
    conn.setInstanceFollowRedirects(false)
    conn.setRequestMethod("POST")
    conn.setRequestProperty("Authorization", "${token.token_type} ${token.access_token}")
    conn.setUseCaches(false)
    conn.getOutputStream().write(postData)
    
    def status = conn.getResponseCode()
    if (status >= 200 && status < 300) {
        return true
    }

    return false
}

def tomcatUpload(tomcatHost, warFile, webapp) {
    return "\"C:\\Program Files\\curl\\bin\\curl.exe\" -v -T \"${warFile}\" \"${tomcatHost}/manager/text/deploy?path=/${webapp}&update=true\""
}

def tomcatStopApp(tomcatHost, webapp) {
    return "\"C:\\Program Files\\curl\\bin\\curl.exe\" -v \"${tomcatHost}/manager/text/stop?path=/${webapp}\""
}

def tomcatStartApp(tomcatHost, webapp) {
    return "\"C:\\Program Files\\curl\\bin\\curl.exe\" -v \"${tomcatHost}/manager/text/start?path=/${webapp}\""
}

def appcenterInstall() {
    return "npm install -g appcenter-cli"
}

def appcenterUpload(appcenterToken, appcenterOwner, appcenterName, appcenterGroup, file) {
    return "appcenter distribute release --token ${appcenterToken} --app ${appcenterOwner}/${appcenterName} --file \"${file}\" --group \"${appcenterGroup}\""
}

def appcenterTestFlightUpload(appcenterToken, appcenterOwner, appcenterName, releaseNotes, file) {
    return "appcenter distribute stores publish --token ${appcenterToken} --app ${appcenterOwner}/${appcenterName} --file \"${file}\" --store \"App Store Connect Users\" --release-notes \"${releaseNotes}\""
}

def iisUpload(source, webapp, server, user, password) {
    return "\"C:\\Program Files (x86)\\IIS\\Microsoft Web Deploy V3\\msdeploy.exe\" -verb:sync -source:package=\"${source}.zip\" -dest:contentPath=\"${webapp}\",ComputerName=\"${server}\",UserName='${user}',Password='${password}',AuthType='NTLM'"
}

def iisStop(source, webapp, server, user, password) {
    return "\"C:\\Program Files (x86)\\IIS\\Microsoft Web Deploy V3\\msdeploy.exe\" -verb:sync -source:recycleApp -dest:recycleApp=\"${webapp}\",recycleMode=\"StopAppPool\",ComputerName=\"${server}\",UserName='${user}',Password='${password}',AuthType='NTLM'"
}

def iisStart(source, webapp, server, user, password) {
    return "\"C:\\Program Files (x86)\\IIS\\Microsoft Web Deploy V3\\msdeploy.exe\" -verb:sync -source:recycleApp -dest:recycleApp=\"${webapp}\",recycleMode=\"StartAppPool\",ComputerName=\"${server}\",UserName='${user}',Password='${password}',AuthType='NTLM'"
}

def iisStopPool(server, pool) {
    return "Invoke-Command -ComputerName \"${server}\" -ScriptBlock { Import-Module WebAdministration; Stop-WebAppPool -Name \"${pool}\" }"
}

def iisStartPool(server, pool) {
    return "Invoke-Command -ComputerName \"${server}\" -ScriptBlock { Import-Module WebAdministration; Start-WebAppPool -Name \"${pool}\" }"
}

def runProcessWS(baseURL, clientId, username, password, processWS, processName) {
    def response = [:]
    def params  = "client_id=${clientId}"
    params += "&username=${username}"
    params += "&password=${password}"

    def postData = params.getBytes("UTF-8")
    def postDataLength = postData.length

    def url = "${baseURL}/oauth/access_token"

    def conn = new URL(url).openConnection();
    conn.setDoOutput(true)
    conn.setInstanceFollowRedirects(false)
    conn.setRequestMethod("POST")
    conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded")
    conn.setRequestProperty("charset", "UTF-8")
    conn.setRequestProperty("Content-Length", Integer.toString(postDataLength))
    conn.setUseCaches(false)
    conn.getOutputStream().write(postData)
    
    def status = conn.getResponseCode()
    if (status >= 200 && status < 300) {
        response = new groovy.json.JsonSlurper().parseText(conn.getInputStream().getText())

        postData = "{\"ProcessName\":\"${processName}\"}".getBytes("UTF-8")
        postDataLength = postData.length

        url = "${baseURL}${processWS}"

        conn = new URL(url).openConnection()
        conn.setDoOutput(true)
        conn.setInstanceFollowRedirects(false)
        conn.setRequestMethod("POST")
        conn.setRequestProperty("Authorization", "OAuth ${response.access_token}")
        conn.setRequestProperty("Content-Type", "application/json")
        conn.setUseCaches(false)
        conn.getOutputStream().write(postData)
        
        status = conn.getResponseCode()
        if (status >= 200 && status < 300) {
            return true
        }
    }

    return false
}

def globalParameters(envName) {
    def global = [:]

    // Google Chat messages configuration
    // global.GoogleChatRoom = "https://chat.googleapis.com/v1/spaces/AAAAU6uEA2g/messages?key=AIzaSyDdI0hCZtE6vySjMm-WEfRq3CPzqKqqsHI&token=OHWcoQI2HZVr1RIL_ZAORgKBkJhAEYBWthOIb2ZUen0%3D"
    global.GoogleChatSuccessMessage = "\\u2714 The pipeline <${env.BUILD_URL}|${currentBuild.fullDisplayName} - ${envName}> completed successfully."
    global.GoogleChatUnstableMessage = "\\u26A0 The pipeline <${env.BUILD_URL}|${currentBuild.fullDisplayName} - ${envName}> is unstable, tests are failing."
    global.GoogleChatFailureMessage = "\\u274C The pipeline <${env.BUILD_URL}|${currentBuild.fullDisplayName} - ${envName}> has failed."

    // Mail Messages
    global.MailAddress = "infra_notificaciones@dvelop.com.uy "
    global.MailSuccessSubject = "Successful Pipeline: ${currentBuild.fullDisplayName} - ${envName}"
    global.MailSuccessBody = "The pipeline ${currentBuild.fullDisplayName} - ${envName} completed successfully.\n\nBuild link: ${env.BUILD_URL}"
    global.MailUnstableSubject = "Unstable Pipeline: ${currentBuild.fullDisplayName} - ${envName}"
    global.MailUnstableBody = "The pipeline ${currentBuild.fullDisplayName} - ${envName} is unstable, tests are failing.\n\nBuild link: ${env.BUILD_URL}"
    global.MailFailureSubject = "Failed Pipeline: ${currentBuild.fullDisplayName} - ${envName}"
    global.MailFailureBody = "The pipeline ${currentBuild.fullDisplayName} - ${envName} has failed.\n\nBuild link: ${env.BUILD_URL}"

    // Locations
    global.Net1 = "dvelopnet1.dvelop.intra"
    global.Net1WebAppFolder = "\\\\dvelopnet1.dvelop.intra\\Apps"
    global.Java1WebAppFolder = "\\\\dvelopjava1.dvelop.intra\\webapps"
    global.Java1SecureWebAppFolder = "\\\\dvelopjava1.dvelop.intra\\securewebapps"
    global.Java1JobsFolder = "\\\\dvelopjava1.dvelop.intra\\Jobs"

    // Artifacts to archive
    global.ArtifactsToArchive = "*.zip,*.war,*.jar,*.apk,*.aab,*.ipa,*.xml"

    return global
}

return this
