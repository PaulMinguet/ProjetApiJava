package fr.iutfbleau.projetIHM2020FI2.MP;
import fr.iutfbleau.projetIHM2020FI2.testP.*;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.*;

public class Vue extends JFrame{

    private TestTexteMP test;
    public JPanel mainPanel, panel, map, inventaire;
    private CardLayout cardLayout, cLMap;
    public Inventaire invent;
    private ImageIcon fond;

    public Vue(TestTexteMP t) {
        super();
        test = t;

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1920, 1000);
        this.getContentPane().setBackground(Color.ORANGE);

        mainPanel = new JPanel(new GridBagLayout());
        inventaire = new JPanel(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        panel = new JPanel(new CardLayout());
        map = new JPanel(new CardLayout());
        fond = new ImageIcon("res/images/baseInventaire.png");

        panel.add(new JLabel(new ImageIcon("res/images/passageNord.png")), "1");
        panel.add(new JLabel(new ImageIcon("res/images/passageEst.png")), "2");
        panel.add(new JLabel(new ImageIcon("res/images/passageSud.png")), "3"); 
        panel.add(new JLabel(new ImageIcon("res/images/passageOuest.png")), "4");
        panel.add(new JLabel(new ImageIcon("res/images/murNord.png")), "5");
        panel.add(new JLabel(new ImageIcon("res/images/murEst.png")), "6");
        panel.add(new JLabel(new ImageIcon("res/images/murSud.png")), "7"); 
        panel.add(new JLabel(new ImageIcon("res/images/murOuest.png")), "8");
        panel.add(new JLabel(new ImageIcon("res/images/passageNordFC.png")), "9");
        panel.add(new JLabel(new ImageIcon("res/images/passageEstFC.png")), "10");
        panel.add(new JLabel(new ImageIcon("res/images/passageSudFC.png")), "11"); 
        panel.add(new JLabel(new ImageIcon("res/images/passageOuestFC.png")), "12");
        inventaire.add(new JLabel(new ImageIcon("res/images/inventaire.png")));
        for(int i = 1; i < 11; i++){
            map.add(new JLabel(new ImageIcon("res/images/map" + i + ".png")), "" + i + "");
        }
        
        cardLayout = (CardLayout) panel.getLayout();
        cLMap = (CardLayout) map.getLayout();

        mainPanel.setBackground(Color.GRAY);
        panel.setBackground(Color.GRAY);
        map.setBackground(Color.GRAY);
        inventaire.setBackground(Color.GRAY);

        gbc.insets = new Insets(0, 0, 0, 40);
        gbc.gridheight = 3;
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(panel, gbc);

        gbc.gridheight = 1;
        gbc.insets = new Insets(150, 0, 100, 40);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 1;
        gbc.gridy = 0;
        mainPanel.add(map, gbc);

        gbc.insets = new Insets(0, 0, 100, 40);
        gbc.gridx = 1;
        gbc.gridy = 2;
        invent = new Inventaire();
        mainPanel.add(invent, gbc);

        this.add(mainPanel);
        affiche(test.getDirection());
        afficheMap(test.getMap());
        effaceTsObjets();
        afficheObjetsP(test.getObjetsP());
        afficheObjets(test.getObjets());
        this.setVisible(true);
    }

    public void affiche(int dir){
        cardLayout.show(panel, Integer.toString(dir));
    }

    public void afficheMap(int piece){
        cLMap.show(map, Integer.toString(piece));
    }

    public void effaceTsObjets(){
        for(int i = 0; i < 6; i++){
            invent.removeObjetP(i);
            invent.removeObjet(i);
        }
    }

    public void effaceObjetP(int i){
        invent.removeObjetP(i);
    }
    public void effaceObjet(int i){
        invent.removeObjet(i);
    }

    public void afficheObjetsP(int[] tt){
        for(int i = 0; i < tt.length; i++){
            if(tt[i] == 1){
                invent.setObjetP(0, new ImageIcon("res/images/alcool.png"));
            }else if(tt[i] == 2){
                invent.setObjetP(1, new ImageIcon("res/images/cle.png"));
            }else if(tt[i] == 3){
                invent.setObjetP(2, new ImageIcon("res/images/eau.png"));
            }else if(tt[i] == 4){
                invent.setObjetP(3, new ImageIcon("res/images/eau.png"));
            }else if(tt[i] == 5){
                invent.setObjetP(4, new ImageIcon("res/images/tresor.png"));
            }else if(tt[i] == 6){
                invent.setObjetP(5, new ImageIcon("res/images/tresor.png"));
            }
        }
    }

    public void afficheObjets(int[] tt){
        for(int i = 0; i < tt.length; i++){
            if(tt[i] == 1){
                invent.setObjet(0, new ImageIcon("res/images/alcool.png"));
            }else if(tt[i] == 2){
                invent.setObjet(1, new ImageIcon("res/images/cle.png"));
            }else if(tt[i] == 3){
                invent.setObjet(2, new ImageIcon("res/images/eau.png"));
            }else if(tt[i] == 4){
                invent.setObjet(3, new ImageIcon("res/images/eau.png"));
            }else if(tt[i] == 5){
                invent.setObjet(4, new ImageIcon("res/images/tresor.png"));
            }else if(tt[i] == 6){
                invent.setObjet(5, new ImageIcon("res/images/tresor.png"));
            }
        }
    }

    public void afficheObjetP(int tt){
        for(int i = 0; i < 6; i++){
            if(i == 1){
                invent.setObjetP(0, new ImageIcon("res/images/alcool.png"));
            }else if(i == 2){
                invent.setObjetP(1, new ImageIcon("res/images/cle.png"));
            }else if(i == 3){
                invent.setObjetP(2, new ImageIcon("res/images/eau.png"));
            }else if(i == 4){
                invent.setObjetP(3, new ImageIcon("res/images/eau.png"));
            }else if(i == 5){
                invent.setObjetP(4, new ImageIcon("res/images/tresor.png"));
            }else if(i == 6){
                invent.setObjetP(5, new ImageIcon("res/images/tresor.png"));
            }
        }
    }

    public void afficheObjet(int tt){
        for(int i = 0; i < 6; i++){
            if(i == 1){
                invent.setObjet(0, new ImageIcon("res/images/alcool.png"));
            }else if(i == 2){
                invent.setObjet(1, new ImageIcon("res/images/cle.png"));
            }else if(i == 3){
                invent.setObjet(2, new ImageIcon("res/images/eau.png"));
            }else if(i == 4){
                invent.setObjet(3, new ImageIcon("res/images/eau.png"));
            }else if(i == 5){
                invent.setObjet(4, new ImageIcon("res/images/tresor.png"));
            }else if(i == 6){
                invent.setObjet(5, new ImageIcon("res/images/tresor.png"));
            }
        }
    }

    public void refreshVue(){
        effaceTsObjets();
        afficheObjetsP(test.getObjetsP());
        afficheObjets(test.getObjets());
    }

}
