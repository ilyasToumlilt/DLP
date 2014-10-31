package fr.upmc.ilp.ilp3esc.ast;

import java.util.Set;

import org.w3c.dom.Element;

import fr.upmc.ilp.ilp1.cgen.CgenerationException;
import fr.upmc.ilp.ilp1.runtime.EvaluationException;
import fr.upmc.ilp.ilp2.ast.CEASTinstruction;
import fr.upmc.ilp.ilp2.ast.CEASTparseException;
import fr.upmc.ilp.ilp2.interfaces.IAST2variable;
import fr.upmc.ilp.ilp2.interfaces.ICgenEnvironment;
import fr.upmc.ilp.ilp2.interfaces.ICgenLexicalEnvironment;
import fr.upmc.ilp.ilp2.interfaces.ICommon;
import fr.upmc.ilp.ilp2.interfaces.IDestination;
import fr.upmc.ilp.ilp2.interfaces.ILexicalEnvironment;
import fr.upmc.ilp.ilp2.interfaces.IParser;
import fr.upmc.ilp.ilp3esc.runtime.WhileNextException;

public class CEASTnext extends CEASTinstruction {

    public CEASTnext () {}

    public static CEASTnext parse (
            final Element e, final IParser<CEASTparseException> parser)
    throws CEASTparseException {
        CEASTFactory factory = (CEASTFactory) parser.getFactory();
        return factory.newNext();
    }

    //NOTE: Accès direct aux champs interdit à partir d'ici!

    public Object eval (final ILexicalEnvironment lexenv,
                        final ICommon common)
    throws EvaluationException {
        final fr.upmc.ilp.ilp3esc.interfaces.ILexicalEnvironment le =
            fr.upmc.ilp.ilp3esc.runtime.LexicalEnvironment.narrow(lexenv);
        if ( le.isWithinWhile() ) {
            throw new WhileNextException();
        } else {
            final String message = "Hors boucle!";
            throw new EvaluationException(message);
        }
    }

    public void compileInstruction(
            final StringBuffer buffer,
            final ICgenLexicalEnvironment lexenv,
            final ICgenEnvironment common,
            final IDestination destination)
    throws CgenerationException {
        final fr.upmc.ilp.ilp3esc.interfaces.ICgenLexicalEnvironment le =
            fr.upmc.ilp.ilp3esc.cgen.CgenLexicalEnvironment.narrow(lexenv);
        if ( le.isWithinWhile() ) {
            buffer.append("continue;\n");
        } else {
            final String msg = "Hors boucle!";
            throw new CgenerationException(msg);
        }
    }
    
    @Override
    public void
    findGlobalVariables (final Set<IAST2variable> globalvars,
                       final ICgenLexicalEnvironment lexenv ) {
        return;
    }
}
