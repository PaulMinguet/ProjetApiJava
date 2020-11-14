package fr.iutfbleau.projetIHM2020FI2.testP;
import fr.iutfbleau.projetIHM2020FI2.API.*;
import fr.iutfbleau.projetIHM2020FI2.MP.*;
import java.sql.*;
public class TestTexteMP{

	public Piece p0, p1, p2, p3, p4, p5, p6, p7, p8, p9;
	public Joueur j;
	private Passage p0p1, p0p2, p0p3, p1p5, p2p4, p3p9, p4p3, p5p6, p6p7, p7p8, p8p9;
	public Truc t0, t1, t2, t3, t4, t5;
	private int direction = 1;

	public TestTexteMP(){

		this.resetLoad();
		int deuxiemeLancement = 0;
		
		// morceaux de modèle
		
		// Une usinePassagePiece à produire les pièces et passage du dongeon (sert de constructeur et de mémoire pour les pièces et passage).
		PassagePieceFactory usinePassagePiece = new PassagePieceFactoryP();

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

		try{
            Class.forName("org.mariadb.jdbc.Driver");
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
        }

        try{
            Connection cnx = DriverManager.getConnection("jdbc:mariadb://dwarves.iut-fbleau.fr/minguetp", "minguetp", "minguetp");
            PreparedStatement pst = cnx.prepareStatement("SELECT COUNT(*) FROM API_Piece");
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                deuxiemeLancement = rs.getInt(1);
            }
            rs.close();
            pst.close();
            cnx.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }

		System.out.println("Création/Synchronisation des pièces");
		p0 = usinePassagePiece.newPiece();
		p1 = usinePassagePiece.newPiece();
		p2 = usinePassagePiece.newPiece();
		p3 = usinePassagePiece.newPiece();
		p4 = usinePassagePiece.newPiece();
		p5 = usinePassagePiece.newPiece();
		p6 = usinePassagePiece.newPiece();
		p7 = usinePassagePiece.newPiece();
		p8 = usinePassagePiece.newPiece();
		p9 = usinePassagePiece.newPiece();

		Piece[] listePiece = new Piece[10];
		listePiece[0] = p0;
		listePiece[1] = p1;
		listePiece[2] = p2;
		listePiece[3] = p3;
		listePiece[4] = p4;
		listePiece[5] = p5;
		listePiece[6] = p6;
		listePiece[7] = p7;
		listePiece[8] = p8;
		listePiece[9] = p9;

		System.out.println("Création/Synchronisation des passages");
		p0p1 = usinePassagePiece.newPassage(Direction.OUEST,p0,Direction.EST,p1);
		//p0p3.setEtatPassage(EtatPassage.OPEN);
		p0p2 = usinePassagePiece.newPassage(Direction.NORD,p0,Direction.SUD,p2);
		p0p3 = usinePassagePiece.newPassage(Direction.EST,p0,Direction.OUEST,p3);
		p0p3.setEtatPassage(EtatPassage.LOCKED);
		p1p5 = usinePassagePiece.newPassage(Direction.SUD,p1,Direction.NORD,p5);
		p2p4 = usinePassagePiece.newPassage(Direction.OUEST,p2,Direction.EST,p4);
		p3p9 = usinePassagePiece.newPassage(Direction.EST,p3,Direction.OUEST,p9);
		p4p3 = usinePassagePiece.newPassage(Direction.SUD,p4,Direction.NORD,p3);
		p5p6 = usinePassagePiece.newPassage(Direction.EST,p5,Direction.OUEST,p6);
		p6p7 = usinePassagePiece.newPassage(Direction.EST,p6,Direction.OUEST,p7);
		p7p8 = usinePassagePiece.newPassage(Direction.EST,p7,Direction.OUEST,p8);
		p8p9 = usinePassagePiece.newPassage(Direction.NORD,p8,Direction.SUD,p9);	
		System.out.print("Création/Synchronisation du dongeon.\n");
		
		System.out.print("Création/Synchronisation du joueur.\n");
		j = new JoueurP();
		if(j.getPiece() == null){
			System.out.println("affecte");
			affecte(p0, p1, p2, p3, p4, p5, p6, p7, p8, p9, j);
		}else{
			System.out.println("p0");
			j.setPiece(p0);
		}
		//Une usinePassagePiece pour produire des trucs.
		System.out.println("Création/Synchronisation des trucs");
		TrucFactory usineTruc = new TrucFactoryP();
		t0 = usineTruc.newTruc(TypeTruc.ALCOOL,"une bouteille de rhum hors d'âge");
		t1 = usineTruc.newTruc(TypeTruc.CLE,"une clé en bronze");
		t2 = usineTruc.newTruc(TypeTruc.EAU,"une cruche d'eau");
		t3 = usineTruc.newTruc(TypeTruc.EAU,"une gourde d'eau");
		t4 = usineTruc.newTruc(TypeTruc.GOODIES,"une bourse en cuir avec 10 euros");	
		t5 = usineTruc.newTruc(TypeTruc.GOODIES,"une bourse en cuir avec 10 euros");

