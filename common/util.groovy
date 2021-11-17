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

def gitWebApp(env, webapp, comment) {

    bat "git checkout ${env}"
    bat "git add ${webapp}"
    bat "git commit -am '${comment}'"    

    return "git push"
}

// def pushGitWebApp (folder) {

// }

return this
