package fr.iutfbleau.projetIHM2020FI2.MP;
import fr.iutfbleau.projetIHM2020FI2.API.*;
import java.util.*;
import java.sql.*;
/**
 * Permet de gérer la topologie du dongeon en créant et manipulant des objets 
 * _ pour les pièces (implémentant l'interface Piece) et 
 * _ des objets modélisant les accès d'une pièce à l'autre (instances de classes implémentant l'interface Passage). 
 * 
 * 
 * Cette classe n'offre pas de gestion persistante des objets.
 * 
 * Cette classe utilise de manière indépendante PieceP et PassageP.
 * L'implémentation actuelle est en fait quasi-générique et pourrait employer n'importe quelle classe implémentant les interfaces Piece et Passage.
 */
public class PassagePieceFactoryP implements PassagePieceFactory{

	private Set<Piece> vertices;

	private Set<Passage> edges;

	/**
	 * constructeur
	 *
	 * On utilise des linkedHashSet.
	 */
	public PassagePieceFactoryP(){
		this.vertices = new LinkedHashSet<Piece>();
		this.edges = new LinkedHashSet<Passage>();
	}


	/**
	 * ajoute et retourne une nouvelle pièce.
	 * @return la nouvelle pièce
	 */
	@Override
	public Piece newPiece(){
		Piece p = new PieceP();
		String pieceNom = "";
		try{
            Class.forName("org.mariadb.jdbc.Driver");
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
        }

        try{
            Connection cnx = DriverManager.getConnection("jdbc:mariadb://dwarves.iut-fbleau.fr/minguetp", "minguetp", "minguetp");
            PreparedStatement pst = cnx.prepareStatement("SELECT pieceNom FROM API_Piece WHERE pieceId = (SELECT MIN(pieceId) FROM API_Piece WHERE pieceLoad IS NULL)");
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
            	pieceNom = rs.getString(1);
            	//System.out.println("\nadresse récupérée : " + pieceNom);
            	if(pieceNom == null){
            		//System.out.println("===============Pas de nom de pièce===============");
	            	this.insertPieceBD(p);
	            	//System.out.println("nouvelle adresse : " + p.toString());
	            }else{
	            	//System.out.println("===============Pièce déjà existante===============");
	            	this.updatePieceBD(p, pieceNom);
	            	//System.out.println("nouvelle adresse : " + p.toString());
	            }
            }else{
            	//System.out.println("===============Pas de pièce existante===============");
				this.insertPieceBD(p);
            	//System.out.println("ajout de la piece : " + p.toString());
            }
           
            pst.close();
            cnx.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
		this.vertices.add(p);
		return p;
	}

	/**
	 * Enlève une pièce, effet cascade sur tous les passages comportant cette pièce et 
	 * les autres pièces de ces passages.
	 * @param  p1 une pièce
	 * @return   vrai si la pièce était bien présente, faux sinon.
	 */
	@Override
	public boolean removePiece(Piece p1){
		Objects.requireNonNull(p1,"On ne peut pas enlever une pièce null.");
		Piece p2;
		if (this.vertices.remove(p1)){//on enlève le sommet de l'univers
			for (Direction d1 : Direction.values()){
            	if (p1.getPassage(d1) != null){//pour tout couloir qui sort
            		// On doit trouver la porte à murer dans l'autrePiece
            		p2 = p1.getPassage(d1).getAutrePiece(p1);// p1 -- p2
            		for (Direction d2 : Direction.values()){
            			if(p2.getPassage(d2)!= null && p2.getPassage(d2).equals(p1.getPassage(d1))){
            				p2.removePassage(d2);// murer le passage dans p2.
            				break;
            			}
            		}
            		// on peut enlever l'arête de l'univers
            		this.edges.remove(p1.getPassage(d1));
            		// on peut enfin murer la porte dans la pièce.
            		p1.removePassage(d1);
            	}
            }	
        	return true;
		}
		else return false;
	}

	/**
	 * Enlève un passage.
	 * @param  p passage à enlever
	 * @return    vrai ssi le passage devait être enlevé.
	 */
	@Override
	public boolean removePassage(Passage p){
		throw new UnsupportedOperationException("Désolé, fonction non supportée.");
	}

	/**
	 * Ajoute et retourne un nouveau passage
	 * La méthode ne vérifie pas que les directions sont compatibles.
	 * @param  d1 direction d'ajout dans la pièce p1 (normalement libre)
	 * @param  p1 pièce existante, qui n'est pas null
	 * @param  d2 direction d'ajout dans la pièce p1 (normalement libre)
	 * @param  p2 pièce existante, qui n'est pas null
	 * @return    un nouveau passage depuis p1 dans la direction d1 vers la pièce p2 dans la direction d2.
	 * @throws NullPointerException si un argument est null
	 * @throws IllegalArgumentException si la direction d'une pièce n'est pas libre ou si les pièces sont identiques
	 */
	@Override
	public Passage newPassage(Direction d1, Piece p1, Direction d2, Piece p2){
		Objects.requireNonNull(p1,"La pièce p1 ne peut être null.");
		Objects.requireNonNull(d1,"La direction d1 ne peut être null.");
		Objects.requireNonNull(p2,"La pièce p2 ne peut être null.");
		Objects.requireNonNull(d2,"La direction d2 ne peut être null.");
		if (p1.getPassage(d1) != null)
			throw new IllegalArgumentException("La direction d1 n'est pas libre dans la pièce p1");
		if (p2.getPassage(d2) != null)
			throw new IllegalArgumentException("La direction d2 n'est pas libre dans la pièce p2");
		Passage p = new PassageP(p1,p2);//throws IllegalArgumentException si pièces identiques
		p1.setPassage(d1,p);
		p2.setPassage(d2,p);
		this.edges.add(p);

		String passageNom = "";
		try{
            Class.forName("org.mariadb.jdbc.Driver");
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
        }

        try{
            Connection cnx = DriverManager.getConnection("jdbc:mariadb://dwarves.iut-fbleau.fr/minguetp", "minguetp", "minguetp");
            PreparedStatement pst = cnx.prepareStatement("SELECT passageNom FROM API_Passage WHERE passageId = (SELECT MIN(passageId) FROM API_Passage WHERE passageLoad IS NULL)");
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
            	passageNom = rs.getString(1);
            	//System.out.println("\nadresse récupérée : " + passageNom);
            	if(passageNom == null){
            		//System.out.println("===============Pas de nom de passage===============");
	            	this.insertPassageBD(p, d1, p1, d2, p2);
	            	//System.out.println("nouvelle adresse : " + p.toString());
	            }else{
	            	//System.out.println("===============Passage déjà existant===============");
	            	this.updatePassageBD(p, d1, p1, d2, p2, passageNom);
	            	//System.out.println("nouvelle adresse : " + p.toString());
	            }
            }else{
            	//System.out.println("===============Pas de passage existant===============");
				this.insertPassageBD(p, d1, p1, d2, p2);
            	//System.out.println("ajout du passage : " + p.toString());
            }

            rs.close();
            pst.close();
            cnx.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }

