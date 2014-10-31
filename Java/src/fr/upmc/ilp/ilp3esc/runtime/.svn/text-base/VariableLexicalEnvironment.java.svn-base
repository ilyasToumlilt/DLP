package fr.upmc.ilp.ilp3esc.runtime;

import fr.upmc.ilp.ilp1.runtime.EvaluationException;
import fr.upmc.ilp.ilp2.interfaces.IAST2variable;
import fr.upmc.ilp.ilp3esc.interfaces.ILexicalEnvironment;

public class VariableLexicalEnvironment extends LexicalEnvironment {

    public VariableLexicalEnvironment (final IAST2variable variable,
                                       final Object value,
                                       final ILexicalEnvironment next )
    {
        super(variable, next);
        this.value = value;
    }
    protected Object value;

    @Override
    public boolean isPresent (final IAST2variable variable) {
        if ( getVariable().equals(variable) ) {
            return true;
        } else {
            return getNext().isPresent(variable);
        }
    }

    public Object lookup (IAST2variable variable)
    throws EvaluationException {
        if ( getVariable().equals(variable) ) {
            return this.value;
        } else {
            return getNext().lookup(variable);
        }
    }

    public void update (final IAST2variable variable,
                        final Object value)
      throws EvaluationException {
      if ( getVariable().equals(variable) ) {
          this.value = value;
      } else {
          getNext().update(variable, value);
      }
    }

    public boolean isWithinWhile() {
        return getNext().isWithinWhile();
    }
    public boolean isWithinWhile(String label) {
        return getNext().isWithinWhile(label);
    }
}
