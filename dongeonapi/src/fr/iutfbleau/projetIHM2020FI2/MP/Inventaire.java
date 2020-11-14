package fr.iutfbleau.projetIHM2020FI2.MP;
import java.awt.*;
import javax.swing.*;

public class Inventaire extends JPanel{

	private Case[] caseInvP = new Case[6];
	private Case[] caseInv = new Case[6];
	private ImageIcon fond;
	public JPanel ctcases, ctcasesp;
	public PanelNom piece, joueur;

	public Inventaire(){
		fond = new ImageIcon("res/images/baseInventaire.png");
		ctcases = new JPanel();
		ctcasesp = new JPanel();
		ctcases.setLayout(new GridLayout(1, 6));
		ctcasesp.setLayout(new GridLayout(1, 6));
		setLayout(new GridLayout(4,1));
		setBackground(Color.GRAY);
		ctcasesp.setBackground(Color.GRAY);
		ctcases.setBackground(Color.GRAY);
		piece = new PanelNom("Pièce");
		joueur = new PanelNom("Joueur");
		for(int i = 0; i < 6; i++){
			caseInv[i] = new Case(fond);
			ctcases.add(caseInv[i]);
		}

		for(int i = 0; i < 6; i++){
			caseInvP[i] = new Case(fond);
			ctcasesp.add(caseInvP[i]);
		}
		add(piece);
		this.add(ctcasesp);
		add(joueur);
		this.add(ctcases);
	}

	public void setObjetP(int id, ImageIcon i){
		caseInvP[id].setObjet(i);
	}

	public void setObjet(int id, ImageIcon i){
		caseInv[id].setObjet(i);
	}

	public void removeObjetP(int id){
		caseInvP[id].setObjet(fond);
	}

	public void removeObjet(int id){
		caseInv[id].setObjet(fond);
	}
}