package fr.upmc.ilp.ilp2tme4.ast;

import fr.upmc.ilp.ilp2.ast.CEASTparseException;
import fr.upmc.ilp.ilp2.interfaces.IAST2expression;
import fr.upmc.ilp.ilp2.interfaces.IAST2instruction;
import fr.upmc.ilp.ilp2tme4.interfaces.IAST2Factory;
import fr.upmc.ilp.ilp2tme4.ast.CEASTaMoinsQue;

public class CEASTFactory extends fr.upmc.ilp.ilp2.ast.CEASTFactory
implements IAST2Factory<CEASTparseException> {

    public fr.upmc.ilp.ilp2tme4.interfaces.IAST2aMoinsQue newAMoinsQue(
            IAST2expression<CEASTparseException> condition,
            IAST2instruction<CEASTparseException> corps) {
        return new CEASTaMoinsQue(condition, corps);
    }

}
