package fr.iutfbleau.projetIHM2020FI2.MNP;
import fr.iutfbleau.projetIHM2020FI2.API.*;
import java.util.*;
/**
 * Implémentation non persistante d'un Joueur
 */
public class JoueurNP extends ContientTrucsNP implements Joueur{

    //piece actuelle
    private Piece p;

    // conteneur de Pièces.
    private List<Piece> cerveau;
    
    /**
    * Constructeur
    * 
    * On utilise un ensemble LinkedList pour gérer la chronologie.
    *
    * Le joueur n'est pas dans une pièce à sa création.
    * @see setPiece() 
    */
    public JoueurNP(){
        super();
        this.cerveau = new LinkedList<Piece>();
   }


   /** 
     * @return la piece dans laquelle le joueur se trouve
     * (null si la pièce n'est pas renseignée)
    */
   @Override
    public Piece getPiece(){
    	return this.p;
    }
	
	/** 
     * Met à jour la piece dans laquelle le joueur se trouve
     * Demande l'ajout de l'ancienne pièce au "cerveau".
     * NB. il n'y a pas de vérification dans Joueur.
    */
   @Override
    public void setPiece(Piece next){
    	if (this.getPiece() != null) 
            this.addVisited(this.getPiece());
    	this.p=next;
    }
    
    /**
     * @return les pieces que le joueur a deja visité.
     */
    @Override
    public Iterator<Piece> getVisited(){
    	return this.cerveau.iterator();
    }
    
    /**
     * Ajoute la Piece si nécessaire (si l'itérateur retourné par 
     * getVisited ne permet pas d'itérer sur la Piece).
     * Une pièce devient visitée quand on la quitte.
     * @param  p Pièce qu'on vient de (re)visiter
     * @throws NullPointerException la Piece ne peut pas être null
     * @return vrai si il fallait l'ajouter et faux sinon.
     */
    @Override
    public boolean addVisited(Piece p){
    	Objects.requireNonNull(p,"On ne peut pas ajouter une pièce null.");
    	return this.cerveau.add(p);
    }
    
    /**
 	* Teste si piece a été visitée
 	* @param  p pièce
 	* @throws NullPointerException si la pièce est null 
 	* @return vrai si la pièce est connue.
 	*/
    @Override
    public boolean isVisited(Piece p){
    	Objects.requireNonNull(p,"On ne peut pas connaître une pièce null.");
    	return this.cerveau.contains(p);
    }
	
	/**
     * Accesseur pour la description textuelle du sac à dos.
     * @return description
     */
    @Override
    public String getDescription(){
        // Stringbuilder is the most efficient method of building a String like datastructure incrementally. 
        StringBuilder sb = new StringBuilder("");
        int goodies = super.combienTrucs();
        if (goodies == 0)
        	sb.append("\n"+"Le sac à dos ne contient pas d'objet. ");
        else if (goodies == 1)	
        	sb.append("\n"+"Le sac à dos contient un objet : ");
        else sb.append("\n"+ "Le sac à dos contient "+ super.combienTrucs()+" objets : ");
        Iterator<Truc> goods = super.getTrucs();
        while (goods.hasNext()){
        	Truc t = goods.next();
        	sb.append("\n _ " + t.getDescription() +".");
        }
        sb.append("\n");
        return sb.toString();
    }
    /**
     * Le joueur non persistent n'est pas tout à fait comme le buveur du petit prince.
     * Il ne boit pas du rhum pour oublier qu'il en boit mais pour oublier les pièces qu'il connaît.
     * @param  t un truc que le joueur doit posséder
     * @throws IllegalStateException  sinon
     * @return vrai si l'objet a un effet sur le joueur
     */
    @Override
    public boolean agir (Truc t){
    	if (t == null){
    		return false;
    	}
    	else if (super.containsTruc(t))
    		throw new IllegalStateException("le joueur ne porte pas l'objet");
        else if (Objects.equals(t.getTypeTruc(),TypeTruc.ALCOOL)){
        	super.removeTruc(t);
        	this.cerveau.clear();
        	return true;
        }
        else if (Objects.equals(t.getTypeTruc(),TypeTruc.EAU)){
        	super.removeTruc(t);
        	return true;
        }
        else return false;
    }
}
