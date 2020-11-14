package fr.iutfbleau.projetIHM2020FI2.API;
public enum EtatPassage implements Descriptible{
    OPEN("ouvert"), CLOSED("fermé"), LOCKED("fermé à clé");

    private final String description;

    EtatPassage(String d){
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
