/* ******************************************************************
 * ILP -- Implantation d'un langage de programmation.
 * Copyright (C) 2004 <Christian.Queinnec@lip6.fr>
 * $Id: CEASTwhileWithEscape.java 1005 2010-10-20 07:22:16Z queinnec $
 * GPL version>=2
 * ******************************************************************/

package fr.upmc.ilp.ilp3escl;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import fr.upmc.ilp.ilp1.cgen.CgenerationException;
import fr.upmc.ilp.ilp1.runtime.EvaluationException;
import fr.upmc.ilp.ilp2.ast.CEASTinstruction;
import fr.upmc.ilp.ilp2.ast.CEASTparseException;
import fr.upmc.ilp.ilp2.cgen.VoidDestination;
import fr.upmc.ilp.ilp2.interfaces.IAST2expression;
import fr.upmc.ilp.ilp2.interfaces.IAST2instruction;
import fr.upmc.ilp.ilp2.interfaces.ICgenEnvironment;
import fr.upmc.ilp.ilp2.interfaces.ICgenLexicalEnvironment;
import fr.upmc.ilp.ilp2.interfaces.ICommon;
import fr.upmc.ilp.ilp2.interfaces.IDestination;
import fr.upmc.ilp.ilp2.interfaces.ILexicalEnvironment;
import fr.upmc.ilp.ilp2.interfaces.IParser;
import fr.upmc.ilp.ilp3esc.runtime.NormalEvaluationException;

/** La boucle tant-que etiquettee: son interpr�tation et sa compilation.
 * Cette variante permet les instructions locales au corps de la boucle
 * que sont next et last. Toutes ces instructions etant etiquettees,
 * on peut controler les iterations de multiples boucles imbriquees.
 */

public class CEASTwhileWithEscape
extends fr.upmc.ilp.ilp3esc.ast.CEASTwhileWithEscape {

  public CEASTwhileWithEscape (
          final IAST2expression<CEASTparseException> condition,
          final IAST2instruction<CEASTparseException> body,
          final String label )
  {
      super(condition, body);
      this.label = label;
  }
  private final String label;

  public String getLabel () {
      return this.label;
  }

  /** Le constructeur analysant syntaxiquement un DOM. */

  public static CEASTwhileWithEscape parse (
          final Element e, final IParser<CEASTparseException> parser)
  throws CEASTparseException {
      final NodeList nl = e.getChildNodes();
      IAST2expression<CEASTparseException> condition = (IAST2expression<CEASTparseException>)
          parser.findThenParseChildAsUnique(nl, "condition");
      IAST2instruction<CEASTparseException> body = (IAST2instruction<CEASTparseException>)
          parser.findThenParseChildAsSequence(nl, "corps");
      String label = e.getAttribute("label");
      CEASTFactory factory = (CEASTFactory) parser.getFactory();
      return factory.newWhile(condition, body, label);
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
                if (getLabel().equals(exc.getLabel())) {
                    continue;
                } else {
                    throw exc;
                }
            } catch (WhileLastException exc) {
                if (getLabel().equals(exc.getLabel())) {
                    break;
                } else {
                    throw exc;
                }
            }
        } else {
            break;
        }
      }
    }
    return Boolean.FALSE;
  }

/** Le probleme de C est que break et continue ne peuvent que sortir de
 * la boucle immediatement englobante. En revanche goto permet d'aller
 * ou l'on souhaite. On suffixe donc les boucles d'un postambule
 * permettant de sortir ou de continuer.  */

    @Override
    public void compileInstruction(
            StringBuffer buffer,
            ICgenLexicalEnvironment lexenv,
            ICgenEnvironment common,
            IDestination destination)
    throws CgenerationException {
        final fr.upmc.ilp.ilp3esc.interfaces.ICgenLexicalEnvironment le =
            fr.upmc.ilp.ilp3esc.cgen.CgenLexicalEnvironment.narrow(lexenv);
        if ( le.isWithinWhile(getLabel()) ) {
            final String msg = "Etiquette de boucle ambigue: " + getLabel();
            throw new CgenerationException(msg);
        }
        final fr.upmc.ilp.ilp3esc.interfaces.ICgenLexicalEnvironment newlexenv = 
            le.withinWhile(getLabel());
        buffer.append("while ( 1 ) {\n");
        buffer.append("  if ( ILP_isEquivalentToTrue( ");
        getCondition().compileExpression(buffer, lexenv, common);
        buffer.append(") ) { ");
        getBody().compileInstruction(
                buffer, newlexenv, common, VoidDestination.create() );
        buffer.append("  } else {\n");
        // et pourquoi ne pas montrer que ceci aussi est possible!
        buffer.append("     ILP_last_" + getLabel() + ": break;\n");
        buffer.append("  }\n");
        buffer.append("  ILP_next_" + getLabel() + ": continue;\n");
        buffer.append("}\n");
        CEASTinstruction.voidInstruction()
            .compileInstruction(buffer, lexenv, common, destination);
    }

  // la methode findGlobalVariables est heritee.

}

// end of CEASTwhileWithEscape.java
