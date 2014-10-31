package fr.upmc.ilp.ilp3tme6.test;

import static junit.framework.Assert.assertEquals;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.script.CompiledScript;
import javax.script.ScriptContext;

import org.junit.runner.Result;
import org.junit.runner.RunWith;

import fr.upmc.ilp.ilp3tme6.ScriptEngine;
import fr.upmc.ilp.tool.File;
import fr.upmc.ilp.tool.FileTool;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class ScriptEngineCompileTest 
extends ScriptEngineTest {
    
    public ScriptEngineCompileTest(File file) {
        super(file);
    }
    
    @Override
    public void handleFile() throws Exception {
        final ScriptEngine se = new ScriptEngine();
        final CompiledScript cs = se.compile(file.getContent());
        final ScriptContext sc = se.getContext();
        final StringWriter sw = new StringWriter();
        sc.setWriter(new PrintWriter(sw));
        
        Object result = cs.eval(sc);
        final String expectedResult =
                FileTool.readExpectedResult(file);
        final String expectedPrinting =
                FileTool.readExpectedPrinting(file);

        if (result == null) {
        	/* pour accepter le copier coller d'ILP4 qui imprime son résultat à la fin */
        	assertEquals(expectedPrinting + expectedResult, sw.toString());        	
        } else {
            if ( result instanceof Double ) {
                double fExpectedResult = Double.parseDouble(expectedResult);
                assertEquals(fExpectedResult, (Double)result, 0.1);
            } else {
                assertEquals(expectedResult, result.toString());
            }
            assertEquals(expectedPrinting, sw.toString());
        }
    }

    public static void main (String [] args) {	
		Class<?>[] classes = new Class[]{
				ScriptEngineCompileTest.class
		};
		Result r = org.junit.runner.JUnitCore.runClasses(classes);
		String msg = "[[[" + r.getRunCount() + " Tests, " 
				+ r.getFailureCount() + " Failures]]]\n";
		System.err.println(msg);
	}
}
