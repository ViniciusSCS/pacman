package dama.view;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class PainelDoJogo extends JPanel {

	private PainelPrincipal painelPrincipal;
	
	public PainelDoJogo() {
		setLayout(new BorderLayout());
		
		painelPrincipal = new PainelPrincipal();
		
		add(painelPrincipal, BorderLayout.CENTER);
		
		JButton restart = new JButton("Restart");
		restart.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				painelPrincipal.restart();	
				painelPrincipal.requestFocus();
			}
		});
		
		JPanel painelBotao = new JPanel();
		painelBotao.add(restart);
		add(painelBotao, BorderLayout.SOUTH);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		painelPrincipal.requestFocus();
	}
}
