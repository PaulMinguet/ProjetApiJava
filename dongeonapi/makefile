# LOUIS Home
#JCFLAGS  =	-cp "/Users/catherine/Desktop/IUT\ 2/ACDA/Projet_API/dongeonapi/mariadb-client.jar:build" -d build -sourcepath "src:tmp"
#JVFLAGS  =	-cp "/Users/catherine/Desktop/IUT\ 2/ACDA/Projet_API/dongeonapi/mariadb-client.jar:build"

# Paul
JCFLAGS  =	-cp "/home/paul/IUT/Annee2/ACDA/F.Madelaine/ACDA/Projets/Projet_API/dongeonapi/lib/mariadb-client/mariadb-client.jar:build" -d build -sourcepath "src:tmp"
JVFLAGS  =	-cp "/home/paul/IUT/Annee2/ACDA/F.Madelaine/ACDA/Projets/Projet_API/dongeonapi/lib/mariadb-client/mariadb-client.jar:build"

#JCFLAGS  =	-cp "/export/documents/mariadb-client.jar:build" -d build -sourcepath "src:tmp"
#JVFLAGS  =	-cp "/export/documents/mariadb-client.jar:build"

JC   	 =	javac
JV 		 =	java

ALLCLASS =  build/fr/iutfbleau/projetIHM2020FI2/*/*.class

PACK	 =	fr.iutfbleau.projetIHM2020FI2.MP.

SMP 	 =  src/fr/iutfbleau/projetIHM2020FI2/MP/
SMNP 	 =  src/fr/iutfbleau/projetIHM2020FI2/MNP/
STESTP	 =  src/fr/iutfbleau/projetIHM2020FI2/testP/
STEST    =  src/fr/iutfbleau/projetIHM2020FI2/test/
SAPI     =  src/fr/iutfbleau/projetIHM2020FI2/API/

BMP      =  build/fr/iutfbleau/projetIHM2020FI2/MP/
BMNP     =  build/fr/iutfbleau/projetIHM2020FI2/MNP/
BTEST    =  build/fr/iutfbleau/projetIHM2020FI2/test/
BAPI     =  build/fr/iutfbleau/projetIHM2020FI2/API/

default: $(BTEST)TestTexteMP.class $(BMP)Action.class $(BMP)Case.class $(BMP)ContientTrucsP.class $(BMP)Inventaire.class $(BMP)JoueurP.class $(BMP)PanelNom.class $(BMP)PassageP.class $(BMP)PassagePieceFactoryP.class $(BMP)PieceP.class $(BMP)Playground.class $(BMP)Reset.class $(BMP)TrucFactoryP.class $(BMP)TrucP.class $(BMP)Vue.class $(BMNP)Action.class $(BMNP)ContientTrucsNP.class $(BMNP)JoueurNP.class $(BMNP)PassageNP.class $(BMNP)PassagePieceFactoryNP.class $(BMNP)PieceNP.class $(BMNP)Playground.class $(BMNP)TrucFactoryNP.class $(BMNP)TrucNP.class 

$(BTEST)TestTexteMP.class : $(STESTP)TestTexteMP.java $(BAPI)Piece.class $(BMP)JoueurP.class $(BMP)TrucFactoryP.class $(BAPI)Truc.class 
	$(JC) $(JCFLAGS) $(STESTP)TestTexteMP.java

$(BMP)Action.class : $(SMP)Action.java
	$(JC) $(JCFLAGS) $(SMP)Action.java
$(BMP)Case.class : $(SMP)Case.java
	$(JC) $(JCFLAGS) $(SMP)Case.java
$(BMP)ContientTrucsP.class : $(SMP)ContientTrucsP.java
	$(JC) $(JCFLAGS) $(SMP)ContientTrucsP.java
$(BMP)Inventaire.class : $(SMP)Inventaire.java
	$(JC) $(JCFLAGS) $(SMP)Inventaire.java
$(BMP)JoueurP.class : $(SMP)JoueurP.java
	$(JC) $(JCFLAGS) $(SMP)JoueurP.javaù
$(BMP)PieceP.class : $(SMP)PieceP.java
	$(JC) $(JCFLAGS) $(SMP)PieceP.java
$(BMP)PanelNom.class : $(SMP)PanelNom.java
	$(JC) $(JCFLAGS) $(SMP)PanelNom.java
$(BMP)PassageP.class : $(SMP)PassageP.java
	$(JC) $(JCFLAGS) $(SMP)PassageP.java
$(BMP)PassagePieceFactoryP.class : $(SMP)PassagePieceFactoryP.java
	$(JC) $(JCFLAGS) $(SMP)PassagePieceFactoryP.java
$(BMP)Playground.class : $(SMP)Playground.java
	$(JC) $(JCFLAGS) $(SMP)Playground.java
$(BMP)Reset.class : $(SMP)Reset.java
	$(JC) $(JCFLAGS) $(SMP)Reset.java
$(BMP)TrucFactoryP.class : $(SMP)TrucFactoryP.java
	$(JC) $(JCFLAGS) $(SMP)TrucFactoryP.java
$(BMP)TrucP.class : $(SMP)TrucP.java
	$(JC) $(JCFLAGS) $(SMP)TrucP.java
$(BMP)Vue.class : $(SMP)Vue.java
	$(JC) $(JCFLAGS) $(SMP)Vue.java

$(BMNP)Action.class : $(SMNP)Action.java
	$(JC) $(JCFLAGS) $(SMNP)Action.java
$(BMNP)ContientTrucsNP.class : $(SMNP)ContientTrucsNP.java
	$(JC) $(JCFLAGS) $(SMNP)ContientTrucsNP.java
$(BMNP)JoueurNP.class : $(SMNP)JoueurNP.java
	$(JC) $(JCFLAGS) $(SMNP)JoueurNP.java
$(BMNP)PassageNP.class : $(SMNP)PassageNP.java
	$(JC) $(JCFLAGS) $(SMNP)PassageNP.java
$(BMNP)PassagePieceFactoryNP.class : $(SMNP)PassagePieceFactoryNP.java
	$(JC) $(JCFLAGS) $(SMNP)PassagePieceFactoryNP.java
$(BMNP)PieceNP.class : $(SMNP)PieceNP.java
	$(JC) $(JCFLAGS) $(SMNP)PieceNP.java
$(BMNP)Playground.class : $(SMNP)Playground.java
	$(JC) $(JCFLAGS) $(SMNP)Playground.java
$(BMNP)TrucFactoryNP.class : $(SMNP)TrucFactoryNP.java	
	$(JC) $(JCFLAGS) $(SMNP)TrucFactoryNP.java
$(BMNP)TrucNP.class : $(SMNP)TrucNP.java
	$(JC) $(JCFLAGS) $(SMNP)TrucNP.java


$(BAPI)Action.class : $(SAPI)Action.java	
	$(JC) $(JCFLAGS) $(SAPI)Action.java
$(BAPI)Activable.class : $(SAPI)Activable.java	
	$(JC) $(JCFLAGS) $(SAPI)Activable.java
$(BAPI)ContientTrucs.class : $(SAPI)ContientTrucs.java	
	$(JC) $(JCFLAGS) $(SAPI)ContientTrucs.java
$(BAPI)Descriptible.class : $(SAPI)Descriptible.java	
	$(JC) $(JCFLAGS) $(SAPI)Descriptible.java
$(BAPI)Direction.class : $(SAPI)Direction.java	
	$(JC) $(JCFLAGS) $(SAPI)Direction.java
$(BAPI)EtatPassage.class : $(SAPI)EtatPassage.java	
	$(JC) $(JCFLAGS) $(SAPI)EtatPassage.java
$(BAPI)Joueur.class : $(SAPI)Joueur.java	
	$(JC) $(JCFLAGS) $(SAPI)Joueur.java
$(BAPI)Passage.class : $(SAPI)Passage.java	
	$(JC) $(JCFLAGS) $(SAPI)Passage.java
$(BAPI)PassagePieceFactory.class : $(SAPI)PassagePieceFactory.java	
	$(JC) $(JCFLAGS) $(SAPI)PassagePieceFactory.java
$(BAPI)Piece.class : $(SAPI)Piece.java	
	$(JC) $(JCFLAGS) $(SAPI)Piece.java
$(BAPI)Playground.class : $(SAPI)Playground.java	
	$(JC) $(JCFLAGS) $(SAPI)Playground.java
$(BAPI)Truc.class :	$(SAPI)Truc.java
	$(JC) $(JCFLAGS) $(SAPI)Truc.java
$(BAPI)TrucFactory.class : $(SAPI)TrucFactory.java
	$(JC) $(JCFLAGS) $(SAPI)TrucFactory.java
$(BAPI)TypeTruc.class : $(SAPI)TypeTruc.java
	$(JC) $(JCFLAGS) $(SAPI)TypeTruc.java

run 	:	
	$(JV) $(JVFLAGS) $(PACK)Playground

clean 	:	
	rm -rf $(ALLCLASS)

creset	:
	$(JC) $(JCFLAGS) $(SMP)Reset.java

reset	:
	$(JV) $(JVFLAGS) $(PACK)Reset

jar		:
	jar cvfe projet.jar fr.iutfbleau.projetIHM2020FI2.MP.Playground -C build fr -C res/images . -C lib/mariadb-client/ .

runjar 	:
	java -jar projet.jar