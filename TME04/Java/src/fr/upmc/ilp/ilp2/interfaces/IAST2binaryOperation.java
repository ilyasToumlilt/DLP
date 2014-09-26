package fr.upmc.ilp.ilp2.interfaces;

public interface IAST2binaryOperation<Exc extends Exception>
extends IAST2operation<Exc> {

        /** renvoie l'op�rande de gauche. */
    IAST2expression<Exc> getLeftOperand ();

    /** renvoie l'op�rande de droite. */
    IAST2expression<Exc> getRightOperand ();
}
