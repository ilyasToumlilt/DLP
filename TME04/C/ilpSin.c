/* $Id$ */

#include "ilpSin.h"
#include "ilpBasicError.h"
#include "ilp.h"

ILP_Object
ILP_sinus (ILP_Object o)
{
  switch (o->_kind) {
  case ILP_INTEGER_KIND: {
    return ILP_make_float(sin(o->_content.asInteger));
  }
  case ILP_FLOAT_KIND: {
    return ILP_make_float(sin(o->_content.asFloat));
  }
  default: {
    return ILP_domain_error("Not a number", o);
  }
  }
}

/* end of ilpSin.c */
