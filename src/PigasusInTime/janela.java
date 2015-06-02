package PigasusInTime;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class janela extends JFrame implements KeyListener, Runnable, pigasusInterface {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Thread gameloop;
	Random rand = new Random();
	
	pigasus pigasus = new pigasus();
	fundo fundo = new fundo();
	
	//x y imagem
	int x_img = 10;
	int y_img = 200;
	
	//objetos de buffer duplo
	BufferedImage backbuffer;
	Graphics2D g2d;
	
	public janela(){
		
		addKeyListener(this);
		setTitle("Teste");
		setVisible(true);
		setSize(ScreenWidth,ScreenHeight); //tamanho janela principal
		setLocationRelativeTo(null); //Centra janela no centro da tela
		setDefaultCloseOperation(EXIT_ON_CLOSE); //QUANDO FECHARMOS O frame A APLICAÇÃO PARA DE EXECUTAR
				
		//criar buffer de fundo para graficos suaves
		backbuffer = new BufferedImage(ScreenWidth, ScreenHeight,
				BufferedImage.TYPE_INT_RGB);
		g2d = backbuffer.createGraphics();

		gameloop = new Thread(this);
		gameloop.start();
	}
	
	public void run() {
		Thread t = Thread.currentThread();
		
		while (t == gameloop) {
			try {
				Thread.sleep(timeLoop);
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
			gameUpdate();
		}
	}
	
	public void gameUpdate() {
		
		//limpar o fundo
		g2d.setColor(Color.BLACK);
		g2d.fill( new Rectangle(0, 0, ScreenWidth-1, ScreenHeight-1) );
		
		//desenha imagens na tela
		fundo.paint(g2d);
		
		pigasus.paint(g2d);
		
		repaint();
		}	
	
	public void paint(Graphics g) {
	//desenhar o buffer de fundo para a tela
	g.drawImage(backbuffer, 0, 0, this);
	}
	
	public void keyPressed(KeyEvent ek){  
        // VK_ESCAPE é uma constante estática. Não faz diferença para o   
        // compilador usar ek ou KeyEvent, mas só para ser preciso  
        if(ek.getKeyCode() == KeyEvent.VK_ESCAPE){   
            // passando this em vez de null, a janelinha vai aparecer no   
            // centro da minha janela principal  
            int selectedOption = JOptionPane.showConfirmDialog(this, "Deseja Sair Realmente?", "Atenção", JOptionPane.YES_NO_OPTION);  
          
            // se o cara escolheu YES, fecha a aplicação  
            if(selectedOption == JOptionPane.YES_OPTION) {  
                // libera os recursos da janela  
                dispose();
 
            }  
            // se o cara escolheu NO, não faça nada  
        } 
        
        if(ek.getKeyCode() == KeyEvent.VK_LEFT){
        	pigasus.moveEsquerda();
        }
        
        if(ek.getKeyCode() == KeyEvent.VK_RIGHT){
        	pigasus.moveDireita();
        }
        
        if(ek.getKeyCode() == KeyEvent.VK_UP){
        	pigasus.moveCima();
        }
        
        if(ek.getKeyCode() == KeyEvent.VK_DOWN){
        	pigasus.moveBaixo();
        }
    } 
	public void keyTyped(KeyEvent ek) { }  
    public void keyReleased(KeyEvent ek) { }
    
    public static void main(String[] args) {
		new janela();
	}

}
