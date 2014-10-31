package fr.upmc.ilp.ilp3escl;

import fr.upmc.ilp.ilp2.ast.CEASTparseException;
import fr.upmc.ilp.ilp2.interfaces.IAST2expression;
import fr.upmc.ilp.ilp2.interfaces.IAST2instruction;
import fr.upmc.ilp.ilp3.IAST3Factory;
import fr.upmc.ilp.ilp3esc.ast.CEASTlast;
import fr.upmc.ilp.ilp3esc.ast.CEASTnext;
import fr.upmc.ilp.ilp3esc.ast.CEASTwhileWithEscape;

public interface IAST3esclFactory <Exc extends Exception>
extends IAST3Factory<Exc> {
    
    CEASTwhileWithEscape newWhile (
            IAST2expression<CEASTparseException> condition,
            IAST2instruction<CEASTparseException> body,
            String label);

    CEASTnext newNext (String label);

    CEASTlast newLast (String label);
}
