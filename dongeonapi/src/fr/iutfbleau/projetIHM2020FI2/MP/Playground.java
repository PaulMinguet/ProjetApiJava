package fr.iutfbleau.projetIHM2020FI2.MP;
import fr.iutfbleau.projetIHM2020FI2.testP.*;

public class Playground{

    public static void main(String[] args) {
        TestTexteMP test = new TestTexteMP();
        Vue vue = new Vue(test);
        Action action = new Action(test, vue);
        vue.addMouseListener(action);
    }

}
