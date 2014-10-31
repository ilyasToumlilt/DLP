package fr.upmc.ilp.ilp3esc.cgen;

import fr.upmc.ilp.ilp1.cgen.CgenerationException;
import fr.upmc.ilp.ilp2.interfaces.IAST2variable;
import fr.upmc.ilp.ilp3esc.interfaces.ICgenLexicalEnvironment;

public class VariableCgenLexicalEnvironment extends CgenLexicalEnvironment {

    public VariableCgenLexicalEnvironment (final IAST2variable variable,
                                           final String compiledName,
                                           final ICgenLexicalEnvironment next) {
        super(variable, next);
        this.compiledName = compiledName;
    }
    protected final String compiledName;
    
    public boolean isWithinWhile() {
        return getNext().isWithinWhile();
    }
    public boolean isWithinWhile(final String label) {
        return getNext().isWithinWhile(label);
    }
    
    public String compile(IAST2variable variable) throws CgenerationException {
        if ( getVariable().equals(variable) ) {
            return this.compiledName;
        } else {
            return getNext().compile(variable);
        }
    }
}
