/* ******************************************************************
 * ILP -- Implantation d'un langage de programmation.
 * Copyright (C) 2004-2007 <Christian.Queinnec@lip6.fr>
 *                         <Jacques.Malenfant@lip6.fr>
 * $Id$
 * GPL version>=2
 * ******************************************************************/

package fr.upmc.ilp.ilp1tme2.eval;

import fr.upmc.ilp.ilp1.eval.EAST;
import fr.upmc.ilp.ilp1.eval.EASTbinaryOperation;

public class TME2EASTbinaryOperation extends EASTbinaryOperation 
implements IASTCountingConstants {

    public TME2EASTbinaryOperation(String operateur, 
                                   EAST operandeGauche, 
                                   EAST operandeDroit) {
        super(operateur, operandeGauche, operandeDroit);
    }

    public int compteConstantes() {
        return
            ((IASTCountingConstants)this.getLeftOperand()).compteConstantes()
            + ((IASTCountingConstants)this.getRightOperand()).compteConstantes();
    }
}
