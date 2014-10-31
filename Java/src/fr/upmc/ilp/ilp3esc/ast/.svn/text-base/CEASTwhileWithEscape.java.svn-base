/* ******************************************************************
 * ILP -- Implantation d'un langage de programmation.
 * Copyright (C) 2004 <Christian.Queinnec@lip6.fr>
 * $Id: CEASTwhileWithEscape.java 921 2010-08-18 14:41:55Z queinnec $
 * GPL version>=2
 * ******************************************************************/

package fr.upmc.ilp.ilp3esc.ast;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import fr.upmc.ilp.ilp1.cgen.CgenerationException;
import fr.upmc.ilp.ilp1.runtime.EvaluationException;
import fr.upmc.ilp.ilp2.ast.CEASTinstruction;
import fr.upmc.ilp.ilp2.ast.CEASTparseException;
import fr.upmc.ilp.ilp2.ast.CEASTwhile;
import fr.upmc.ilp.ilp2.cgen.VoidDestination;
import fr.upmc.ilp.ilp2.interfaces.IAST2expression;
import fr.upmc.ilp.ilp2.interfaces.IAST2instruction;
import fr.upmc.ilp.ilp2.interfaces.IAST2while;
import fr.upmc.ilp.ilp2.interfaces.ICgenEnvironment;
import fr.upmc.ilp.ilp2.interfaces.ICgenLexicalEnvironment;
import fr.upmc.ilp.ilp2.interfaces.ICommon;
import fr.upmc.ilp.ilp2.interfaces.IDestination;
import fr.upmc.ilp.ilp2.interfaces.ILexicalEnvironment;
import fr.upmc.ilp.ilp3esc.interfaces.IAST3escFactory;
import fr.upmc.ilp.ilp3esc.interfaces.IParser;
import fr.upmc.ilp.ilp3esc.runtime.NormalEvaluationException;
import fr.upmc.ilp.ilp3esc.runtime.WhileLastException;
import fr.upmc.ilp.ilp3esc.runtime.WhileNextException;

/** La boucle tant-que: son interprétation et sa compilation.
 * Cette variante permet les instructions locales au corps de la boucle
 * que sont next et last.
 */

public class CEASTwhileWithEscape
  extends CEASTwhile {

  public CEASTwhileWithEscape (
          final IAST2expression<CEASTparseException> condition,
          final IAST2instruction<CEASTparseException> body ) {
      super(condition, body);
  }

  /** Le constructeur analysant syntaxiquement un DOM. */

  public static IAST2while<CEASTparseException> parse (
          final Element e, final IParser<CEASTparseException> parser)
  throws CEASTparseException {
        final NodeList nl = e.getChildNodes();
        IAST2expression<CEASTparseException> condition = (IAST2expression<CEASTparseException>)
            parser.findThenParseChildAsUnique(nl, "condition");
        IAST2instruction<CEASTparseException> body = (IAST2instruction<CEASTparseException>)
            parser.findThenParseChildAsSequence(nl, "corps");
        IAST3escFactory<CEASTparseException> factory = parser.getFactory();
        return factory.newWhile(condition, body);
  }

  //NOTE: Accès direct aux champs interdit à partir d'ici!

  @Override
  public Object eval (final ILexicalEnvironment lexenv,
                      final ICommon common)
    throws EvaluationException, NormalEvaluationException {
    final fr.upmc.ilp.ilp3esc.interfaces.ILexicalEnvironment le =
        fr.upmc.ilp.ilp3esc.runtime.LexicalEnvironment.narrow(lexenv);
    final fr.upmc.ilp.ilp3esc.interfaces.ILexicalEnvironment newlexenv = 
        le.withinWhile();
    while ( true ) {
      Object bool = getCondition().eval(lexenv, common);
      if ( bool instanceof Boolean ) {
        Boolean b = (Boolean) bool;
        if ( b.booleanValue() ) {
            try {
                getBody().eval(newlexenv, common);
            } catch (WhileNextException exc) {
                continue;
            } catch (WhileLastException exc) {
                break;
            }
        } else {
            break;
        }
      }
    }
    return Boolean.FALSE;
  }

  @Override
  public void compileInstruction (final StringBuffer buffer,
                                  final ICgenLexicalEnvironment lexenv,
                                  final ICgenEnvironment common,
                                  final IDestination destination)
  throws CgenerationException {
  final fr.upmc.ilp.ilp3esc.interfaces.ICgenLexicalEnvironment le =
      fr.upmc.ilp.ilp3esc.cgen.CgenLexicalEnvironment.narrow(lexenv);
  final fr.upmc.ilp.ilp3esc.interfaces.ICgenLexicalEnvironment newlexenv = 
      le.withinWhile();
    buffer.append(" while ( ILP_isEquivalentToTrue( ");
    getCondition().compileExpression(buffer, lexenv, common);
    buffer.append(") ) { ");
    getBody().compileInstruction(
            buffer, newlexenv, common, VoidDestination.create() );
    buffer.append("}\n");
    CEASTinstruction.voidInstruction()
      .compileInstruction(buffer, lexenv, common, destination);
  }
  
  // la méthode findGlobalVariables est héritée.
}

// end of CEASTwhileWithEscape.java
