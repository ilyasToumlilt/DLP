package fr.upmc.ilp.ilp3esc.ast;

import fr.upmc.ilp.ilp2.ast.CEASTparseException;
import fr.upmc.ilp.ilp2.interfaces.IAST2expression;
import fr.upmc.ilp.ilp2.interfaces.IAST2instruction;
import fr.upmc.ilp.ilp3esc.interfaces.IAST3escFactory;

public class CEASTFactory
extends fr.upmc.ilp.ilp3.CEASTFactory
implements IAST3escFactory<CEASTparseException> {

    public CEASTFactory () {
        super();
    }

    @Override
    public CEASTwhileWithEscape newWhile (
            final IAST2expression<CEASTparseException> condition,
            final IAST2instruction<CEASTparseException> body ) {
        return new CEASTwhileWithEscape(condition, body);
    }

    public CEASTnext newNext () {
        return new CEASTnext();
    }

    public CEASTlast newLast () {
        return new CEASTlast();
    }
}
