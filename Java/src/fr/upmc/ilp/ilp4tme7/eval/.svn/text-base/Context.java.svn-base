package fr.upmc.ilp.ilp4tme7.eval;

import fr.upmc.ilp.ilp2.interfaces.ICommon;
import fr.upmc.ilp.ilp2.interfaces.ILexicalEnvironment;

/**
 * On réunit en un seul objet, le contexte d'évaluation c'est-�-dire
 * l'environnement lexical et global. */

public class Context implements IContext {

    public Context (ILexicalEnvironment lexenv, ICommon common) {
        this.lexenv = lexenv;
        this.common = common;
    }
    private ILexicalEnvironment lexenv;
    private ICommon common;

    public ILexicalEnvironment getLexicalEnvironment() {
        return this.lexenv;
    }
    public ICommon getCommonEnvironment() {
        return this.common;
    }
}
