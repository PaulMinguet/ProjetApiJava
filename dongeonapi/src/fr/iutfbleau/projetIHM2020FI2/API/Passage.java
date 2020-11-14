package fr.iutfbleau.projetIHM2020FI2.API;
import java.util.*;
// Un passage relie deux pièces.
// Le passage est ouvert, fermé ou fermé à clé.
// NB : Ce sont les pièces qui gèrent les directions.
public interface Passage extends Activable, Descriptible {

    /**
     * Normalement les deux pièces sont différentes. On donne une piece pour obtenir la piece de l'autre côté du passage.
     * @param  p pièce
     * @throws IllegalArgumentException si le passage ne relie pas p à une autre pièce
     * @return l'autre pièce que le passage relie à p.
     */
    public Piece getAutrePiece(Piece p);

    /**
     * Normalement un passage relie deux pièces distinctes. 
     * L'ordre des pièces est arbitraire et pourrait changer d'un appel à l'autre.
     * @return un itérateur sur les pièces du passage.
     */
    public Iterator<Piece> getPieces();

    /**
     * teste si la pièce est liée à une autre pièce par ce passage
     * @param  p pièce à tester
     * @return vrai ssi c'est le cas.
     */
    public boolean reliePiece(Piece p);

    /**
     * pour savoir si le passage est ouvert, fermé ou fermé à clé.
     * @return l'état du passage.
     */
    public EtatPassage getEtatPassage();

    /**
     * change l'état du passage
     * e ne doit pas être null 
     * @param e 
     * @throws NullPointerException
     */
    public void setEtatPassage(EtatPassage e);

    default public String getDescription(){
        return "passage "+ this.getEtatPassage().getDescription();
    }
    
}
