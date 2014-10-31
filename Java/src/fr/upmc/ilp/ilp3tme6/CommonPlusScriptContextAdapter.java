package fr.upmc.ilp.ilp3tme6;

import java.io.Reader;
import java.io.Writer;
import java.util.List;

import javax.script.Bindings;
import javax.script.ScriptContext;
import fr.upmc.ilp.ilp1.runtime.EvaluationException;
import fr.upmc.ilp.ilp2.interfaces.IAST2variable;
import fr.upmc.ilp.ilp2.runtime.CommonPlus;

public class CommonPlusScriptContextAdapter
	extends CommonPlus
	implements ScriptContext {

	private ScriptContext context;

	public CommonPlusScriptContextAdapter (ScriptContext context) {
		this.context = context;
	}
	@Override
	public Object getAttribute(String name) {
		return context.getAttribute(name);
	}

	@Override
	public Object getAttribute(String name, int scope) {
		return context.getAttribute(name, scope);
	}

	@Override
	public int getAttributesScope(String name) {
		return context.getAttributesScope(name);
	}

	@Override
	public Bindings getBindings(int scope) {
		return context.getBindings(scope);
	}

	@Override
	public Writer getErrorWriter() {
		return context.getErrorWriter();
	}

	@Override
	public Reader getReader() {
		return context.getReader();
	}

	@Override
	public List<Integer> getScopes() {
		return context.getScopes();
	}

	@Override
	public Writer getWriter() {
		return context.getWriter();
	}

	@Override
	public Object removeAttribute(String name, int scope) {
		return context.removeAttribute(name,scope);
	}

	@Override
	public void setAttribute(String name, Object value, int scope) {
		context.setAttribute(name, value, scope);
	}

	@Override
	public void setBindings(Bindings bindings, int scope) {
		context.setBindings(bindings, scope);
	}

	@Override
	public void setErrorWriter(Writer writer) {
		context.setErrorWriter(writer);
	}

	@Override
	public void setReader(Reader reader) {
		context.setReader(reader);
	}

	@Override
	public void setWriter(Writer writer) {
		context.setWriter(writer);
	}
	@Override
	public Object globalLookup(IAST2variable variable) throws EvaluationException {
		Object res = context.getAttribute(variable.getName(), ScriptContext.GLOBAL_SCOPE);
		if (res == null) {
		  throw new EvaluationException ("global " + variable.getName() + " not found");
		} else {
		  return res;
		}
	}
	@Override
	public void updateGlobal(String globalVariableName, Object value) {
		context.setAttribute(globalVariableName, value, ScriptContext.GLOBAL_SCOPE);
	}
}
