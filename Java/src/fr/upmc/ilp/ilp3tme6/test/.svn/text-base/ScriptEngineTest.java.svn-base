package fr.upmc.ilp.ilp3tme6.test;

import static junit.framework.Assert.assertEquals;

import java.io.FilenameFilter;
import java.io.StringWriter;
import java.util.Collection;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.script.ScriptContext;

import org.junit.Test;
import org.junit.runner.Result;
import org.junit.runner.RunWith;

import fr.upmc.ilp.ilp3tme6.ScriptEngine;
import fr.upmc.ilp.tool.File;
import fr.upmc.ilp.tool.FileTool;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class ScriptEngineTest  {
	public ScriptEngineTest(File file) {
		super();
		this.file = file;
	}

	protected File file;

	private static String dir = "Grammars/Samples/";
	private static String pattern = "u(0\\d+"
			+ "|1[^3]\\d*"
			+ "|[2-5]\\d+"
			+ "|6[^4]\\d*|64[^5]\\d*"
			+ "|7[^4]\\d*"
			+ "|[8-9]\\d+)-[1-4]";
	
	@Parameters
	public static Collection<File[]> getSamples() throws Exception {
		final Pattern p = Pattern.compile("^" + pattern + ".xml$");
		final FilenameFilter ff = new FilenameFilter() {
			public boolean accept (java.io.File dir, String name) {
				final Matcher m = p.matcher(name);
				return m.matches();
			}
		};
		final java.io.File[] testFiles = new File (dir).listFiles(ff);

		// Vérifier qu'il y a au moins un programme à tester sinon on pourrait
		// avoir l'erreur bête que tout marche puisqu'aucune erreur n'a été vue!
		if ( testFiles.length == 0 ) {
			final String msg = "ILP: Cannot find a single test like " + pattern;
			throw new RuntimeException(msg);
		}

		// Trier les noms de fichiers en place:
		java.util.Arrays.sort(testFiles,
				new java.util.Comparator<java.io.File>() {
			public int compare (java.io.File f1, java.io.File f2) {
				return f1.getName().compareTo(f2.getName());
			}
		});

		Collection<File[]> result = new Vector<>();
		for ( final java.io.File f : testFiles ) {
			result.add(new File[]{new File(f)});
		}
		return result;
	}

	@Test
	public void handleFile() throws Exception {
		try {
			final ScriptEngine se = new ScriptEngine();
		final ScriptContext sc = se.getContext();
		final StringWriter sw = new StringWriter();
		sc.setWriter(sw);
		Object result = se.eval(file.getContent(), sc);

		final String expectedResult =
				FileTool.readExpectedResult(file);
		if ( result instanceof Double ) {
			double fExpectedResult = Double.parseDouble(expectedResult);
			assertEquals(fExpectedResult, (Double)result, 0.1);
		} else {
			assertEquals(expectedResult, result.toString());
		}
		final String expectedPrinting =
				FileTool.readExpectedPrinting(file);
		assertEquals(expectedPrinting, sw.toString());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void main (String [] args) {	
		Class<?>[] classes = new Class[]{
				ScriptEngineTest.class
		};
		Result r = org.junit.runner.JUnitCore.runClasses(classes);
		String msg = "[[[" + r.getRunCount() + " Tests, " 
				+ r.getFailureCount() + " Failures]]]\n";
		System.err.println(msg);
	}
}