		Truc[] listeTruc = new Truc[6];
		listeTruc[0] = t0;
		listeTruc[1] = t1;
		listeTruc[2] = t2;
		listeTruc[3] = t3;
		listeTruc[4] = t4;
		listeTruc[5] = t5;

		// SI COUNT >0 
			// PARCOURIR TABLE CONTIENT, pour chaque occurence :
				// PARCOUR TABLE PIECE pour chercher le Pn correspondant
				// PARCOURIR TABLE TRUC Pour chercher le Tn correspondant
				// Ajouter AddTrucContient
			// PAROUCRIR TABLE POSSEDE et fait addTrucPossede qui sont dans la table
		// SINON
		if(deuxiemeLancement != 0){
			int idP = 0, idT = 0;
			String nomP = "", nomT = "";
			try{
	            Class.forName("org.mariadb.jdbc.Driver");
	        }
	        catch(ClassNotFoundException e){
	            e.printStackTrace();
	        }

	        try{
	            Connection cnx = DriverManager.getConnection("jdbc:mariadb://dwarves.iut-fbleau.fr/minguetp", "minguetp", "minguetp");
	            PreparedStatement pst = cnx.prepareStatement("SELECT pieceId, trucId FROM API_ContientTrucs");
	            ResultSet rs = pst.executeQuery();
	            while(rs.next()){
	            	idP = rs.getInt(1);
	            	idT = rs.getInt(2);
	                PreparedStatement pst2 = cnx.prepareStatement("SELECT pieceNom FROM API_Piece WHERE pieceId = ?");
	                pst2.setInt(1, idP);
	                ResultSet rs2 = pst2.executeQuery();
	                while(rs2.next()){
	                	nomP = rs2.getString(1);
	                }
	                pst2 = cnx.prepareStatement("SELECT trucNom FROM API_Truc WHERE trucId = ?");
	                pst2.setInt(1, idT);
	                rs2 = pst2.executeQuery();
	                while(rs2.next()){
	                	nomT = rs2.getString(1);
	                }
	                for(int i = 0; i < 10; i++){
	                	for(int j = 0; j < 6; j++){
			                if(listePiece[i].toString().equals(nomP) && listeTruc[j].toString().equals(nomT))
			                	listePiece[i].addTruc(listeTruc[j]);
			            }
			        }
	            }
	            pst = cnx.prepareStatement("SELECT trucId FROM API_PossedeTruc");
	            rs = pst.executeQuery();
	            while(rs.next()){
	            	idT = rs.getInt(1);
	                PreparedStatement pst2 = cnx.prepareStatement("SELECT trucNom FROM API_Truc WHERE trucId = ?");
	                pst2.setInt(1, idP);
	                ResultSet rs2 = pst2.executeQuery();
	                while(rs2.next()){
	                	nomT = rs2.getString(1);
	                }
	                rs2.close();
	                pst2.close();
                	for(int j = 0; j < 6; j++){
		                if(listeTruc[j].toString().equals(nomT))
		                	this.j.addTruc(listeTruc[j]);
		            }
	            }
	            rs.close();
	            pst.close();
	            cnx.close();
	        }
	        catch(SQLException e){
	            e.printStackTrace();
	        }
		}else{
			if (p0.addTruc(t0)&&p0.addTruc(t1)&&p0.addTruc(t2)&&p1.addTruc(t3)&&p2.addTruc(t4)&&p3.addTruc(t5))
				System.out.print("Ajout des objets dans les pièces du dongeon.\n");
		}
		//Restoration des états
		
		// la vue et l'interface sont créées ici.
		// Notez que les objets du modèle créés ci-dessus sont tous castés en classes abstraites ou interfaces de l'API.
		// la vue et l'interface doivent donc utiliser seulement les méthodes publiques de l'API.
		// On peut donc changer l'implémentation du modèle tant qu'on ne change pas l'API en préservant le bon fonctionement de la vue et du controleur.
		
