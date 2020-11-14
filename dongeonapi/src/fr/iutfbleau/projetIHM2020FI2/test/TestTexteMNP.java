package fr.iutfbleau.projetIHM2020FI2.test;
import fr.iutfbleau.projetIHM2020FI2.API.*;
import fr.iutfbleau.projetIHM2020FI2.MNP.*;
public class TestTexteMNP{
	
	public static void main(String[] args) {
		// morceaux de modèle
		
		// Une usinePassagePiece à produire les pièces et passage du dongeon (sert de constructeur et de mémoire pour les pièces et passage).
		PassagePieceFactory usinePassagePiece = new PassagePieceFactoryNP();
		
		//    Illustration de l'exemple de dongeon 
		//
		//    [NB. les passages pourraient être des couloir arbitraires, pouvant se croiser.]              
		//
		//          p2 - p4     
		//          |    |      .
		//     p1 - p0 - p3 - p9
		//     |              |
		//     p5 - p6 - p7 - p8
		//     
		Piece p0 = usinePassagePiece.newPiece();
		Piece p1 = usinePassagePiece.newPiece();
		Piece p2 = usinePassagePiece.newPiece();
		Piece p3 = usinePassagePiece.newPiece();
		Piece p4 = usinePassagePiece.newPiece();
		Piece p5 = usinePassagePiece.newPiece();
		Piece p6 = usinePassagePiece.newPiece();
		Piece p7 = usinePassagePiece.newPiece();
		Piece p8 = usinePassagePiece.newPiece();	
		Piece p9 = usinePassagePiece.newPiece();

		Passage p0p1 = usinePassagePiece.newPassage(Direction.OUEST,p0,Direction.EST,p1);
		//p0p3.setEtatPassage(EtatPassage.OPEN);
		Passage p0p2 = usinePassagePiece.newPassage(Direction.NORD,p0,Direction.SUD,p2);
		Passage p0p3 = usinePassagePiece.newPassage(Direction.EST,p0,Direction.OUEST,p3);
		p0p3.setEtatPassage(EtatPassage.LOCKED);
		Passage p1p5 = usinePassagePiece.newPassage(Direction.SUD,p1,Direction.NORD,p5);
		Passage p2p4 = usinePassagePiece.newPassage(Direction.OUEST,p2,Direction.EST,p4);
		Passage p3p9 = usinePassagePiece.newPassage(Direction.EST,p3,Direction.OUEST,p9);
		Passage p4p3 = usinePassagePiece.newPassage(Direction.SUD,p4,Direction.NORD,p3);
		Passage p5p6 = usinePassagePiece.newPassage(Direction.EST,p5,Direction.OUEST,p6);
		Passage p6p7 = usinePassagePiece.newPassage(Direction.EST,p6,Direction.OUEST,p7);
		Passage p7p8 = usinePassagePiece.newPassage(Direction.EST,p7,Direction.OUEST,p8);
		Passage p8p9 = usinePassagePiece.newPassage(Direction.NORD,p8,Direction.SUD,p9);	
		System.out.print("Création du dongeon.\n");
		
		Joueur j = new JoueurNP();
		j.setPiece(p0);
		System.out.print("Création du joueur.\n");

		//Une usinePassagePiece pour produire des trucs.
		TrucFactory usineTruc = new TrucFactoryNP();
		Truc t0 = usineTruc.newTruc(TypeTruc.ALCOOL,"une bouteille de rhum hors d'âge");
		Truc t1 = usineTruc.newTruc(TypeTruc.CLE,"une clé en bronze");
		Truc t2 = usineTruc.newTruc(TypeTruc.EAU,"une cruche d'eau");
		Truc t3 = usineTruc.newTruc(TypeTruc.EAU,"une gourde d'eau");
		Truc t4 = usineTruc.newTruc(TypeTruc.GOODIES,"une bourse en cuir avec 10 euros");	
		Truc t5 = usineTruc.newTruc(TypeTruc.GOODIES,"une bourse en cuir avec 10 euros");
		System.out.print("Création des objets.\n");

		if (p0.addTruc(t0)&&p0.addTruc(t1)&&p0.addTruc(t2)&&p1.addTruc(t3)&&p2.addTruc(t4)&&p1.addTruc(t4)&&p3.addTruc(t5))
			System.out.print("Ajout des objets dans les pièces du dongeon.\n");

		// la vue et l'interface sont créées ici.
		// Notez que les objets du modèle créés ci-dessus sont tous castés en classes abstraites ou interfaces de l'API.
		// la vue et l'interface doivent donc utiliser seulement les méthodes publiques de l'API.
		// On peut donc changer l'implémentation du modèle tant qu'on ne change pas l'API en préservant le bon fonctionement de la vue et du controleur.
		
		// Pour l'instant, nous n'avons ni vue, ni controleur, mais nous pouvons faire semblant en interagissant avec le modèle via l'API. 

		System.out.println("==========================");
		//description pièce du joueur et son sac à dos.
		System.out.print(j.getPiece().getDescription());
		System.out.print(j.getDescription());
		System.out.println("==========================");
		// action sur une porte
		Passage p = j.getPiece().getPassage(Direction.OUEST);//c'est le passage p0p1
		if (p.agir(null)) {//"le joueur" agit sur le passage à l'ouest, en cas de changement la méthode retourne vrai.
			System.out.print("Le joueur ouvre la porte à l'ouest.\n");	
			System.out.print(j.getPiece().getDescription());
			System.out.print(j.getDescription());
		}
		System.out.println("==========================");
		// prise d'un objet
		if (j.getPiece().removeTruc(t2) && j.addTruc(t2))
			System.out.print("Le joueur prend un objet.\n");
		System.out.print(j.getPiece().getDescription());
		System.out.print(j.getDescription());
		System.out.println("==========================");
		//déplacement
		System.out.print("Le joueur va à l'ouest.\n");
		j.setPiece(p.getAutrePiece(j.getPiece()));       // le joueur est dans la pièce p1
		System.out.print(j.getPiece().getDescription());
		System.out.print(j.getDescription());
		System.out.println("==========================");
		//dépôt d'un objet.
		if (j.removeTruc(t2) && j.getPiece().addTruc(t2))
			System.out.print("Le joueur dépose un objet.\n");
		System.out.print(j.getPiece().getDescription());
		System.out.print(j.getDescription());
		System.out.println("==========================");
		//déplacement
		System.out.print("Le joueur retourne à l'est.\n");
		p = j.getPiece().getPassage(Direction.EST);
		j.setPiece(p.getAutrePiece(j.getPiece()));
		System.out.print(j.getPiece().getDescription());
		System.out.print(j.getDescription());
		System.out.println("==========================");
		//prise d'un objet
		if (j.getPiece().removeTruc(t1) && j.addTruc(t1))
			System.out.print("Le joueur prend un objet.\n");
		System.out.print(j.getPiece().getDescription());
		System.out.print(j.getDescription());
		System.out.println("==========================");
		// action sur une porte
		p = j.getPiece().getPassage(Direction.EST);
		if (p.agir(t1)) {//"le joueur" agit sur le passage à l'est avec t1, en cas de changement la méthode retourne vrai.
			System.out.print("Le joueur ouvre une porte avec un objet.\n");
			System.out.print(j.getPiece().getDescription());
			System.out.print(j.getDescription());
		}
		System.out.println("==========================");
		//déplacement
		System.out.print("Le joueur va à l'est.\n");
		j.setPiece(p.getAutrePiece(j.getPiece()));
		System.out.print(j.getPiece().getDescription());
		System.out.print(j.getDescription());
		System.out.println("==========================");
	}
}
