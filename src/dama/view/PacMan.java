package dama.view;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class PacMan {

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		
		JPanel painel = new PainelDoJogo();
		frame.setContentPane(painel);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 500);
//		frame.setResizable(false);
		frame.setVisible(true);
	}
}
