def getForEnvironment(envName) {

    // Configuration root object
    def config              = [:]
    config.General          = [:]
    config.Git              = [:]
    config.GXServer         = [:]    
    config.Environment      = [:]
    config.Datastores       = [:]
    config.DeploymentUnits  = [:]

    config.General.WorkingVersion           = "Canastas" 
    config.General.WebAppName               = "canastas"
    config.General.VersionSuffix            = "_v${env.BUILD_NUMBER}_${envName}_${env.JOB_NAME}"
    config.General.GXProgramDirKey          = "gx-programdir"
    config.General.GXWorkingDirectoryKey    = "gx-canastas-workingdir"

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
    config.General.ForceRebuild         = "False"  

    //================================//
    // DEPLOYMENT UNITS CONFIGURATION
    //================================//

    config.DeploymentUnits["WebAppDeploy"]                              = [:]
    config.DeploymentUnits["WebAppDeploy"].DeployTargetId               = "LOCAL" 
    config.DeploymentUnits["WebAppDeploy"].DeployApplicationCompiler    = "JAVA" 
    config.DeploymentUnits["WebAppDeploy"].DeploySelectedObjectsOnly    = "False"
    config.DeploymentUnits["WebAppDeploy"].DeployApplicationServer      = "Tomcat 8.x"
    config.DeploymentUnits["WebAppDeploy"].DeployUseAppServerDatasource = "False" 
    config.DeploymentUnits["WebAppDeploy"].DeployIncludeGAM             = "False" 
    config.DeploymentUnits["WebAppDeploy"].DeployTargetJRE              = "9" 
    config.DeploymentUnits["WebAppDeploy"].DeployPackageFormat          = "Automatic" 
    config.DeploymentUnits["WebAppDeploy"].DeployTimeStamp              = "${env.BUILD_TIMESTAMP}"
    config.DeploymentUnits["WebAppDeploy"].DeployFileFullPath           = "${env.WORKSPACE}" 
    config.DeploymentUnits["WebAppDeploy"].DeployWebappName             = config.General.WebAppName
    config.DeploymentUnits["WebAppDeploy"].DeployProjectName            = "${config.General.WebAppName}${config.General.VersionSuffix}" 
    config.DeploymentUnits["WebAppDeploy"].DeployFullPath               = "${env.WORKSPACE}\\${config.General.WebAppName}${config.General.VersionSuffix}" 
    config.DeploymentUnits["WebAppDeploy"].DeployObjectNames            = "DeploymentUnit:DeploymentUnit"   

    if(envName.equals("EnvLocal")) {

        config.General.DBCredentialKey          = "jdbc-sqlserver"
        config.General.WorkingEnvironment       = "EnvLocal"    

        //=======================//
        // GENERAL CONFIGURATION
        //=======================//        
        config.Datastores["Default"] = [:]
        config.Datastores["Default"].DatastoreServer = "localhost"
        config.Datastores["Default"].DatastoreDatabase = "Canastas_Local"
        config.Datastores["Default"].DatastoreUseJDBCCustomUrl = "True"
        config.Datastores["Default"].DatastoreJDBCCustomUrl = "jdbc:jtds:sqlserver://localhost:1433/Canastas_Local"            

        // GAM datastore connection information
        config.Datastores["GAM"] = [:]
        config.Datastores["GAM"].DatastoreServer = "localhost"
        config.Datastores["GAM"].DatastoreDatabase = "CanastasGAM_Local"
        config.Datastores["GAM"].DatastoreUseJDBCCustomUrl = "True"
        config.Datastores["GAM"].DatastoreJDBCCustomUrl = "jdbc:jtds:sqlserver://localhost:1433/CanastasGAM_Local"            

        config.Environment.GAMRepositoryId  = "fb79fce5-453d-43ac-9457-f93fd88a2810"  

        //================================//
        // GIT CONFIGURATION
        //================================//    

        config.Git.GitEnvironment = "qa" //GitHub Testing           

    }

    if(envName.equals("EnvStable")) {

        config.General.DBCredentialKey          = "jdbc-sqlserver"
        config.General.WorkingEnvironment       = "EnvRelease"    

        //=======================//
        // GENERAL CONFIGURATION
        //=======================//        
        config.Datastores["Default"] = [:]
        config.Datastores["Default"].DatastoreServer = "uycls267.mdp.local"
        config.Datastores["Default"].DatastoreDatabase = "Canastas_Desarrollo"
        config.Datastores["Default"].DatastoreUseJDBCCustomUrl = "True"
        config.Datastores["Default"].DatastoreJDBCCustomUrl = "jdbc:jtds:sqlserver://uycls267.mdp.local:1433/Canastas_Desarrollo"            

        // GAM datastore connection information
        config.Datastores["GAM"] = [:]
        config.Datastores["GAM"].DatastoreServer = "localhost"
        config.Datastores["GAM"].DatastoreDatabase = "CanastasGAM_Desarrollo"
        config.Datastores["GAM"].DatastoreUseJDBCCustomUrl = "True"
        config.Datastores["GAM"].DatastoreJDBCCustomUrl = "jdbc:jtds:sqlserver://uycls267.mdp.local:1433/CanastasGAM_Desarrollo"             

        config.Environment.GAMRepositoryId  = "ca9bc12d-ec23-496a-bf95-f919629189ef"

        config.Git.GitEnvironment = "qa" //GitHub MdP    

    }

    if(envName.equals("EnvRelease")) {

        config.General.DBCredentialKey          = "jdbc-sqlserver"
        config.General.WorkingEnvironment       = "EnvRelease"    

        //=======================//
        // GENERAL CONFIGURATION
        //=======================//        
        config.Datastores["Default"] = [:]
        config.Datastores["Default"].DatastoreServer = "uycls266.mdp.local:1433"
        config.Datastores["Default"].DatastoreDatabase = "Canastas_Desarrollo"
        config.Datastores["Default"].DatastoreUseJDBCCustomUrl = "True"
        config.Datastores["Default"].DatastoreJDBCCustomUrl = "jdbc:jtds:sqlserver://uycls266.mdp.local:1433:1433/Canastas_Produccion"            

        // GAM datastore connection information
        config.Datastores["GAM"] = [:]
        config.Datastores["GAM"].DatastoreServer = "uycls266.mdp.local"
        config.Datastores["GAM"].DatastoreDatabase = "CanastasGAM_Desarrollo"
        config.Datastores["GAM"].DatastoreUseJDBCCustomUrl = "True"
        config.Datastores["GAM"].DatastoreJDBCCustomUrl = "jdbc:jtds:sqlserver://uycls266.mdp.local:1433/CanastasGAM_Produccion"        

        config.Environment.GAMRepositoryId  = "ca9bc12d-ec23-496a-bf95-f919629189ef"

        config.Git.GitEnvironment = "prod" //GitHub MdP

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
        config.Git.GitEnvFolder = "${path}${config.General.WebAppName}_${config.Git.GitEnvironment}"
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
    config.Environment.GAMConnectionCredentialKey   = "credentials-canastas-gamconnection"
    // config.Environment.GAMConnectionUsername        = ""
    // config.Environment.GAMConnectionPassword        = ""

    // Use parameter encryption in URLs
    config.Environment.UseEncryption = "NO"

    // Use HTTP or HTTPS protocol
    config.Environment.HttpProtocol = "Unsecure"

    // Set Environment Path in disk
    config.Environment.TargetPath = "${config.General.EnvironmentRelativePath}"  

    // Reorganize GAM Tables        
    config.Environment.ReorganizeGamDatabase    = "True"    
    config.Environment.DoNotExecuteReorg        = "True"       

    return config
}

return this