#!/bin/bash

while true 
do 
	echo -e "Ajouter\nMathieu\nBoire un verre\n"
	echo -e "Ajouter\nCyrille\nFaire les courses\n"
	echo -e "Ajouter\nMathieu\nSortir à Lille\n"
	echo -e "Ajouter\nFrançois\nFaire ses devoirs\n"
	echo -e "Affecter\n1\nCyrille\n"
	echo -e "Affecter\n0\nMathieu\n"
	echo -e "Terminer\n2\n"
	echo -e "Lister\n"
	echo -e "STOP\n"
done | nc localhost 9921
