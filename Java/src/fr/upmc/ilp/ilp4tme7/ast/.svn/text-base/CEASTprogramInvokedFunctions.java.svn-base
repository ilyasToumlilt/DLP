/* ******************************************************************
 * ILP -- Implantation d'un langage de programmation.
 * Copyright (C) 2004-2005 <Christian.Queinnec@lip6.fr>
 * $Id: CEASTprogram.java 939 2010-08-21 16:37:57Z queinnec $
 * GPL version>=2
 * ******************************************************************/

package fr.upmc.ilp.ilp4tme7.ast;

import fr.upmc.ilp.ilp4.ast.FindingInvokedFunctionsException;
import fr.upmc.ilp.ilp4.interfaces.IAST4expression;
import fr.upmc.ilp.ilp4.interfaces.IAST4functionDefinition;

/** La classe d'un programme composé de fonctions globales et
 * d'instructions. Ce n'est pas une expression ni une instruction mais
 * un programme. */

public class CEASTprogramInvokedFunctions
extends fr.upmc.ilp.ilp4.ast.CEASTprogram {

    public CEASTprogramInvokedFunctions (final IAST4functionDefinition[] definitions,
                         final IAST4expression body) {
        super(definitions, body);
    }

  /** On calcule pour chaque fonction globale, les fonctions qu'elle
   * invoque puis on calcule la fermeture transitive. L'ensemble des
   * fonctions invoquées du programme n'est constitué que des seules
   * fonctions invoquées par son corps. */

  @Override
  public void computeInvokedFunctions ()
  throws FindingInvokedFunctionsException {
	  throw new RuntimeException("Should never run!");
  }
}

// end of CEASTprogram.java
