package fr.upmc.ilp.ilp1tme1.test;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.junit.Before;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import fr.upmc.ilp.ilp1.fromxml.AST;
import fr.upmc.ilp.ilp1.fromxml.ASTException;
import fr.upmc.ilp.ilp1.fromxml.test.ASTParserTest;
import fr.upmc.ilp.ilp1.interfaces.IAST;
import fr.upmc.ilp.ilp1tme1.ASTsequence;
import fr.upmc.ilp.ilp1tme1.IASTsequence;

public class ASTsequenceTest extends ASTParserTest {

        /* NOTA: On recopie mon ASTParser afin de n'avoir, pour l'exercice, 
         * qu'une unique dependance sur l'ASTsequence des etudiants. */
    
    static class ASTParser extends fr.upmc.ilp.ilp1.fromxml.ASTParser {

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

    /* Utilitaire pour convertir une chaine XML en AST. */
    public static AST toAST(ASTParser parser, String xml)
    throws ASTException {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            StringReader sr = new StringReader(xml);
            InputSource is = new InputSource(sr);
            Document d = db.parse(is);
            AST ast = parser.parse(d);
            return ast;
        } catch (ASTException exc) {
            throw exc;
        } catch (Throwable cause) {
            throw new ASTException(cause);
        }
    }
    
    @Override
    @Before
    public void setUp () {
        this.parser = new ASTParser();
    }
    private ASTParser parser;
    
    /* Test sur une sequence de 2 termes */
    @Override
    public void testSequence () throws Exception {
        String program = "<sequence>"
              + "<booleen valeur='true'/>"
              + "<entier valeur='1'/>"
              + "</sequence>";
        AST a = toAST(parser, program);
        assertEquals(program, a.toXML());
        assertTrue(a instanceof IASTsequence);
        IASTsequence iast = (IASTsequence) a;
        IAST[] iastlist = iast.getInstructions();
        assertNotNull(iastlist);
        assertTrue(iastlist.length == 2);
        IAST e1 = iastlist[0];
        assertXMLEqual("<booleen valeur='true'/>", ((AST)e1).toXML());
        IAST e2 = iastlist[1];
        assertXMLEqual("<entier valeur='1'/>", ((AST)e2).toXML());
        // Les tests supplementaires:
        IAST[] iastbl = iast.getAllButLastInstructions();
        assertEquals(1, iastbl.length);
        assertXMLEqual("<booleen valeur='true'/>", ((AST)iastbl[0]).toXML());
    }

    /* Test sur une sequence de 3 termes pour verifier que les termes 
     * restant sont bien dans le bon ordre. */
    public void testSequenceThree () throws Exception {
        String program = "<sequence>"
              + "<booleen valeur='true'/>"
              + "<entier valeur='1'/>"
              + "<entier valeur='11'/>"
              + "</sequence>";
        AST a = toAST(parser, program);
        assertEquals(program, a.toXML());
        assertTrue(a instanceof IASTsequence);
        IASTsequence iast = (IASTsequence) a;
        IAST[] iastlist = iast.getInstructions();
        assertNotNull(iastlist);
        assertTrue(iastlist.length == 3);
        IAST e1 = iastlist[0];
        assertXMLEqual("<booleen valeur='true'/>", ((AST)e1).toXML());
        IAST e2 = iastlist[1];
        assertXMLEqual("<entier valeur='1'/>", ((AST)e2).toXML());
        // Les tests supplementaires:
        IAST[] iastbl = iast.getAllButLastInstructions();
        assertEquals(2, iastbl.length);
        assertXMLEqual("<booleen valeur='true'/>", ((AST)iastbl[0]).toXML());
        assertXMLEqual("<entier valeur='1'/>", ((AST)iastbl[1]).toXML());
    }
    
    /* Test sur une sequence d'un terme seulement */
    @Override
    public void testSequenceOne () throws Exception {
        String program = "<sequence>"
            + "<entier valeur='1'/>"
            + "</sequence>";
        AST a = toAST(parser, program);
        assertEquals(program, a.toXML());
        assertTrue(a instanceof fr.upmc.ilp.ilp1.fromxml.ASTsequence);
        IASTsequence iast = (IASTsequence) a;
        IAST[] iastlist = iast.getInstructions();
        assertNotNull(iastlist);
        assertTrue(iastlist.length == 1);
        IAST e1 = iastlist[0];
        assertXMLEqual("<entier valeur='1'/>", ((AST)e1).toXML());
        // Les tests supplementaires:
        IAST[] iastbl = iast.getAllButLastInstructions();
        assertEquals(0, iastbl.length);
    }

    /* Test sur une sequence vide: exception attendue! */
    //@Override
    public void no_testSequenceEmpty () throws ASTException {
        String program = "<sequence>"
            + "</sequence>";
        AST a = toAST(parser, program);
        assertEquals(program, a.toXML());
        assertTrue(a instanceof fr.upmc.ilp.ilp1.fromxml.ASTsequence);
        IASTsequence iast = (IASTsequence) a;
        IAST[] iastlist = iast.getInstructions();
        assertNotNull(iastlist);
        assertTrue(iastlist.length == 0);
        // Les tests supplementaires:
        try {
            iast.getAllButLastInstructions();
            fail("Pas de getAllButLastInstructions(sequence vide)!");
        } catch (ASTException e) {
            // OK
        }
    }
}
