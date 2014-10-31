package fr.upmc.ilp.ilp3esc.test;

import java.io.IOException;
import java.util.Collection;

import org.junit.Before;
import org.junit.runner.RunWith;

import fr.upmc.ilp.ilp1.test.AbstractProcessTest;
import fr.upmc.ilp.ilp3esc.Process;
import fr.upmc.ilp.tool.File;
import fr.upmc.ilp.tool.Parameterized;
import fr.upmc.ilp.tool.Parameterized.Parameters;

@RunWith(value=Parameterized.class)
public class ProcessTest
extends fr.upmc.ilp.ilp3.test.ProcessTest {

    /** Le constructeur du test sur un fichier. */

    public ProcessTest (final File file) {
        super(file);
    }

    @Parameters
    public static Collection<File[]> data() throws Exception {
        initializeFromOptions();
        AbstractProcessTest.staticSetUp(samplesDir, "(u\\d+-[123]|e\\d+-2esc)");
        // Pour un (ou plusieurs) test(s) en particulier:
        //AbstractProcessTest.staticSetUp("u26-1");
        return AbstractProcessTest.collectData();
    }

    @Before
    @Override
    public void setUp () throws IOException {
        this.setProcess(new Process(finder));
        getProcess().setVerbose(options.verbose);
        //getProcess().setGrammar(grammarFile);
        getProcess().setCFile(cFile);
        getProcess().setCompileThenRunScript(scriptFile);
    }
}
