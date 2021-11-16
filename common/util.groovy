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

def gitWebApp(path, webapp, env) {

    "cd ${path}"
    "git checkout ${env}"
    "git add ${webapp}"
    "git commit -am 'WebApp'"
    // "git push ${env}"

    return "git push ${env}"
}

// def pushGitWebApp (folder) {

// }

return this
