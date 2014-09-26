package fr.upmc.ilp.ilp2.interfaces;

public interface IAST2operation<Exc extends Exception>
extends IAST2expression<Exc> {

    /** Renvoie le nom de l'op�rateur concern� par l'op�ration. */
    String getOperatorName ();

    /** Renvoie l'arit� de l'op�rateur concern� par l'op�ration. L'arit�
     * est toujours de 1 pour IAST2unaryOperation et de 2 pour une
     * IAST2binaryOperation. */
    int getArity ();

    /** Renvoie les op�randes d'une op�ration. */
    IAST2expression<Exc>[] getOperands ();
}
