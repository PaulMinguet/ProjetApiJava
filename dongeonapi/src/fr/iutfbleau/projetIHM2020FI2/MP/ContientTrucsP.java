package fr.iutfbleau.projetIHM2020FI2.MP;
import fr.iutfbleau.projetIHM2020FI2.API.*;
import java.util.*;
import java.sql.*;

/**
 * Implémentation non persistante.
 * Il s'agit en fait à peu de chose près d'une façade sur Set<Truc>.
 */
public class ContientTrucsP implements ContientTrucs{

    /**
     * Conformément aux recommandations de la documentation de l'interface, il faut un ensemble.
     */
	private Set<Truc> contenu;

    /**
     * Constructeur.
     * On utilise un LinkedHashSet qui permet de garantir que l'ordre des trucs reste le même.
     */
    public ContientTrucsP(){
        this.contenu= new LinkedHashSet<Truc>();	
    }
   
    /**
     * permet d'accéder aux trucs.
     * @return Iterator sur les trucs.
     */
    @Override
    public Iterator<Truc> getTrucs(){
    	return this.contenu.iterator();
    }
    
    /**
     * Ajoute le truc si nécessaire
     * @param  t Truc à ajouter
     * @return   vrai si il fallait l'ajouter et faux sinon.
     * @throws NullPointerException si t est null
     */
    @Override
    public boolean addTruc(Truc t){
    	Objects.requireNonNull(t,"On ne peut pas ajouter un truc null.");

        System.out.println(this.getClass().getName());

        int id = 0;
        int ctid = 0;

        //System.out.println(this.toString());

        try{
            Class.forName("org.mariadb.jdbc.Driver");
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
        }

        try{
            if(this.getClass().getName() == "fr.iutfbleau.projetIHM2020FI2.MP.PieceP"){
                Connection cnx = DriverManager.getConnection("jdbc:mariadb://dwarves.iut-fbleau.fr/minguetp", "minguetp", "minguetp");
                PreparedStatement pst = cnx.prepareStatement("SELECT ctId FROM API_ContientTrucs WHERE ctId = (SELECT MIN(ctId) FROM API_ContientTrucs WHERE ctLoad IS NULL)");
                ResultSet rs = pst.executeQuery();
                if(rs.next()){
                    ctid = rs.getInt(1);
                    //System.out.println("UUUUUPPPPPPPPPPDDDDDDDDDDDDDAAAAAAATTTTTTEEEEEEEEEEEEEE");
                    this.updateCTBD(t, ctid);
                }else{
                    //System.out.println("IIIIINNNNNNNSEEEEEERRRRTTTTTTTTTTTT");
                    this.insertCTBD(t);
                }
               
                pst.close();
                cnx.close();
            }else{
                Connection cnx = DriverManager.getConnection("jdbc:mariadb://dwarves.iut-fbleau.fr/minguetp", "minguetp", "minguetp");
                PreparedStatement pst = cnx.prepareStatement("SELECT ptId FROM API_PossedeTruc WHERE ptId = (SELECT MIN(ptId) FROM API_PossedeTruc WHERE ptLoad IS NULL)");
                ResultSet rs = pst.executeQuery();
                if(rs.next()){
                    ctid = rs.getInt(1);
                    //System.out.println("UUUUUPPPPPPPPPPDDDDDDDDDDDDDAAAAAAATTTTTTEEEEEEEEEEEEEE");
                    this.updatePTBD(t, ctid);
                }else{
                    //System.out.println("IIIIINNNNNNNSEEEEEERRRRTTTTTTTTTTTT");
                    this.insertPTBD(t);
                }
               
                pst.close();
                cnx.close();
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }

        return this.contenu.add(t);
    }

    private void insertCTBD(Truc t){
        int id = 0;
        try{
            Class.forName("org.mariadb.jdbc.Driver");
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
        }

        try{
            Connection cnx = DriverManager.getConnection("jdbc:mariadb://dwarves.iut-fbleau.fr/minguetp", "minguetp", "minguetp");
            PreparedStatement pst = cnx.prepareStatement("INSERT INTO API_ContientTrucs (pieceId, trucId, ctLoad) VALUES (?, ?, 1)");
            PreparedStatement pst2 = cnx.prepareStatement("SELECT pieceId FROM API_Piece WHERE pieceNom = ?");
            pst2.setString(1,this.toString());
            ResultSet rs = pst2.executeQuery();
            while(rs.next()){
                id = rs.getInt(1);
            }
            pst.setInt(1, id);
            pst2 = cnx.prepareStatement("SELECT trucId FROM API_Truc WHERE trucNom = ?");
            pst2.setString(1,t.toString());
            rs = pst2.executeQuery();
            while(rs.next()){
                id = rs.getInt(1);
            }
            pst.setInt(2, id);
            pst2.close();
            rs.close();
            pst.executeUpdate();
            pst.close();
            cnx.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    private void updateCTBD(Truc t, int ctid){
        int id = 0;
        try{
            Class.forName("org.mariadb.jdbc.Driver");
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
        }

        try{
            Connection cnx = DriverManager.getConnection("jdbc:mariadb://dwarves.iut-fbleau.fr/minguetp", "minguetp", "minguetp");
            PreparedStatement pst = cnx.prepareStatement("UPDATE API_ContientTrucs SET pieceId = ?, trucId = ?, ctLoad = 1 WHERE ctId = ?");
            PreparedStatement pst2 = cnx.prepareStatement("SELECT pieceId FROM API_Piece WHERE pieceNom = ?");
            pst2.setString(1,this.toString());
            ResultSet rs = pst2.executeQuery();
            while(rs.next()){
                id = rs.getInt(1);
            }
            pst.setInt(1, id);
            pst2 = cnx.prepareStatement("SELECT trucId FROM API_Truc WHERE trucNom = ?");
            pst2.setString(1,t.toString());
            rs = pst2.executeQuery();
            while(rs.next()){
                id = rs.getInt(1);
            }
            pst.setInt(2, id);
            pst2.close();
            rs.close();
            pst.setInt(3, ctid);
            pst.executeUpdate();
            pst.close();
            cnx.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    private void insertPTBD(Truc t){
        int id = 0;
        try{
            Class.forName("org.mariadb.jdbc.Driver");
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
        }

        try{
            Connection cnx = DriverManager.getConnection("jdbc:mariadb://dwarves.iut-fbleau.fr/minguetp", "minguetp", "minguetp");
            PreparedStatement pst = cnx.prepareStatement("INSERT INTO API_PossedeTruc (joueurId, trucId, ptLoad) VALUES (1, ?, 1)");
            PreparedStatement pst2 = cnx.prepareStatement("SELECT trucId FROM API_Truc WHERE trucNom = ?");
            pst2.setString(1,t.toString());
            ResultSet rs = pst2.executeQuery();
            while(rs.next()){
                id = rs.getInt(1);
            }
            pst.setInt(1, id);
            rs.close();
            pst.executeUpdate();
            pst.close();
            cnx.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    private void updatePTBD(Truc t, int ptid){
        int id = 0;
        try{
            Class.forName("org.mariadb.jdbc.Driver");
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
        }

        try{
            Connection cnx = DriverManager.getConnection("jdbc:mariadb://dwarves.iut-fbleau.fr/minguetp", "minguetp", "minguetp");
            PreparedStatement pst = cnx.prepareStatement("UPDATE API_PossedeTruc SET joueurId = 1, trucId = ?, ptLoad = 1 WHERE ptId = ?");
            PreparedStatement pst2 = cnx.prepareStatement("SELECT trucId FROM API_Truc WHERE trucNom = ?");
            pst2.setString(1,t.toString());
            ResultSet rs = pst2.executeQuery();
            while(rs.next()){
                id = rs.getInt(1);
            }
            pst.setInt(1, id);
            pst2.close();
            rs.close();
            pst.setInt(2, ptid);
            pst.executeUpdate();
            pst.close();
            cnx.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * Enlève le truc si nécessaire
     * @param  t Truc à enleverer
     * @return   vrai si il fallait l'enlever et faux sinon.
     * @throws NullPointerException si t est null
     */
    @Override
    public boolean removeTruc(Truc t){
    	Objects.requireNonNull(t,"On ne peut pas enlever un truc null.");
        try{
            Class.forName("org.mariadb.jdbc.Driver");
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
        }

        try{
            if(this.getClass().getName() == "fr.iutfbleau.projetIHM2020FI2.MP.PieceP"){
                Connection cnx = DriverManager.getConnection("jdbc:mariadb://dwarves.iut-fbleau.fr/minguetp", "minguetp", "minguetp");
                PreparedStatement pst = cnx.prepareStatement("DELETE FROM API_ContientTrucs WHERE trucId = (SELECT trucId FROM API_Truc WHERE trucNom = ?)");
                pst.setString(1, t.toString());
                pst.executeUpdate();
                pst.close();
                cnx.close();
            }else{
                Connection cnx = DriverManager.getConnection("jdbc:mariadb://dwarves.iut-fbleau.fr/minguetp", "minguetp", "minguetp");
                PreparedStatement pst = cnx.prepareStatement("DELETE FROM API_PossedeTruc WHERE trucId = (SELECT trucId FROM API_Truc WHERE trucNom = ?)");
                pst.setString(1, t.toString());
                pst.executeUpdate();
                pst.close();
                cnx.close();
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    	return this.contenu.remove(t);
    }

    /**
     * test d'appartenance
     * @param  t Truc dont il faut tester l'appartenance 
     * @throws NullPointerException si  null.
     * @return vrai ssi le truc est contenu
     */
    @Override
    public boolean containsTruc(Truc t){
    	Objects.requireNonNull(t,"On ne peut pas contenir un truc null");
        boolean resultat = true;
        try{
            Class.forName("org.mariadb.jdbc.Driver");
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
        }

        try{
            if(this.getClass().getName() == "fr.iutfbleau.projetIHM2020FI2.MP.PieceP"){
                Connection cnx = DriverManager.getConnection("jdbc:mariadb://dwarves.iut-fbleau.fr/minguetp", "minguetp", "minguetp");
                PreparedStatement pst = cnx.prepareStatement("SELECT ctId FROM API_ContientTrucs WHERE trucId = (SELECT trucId FROM API_Truc WHERE trucNom = ?)");
                pst.setString(1, t.toString());
                ResultSet rs = pst.executeQuery();
                if(rs.next()){
                    resultat = true;
                }else{
                    resultat = false;
                }
                pst.close();
                cnx.close();
            }else{
                Connection cnx = DriverManager.getConnection("jdbc:mariadb://dwarves.iut-fbleau.fr/minguetp", "minguetp", "minguetp");
                PreparedStatement pst = cnx.prepareStatement("SELECT ptId FROM API_PossedeTruc WHERE trucId = (SELECT trucId FROM API_Truc WHERE trucNom = ?)");
                pst.setString(1, t.toString());
                ResultSet rs = pst.executeQuery();
                if(rs.next()){
                    resultat = true;
                }else{
                    resultat = false;
                }
                pst.close();
                cnx.close();
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }

    	return resultat;
    }  
   
    /**
     * pour obtenir le nombre de trucs contenus.
     * @return un entier positif ou nul.
     */
    @Override
    public int combienTrucs(){
    	return this.contenu.size();
    }

}
