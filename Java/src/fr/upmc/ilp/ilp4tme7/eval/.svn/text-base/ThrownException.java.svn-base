package fr.upmc.ilp.ilp4tme7.eval;

public class ThrownException extends VisitorEvaluationException {

    private static final long serialVersionUID = 200711241629L;

    public ThrownException(final Object value) {
        super("Thrown value");
        this.value = value;
    }
    private final Object value;

    public Object getThrownValue () {
        return value;
    }

    @Override
    public String toString () {
      return "Thrown value: " + value;
    }
}
