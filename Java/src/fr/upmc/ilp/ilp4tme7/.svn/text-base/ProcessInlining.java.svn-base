package fr.upmc.ilp.ilp4tme7;

import java.io.IOException;

import org.w3c.dom.Document;

import fr.upmc.ilp.ilp4tme7.ast.CEASTParserInlining;
import fr.upmc.ilp.ilp4tme7.ast.VisitorInlining;
import fr.upmc.ilp.tool.IFinder;

/**
 * Cette version introduit des fonctions localement definies. Le compilateur
 * reste à écrire.
 */

public class ProcessInlining extends fr.upmc.ilp.ilp4.Process {

    public ProcessInlining (IFinder finder) throws IOException {
        super(finder);
        setParser(new CEASTParserInlining(getFactory()));
    }
    
    /** Initialisation: @see fr.upmc.ilp.tool.AbstractProcess. */

    /** Préparation  */
    
    @Override
    public void prepare() {
        try {
            final Document d = getDocument(this.rngFile);
            setCEAST(getParser().parse(d));

            // Toutes les analyses statiques
            setCEAST(performNormalization());
            getCEAST().computeInvokedFunctions();
            //getCEAST().inline(getFactory()); devient:
            VisitorInlining vi = new VisitorInlining();
            vi.visit(getCEAST(), getFactory());
            getCEAST().computeGlobalVariables(); 

            this.prepared = true;

        } catch (Throwable e) {
            this.preparationFailure = e;
        }
    }
    
    /** Interpretation (héritée) */
    
    /** Compilation vers C (héritée) */

    /** Exécution du programme compilé (héritée) */
}
