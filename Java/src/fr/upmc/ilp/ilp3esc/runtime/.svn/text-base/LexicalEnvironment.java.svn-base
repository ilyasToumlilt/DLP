package fr.upmc.ilp.ilp3esc.runtime;

import fr.upmc.ilp.ilp2.interfaces.IAST2variable;
import fr.upmc.ilp.ilp2.runtime.BasicEnvironment;
import fr.upmc.ilp.ilp3esc.interfaces.ILexicalEnvironment;

public abstract class LexicalEnvironment 
extends BasicEnvironment<IAST2variable>
implements ILexicalEnvironment {

    protected LexicalEnvironment (final IAST2variable variable,
                                  final ILexicalEnvironment next) {
        super(variable, next);
    }

    /** Un utilitaire pour vérifier qu'on a bien affaire à un environnement
     * d'interprétation connaissant les boucles. */
    public static ILexicalEnvironment narrow (
            fr.upmc.ilp.ilp2.interfaces.ILexicalEnvironment o) {
        if ( o instanceof ILexicalEnvironment ) {
            return (ILexicalEnvironment) o;
        } else {
            final String msg = 
                "Not a fr.upmc.ilp.ilp3esc.interfaces.ILexicalEnvironment: "
                + o;
            throw new ClassCastException(msg);
        }
    }
    
    @Override
    public ILexicalEnvironment getNext () {
        return (ILexicalEnvironment) super.getNext();
    }
    
    @Override
    public ILexicalEnvironment shrink (final IAST2variable v) {
        return (ILexicalEnvironment) super.shrink(v);
    }

    /** On peut etendre tout environnement. */
    public ILexicalEnvironment withinWhile() {
        return new WhileLexicalEnvironment(this);
    }
    public ILexicalEnvironment withinWhile(String label) {
        return new WhileLexicalEnvironment(this, label);
    }
    
    /** On peut etendre tout environnement. */
    public ILexicalEnvironment extend (final IAST2variable variable,
                                       final Object value) {
      return new VariableLexicalEnvironment(variable, value, this);
    }
    /** ===========================================================
     * Comme il y a une dependance entre  EmptyLexicalEnvironment
     * et LexicalEnvironment, on lie leurs definitions en un unique fichier.
     * */

    public static class EmptyLexicalEnvironment
    extends fr.upmc.ilp.ilp2.runtime.LexicalEnvironment.EmptyLexicalEnvironment
    implements ILexicalEnvironment {

      // La technique du singleton:
      protected EmptyLexicalEnvironment () {}
      private static final EmptyLexicalEnvironment THE_EMPTY_LEXICAL_ENVIRONMENT;
      static {
        THE_EMPTY_LEXICAL_ENVIRONMENT = new EmptyLexicalEnvironment();
      }

      public static EmptyLexicalEnvironment create () {
        return EmptyLexicalEnvironment.THE_EMPTY_LEXICAL_ENVIRONMENT;
      }

      /** On peut etendre l'environnement vide.
       *
       * Malheureusement, cela cree une dependance avec la classe des
       * environnements non vides d'ou l'inclusion de cette classe dans
       * celle des environnements non vides.
       */
      @Override
      public ILexicalEnvironment extend (final IAST2variable variable,
                                         final Object value) {
          return new VariableLexicalEnvironment(variable, value, this);
      }

      public boolean isWithinWhile() {
          return false;
      }
      public boolean isWithinWhile(String label) {
          return false;
      }
      
      public ILexicalEnvironment withinWhile() {
          return new WhileLexicalEnvironment(this);
      }
      public ILexicalEnvironment withinWhile(String label) {
          return new WhileLexicalEnvironment(this, label);
      }
    }
}
