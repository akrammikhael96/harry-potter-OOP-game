package harrypotter.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class SecondFrame extends JFrame {

	private JLabel x;
	private JTextField Champ1 ;
	private JTextField Champ2 ;
	private JTextField Champ3 ;
	private JTextField Champ4 ;
	private JButton proceed;
	private JLabel y;
	
	public JTextField getChamp1() {
		return Champ1;
	}

	public JTextField getChamp2() {
		return Champ2;
	}

	public JTextField getChamp3() {
		return Champ3;
	}

	public JTextField getChamp4() {
		return Champ4;
	}

	
	public SecondFrame() {
		super();

		java.awt.Toolkit toolkit = java.awt.Toolkit.getDefaultToolkit();
		Image image = toolkit.getImage("wwwa.png");
		Cursor a = toolkit.createCustomCursor(image, new Point(this.getX(), this.getY()), "");
		this.setCursor(a);

		setTitle("Harry Potter: The Return of the Triwizard Tournament");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1100, 700);
		setLayout(null);
		setLocationRelativeTo(null);
		setResizable(false);
		
		y = new JLabel();
		x = new JLabel();
		proceed = new JButton();
		Champ1 = new JTextField();
		Champ2 = new JTextField(); 
		Champ3 = new JTextField();
		Champ4 = new JTextField();
		
		proceed.setText("PROCEED");
		proceed.setFont(new Font("JF Flat", Font.BOLD, 25));
		proceed.setBounds(700, 580, 200, 50);
		proceed.setBackground(Color.white);
		
		
		Champ1.setFont(new Font("JF Flat", Font.BOLD, 15));
		Champ2.setFont(new Font("JF Flat", Font.BOLD, 15));
		Champ3.setFont(new Font("JF Flat", Font.BOLD, 15));
		Champ4.setFont(new Font("JF Flat", Font.BOLD, 15));
		
		Champ1.setText("CHAMPION I");
		Champ2.setText("CHAMPION II");
		Champ3.setText("CHAMPION III");
		Champ4.setText("CHAMPION IV");
		Champ1.setHorizontalAlignment(JTextField.CENTER);
		Champ2.setHorizontalAlignment(JTextField.CENTER);
		Champ3.setHorizontalAlignment(JTextField.CENTER);
		Champ4.setHorizontalAlignment(JTextField.CENTER);
		
		Champ1.setBounds(650, 230, 300, 50);
		Champ2.setBounds(650, 310, 300, 50);
		Champ3.setBounds(650, 390, 300, 50);
		Champ4.setBounds(650, 470, 300, 50);
		
		y.setText("ENTER THE NAMES");
		y.setFont(new Font("JF Flat", Font.BOLD, 45));
		y.setBounds(620,120, 500, 40);
		y.setForeground(Color.white);
		
		
				
				
				
	
		
		//y.setFont(new Font("JF Flat", Font.BOLD, 20));	
		
		
		
		
		
		x.setIcon(new ImageIcon("her.jpg"));
		x.setBounds(0, 0, 1100, 700);
		
		getContentPane().add(y);
		getContentPane().add(proceed);
		getContentPane().add(Champ1);
		getContentPane().add(Champ2);
		getContentPane().add(Champ3);
		getContentPane().add(Champ4);
		getContentPane().add(x);
		
		/*proceed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ThirdFrame x = new ThirdFrame();
				SecondFrame y = new SecondFrame();
				y.setVisible(false);
				x.setVisible(true);
				
			}
		});*/

	}
	
	public JButton getProceed() {
		return proceed;
	}

	public static void main(String[] args) {

		SecondFrame x = new SecondFrame();
		x.setVisible(true);

	}

}
