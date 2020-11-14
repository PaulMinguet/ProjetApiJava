package fr.iutfbleau.projetIHM2020FI2.MP;
import fr.iutfbleau.projetIHM2020FI2.testP.*;
import fr.iutfbleau.projetIHM2020FI2.API.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class Action implements MouseListener {

    private Vue vue;
    private TestTexteMP test;

    public Action(TestTexteMP t, Vue v){
        vue = v;
        test = t;
    }

    public void mousePressed(MouseEvent e){

        // Directions : 1 = Nord, 2 = Est, 3 = Sud, 4 = Ouest

        int x = e.getX();
        int y = e.getY();
        if(x < vue.panel.getWidth()/4){     // Gauche
            vue.affiche(test.changerDirection(true));
        }

        if(x > 3*(vue.panel.getWidth()/4) && x < vue.panel.getWidth()){ // Droite
            vue.affiche(test.changerDirection(false));
        }

        if(x > vue.panel.getWidth()/4 && x < 3*(vue.panel.getWidth()/4) && y > 4*(vue.panel.getHeight()/5)){    // Demi-tour
            vue.affiche(test.changerDirDT());
        }

        if(x > vue.panel.getWidth()/4 && x < 3*vue.panel.getWidth()/4 && y < 4*(vue.panel.getHeight()/5) && y > 2*(vue.panel.getHeight()/5)){ // Action porte
            test.action();
            vue.affiche(test.getDirection());
            vue.afficheMap(test.getMap());
            vue.refreshVue();
        }

        if(x > vue.panel.getWidth()+40 && x < vue.getWidth()-40 && y > vue.getHeight()-vue.invent.getHeight()-40 && y < vue.getHeight()-40){
            if(y > vue.getHeight()-vue.invent.getHeight()-40+vue.invent.piece.getHeight() && y < vue.getHeight()-vue.invent.getHeight()-40+vue.invent.piece.getHeight()+vue.invent.ctcasesp.getHeight()){
                System.out.println("Piece");
                if(x > vue.getWidth()-40-vue.invent.getWidth() && x < vue.getWidth()-40-(5*vue.invent.getWidth())/6){               // Slot 1
                    System.out.println("Slot 1");
                    if(test.j.getPiece().containsTruc(test.t0)){
                        test.j.getPiece().removeTruc(test.t0);
                        test.j.addTruc(test.t0);
                        vue.refreshVue();
                    }
                }else if(x > vue.getWidth()-40-(5*vue.invent.getWidth())/6 && x < vue.getWidth()-40-(4*vue.invent.getWidth())/6){   // Slot 2
                    System.out.println("Slot 2");
                    if(test.j.getPiece().containsTruc(test.t1)){
                        test.j.getPiece().removeTruc(test.t1);
                        test.j.addTruc(test.t1);
                        vue.refreshVue();
                    }
                }else if(x > vue.getWidth()-40-(4*vue.invent.getWidth())/6 && x < vue.getWidth()-40-(3*vue.invent.getWidth())/6){   // Slot 3
                    System.out.println("Slot 3");
                    if(test.j.getPiece().containsTruc(test.t2)){
                        test.j.getPiece().removeTruc(test.t2);
                        test.j.addTruc(test.t2);
                        vue.refreshVue();
                    }
                }else if(x > vue.getWidth()-40-(3*vue.invent.getWidth())/6 && x < vue.getWidth()-40-(2*vue.invent.getWidth())/6){   // Slot 4
                    System.out.println("Slot 4");
                    if(test.j.getPiece().containsTruc(test.t3)){
                        test.j.getPiece().removeTruc(test.t3);
                        test.j.addTruc(test.t3);
                        vue.refreshVue();
                    }
                }else if(x > vue.getWidth()-40-(2*vue.invent.getWidth())/6 && x < vue.getWidth()-40-(vue.invent.getWidth())/6){     // Slot 5
                    System.out.println("Slot 5");
                    if(test.j.getPiece().containsTruc(test.t4)){
                        test.j.getPiece().removeTruc(test.t4);
                        test.j.addTruc(test.t4);
                        vue.refreshVue();
                    }
                }else if(x > vue.getWidth()-40-(vue.invent.getWidth())/6 && x < vue.getWidth()-40){                                 // Slot 6
                    System.out.println("Slot 6");
                    if(test.j.getPiece().containsTruc(test.t5)){
                        test.j.getPiece().removeTruc(test.t5);
                        test.j.addTruc(test.t5);
                        vue.refreshVue();
                    }
                }
            }else if(y > vue.getHeight()-40-vue.invent.ctcases.getHeight() && y < vue.getHeight()-40){
                System.out.println("Joueur");
                if(x > vue.getWidth()-40-vue.invent.getWidth() && x < vue.getWidth()-40-(5*vue.invent.getWidth())/6){               // Slot 1
                    System.out.println("Slot 1");
                    if(test.j.containsTruc(test.t0)){
                        int dialogButton = JOptionPane.YES_NO_OPTION;
                        int dialogResult = JOptionPane.showConfirmDialog (null, "Voulez-vous boir : alcool ?","Warning",dialogButton);
                        if(dialogResult == JOptionPane.YES_OPTION)
                            test.j.removeTruc(test.t0);
                        if(dialogResult == JOptionPane.NO_OPTION){
                            test.j.removeTruc(test.t0);
                            test.j.getPiece().addTruc(test.t0);
                        }
                        vue.refreshVue();
                    }
                }else if(x > vue.getWidth()-40-(5*vue.invent.getWidth())/6 && x < vue.getWidth()-40-(4*vue.invent.getWidth())/6){   // Slot 2
                    System.out.println("Slot 2");
                    if(test.j.containsTruc(test.t1)){
                        test.j.removeTruc(test.t1);
                        test.j.getPiece().addTruc(test.t1);
                        vue.refreshVue();
                    }
                }else if(x > vue.getWidth()-40-(4*vue.invent.getWidth())/6 && x < vue.getWidth()-40-(3*vue.invent.getWidth())/6){   // Slot 3
                    System.out.println("Slot 3");
                    if(test.j.containsTruc(test.t2)){
                        int dialogButton = JOptionPane.YES_NO_OPTION;
                        int dialogResult = JOptionPane.showConfirmDialog (null, "Voulez-vous boir : eau ?","Warning",dialogButton);
                        if(dialogResult == JOptionPane.YES_OPTION)
                            test.j.removeTruc(test.t2);
                        if(dialogResult == JOptionPane.NO_OPTION){
                            test.j.removeTruc(test.t2);
                            test.j.getPiece().addTruc(test.t2);
                        }
                        vue.refreshVue();
                    }
                }else if(x > vue.getWidth()-40-(3*vue.invent.getWidth())/6 && x < vue.getWidth()-40-(2*vue.invent.getWidth())/6){   // Slot 4
                    System.out.println("Slot 4");
                    if(test.j.containsTruc(test.t3)){
                        int dialogButton = JOptionPane.YES_NO_OPTION;
                        int dialogResult = JOptionPane.showConfirmDialog (null, "Voulez-vous boir : alcool ?","Warning",dialogButton);
                        if(dialogResult == JOptionPane.YES_OPTION)
                            test.j.removeTruc(test.t3);
                        vue.refreshVue();
                    }
                }else if(x > vue.getWidth()-40-(2*vue.invent.getWidth())/6 && x < vue.getWidth()-40-(vue.invent.getWidth())/6){     // Slot 5
                    System.out.println("Slot 5");
                    if(test.j.containsTruc(test.t4)){
                        test.j.removeTruc(test.t4);
                        test.j.getPiece().addTruc(test.t4);
                        vue.refreshVue();
                    }
                }else if(x > vue.getWidth()-40-(vue.invent.getWidth())/6 && x < vue.getWidth()-40){                                 // Slot 6
                    System.out.println("Slot 6");
                    if(test.j.containsTruc(test.t5)){
                        test.j.removeTruc(test.t5);
                        test.j.getPiece().addTruc(test.t5);
                        vue.refreshVue();
                    }
                }
            }
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