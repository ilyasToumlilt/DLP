package fr.upmc.ilp.ilp4tme7.eval;

public class VisitorEvaluationException
extends RuntimeException {

    private static final long serialVersionUID = 200711241501L;

    public VisitorEvaluationException(Exception exc) {
        super(exc);
    }
    public VisitorEvaluationException(String msg) {
        super(msg);
    }
}
