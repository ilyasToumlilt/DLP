package fr.upmc.ilp.ilp2tme4.test;

import java.io.IOException;

import org.junit.Before;
import org.junit.runner.Result;

import com.beust.jcommander.JCommander;

import fr.upmc.ilp.ilp2tme4.Process;
import fr.upmc.ilp.tool.File;

public class ProcessTest extends fr.upmc.ilp.ilp1.test.ProcessTest {

    public ProcessTest(File file) {
        super(file);
    }

    @Override
    @Before
    public void setUp() throws IOException {
        this.setProcess(new Process(finder));
        getProcess().setVerbose(options.verbose);
    }

    /** Un point d'entree pour des tests depuis sh. Les arguments sont
     *    -verbose -path . -path Java/src/fr/upmc/ilp/ilp2tme4
     *    -grammar grammar2-tme4.rng 
     *    -cfile /tmp/theProgram.c
     */
    
    @SuppressWarnings("unused")
    public static void main (final String[] arguments) throws Exception {
        new JCommander(options, arguments); // Unused value!
        initializeFromOptions();
        //System.out.println(options); //DEBUG

        // Affronter les tests:
        Class<?>[] classes = new Class[]{
             ProcessTest.class
        };
        Result r = org.junit.runner.JUnitCore.runClasses(classes);
        String msg = "[[[" + r.getRunCount() + " Tests, " 
                + r.getFailureCount() + " Failures]]]\n";
        System.err.println(msg);
    }
}
