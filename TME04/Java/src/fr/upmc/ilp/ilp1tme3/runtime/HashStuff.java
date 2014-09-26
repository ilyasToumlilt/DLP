/* ******************************************************************
 * ILP -- Implantation d'un langage de programmation.
 * Copyright (C) 2004 <Christian.Queinnec@lip6.fr>
 * $Id:PrintStuff.java 405 2006-09-13 17:21:53Z queinnec $
 * GPL version>=2
 * ******************************************************************/

package fr.upmc.ilp.ilp1tme3.runtime;

import java.util.Hashtable;

import fr.upmc.ilp.ilp1.interfaces.IASTvariable;
import fr.upmc.ilp.ilp1.runtime.AbstractInvokableImpl;
import fr.upmc.ilp.ilp1.runtime.EvaluationException;
import fr.upmc.ilp.ilp1.runtime.ILexicalEnvironment;

public class HashStuff {
      
    public HashStuff (){}

    /** étendre un environnement lexical pour y installer la primitive
      sin(x). */
    
    public ILexicalEnvironment extendWithHashPrimitives (
            final ILexicalEnvironment lexenv) {
        final ILexicalEnvironment lexenv1 = lexenv.extend(
                new IASTvariable() {
                    public String getName () {
                        return "makehash";
                    }
                }, new MakeHashPrimitive());
        final ILexicalEnvironment lexenv2 = lexenv1.extend(
                new IASTvariable() {
                    public String getName () {
                        return "hashget";
                    }
                }, new HashGetPrimitive());
        final ILexicalEnvironment lexenv3 = lexenv2.extend(
                new IASTvariable() {
                    public String getName () {
                        return "hashput";
                    }
                }, new HashPutPrimitive());
        return lexenv3;
    }
    
    /** Cette classe implante la fonction makehash() */
    protected class MakeHashPrimitive extends AbstractInvokableImpl {
        public MakeHashPrimitive () {}
        // La fonction makehash est 0-aire:
        @Override
        public Object invoke (){
            return new Hashtable<>();
        }
    }
    
    /** Cette classe implante la fonction hashget(h, k) */
    protected class HashGetPrimitive extends AbstractInvokableImpl {
        public HashGetPrimitive () {}
        // La fonction hashget est 2-aire:
        @Override
        public Object invoke (Object hashtable, Object key) throws EvaluationException{
            if (hashtable instanceof Hashtable<?, ?>)
                try{
                    return (((Hashtable<?, ?>) hashtable).get(key));
                }catch(NullPointerException e){
                    throw new EvaluationException("key cannot be null");
                }
            else
                throw new EvaluationException("First argument must be a hashtable");
        }
    }
    
    /** Cette classe implante la fonction hashput(h, k, v) */
    protected class HashPutPrimitive extends AbstractInvokableImpl {
        public HashPutPrimitive () {}
        // La fonction hashput est 3-aire:
        @SuppressWarnings("unchecked")
        @Override
        public Object invoke (Object hashtable, Object key, Object value) throws EvaluationException{
            if (hashtable instanceof Hashtable<?, ?>)
                try{
                    ((Hashtable<Object, Object>)hashtable).put(key, value);
                }catch(NullPointerException e){
                    throw new EvaluationException("key or value cannot be null");
                }
            else
                throw new EvaluationException("First argument must be a hashtable");
            return Boolean.FALSE;
        }
    }
}

// end of HashStuff.java
