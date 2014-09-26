package fr.upmc.ilp.ilp1tme2.eval;

import java.util.List;

import fr.upmc.ilp.ilp1.eval.EAST;
import fr.upmc.ilp.ilp1.eval.EASTFactory;
import fr.upmc.ilp.ilp1.eval.EASTalternative;
import fr.upmc.ilp.ilp1.eval.EASTbinaryOperation;
import fr.upmc.ilp.ilp1.eval.EASTboolean;
import fr.upmc.ilp.ilp1.eval.EASTfloat;
import fr.upmc.ilp.ilp1.eval.EASTinteger;
import fr.upmc.ilp.ilp1.eval.EASTinvocation;
import fr.upmc.ilp.ilp1.eval.EASTprogram;
import fr.upmc.ilp.ilp1.eval.EASTsequence;
import fr.upmc.ilp.ilp1.eval.EASTstring;
import fr.upmc.ilp.ilp1.eval.EASTunaryBlock;
import fr.upmc.ilp.ilp1.eval.EASTunaryOperation;
import fr.upmc.ilp.ilp1.eval.EASTvariable;

public class TME2EASTFactory extends EASTFactory {

    @Override
    public EASTprogram newProgram (List<EAST> asts) {
        return new TME2EASTprogram(asts);
    }

    @Override
    public EASTsequence newSequence (List<EAST> asts) {
        return new TME2EASTsequence(asts);
    }

    @Override
    public EASTalternative newAlternative (EAST condition,
                                           EAST consequent) {
        return new TME2EASTalternative(condition, consequent);
    }

    @Override
    public EASTalternative newAlternative (EAST condition,
                                           EAST consequent,
                                           EAST alternant) {
        return new TME2EASTalternative(condition, consequent, alternant);
    }

    @Override
    public EASTunaryBlock newUnaryBlock (EASTvariable variable,
                                         EAST initialisation,
                                         EASTsequence body) {
        return new TME2EASTunaryBlock(variable, initialisation, body);
    }

    @Override
    public EASTvariable newVariable (String name) {
        return new TME2EASTvariable(name);
    }

    @Override
    public EASTinvocation newInvocation (String name, List<EAST> asts) {
        return new TME2EASTinvocationPrimitive(name, asts);
    }

    @Override
    public EASTunaryOperation newUnaryOperation (String operatorName,
                                                 EAST operand) {
        return new TME2EASTunaryOperation(operatorName, operand);
    }

    @Override
    public EASTbinaryOperation newBinaryOperation (String operatorName,
                                                   EAST leftOperand,
                                                   EAST rightOperand) {
        return new TME2EASTbinaryOperation(operatorName, leftOperand, rightOperand);
    }

    @Override
    public EASTinteger newIntegerConstant (String value) {
        return new TME2EASTinteger(value);
    }

    @Override
    public EASTfloat newFloatConstant (String value) {
        return new TME2EASTfloat(value);
    }

    @Override
    public EASTstring newStringConstant (String value) {
        return new TME2EASTstring(value);
    }

    @Override
    public EASTboolean newBooleanConstant (String value) {
        return new TME2EASTboolean(value);
    }

}
