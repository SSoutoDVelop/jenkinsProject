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

def gitWebApp(webapp) {
    "cd C:\\Users\\ssouto\\Desktop\\Proyectos\\Jenkins\\GitTest\\"
    
    return "git"
}

// def pushGitWebApp (folder) {

// }

return this
