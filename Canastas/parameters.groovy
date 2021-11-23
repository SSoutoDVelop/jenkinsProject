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
    config.General.VersionSuffix            = "_v${env.BUILD_NUMBER}_${envName}_${env.BUILD_TIMESTAMP}"
    config.General.WorkingEnvironment       = envName    
    config.General.GXProgramDirKey          = "gx-programdir"
    config.General.GXWorkingDirectoryKey    = "gx-workingdir"

    withCredentials([
        string(
            credentialsId: 'gx-programdir', 
            variable: 'programdir'),
        string(
            credentialsId: 'gx-workingdir', 
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

    config.Datastores.Key = "jdbc-sqlserver"
  
    if(envName.equals("EnvLocal")) {

        //=======================//
        // GENERAL CONFIGURATION
        //=======================//        

        // config.General.GX_PROGRAM_DIR       = "C:\Program Files (x86)\GeneXus\GeneXus 17U5HF"
        // config.General.WorkingDirectory     = "C:\Models\TuRegaloMdP" 

        withCredentials([
            usernamePassword(
                credentialsId: config.Datastores.Key, 
                usernameVariable: 'user', 
                passwordVariable: 'password')
        ])  {

            config.Datastores["Default"] = [:]
            config.Datastores["Default"].DatastoreUseJDBCCustomUrl = true
            config.Datastores["Default"].DatastoreJDBCCustomUrl = "jdbc:jtds:sqlserver://localhost:1433/Canastas_Local;instance=SQLEXPRESS;user=${user};password=${password}"
            config.Datastores["Default"].DatastoreDatabase = ""

            // GAM datastore connection information
            config.Datastores["GAM"] = [:]
            config.Datastores["GAM"].DatastoreUseJDBCCustomUrl = true
            config.Datastores["GAM"].DatastoreJDBCCustomUrl = "jdbc:jtds:sqlserver://localhost:1433/CanastasGAM_Local;instance=SQLEXPRESS;user=${user};password=${password}"
            config.Datastores["GAM"].DatastoreDatabase = ""            

        }        

        config.Environment.GAMRepositoryId  = "fb79fce5-453d-43ac-9457-f93fd88a2810"  

        //================================//
        // GIT CONFIGURATION
        //================================//    

        config.Git.GitEnvironment = "qa" //GitHub Testing           

    }

    if(envName.equals("EnvStable")) {

        //=======================//
        // GENERAL CONFIGURATION
        //=======================//        

        // config.General.GX_PROGRAM_DIR       = "C:\\Program Files (x86)\\GeneXus\\Genexus17u5"
        // config.General.WorkingDirectory     = "C:\\Models\\Canastas_Release" 
        // config.General.ForceRebuild         = "False"  

        // Default datastore connection information
        withCredentials([
            usernamePassword(
                credentialsId: config.Datastores.Key, 
                usernameVariable: 'user', 
                passwordVariable: 'password')
            )
        ])  {

            config.Datastores["Default"] = [:]
            config.Datastores["Default"].DatastoreUseJDBCCustomUrl = true
            config.Datastores["Default"].DatastoreJDBCCustomUrl = "jdbc:jtds:sqlserver://uycls267.mdp.local:1433/Canastas_Desarrollo;instance=SQLEXPRESS;user=${user};password=${password}"
            config.Datastores["Default"].DatastoreDatabase = ""

            // GAM datastore connection information
            config.Datastores["GAM"] = [:]
            config.Datastores["GAM"].DatastoreUseJDBCCustomUrl = true
            config.Datastores["GAM"].DatastoreJDBCCustomUrl = "jdbc:jtds:sqlserver://uycls267.mdp.local:1433/CanastasGAM_Desarrollo;instance=SQLEXPRESS;user=${user};password=${password}"
            config.Datastores["GAM"].DatastoreDatabase = ""            

        }      

        config.Environment.GAMRepositoryId  = "ca9bc12d-ec23-496a-bf95-f919629189ef"

        config.Git.GitEnvironment = "qa" //GitHub MdP    

    }

    if(envName.equals("EnvRelease")) {

        //=======================//
        // GENERAL CONFIGURATION
        //=======================//        

        // config.General.GX_PROGRAM_DIR       = "C:\\Program Files (x86)\\GeneXus\\Genexus17u5"
        // config.General.WorkingDirectory     = "C:\\Models\\Canastas_Release" 
        // config.General.ForceRebuild         = "False"  

        // Default datastore connection information
        withCredentials([
            usernamePassword(
                credentialsId: config.Datastores.Key, 
                usernameVariable: 'user', 
                passwordVariable: 'password')
            )
        ])  {

            config.Datastores["Default"] = [:]
            config.Datastores["Default"].DatastoreUseJDBCCustomUrl = true
            config.Datastores["Default"].DatastoreJDBCCustomUrl = "jdbc:jtds:sqlserver://uycls266.mdp.local:1433/Canastas_Produccion;instance=SQLEXPRESS;user=${user};password=${password}"
            config.Datastores["Default"].DatastoreDatabase = ""

            // GAM datastore connection information
            config.Datastores["GAM"] = [:]
            config.Datastores["GAM"].DatastoreUseJDBCCustomUrl = true
            config.Datastores["GAM"].DatastoreJDBCCustomUrl = "jdbc:jtds:sqlserver://uycls266.mdp.local:1433/CanastasGAM_Produccion;instance=SQLEXPRESS;user=${user};password=${password}"
            config.Datastores["GAM"].DatastoreDatabase = ""            

        }

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
            variable: 'path'
        )]) {    
        config.Git.GitFolder    = "${path}"
        config.Git.GitEnvFolder = "${path}${config.General.WebAppName}_${config.Git.GitEnvironment}"
    }        

    //=========================================//
    // GENEXUS SERVER CONNECTION CONFIGURATION
    //=========================================//

    config.GXServer.GXServerUrl                 = "http://gxserver.montesdelplata.com.uy/genexusserver17"
    config.GXServer.GXServerCredentialsKey      = "credentials-genexusserver17"
    config.GXServer.GXServerUsername            = ""
    config.GXServer.GXServerPassword            = ""
    config.GXServer.GXServerKB                  = config.General.WorkingVersion
    config.GXServer.GXServerVersion             = config.General.WorkingVersion 
    config.GXServer.GXServerChangelog           = "${env.WORKSPACE}\\Changelog${config.General.VersionSuffix}.xml" 
    config.GXServer.GXServerFreezeAfterDeploy   = "False"     

    // GAM connection information
    config.Environment.GAMAdminCredentialKey        = "credentials-gamadmin"
    config.Environment.GAMAdminUsername             = ""
    config.Environment.GAMAdminPassword             = ""
    config.Environment.GAMConnectionCredentialKey   = "credentials-canastas-gamconnection"
    config.Environment.GAMConnectionUsername        = ""
    config.Environment.GAMConnectionPassword        = ""

    // Use parameter encryption in URLs
    config.Environment.UseEncryption = "NO"

    // Use HTTP or HTTPS protocol
    config.Environment.HttpProtocol = "Unsecure"

    // Set Environment Path in disk
    config.Environment.TargetPath = "${config.General.EnvironmentRelativePath}"  

    // Reorganize GAM Tables        
    config.Environment.ReorganizeGamDatabase = "False"         

    return config
}

return this