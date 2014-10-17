package fr.upmc.ilp.ilp2tme4.interfaces;

import fr.upmc.ilp.ilp2.ast.CEASTparseException;
import fr.upmc.ilp.ilp2.interfaces.IAST2;
import fr.upmc.ilp.ilp2.interfaces.IAST2instruction;

public interface IAST2aMoinsQue extends IAST2instruction<CEASTparseException> {
    public IAST2<CEASTparseException> getCondition();
    public IAST2<CEASTparseException> getCorps();
}
