package aru.scripts

import static org.junit.Assert.*

import org.junit.Before
import org.junit.Test

class ScriptTests {
    GroovyShell shell
    Binding binding
    StringWriter content

    @Before
    void setUp() {
        content = new StringWriter()
        binding = new Binding()
        binding.out = new PrintWriter(content)
        shell = new GroovyShell(binding)
    }

    @Test
    void testScriptWithAssert() {
        shell.evaluate(new File("src/aru/scripts/helloWorld/script_with_assert.groovy"))
    }

    @Test
    void testScriptWithTrueVariable() {
        binding.ok = true
        shell.evaluate(new File("src/aru/scripts/helloWorld/script_with_variable.groovy"))
        assertTrue shell.ok
    }

    @Test
    void testScriptWithFalseVariable() {
        binding.ok = false
        shell.evaluate(new File("src/aru/scripts/helloWorld/script_with_variable.groovy"))
        assertFalse shell.ok
    }

    @Test
    void testHelloWorld() {
        shell.evaluate(new File("src/aru/scripts/helloWorld/hello_world.groovy"))
        assertEquals "Hello, World!", content.toString().trim()
    }

    @Test
    void testGetVersions() {
        // Execute the script containing the getVersions method
        shell.evaluate(new File("src/aru/scripts/version/get_version.groovy"))

        // Check the output or assert on the expected values
        def version1 = binding.variables['version1']
        def version2 = binding.variables['version2']
        def version3 = binding.variables['version3']
        def lastVersion = binding.variables['lastVersion']

        def majorMinorVersion1 = binding.variables['majorMinorVersion1']
        def majorMinorVersion2 = binding.variables['majorMinorVersion2']
        def majorMinorVersion3 = binding.variables['majorMinorVersion3']
        def lastMajorMinorVersion = binding.variables['lastMajorMinorVersion']

        assertNotNull version1
        assertNotNull version2
        assertNotNull version3
        assertNotNull lastVersion

        assertNotNull majorMinorVersion1
        assertNotNull majorMinorVersion2
        assertNotNull majorMinorVersion3
        assertNotNull lastMajorMinorVersion

        assertEquals (["3.10"], version1)
        assertEquals (["3.8.1", "3.10.15"], version2)
        assertEquals (["3.10"], version3)
        assertEquals ("3.10.15", lastVersion)

        assertEquals (["3.10"], majorMinorVersion1)
        assertEquals (["3.8", "3.10"], majorMinorVersion2)
        assertEquals (["3.10"], majorMinorVersion3)
        assertEquals ("3.10", lastMajorMinorVersion)
    }

}