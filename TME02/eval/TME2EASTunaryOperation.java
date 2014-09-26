/* ******************************************************************
 * ILP -- Implantation d'un langage de programmation.
 * Copyright (C) 2004-2007 <Christian.Queinnec@lip6.fr>
 *                         <Jacques.Malenfant@lip6.fr>
 * $Id$
 * GPL version>=2
 * ******************************************************************/

package fr.upmc.ilp.ilp1tme2.eval;

import fr.upmc.ilp.ilp1.eval.EAST;
import fr.upmc.ilp.ilp1.eval.EASTunaryOperation;

public class TME2EASTunaryOperation extends EASTunaryOperation implements
        IASTCountingConstants {

    public TME2EASTunaryOperation(String operateur, EAST operand) {
        super(operateur, operand);
    }

    public int compteConstantes() {
        return ((IASTCountingConstants)this.getOperand()).compteConstantes() ;
    }
}
