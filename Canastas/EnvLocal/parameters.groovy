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
    // if (envName == "EnvLocal") {
    config.General.WorkingDirectory = "C:\\Models\\TuRegaloMdP" 

    // WorkingVersion is related to the name assigned to the KB
    config.General.WorkingVersion = "Canastas" 

    // }
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
    config.General.EnvironmentRelativePath = "${config.General.WorkingEnvironment}"
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
    config.GXServer.GXServerUrl = "http://gxserver.montesdelplata.com.uy/genexusserver17"
    config.GXServer.GXServerUsername = "local\\dvelop"
    config.GXServer.GXServerPassword = credentials("password-genexusserver17")
    config.GXServer.GXServerKB = "Canastas"
    config.GXServer.GXServerVersion = "Canastas"
    config.GXServer.GXServerChangelog = "${env.WORKSPACE}\\Changelog${config.General.VersionSuffix}.xml" 
    config.GXServer.GXServerFreezeAfterDeploy = "False"    

    return config
}

return this