package fr.upmc.ilp.ilp3tme6;

import java.io.IOException;

import javax.script.ScriptContext;

import fr.upmc.ilp.ilp2.interfaces.ILexicalEnvironment;
import fr.upmc.ilp.ilp2.runtime.CommonPlus;
import fr.upmc.ilp.ilp2.runtime.ConstantsStuff;
import fr.upmc.ilp.ilp2.runtime.LexicalEnvironment;
import fr.upmc.ilp.ilp2.runtime.PrintStuff;
import fr.upmc.ilp.ilp3.ThrowPrimitive;
import fr.upmc.ilp.tool.IFinder;

/**
 * Le Process decrivant comment evaluer un programme ILP2 dans le cadre
 * de la specification JSR 223. Il herite du Process du compilateur vers
 * Javascript afin d'heriter de la methode compile.
 */

public class Process extends fr.upmc.ilp.ilp4.jsgen.Process {
    
    public Process (IFinder finder) throws IOException {
        super(finder);
    }
        
  public void setScriptContext (ScriptContext context) {
    this.sc = context;
  }
  private ScriptContext sc;

  /** Interprétation.
   *  La difference avec la methode heritee est d'utiliser le flux
   * de sortie specifie par le contexte et de ne pas peupler
   * l'environnement global avec les precedentes constantes mais
   * seulement celles du ScriptContext. */

  @Override
    public void interpret() {
    try {
      final CommonPlus intcommon = new CommonPlusScriptContextAdapter(sc);
      intcommon.bindPrimitive("throw", ThrowPrimitive.create());
      ILexicalEnvironment intlexenv = 
          LexicalEnvironment.EmptyLexicalEnvironment.create();
      // On ajoute print, newline et pi encore que...
      final PrintStuff intps = new PrintStuff(sc.getWriter());
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
}
