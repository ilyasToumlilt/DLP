package fr.upmc.ilp.ilp2tme4;

import java.io.IOException;

import fr.upmc.ilp.ilp2tme4.ast.CEASTFactory;
import fr.upmc.ilp.ilp2tme4.ast.CEASTParser;
import fr.upmc.ilp.tool.IFinder;

public class Process extends fr.upmc.ilp.ilp2.Process {

    public Process (IFinder finder) throws IOException {
        super(finder); // Pour mémoire!
        getFinder().addPossiblePath("Java/src/fr/upmc/ilp/ilp2tme4");
        setGrammar(getFinder().findFile("grammar2-tme4.rng"));
        CEASTFactory factory = new CEASTFactory();
        setFactory(factory);
        setParser(new CEASTParser(factory));
    }
}
