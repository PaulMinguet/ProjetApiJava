package fr.iutfbleau.projetIHM2020FI2.API;
import java.util.*;
/**
 * Un joueur est dans une pièce.
 * Un joueur connaît les pièces qu'il a visité.
 * Un joueur possède un sac à dos qui contient des objets (qu'on appelle des trucs pour bien faire la différence avec Object).
 * Certains trucs pourraient avoir un effet sur le joueur.
 */
public interface Joueur extends Activable, ContientTrucs, Descriptible{
    
    /** 
     * @return la piece dans laquelle le joueur se trouve
    */
    public Piece getPiece();
    
    /** 
     * Met à jour la piece dans laquelle le joueur se trouve
    */
    public void setPiece(Piece next);

    /**
     * L'itérateur retourne les pièces dans l'ordre chronologique 
     * des visites. Il peut donc itérer plusieurs foir sur une même 
     * pièce.
     * 
     * @return les pieces que le joueur a deja visité.
     */
    public Iterator<Piece> getVisited();
    
    /**
     * Ajoute la Piece à la liste des pièces visitées.
     * NB : Une pièce devient visitée quand on la quitte.
     * 
     * @param  p Pièce qu'on vient de (re)visiter
     * @throws NullPointerException si p est null
     * @return vrai si il fallait l'ajouter et faux sinon.
     */
    public boolean addVisited(Piece p);

	/**
 	* Teste si piece a été visitée
 	* @param  p pièce
 	* @throws NullPointerException si la pièce est null 
 	* @return vrai si la pièce est connue.
 	*/
    public boolean isVisited(Piece p);

}
