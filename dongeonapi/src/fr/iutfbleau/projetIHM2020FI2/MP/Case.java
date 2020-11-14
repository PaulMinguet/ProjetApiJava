package fr.iutfbleau.projetIHM2020FI2.MP;
import java.awt.*;
import javax.swing.*;
import java.util.*;

public class Case extends JComponent{

	private ImageIcon fond;
	private ImageIcon objet;

	public Case(ImageIcon bs){
		setPreferredSize(new Dimension(50, 50));
		this.fond = bs;
	}

	public void setObjet(ImageIcon truc){
		this.objet = truc;
		repaint();
	}

	@Override
	protected void paintComponent(Graphics pinceau){
		Graphics secondPinceau = pinceau.create();
        if(this.isOpaque()){
        	secondPinceau.setColor(new Color(0, 0, 0));
        	secondPinceau.fillRect(0, 0, this.getWidth(), this.getHeight());
        }
        secondPinceau.drawImage(this.fond.getImage(), 0, 0, getWidth(), getHeight(), this);
        if(this.objet != null){
        	secondPinceau.drawImage(this.objet.getImage(), 0, 0, getWidth(), getHeight(), this);
        }
	}
}