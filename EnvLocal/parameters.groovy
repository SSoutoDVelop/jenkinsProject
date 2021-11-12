def getForEnvironment(envName) {

    // Configuration root object
    def config = [:]
    config.General = [:]
    config.GXServer = [:]    
    config.Environment = [:]
    config.WebGenerator = [:]
    config.Datastores = [:]
    config.SDGenerator = [:]
    config.SDMainObjects = [:]
    config.DeploymentUnits = [:]
    config.AppCenter = [:]
    config.Jenkins = [:]
    
    //=======================//
    // GENERAL CONFIGURATION
    //=======================//

    // GeneXus install folder in build server
    config.General.GX_PROGRAM_DIR = "C:\\Program Files (x86)\\GeneXus\\GeneXus 17U5HF"

    // Environment and Working Directory
    if (envName == "EnvLocal") {
        config.General.WorkingDirectory = "C:\\Users\\ssouto\\Desktop\\Proyectos\\Jenkins\\KBTesting"
        config.General.WorkingVersion = "KBTesting"
    }
    // else if (envName == "EnvTest") {
    //     config.General.WorkingDirectory = "C:\\Users\\ssouto\\Desktop\\Proyectos\\Jenkins\\KBTesting"
    //     config.General.WorkingVersion = "KBTesting"
    // }
    // else if (envName == "EnvQA") {
    //     config.General.WorkingDirectory = "C:\\Users\\ssouto\\Desktop\\Proyectos\\Jenkins\\KBTesting"
    //     config.General.WorkingVersion = "KBTesting"
    // }
    // else if (envName == "EnvProd") {
    //     config.General.WorkingDirectory = "C:\\Users\\ssouto\\Desktop\\Proyectos\\Jenkins\\KBTesting"
    //     config.General.WorkingVersion = "KBTesting"
    // }

    // Set KB Environment
    config.General.WorkingEnvironment = envName

    // Unique version suffix for use in artifacts
    config.General.VersionSuffix = "_v${env.BUILD_NUMBER}_${envName}_${env.BUILD_TIMESTAMP}"

    // Useful directories
    config.General.EnvironmentRelativePath = "${config.General.WorkingVersion}_${config.General.WorkingEnvironment}"
    config.General.EnvironmentRootFolder = "${config.General.WorkingDirectory}\\${config.General.EnvironmentRelativePath}"
    // config.General.EnvironmentWebFolder = "${config.General.EnvironmentRootFolder}\\web"
    // config.General.EnvironmentMobileFolder = "${config.General.EnvironmentRootFolder}\\mobile"
    
    // Default build action
    config.General.ForceRebuild = "True"    

    // Run Data Load Procedure after deploy
    config.General.RunDataLoad = "True"

    //=========================================//
    // GENEXUS SERVER CONNECTION CONFIGURATION
    //=========================================//

    // GXServer information
    // config.GXServer.GXServerUrl = "http://dvelopgx1.dvelop.intra/GXServer_DVelop_GX17/"
    // config.GXServer.GXServerUsername = "local\\jenkins"
    // config.GXServer.GXServerPassword = "9PaHqwCr4"
    // config.GXServer.GXServerKB = "Bigua_Socios"
    // config.GXServer.GXServerVersion = "Bigua_Socios"
    // config.GXServer.GXServerChangelog = "${env.WORKSPACE}\\Changelog${config.General.VersionSuffix}.xml" 
    // config.GXServer.GXServerFreezeAfterDeploy = "False"
    
    //===================================//
    // CURRENT ENVIRONMENT CONFIGURATION
    //===================================//

    // Reorganize GAM Tables        
    // config.Environment.ReorganizeGamDatabase = "True"
    
    // GAM connection information
    // config.Environment.GAMRepositoryId = "cc13c682-4538-4bb0-8ab2-189058ed5176"
    // config.Environment.GAMAdminUsername = "admin"
    // config.Environment.GAMAdminPassword = "admin123"
    // config.Environment.GAMConnectionUsername = "connection"
    // config.Environment.GAMConnectionPassword = "connection123"

    // Use parameter encryption in URLs
    // config.Environment.UseEncryption = "NO"

    // Use HTTP or HTTPS protocol
    // config.Environment.HttpProtocol = "Unsecure"

    // Set Environment Path in disk
    // config.Environment.TargetPath = "${config.General.EnvironmentRelativePath}"

    //=====================================//
    // DEFAULT WEB GENERATOR CONFIGURATION
    //=====================================//

    // Reoganize server tables
    // config.WebGenerator.ReorganizeServerTables = "Yes"

    // Web Logging Level
    // config.WebGenerator.LogLevelGX = "ERROR"
    // config.WebGenerator.LogLevelUser = "ALL"
    // config.WebGenerator.LogOutput = "File"
    // config.WebGenerator.LogFile = "client.log"

    //==========================//
    // DATASTORES CONFIGURATION
    //==========================//

    // Default datastore connection information
    // config.Datastores["Default"] = [:]
    // config.Datastores["Default"].DatastoreServer = "localhost"
    // config.Datastores["Default"].DatastoreUsername = "root"
    // config.Datastores["Default"].DatastorePassword = "yt5XTLmduaZuj5zU"
    // config.Datastores["Default"].DatastoreDatabase = "bigua_socios_dev"
    // config.Datastores["Default"].DatastoreUseJDBCCustomUrl = "True"
    // config.Datastores["Default"].DatastoreJDBCCustomUrl = "jdbc:mysql://localhost:3306/bigua_socios_dev?allowPublicKeyRetrieval=true&useSSL=false"

    // GAM datastore connection information
    // config.Datastores["GAM"] = [:]
    // config.Datastores["GAM"].DatastoreServer = "localhost"
    // config.Datastores["GAM"].DatastoreUsername = "root"
    // config.Datastores["GAM"].DatastorePassword = "yt5XTLmduaZuj5zU"
    // config.Datastores["GAM"].DatastoreDatabase = "bigua_socios_dev_gam"
    // config.Datastores["GAM"].DatastoreUseJDBCCustomUrl = "True"
    // config.Datastores["GAM"].DatastoreJDBCCustomUrl = "jdbc:mysql://localhost:3306/bigua_socios_dev_gam?allowPublicKeyRetrieval=true&useSSL=false"

    //=======================================//
    // SMART DEVICES GENERATOR CONFIGURATION
    //=======================================//

    // Enable Android Build
    // config.SDGenerator.SmartDevicesBuildAndroid = "True"
    
    // Enable iOS Build
    // config.SDGenerator.SmartDevicesBuildIOS = "True"

    // Use dynamic URL
    // config.SDGenerator.SmartDevicesDynamicURL = "False"

    // Services URL
    // config.SDGenerator.SmartDevicesServicesURL = "http://localhost:8080/Bigua_SociosEnvLocal/"

    // iOS Settings
    // config.SDGenerator.SmartDevicesIOSMacHost = "192.168.125.32"
    // config.SDGenerator.SmartDevicesIOSMacUser = "dvelop"
    // config.SDGenerator.SmartDevicesIOSMacPassword = "facil12."
    // config.SDGenerator.SmartDevicesIOSExecutionType = "SimMac" // BuildIPA

    // Android Settings
    // config.SDGenerator.SmartDevicesAndroidSDK = "C:\\AndroidSDK"
    // config.SDGenerator.SmartDevicesAndroidKeystoreFile = "${env.WORKSPACE}\\projects\\bigua_socios\\android\\android.keystore"
    // config.SDGenerator.SmartDevicesAndroidKeyAlias = "DVelopersSD"
    // config.SDGenerator.SmartDevicesAndroidKeyStorePassword = "Zaq1Xsw2"
    // config.SDGenerator.SmartDevicesAndroidKeyPassword = "Zaq1Xsw2"
    // config.SDGenerator.SmartDevicesAndroidCompilationMode = "COMPILATION_MODE_DEVELOPMENT"

    //==========================================//
    // SMART DEVICES MAIN OBJECTS CONFIGURATION
    //==========================================//

    // DVelopersSD Application
    // config.SDMainObjects["SD_Bigua"] = [:]
    // config.SDMainObjects["SD_Bigua"].SmartDevicesVersionCode = "1.${env.BUILD_NUMBER}"
    // config.SDMainObjects["SD_Bigua"].SmartDevicesVersionName = "1.${env.BUILD_NUMBER}"
    // config.SDMainObjects["SD_Bigua"].SmartDevicesAppTitle = "Bigua Socios DEV"
    // config.SDMainObjects["SD_Bigua"].SmartDevicesObfuscateApplication = "False"
    // config.SDMainObjects["SD_Bigua"].SmartDevicesIdentifier = "uy.com.dvelop.bigua"
    // config.SDMainObjects["SD_Bigua"].SmartDevicesLogLevel = "Debug"
    // config.SDMainObjects["SD_Bigua"].SmartDevicesAnalyticsProvider = "Google"
    // config.SDMainObjects["SD_Bigua"].SmartDevicesGoogleAnalyticsTrackerId = "UA-152881789-1"

    // iOS Settings
    // config.SDMainObjects["SD_Bigua"].SmartDevicesIOSAppleTeamId = "T98RYRQ3P4"
    // config.SDMainObjects["SD_Bigua"].SmartDevicesIOSCertificate = "${env.WORKSPACE}\\projects\\bigua_socios\\ios\\ios_push_certificate.p12"
    // config.SDMainObjects["SD_Bigua"].SmartDevicesIOSCertificatePassword = "fatvvjiizh" 
    // config.SDMainObjects["SD_Bigua"].SmartDevicesIOSIPA = "${config.General.EnvironmentMobileFolder}\\iOS\\SD_Bigua\\SD_Bigua.ipa"

    // Android Settings
    // config.SDMainObjects["SD_Bigua"].SmartDevicesAndroidSenderId = "506543871814"
    // config.SDMainObjects["SD_Bigua"].SmartDevicesAndroidSenderApiKey = "AAAAdfBeF0Y:APA91bEMUYV_suZxDv9Ea1qhwvfRvneLEaSBd3IVHOw5HrN4uEPr7eozxtqy2my8F8L7rJWFKPQFSuv2L3LXlm1g4iU6ogajRwVtBr-LZPzHXe1Aro8qngrbY5WGLkMLmNkgfN12Bj9p"
    // config.SDMainObjects["SD_Bigua"].SmartDevicesAndroidAPK = "${config.General.EnvironmentMobileFolder}\\Android\\SD_Bigua\\build\\outputs\\apk\\debug\\SD_Bigua-debug.apk"
    // config.SDMainObjects["SD_Bigua"].SmartDevicesAndroidAAB = "${config.General.EnvironmentMobileFolder}\\Android\\SD_Bigua\\build\\outputs\\bundle\\release\\SD_Bigua-release.aab"

    //================================//
    // DEPLOYMENT UNITS CONFIGURATION
    //================================//

    // WebApp Deployment Unit
    // config.DeploymentUnits["WebAppDeploy"] = [:]
    // config.DeploymentUnits["WebAppDeploy"].DeployTargetId = "LOCAL" 
    // config.DeploymentUnits["WebAppDeploy"].DeploySelectedObjectsOnly = "False"
    // config.DeploymentUnits["WebAppDeploy"].DeployApplicationServer = "Tomcat 8.x"
    // config.DeploymentUnits["WebAppDeploy"].DeployUseAppServerDatasource = "False" 
    // config.DeploymentUnits["WebAppDeploy"].DeployIncludeGAM = "False" 
    // config.DeploymentUnits["WebAppDeploy"].DeployTargetJRE = "9" 
    // config.DeploymentUnits["WebAppDeploy"].DeployPackageFormat = "Automatic" 
    // config.DeploymentUnits["WebAppDeploy"].DeployTimeStamp = "${env.BUILD_TIMESTAMP}"
    // config.DeploymentUnits["WebAppDeploy"].DeployFileFullPath = "${env.WORKSPACE}" 
    // config.DeploymentUnits["WebAppDeploy"].DeployWebappName = "BiguaSociosWebApp" 
    // config.DeploymentUnits["WebAppDeploy"].DeployProjectName = "BiguaSociosWebApp${config.General.VersionSuffix}" 
    // config.DeploymentUnits["WebAppDeploy"].DeployFullPath = "${env.WORKSPACE}\\BiguaSociosWebApp${config.General.VersionSuffix}" 
    // config.DeploymentUnits["WebAppDeploy"].DeployObjectNames = "DeploymentUnit:WebAppDeploy" 

    // Jobs Services Deployment Unit
    // config.DeploymentUnits["JobsDeploy"] = [:]
    // config.DeploymentUnits["JobsDeploy"].DeployTargetId = "LOCAL" 
    // config.DeploymentUnits["JobsDeploy"].DeploySelectedObjectsOnly = "False"
    // config.DeploymentUnits["JobsDeploy"].DeployApplicationServer = "Tomcat 8.x"
    // config.DeploymentUnits["JobsDeploy"].DeployUseAppServerDatasource = "False" 
    // config.DeploymentUnits["JobsDeploy"].DeployIncludeGAM = "False" 
    // config.DeploymentUnits["JobsDeploy"].DeployTargetJRE = "9" 
    // config.DeploymentUnits["JobsDeploy"].DeployPackageFormat = "Automatic" 
    // config.DeploymentUnits["JobsDeploy"].DeployTimeStamp = "${env.BUILD_TIMESTAMP}"
    // config.DeploymentUnits["JobsDeploy"].DeployFileFullPath = "${env.WORKSPACE}" 
    // config.DeploymentUnits["JobsDeploy"].DeployWebappName = "BiguaJobs" 
    // config.DeploymentUnits["JobsDeploy"].DeployProjectName = "BiguaJobs${config.General.VersionSuffix}" 
    // config.DeploymentUnits["JobsDeploy"].DeployFullPath = "${env.WORKSPACE}\\BiguaJobs${config.General.VersionSuffix}" 
    // config.DeploymentUnits["JobsDeploy"].DeployObjectNames = "DeploymentUnit:JobsDeploy" 

    //==========================//
    // APP CENTER CONFIGURATION
    //==========================//

    // AppCenter settings
    // config.AppCenter.AppCenterDistribute = "True"
    // config.AppCenter.AppCenterToken = "f751744271f6191e5a848b6964883a56308454ee"
    // config.AppCenter.AppCenterGroup = "DVTesters"
    // config.AppCenter.AppCenterOwner = "DVelopSoftware"

    //=====================================//
    // JENKINS NOTIFICATIONS CONFIGURATION
    //=====================================//

    // config.Jenkins.ProjectMailAddress = "bigua@dvelop.com.uy"
    // config.Jenkins.ProjectGoogleChatRoom = "https://chat.googleapis.com/v1/spaces/AAAAtbSIUY4/messages?key=AIzaSyDdI0hCZtE6vySjMm-WEfRq3CPzqKqqsHI&token=1fCYmPzoJ_6mW8P0FVthGhlSt7A3Hq7cvDZI9dGCnRA%3D"

    if(envName.equals("EnvLocal")) {
    
        // GXServer Version
        // config.GXServer.GXServerVersion = "StableVersion"
        // config.GXServer.BringChangesVersionFrom = "Bigua_Socios"
        
        // Use parameter encryption in URLs
        // config.Environment.UseEncryption = "SITE"

        // Use HTTP or HTTPS protocol
        // config.Environment.HttpProtocol = "Secure"

        // Default datastore connection information
        // config.Datastores["Default"].DatastoreDatabase = "bigua_socios_test"
        // config.Datastores["Default"].DatastoreJDBCCustomUrl = "jdbc:mysql://localhost:3306/bigua_socios_test?allowPublicKeyRetrieval=true&useSSL=false"

        // GAM datastore connection information
        // config.Datastores["GAM"].DatastoreDatabase = "bigua_socios_test_gam"
        // config.Datastores["GAM"].DatastoreJDBCCustomUrl = "jdbc:mysql://localhost:3306/bigua_socios_test_gam?allowPublicKeyRetrieval=true&useSSL=false"
        
        // Services URL
        // config.SDGenerator.SmartDevicesServicesURL = "https://vm-bigua.eastus.cloudapp.azure.com:8443/BiguaSociosTestWebApp/"

        // DVelopersSD Application
        // config.SDMainObjects["SD_Bigua"].SmartDevicesAppTitle = "Bigua Socios TEST"
        // config.SDMainObjects["SD_Bigua"].SmartDevicesIdentifier = "uy.com.dvelop.bigua"
        // config.SDMainObjects["SD_Bigua"].AppCenterNameIOS = "BiguaSD-iOS-Test"
        // config.SDMainObjects["SD_Bigua"].AppCenterNameAndroid = "BiguaSD-Android-Test"
        // config.SDMainObjects["SD_Bigua"].SmartDevicesDeepLinkBaseURL = "https://vm-bigua.eastus.cloudapp.azure.com:8443/"
        
            
        // Android Settings
        // config.SDMainObjects["SD_Bigua"].SmartDevicesAndroidSenderId = "640211082810"
        // config.SDMainObjects["SD_Bigua"].SmartDevicesAndroidSenderApiKey = "AAAAlQ-N3jo:APA91bHZEpNt-A_xdRBks6w9fhCUPJwoABB8Xy891jfqIBGOh_Y5Hw44QGxOrkpZ2R8lxyG3ec4NrPAaJHB__zq3A_Ct83EfaTPzuCkPEANNF73x9qngOJZK9C1Mi9oHvJGLlYHObdmO"
 
        // Tomcat deploy settings
        // config.DeploymentUnits["WebAppDeploy"].DeployWebappName = "BiguaSociosTestWebApp"     
        // config.DeploymentUnits["WebAppDeploy"].TomcatHost = "http://tomcatadmin:yt5XTLmduaZuj5zU@vm-bigua.eastus.cloudapp.azure.com:8080/"
        // config.DeploymentUnits["WebAppDeploy"].TomcatWebapp = "BiguaSociosTestWebApp"

        // Jobs deploy settings
        // config.DeploymentUnits["JobsDeploy"].JobsDirectory = "\\\\dvelopjava1.dvelop.intra\\Jobs"
    }

    if(envName.equals("EnvQA")) {
    
        // GXServer Version
        // config.GXServer.GXServerVersion = "ReleaseVersion"
        // config.GXServer.BringChangesVersionFrom = "StableVersion"

        // Freeze Version after successful deploy
        // config.GXServer.GXServerFreezeAfterDeploy = "False"

        // Use parameter encryption in URLs
        // config.Environment.UseEncryption = "SITE"

        // Use HTTP or HTTPS protocol
        // config.Environment.HttpProtocol = "Secure"

        // Default datastore connection information
        // config.Datastores["Default"].DatastoreDatabase = "bigua_socios_qa"
        // config.Datastores["Default"].DatastoreJDBCCustomUrl = "jdbc:mysql://localhost:3306/bigua_socios_qa?allowPublicKeyRetrieval=true&useSSL=false"

        // GAM datastore connection information
        // config.Datastores["GAM"].DatastoreDatabase = "bigua_socios_qa_gam"
        // config.Datastores["GAM"].DatastoreJDBCCustomUrl = "jdbc:mysql://localhost:3306/bigua_socios_qa_gam?allowPublicKeyRetrieval=true&useSSL=false"
        
        // Services URL
        // config.SDGenerator.SmartDevicesServicesURL = "https://vm-bigua.eastus.cloudapp.azure.com:8443/BiguaSociosQAWebApp/"

        // DVelopersSD Application
        // config.SDMainObjects["SD_Bigua"].SmartDevicesAppTitle = "Bigua Socios QA"
        // config.SDMainObjects["SD_Bigua"].SmartDevicesAndroidIdentifier = "uy.com.bigua.BiguaSociosSDQA"
        // config.SDMainObjects["SD_Bigua"].SmartDevicesIOSIdentifier = "uy.com.bigua.BiguaSociosSDQA"
        // config.SDMainObjects["SD_Bigua"].AppCenterNameIOS = "BiguaSD-iOS-QA"
        // config.SDMainObjects["SD_Bigua"].AppCenterNameAndroid = "BiguaSD-Android-QA"        
        // config.SDMainObjects["SD_Bigua"].SmartDevicesDeepLinkBaseURL = "https://vm-bigua.eastus.cloudapp.azure.com:8443/"
            
        // Android Settings
        // config.SDMainObjects["SD_Bigua"].SmartDevicesAndroidSenderId = "640211082810"
        // config.SDMainObjects["SD_Bigua"].SmartDevicesAndroidSenderApiKey = "AAAAlQ-N3jo:APA91bHZEpNt-A_xdRBks6w9fhCUPJwoABB8Xy891jfqIBGOh_Y5Hw44QGxOrkpZ2R8lxyG3ec4NrPAaJHB__zq3A_Ct83EfaTPzuCkPEANNF73x9qngOJZK9C1Mi9oHvJGLlYHObdmO"
 
        // Tomcat deploy settings
        // config.DeploymentUnits["WebAppDeploy"].DeployWebappName = "BiguaSociosQAWebApp"     
        // config.DeploymentUnits["WebAppDeploy"].TomcatHost = "http://tomcatadmin:yt5XTLmduaZuj5zU@vm-bigua.eastus.cloudapp.azure.com:8080/"
        // config.DeploymentUnits["WebAppDeploy"].TomcatWebapp = "BiguaSociosQAWebApp"

        // Jobs deploy settings
        // config.DeploymentUnits["JobsDeploy"].JobsDirectory = "\\\\dvelopjava1.dvelop.intra\\Jobs"
    }

    if(envName.equals("EnvProd")) {

        // config.General.RunDataLoad = "False"

        // GXServer Version
        // config.GXServer.GXServerVersion = "ReleaseVersion"
        // config.GXServer.BringChangesVersionFrom = "StableVersion"

        // Freeze Version after successful deploy
        // config.GXServer.GXServerFreezeAfterDeploy = "True"

        // Use parameter encryption in URLs
        // config.Environment.UseEncryption = "SITE"

        // Use HTTP or HTTPS protocol
        // config.Environment.HttpProtocol = "Secure"
        
        // Reorganize GAM Tables        
        // config.Environment.ReorganizeGamDatabase = "False"

        // GAM connection information
        // config.Environment.GAMAdminUsername = "admin"
        // config.Environment.GAMAdminPassword = "Bigua2021!"

        // Reoganize server tables
        // config.WebGenerator.ReorganizeServerTables = "No"
        
        // Notifications Provider    
        // config.WebGenerator.NotificationsProvider = "OneSignal"
        // config.WebGenerator.OneSignalAppId = "884aa7dd-5ddf-41a0-9476-db2d4816cd9d"
        // config.WebGenerator.OneSignalApiKey = "MTQ1MmVmYTAtOTA1Ny00ODlkLWFhZTgtNWYzYmYwYzIyZWNk"

        // Default datastore connection information
        // config.Datastores["Default"].DatastoreServer = "localhost"
        // config.Datastores["Default"].DatastoreUsername = "root"
        // config.Datastores["Default"].DatastorePassword = "H7e+gXInxOyK8Lk5OWmZaf"
        // config.Datastores["Default"].DatastoreDatabase = "bigua_socios_prod"
        // config.Datastores["Default"].DatastoreJDBCCustomUrl = "jdbc:mysql://localhost:3306/bigua_socios_prod?allowPublicKeyRetrieval=true&useSSL=false"

        // GAM datastore connection information
        // config.Datastores["GAM"].DatastoreServer = "localhost"
        // config.Datastores["GAM"].DatastoreUsername = "root"
        // config.Datastores["GAM"].DatastorePassword = "H7e+gXInxOyK8Lk5OWmZaf"
        // config.Datastores["GAM"].DatastoreDatabase = "bigua_socios_prod_gam"
        // config.Datastores["GAM"].DatastoreJDBCCustomUrl = "jdbc:mysql://localhost:3306/bigua_socios_prod_gam?allowPublicKeyRetrieval=true&useSSL=false"
        
        // Services URL
        // config.SDGenerator.SmartDevicesServicesURL = "https://bigua.uy/"

        
        // config.SDGenerator.SmartDevicesIOSExecutionType = "BuildIPA"

        // Android KeyStore
        // config.SDGenerator.SmartDevicesAndroidKeystoreFile = "${env.WORKSPACE}\\projects\\bigua_socios\\android\\biguasocios.keystore"
        // config.SDGenerator.SmartDevicesAndroidKeyAlias = "biguasocios"
        // config.SDGenerator.SmartDevicesAndroidKeyStorePassword = "xsLh9ASsrDDp8yfn"
        // config.SDGenerator.SmartDevicesAndroidKeyPassword = "xsLh9ASsrDDp8yfn"
        // config.SDGenerator.SmartDevicesAndroidCompilationMode = "COMPILATION_MODE_DISTRIBUTION"

        // DVelopersSD Application
        // config.SDMainObjects["SD_Bigua"].SmartDevicesAppTitle = "Bigua Socios"
        // config.SDMainObjects["SD_Bigua"].SmartDevicesIdentifier = "uy.com.biguasocios"
        //config.SDMainObjects["SD_Bigua"].AppCenterNameIOS = "BiguaSD-iOS-PROD"
        //config.SDMainObjects["SD_Bigua"].AppCenterNameAndroid = "BiguaSD-Android-PROD"        
        // config.SDMainObjects["SD_Bigua"].SmartDevicesDeepLinkBaseURL = "https://bigua.uy/"
        
        // NO PARECE ESTAR FUNCIONANDO, HACE QUE SE CAIGA LA APP
        //config.SDMainObjects["SD_Bigua"].SmartDevicesObfuscateApplication = "True"
        // config.SDMainObjects["SD_Bigua"].SmartDevicesLogLevel = "Debug"

        // iOS Settings
        // config.SDMainObjects["SD_Bigua"].SmartDevicesIOSAppleTeamId = "AGD566QUD5"
        // config.SDMainObjects["SD_Bigua"].SmartDevicesIOSCertificate = "${env.WORKSPACE}\\projects\\bigua_socios\\ios\\biguasocios.p12"
        // config.SDMainObjects["SD_Bigua"].SmartDevicesIOSCertificatePassword = "xsLh9ASsrDDp8yfn"
            
        // Android Settings
        // config.SDMainObjects["SD_Bigua"].SmartDevicesAndroidSenderId = "278068710405"
        // config.SDMainObjects["SD_Bigua"].SmartDevicesAndroidSenderApiKey = "AAAAQL4vzAU:APA91bGPgXSVFMxqt0THj0M0JgHYhLa24d9Xvx5sPTJxHitoN9MQ0lXMQ82ncbWhERO7wJPEXsgQ3NsMiJckqj6k5V7EGVO2TJ4i63Iy66C3vDWw46q1kOiPpl04_PeD11FWKRLxJW6k"
 
        // Tomcat deploy settings
        // config.DeploymentUnits["WebAppDeploy"].DeployWebappName = "ROOT"

        // Jobs deploy settings
        // config.DeploymentUnits["JobsDeploy"].JobsDirectory = "\\\\dvelopjava1.dvelop.intra\\Jobs"

        // config.AppCenter.AppCenterDistribute = "False"
    }

    return config
}

return this
