package fr.upmc.ilp.ilp3esc.cgen;

import fr.upmc.ilp.ilp2.interfaces.IAST2variable;
import fr.upmc.ilp.ilp2.runtime.BasicEnvironment;
import fr.upmc.ilp.ilp3esc.interfaces.ICgenLexicalEnvironment;

public abstract class CgenLexicalEnvironment 
extends BasicEnvironment<IAST2variable>
implements ICgenLexicalEnvironment {

    protected CgenLexicalEnvironment (final ICgenLexicalEnvironment next) {
        super(null, next);
    }
    protected CgenLexicalEnvironment (final IAST2variable variable,
                                      final ICgenLexicalEnvironment next) {
        super(variable, next);
    }
    
    /** Un utilitaire pour vérifier qu'on a bien affaire à un environnement
     * d'interprétation connaissant les boucles. */
    public static ICgenLexicalEnvironment narrow (
            fr.upmc.ilp.ilp2.interfaces.ICgenLexicalEnvironment o) {
        if ( o instanceof ICgenLexicalEnvironment ) {
            return (ICgenLexicalEnvironment) o;
        } else {
            final String msg = 
                "Not a fr.upmc.ilp.ilp3esc.interfaces.ICgenLexicalEnvironment: "
                + o;
            throw new ClassCastException(msg);
        }
    }
    
    @Override
    public ICgenLexicalEnvironment getNext () {
        return (ICgenLexicalEnvironment) super.getNext();
    }
    
    @Override
    public ICgenLexicalEnvironment shrink (final IAST2variable v) {
        return (ICgenLexicalEnvironment) super.shrink(v);
    }

    /** On peut etendre tout environnement. */
    public ICgenLexicalEnvironment withinWhile() {
        return new WhileCgenLexicalEnvironment(this);
    }
    public ICgenLexicalEnvironment withinWhile(String label) {
        return new WhileCgenLexicalEnvironment(this, label);
    }
    
    /** On peut etendre tout environnement. */
    public ICgenLexicalEnvironment extend (final IAST2variable variable) {
      return new VariableCgenLexicalEnvironment(
              variable, variable.getMangledName(), this);
    }
    public ICgenLexicalEnvironment extend (final IAST2variable variable,
                                           final String compiledName) {
        return new VariableCgenLexicalEnvironment(variable, compiledName, this);
    }

    /** ===================================================== */
    public static class CgenEmptyLexicalEnvironment
    extends fr.upmc.ilp.ilp2.cgen.CgenLexicalEnvironment.CgenEmptyLexicalEnvironment
    implements ICgenLexicalEnvironment {

      // La technique du singleton:
      protected CgenEmptyLexicalEnvironment () {}
      private static final CgenEmptyLexicalEnvironment
        THE_EMPTY_LEXICAL_ENVIRONMENT;
      static {
        THE_EMPTY_LEXICAL_ENVIRONMENT = new CgenEmptyLexicalEnvironment();
      }

      public static ICgenLexicalEnvironment create () {
        return CgenEmptyLexicalEnvironment.THE_EMPTY_LEXICAL_ENVIRONMENT;
      }

      @Override
      public ICgenLexicalEnvironment extend (final IAST2variable variable) {
        return new VariableCgenLexicalEnvironment(variable, variable.getName(), this);
      }

      public boolean isWithinWhile() {
          return false;
      }
      public boolean isWithinWhile(String label) {
          return false;
      }

      public ICgenLexicalEnvironment withinWhile() {
          return new WhileCgenLexicalEnvironment(this);
      }
      public ICgenLexicalEnvironment withinWhile(String label) {
          return new WhileCgenLexicalEnvironment(this, label);
      }
    }
}
