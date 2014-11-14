package fr.upmc.ilp.ilp4tme7.ast;

import java.util.HashSet;
import java.util.Set;

import fr.upmc.ilp.ilp4.interfaces.AbstractExplicitVisitor;
import fr.upmc.ilp.ilp4.interfaces.IAST4functionDefinition;
import fr.upmc.ilp.ilp4.interfaces.IAST4globalFunctionVariable;
import fr.upmc.ilp.ilp4.interfaces.IAST4globalInvocation;
import fr.upmc.ilp.ilp4.interfaces.IAST4invocation;
import fr.upmc.ilp.ilp4.interfaces.IAST4program;
import fr.upmc.ilp.ilp4.interfaces.IAST4variable;

/**
 * On herite d'un AbstractExplicitVisitor qui, d'emblée, sait traverser
 * à peu près tout IAST.
 */

public class InvokedFunctionVisitor 
extends AbstractExplicitVisitor<Set<IAST4globalFunctionVariable>, RuntimeException> {
    
    public InvokedFunctionVisitor () {}
    
    // Un point d'entrée commode:
    public static void computeInvokedFunctions (IAST4program iast) {
        InvokedFunctionVisitor visitor = new InvokedFunctionVisitor();
        visitor.visit(iast, new HashSet<IAST4globalFunctionVariable>());                    
    }

    @Override
    public Set<IAST4globalFunctionVariable> visit(
            IAST4invocation iast, 
            Set<IAST4globalFunctionVariable> data) {
        // Normalement, apres normalisation, il n'y a plus de tels noeuds.
        throw new RuntimeException("Should never occur!");
    }

    @Override
    public Set<IAST4globalFunctionVariable> visit(
            IAST4globalInvocation iast, 
            Set<IAST4globalFunctionVariable> data) {
        super.visit(iast, data);
        data.add(iast.getFunctionGlobalVariable());
        return data;
    }

    @Override
    public Set<IAST4globalFunctionVariable> visit(
            IAST4program iast, 
            Set<IAST4globalFunctionVariable> data) {
        IAST4functionDefinition[] definitions = iast.getFunctionDefinitions();
        for ( int i = 0 ; i<definitions.length ; i++ ) {
            Set<IAST4globalFunctionVariable> gfv = new HashSet<>();
            definitions[i].accept(this, gfv);
            definitions[i].setInvokedFunctions(gfv);
        }
        boolean shouldContinue = true;
        while ( shouldContinue ) {
            shouldContinue = false;
            for ( int i = 0 ; i<definitions.length ; i++ ) {
                final IAST4functionDefinition currentFunction = definitions[i];
                for ( IAST4globalFunctionVariable gv :
                        currentFunction.getInvokedFunctions()
                            .toArray(CEASTprogram.IAST4GFV_EMPTY_ARRAY) ) {
                    final IAST4functionDefinition other =
                      gv.getFunctionDefinition();
                    shouldContinue |= currentFunction
                        .addInvokedFunctions(other.getInvokedFunctions());
                }
            }
        }
        // Pas la peine de savoir ce qu'invoque le corps du programme!
        return null;
    }

    public Set<IAST4globalFunctionVariable> visit(
            IAST4variable iast, 
            Set<IAST4globalFunctionVariable> data) {
        return data;
    }
}
