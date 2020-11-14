package fr.iutfbleau.projetIHM2020FI2.API;
import java.util.*;
/**
 * Usine abstraite pour créer les objets du jeu (qu'on appelle des trucs pour bien faire la différence avec Object).
 * 
 * Permet de modéliser un conteneur de tous les trucs. (pas forcément nécessaire).
 * Ce conteneur est ausi une usine permettant de créer des trucs.
 * 
 * @see Joueur, Piece, Truc
 */
public interface TrucFactory extends ContientTrucs {

	/**
	 * ajoute et retourne un nouvel objet
	 * @param  tt          type de l'objet
	 * @param  description sa description
	 * @return Truc        le nouvel objet
	 */
   	public Truc newTruc(TypeTruc tt, String description);
}
