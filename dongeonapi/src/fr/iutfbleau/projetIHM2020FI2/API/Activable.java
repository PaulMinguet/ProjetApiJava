package fr.iutfbleau.projetIHM2020FI2.API;
/**
 * Une classe doit implémenter cette interface pour que le joueur puisse interagir avec ses instances.
 * @see Joueur, Truc
 */
public interface Activable{

	 /**
	 * Méthode permettant de modéliser une action du joueur (du jeu pas du modèle).
     * Toute classe qui implémente cette interface peut être cible de l'action du joueur.
     * 
     * Un truc en paramètre qui est null correspond à une action du joueur sans 
     * l'aide d'un truc particulier.
     *
     * NB. La classe cible (par exemple Passage) ne peut pas forcément vérifier que 
     * le joueur (du modèle) possède l'objet passé en paramètre.
     * Le controleur devra donc prendre en charge ce travail de vérification.
     *       
     * @param  t est un truc que le joueur devrait posséder
     * @throws IllegalStateException  sinon
     * @return vrai si l'objet a un effet
     *
     * @see Joueur
     * @see Passage
     */
   	public boolean agir (Truc t);
}
