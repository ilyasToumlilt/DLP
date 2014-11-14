package fr.upmc.ilp.ilp4tme7.ast;

import fr.upmc.ilp.ilp4.interfaces.AbstractExplicitVisitor;
import fr.upmc.ilp.ilp4.interfaces.IAST4Factory;
import fr.upmc.ilp.ilp4.interfaces.IAST4expression;
import fr.upmc.ilp.ilp4.interfaces.IAST4functionDefinition;
import fr.upmc.ilp.ilp4.interfaces.IAST4globalInvocation;
import fr.upmc.ilp.ilp4.interfaces.IAST4invocation;
import fr.upmc.ilp.ilp4.interfaces.IAST4program;
import fr.upmc.ilp.ilp4.interfaces.IAST4variable;

/**
 * On herite d'un AbstractExplicitVisitor qui, d'emblée, sait traverser
 * à peu près tout IAST. Qu'il retourne ici un IAST4Factory n'est pas gênant
 * puisque l'on utilise le visiteur pour son effet et non pour sa valeur.
 */

public class VisitorInlining extends AbstractExplicitVisitor<IAST4Factory, RuntimeException> {
    
    public VisitorInlining () {}
    
    // Un point d'entrée commode:
    public static void inline (IAST4program iast, IAST4Factory factory) {
        final VisitorInlining vi = new VisitorInlining();
        vi.visit(iast, factory);
    }

    @Override
    public IAST4Factory visit(IAST4invocation iast, IAST4Factory factory) {
        // Normalement, apres normalisation, il n'y a plus de tels noeuds.
        throw new RuntimeException("Should never occur!");
    }

    @Override
    public IAST4Factory visit(IAST4globalInvocation iast, IAST4Factory factory) {
        if ( iast.getInlined() != null ) {
            return factory;
        } else {
            // On analyse les arguments!
            for ( IAST4expression arg : iast.getArguments() ) {
                arg.accept(this, factory);
            }
            final IAST4functionDefinition function =
                iast.getFunctionGlobalVariable().getFunctionDefinition();
            if ( function.isRecursive() ) {
                // On n'intègre pas les fonctions récursives!
                return factory;
            } else {
                // La fonction a toutes les qualités requises, on l'intègre!
                iast.setInlined(factory.newLocalBlock(
                        function.getVariables(),
                        iast.getArguments(),
                        function.getBody()));
                return factory;
            }
        }

    }

    public IAST4Factory visit(IAST4variable iast, IAST4Factory factory) {
        // Les variables ne s'integrent pas. 
        return null;
    }
}
