package fr.iutfbleau.projetIHM2020FI2.MP;
import fr.iutfbleau.projetIHM2020FI2.API.*;
import java.util.*;
import java.sql.*;
/**
 * Implémentation non persistante d'un Joueur
 */
public class JoueurP extends ContientTrucsP implements Joueur{

    //piece actuelle
    private Piece p;

    // conteneur de Pièces.
    private List<Piece> cerveau;
    
    /**
    * Constructeur
    * 
    * On utilise un ensemble LinkedList pour gérer la chronologie.
    *
    * Le joueur n'est pas dans une pièce à sa création.
    * @see setPiece() 
    */
    public JoueurP(){
        super();
        this.cerveau = new LinkedList<Piece>();
        try{
            Class.forName("org.mariadb.jdbc.Driver");
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
        }

        try{
            Connection cnx = DriverManager.getConnection("jdbc:mariadb://dwarves.iut-fbleau.fr/minguetp", "minguetp", "minguetp");
            PreparedStatement pst = cnx.prepareStatement("SELECT joueurId FROM API_Joueur");
            ResultSet rs = pst.executeQuery();
            if(rs.next()){

            }else{
                pst = cnx.prepareStatement("INSERT INTO API_Joueur (joueurPiece) VALUES (?)");
                pst.setInt(1, 1);
                pst.executeUpdate();
            }
            rs.close();
            pst.close();
            cnx.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }


   /** 
     * @return la piece dans laquelle le joueur se trouve
     * (null si la pièce n'est pas renseignée)
    */
   @Override
    public Piece getPiece(){
    	return this.p;
    }
	
	/** 
     * Met à jour la piece dans laquelle le joueur se trouve
     * Demande l'ajout de l'ancienne pièce au "cerveau".
     * NB. il n'y a pas de vérification dans Joueur.
    */
   @Override
    public void setPiece(Piece next){
    	if (this.getPiece() != null) 
            this.addVisited(this.getPiece());
    	this.p=next;

        int pieceUpdate = 0;

        try{
            Class.forName("org.mariadb.jdbc.Driver");
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
        }

        try{
            Connection cnx = DriverManager.getConnection("jdbc:mariadb://dwarves.iut-fbleau.fr/minguetp", "minguetp", "minguetp");
            PreparedStatement pst = cnx.prepareStatement("UPDATE API_Joueur SET joueurPiece = (SELECT pieceId FROM API_Piece WHERE pieceNom = ?)");
            pst.setString(1, next.toString());
            pst.executeUpdate();

            pst = cnx.prepareStatement("INSERT INTO API_PieceVisitee (joueurId, pieceId) VALUES (?, ?)");
            pst.setInt(1, 1);

            PreparedStatement pst2 = cnx.prepareStatement("SELECT pieceId FROM API_Piece WHERE pieceNom = ?");
            pst2.setString(1, next.toString());
            ResultSet rs = pst2.executeQuery();
            while(rs.next()){
                pieceUpdate = rs.getInt(1);
            }
            pst.setInt(2, pieceUpdate);
            pst.executeUpdate();

            pst2.close();
            pst.close();
            cnx.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        System.out.println("Déplacement du joueur dans la piece : " + pieceUpdate);
    }
    
    /**
     * @return les pieces que le joueur a deja visité.
     */
    @Override
    public Iterator<Piece> getVisited(){
    	return this.cerveau.iterator();
    }
    
    /**
     * Ajoute la Piece si nécessaire (si l'itérateur retourné par 
     * getVisited ne permet pas d'itérer sur la Piece).
     * Une pièce devient visitée quand on la quitte.
     * @param  p Pièce qu'on vient de (re)visiter
     * @throws NullPointerException la Piece ne peut pas être null
     * @return vrai si il fallait l'ajouter et faux sinon.
     */
    @Override
    public boolean addVisited(Piece p){
    	Objects.requireNonNull(p,"On ne peut pas ajouter une pièce null.");
        int pieceUpdate = 0;
        int pv=0;
        try{
            Class.forName("org.mariadb.jdbc.Driver");
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
        }

        try{
            Connection cnx = DriverManager.getConnection("jdbc:mariadb://dwarves.iut-fbleau.fr/minguetp", "minguetp", "minguetp");
            PreparedStatement pst = cnx.prepareStatement("SELECT pieceId FROM API_Piece WHERE pieceNom = ?");
            pst.setString(1, p.toString());
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                pieceUpdate = rs.getInt(1);
            }
            pst.setInt(1, pieceUpdate);
            pst.executeUpdate();
            pst = cnx.prepareStatement("SELECT MIN(pvId) FROM API_PieceVisitee WHERE pvLoad IS NULL");
            rs = pst.executeQuery();
            if(rs.next()){
                pv = rs.getInt(1);
                if(pv != 1){
                    this.insertPieceVisiteeBD(pieceUpdate);
                }else{
                    this.updatePieceVisiteeBD(pieceUpdate, pv);
                }
            }else{
                this.insertPieceVisiteeBD(pieceUpdate);
            }
           
            pst.close();
            cnx.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        try{
            Class.forName("org.mariadb.jdbc.Driver");
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
        }
    	return this.cerveau.add(p);
    }
    
    /**
 	* Teste si piece a été visitée
 	* @param  p pièce
 	* @throws NullPointerException si la pièce est null 
 	* @return vrai si la pièce est connue.
 	*/
    @Override
    public boolean isVisited(Piece p){
    	Objects.requireNonNull(p,"On ne peut pas connaître une pièce null.");
    	return this.cerveau.contains(p);
    }
	
	/**
     * Accesseur pour la description textuelle du sac à dos.
     * @return description
     */
    @Override
    public String getDescription(){
        // Stringbuilder is the most efficient method of building a String like datastructure incrementally. 
        StringBuilder sb = new StringBuilder("");
        int goodies = super.combienTrucs();
        if (goodies == 0)
        	sb.append("\n"+"Le sac à dos ne contient pas d'objet. ");
        else if (goodies == 1)	
        	sb.append("\n"+"Le sac à dos contient un objet : ");
        else sb.append("\n"+ "Le sac à dos contient "+ super.combienTrucs()+" objets : ");
        Iterator<Truc> goods = super.getTrucs();
        while (goods.hasNext()){
        	Truc t = goods.next();
        	sb.append("\n _ " + t.getDescription() +".");
        }
        sb.append("\n");

        return sb.toString();
    }
    /**
     * Le joueur non persistent n'est pas tout à fait comme le buveur du petit prince.
     * Il ne boit pas du rhum pour oublier qu'il en boit mais pour oublier les pièces qu'il connaît.
     * @param  t un truc que le joueur doit posséder
     * @throws IllegalStateException  sinon
     * @return vrai si l'objet a un effet sur le joueur
     */
    @Override
    public boolean agir (Truc t){
    	if (t == null){
    		return false;
    	}
    	else if (!super.containsTruc(t))
    		throw new IllegalStateException("le joueur ne porte pas l'objet");
        else if (Objects.equals(t.getTypeTruc(),TypeTruc.ALCOOL)){
        	super.removeTruc(t);
        	this.cerveau.clear();
        	return true;
        }
        else if (Objects.equals(t.getTypeTruc(),TypeTruc.EAU)){
        	super.removeTruc(t);
        	return true;
        }
        else return false;
    }

    private void insertPieceVisiteeBD(int pvId){
        try{
            Class.forName("org.mariadb.jdbc.Driver");
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
        }

        try{
            Connection cnx = DriverManager.getConnection("jdbc:mariadb://dwarves.iut-fbleau.fr/minguetp", "minguetp", "minguetp");
            PreparedStatement pst = cnx.prepareStatement("INSERT INTO API_PieceVisitee (joueurId, pieceId, pvLoad) Values (1, ?, 1)");
            pst.setInt(1, pvId);
            pst.executeUpdate();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    private void updatePieceVisiteeBD(int p, int pvId){
        try{
            Class.forName("org.mariadb.jdbc.Driver");
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
        }

        try{
            Connection cnx = DriverManager.getConnection("jdbc:mariadb://dwarves.iut-fbleau.fr/minguetp", "minguetp", "minguetp");
            PreparedStatement pst = cnx.prepareStatement("UPDATE API_PieceVisitee SET pieceId = ?, pvLoad = 1 WHERE pieceId = ?");
            pst.setInt(1, p);
            pst.setInt(2, pvId);
            pst.executeUpdate();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
}
