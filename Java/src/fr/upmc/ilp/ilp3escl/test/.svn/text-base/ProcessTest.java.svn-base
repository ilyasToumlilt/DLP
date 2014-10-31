package fr.upmc.ilp.ilp3escl.test;

import java.io.IOException;
import java.util.Collection;

import org.junit.Before;
import org.junit.runner.RunWith;

import fr.upmc.ilp.ilp1.test.AbstractProcessTest;
import fr.upmc.ilp.ilp3escl.Process;
import fr.upmc.ilp.tool.File;
import fr.upmc.ilp.tool.Parameterized;
import fr.upmc.ilp.tool.Parameterized.Parameters;

@RunWith(value=Parameterized.class)
public class ProcessTest
extends fr.upmc.ilp.ilp3esc.test.ProcessTest {

    /** Le constructeur du test sur un fichier. */

    public ProcessTest (final File file) {
        super(file);
    }

    @Parameters
    public static Collection<File[]> data() throws Exception {
        initializeFromOptions();
        AbstractProcessTest.staticSetUp(samplesDir, "(u\\d+-[123]|e\\d+-2escl)");
        // Pour un (ou plusieurs) test(s) en particulier:
        //AbstractProcessTest.staticSetUp("d020-2exdef");
        return AbstractProcessTest.collectData();
    }

    @Before
    @Override
    public void setUp () throws IOException {
        this.setProcess(new Process(finder));
        getProcess().setVerbose(options.verbose);
        //getProcess().setGrammar(grammarFile);
        getProcess().setCFile(cFile);
        // NON car le patron est specifié par new fr.upmc.ilp.ilp1.Process():
        //getProcess().setCTemplateFile(cTemplateFile);
        getProcess().setCompileThenRunScript(scriptFile);
    }
}
