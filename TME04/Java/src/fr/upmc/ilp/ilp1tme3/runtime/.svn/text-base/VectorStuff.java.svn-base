package fr.upmc.ilp.ilp1tme3.runtime;

import java.math.BigInteger;
import java.util.Vector;

import fr.upmc.ilp.ilp1.cgen.ICgenLexicalEnvironment;
import fr.upmc.ilp.ilp1.interfaces.IASTvariable;
import fr.upmc.ilp.ilp1.runtime.AbstractInvokableImpl;
import fr.upmc.ilp.ilp1.runtime.EvaluationException;
import fr.upmc.ilp.ilp1.runtime.ILexicalEnvironment;

public class VectorStuff {
    
    public VectorStuff () {}
    
    /** étendre un environnement lexical d'interpretation pour y installer 
     * les primitives make-vector(size, filling), vector-get(vector,index)
     * et vector-length(vector). */

  public ILexicalEnvironment extendWithVectorPrimitives (
          final ILexicalEnvironment lexenv) {
      final ILexicalEnvironment lexenv1 = lexenv.extend(
              new IASTvariable() {
                  public String getName () {
                      return "makeVector";
                  }
              }, new MakeVectorPrimitive());
      final ILexicalEnvironment lexenv2 = lexenv1.extend(
              new IASTvariable() {
                  public String getName () {
                      return "vectorGet";
                  }
              }, new VectorGetPrimitive());
      final ILexicalEnvironment lexenv3 = lexenv2.extend(
              new IASTvariable() {
                  public String getName () {
                      return "vectorLength";
                  }
              }, new VectorLengthPrimitive());
      return lexenv3;
  }

  /** étendre un environnement lexical pour la compilation pour y installer 
   * les primitives liees aux vecteurs. */
  
  public static ICgenLexicalEnvironment extendWithVectorPrimitives (
          final ICgenLexicalEnvironment lexenv){
      final ICgenLexicalEnvironment lexenv1 = lexenv.extend(
              new IASTvariable () {
                  public String getName () {
                      return "makeVector";
                  }
              }, "ILP_make_vector");
      final ICgenLexicalEnvironment lexenv2 = lexenv1.extend(
              new IASTvariable () {
                  public String getName () {
                      return "vectorGet";
                  }
              }, "ILP_vector_get");
      final ICgenLexicalEnvironment lexenv3 = lexenv2.extend(
              new IASTvariable () {
                  public String getName () {
                      return "vectorLength";
                  }
              }, "ILP_vector_length");
      return lexenv3;
  }
  
  /** Cette classe implante la fonction make-vector(size, filling) 
   * NOTA: utiliser Vector pour l'implantation est quelque peu dispendieux
   * et trop puissant notamment si l'on pouvait ecrire dedans. */
  protected class MakeVectorPrimitive extends AbstractInvokableImpl {
      public MakeVectorPrimitive () {}
      // La fonction make-vector est 2-aire:
      @Override
      public Object invoke (Object size, Object filling) 
      throws EvaluationException{
          if ( size instanceof BigInteger ) {
              int length = ((BigInteger) size).intValue();
              Vector<Object> vector = new Vector<>();
              vector.setSize(length);
              for ( int i=0 ; i<length ; i++ ) {
                  vector.set(i, filling);
              }
              return vector;
          } else {
              throw new EvaluationException("Incorrect length!");
          }
      }
  }
  
  /** Cette classe implante la fonction vector-get(vector, index) */
  protected class VectorGetPrimitive extends AbstractInvokableImpl {
      public VectorGetPrimitive () {}
      // La fonction vector-get est 2-aire:
      @Override
      public Object invoke (Object vector, Object index) 
      throws EvaluationException{
          if (vector instanceof Vector<?>
           && index instanceof BigInteger ) {
              return ((Vector<?>) vector)
                      .elementAt(((BigInteger)index).intValue());
          } else {
              throw new EvaluationException("Incorrect argument(s)!");
          }
      }
  }
  
  /** Cette classe implante la fonction vector-length(vector) */
  protected class VectorLengthPrimitive extends AbstractInvokableImpl {
      public VectorLengthPrimitive () {}
      // La fonction vector-length est 1-aire:
      @Override
      public Object invoke (Object vector) throws EvaluationException{
          if (vector instanceof Vector<?>) {
              return ((Vector<?>)vector).size();
          } else {
              throw new EvaluationException("Argument must be a vector");
          }
      }
  }
}
