package aru.scripts.version

def getVersions(String input) {
    // Regular expression to match version numbers
    def versionPattern = /[\^~>=<]*([\d\.]+)/

    // Find all matches in the input string
    def matcher = (input =~ versionPattern)

    // Initialize a list to store extracted versions
    def versions = []

    // Iterate through matches and extract version numbers
    while (matcher.find()) {
        versions.add(matcher.group(1))
    }

    // If we have at least two versions, return them sorted in descending order
    if (versions.size() >= 2) {
        versions.sort { a, b -> b <=> a }
        return versions
    }

    // If only one version is found, return it twice
    if (versions.size() == 1) {
        return [versions[0]]
    }

    // Return null if no matches are found
    return null
}

def getMajorMinorVersions(String input) {
    // Regular expression to match version numbers
    def versionPattern = /[\^~>=<]*([\d]+\.[\d]+)/

    // Find all matches in the input string
    def matcher = (input =~ versionPattern)

    // Initialize a list to store extracted major.minor versions
    def majorMinorVersions = []

    // Iterate through matches and extract major.minor version numbers
    while (matcher.find()) {
        majorMinorVersions.add(matcher.group(1))
    }

    // If we have at least two major.minor versions, return them sorted in descending order
    if (majorMinorVersions.size() >= 2) {
        majorMinorVersions.sort { a, b -> b <=> a }
        return majorMinorVersions
    }

    // If only one major.minor version is found, return it twice
    if (majorMinorVersions.size() == 1) {
        return [majorMinorVersions[0]]
    }

    // Return null if no matches are found
    return null
}

// Example usage
def version1 = getVersions("~3.10")
def version2 = getVersions(">=3.8.1,<3.10.15")
def version3 = getVersions("^3.10")
def lastVersion = version2.last()

binding.variables['version1'] = version1
binding.variables['version2'] = version2
binding.variables['version3'] = version3
binding.variables['lastVersion'] = lastVersion

// Example usage
def majorMinorVersion1 = getMajorMinorVersions("~3.10")
def majorMinorVersion2 = getMajorMinorVersions(">=3.8.1,<3.10.15")
def majorMinorVersion3 = getMajorMinorVersions("^3.10.1")
def lastMajorMinorVersion = majorMinorVersion2.last()

binding.variables['majorMinorVersion1'] = majorMinorVersion1
binding.variables['majorMinorVersion2'] = majorMinorVersion2
binding.variables['majorMinorVersion3'] = majorMinorVersion3
binding.variables['lastMajorMinorVersion'] = lastMajorMinorVersion



