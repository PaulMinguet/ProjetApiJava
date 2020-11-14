package fr.iutfbleau.projetIHM2020FI2.MP;
import java.awt.*;
import javax.swing.*;
import java.util.*;

public class PanelNom extends JComponent{

	private Font font;
	private String nom;

	public PanelNom(String n){
		this.font = new Font("Verdana", Font.BOLD, 30);
		this.nom = n;
	}

	@Override
	protected void paintComponent(Graphics pinceau){
		Graphics secondPinceau = pinceau.create();
        if(this.isOpaque()){
        	secondPinceau.setColor(new Color(0, 0, 0));
        	secondPinceau.fillRect(0, 0, this.getWidth(), this.getHeight());
        }
        secondPinceau.setFont(this.font);
        drawCenteredString(secondPinceau, this.nom, this.font);
	}

	public void drawCenteredString(Graphics g, String text, Font font) 
    {
        FontMetrics metrics = g.getFontMetrics(font);
        int x = (getWidth() - metrics.stringWidth(text)) / 2;    
        int y = ((getHeight() - metrics.getHeight()) / 2) + metrics.getAscent();
        g.setFont(font);
        g.drawString(text, x, y);
    }
}