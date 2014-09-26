/* ******************************************************************
 * ILP -- Implantation d'un langage de programmation.
 * Copyright (C) 2004-2005 <Christian.Queinnec@lip6.fr>
 *                         <Jacques.Malenfant@lip6.fr>
 * $Id$
 * GPL version>=2
 * ******************************************************************/

package fr.upmc.ilp.ilp1tme2.eval;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

import fr.upmc.ilp.ilp1.eval.EAST;
import fr.upmc.ilp.ilp1.eval.EASTException;
import fr.upmc.ilp.ilp1.eval.EASTParser;
import fr.upmc.ilp.ilp1.eval.IEASTFactory;

public class TME2EASTParser extends EASTParser {

    public TME2EASTParser(IEASTFactory<EASTException> factory) {
        super(factory);
        this.nbConstantes = 0 ;
    }
    private int nbConstantes ;

    public int getNbConstantes() {
        return nbConstantes;
    }

    /** Analyseur de DOM en AST. Les noeuds de l'AST sont créés par la
     * fabrique spécifiée à la construction de l'EASTParser. Même les
     * exceptions sont signalées par cette même fabrique.
     */
    @Override
    public EAST parse (final Node n)
    throws EASTException {
        try {
            switch ( n.getNodeType() ) {
            case Node.DOCUMENT_NODE: {
                return super.parse(n) ;
            }
            case Node.ELEMENT_NODE: {
                final Element e = (Element) n;
                final String name = e.getTagName();

                if ( "entier".equals(name) ||
                     "flottant".equals(name) ||
                     "chaine".equals(name) ||
                     "booleen".equals(name) ) {
                    this.nbConstantes++ ;
                    return super.parse(n);
                } else {
                    return super.parse(n);
                }
            }
            default: {
                return super.parse(n);
            }
            }
        } catch (final EASTException e) {
            throw e;
        }
    }

}
