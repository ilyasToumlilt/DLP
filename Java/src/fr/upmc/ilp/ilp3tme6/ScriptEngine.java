package fr.upmc.ilp.ilp3tme6;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.script.AbstractScriptEngine;
import javax.script.Bindings;
import javax.script.Compilable;
import javax.script.CompiledScript;
import javax.script.ScriptContext;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptException;
import javax.script.SimpleBindings;

import fr.upmc.ilp.ilp1.runtime.EvaluationException;
import fr.upmc.ilp.ilp2.runtime.InvokableImpl;
import fr.upmc.ilp.tool.Finder;
import fr.upmc.ilp.tool.IContent;

public class ScriptEngine extends AbstractScriptEngine
implements Compilable, ScriptEngineWithGlobalJavaWrapper {

	public ScriptEngine () {
		super();
		this.setContext(new CommonPlusScriptContextAdapter(context));
		// Chaque ScriptEngine a son propre environnement global:
		this.setBindings(createBindings(), ScriptContext.GLOBAL_SCOPE);
	}

	public Bindings createBindings() {
		return new SimpleBindings();
	}

	/* (non-Javadoc)
	 * @see fr.upmc.ilp.jsr223.ScriptEngineWithGlobalJavaWrapper#wrapGlobalJavaPrimitive(fr.upmc.ilp.jsr223.ScriptEngine.String, java.lang.Class, fr.upmc.ilp.jsr223.ScriptEngine.String)
	 */  
	@Override
	public void wrapGlobalJavaPrimitive(java.lang.String ilpname, Class<?> cname, java.lang.String mname) throws ScriptException {
		for ( final Method m : cname.getDeclaredMethods() ) {
			if ( m.getName().equals(mname) ) {
				final class JavaPrimitive extends InvokableImpl {
					@Override
					public Object invoke(Object[] arguments) 
							throws EvaluationException {
						try {
							return m.invoke(null, arguments);
						} catch (Exception exc) {
							throw new EvaluationException(exc); 
						}
					}
				}
				this.getContext().getBindings(ScriptContext.GLOBAL_SCOPE).put(ilpname, new JavaPrimitive());
				return;
			}
		}
		throw new ScriptException("Method not found!");	
	}

	// Utilitaire pour qu'un java.lang.String implante IContent:
	public static class String implements IContent {
		public String (java.lang.String s) {
			this.string = s;
		}
		java.lang.String string;
		public java.lang.String getContent () {
			return this.string;
		}
	}

	// Utilitaire pour qu'un java.io.Reader implante IContent:
	public static class Reader implements IContent {
		public Reader (java.io.Reader reader) {
			this.reader = reader;
		}
		java.io.Reader reader;
		public java.lang.String getContent () throws IOException {
			return fr.upmc.ilp.tool.FileTool.slurpFile(this.reader);
		}
	}

	// Les methodes d'evaluation:

	public Object eval(java.lang.String script, ScriptContext context)
			throws ScriptException {
		return this.eval(new String(script), context);
	}

	public Object eval(java.io.Reader reader, ScriptContext context)
			throws ScriptException {
		return this.eval(new Reader(reader), context);
	}

	private Object eval(IContent content, ScriptContext context)
			throws ScriptException {
		Process ip;
		try {
			ip = new Process(new Finder());
			ip.initialize(content);
			if (! ip.isInitialized()) {
				throw new ScriptException(ip.getInitializationFailure().getMessage());
			}
			ip.prepare();
			if (! ip.isPrepared()) {
				throw new ScriptException(ip.getPreparationFailure().getMessage());
			}

			ip.setScriptContext(context);
			ip.interpret();
			if (! ip.isInterpreted()) {
				throw new ScriptException(ip.getInterpretationFailure().getMessage());
			}
			// Pas de compilation vers C ici!
			return ip.getInterpretationValue();
		} catch (ScriptException se) {
			throw se;
		} catch (Exception exc) {
			throw new ScriptException(exc.getMessage());
		}
	}

	// Les methodes de compilation. On compile vers Javascript puis on 
	// demande a Rhino de compiler ce Javascript.

	public CompiledScript compile (java.lang.String script)
			throws ScriptException {
		return this.compile(new String(script));
	}

	public CompiledScript compile (java.io.Reader reader)
			throws ScriptException {
		return this.compile(new Reader(reader));
	}

	private CompiledScript compile(IContent content) throws ScriptException {
		Process ip;
		try {
			ip = new Process(new Finder());
		} catch (IOException e) {
			throw new ScriptException(e);
		}

		ip.initialize(content);
		if (! ip.isInitialized()) {
			throw new ScriptException(ip.getInitializationFailure().getMessage());
		}
		ip.prepare();
		if (! ip.isPrepared()) {
			throw new ScriptException(ip.getPreparationFailure().getMessage());
		}
		
		ip.compile();
		java.lang.String totalCode = "// Runtime stuff:\n"
				+ "function ILP() {};\n"
				+ "ILP.pi = 3.1416;\n"
				+ "ILP['print'] = function(o) {print(o, false);return false};\n"
				+ "ILP['throw'] = function(o) {throw o};\n"
				+ ip.getCompiledProgram() + "\n"
				+ "// Lancement programme entier:\n"
				+ "try { " + fr.upmc.ilp.ilp4.jsgen.Compiler.PROGRAM + "();\n"
				+ "} catch (e) { e };\n";

		javax.script.ScriptEngine rhino = new javax.script.ScriptEngineManager()
		.getEngineByName("JavaScript");
		if ( rhino != null && rhino instanceof Compilable) {
			return ((Compilable)rhino).compile(totalCode);
		} else {
			throw new ScriptException("Cannot compile!");
		}
	}

	@Override
	public ScriptEngineFactory getFactory() {
		return null;
	}
}
