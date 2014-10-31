package fr.upmc.ilp.ilp3esc.interfaces;

public interface ICgenLexicalEnvironment 
extends fr.upmc.ilp.ilp2.interfaces.ICgenLexicalEnvironment {

    boolean isWithinWhile();
    boolean isWithinWhile(String label);
    
    ICgenLexicalEnvironment withinWhile();
    ICgenLexicalEnvironment withinWhile(String label);

}
