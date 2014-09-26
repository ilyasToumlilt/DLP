package fr.upmc.ilp.ilp1tme1;

import java.util.List;

import fr.upmc.ilp.ilp1.fromxml.AST;
import fr.upmc.ilp.ilp1.fromxml.ASTException;
import fr.upmc.ilp.ilp1.interfaces.IAST;

public class ASTsequence 
extends fr.upmc.ilp.ilp1.fromxml.ASTsequence
implements fr.upmc.ilp.ilp1tme1.IASTsequence {
    
    public ASTsequence(List<AST> instructions) {
        super(instructions);
    }
    
    public IAST[] getAllButLastInstructions() throws ASTException {
        int length = this.getInstructionsLength();
        try {
            IAST[] _instructions = new IAST[length-1];
            for (int i = 0; i<length-1; i++) {
                _instructions[i] = this.getInstruction(i);
            }
            return _instructions;
        } catch (Exception e) {
            throw new ASTException(e.getMessage());
        }
    }
}
