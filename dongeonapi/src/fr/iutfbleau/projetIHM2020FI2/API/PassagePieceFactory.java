package fr.iutfbleau.projetIHM2020FI2.API;
import java.util.*;
/**
 * Cette interface est une usine abstraite.
 * 
 *  Elle permet de gérer la topologie du dongeon en créant et manipulant des objets 
 * _ pour les pièces (implémentant l'interface Piece) et 
 * _ des objets modélisant les accès d'une pièce à l'autre (instances de classes implémentant l'interface Passage). 
 *
 */
public interface PassagePieceFactory{

	/**
	 * ajoute et retourne une nouvelle pièce.
	 * @return la nouvelle pièce
	 */
	public Piece newPiece();

	/**
	 * Enlève une pièce, effet cascade sur tous les passages comportant cette pièce.
	 * @param  p une pièce
	 * @return   vrai si la pièce était bien présente, faux sinon.
	 */
	public boolean removePiece(Piece p);

	/**
	 * Enlève un passage.
	 * @param  p passage à enlever
	 * @return    vrai ssi le passage devait être enlevé.
	 */
	public boolean removePassage(Passage p);

	/**
	 * Ajoute et retourne un nouveau passage
	 * La méthode ne vérifie pas que les directions sont compatibles.
	 * @param  d1 direction d'ajout dans la pièce p1 (normalement libre)
	 * @param  p1 pièce existante, qui n'est pas null
	 * @param  d2 direction d'ajout dans la pièce p1 (normalement libre)
	 * @param  p2 pièce existante, qui n'est pas null
	 * @return    un nouveau passage depuis p1 dans la direction d1 vers la pièce p2 dans la direction d2.
	 * @throws NullPointerException si un argument est null
	 * @throws IllegalArgumentException si la direction d'une pièce n'est pas libre ou si les pièces sont identiques
	 */
	public Passage newPassage(Direction d1, Piece p1, Direction d2, Piece p2);

}