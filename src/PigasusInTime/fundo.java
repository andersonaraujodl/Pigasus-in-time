package PigasusInTime;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.net.URL;

public class fundo implements pigasusInterface {

	Image fundo;
	double movTela = 0.0;
	
	public fundo(){
		Toolkit tk = Toolkit.getDefaultToolkit();
		fundo = tk.getImage(getURL("fundo.jpg"));
		
	}
	
	public void paint(Graphics g) {	//Método paint (pintar)
		Graphics2D g2d = (Graphics2D) g.create();
			
		//Anti-aliasing - mistura gradualmente a cor das bordas com a do fundo, eliminando a aparência serrilhada e adicionando qualidade ao desenho (utiliza mais processameto)
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		//RenderHints - renderização e manipulação de imagem 
			
		//Desenha
		desenhaImagem(g2d);
			
		g2d.dispose();
	}
	
	private void desenhaImagem(Graphics2D g){	
		
		Graphics2D g2d = (Graphics2D) g.create();
		//
		g2d.translate(meioX, meioY); //define centro da tela
		g2d.rotate(movTela = movTela + 0.05); //rotaciona
		
		
		g2d.drawImage(fundo, -(fundo.getWidth(null)/2), -(fundo.getHeight(null)/2), null);
					
		g2d.dispose();
		}
	
	private URL getURL(String filename) {
		URL url = null;
			try {
				url = this.getClass().getResource(filename);
			}
			
			catch (Exception e) {}
			return url;
	}



}
