package fr.upmc.ilp.ilp2.interfaces;

/** Cette interface d�crit une r�f�rence en lecture � une variable
 * car on distingue maintenant lecture et r�f�rence � une variable. */

public interface IAST2reference<Exc extends Exception>
extends IAST2expression<Exc> {

    /** Retourne la variable lue */
    IAST2variable getVariable();
}
