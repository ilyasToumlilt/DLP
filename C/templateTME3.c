/* $Id$
 * Patron pour TME3
 */

#include <stdio.h>
#include <stdlib.h>

#include "ilpVector.h"
#include "ilpSin.h"

#include "ilpSin.c"
#include "ilpVector.c"

char *ilpTemplate_Id = "$Id$";

ILP_Object
program ()
#   include FICHIER_C

int
main (int argc, char *argv[])
{
     ILP_print(program());
     ILP_newline();
     return EXIT_SUCCESS;
}

/* end of templateTestTME3.c */
