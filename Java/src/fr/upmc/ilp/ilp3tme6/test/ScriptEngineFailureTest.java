package fr.upmc.ilp.ilp3tme6.test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.math.BigInteger;

import javax.script.ScriptContext;
import javax.script.ScriptException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.Result;

import fr.upmc.ilp.ilp3tme6.ScriptEngine;

public class ScriptEngineFailureTest {
    
    @Before
    public void instantiateScriptEngine () throws Exception {
        this.se = new ScriptEngine();
        this.sc = this.se.getContext();
    }
    ScriptEngine se;
    ScriptContext sc;

    @Test
    public void notWellFormedXMLprogram () throws Exception {
        try {
            se.eval("<var", sc);
            fail();
        } catch (ScriptException e) {
            assertTrue(true);
        }
    }
    
    @Test(expected = ScriptException.class)
    public void invalidXMLprogram () throws Exception {
        // Les anomalies signalees par XML sont imprimees:
        se.eval("<variable name='a'/>", sc);
        fail();
    }

    @Test
    public void throwValue () throws Exception {
        final String program = "<programme3>"
            + " <invocationPrimitive fonction='throw'>"
            + "   <entier valeur='345'/>"
            + " </invocationPrimitive>"
            + "</programme3>";
        Object result = se.eval(program, sc);
        assertEquals(345, ((BigInteger)result).intValue());
    }

    @Test(expected = ScriptException.class)
    public void throwException () throws Exception {
        final String program = "<programme3>"
            + " <operationBinaire operateur='/'>"
            + "    <operandeGauche>"
            + "       <entier valeur='345'/>"
            + "    </operandeGauche>"
            + "    <operandeDroit>"
            + "       <entier valeur='0'/>"
            + "    </operandeDroit>"
            + " </operationBinaire>"
            + "</programme3>";
        se.eval(program, sc);
        fail();
    }

	public static void main (final String[] arguments) throws Exception {
        Class<?>[] classes = new Class[]{
        		ScriptEngineFailureTest.class
        };
        Result r = org.junit.runner.JUnitCore.runClasses(classes);
        String msg = "[[[" + r.getRunCount() + " Tests, " 
                + r.getFailureCount() + " Failures]]]\n";
        System.err.println(msg);
    }
}
