package fr.upmc.ilp.ilp4tme7;

import java.io.IOException;
import java.util.HashSet;

import org.w3c.dom.Document;
import fr.upmc.ilp.ilp4.interfaces.IAST4globalFunctionVariable;
import fr.upmc.ilp.ilp4tme7.ast.CEASTParserInvokedFunctions;
import fr.upmc.ilp.ilp4tme7.ast.InvokedFunctionVisitor;
import fr.upmc.ilp.tool.IFinder;

/**
 * Cette version calcule les fonctions invoquées à l'aide d'un visiteur.
 */

public class ProcessInvokedFunctions extends fr.upmc.ilp.ilp4.Process {

    public ProcessInvokedFunctions (IFinder finder) throws IOException {
        super(finder);
        setParser(new CEASTParserInvokedFunctions(getFactory()));
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
            //getCEAST().computeInvokedFunctions(); devient:
            InvokedFunctionVisitor ifv = new InvokedFunctionVisitor();
            ifv.visit(getCEAST(), new HashSet<IAST4globalFunctionVariable>());
            getCEAST().inline(getFactory()); 
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
