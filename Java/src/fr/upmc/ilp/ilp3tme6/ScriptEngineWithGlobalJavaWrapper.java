package fr.upmc.ilp.ilp3tme6;

import javax.script.ScriptException;

public interface ScriptEngineWithGlobalJavaWrapper
	extends javax.script.ScriptEngine {
	public abstract void wrapGlobalJavaPrimitive(String ilpname,
			Class<?> cname, String mname) throws ScriptException;
}