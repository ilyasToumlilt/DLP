/* ******************************************************************
 * ILP -- Implantation d'un langage de programmation.
 * Copyright (C) 2004 <Christian.Queinnec@lip6.fr>
 * $Id: CEASTParser.java 735 2008-09-26 16:38:19Z queinnec $
 * GPL version>=2
 * ******************************************************************/

package fr.upmc.ilp.ilp3esc.ast;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

import fr.upmc.ilp.ilp2.ast.CEASTparseException;
import fr.upmc.ilp.ilp2.interfaces.IAST2;
import fr.upmc.ilp.ilp3esc.interfaces.IAST3escFactory;
import fr.upmc.ilp.ilp3esc.interfaces.IParser;

/** Analyseur syntaxique pour le langage ILP2esc.
 * En fait, il sait presque lire du ILP2escl c'est-a-dire traiter les
 * etiquettes sur les nouvelles instructions qu'apporte ILP2esc.
 * */

public class CEASTParser extends fr.upmc.ilp.ilp3.CEASTParser
implements IParser<CEASTparseException> {

    public CEASTParser (IAST3escFactory<CEASTparseException> factory) {
        super(factory);
    }

    @Override
    public IAST3escFactory<CEASTparseException> getFactory () {
        return (IAST3escFactory<CEASTparseException>) super.getFactory();
    }
    
    @Override
    public IAST2<CEASTparseException> parse (final Node n)
    throws CEASTparseException {
    switch ( n.getNodeType() ) {

    case Node.ELEMENT_NODE: {
      final Element e = (Element) n;
      final String name = e.getTagName();

      if ( "suivant".equals(name) ) {
          return CEASTnext.parse(e, this);
      } else if ( "dernier".equals(name) ) {
          return CEASTlast.parse(e, this);
      } else if ( "boucleAvecEchappement".equals(name) ) {
          return CEASTwhileWithEscape.parse(e, this);

      } else {
        return super.parse(n);
      }
    }

    default: {
      return super.parse(n);
    }
    }
  }
}

// end of CEASTParser.java
