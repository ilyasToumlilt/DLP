/* ******************************************************************
 * ILP -- Implantation d'un langage de programmation.
 * Copyright (C) 2004 <Christian.Queinnec@lip6.fr>
 * $Id:ProcessTest.java 405 2006-09-13 17:21:53Z queinnec $
 * GPL version>=2
 * ******************************************************************/

package fr.upmc.ilp.ilp4tme7.test;

import java.io.IOException;
import java.util.Collection;

import org.junit.Before;
import org.junit.runner.Result;
import org.junit.runner.RunWith;

import fr.upmc.ilp.ilp1.test.AbstractProcessTest;
import fr.upmc.ilp.ilp4tme7.ProcessInlining;
import fr.upmc.ilp.tool.File;
import fr.upmc.ilp.tool.Parameterized;
import fr.upmc.ilp.tool.Parameterized.Parameters;

@RunWith(value=Parameterized.class)
public class ProcessInliningTest
extends fr.upmc.ilp.ilp4.test.ProcessTest {

    /** Le constructeur du test sur un fichier. */

    public ProcessInliningTest (final File file) {
        super(file);
    }

    @Before
    @Override
    public void setUp () throws IOException {
        this.setProcess(new ProcessInlining(finder));
    }

    @Parameters
    public static Collection<File[]> data() throws Exception {
        initializeFromOptions();
        AbstractProcessTest.staticSetUp(samplesDir, "u\\d+-[1-4]");
        // Pour un (ou plusieurs) test(s) en particulier:
        //AbstractProcessTest.staticSetUp("u");
        return AbstractProcessTest.collectData();
    }
    
	public static void main (String [] args) {	
		Class<?>[] classes = new Class[]{
				ProcessInliningTest.class
		};
		Result r = org.junit.runner.JUnitCore.runClasses(classes);
		String msg = "[[[" + r.getRunCount() + " Tests, " 
				+ r.getFailureCount() + " Failures]]]\n";
		System.err.println(msg);
	}    
}
