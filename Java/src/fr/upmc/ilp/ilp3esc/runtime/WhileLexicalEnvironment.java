package fr.upmc.ilp.ilp3esc.runtime;

import fr.upmc.ilp.ilp1.runtime.EvaluationException;
import fr.upmc.ilp.ilp2.interfaces.IAST2variable;
import fr.upmc.ilp.ilp3esc.interfaces.ILexicalEnvironment;

public class WhileLexicalEnvironment extends LexicalEnvironment {

    // L'extenseur indiquant que l'on entre dans le corps d'une boucle:
    public WhileLexicalEnvironment(ILexicalEnvironment next) {
        super(null, next);
        this.label = "loop";
    }
    // L'extenseur indiquant que l'on entre dans le corps d'une boucle nommée:
    public WhileLexicalEnvironment(ILexicalEnvironment next, String label) {
        super(null, next);
        this.label = label;
    }
    protected String label;
    
    @Override
    public boolean isPresent (final IAST2variable variable) {
        return getNext().isPresent(variable);
    }

    public Object lookup (IAST2variable variable)
    throws EvaluationException {
        return getNext().lookup(variable);
    }

    public void update (final IAST2variable variable,
                        final Object value)
      throws EvaluationException {
        getNext().update(variable, value);
    }
    
    public boolean isWithinWhile() {
        return true;
    }
    public boolean isWithinWhile(String label) {
        return label.equals(this.label);
    }
 }
