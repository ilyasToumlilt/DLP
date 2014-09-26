/* ******************************************************************
 * ILP -- Implantation d'un langage de programmation.
 * Copyright (C) 2004-2007 <Christian.Queinnec@lip6.fr>
 *                         <Jacques.Malenfant@lip6.fr>
 * $Id$
 * GPL version>=2
 * ******************************************************************/

package fr.upmc.ilp.ilp1tme2.eval;

import fr.upmc.ilp.ilp1.eval.EAST;
import fr.upmc.ilp.ilp1.eval.EASTalternative;

public class TME2EASTalternative extends EASTalternative
implements IASTCountingConstants {

    public TME2EASTalternative(EAST condition, EAST consequence, EAST alternant) {
        super(condition, consequence, alternant);
    }

    public TME2EASTalternative(EAST condition, EAST consequence) {
        super(condition, consequence);
    }

    public int compteConstantes() {
        int nbConstantesAlternant = 0 ;
        if (this.isTernary()) {
            nbConstantesAlternant =
                    ((IASTCountingConstants)this.getAlternant()).
                    compteConstantes();
        }
        return ((IASTCountingConstants)this.getCondition()).compteConstantes()
                + ((IASTCountingConstants)this.getConsequent()).compteConstantes()
                + nbConstantesAlternant;
    }
}
