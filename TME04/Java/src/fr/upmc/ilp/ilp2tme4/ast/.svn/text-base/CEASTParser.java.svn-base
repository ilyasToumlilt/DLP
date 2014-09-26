package fr.upmc.ilp.ilp2tme4.ast;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import fr.upmc.ilp.ilp2.ast.CEASTparseException;
import fr.upmc.ilp.ilp2.interfaces.IAST2;
import fr.upmc.ilp.ilp2tme4.ast.CEASTaMoinsQue;

public class CEASTParser extends fr.upmc.ilp.ilp2.ast.CEASTParser {

    public CEASTParser(CEASTFactory factory) {
        super(factory);
    }

    @Override
    public IAST2<CEASTparseException> parse(Node n) throws CEASTparseException {
        if (n.getNodeType() == Node.ELEMENT_NODE) {
            Element e = (Element) n;
            String name = e.getTagName();
            if ("aMoinsQue".equals(name)) {
                return CEASTaMoinsQue.parse(e, this);
            } else {
                return super.parse(n);
            }
        } else {
            return super.parse(n);
        }
    }

}
