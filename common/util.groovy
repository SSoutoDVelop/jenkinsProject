def getMSBuildParams(dictionary) {
    def params = ""
    for (p in dictionary) {
        params += "/p:${p.key}=\"${p.value}\" "
    }
    return params
}

def copyFile(source, target) {
    return "copy \"${source}\" \"${target}\""
}

def cloneRepo(env, gitEnv) {

    switch (env) {

        case "EnvLocal":

            "git clone -b ${gitEnv} https://github.com/SSoutoDVelop/GitTest.git"

            break

        case "EnvStable" || "EnvRelease":

            "git clone -b ${gitEnv} https://soporte.dvelop:MDPOctubre.2021@gitlab.montesdelplata.com.uy/OKD/canastas.git"

            break

    }

}

def gitWebApp(env, webapp, comment) {

    "git checkout ${env}"
    "git add ${webapp}"
    "git commit -am '${comment}'"    

    return "git push"
}

return this
