package fr.upmc.ilp.ilp3escl;

import org.w3c.dom.Element;

import fr.upmc.ilp.ilp1.cgen.CgenerationException;
import fr.upmc.ilp.ilp1.runtime.EvaluationException;
import fr.upmc.ilp.ilp2.ast.CEASTparseException;
import fr.upmc.ilp.ilp2.interfaces.ICgenEnvironment;
import fr.upmc.ilp.ilp2.interfaces.ICgenLexicalEnvironment;
import fr.upmc.ilp.ilp2.interfaces.ICommon;
import fr.upmc.ilp.ilp2.interfaces.IDestination;
import fr.upmc.ilp.ilp2.interfaces.ILexicalEnvironment;
import fr.upmc.ilp.ilp2.interfaces.IParser;

public class CEASTnext extends fr.upmc.ilp.ilp3esc.ast.CEASTnext {

    public CEASTnext (final String label) {
        super();
        this.label = label;
    }
    private final String label;

    public String getLabel () {
        return this.label;
    }

    /** Le constructeur analysant syntaxiquement un DOM. */

    public static CEASTnext parse (
            final Element e, final IParser<CEASTparseException> parser)
    throws CEASTparseException {
        CEASTFactory factory = (CEASTFactory) parser.getFactory();
        return factory.newNext(e.getAttribute("label"));
    }

    //NOTE: Accès direct aux champs interdit à partir d'ici!

    @Override
    public Object eval(ILexicalEnvironment lexenv, ICommon common)
    throws EvaluationException {
        final fr.upmc.ilp.ilp3esc.interfaces.ILexicalEnvironment le =
            fr.upmc.ilp.ilp3esc.runtime.LexicalEnvironment.narrow(lexenv);
        if ( le.isWithinWhile() ) {
            throw new WhileNextException(getLabel());
        } else {
            final String message = "Hors boucle "+ getLabel();
            throw new EvaluationException(message);
        }
    }

    @Override
    public void compileInstruction(
            StringBuffer buffer,
            ICgenLexicalEnvironment lexenv,
            ICgenEnvironment common,
            IDestination destination)
    throws CgenerationException {
        final fr.upmc.ilp.ilp3esc.interfaces.ICgenLexicalEnvironment le =
            fr.upmc.ilp.ilp3esc.cgen.CgenLexicalEnvironment.narrow(lexenv);
        if ( le.isWithinWhile() ) {
            buffer.append("goto ILP_next_" + getLabel() + ";\n");
        } else {
            final String msg = "Hors boucle " + getLabel();
            throw new CgenerationException(msg);
        }
    }

}
