package fr.upmc.ilp.ilp3escl;

import fr.upmc.ilp.ilp2.ast.CEASTparseException;
import fr.upmc.ilp.ilp2.interfaces.IAST2expression;
import fr.upmc.ilp.ilp2.interfaces.IAST2instruction;

public class CEASTFactory
extends fr.upmc.ilp.ilp3.CEASTFactory
implements IAST3esclFactory<CEASTparseException> {

    public CEASTFactory () {
        super();
    }

    public CEASTwhileWithEscape newWhile (
            final IAST2expression<CEASTparseException> condition,
            final IAST2instruction<CEASTparseException> body,
            String label ) {
        return new CEASTwhileWithEscape(condition, body, label);
    }

    public CEASTnext newNext (String label) {
        return new CEASTnext(label);
    }

    public CEASTlast newLast (String label) {
        return new CEASTlast(label);
    }
}
