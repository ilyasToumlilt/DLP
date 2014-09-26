package fr.upmc.ilp.ilp2tme4.ast;

import java.util.Set;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import fr.upmc.ilp.ilp1.cgen.CgenerationException;
import fr.upmc.ilp.ilp1.runtime.EvaluationException;
import fr.upmc.ilp.ilp2.ast.CEAST;
import fr.upmc.ilp.ilp2.ast.CEASTinstruction;
import fr.upmc.ilp.ilp2.ast.CEASTparseException;
import fr.upmc.ilp.ilp2.interfaces.IAST2;
import fr.upmc.ilp.ilp2.interfaces.IAST2expression;
import fr.upmc.ilp.ilp2.interfaces.IAST2instruction;
import fr.upmc.ilp.ilp2.interfaces.IAST2variable;
import fr.upmc.ilp.ilp2.interfaces.ICgenEnvironment;
import fr.upmc.ilp.ilp2.interfaces.ICgenLexicalEnvironment;
import fr.upmc.ilp.ilp2.interfaces.ICommon;
import fr.upmc.ilp.ilp2.interfaces.IDestination;
import fr.upmc.ilp.ilp2.interfaces.ILexicalEnvironment;
import fr.upmc.ilp.ilp2.interfaces.IParser;
import fr.upmc.ilp.ilp2tme4.interfaces.IAST2aMoinsQue;

public class CEASTaMoinsQue extends CEASTinstruction implements IAST2aMoinsQue {

    protected CEAST condition;
    protected CEAST corps;

    public CEASTaMoinsQue(
            IAST2expression<CEASTparseException> condition,
            IAST2instruction<CEASTparseException> corps) 
    {
        this.condition = (CEAST) condition;
        this.corps = (CEAST) corps;
    }

    public IAST2<CEASTparseException> getCorps() {
        return corps;
    }

    public IAST2<CEASTparseException> getCondition() {
        return condition;
    }

    public static IAST2aMoinsQue parse(
              final Element e, final IParser<CEASTparseException> parser)
    throws CEASTparseException
    {
        final NodeList nl = e.getChildNodes();
        IAST2expression<CEASTparseException> condition =
            (IAST2expression<CEASTparseException>)
                parser.findThenParseChildAsUnique(nl, "condition");
        IAST2instruction<CEASTparseException> corps =
            (IAST2instruction<CEASTparseException>)
                parser.findThenParseChildAsSequence(nl, "corps");
        return new CEASTaMoinsQue(condition, corps) ;
    }

    
    public Object eval(ILexicalEnvironment lexenv, ICommon common)
            throws EvaluationException {
        Object bool = this.condition.eval(lexenv, common);
        if( bool == Boolean.FALSE)  {
            return this.corps.eval(lexenv, common);
        } else {
            return CEASTinstruction.voidInstruction().eval(lexenv, common);
        }
    }

    public void compileInstruction(StringBuffer buffer,
            ICgenLexicalEnvironment lexenv, ICgenEnvironment common,
            IDestination destination) throws CgenerationException
    {
        buffer.append(" if ( !ILP_isEquivalentToTrue( ");
        ((IAST2expression<CEASTparseException>) getCondition()).
            compileExpression(buffer, lexenv, common);
        buffer.append(" ) ) {\n");
        ((IAST2instruction<CEASTparseException>) getCorps()).
            compileInstruction(buffer, lexenv, common, destination);
        buffer.append(";\n} else {\n");
        buffer.append(";\n}");
    }

    @Override
    public void findGlobalVariables (
        final Set<IAST2variable> globalvars,
        final ICgenLexicalEnvironment lexenv )
    {
        getCondition().findGlobalVariables(globalvars, lexenv);
        getCorps().findGlobalVariables(globalvars, lexenv);
    }

}
