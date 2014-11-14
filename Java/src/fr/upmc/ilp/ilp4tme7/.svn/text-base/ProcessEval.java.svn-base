package fr.upmc.ilp.ilp4tme7;

import java.io.IOException;

import fr.upmc.ilp.ilp2.interfaces.ICommon;
import fr.upmc.ilp.ilp2.interfaces.ILexicalEnvironment;
import fr.upmc.ilp.ilp2.runtime.ConstantsStuff;
import fr.upmc.ilp.ilp4.interfaces.IAST4visitor;
import fr.upmc.ilp.ilp4.runtime.CommonPlus;
import fr.upmc.ilp.ilp4.runtime.LexicalEnvironment;
import fr.upmc.ilp.ilp4.runtime.PrintStuff;
import fr.upmc.ilp.ilp4tme7.ast.CEASTParserEval;
import fr.upmc.ilp.ilp4tme7.eval.Context;
import fr.upmc.ilp.ilp4tme7.eval.IContext;
import fr.upmc.ilp.ilp4tme7.eval.ThrowPrimitive;
import fr.upmc.ilp.ilp4tme7.eval.VisitorEvaluationException;
import fr.upmc.ilp.ilp4tme7.eval.VisitorEvaluator;
import fr.upmc.ilp.tool.IFinder;

/**
 * Cette version réalise l'évaluation à l'aide d'un visiteur.
 */

public class ProcessEval extends fr.upmc.ilp.ilp4.Process {

    public ProcessEval (IFinder finder) throws IOException {
        super(finder);
        setParser(new CEASTParserEval(getFactory()));
    }
    
    /** Initialisation: @see fr.upmc.ilp.tool.AbstractProcess. */

    /** Préparation (héritée) */
 
    /** Interpretation */

    @Override
    public void interpret() {
        try {
            final ICommon intcommon = new CommonPlus();
            intcommon.bindPrimitive("throw", ThrowPrimitive.create());
            final ILexicalEnvironment intlexenv =
                LexicalEnvironment.EmptyLexicalEnvironment.create();
            final PrintStuff intps = new PrintStuff();
            intps.extendWithPrintPrimitives(intcommon);
            final ConstantsStuff csps = new ConstantsStuff();
            csps.extendWithPredefinedConstants(intcommon);

            IContext context = new Context(intlexenv, intcommon);
            IAST4visitor<IContext, Object, VisitorEvaluationException> visitor = 
                    new VisitorEvaluator();

            this.result = getCEAST().accept(visitor, context);
            this.printing = intps.getPrintedOutput().trim();

            this.interpreted = true;

        } catch (Throwable e) {
            this.interpretationFailure = e;
        }
    }
    
    /** Compilation vers C (héritée) */

    /** Exécution du programme compilé (héritée) */
}
