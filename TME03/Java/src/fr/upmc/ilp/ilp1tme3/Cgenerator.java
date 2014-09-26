package fr.upmc.ilp.ilp1tme3;

import fr.upmc.ilp.ilp1.cgen.CgenerationException;
import fr.upmc.ilp.ilp1.cgen.ICgenEnvironment;
import fr.upmc.ilp.ilp1.cgen.ICgenLexicalEnvironment;
import fr.upmc.ilp.ilp1.interfaces.IAST;
import fr.upmc.ilp.ilp1.interfaces.IASTprogram;


public class Cgenerator extends fr.upmc.ilp.ilp1.cgen.Cgenerator {

    public Cgenerator (final ICgenEnvironment common) {
        super(common);
    }

    @Override
    protected void analyze (final IAST iast,
                              final ICgenLexicalEnvironment lexenv,
                              final ICgenEnvironment common,
                              final String destination)
      throws CgenerationException {
      if ( iast instanceof IASTprogram ) {
          generate((IASTprogram) iast, lexenv, common);
      } else {
          super.analyze(iast, lexenv, common, destination);
      }
    }

    @Override
    protected void generate (final IASTprogram iast,
                               final ICgenLexicalEnvironment lexenv,
                               final ICgenEnvironment common )
        throws CgenerationException {
        buffer.append("#include <stdio.h>\n");
        buffer.append("#include <stdlib.h>\n");
        buffer.append("\n");
        buffer.append("#include \"../Java/src/fr/upmc/ilp/ilp1tme3/ilpVector.h\"\n");
        buffer.append("#include \"../Java/src/fr/upmc/ilp/ilp1tme3/ilpSin.h\"\n");
        buffer.append("\n");
        buffer.append("ILP_Object ilp_program ()\n");
        analyzeInstruction(iast.getBody(), lexenv, common, "return");
        buffer.append("\n");
        buffer.append("int main (int argc, char *argv[]) {\n");
        buffer.append("  ILP_print(ilp_program());\n");
        buffer.append("  ILP_newline();\n");
        buffer.append("  return EXIT_SUCCESS;\n");
        buffer.append("}\n");
        buffer.append("/* fin */\n");
    }
}
