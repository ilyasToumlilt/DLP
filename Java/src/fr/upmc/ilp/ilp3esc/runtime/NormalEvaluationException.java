package fr.upmc.ilp.ilp3esc.runtime;

/** Cette classe correspond a des exceptions utilisees pour le comportement
 * normal de l'inteprete. Elle ne correspondent donc pas a des situations
 * exceptionnelles en ILP. 
 */

public abstract class NormalEvaluationException extends RuntimeException {
    
    static final long serialVersionUID = +12345678900025000L;
    
    public NormalEvaluationException () {
        super("Exception normale (sic)");
    }
    
    public NormalEvaluationException (Throwable cause) {
        super(cause);
    }

}
