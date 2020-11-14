package fr.iutfbleau.projetIHM2020FI2.API;
/**
 * Un objet pouvant être dans une pièce ou porté par le joueur
 */

public interface Truc extends Descriptible, Activable {

    public TypeTruc getTypeTruc();

    /** 
     * par défaut un truc ne sert à rien sur un autre truc
     */
    default public boolean agir (Truc t){
        return false;
    }

}
