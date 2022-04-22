def getForEnvironment(envName, envVersion) {

    // Configuration root object
    def config              = [:]
    config.General          = [:]
    config.Git              = [:]
    config.GXServer         = [:]    
    config.Environment      = [:]
    config.Datastores       = [:]
    config.DeploymentUnits  = [:]
    config.WebGenerator     = [:]
    config.SDGenerator     = [:]

    config.General.Name                     = "SIG"
    config.General.WebAppName               = "sig"
    config.General.VersionSuffix            = "_v${env.BUILD_NUMBER}_${envName}"
    config.General.GXProgramDirKey          = "gx-programdir-17U7"
    config.General.DeploymentPlatform       = "OKD"

    config.WebGenerator.LogLevelGX = "ERROR"
    config.WebGenerator.LogLevelUser = "WARN"
    config.WebGenerator.LogOutput = "File"
    config.WebGenerator.LogFile = "/app/logs/client.log"    
    
    config.General.ForceRebuild         = "False"  

    //================================//
    // DEPLOYMENT UNITS CONFIGURATION
    //================================//

    config.DeploymentUnits["DUWeb"]                              = [:]
    config.DeploymentUnits["DUWeb"].DeployTargetId               = "LOCAL" 
    config.DeploymentUnits["DUWeb"].DeployApplicationCompiler    = "JAVA" 
    config.DeploymentUnits["DUWeb"].DeploySelectedObjectsOnly    = "False"
    config.DeploymentUnits["DUWeb"].DeployApplicationServer      = "Tomcat 8.x"
    config.DeploymentUnits["DUWeb"].DeployUseAppServerDatasource = "False" 
    config.DeploymentUnits["DUWeb"].DeployIncludeGAM             = "False" 
    config.DeploymentUnits["DUWeb"].DeployGXflowbackoffice       = "True" 
    config.DeploymentUnits["DUWeb"].DeployTargetJRE              = "9" 
    config.DeploymentUnits["DUWeb"].DeployPackageFormat          = "Automatic" 
    config.DeploymentUnits["DUWeb"].DeployTimeStamp              = "${env.BUILD_TIMESTAMP}"
    config.DeploymentUnits["DUWeb"].DeployFileFullPath           = "${env.WORKSPACE}" 
    config.DeploymentUnits["DUWeb"].DeployWebappName             = config.General.Name
    config.DeploymentUnits["DUWeb"].DeployProjectName            = "${config.General.WebAppName}${config.General.VersionSuffix}" 
    config.DeploymentUnits["DUWeb"].DeployFullPath               = "${env.WORKSPACE}\\${config.General.WebAppName}${config.General.VersionSuffix}" 
    config.DeploymentUnits["DUWeb"].DeployObjectNames            = "DeploymentUnit:DUWeb"      
    config.DeploymentUnits["DUWeb"].VersionEnvironment           = "web"   

    // config.DeploymentUnits["DUMobile"]                              = [:]
    // config.DeploymentUnits["DUMobile"].DeployTargetId               = "LOCAL" 
    // config.DeploymentUnits["DUMobile"].DeployApplicationCompiler    = "JAVA" 
    // config.DeploymentUnits["DUMobile"].DeploySelectedObjectsOnly    = "False"
    // config.DeploymentUnits["DUMobile"].DeployApplicationServer      = "Tomcat 8.x"
    // config.DeploymentUnits["DUMobile"].DeployUseAppServerDatasource = "False" 
    // config.DeploymentUnits["DUMobile"].DeployIncludeGAM             = "False" 
    // config.DeploymentUnits["DUMobile"].DeployGXflowbackoffice       = "True" 
    // config.DeploymentUnits["DUMobile"].DeployTargetJRE              = "9" 
    // config.DeploymentUnits["DUMobile"].DeployPackageFormat          = "Automatic" 
    // config.DeploymentUnits["DUMobile"].DeployTimeStamp              = "${env.BUILD_TIMESTAMP}"    
    // config.DeploymentUnits["DUMobile"].DeployFileFullPath           = "${env.WORKSPACE}" 
    // config.DeploymentUnits["DUMobile"].DeployWebappName             = config.General.Name
    // config.DeploymentUnits["DUMobile"].DeployProjectName            = "${config.General.WebAppName}${config.General.VersionSuffix}" 
    // config.DeploymentUnits["DUMobile"].DeployFullPath               = "${env.WORKSPACE}\\${config.General.WebAppName}${config.General.VersionSuffix}" 
    // config.DeploymentUnits["DUMobile"].DeployObjectNames            = "DeploymentUnit:DUMobile"     
    // config.DeploymentUnits["DUMobile"].VersionEnvironment           = "mobile"     

    if(envName.equals("EnvLocal")) {

        config.General.WorkingEnvironment       = "EnvLocal"    
        config.General.DeployEnvironment        = "EnvLocal"  
        config.General.WorkingVersion           = "SIG" 
        config.General.GXWorkingDirectoryKey    = "gx-sig_local-workingdir"

        //=======================//
        // GENERAL CONFIGURATION
        //=======================//     

        config.Datastores["Default"] = [:]
        config.Datastores["Default"].DatastoreServer = "localhost"
        config.Datastores["Default"].DatastoreDatabase = "SIGEnvLocal"
        config.Datastores["Default"].DatastoreUseJDBCCustomUrl = "True"
        config.Datastores["Default"].CredentialId = "localhost"          

        config.Datastores["GAM"] = [:]
        config.Datastores["GAM"].DatastoreServer = "localhost"
        config.Datastores["GAM"].DatastoreDatabase = "SIGEnvLocal_GAM"
        config.Datastores["GAM"].DatastoreUseJDBCCustomUrl = "True" 
        config.Datastores["GAM"].CredentialId = "localhost"        

        config.Environment.GAMRepositoryId  = "9ef870c2-ab9a-442e-80ae-b9669c263c5f" 

        config.Datastores["MdPStaging"] = [:]
        config.Datastores["MdPStaging"].DatastoreServer = "localhost"
        config.Datastores["MdPStaging"].DatastoreDatabase = "SIGEnvLocal"
        config.Datastores["MdPStaging"].DatastoreUseJDBCCustomUrl = "True"
        config.Datastores["MdPStaging"].CredentialId = "localhost"   

        config.Datastores["Portal"] = [:]
        config.Datastores["Portal"].DatastoreServer = "localhost"
        config.Datastores["Portal"].DatastoreDatabase = "SIGEnvLocal_GAM"
        config.Datastores["Portal"].DatastoreUseJDBCCustomUrl = "True" 
        config.Datastores["Portal"].CredentialId = "localhost"        

        //================================//
        // GIT CONFIGURATION
        //================================//    

        config.Git.GitEnvironment = envVersion.equals("mobile") == true ? "qa-${envVersion}" : "qa" //GitHub Testing  

        // Android Settings
        config.SDGenerator.SmartDevicesAndroidSDK               = "C:\\Users\\ssouto\\AppData\\Local\\Android\\Sdk"
        config.SDGenerator.SmartDevicesAndroidKeystoreFile      = "C:\\Users\\ssouto\\Desktop\\Proyectos\\MdP\\Campo\\campoenvlocal.keystore"
        config.SDGenerator.SmartDevicesAndroidKeyAlias          = "ssouto"
        config.SDGenerator.SmartDevicesAndroidKeyStorePassword  = "121212"
        config.SDGenerator.SmartDevicesAndroidKeyPassword       = "121212"
        config.SDGenerator.SmartDevicesAndroidCompilationMode   = "COMPILATION_MODE_DISTRIBUTION"                  

    }

    if(envName.equals("EnvStable")) {

        config.General.WorkingEnvironment       = "EnvRelease"   
        config.General.DeployEnvironment        = "EnvStable"  
        config.General.WorkingVersion           = "SIG" 
        config.General.GXWorkingDirectoryKey    = "gx-sig_stable-workingdir" 

        //=======================//
        // GENERAL CONFIGURATION
        //=======================//        
        config.Datastores["Default"] = [:]
        config.Datastores["Default"].DatastoreServer = "uycls267.mdp.local"
        config.Datastores["Default"].DatastoreDatabase = "SIG_Dev"
        config.Datastores["Default"].DatastoreUseJDBCCustomUrl = "False"
        config.Datastores["Default"].CredentialId = "BDServer_uycls267"

        config.Datastores["GAM"] = [:]
        config.Datastores["GAM"].DatastoreServer = "uycls267.mdp.local"
        config.Datastores["GAM"].DatastoreDatabase = "Portal_Dev"
        config.Datastores["GAM"].DatastoreUseJDBCCustomUrl = "False" 
        config.Datastores["GAM"].CredentialId = "BDServer_uycls267"          

        config.Environment.GAMRepositoryId  = "41474542-c051-4909-b51c-607730d0da8d" 

        config.Datastores["MdPStaging"] = [:]
        config.Datastores["MdPStaging"].DatastoreServer = "uycls205.mdp.local"
        config.Datastores["MdPStaging"].DatastoreDatabase = "MdP_Staging"
        config.Datastores["MdPStaging"].DatastoreUseJDBCCustomUrl = "False"  
        config.Datastores["MdPStaging"].CredentialId = "BDServer_uycls205"

        config.Datastores["Portal"] = [:]
        config.Datastores["Portal"].DatastoreServer = "uycls267.mdp.local"
        config.Datastores["Portal"].DatastoreDatabase = "Portal_Dev"
        config.Datastores["Portal"].DatastoreUseJDBCCustomUrl = "False"  
        config.Datastores["Portal"].CredentialId = "BDServer_uycls267"    

        config.Git.GitEnvironment = envVersion.equals("mobile") == true ? "dev-${envVersion}" : "dev" //GitHub MdP    

        // Services URL
        config.SDGenerator.SmartDevicesServicesURL  = "http://sigdev.mdp.local/"     

        // Android Settings
        config.SDGenerator.SmartDevicesAndroidSDK               = "C:\\Android-SDK"
        config.SDGenerator.SmartDevicesAndroidKeystoreFile      = "C:\\DVelop\\dvelop-montes.keystore"
        config.SDGenerator.SmartDevicesAndroidKeyAlias          = "dvelop-montes"
        config.SDGenerator.SmartDevicesAndroidKeyStorePassword  = "montes"
        config.SDGenerator.SmartDevicesAndroidKeyPassword       = "montes"
        config.SDGenerator.SmartDevicesAndroidCompilationMode   = "COMPILATION_MODE_DISTRIBUTION"            

    }

    if(envName.equals("EnvRelease")) {

        config.General.WorkingEnvironment       = "EnvRelease"  
        config.General.DeployEnvironment        = "EnvRelease"  
        config.General.WorkingVersion           = "SIG"  
        config.General.GXWorkingDirectoryKey    = "gx-sig_release-workingdir" 

        //=======================//
        // GENERAL CONFIGURATION
        //=======================//        
        config.Datastores["Default"] = [:]
        config.Datastores["Default"].DatastoreServer = "localhost"
        config.Datastores["Default"].DatastoreDatabase = "SIGRelease"
        config.Datastores["Default"].DatastoreUseJDBCCustomUrl = "False"
        config.Datastores["Default"].CredentialId = "BDServer_localhost"

        config.Datastores["GAM"] = [:]
        config.Datastores["GAM"].DatastoreServer = "uycls267.mdp.local"
        config.Datastores["GAM"].DatastoreDatabase = "Portal_Qa"
        config.Datastores["GAM"].DatastoreUseJDBCCustomUrl = "False"  
        config.Datastores["GAM"].CredentialId = "BDServer_uycls267"         

        config.Environment.GAMRepositoryId  = "41474542-c051-4909-b51c-607730d0da8d" 

        config.Datastores["MdPStaging"] = [:]
        config.Datastores["MdPStaging"].DatastoreServer = "uycls205.mdp.local"
        config.Datastores["MdPStaging"].DatastoreDatabase = "MdP_Staging"
        config.Datastores["MdPStaging"].DatastoreUseJDBCCustomUrl = "False" 
        config.Datastores["MdPStaging"].CredentialId = "BDServer_uycls205"  

        config.Datastores["Portal"] = [:]
        config.Datastores["Portal"].DatastoreServer = "uycls267.mdp.local"
        config.Datastores["Portal"].DatastoreDatabase = "Portal_Qa"
        config.Datastores["Portal"].DatastoreUseJDBCCustomUrl = "False" 
        config.Datastores["Portal"].CredentialId = "BDServer_uycls267"         

        config.Git.GitEnvironment = envVersion.equals("mobile") == true ? "qa-${envVersion}" : "qa" //GitHub MdP

        // Services URL
        config.SDGenerator.SmartDevicesServicesURL  = "https://sigqa.montesdelplata.com.uy/"  

        // Android Settings
        config.SDGenerator.SmartDevicesAndroidSDK               = "C:\\Android-SDK"
        config.SDGenerator.SmartDevicesAndroidKeystoreFile      = "C:\\DVelop\\dvelop-montes.keystore"
        config.SDGenerator.SmartDevicesAndroidKeyAlias          = "dvelop-montes"
        config.SDGenerator.SmartDevicesAndroidKeyStorePassword  = "montes"
        config.SDGenerator.SmartDevicesAndroidKeyPassword       = "montes"
        config.SDGenerator.SmartDevicesAndroidCompilationMode   = "COMPILATION_MODE_DISTRIBUTION"         
    }

    withCredentials([
        string(
            credentialsId: config.General.GXProgramDirKey, 
            variable: 'programdir'),
        string(
            credentialsId: config.General.GXWorkingDirectoryKey, 
            variable: 'workingdir')
        ]) {    
        config.General.GX_PROGRAM_DIR   = "${programdir}"
        config.General.WorkingDirectory = "${workingdir}"
    }    

    //=========================================//
    // PATHING CONFIGURATION
    //=========================================//    

    config.General.EnvironmentRelativePath  = "${config.General.WorkingEnvironment}"
    config.General.EnvironmentRootFolder    = "${config.General.WorkingDirectory}\\${config.General.EnvironmentRelativePath}"
    config.General.EnvironmentWebFolder     = "${config.General.EnvironmentRootFolder}\\web"
    config.General.EnvironmentMobileFolder  = "${config.General.EnvironmentRootFolder}\\mobile"

    withCredentials([
        string(
            credentialsId: 'git-path', 
            variable: 'path')
        ]) {    
        config.Git.GitFolder    = "${path}"
        config.Git.GitEnvFolder = "${path}${config.General.Name}_${config.Git.GitEnvironment}"
    }        

    //=========================================//
    // GENEXUS SERVER CONNECTION CONFIGURATION
    //=========================================//

    config.GXServer.GXServerUrl                 = "http://gxserver.montesdelplata.com.uy/genexusserver17"
    config.GXServer.GXServerCredentialsKey      = "credentials-genexusserver17"
    // config.GXServer.GXServerUsername            = ""
    // config.GXServer.GXServerPassword            = ""
    config.GXServer.GXServerKB                  = config.General.WorkingVersion
    config.GXServer.GXServerVersion             = config.General.WorkingVersion 
    config.GXServer.GXServerChangelog           = "${env.WORKSPACE}\\Changelog${config.General.VersionSuffix}.xml" 
    config.GXServer.GXServerFreezeAfterDeploy   = "False"     

    // GAM connection information
    config.Environment.GAMAdminCredentialKey        = "credentials-gamadmin"
    // config.Environment.GAMAdminUsername             = ""
    // config.Environment.GAMAdminPassword             = ""
    config.Environment.GAMConnectionCredentialKey   = "credentials-sig-gamconnection"
    // config.Environment.GAMConnectionUsername        = ""
    // config.Environment.GAMConnectionPassword        = ""

    // Use parameter encryption in URLs
    config.Environment.UseEncryption = "NO"

    // Use HTTP or HTTPS protocol
    config.Environment.HttpProtocol = "Secure"

    // Set Environment Path in disk
    config.Environment.TargetPath = "${config.General.EnvironmentRelativePath}"  

    // Esta reorganización simplemente mantiene a GAM actualizado, pero nunca va crear la tabla de GAM siempre y cuando esta exista. (Creación de permisos, contemplación de cambio de versión sobre migraciones, entre otros)     
    config.Environment.ReorganizeGamDatabase    = "True" 

    // Con este parámetro evitamos que se realice una reoganización de las tablas correspondientes al sistema en si mismo   
    config.Environment.DoNotExecuteReorg        = "True"       

    return config
}

return this