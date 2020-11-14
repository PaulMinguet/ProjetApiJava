package fr.iutfbleau.projetIHM2020FI2.MP;
import fr.iutfbleau.projetIHM2020FI2.API.*;
import java.util.*;
import java.sql.*;
/**
 * Permet de modéliser un conteneur d'objets (qu'on appelle des trucs pour bien faire la différence avec Object).
 * Ce conteneur permet de créer des trucs.
 * 
 * @see Joueur, Piece, Truc
 */
public class TrucFactoryP implements TrucFactory {

    // univers de tous les trucs
    private ContientTrucs univers = new ContientTrucsP();

     /**
     * permet d'accéder aux trucs.
     */
    public Iterator<Truc> getTrucs(){
        return this.univers.getTrucs();
    }

    /**
     * Le truc ne peut pas être null sinon la méthode lève une NullPointerException
     * Ajoute le truc si nécessaire.
     * retourne vrai si il fallait l'ajouter et faux sinon.
     */
    public boolean addTruc(Truc t){
        return this.univers.addTruc(t);
    }

    /**
     * Le truc ne peut pas être null sinon la méthode lève une NullPointerException
     * Enlève le truc si nécessaire.
     * retourne vrai si on pouvait l'enlever et faux sinon.
     */
    public boolean removeTruc(Truc t){
        return this.univers.removeTruc(t);
    }
    
    /**
     * test d'appartenance
     * @param  t ne peut pas être null sinon lève une NullPointerException
     * @return vrai ssi le truc est contenu
     */
    public boolean containsTruc(Truc t){
        return this.univers.containsTruc(t);
    }

    /**
     * le nombre de trucs contenus dans le sac à dos du joueur
     * @return un entier positif ou nul.
     */
    public int combienTrucs(){
        return this.univers.combienTrucs();
    }
    
    /**
     * ajoute et retourne un nouvel objet (on délègue à TrucP)
     * @param  tt          type de l'objet
     * @param  description sa description
     * @return Truc        le nouvel objet
     */
    public Truc newTruc(TypeTruc tt, String description){
        Truc t = new TrucP(tt,description);
        //this.addTruc(t);    //========================================================================================================================

        System.out.println("nouveau truc : " + tt.toString() + "; description : " + description);

        String trucNom = "";
        try{
            Class.forName("org.mariadb.jdbc.Driver");
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
        }

        try{
            Connection cnx = DriverManager.getConnection("jdbc:mariadb://dwarves.iut-fbleau.fr/minguetp", "minguetp", "minguetp");
            PreparedStatement pst = cnx.prepareStatement("SELECT trucNom FROM API_Truc WHERE trucId = (SELECT MIN(trucId) FROM API_Truc WHERE trucLoad IS NULL)");
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                trucNom = rs.getString(1);
                //System.out.println("\nadresse truc récupérée : " + trucNom);
                if(trucNom == null){
                    //System.out.println("===============Pas de nom de truc===============");
                    this.insertTrucBD(t, tt, description);
                    //System.out.println("nouvelle adresse truc : " + t.toString());
                }else{
                    //System.out.println("===============Truc déjà existant===============");
                    this.updateTrucBD(t, tt, description, trucNom);
                    //System.out.println("nouvelle adresse truc : " + t.toString());
                }
            }else{
                //System.out.println("===============Pas de truc existant===============");
                this.insertTrucBD(t, tt, description);
                //System.out.println("ajout du truc : " + t.toString());
            }
           
            pst.close();
            cnx.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return t;
    }

    private void insertTrucBD(Truc t, TypeTruc tt, String description){
        try{
            Class.forName("org.mariadb.jdbc.Driver");
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
        }

        try{
            Connection cnx = DriverManager.getConnection("jdbc:mariadb://dwarves.iut-fbleau.fr/minguetp", "minguetp", "minguetp");
            PreparedStatement pst = cnx.prepareStatement("INSERT INTO API_Truc (trucNom, trucType, trucTypeId, trucDesc, trucLoad) Values (?, ?, ?, ?, ?)");
            pst.setString(1, t.toString());
            pst.setString(2, tt.toString());
            if(tt.toString().equals("alcool"))
                pst.setInt(3, 1);
            else if(tt.toString().equals("eau"))
                pst.setInt(3, 3);
            else if(tt.toString().equals("clé"))
                pst.setInt(3, 2);
            else
                pst.setInt(3, 4);
            pst.setString(4, description);
            pst.setInt(5, 1);
            pst.executeUpdate();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    private void updateTrucBD(Truc t, TypeTruc tt, String description, String trucNom){
        try{
            Class.forName("org.mariadb.jdbc.Driver");
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
        }

        try{
            Connection cnx = DriverManager.getConnection("jdbc:mariadb://dwarves.iut-fbleau.fr/minguetp", "minguetp", "minguetp");
            PreparedStatement pst = cnx.prepareStatement("UPDATE API_Truc SET trucNom = ?, trucType = ?, trucTypeId = ?, trucDesc = ?, trucLoad = 1 WHERE trucNom = ?");
            pst.setString(1, t.toString());
            pst.setString(2, tt.toString());
            if(tt.toString().equals("alcool"))
                pst.setInt(3, 1);
            else if(tt.toString().equals("eau"))
                pst.setInt(3, 3);
            else if(tt.toString().equals("clé"))
                pst.setInt(3, 2);
            else
                pst.setInt(3, 4);
            pst.setString(4, description);
            pst.setString(5, trucNom.toString());
            pst.executeUpdate();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
}
