def getMSBuildParams(dictionary) {
    def params = ""
    for (p in dictionary) {
        if (p.key != "CredentialId") {
            params += "/p:${p.key}=\"${p.value}\" "
        }
    }
    return params
}

def makeParam(key, value) {
    return "/p:${key}=\"${value}\" "
}

def copyFile(source, target) {
    return "xcopy /Y \"${source}\" \"${target}\"*"
}

def cloneRepo(env, gitEnv, name) {

    switch (env) {

        case "EnvLocal":

            return "git clone -b ${gitEnv} https://github.com/SSoutoDVelop/${name}.git ${name}_${gitEnv}"

            break

        case "EnvStable" || "EnvRelease":

            return "git clone -b ${gitEnv} https://soporte.dvelop:MDPOctubre.2021@gitlab.montesdelplata.com.uy/OKD/${name}.git ${name}_${gitEnv}"

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

def gitPull() {

    return "git pull"

}

def gitPush() {

    return "git push"

}

def gitLFSTrack() {

    return 'git lfs track "*.war"'
}

def gitAttributes() {
    
    return "git add .gitattributes"
}

def deployAzure(user, pass, zipfile, apiurl) {

    return "curl -X PUT -u ${user}:${pass} --data-binary @${zipfile} ${apiurl}"

}

def deployTareasProgramadasAzure(zipfile, taskDir) {

    return '"C:\\Program Files (x86)\\IIS\\Microsoft Web Deploy V3\\msdeploy"' + " -verb:sync -skip:Directory=Logs -source:package=${zipfile} -dest:contentPath=C:\\DVelop\\TareasProgramadas\\${taskDir},computerName=clazs003"

}

return this
