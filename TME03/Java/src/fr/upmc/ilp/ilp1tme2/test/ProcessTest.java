/* ******************************************************************
 * ILP -- Implantation d'un langage de programmation.
 * Copyright (C) 2011 <Christian.Queinnec@lip6.fr>
 * $Id$
 * GPL version>=2
 * ******************************************************************/

package fr.upmc.ilp.ilp1tme2.test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

import java.io.IOException;
import java.util.Collection;

import org.junit.runner.Result;
import org.w3c.dom.Document;

import com.beust.jcommander.JCommander;

import fr.upmc.ilp.ilp1.test.AbstractProcessTest;
import fr.upmc.ilp.tool.File;
import fr.upmc.ilp.tool.Parameterized.Parameters;

/** Cette classe peut etre lancee depuis Eclipse comme un test JUnit ou
 * comme une application autonome. Dans ce dernier cas, si on lance 
 * l'application depuis Eclipse, il faut donner les arguments suivants:
 *     -path . -grammar Grammars/grammar1.rng -cfile /tmp/theProgram.c
 * */

public class ProcessTest extends fr.upmc.ilp.ilp1.test.ProcessTest {

    public ProcessTest(final File file) {
        super(file);
    }

    @Override
    public void setUp() throws IOException {
        this.setProcess(new fr.upmc.ilp.ilp1tme2.Process(finder));
        getProcess().setVerbose(options.verbose);
    }

    @Parameters
    public static Collection<File[]> data() throws Exception {
        initializeFromOptions();
        AbstractProcessTest.staticSetUp("u\\d+-1");
        // Pour un (ou plusieurs) test(s) en particulier:
        // AbstractProcessTest.staticSetUp(samplesDir, "u0[13]*-1");
        return AbstractProcessTest.collectData();
    }

    @Override
    public void handleFile() throws Exception {
        //NON: super.handleFile(); 
        // car on ne veut ni interpretation, ni compilation.
        // {{{ On ne reprend que les lignes utiles:
        
        System.err.println("Testing " + this.file.getAbsolutePath() + " ...");

        final fr.upmc.ilp.tool.File xmlFile =
            new fr.upmc.ilp.tool.File(this.file);
        assertTrue(xmlFile.exists());

        this.getProcess().initialize(xmlFile);
        // Si erreur, voir this.process.initializationFailure
        assertTrue(this.getProcess().isInitialized());

        this.getProcess().prepare();
        // Si erreur, voir this.process.preparationFailure
        assertTrue(this.getProcess().isPrepared());
        
        // }}} fin de reprise de super.handleFile()
        
        assertTrue(this.getProcess() instanceof fr.upmc.ilp.ilp1tme2.Process);
        assertTrue(this.getProcess() 
                    instanceof fr.upmc.ilp.ilp1tme2.ICountingConstantsProcess);
        fr.upmc.ilp.ilp1tme2.ICountingConstantsProcess theProcess = 
                (fr.upmc.ilp.ilp1tme2.ICountingConstantsProcess) 
                    this.getProcess();
        final String basefilename = this.file.getBaseName();
        final int nCd = theProcess.getNbConstantesDOM();
        System.out.println("Nombre de constantes dans DOM de "
                + basefilename + " : " + nCd);
        
        final int nCa = theProcess.getNbConstantesAST();
        System.out.println("Nombre de constantes dans AST de "
                + basefilename + " : " + nCa);
        assertEquals(nCd, nCa);
        
        // Calcul du nombre de constantes directement dans le DOM:
        int nCst = 0;
        final Document dom = getProcess().getDocument();
        nCst += dom.getElementsByTagName("entier").getLength();
        nCst += dom.getElementsByTagName("flottant").getLength();
        nCst += dom.getElementsByTagName("chaine").getLength();
        nCst += dom.getElementsByTagName("booleen").getLength();
        System.out.println("Nombre réel de constantes dans  "
                + basefilename + " : " + nCst);
        assertEquals(nCa, nCst); 
        assertEquals(nCd, nCst); 
    }
    
    /** Un point d'entree pour des tests depuis sh. */
    
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
