/* ******************************************************************
 * ILP -- Implantation d'un langage de programmation.
 * Copyright (C) 2004-2005 <Christian.Queinnec@lip6.fr>
 * $Id: CEASTprogram.java 939 2010-08-21 16:37:57Z queinnec $
 * GPL version>=2
 * ******************************************************************/

package fr.upmc.ilp.ilp4tme7.ast;

import fr.upmc.ilp.ilp1.runtime.EvaluationException;
import fr.upmc.ilp.ilp2.interfaces.ICommon;
import fr.upmc.ilp.ilp2.interfaces.ILexicalEnvironment;
import fr.upmc.ilp.ilp4.ast.FindingInvokedFunctionsException;
import fr.upmc.ilp.ilp4.ast.InliningException;
import fr.upmc.ilp.ilp4.interfaces.IAST4Factory;
import fr.upmc.ilp.ilp4.interfaces.IAST4expression;
import fr.upmc.ilp.ilp4.interfaces.IAST4functionDefinition;
import fr.upmc.ilp.ilp4.interfaces.IAST4globalFunctionVariable;

/** La classe d'un programme composé de fonctions globales et
 * d'instructions. Ce n'est pas une expression ni une instruction mais
 * un programme. */

public class CEASTprogram
extends fr.upmc.ilp.ilp4.ast.CEASTprogram {

    public CEASTprogram (final IAST4functionDefinition[] definitions,
                         final IAST4expression body) {
        super(definitions, body);
    }

    @Override
    public Object eval (final ILexicalEnvironment lexenv,
                        final ICommon common)
    throws EvaluationException {
        throw new RuntimeException("Should never be run!");
    }

  /** On calcule pour chaque fonction globale, les fonctions qu'elle
   * invoque puis on calcule la fermeture transitive. L'ensemble des
   * fonctions invoquées du programme n'est constitué que des seules
   * fonctions invoquées par son corps. */

  @Override
  public void computeInvokedFunctions ()
  throws FindingInvokedFunctionsException {
      IAST4functionDefinition[] definitions = getFunctionDefinitions();
      for ( int i = 0 ; i<definitions.length ; i++ ) {
          definitions[i].computeInvokedFunctions();
      }
      boolean shouldContinue = true;
      while ( shouldContinue ) {
          shouldContinue = false;
          for ( int i = 0 ; i<definitions.length ; i++ ) {
              final IAST4functionDefinition currentFunction = definitions[i];
              for ( IAST4globalFunctionVariable gv :
                      currentFunction.getInvokedFunctions()
                          .toArray(IAST4GFV_EMPTY_ARRAY) ) {
                  // currentFunction invoque gv donc elle invoque
                  // (indirectement) les fonctions qu'invoque gv.
                  final IAST4functionDefinition other =
                    gv.getFunctionDefinition();
                  // | et non || comme remarqué par <Jeremie.Lumbroso@etu.upmc.fr>
                  shouldContinue |= currentFunction
                      .addInvokedFunctions(other.getInvokedFunctions());
                  // NOTA: la precedente methode change la collection que l'on
                  // est en train d'inspecter ce qui pose des problemes
                  // d'acces simultanes a cette collection d'ou l'emploi d'un
                  // toArray() plus haut.
              }
          }
      }
      // Savoir ce qu'invoque le programme est de peu d'utilite!
      findAndAdjoinToInvokedFunctions(getBody());
  }
  
  @Override
  // Nouvelle version avec visiteur:
  public void inline (IAST4Factory factory) throws InliningException {
      throw new RuntimeException("Should never run!");
  }
}

// end of CEASTprogram.java
