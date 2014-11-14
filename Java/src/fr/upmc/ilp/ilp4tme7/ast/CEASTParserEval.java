/* ******************************************************************
 * ILP -- Implantation d'un langage de programmation.
 * Copyright (C) 2004 <Christian.Queinnec@lip6.fr>
 * $Id: CEASTParser.java 918 2010-01-15 16:51:15Z queinnec $
 * GPL version>=2
 * ******************************************************************/

package fr.upmc.ilp.ilp4tme7.ast;

import fr.upmc.ilp.ilp4.interfaces.IAST4Factory;

/** Transformer un document XML en un CEAST. */

public class CEASTParserEval extends fr.upmc.ilp.ilp4.ast.CEASTParser {

    public CEASTParserEval (IAST4Factory factory) {
        super(factory);
        addParser("program1", CEASTprogramEval.class);
        addParser("program2", CEASTprogramEval.class);
        addParser("program3", CEASTprogramEval.class);
        addParser("program4", CEASTprogramEval.class);
    }
}

// end of CEASTParser.java
