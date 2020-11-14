package fr.iutfbleau.projetIHM2020FI2.MNP;
import fr.iutfbleau.projetIHM2020FI2.API.*;
import java.util.*;
/**
 * Permet de modéliser un conteneur d'objets (qu'on appelle des trucs pour bien faire la différence avec Object).
 * Ce conteneur permet de créer des trucs.
 * 
 * @see Joueur, Piece, Truc
 */
public class TrucFactoryNP implements TrucFactory {
    // univers de tous les trucs
    private ContientTrucsNP univers = new ContientTrucsNP();

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
     * ajoute et retourne un nouvel objet (on délègue à TrucNP)
     * @param  tt          type de l'objet
     * @param  description sa description
     * @return Truc        le nouvel objet
     */
    public Truc newTruc(TypeTruc tt, String description){
        Truc t = new TrucNP(tt,description);
        this.addTruc(t);
        return t;
    }
}
