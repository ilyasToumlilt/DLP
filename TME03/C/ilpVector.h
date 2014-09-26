/* $Id$ */

#ifndef ILP_VECTOR_H
#define ILP_VECTOR_H

#define ILP_OTHER_KINDS , \
          ILP_VECTOR_KIND = 0xab080ba
#define ILP_OTHER_STRUCTS \
          struct asVector {                         \
               int                 _size;           \
               struct ILP_Object*  asVectorItem[1]; \
          } asVector;

#include "ilp.h"

extern ILP_Object ILP_make_vector (ILP_Object size, ILP_Object filling);
extern ILP_Object ILP_vector_get (ILP_Object vector, ILP_Object index);
extern ILP_Object ILP_vector_length (ILP_Object vector);

#endif /* ILP_VECTOR_H */

/* end of ilpVector.h */
