package fr.upmc.ilp.ilp3tme6.test;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.math.BigInteger;

import javax.script.Bindings;
import javax.script.ScriptContext;
import javax.script.ScriptException;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.Result;

import fr.upmc.ilp.ilp3tme6.ScriptEngine;

public class ScriptEngineEnvironmentTest {
    
    @Before
    public void instantiateScriptEngine () throws Exception {
        this.se = new ScriptEngine();
        this.sc = this.se.getContext();
    }
    ScriptEngine se;
    ScriptContext sc;
    
    @Test
    public void checkIfContextPresent () throws Exception {
        javax.script.ScriptContext sc = this.se.getContext();
        assertNotNull(sc);
    }
    
    @Test
    public void checkNewContexts () throws Exception {
        ScriptEngine se1 = new ScriptEngine();
        ScriptEngine se2 = new ScriptEngine();
        Assert.assertNotSame(se1.getContext(), se2.getContext());
    }

    @Test
    public void checkNonPersistentContexts () throws Exception {
        ScriptEngine se1 = new ScriptEngine();
        // NOTA se.put() existe aussi mais emplit l'ENGINE_SCOPE!
        se1.getBindings(ScriptContext.GLOBAL_SCOPE).put("a", 1);
        ScriptEngine se2 = new ScriptEngine();
        assertNull(se2.getBindings(ScriptContext.GLOBAL_SCOPE).get("a"));
    }
    
    @Test
    public void importGlobalVariable () throws Exception {
        Bindings bm = sc.getBindings(ScriptContext.GLOBAL_SCOPE);
        bm.put("e", new Double(2.78));
        sc.setBindings(bm, ScriptContext.GLOBAL_SCOPE);
        final String program = "<programme1>"
            + "<variable nom='e'/>"
            + "</programme1>";
        Object result = se.eval(program, sc);
        assertEquals(true, result instanceof Double);
        assertEquals(2.78, (Double)result, 0.1);
    }

    public void checkPersistentContext () throws Exception {
        Bindings bm = sc.getBindings(ScriptContext.GLOBAL_SCOPE);
        bm.put("e", new Double(2.78));
        sc.setBindings(bm, ScriptContext.GLOBAL_SCOPE);
        final String program = "<programme1>"
            + "<variable nom='e'/>"
            + "</programme1>";
        Object result = se.eval(program, sc);
        assertEquals(true, result instanceof Double);
        assertEquals(2.78, (Double)result, 0.1);
        // encore une fois et 'e' est encore la:
        result = se.eval(program, sc);
        assertEquals(true, result instanceof Double);
        assertEquals(2.78, (Double)result, 0.1);
    }
    
    @Test(expected = ScriptException.class)
    public void checkEAbsent () throws Exception {
        final String program = "<programme1>"
            + "<variable nom='e'/>"
            + "</programme1>";
        se.eval(program, sc);
        fail();
    }
    
    @Test
    public void import2GlobalVariables () throws Exception {
        Bindings bm = sc.getBindings(ScriptContext.GLOBAL_SCOPE);
        bm.put("e", new Double(2.78));
        bm.put("c", new Double(4.3));
        sc.setBindings(bm, ScriptContext.GLOBAL_SCOPE);
        final String program = "<programme2>"
            + " <operationBinaire operateur='+'>"
            + "   <operandeGauche>"
            + "     <variable nom='e'/>"
            + "   </operandeGauche>"
            + "   <operandeDroit>"
            + "     <variable nom='c'/>"
            + "   </operandeDroit>"
            + " </operationBinaire>"
            + "</programme2>";
        Object result = se.eval(program, sc);
        assertEquals(true, result instanceof Double);
        assertEquals(7.08, (Double)result, 0.1);
    }
    
    // Une affectation sur une variable globale en ILP est bien
    // dans l'environnement global.
    @Test
    public void export2GlobalVariable () throws Exception {
        Bindings bm = sc.getBindings(ScriptContext.GLOBAL_SCOPE);
        final String program = "<programme2>"
            + "<affectation nom='N'>"
            + "  <valeur><entier valeur='123'/></valeur>"
            + "</affectation>"
            + "</programme2>";
        Object result = se.eval(program, sc);
        assertEquals(123, ((BigInteger)result).intValue());
        assertEquals(123, ((BigInteger)bm.get("N")).intValue());
    }
    
	public static void main (final String[] arguments) throws Exception {
        Class<?>[] classes = new Class[]{
        		ScriptEngineEnvironmentTest.class
        };
        Result r = org.junit.runner.JUnitCore.runClasses(classes);
        String msg = "[[[" + r.getRunCount() + " Tests, " 
                + r.getFailureCount() + " Failures]]]\n";
        System.err.println(msg);
    }
}
