package fr.upmc.ilp.ilp1tme2.eval;

import java.util.List;

import fr.upmc.ilp.ilp1.eval.EAST;
import fr.upmc.ilp.ilp1.eval.EASTprogram;
import fr.upmc.ilp.ilp1.interfaces.IASTsequence;

public class TME2EASTprogram extends EASTprogram 
implements IASTCountingConstants {
    
    public TME2EASTprogram(List<EAST> body) {
        super(body);
        this.body1 = new TME2EASTsequence(body);
    }
    protected TME2EASTsequence body1;
    
    @Override
    public IASTsequence getBody() {
        return body1;
    }
    
    public int compteConstantes() {
        return ((TME2EASTsequence)getBody()).compteConstantes();
    }
}
