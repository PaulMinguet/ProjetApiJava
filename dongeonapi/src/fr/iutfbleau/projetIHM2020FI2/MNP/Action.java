package fr.iutfbleau.projetIHM2020FI2.MNP;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class Action implements MouseListener {

    CardLayout layout;
    Container panel;

    public Action(CardLayout c,Container p){
        layout=c;
        panel=p;
    }


    public void mousePressed(MouseEvent e){
        /*String nom = e.getActionCommand();

        if (nom.equals("GAUCHE")) {
            layout.previous(panel);
            System.out.println("GAUCHE");
        }else if(nom.equals("DROITE")) {
            layout.next(panel);
            System.out.println("DROITE");
        } else if(nom.equals("AVANCER")){
            for (int i = 0; i < 4; i++) {
                layout.next(panel);
            }
            System.out.println("AVANCER");
        } else if (nom.equals("DEMI-TOUR")) {
            for (int i = 0; i < 2; i++) {
                layout.next(panel);
            }
            System.out.println("DEMI-TOUR");
        }*/

        int x = e.getX();
        int y = e.getY();
        System.out.println(x);
        System.out.println(y);
        if(x < panel.getWidth()/4){     // Gauche
            layout.previous(panel);
        }
        if(x > 3*(panel.getWidth()/4)){ // Droite
            layout.next(panel);
        }
        if(x > panel.getWidth()/4 && x < 3*(panel.getWidth()/4) && y > 4*(panel.getHeight()/5)){    // Demi-tour
            for (int i = 0; i < 2; i++) {
                layout.next(panel);
            }
        }
        if(x > panel.getWidth()/4+panel.getWidth()/8 && x < 3*panel.getWidth()/4-panel.getWidth()/8 && y < 4*(panel.getHeight()/5) && y > 2*(panel.getHeight()/5)){ // Action porte
            System.out.println("Action");
        }
    }

    public void mouseReleased(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }

    public void mouseClicked(MouseEvent e) {

    }

  
  
}