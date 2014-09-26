/* $Id$ */

#include "ilpVector.h"
#include "ilpAlloc.h"
#include "ilpBasicError.h"
#include "ilp.h"

ILP_Object
ILP_make_vector (ILP_Object size, ILP_Object filling) 
{
  switch (size->_kind) {
  case ILP_INTEGER_KIND: {
       int length = size->_content.asInteger;
       int byte_size = sizeof(struct ILP_Object)
            + (sizeof(ILP_Object) * (length));
       ILP_Object vector = ILP_malloc(byte_size, ILP_VECTOR_KIND);
       vector->_content.asVector._size = length;
       for ( int i = 0 ; i<length ; i++ ) {
            vector->_content.asVector.asVectorItem[i] = filling;
       }
       return vector;
  }
  default: {
    return ILP_domain_error("Not a number", size);
  }
  }
}

ILP_Object
ILP_vector_get (ILP_Object vector, ILP_Object index) 
{
  switch (vector->_kind) {
  case ILP_VECTOR_KIND: {
       switch (index->_kind) {
            case ILP_INTEGER_KIND: {
                 int i = index->_content.asInteger;
                 return vector->_content.asVector.asVectorItem[i];
            }
            default: {
                 return ILP_domain_error("Not a number", index);
            }
       }
  }
  default: {
    return ILP_domain_error("Not a vector", vector);
  }
  }
}

ILP_Object
ILP_vector_length (ILP_Object vector) 
{
  switch (vector->_kind) {
  case ILP_VECTOR_KIND: {
       return ILP_make_integer(vector->_content.asVector._size);
  }
  default: {
    return ILP_domain_error("Not a vector", vector);
  }
  }
}

/* end of ilpVector.c */
