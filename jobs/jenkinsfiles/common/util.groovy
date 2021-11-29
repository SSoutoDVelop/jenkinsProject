def getMSBuildParams(dictionary) {
    def params = ""
    for (p in dictionary) {
        params += "/p:${p.key}=\"${p.value}\" "
    }
    return params
}

def makeParam(key, value) {
    return "/p:${key}=\"${value}\" "
}

def copyFile(source, target) {
    return "xcopy /Y \"${source}\" \"${target}\""
}

def cloneRepo(env, gitEnv, name) {

    switch (env) {

        case "EnvLocal":

            return "git clone -b ${gitEnv} https://github.com/SSoutoDVelop/canastas.git ${name}_${gitEnv}"

            break

        case "EnvStable" || "EnvRelease":

            return "git clone -b ${gitEnv} https://soporte.dvelop:MDPOctubre.2021@gitlab.montesdelplata.com.uy/OKD/canastas.git ${name}_${gitEnv}"

            break

    }

}

def gitCheckout(env) {

    return "git checkout ${env}"
}

def gitAdd(webapp) {

    return "git add ${webapp}"

}

def gitCommit(comment) {

    return "git commit -am '${comment}'"

}

def gitPush() {

    return "git push"

}

def addPropertyGxProj(gxdproj, parentElement, element, innerText) {

    "powershell"
    "powershell $xmlfile = [XML](Get-Content ${gxdproj})"
    "powershell $newelement = $xmlfile.CreateElement(${parentElement}, '')"
    "powershell $xmlfile.project.AppendChild($newelement)"
    "powershell $newelement = $xmlfile.CreateElement(${element})"
    "powershell $element = $xmlfile.SelectSingleNode(${element})"
    "powershell $element.InnerText = '${innerText}'"
    "powershell $xmlfile.save('${gxdproj})"  


    // powershell '$xmlfile = [XML](Get-Content "${config.General.EnvironmentWebFolder}\\${config.DeploymentUnits[du.key].DeployProjectName}.gxdproj)\\"'
    // powershell "$newelement = $xmlfile.CreateElement('PropertyGroup', '')"
    // powershell "$xmlfile.project.AppendChild($newelement)"
    // powershell "$newelement = $xmlfile.CreateElement('AdditionalDirectory')"
    // powershell "$element = $xmlfile.SelectSingleNode('AdditionalDirectory')"
    // powershell "$element.InnerText = 'C:\\DVelop\\${config.General.Name}\\${config.General.WorkingEnvironment}\\**\\*.*'"
    // powershell "$xmlfile.save('${config.General.EnvironmentWebFolder}\\${config.DeploymentUnits[du.key].DeployProjectName}.gxdproj')"    




}

return this