		// Pour l'instant, nous n'avons ni vue, ni controleur, mais nous pouvons faire semblant en interagissant avec le modèle via l'API. 
		/*System.out.println("==========================");
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
		Syst);
                    vue.afficheObjets(test.getObjets());em.out.print(j.getDescription());
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
		System.out.println("==========================");*/
	}

	public int getMap(){
		int id = 0;
        try{
            Class.forName("org.mariadb.jdbc.Driver");
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
        }

        try{
            Connection cnx = DriverManager.getConnection("jdbc:mariadb://dwarves.iut-fbleau.fr/minguetp", "minguetp", "minguetp");
            PreparedStatement pst = cnx.prepareStatement("SELECT joueurPiece FROM API_Joueur");
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                id = rs.getInt(1);
            }
            rs.close();
            pst.close();
            cnx.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }

        return id;
	}

	public int[] getObjetsP(){
		int[] listeObjets = new int[6];
		int i = 0;
		try{
            Class.forName("org.mariadb.jdbc.Driver");
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
        }

        try{
            Connection cnx = DriverManager.getConnection("jdbc:mariadb://dwarves.iut-fbleau.fr/minguetp", "minguetp", "minguetp");
            PreparedStatement pst = cnx.prepareStatement("SELECT trucId FROM API_ContientTrucs WHERE pieceId = ?");
            pst.setInt(1, getMap());
            pst.executeQuery();
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                listeObjets[i] = rs.getInt(1);
                i++;
	        }
            rs.close();
            pst.close();
            cnx.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
		return listeObjets;
	}

	public int[] getObjets(){
		int[] listeObjets = new int[6];
		int i = 0, tId = 0;
		try{
            Class.forName("org.mariadb.jdbc.Driver");
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
        }

        try{
            Connection cnx = DriverManager.getConnection("jdbc:mariadb://dwarves.iut-fbleau.fr/minguetp", "minguetp", "minguetp");
            PreparedStatement pst = cnx.prepareStatement("SELECT trucId FROM API_PossedeTruc");
            pst.executeQuery();
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                listeObjets[i] = rs.getInt(1);
                i++;
	        }
            rs.close();
            pst.close();
            cnx.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
		return listeObjets;
	}

	public int changerDirection(boolean gauche){
		if(direction%4 == (gauche?1:0)){
			System.out.print("Gauche ");
            direction = (gauche?4:1);
        }else{
        	System.out.print("Droite ");
            direction+=(gauche?-1:1);
        }
        return chdir();
	}

	public int getDirection(){
        return chdir();
	}

	public int changerDirDT(){
		direction = (direction + 1)%4+1;
        return chdir();
	}

	public int chdir(){
		System.out.println("direction = " + direction);
    	if(j.getPiece().getPassage(Direction.values()[direction-1]) == null){
        	return direction+4;
        }else{
        	if(j.getPiece().getPassage(Direction.values()[direction-1]).getEtatPassage() == EtatPassage.LOCKED){
	        	return direction+8;
	        }else{
	        	return direction;
	        }
	    }
	}

	public boolean getCle(){
		boolean valid = false;
		try{
            Class.forName("org.mariadb.jdbc.Driver");
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
        }

        try{
            Connection cnx = DriverManager.getConnection("jdbc:mariadb://dwarves.iut-fbleau.fr/minguetp", "minguetp", "minguetp");
            PreparedStatement pst = cnx.prepareStatement("SELECT * FROM API_PossedeTruc WHERE trucId = 2");
            pst.executeQuery();
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
            	valid = true;
            }else{
            	valid = false;
            }
            rs.close();
            pst.close();
            cnx.close();
        }

        catch(SQLException e){
            e.printStackTrace();
        }
        return valid;
	}

	public void action(){
		Passage p = j.getPiece().getPassage(Direction.values()[direction-1]);
		if(j.getPiece().getPassage(Direction.values()[direction-1]).getEtatPassage() == EtatPassage.LOCKED && getCle()){
			j.removeTruc(t1);
			j.getPiece().getPassage(Direction.values()[direction-1]).setEtatPassage(EtatPassage.OPEN);
		}
		if(p != null){
			if(p.agir(null)){
				j.setPiece(p.getAutrePiece(j.getPiece()));
			}
		}
	}

	public void affecte(Piece p0, Piece p1, Piece p2, Piece p3, Piece p4, Piece p5, Piece p6, Piece p7, Piece p8, Piece p9, Joueur j){
        String pNom = "";
        try{
            Class.forName("org.mariadb.jdbc.Driver");
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
        }

        try{
            Connection cnx = DriverManager.getConnection("jdbc:mariadb://dwarves.iut-fbleau.fr/minguetp", "minguetp", "minguetp");
            PreparedStatement pst = cnx.prepareStatement("SELECT pieceNom FROM API_Piece WHERE pieceId = (SELECT joueurPiece FROM API_Joueur)");
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                pNom = rs.getString(1);
            }
            rs.close();
            pst.close();
            cnx.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        if(pNom.equals(p0.toString())){
        	System.out.println("Affectation de la pièce 1");
            j.setPiece(p0);
        }else if(pNom.equals(p1.toString())){
        	System.out.println("Affectation de la pièce 2");
            j.setPiece(p1);
        }else if(pNom.equals(p2.toString())){
        	System.out.println("Affectation de la pièce 3");
            j.setPiece(p2);
        }else if(pNom.equals(p3.toString())){
        	System.out.println("Affectation de la pièce 4");
            j.setPiece(p3);
        }else if(pNom.equals(p4.toString())){
        	System.out.println("Affectation de la pièce 5");
            j.setPiece(p4);
        }else if(pNom.equals(p5.toString())){
        	System.out.println("Affectation de la pièce 6");
            j.setPiece(p5);
        }else if(pNom.equals(p6.toString())){
        	System.out.println("Affectation de la pièce 7");
            j.setPiece(p6);
        }else if(pNom.equals(p7.toString())){
        	System.out.println("Affectation de la pièce 8");
            j.setPiece(p7);
        }else if(pNom.equals(p8.toString())){
        	System.out.println("Affectation de la pièce 9");
            j.setPiece(p8);
        }else if(pNom.equals(p9.toString())){
        	System.out.println("Affectation de la pièce 10");
            j.setPiece(p9);
        }
    }

	public void resetBD(){
		try{
            Class.forName("org.mariadb.jdbc.Driver");
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
        }

        try{
            Connection cnx = DriverManager.getConnection("jdbc:mariadb://dwarves.iut-fbleau.fr/minguetp", "minguetp", "minguetp");
            PreparedStatement pst = cnx.prepareStatement("DELETE FROM API_Passage");
            pst.executeUpdate();
            System.out.println("Initialisation des passages");
            pst = cnx.prepareStatement("DELETE FROM API_ContientTrucs");
            pst.executeUpdate();
            pst = cnx.prepareStatement("DELETE FROM API_PossedeTruc");
            pst.executeUpdate();
            System.out.println("Initialisation des Contenus");
            pst = cnx.prepareStatement("DELETE FROM API_Truc");
            pst.executeUpdate();
            System.out.println("Initialisation des trucs");
            pst = cnx.prepareStatement("DELETE FROM API_PieceVisitee");
            pst.executeUpdate();
            System.out.println("Initialisation des pièces visitées");
            pst = cnx.prepareStatement("DELETE FROM API_Joueur");
            pst.executeUpdate();
            System.out.println("Initialisation des joueurs");
            pst = cnx.prepareStatement("DELETE FROM API_Piece");
            pst.executeUpdate();
            System.out.println("Initialisation des pièces");
            pst = cnx.prepareStatement("ALTER TABLE API_Passage AUTO_INCREMENT=0");
            pst.executeUpdate();
            pst = cnx.prepareStatement("ALTER TABLE API_ContientTrucs AUTO_INCREMENT=0");
            pst.executeUpdate();
            pst = cnx.prepareStatement("ALTER TABLE API_PossedeTruc AUTO_INCREMENT=0");
            pst.executeUpdate();
            pst = cnx.prepareStatement("ALTER TABLE API_Passage AUTO_INCREMENT=0");
            pst.executeUpdate();
            pst = cnx.prepareStatement("ALTER TABLE API_Truc AUTO_INCREMENT=0");
            pst.executeUpdate();
            pst = cnx.prepareStatement("ALTER TABLE API_PieceVisitee AUTO_INCREMENT=0");
            pst.executeUpdate();
            pst = cnx.prepareStatement("ALTER TABLE API_Joueur AUTO_INCREMENT=0");
            pst.executeUpdate();
            pst = cnx.prepareStatement("ALTER TABLE API_Piece AUTO_INCREMENT=0");
            pst.executeUpdate();
            pst.close();
            cnx.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
	}

	public void resetLoad(){
		System.out.println("Réinitialisation des mémoires");
		try{
            Class.forName("org.mariadb.jdbc.Driver");
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
        }

        try{
            Connection cnx = DriverManager.getConnection("jdbc:mariadb://dwarves.iut-fbleau.fr/minguetp", "minguetp", "minguetp");
            PreparedStatement pst = cnx.prepareStatement("UPDATE API_Piece SET pieceLoad = null");
            pst.executeUpdate();
            pst = cnx.prepareStatement("UPDATE API_Passage SET passageLoad = null");
            pst.executeUpdate();
            pst = cnx.prepareStatement("UPDATE API_Truc SET trucLoad = null");
            pst.executeUpdate();
            pst = cnx.prepareStatement("UPDATE API_ContientTrucs SET ctLoad = null");
            pst.executeUpdate();
            pst = cnx.prepareStatement("UPDATE API_PossedeTruc SET ptLoad = null");
            pst.executeUpdate();
            pst = cnx.prepareStatement("UPDATE API_PieceVisitee SET pvLoad = null");
            pst.executeUpdate();
            pst.close();
            cnx.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
	}
}
