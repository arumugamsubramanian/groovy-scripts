package aru.scripts.strings

import static org.junit.Assert.*

def image = 'org/parent/java-openjdk-11-jre:2.5.3-beta.20230321203608-amd64'

def result = image.substring(image.lastIndexOf("/") + 1).split(':')[1]

println(result)

binding.variables['result'] = result

assertEquals ('2.5.3-beta.20230321203608-amd64', result)