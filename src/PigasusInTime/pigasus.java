package PigasusInTime;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.net.URL;

public class pigasus implements pigasusInterface {
	
	Image imagem;
	
	//x y imagem
	private int x_img = 0;
	private int y_img = 400;
	private double curva = 0.0;
	private double p = 0.5;
	
	public void setXImg(int x){
		x_img = x;
	}
	
	public int getXImg(){
		return x_img;
	}
	
	public void setYImg(int y){
		y_img = y;
	}
	
	public int getYImg(){
		return y_img;
	}
	
	public void moveDireita(){
		curva = curva - 0.02; p = p + 0.001;
		//if (x_img >= (ScreenWidth - (imagem.getWidth(null) + 5)))	x_img = ScreenWidth - (imagem.getWidth(null) + 5);	
		//else														x_img = x_img + movimento;
	}
	public void moveEsquerda(){
		curva = curva + 0.02; p = p - 0.001;
		//if (x_img <= 5)						x_img = 5;
		//else 								x_img = x_img - movimento;
	}
	
	public void moveCima(){
		if (y_img <= 30)					y_img = 30;
		else								y_img = y_img - movimento;
	}
	public void moveBaixo(){
		if (y_img >= (ScreenHeight - (imagem.getHeight(null) + 5)))	y_img = ScreenHeight - (imagem.getHeight(null) + 5);
		else														y_img = y_img + movimento;
	}

	public pigasus(){
		Toolkit tk = Toolkit.getDefaultToolkit();
		imagem = tk.getImage(getURL("pigasus.jpg"));
		
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
		
		g2d.translate(meioX, meioY); //define centro da tela
		g2d.rotate(curva); //rotaciona
		g2d.scale(p, p); //escala da figura
		
		g2d.drawImage(imagem, x_img - (imagem.getHeight(null)/2), y_img, null); // desenha imagem no centro
					
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
