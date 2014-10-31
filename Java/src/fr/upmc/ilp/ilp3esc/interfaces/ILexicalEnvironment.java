package fr.upmc.ilp.ilp3esc.interfaces;

public interface ILexicalEnvironment 
extends fr.upmc.ilp.ilp2.interfaces.ILexicalEnvironment {
    
    // Pour simplifier, la bibliothèque d'exécution est commune à
    // ilp3esc et ilp3escl.
    
    boolean isWithinWhile();
    boolean isWithinWhile(String label);
    
    ILexicalEnvironment withinWhile();
    ILexicalEnvironment withinWhile(String label);

}
