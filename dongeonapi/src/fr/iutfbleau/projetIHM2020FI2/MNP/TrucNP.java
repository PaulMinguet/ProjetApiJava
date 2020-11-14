package fr.iutfbleau.projetIHM2020FI2.MNP;
import fr.iutfbleau.projetIHM2020FI2.API.*;
import java.util.*;
/**
 * Un objet pouvant être dans une pièce ou porté par le joueur.
 * Version non persistante.
 */

public class TrucNP implements Truc {

	private TypeTruc tt;

	/**
	 * type de l'objet
	 * @return type
	 */
    public TypeTruc getTypeTruc(){
    	return tt;
    }

    private String description;

	/**
	 * description textuelle de l'objet.
	 * @return description.
	 */
    public String getDescription(){
    	return description;
    }
	/**
	 * constructeur
	 * @param  tt type de l'objet à créer
	 * @param  d sa description
	 * @throws NullPointerException si un paramètre est null
	 */
    public TrucNP(TypeTruc tt, String d){
		Objects.requireNonNull(tt,"On ne peut pas créer un truc avec un TypeTruc null.");
		Objects.requireNonNull(d,"On ne peut pas créer un truc avec une description null.");
		this.tt=tt;
		this.description=d;
    }

    
}
