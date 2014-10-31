/* ******************************************************************
 * ILP -- Implantation d'un langage de programmation.
 * Copyright (C) 2006 <Christian.Queinnec@lip6.fr>
 * $Id: Process.java 1331 2014-01-04 15:35:11Z queinnec $
 * GPL version=2
 * ******************************************************************/

package fr.upmc.ilp.ilp4;

import java.io.IOException;

import org.w3c.dom.Document;

import fr.upmc.ilp.ilp2.interfaces.ICommon;
import fr.upmc.ilp.ilp2.interfaces.ILexicalEnvironment;
import fr.upmc.ilp.ilp2.runtime.ConstantsStuff;
import fr.upmc.ilp.ilp3.ThrowPrimitive;
import fr.upmc.ilp.ilp4.ast.CEAST;
import fr.upmc.ilp.ilp4.ast.CEASTFactory;
import fr.upmc.ilp.ilp4.ast.CEASTParser;
import fr.upmc.ilp.ilp4.ast.NormalizeException;
import fr.upmc.ilp.ilp4.ast.NormalizeGlobalEnvironment;
import fr.upmc.ilp.ilp4.ast.NormalizeLexicalEnvironment;
import fr.upmc.ilp.ilp4.interfaces.IAST4Factory;
import fr.upmc.ilp.ilp4.interfaces.IAST4program;
import fr.upmc.ilp.ilp4.interfaces.INormalizeGlobalEnvironment;
import fr.upmc.ilp.ilp4.interfaces.INormalizeLexicalEnvironment;
import fr.upmc.ilp.ilp4.runtime.CommonPlus;
import fr.upmc.ilp.ilp4.runtime.LexicalEnvironment;
import fr.upmc.ilp.ilp4.runtime.PrintStuff;
import fr.upmc.ilp.tool.IFinder;

/** Cette classe pr�cise comment est trait� un programme d'ILP4. */

public class Process extends fr.upmc.ilp.ilp3.Process {

    /** Un constructeur utilisant toutes les valeurs par defaut possibles. 
     * @throws IOException */

    public Process (IFinder finder) throws IOException {
        super(finder); // pour m�moire!
        setGrammar(getFinder().findFile("grammar4.rng"));
        IAST4Factory factory = new CEASTFactory();
        setFactory(factory);
        setParser(new CEASTParser(factory));
    }

    /** Profitons de la covariance! */
    @Override
    public IAST4program getCEAST () {
        return CEAST.narrowToIAST4program(super.getCEAST());
    }
    @Override
    public IAST4Factory getFactory () {
        return CEAST.narrowToIAST4Factory(super.getFactory());
    }

    /** Initialisation: @see fr.upmc.ilp.tool.AbstractProcess. */

    /** Pr�paration. On analyse syntaxiquement le texte du programme,
     * on effectue quelques analyses et on l'am�ne � un �tat o� il
     * pourra �tre interpr�t� ou compil�. Toutes les analyses communes
     * � ces deux fins sont partag�es ici.
     */
    @Override
    public void prepare() {
        try {
            final Document d = getDocument(this.rngFile);
            setCEAST(getParser().parse(d));

            // Toutes les analyses statiques
            setCEAST(performNormalization());
            //System.out.println(new XMLwriter().process(getCEAST()));
            getCEAST().computeInvokedFunctions();
            getCEAST().inline(getFactory());
            getCEAST().computeGlobalVariables();

            this.prepared = true;

        } catch (Throwable e) {
            this.preparationFailure = e;
        }
    }
    
    /** Normalization */
    
    public IAST4program performNormalization()
    throws NormalizeException {
        IAST4Factory factory = getFactory();
        final INormalizeLexicalEnvironment normlexenv =
            new NormalizeLexicalEnvironment.EmptyNormalizeLexicalEnvironment();
        final INormalizeGlobalEnvironment normcommon =
            new NormalizeGlobalEnvironment();
        normcommon.addPrimitive(factory.newGlobalVariable("print"));
        normcommon.addPrimitive(factory.newGlobalVariable("newline"));
        normcommon.addPrimitive(factory.newGlobalVariable("throw"));
        return getCEAST().normalize(normlexenv, normcommon, factory);
    }

    /** Interpretation */

    @Override
    public void interpret() {
        try {
            assert this.prepared;
            final ICommon intcommon = new CommonPlus();
            intcommon.bindPrimitive("throw", ThrowPrimitive.create());
            final ILexicalEnvironment intlexenv =
                LexicalEnvironment.EmptyLexicalEnvironment.create();
            final PrintStuff intps = new PrintStuff();
            intps.extendWithPrintPrimitives(intcommon);
            final ConstantsStuff csps = new ConstantsStuff();
            csps.extendWithPredefinedConstants(intcommon);

            this.result = getCEAST().eval(intlexenv, intcommon);
            this.printing = intps.getPrintedOutput().trim();

            this.interpreted = true;

        } catch (Throwable e) {
            this.interpretationFailure = e;
        }
    }

    /** Compilation vers C (h�rit�e) */

    /** Ex�cution du programme compil� (h�rit�e) */
}

// end of Process.java
