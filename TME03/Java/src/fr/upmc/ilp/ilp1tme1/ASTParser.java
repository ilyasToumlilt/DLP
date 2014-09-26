package fr.upmc.ilp.ilp1tme1;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import fr.upmc.ilp.ilp1.fromxml.AST;
import fr.upmc.ilp.ilp1.fromxml.ASTException;

public class ASTParser extends fr.upmc.ilp.ilp1.fromxml.ASTParser {

      /** Analyser le DOM pour convertir les sequences et laisser l'analyseur
       * dont on herite traiter tout le reste. */
      
    @Override
    public AST parse (Node n) throws ASTException {
        switch ( n.getNodeType() ) {

        case Node.ELEMENT_NODE: {
          Element e = (Element) n;
          NodeList nl = e.getChildNodes();
          String name = e.getTagName();

          if ( "sequence".equals(name) ) {
              return new ASTsequence(this.parseList(nl));
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
