package fr.upmc.ilp.ilp3esc;

import java.io.IOException;

import fr.upmc.ilp.ilp2.cgen.CgenEnvironment;
import fr.upmc.ilp.ilp2.interfaces.ICgenEnvironment;
import fr.upmc.ilp.ilp2.interfaces.ICgenLexicalEnvironment;
import fr.upmc.ilp.ilp2.interfaces.ICommon;
import fr.upmc.ilp.ilp2.runtime.CommonPlus;
import fr.upmc.ilp.ilp2.runtime.ConstantsStuff;
import fr.upmc.ilp.ilp2.runtime.PrintStuff;
import fr.upmc.ilp.ilp3.ThrowPrimitive;
import fr.upmc.ilp.ilp3esc.ast.CEASTFactory;
import fr.upmc.ilp.ilp3esc.ast.CEASTParser;
import fr.upmc.ilp.ilp3esc.cgen.CgenLexicalEnvironment;
import fr.upmc.ilp.ilp3esc.interfaces.ILexicalEnvironment;
import fr.upmc.ilp.ilp3esc.runtime.LexicalEnvironment;
import fr.upmc.ilp.tool.IFinder;

public class Process extends fr.upmc.ilp.ilp3.Process {
    
    public Process (IFinder finder) throws IOException {
        super(finder);
        this.setGrammar(getFinder().findFile("grammar3esc.rng"));
        CEASTFactory factory = new CEASTFactory();
        setFactory(factory);
        setParser(new CEASTParser(factory));
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

            this.result = getCEAST().eval(intlexenv, intcommon);
            this.printing = intps.getPrintedOutput().trim();

            this.interpreted = true;

        } catch (Throwable e) {
            this.interpretationFailure = e;
        }
    }

    /** Compilation vers C. */

    @Override
    public void compile() {
        try {
            final ICgenEnvironment common = new CgenEnvironment();
            common.bindPrimitive("throw");
            final ICgenLexicalEnvironment lexenv =
                CgenLexicalEnvironment.CgenEmptyLexicalEnvironment.create();
            this.ccode = getCEAST().compile(lexenv, common);

            this.compiled = true;

        } catch (Throwable e) {
            this.compilationFailure = e;
        }
    }

}