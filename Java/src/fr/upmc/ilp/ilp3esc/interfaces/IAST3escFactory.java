package fr.upmc.ilp.ilp3esc.interfaces;

import fr.upmc.ilp.ilp3.IAST3Factory;
import fr.upmc.ilp.ilp3esc.ast.CEASTlast;
import fr.upmc.ilp.ilp3esc.ast.CEASTnext;

public interface IAST3escFactory <Exc extends Exception>
extends IAST3Factory<Exc> {
    
    CEASTnext newNext ();

    CEASTlast newLast ();
}
