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
    
    //=========================================//
    // GENEXUS SERVER CONNECTION CONFIGURATION
    //=========================================//

    config.GXServer.GXServerUrl = "http://gxserver.montesdelplata.com.uy/genexusserver17"
    config.GXServer.GXServerUsername = ""
    config.GXServer.GXServerPassword = ""
    config.GXServer.GXServerKB = "Canastas"
    config.GXServer.GXServerVersion = "Canastas"
    config.GXServer.GXServerChangelog = "${env.WORKSPACE}\\Changelog${config.General.VersionSuffix}.xml" 
    config.GXServer.GXServerFreezeAfterDeploy = "False"    

    if(envName.equals("EnvLocal")) {

        //=======================//
        // GENERAL CONFIGURATION
        //=======================//        

        config.General.GX_PROGRAM_DIR = "C:\\Program Files (x86)\\GeneXus\\GeneXus 17U5HF"
        config.General.WorkingDirectory = "C:\\Models\\TuRegaloMdP" 
        config.General.WorkingVersion = "Canastas" 
        config.General.WorkingEnvironment = envName
        config.General.VersionSuffix = "_v${env.BUILD_NUMBER}_${envName}_${env.BUILD_TIMESTAMP}"
        config.General.ForceRebuild = "True"  
        config.General.RunDataLoad = "True" 
        config.General.WebAppName = "canastas"

        //================================//
        // DEPLOYMENT UNITS CONFIGURATION
        //================================//

        config.DeploymentUnits["WebAppDeploy"] = [:]
        config.DeploymentUnits["WebAppDeploy"].DeployTargetId = "LOCAL" 
        config.DeploymentUnits["WebAppDeploy"].DeployApplicationCompiler = "JAVA" 
        config.DeploymentUnits["WebAppDeploy"].DeploySelectedObjectsOnly = "False"
        config.DeploymentUnits["WebAppDeploy"].DeployApplicationServer = "Tomcat 8.x"
        config.DeploymentUnits["WebAppDeploy"].DeployUseAppServerDatasource = "False" 
        config.DeploymentUnits["WebAppDeploy"].DeployIncludeGAM = "False" 
        config.DeploymentUnits["WebAppDeploy"].DeployTargetJRE = "9" 
        config.DeploymentUnits["WebAppDeploy"].DeployPackageFormat = "Automatic" 
        config.DeploymentUnits["WebAppDeploy"].DeployTimeStamp = "${env.BUILD_TIMESTAMP}"
        config.DeploymentUnits["WebAppDeploy"].DeployFileFullPath = "${env.WORKSPACE}" 
        config.DeploymentUnits["WebAppDeploy"].DeployWebappName = config.General.WebAppName
        config.DeploymentUnits["WebAppDeploy"].DeployProjectName = "${config.General.WebAppName}${config.General.VersionSuffix}" 
        config.DeploymentUnits["WebAppDeploy"].DeployFullPath = "${env.WORKSPACE}\\${config.General.WebAppName}${config.General.VersionSuffix}" 
        config.DeploymentUnits["WebAppDeploy"].DeployObjectNames = "DeploymentUnit:DeploymentUnit"  

        //================================//
        // GIT CONFIGURATION
        //================================//    

        config.General.GitEnvironment = "qa"            

        withCredentials([string(credentialsId: 'git-path', variable: 'path')]) {    
            config.General.GitFolder = "${path}${config.General.WebAppName}_${config.General.GitEnvironment}"
        }

    }

    config.General.EnvironmentRelativePath = "${config.General.WorkingEnvironment}"
    config.General.EnvironmentRootFolder = "${config.General.WorkingDirectory}\\${config.General.EnvironmentRelativePath}"
    config.General.EnvironmentWebFolder = "${config.General.EnvironmentRootFolder}\\web"
    config.General.EnvironmentMobileFolder = "${config.General.EnvironmentRootFolder}\\mobile"

    return config
}

return this