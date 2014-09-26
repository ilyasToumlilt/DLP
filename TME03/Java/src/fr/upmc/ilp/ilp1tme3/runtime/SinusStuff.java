/* ******************************************************************
 * ILP -- Implantation d'un langage de programmation.
 * Copyright (C) 2004 <Christian.Queinnec@lip6.fr>
 * $Id:PrintStuff.java 405 2006-09-13 17:21:53Z queinnec $
 * GPL version>=2
 * ******************************************************************/

package fr.upmc.ilp.ilp1tme3.runtime;

import java.math.BigInteger;

import fr.upmc.ilp.ilp1.cgen.ICgenLexicalEnvironment;
import fr.upmc.ilp.ilp1.interfaces.IASTvariable;
import fr.upmc.ilp.ilp1.runtime.AbstractInvokableImpl;
import fr.upmc.ilp.ilp1.runtime.EvaluationException;
import fr.upmc.ilp.ilp1.runtime.ILexicalEnvironment;

/** les primitives pour imprimer à savoir print et newline. En fait,
 * newline pourrait se programmer à partir de print et de la chaîne
 * contenant une fin de ligne mais comme nous n'avons pas encore de
 * fonctions, elle est utile.
 */

public class SinusStuff {
      
    public SinusStuff () {}

    /** étendre un environnement lexical pour l'evaluation pour y installer 
     * la primitive sin(x). */
    
    public ILexicalEnvironment extendWithSinusPrimitive (
            final ILexicalEnvironment lexenv) {
        final ILexicalEnvironment lexenv1 = lexenv.extend(
                new IASTvariable() {
                    public String getName () {
                        return "sinus";
                    }
                }, new SinusPrimitive());
        return lexenv1;
    }

    /** étendre un environnement lexical pour la compilation pour y installer 
     * la primitive sin(x). */
    
    public static ICgenLexicalEnvironment extendWithSinusPrimitive (
            final ICgenLexicalEnvironment lexenv){
        final ICgenLexicalEnvironment lexenv1 = lexenv.extend(
                new IASTvariable () {
                    public String getName () {
                        return "sinus";
                    }
                }, "ILP_sinus");
        return lexenv1;
    }
    
    /** Cette classe implante la fonction sin(x) pour l'interprete */
    
    protected class SinusPrimitive extends AbstractInvokableImpl {
        public SinusPrimitive () {}"
        // La fonction sin() est unaire:
        @Override
        public Object invoke (Object value) throws EvaluationException{
            if (value instanceof Double)
                return Math.sin((Double) value);
            else if (value instanceof BigInteger)
                return Math.sin(((BigInteger) value).doubleValue());
            else throw new EvaluationException("Invalid argument: number expected");
        }
    }
}

// end of Trigo.java
