def getMSBuildParams(dictionary) {
    def params = ""
    for (p in dictionary) {
        params += "/p:${p.key}=\"${p.value}\" "
    }
    return params
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

def gitWebApp(env, webapp, comment) {

    gitCheckout(env)
    gitAdd(webapp)
    gitCommit(comment)
    // gitPush()

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

return this
