package fr.upmc.ilp.ilp3tme6.test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import javax.script.ScriptContext;
import javax.script.ScriptException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.Result;

import fr.upmc.ilp.ilp3tme6.ScriptEngine;

public class ScriptEngineGlobalPrimitiveTest {

	@Before
	public void instantiateScriptEngine () throws Exception {
		this.se = new ScriptEngine();
		this.sc = this.se.getContext();
	}
	ScriptEngine se;
	ScriptContext sc;


	/* some ad-hoc static primitive */
	public static Integer parseInt(String s) {
		return Integer.parseInt(s);
	}
	
	@Test
	public void invokeMathSin () throws ScriptException {
		se.wrapGlobalJavaPrimitive("sinus", java.lang.Math.class, "sin");
		final String program = "<programme3>"
				+ "<invocation>"
				+ "  <fonction><variable nom='sinus'/></fonction>"
				+ "  <arguments><flottant valeur='0'/></arguments>"
				+ "</invocation>"
				+ "</programme3>";
		Object result = se.eval(program, sc);
		assertEquals(0.0, (Double) result, 0.1);
	}
	
	@Test
	public void invokeParseInt () throws ScriptException {
		se.wrapGlobalJavaPrimitive("parseInt", ScriptEngineGlobalPrimitiveTest.class, "parseInt");
		final String program = "<programme3>"
				+ "<invocation>"
				+ "  <fonction><variable nom='parseInt'/></fonction>"
				+ "  <arguments><chaine>123456</chaine></arguments>"
				+ "</invocation>"
				+ "</programme3>";
		Object result = se.eval(program, sc);
		assertEquals(new Integer(123456), (Integer)result);
	}

	@Test
	public void invokeParseIntTypeEx () throws Exception{
		try {
			se.wrapGlobalJavaPrimitive("parseInt", ScriptEngineGlobalPrimitiveTest.class, "parseInt");
			final String program = "<programme3>"
					+ "<invocation>"
					+ "  <fonction><variable nom='parseInt'/></fonction>"
					+ "  <arguments><chaine>TOTO</chaine></arguments>"
					+ "</invocation>"
					+ "</programme3>";
			se.eval(program, sc);
			assertTrue(false);
		} catch (ScriptException e) {
			assertTrue(true);
		}
	}
	
	@Test
	public void invokeParseIntArityEx () throws Exception {
		try {
			se.wrapGlobalJavaPrimitive("parseInt", ScriptEngineGlobalPrimitiveTest.class, "parseInt");
			final String program = "<programme3>"
					+ "<invocation>"
					+ "  <fonction><variable nom='parseInt'/></fonction>"
					+ "  <arguments><chaine>A</chaine><chaine>B</chaine></arguments>"
					+ "</invocation>"
					+ "</programme3>";
			se.eval(program, sc);
			assertTrue(false);
		} catch (ScriptException e) {
			assertTrue(true);
		}
	}
	
	public static void main (final String[] arguments) throws Exception {
        Class<?>[] classes = new Class[]{
        		ScriptEngineGlobalPrimitiveTest.class
        };
        Result r = org.junit.runner.JUnitCore.runClasses(classes);
        String msg = "[[[" + r.getRunCount() + " Tests, " 
                + r.getFailureCount() + " Failures]]]\n";
        System.err.println(msg);
    }
}
