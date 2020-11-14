package fr.iutfbleau.projetIHM2020FI2.API;
/**
 * Le mécanisme d'action est très simpliste pour l'instant.
 * Un truc a un type.
 * C'est ce type qui va influencer le déclenchement et l'effet d'une action.
 *
 * @see Activable
 */
public enum TypeTruc implements Descriptible{
    CLE("clé"), ALCOOL("alcool"), EAU("eau"), GOODIES("trésor");

    private final String description;

    TypeTruc(String d){
        this.description = d;
    }

    @Override
    public String toString() {
        return this.description;
    }

    // pas nécessaire car c'est le comportement par défaut.
    // public String getDescription(){
    //     return this.toString();
    // }
    
}

