package fr.upmc.ilp.ilp4tme7;

import java.io.IOException;

import org.w3c.dom.Document;

import fr.upmc.ilp.ilp2.interfaces.ICommon;
import fr.upmc.ilp.ilp2.interfaces.ILexicalEnvironment;
import fr.upmc.ilp.ilp2.runtime.ConstantsStuff;
import fr.upmc.ilp.ilp4.ast.GlobalCollector;
import fr.upmc.ilp.ilp4.interfaces.IAST4visitor;
import fr.upmc.ilp.ilp4.runtime.CommonPlus;
import fr.upmc.ilp.ilp4.runtime.LexicalEnvironment;
import fr.upmc.ilp.ilp4.runtime.PrintStuff;
import fr.upmc.ilp.ilp4tme7.ast.CEASTParser;
import fr.upmc.ilp.ilp4tme7.ast.InvokedFunctionVisitor;
import fr.upmc.ilp.ilp4tme7.ast.VisitorInlining;
import fr.upmc.ilp.ilp4tme7.eval.Context;
import fr.upmc.ilp.ilp4tme7.eval.IContext;
import fr.upmc.ilp.ilp4tme7.eval.ThrowPrimitive;
import fr.upmc.ilp.ilp4tme7.eval.VisitorEvaluationException;
import fr.upmc.ilp.ilp4tme7.eval.VisitorEvaluator;
import fr.upmc.ilp.tool.IFinder;

/**
 * Cette version introduit des fonctions localement definies. Le compilateur
 * reste à écrire.
 */

public class Process extends fr.upmc.ilp.ilp4.Process {

    public Process (IFinder finder) throws IOException {
        super(finder);
        setParser(new CEASTParser(getFactory()));
    }
    
    /** Initialisation: @see fr.upmc.ilp.tool.AbstractProcess. */

    /** Préparation  */
    
    @Override
    public void prepare() {
        try {
            final Document d = getDocument(this.rngFile);
            setCEAST(getParser().parse(d));

            // Toutes les analyses statiques
            setCEAST(performNormalization());
            //getCEAST().computeInvokedFunctions(); devient:
            InvokedFunctionVisitor.computeInvokedFunctions(getCEAST());
            //getCEAST().inline(getFactory()); devient:
            VisitorInlining.inline(getCEAST(), getFactory());
            //getCEAST().computeGlobalVariables(); devient:
            getCEAST().setGlobalVariables(
                  GlobalCollector.getGlobalVariables(getCEAST()));

            this.prepared = true;

        } catch (Throwable e) {
            this.preparationFailure = e;
        }
    }
    
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
