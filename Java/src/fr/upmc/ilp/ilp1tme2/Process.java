/* ******************************************************************
 * ILP -- Implantation d'un langage de programmation.
 * Copyright (C) 2004-2007 <Christian.Queinnec@lip6.fr>
 *                         <Jacques.Malenfant@lip6.fr>
 * $Id$
 * GPL version>=2
 * ******************************************************************/

package fr.upmc.ilp.ilp1tme2;

import java.io.IOException;

import org.w3c.dom.Document;

import fr.upmc.ilp.ilp1tme2.eval.IASTCountingConstants;
import fr.upmc.ilp.ilp1tme2.eval.TME2EASTFactory;
import fr.upmc.ilp.ilp1tme2.eval.TME2EASTParser;
import fr.upmc.ilp.tool.IFinder;

public class Process extends fr.upmc.ilp.ilp1.Process 
implements ICountingConstantsProcess {

    public Process (IFinder finder) throws IOException {
        super(finder);
    }

    protected TME2EASTParser parser ;

    @Override
    public void prepare() {
        try {
            final Document d = getDocument(this.rngFile);

            final TME2EASTFactory factory = new TME2EASTFactory();
            final TME2EASTParser parser = new TME2EASTParser(factory);
            this.parser = parser ;
            this.east = parser.parse(d);

            System.err.println(
                "Le nombre de constantes d'après l'AST est : " +
                ((IASTCountingConstants)this.east).compteConstantes()) ;

            this.prepared = true;

        } catch (Throwable e) {
            this.preparationFailure = e;
            if ( this.verbose ) {
                System.err.println(e);
            }
        }
    }

    // Nombre de constantes comptées sur l'arbre DOM
    public int getNbConstantesDOM(){
        return this.parser.getNbConstantes() ;
    }

    // Nombre de constantes comptées sur l'arbre AST
    public int getNbConstantesAST(){
    	return ((IASTCountingConstants)this.east).compteConstantes();
    }
    
    // Interpretation et compilation seront effectuees!
}
