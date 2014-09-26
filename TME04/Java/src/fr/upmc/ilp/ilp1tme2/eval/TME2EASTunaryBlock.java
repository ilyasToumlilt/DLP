/* ******************************************************************
 * ILP -- Implantation d'un langage de programmation.
 * Copyright (C) 2004-2007 <Christian.Queinnec@lip6.fr>
 *                         <Jacques.Malenfant@lip6.fr>
 * $Id$
 * GPL version>=2
 * ******************************************************************/

package fr.upmc.ilp.ilp1tme2.eval;

import fr.upmc.ilp.ilp1.eval.EAST;
import fr.upmc.ilp.ilp1.eval.EASTsequence;
import fr.upmc.ilp.ilp1.eval.EASTunaryBlock;
import fr.upmc.ilp.ilp1.eval.EASTvariable;

public class TME2EASTunaryBlock extends EASTunaryBlock implements
        IASTCountingConstants {

    public TME2EASTunaryBlock(EASTvariable variable, 
                              EAST initialization, 
                              EASTsequence body) {
        super(variable, initialization, body);
    }

    public int compteConstantes() {
        return
            ((IASTCountingConstants)this.getInitialization()).compteConstantes()
            + ((IASTCountingConstants)this.getBody()).compteConstantes() ;
    }
}
