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
import fr.upmc.ilp.ilp1.eval.EASTinvocationPrimitive;

public class TME2EASTinvocationPrimitive extends EASTinvocationPrimitive
implements IASTCountingConstants {

    public TME2EASTinvocationPrimitive(String fonction, List<EAST> arguments) {
        super(fonction, arguments);
    }

    public int compteConstantes() {
        int cpt = 0 ;
        for (int i = 0 ; i < this.getArgumentsLength() ; i++) {
            cpt = cpt +
                ((IASTCountingConstants)this.getArgument(i)).compteConstantes() ;
        }
        return cpt ;

    }

}
