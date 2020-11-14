package fr.iutfbleau.projetIHM2020FI2.API;
import java.util.*;
/**
 * Permet de modéliser un conteneur d'objets (qu'on appelle des trucs pour bien faire la différence avec Object).
 * Notez que ce conteneur est un ensemble : un truc ne peut pas y être présent plus d'une fois.
 * Vous pouvez par contre avoir 2 trucs ayant exactement les mêmes attributs (une copie de l'autre objet).
 * 
 * @see Joueur, Piece
 */
public interface ContientTrucs {

    /**
     * permet d'accéder aux trucs.
     * @return Iterator sur les trucs.
     */
    public Iterator<Truc> getTrucs();

    /**
     * Ajoute le truc si nécessaire
     * @param  t Truc à ajouter
     * @return   vrai si il fallait l'ajouter et faux sinon.
     * @throws NullPointerException si t est null
     */
    public boolean addTruc(Truc t);

     /**
     * Enlève le truc si nécessaire
     * @param  t Truc à enleverer
     * @return   vrai si il fallait l'enlever et faux sinon.
     * @throws NullPointerException si t est null
     */
    public boolean removeTruc(Truc t);   
    
    /**
     * test d'appartenance
     * @param  t Truc dont il faut tester l'appartenance 
     * @throws NullPointerException si  null.
     * @return vrai ssi le truc est contenu
     */
    public boolean containsTruc(Truc t);

    /**
     * pour obtenir le nombre de trucs contenus.
     * @return un entier positif ou nul.
     */
    public int combienTrucs();
}
