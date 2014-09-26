package fr.upmc.ilp.ilp2tme4.interfaces;

import fr.upmc.ilp.ilp2.ast.CEASTparseException;
import fr.upmc.ilp.ilp2.interfaces.IAST2expression;
import fr.upmc.ilp.ilp2.interfaces.IAST2instruction;
import fr.upmc.ilp.ilp2tme4.interfaces.IAST2aMoinsQue;

public interface IAST2Factory<Exc extends Exception>
extends fr.upmc.ilp.ilp2.interfaces.IAST2Factory<Exc> {

    public IAST2aMoinsQue newAMoinsQue(
            IAST2expression<CEASTparseException> condition,
            IAST2instruction<CEASTparseException> corps) ;

}
