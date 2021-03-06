# ******************************************************************
# ILP -- Implantation d'un langage de programmation.
# Copyright (C) 2004 <Christian.Queinnec@lip6.fr>
# $Id: Makefile 405 2006-09-13 17:21:53Z queinnec $
# GPL version>=2
# ******************************************************************

work : generate.xml
do.work : generate.xml verify.xml
clean :: cleanMakefile
	-rm -f s*.xml

# Convertir des programmes �crits en syntaxe Scheme (avec tout plein
# de parenth�ses) en des fichiers XML conformes aux grammaires d'ILP.
# C'est quand m�me nettement plus simple d'�crire comme cela! Cette
# r�gle engendre �galement les valeurs et les impressions attendues
# pour ces programmes.

#SCHEME	=	umb-scheme
SCHEME	=	bigloo -i

# Sur MacOSX, si bigloo est installe via DarwinPorts alors il est en 
# /opt/local/bin/. Comme ce chemin n'est pas dans le PATH du dock qui 
# lance Eclipse, on corrige le tir dans ce Makefile.

restart.with.bigloo.and.generate.xml :
	@echo Je verifie si je trouve bien Bigloo ...
	PATH="$$PATH:/opt/local/bin" ; which bigloo && ${MAKE} generate.xml

generate.xml : scm2xml.scm
	{ echo "(load \"scm2xml.scm\")" ; \
	  for f in scm2xml-*.scm ; do \
	  echo "(load \"$$f\")" ; done ; \
	  for s in *-[0-9]*.scm ; do \
	  echo "(quote \"Convertir $$s en $${s%.scm}.xml\")" ; \
	  echo "(handle-file \"$$s\" \"../Samples/$${s%.scm}\")" ; \
	  done ; } | ${SCHEME}
# Ces programmes provoquent des erreurs attendues de compilation or les
# tests ne supportent que des erreurs d'execution.
	rm -f ../Samples/e270*
# bizarrement Umb-Scheme sort en erreur !?
# Attention: Umb-Scheme represente 2.0 par un entier et non par un flottant.
# Attention: set! ne cree pas de variable globale en Umb-Scheme.
# Bien mieux avec Bigloo.

JAVA	=	java
JING	=	../../Java/jars/jing.jar

# V�rifier que les programmes XML engendr�s sont valides vis-�-vis des
# grammaires d'ILP. Les grammaires �tant embo�t�es, la grammaire i
# doit valider tous les programmes reconnus par les grammaires de
# niveau inf�rieur � i. Un programme a un nom u*-i.scm signifiant
# qu'il doit �tre reconnu par toutes les grammaires de niveau au moins
# i. Attention aux caract�res sp�ciaux d'XML < et consorts qui nomment
# les op�rateurs de comparaison.

verify.xml : 
	cd ../ && m create.rng.files
	for g in 6 4 3 2 1 ; do \
	for s in u*-[1-$$g].scm ; do \
	echo "------- Validite de ../Samples/$${s%.scm}.xml pour grammar$$g" ; \
	${JAVA} -jar ${JING} ../grammar$$g.rng ../Samples/$${s%.scm}.xml ; \
	done ; done
# Ces tests sont realis�s beaucoup plus vite qu'ici avec le build.xml d'ant.
# Tout simplement parce qu'ici, on relance la JVM pour chaque test et que la
# JVM est de d�marrage tr�������s lent.

# Un test par-ci par-la.

BASE	=	u57-2

test.by.hand :
	{ echo '(load "scm2xml.scm")' ; \
	  echo '(handle-file "${BASE}.scm" "../Samples/${BASE}")' ; } | \
	${SCHEME}
	cd ../Samples/ && cat ${BASE}.xml ${BASE}.result ${BASE}.print
# Apres avoir charg� le fichier scm2xml.scm, la fonction handle-file prend
# deux arguments: le fichier � traiter, le fichier o� stocker le r�sultat.
# NOTE: Si l'on utilise bigloo, on peut � partir de scm2xml.scm fabriquer
# un ex�cutable (en Java) prenant les arguments de handle-file sur sa
# ligne de commande et ainsi obtenir un pr�processeur pour pas cher.

# un autre test par-ci, par la

FILE	=	u60-3
I	=	3
test.rng : 
	cd ../ && m create.rng.files
	${JAVA} -jar ${JING} ../grammar$I.rng ../Samples/${FILE}.xml

# tests du convertisseur Sexpression vers XML

test.scm2xml :
	{ echo '(load "scm2xml.scm")' ; \
	  echo '(load "scm2xmlTest.scm")' ; } | \
	${SCHEME}

# ########################################## Compilation
# Compilation du programme Scheme de conversion scm -> xml en Java.

scm2xml.java : 
# -gjvm 
	bigloo -jvm -c -o scm2xml.java -call/cc -v3 -Wall -jvm-jar \
		scm2xml.scm scm2xml-*.scm

# end of Imakefile
