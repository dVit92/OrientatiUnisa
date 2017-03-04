package tfidf;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;

/**
 * Show the result to the users 
 * @authors Daniele Vitale, Marianna di Gregorio, Alessandra Orsi
 *
 */

public class MainGrafica {

	public static void main(String[] args) throws IOException {
		JFrame frame = new JFrame();
		frame.setBounds(200, 200, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("/Users/Alessandra/Desktop/unisa.png"));
		frame.getContentPane().add(label, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(143, 188, 143));
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblScopriIlTuo = new JLabel("Scopri il tuo corso di laurea!");
		lblScopriIlTuo.setBounds(550, 80, 356, 35);
		lblScopriIlTuo.setFont(new Font("Arial", Font.ITALIC, 29));
		panel.add(lblScopriIlTuo);
		
		JButton btnNewButton = new JButton("Clicca qui");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JTextPane txtpnCiao = new JTextPane();
				//txtpnCiao.setText("ciao");
				txtpnCiao.setBounds(650, 209, 380, 16);
				panel.add(txtpnCiao);
			}
		});
		btnNewButton.setBounds(670, 149, 106, 29);
		panel.add(btnNewButton);
		
		
		
		//JLabel lblNewLabel = new JLabel("New label");
		//lblNewLabel.setIcon(new ImageIcon("/Users/Marianna/Desktop/IA-Progetto/logo_standard.png"));
		//lblNewLabel.setBounds(30, 36, 356, 336);
		//panel.add(lblNewLabel);
		
		frame.setVisible(true);
		
		Risultati r = new Risultati();
		r.primaParte();
		r.secondaParte();
		r.terzaParte();
		r.risultatoUnico();
		
	}

}
