package dama.view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import dama.controler.ControlodorJogoPacMan;

public class PainelPrincipal extends JPanel {

	enum Direcao {
		CIMA, BAIXO, ESQUERDA, DIREITA
	}
	
	private ControlodorJogoPacMan controlador;
	
	private Direcao direcao;

	private Timer timer;

	private boolean flag;
	
	private int keyCode;
	
	private static final Color FUNDO = new Color(3, 3, 3);
	private static final Color BOLINHA = new Color(255, 255, 171);
	private static final Color PAREDE = new Color(87, 87, 255);
	
	private static BufferedImage IMG_FECHADO;
	private static BufferedImage IMG_CIMA;
	private static BufferedImage IMG_BAIXO;
	private static BufferedImage IMG_ESQUERDA;
	private static BufferedImage IMG_DIREITA;
	private static BufferedImage IMG_FANTASMA;
	
	static {
		try {
			IMG_FECHADO = ImageIO.read(new File("resources/Fechado.png"));
			IMG_BAIXO = ImageIO.read(new File("resources/PacmanBaixo.png"));
			IMG_CIMA = ImageIO.read(new File("resources/PacmanCima.png"));
			IMG_ESQUERDA = ImageIO.read(new File("resources/PacmanEsquerda.png"));
			IMG_DIREITA = ImageIO.read(new File("resources/PacmanDireita.png"));
			IMG_FANTASMA = ImageIO.read(new File("resources/Fantasma.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public PainelPrincipal() {
		controlador = new ControlodorJogoPacMan();
		setBackground(FUNDO);
		
		addKeyListener(new KeyAdapter() {
			private long lastPressProcessed = 0;

			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() != keyCode || (System.currentTimeMillis() - lastPressProcessed) > controlador.getVelocidade()) {
					if(e.getKeyCode() == KeyEvent.VK_UP ||
							e.getKeyCode() == KeyEvent.VK_DOWN ||
							e.getKeyCode() == KeyEvent.VK_LEFT ||
							e.getKeyCode() == KeyEvent.VK_RIGHT) {
						keyCode = e.getKeyCode();
						switch (e.getKeyCode()) {
						case KeyEvent.VK_LEFT:
							controlador.PackManParaEsquerda();
							direcao = Direcao.ESQUERDA;
							break;
			
						case KeyEvent.VK_RIGHT:
							controlador.PackManParaDireita();
							direcao = Direcao.DIREITA;
							break;
							
						case KeyEvent.VK_UP:
							controlador.PackManParaCima();
							direcao = Direcao.CIMA;
							break;
							
						case KeyEvent.VK_DOWN:
							controlador.PackManParaBaixo();
							direcao = Direcao.BAIXO;
							break;
						}
						startTimer();
					}
		            lastPressProcessed = System.currentTimeMillis();
				}     
			}
		});
		
		configure();
	}
	
	private void configure() {
		direcao = Direcao.DIREITA;
		flag = true;
	}

	private void startTimer() {
		if(timer == null) {
			timer = new Timer(true);
			timer.schedule(new TimerTask() {
				@Override
				public void run() {
					repaint();
				}
			}, 0, controlador.getVelocidade()/2);
		}
	}
	
	private void stopTimer() {
		if(timer != null) {
			timer.cancel();
			timer = null;
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.flag = (flag == false);
		
		Graphics2D g2d = (Graphics2D) g;
		
		for(int linha = 0; linha < controlador.getTamanho(); linha++)
			for(int coluna = 0; coluna < controlador.getTamanho(); coluna++) {
				if(controlador.temParede(linha, coluna)) { 
					desenheParede(linha, coluna, g2d);
				}
				if(controlador.temBolinha(linha, coluna)) {
					desenheBolinha(linha, coluna, g2d);
				}
				if(controlador.temFantasma(linha, coluna)) {
					desenheFantasma(linha, coluna, g2d);
				}
				if(controlador.temPackMan(linha, coluna)) {
					desenhePackman(linha, coluna, g2d);
				}
			}
		
		if(controlador.terminado())
			stopTimer();
	}

	private void desenhePackman(int linha, int coluna, Graphics2D g) {
		if(flag) {
			switch (direcao) {
			case CIMA:
				g.drawImage(IMG_CIMA, calculaMenorDimensao() * coluna + 1, calculaMenorDimensao() * linha + 1,
						calculaMenorDimensao() - 2, calculaMenorDimensao() - 2, null);
				break;
	
			case BAIXO:
				g.drawImage(IMG_BAIXO, calculaMenorDimensao() * coluna + 1, calculaMenorDimensao() * linha + 1,
						calculaMenorDimensao() - 2, calculaMenorDimensao() - 2, null);
				break;
				
			case ESQUERDA:
				g.drawImage(IMG_ESQUERDA, calculaMenorDimensao() * coluna + 1, calculaMenorDimensao() * linha + 1,
						calculaMenorDimensao() - 2, calculaMenorDimensao() - 2, null);
				break;
				
			case DIREITA:
				g.drawImage(IMG_DIREITA, calculaMenorDimensao() * coluna + 1, calculaMenorDimensao() * linha + 1,
						calculaMenorDimensao() - 2, calculaMenorDimensao() - 2, null);
				break;
			default:
				break;
			}
		} else
			g.drawImage(IMG_FECHADO, calculaMenorDimensao() * coluna + 1, calculaMenorDimensao() * linha + 1,
					calculaMenorDimensao() - 2, calculaMenorDimensao() - 2, null);
	}

	private void desenheFantasma(int linha, int coluna, Graphics2D g) {
		g.drawImage(IMG_FANTASMA, calculaMenorDimensao() * linha + 1, calculaMenorDimensao() * coluna + 1,
				calculaMenorDimensao() - 2, calculaMenorDimensao() - 2, null);
	}

	private void desenheBolinha(int linha, int coluna, Graphics2D g) {
		g.setColor(BOLINHA);
		g.fillOval((calculaMenorDimensao() * (2 * linha + 1))/2,
				(calculaMenorDimensao() * (2 * coluna + 1))/2, 2, 2);
	}

	private void desenheParede(int linha, int coluna, Graphics2D g) {
		g.setColor(PAREDE);
		g.setStroke(new BasicStroke(3));  
		g.drawRect(calculaMenorDimensao() * linha, calculaMenorDimensao() * coluna,
				calculaMenorDimensao(), calculaMenorDimensao());
	}

	private int calculaMenorDimensao() {
		int dimensao = getSize().height;
		
		if(dimensao > getSize().width)
			dimensao = getSize().width;
		
		return dimensao/controlador.getTamanho();
	}
	

	public void restart() {
		stopTimer();
		configure();
		controlador.restart();
		repaint();
	}
}
