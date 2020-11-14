package fr.iutfbleau.projetIHM2020FI2.MNP;
import fr.iutfbleau.projetIHM2020FI2.API.*;
import java.util.*;
/**
 * Permet de gérer la topologie du dongeon en créant et manipulant des objets 
 * _ pour les pièces (implémentant l'interface Piece) et 
 * _ des objets modélisant les accès d'une pièce à l'autre (instances de classes implémentant l'interface Passage). 
 * 
 * 
 * Cette classe n'offre pas de gestion persistante des objets.
 * 
 * Cette classe utilise de manière indépendante PieceNP et PassageNP.
 * L'implémentation actuelle est en fait quasi-générique et pourrait employer n'importe quelle classe implémentant les interfaces Piece et Passage.
 */
public class PassagePieceFactoryNP implements PassagePieceFactory{

	private Set<Piece> vertices;

	private Set<Passage> edges;

	/**
	 * constructeur
	 *
	 * On utilise des linkedHashSet.
	 */
	public PassagePieceFactoryNP(){
		this.vertices = new LinkedHashSet<Piece>();
		this.edges = new LinkedHashSet<Passage>();
	}


	/**
	 * ajoute et retourne une nouvelle pièce.
	 * @return la nouvelle pièce
	 */
	@Override
	public Piece newPiece(){
		Piece p = new PieceNP();
		this.vertices.add(p);
		return p;
	}

	/**
	 * Enlève une pièce, effet cascade sur tous les passages comportant cette pièce et 
	 * les autres pièces de ces passages.
	 * @param  p1 une pièce
	 * @return   vrai si la pièce était bien présente, faux sinon.
	 */
	@Override
	public boolean removePiece(Piece p1){
		Objects.requireNonNull(p1,"On ne peut pas enlever une pièce null.");
		Piece p2;
		if (this.vertices.remove(p1)){//on enlève le sommet de l'univers
			for (Direction d1 : Direction.values()){
            	if (p1.getPassage(d1) != null){//pour tout couloir qui sort
            		// On doit trouver la porte à murer dans l'autrePiece
            		p2 = p1.getPassage(d1).getAutrePiece(p1);// p1 -- p2
            		for (Direction d2 : Direction.values()){
            			if(p2.getPassage(d2)!= null && p2.getPassage(d2).equals(p1.getPassage(d1))){
            				p2.removePassage(d2);// murer le passage dans p2.
            				break;
            			}
            		}
            		// on peut enlever l'arête de l'univers
            		this.edges.remove(p1.getPassage(d1));
            		// on peut enfin murer la porte dans la pièce.
            		p1.removePassage(d1);
            	}
            }	
        	return true;
		}
		else return false;
	}

	/**
	 * Enlève un passage.
	 * @param  p passage à enlever
	 * @return    vrai ssi le passage devait être enlevé.
	 */
	@Override
	public boolean removePassage(Passage p){
		throw new UnsupportedOperationException("Désolé, fonction non supportée.");
	}

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
	@Override
	public Passage newPassage(Direction d1, Piece p1, Direction d2, Piece p2){
		Objects.requireNonNull(p1,"La pièce p1 ne peut être null.");
		Objects.requireNonNull(d1,"La direction d1 ne peut être null.");
		Objects.requireNonNull(p2,"La pièce p2 ne peut être null.");
		Objects.requireNonNull(d2,"La direction d2 ne peut être null.");
		if (p1.getPassage(d1) != null)
			throw new IllegalArgumentException("La direction d1 n'est pas libre dans la pièce p1");
		if (p2.getPassage(d2) != null)
			throw new IllegalArgumentException("La direction d2 n'est pas libre dans la pièce p2");
		Passage p = new PassageNP(p1,p2);//throws IllegalArgumentException si pièces identiques
		p1.setPassage(d1,p);
		p2.setPassage(d2,p);
		this.edges.add(p);
		return p;
	}

}