        //System.out.println("nouveau passage depuis : " + d1.toString() + " pièce : " + this.getPieceBD(p1) + " vers : " + d2.toString() + " pièce : " + this.getPieceBD(p2));

		return p;
	}

	private int getPieceBD(Piece p){
		int piece = 0;
		try{
            Class.forName("org.mariadb.jdbc.Driver");
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
        }

        try{
            Connection cnx = DriverManager.getConnection("jdbc:mariadb://dwarves.iut-fbleau.fr/minguetp", "minguetp", "minguetp");
			PreparedStatement pst2 = cnx.prepareStatement("SELECT pieceId FROM API_Piece WHERE pieceNom = ?");
	    	pst2.setString(1, p.toString());
	    	ResultSet rs2 = pst2.executeQuery();
	    	rs2.next();
	    		piece = rs2.getInt(1);
	    	rs2.close();
	        pst2.close();
	    }
	    catch(SQLException e){
            e.printStackTrace();
        }
    	return piece;
	}

	private void insertPieceBD(Piece p){
		try{
            Class.forName("org.mariadb.jdbc.Driver");
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
        }

        try{
        	Connection cnx = DriverManager.getConnection("jdbc:mariadb://dwarves.iut-fbleau.fr/minguetp", "minguetp", "minguetp");
			PreparedStatement pst = cnx.prepareStatement("INSERT INTO API_Piece (pieceNom, pieceLoad) Values (?, ?)");
        	pst.setString(1, p.toString());
        	pst.setInt(2, 1);
        	pst.executeUpdate();
    	}
	    catch(SQLException e){
            e.printStackTrace();
        }
	}

	private void updatePieceBD(Piece p, String pieceNom){
		try{
            Class.forName("org.mariadb.jdbc.Driver");
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
        }

        try{
        	Connection cnx = DriverManager.getConnection("jdbc:mariadb://dwarves.iut-fbleau.fr/minguetp", "minguetp", "minguetp");
			PreparedStatement pst = cnx.prepareStatement("UPDATE API_Piece SET pieceNom = ?, pieceLoad = 1 WHERE pieceNom = ?");
        	pst.setString(1, p.toString());
        	pst.setString(2, pieceNom);
        	pst.executeUpdate();
    	}
	    catch(SQLException e){
            e.printStackTrace();
        }
	}

	private void insertPassageBD(Passage p, Direction d1, Piece p1, Direction d2, Piece p2){
		try{
            Class.forName("org.mariadb.jdbc.Driver");
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
        }

        try{
        	Connection cnx = DriverManager.getConnection("jdbc:mariadb://dwarves.iut-fbleau.fr/minguetp", "minguetp", "minguetp");
			PreparedStatement pst = cnx.prepareStatement("INSERT INTO API_Passage (passageNom, passageLoad, passageDirection1, passagePiece1, passageDirection2, passagePiece2) Values (?, ?, ?, ?, ?, ?)");
	    	pst.setString(1, p.toString());
	    	pst.setInt(2, 1);
	    	pst.setString(3, d1.toString());
	        pst.setInt(4, this.getPieceBD(p1));
	        pst.setString(5, d2.toString());
	        pst.setInt(6, this.getPieceBD(p2));
	    	pst.executeUpdate();
    	}
	    catch(SQLException e){
            e.printStackTrace();
        }
	}

	private void updatePassageBD(Passage p, Direction d1, Piece p1, Direction d2, Piece p2, String psgNom){
		String passageNom = psgNom;
		try{
            Class.forName("org.mariadb.jdbc.Driver");
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
        }

        try{
        	Connection cnx = DriverManager.getConnection("jdbc:mariadb://dwarves.iut-fbleau.fr/minguetp", "minguetp", "minguetp");
			PreparedStatement pst = cnx.prepareStatement("UPDATE API_Passage SET passageNom = ?, passageDirection1 = ?, passagePiece1 = ?, passageDirection2 = ?, passagePiece2 = ?, passageLoad = 1 WHERE passageNom = ?");
        	pst.setString(1, p.toString());
        	pst.setString(2, d1.toString());
            pst.setInt(3, this.getPieceBD(p1));
            pst.setString(4, d2.toString());
            pst.setInt(5, this.getPieceBD(p2));
            pst.setString(6, passageNom);
        	pst.executeUpdate();
    	}
	    catch(SQLException e){
            e.printStackTrace();
        }
	}

}
