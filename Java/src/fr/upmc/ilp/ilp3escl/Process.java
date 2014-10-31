package fr.upmc.ilp.ilp3escl;

import java.io.IOException;

import fr.upmc.ilp.tool.IFinder;

public class Process extends fr.upmc.ilp.ilp3esc.Process {

    public Process (IFinder finder) throws IOException {
        super(finder);
        CEASTFactory factory = new CEASTFactory();
        setFactory(factory);
        setParser(new CEASTParser(factory));
    }
}
