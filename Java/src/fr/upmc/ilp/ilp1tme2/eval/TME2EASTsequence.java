/* ******************************************************************
 * ILP -- Implantation d'un langage de programmation.
 * Copyright (C) 2004-2007 <Christian.Queinnec@lip6.fr>
 *                         <Jacques.Malenfant@lip6.fr>
 * $Id$
 * GPL version>=2
 * ******************************************************************/

package fr.upmc.ilp.ilp1tme2.eval;

import java.util.List;

import fr.upmc.ilp.ilp1.eval.EAST;
import fr.upmc.ilp.ilp1.eval.EASTsequence;

public class TME2EASTsequence extends EASTsequence 
implements IASTCountingConstants {

    public TME2EASTsequence(List<EAST> instructions) {
        super(instructions);
    }

    public int compteConstantes() {
        int cpt = 0 ;
        for(int i = 0 ; i < this.getInstructionsLength() ; i++) {
            try {
                cpt = cpt +
                    ((IASTCountingConstants)this.getInstruction(i)).
                            compteConstantes() ;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return cpt ;
    }
}
