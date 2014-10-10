/* ******************************************************************
 * ILP -- Implantation d'un langage de programmation.
 * Copyright (C) 2006 <Christian.Queinnec@lip6.fr>
 * $Id:ProcessTest.java 505 2006-10-11 06:58:35Z queinnec $
 * GPL version>=2
 * ******************************************************************/

package fr.upmc.ilp.ilp1tme3.test;

import java.io.IOException;
import java.util.Collection;

import org.junit.runner.Result;
import org.junit.runner.RunWith;

import com.beust.jcommander.JCommander;

import fr.upmc.ilp.ilp1.test.AbstractProcessTest;
import fr.upmc.ilp.tool.File;
import fr.upmc.ilp.tool.Parameterized;
import fr.upmc.ilp.tool.Parameterized.Parameters;

@RunWith(value=Parameterized.class)
public class ProcessTest extends fr.upmc.ilp.ilp1.test.ProcessTest {

    public ProcessTest (final File file) {
        super(file);
    }

    @Override
    public void setUp () throws IOException {
        this.setProcess(new fr.upmc.ilp.ilp1tme3.Process(finder));
        getProcess().setVerbose(options.verbose);
    }

    @Parameters
    public static Collection<File[]> data() throws Exception {
        initializeFromOptions();
        AbstractProcessTest.staticSetUp(samplesDir, "u\\d+-1(tme3)?");
        return AbstractProcessTest.collectData();
    }
    
    /** Un point d'entree pour des tests depuis sh. Les arguments sont
     *    -verbose -path . -path Java/src/fr/upmc/ilp/ilp1tme3
     *    -grammar Grammars/grammar1.rng 
     *    -cfile /tmp/theProgram.c -ctemplate templateTME3.c
     * */
    
    @SuppressWarnings("unused")
    public static void main (final String[] arguments) throws Exception {
        new JCommander(options, arguments); // Unused value!
        initializeFromOptions();

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
