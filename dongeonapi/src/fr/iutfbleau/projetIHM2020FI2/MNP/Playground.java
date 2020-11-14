    package fr.iutfbleau.projetIHM2020FI2.MNP;

    import java.util.*;
    import javax.swing.*;
    import java.awt.Image;

    import javax.swing.JPanel;
    import java.io.File;
    import javax.imageio.ImageIO;
    import java.io.*;
    import java.awt.event.*;

    import java.awt.*;
    import java.awt.Graphics;

    public class Playground extends JFrame {
        private Image perso;

        public Playground() {
            super();

            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setSize(2000, 1000);
            JPanel p = new JPanel();
            /*p.setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            JButton avancer = new JButton("AVANCER");
            JButton gauche = new JButton("GAUCHE");
            JButton droite = new JButton("DROITE");
            JButton demitour = new JButton("DEMI-TOUR");
            JButton prendre = new JButton("PRENDRE OBJET");*/
            CardLayout carousel = new CardLayout();
            JPanel panel = new JPanel(carousel);
            Action event = new Action(carousel, panel);
            /*gbc.fill = GridBagConstraints.BOTH; // n'occupe pas tout l'espace de la plage
            gbc.weightx = 0.6; // remplir la plage horizontale de 0.6
            gbc.weighty = 0.3; // ' ' veritcale ' '
            gbc.gridx = 1; // la plage de cellules commence à la première colonne
            gbc.gridy = 0; // la plage de cellules commence à la deuxième ligne
            gbc.gridwidth = 1; // la plage de cellules englobe deux colonnes
            gbc.gridheight = 1; // la plage de cellules englobe une ligne
            p.add(avancer, gbc);

            gbc.gridx = 1;
            gbc.gridy = 2;
            p.add(demitour, gbc);
            
            gbc.weightx = 0.3;
            gbc.weighty = 3;
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridheight = 3;
            p.add(gauche, gbc);

            gbc.weightx = 0.3;
            gbc.weighty = 0.6;
            gbc.gridx = 2;
            gbc.gridy = 0;
            gbc.gridwidth = 3;
            p.add(droite, gbc);
            
            gbc.weightx = 0.3;
            gbc.weighty = 0.3;
            gbc.gridx = 1;
            gbc.gridy = 1;
            gbc.gridwidth = 1;
            gbc.gridheight = 1;
            p.add(prendre, gbc);

            avancer.addActionListener(event);
            gauche.addActionListener(event);
            droite.addActionListener(event);
            demitour.addActionListener(event);*/

            JLabel img0 = new JLabel(new ImageIcon("img/passageNord.png"));
            JLabel img1 = new JLabel(new ImageIcon("img/passageEst.png"));
            JLabel img2 = new JLabel(new ImageIcon("img/passageSud.png"));
            JLabel img3 = new JLabel(new ImageIcon("img/passageOuest.png"));

            panel.add(img0);
            panel.add(img1);
            panel.add(img2);
            panel.add(img3);
      
            add(panel, BorderLayout.CENTER);
            add(p, BorderLayout.SOUTH);
            this.addMouseListener(event);
        }

    }
