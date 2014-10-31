package fr.upmc.ilp.ilp3esc.cgen;

import fr.upmc.ilp.ilp1.cgen.CgenerationException;
import fr.upmc.ilp.ilp2.interfaces.IAST2variable;
import fr.upmc.ilp.ilp3esc.interfaces.ICgenLexicalEnvironment;

public class WhileCgenLexicalEnvironment extends CgenLexicalEnvironment {

    // L'extenseur indiquant que l'on entre dans le corps d'une boucle:
    public WhileCgenLexicalEnvironment(ICgenLexicalEnvironment next) {
        super(next);
        this.label = "loop";
    }
    // L'extenseur indiquant que l'on entre dans le corps d'une boucle nommée:
    public WhileCgenLexicalEnvironment(ICgenLexicalEnvironment next, String label) {
        super(next);
        this.label = label;
    }
    protected String label;
    
    public boolean isWithinWhile() {
        return true;
    }
    public boolean isWithinWhile(String label) {
        return label.equals(this.label);
    }

    @Override
    public ICgenLexicalEnvironment shrink (final IAST2variable v) {
        return (ICgenLexicalEnvironment) getNext().shrink(v);
    }
    public String compile(IAST2variable variable) throws CgenerationException {
        return getNext().compile(variable);
    }
}
