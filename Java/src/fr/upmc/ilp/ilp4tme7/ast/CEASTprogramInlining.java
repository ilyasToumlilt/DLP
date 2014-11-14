/* ******************************************************************
 * ILP -- Implantation d'un langage de programmation.
 * Copyright (C) 2004-2005 <Christian.Queinnec@lip6.fr>
 * $Id: CEASTprogram.java 939 2010-08-21 16:37:57Z queinnec $
 * GPL version>=2
 * ******************************************************************/

package fr.upmc.ilp.ilp4tme7.ast;

import fr.upmc.ilp.ilp4.ast.InliningException;
import fr.upmc.ilp.ilp4.interfaces.IAST4Factory;
import fr.upmc.ilp.ilp4.interfaces.IAST4expression;
import fr.upmc.ilp.ilp4.interfaces.IAST4functionDefinition;

/** La classe d'un programme composé de fonctions globales et
 * d'instructions. Ce n'est pas une expression ni une instruction mais
 * un programme. */

public class CEASTprogramInlining
extends fr.upmc.ilp.ilp4.ast.CEASTprogram {

    public CEASTprogramInlining (final IAST4functionDefinition[] definitions,
                         final IAST4expression body) {
        super(definitions, body);
    }
  
  @Override
  // Nouvelle version avec visiteur:
  public void inline (IAST4Factory factory) throws InliningException {
      throw new RuntimeException("Should never run!");
  }
}

// end of CEASTprogram.java
