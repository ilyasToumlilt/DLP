/* ******************************************************************
 * ILP -- Implantation d'un langage de programmation.
 * Copyright (C) 2004-2007 <Christian.Queinnec@lip6.fr>
 *                         <Jacques.Malenfant@lip6.fr>
 * $Id$
 * GPL version>=2
 * ******************************************************************/

package fr.upmc.ilp.ilp1tme2.eval;

import fr.upmc.ilp.ilp1.eval.EASTboolean;
import fr.upmc.ilp.ilp1tme2.eval.IASTCountingConstants;

public class TME2EASTboolean extends EASTboolean implements IASTCountingConstants {

    public TME2EASTboolean(String valeur) {
        super(valeur);
    }

    public int compteConstantes() {
        return 1 ;
    }

}
