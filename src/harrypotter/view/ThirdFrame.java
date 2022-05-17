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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ThirdFrame extends JFrame {
	
	private JLabel y;
	private JLabel c1;
	private JLabel c2;
	private JLabel c3;
	private JLabel c4;
	private JLabel x;
	private JComboBox ch1;
	private JComboBox ch2;
	private JComboBox ch3;
	private JComboBox ch4;
	private String p1;
	private String p2;
	private String p3;
	private String p4;
	private JButton proceed;
	
	public String getP1() {
		return p1;
	}

	public JLabel getC1() {
		return c1;
	}



	public JLabel getC2() {
		return c2;
	}



	public JLabel getC3() {
		return c3;
	}



	public JLabel getC4() {
		return c4;
	}







	public String getP2() {
		return p2;
	}



	public String getP3() {
		return p3;
	}



	public String getP4() {
		return p4;
	}



	public void setP1(String p1) {
		this.p1 = p1;
	}



	public void setP2(String p2) {
		this.p2 = p2;
	}



	public void setP3(String p3) {
		this.p3 = p3;
	}



	public void setP4(String p4) {
		this.p4 = p4;
	}

	public JComboBox getCh1() {
		return ch1;
	}



	public JComboBox getCh2() {
		return ch2;
	}



	public JComboBox getCh3() {
		return ch3;
	}



	public JComboBox getCh4() {
		return ch4;
	}
	
	
	
	public JButton getProceed() {
		return proceed;
	}



	public ThirdFrame(){
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
		
		String houses[]={"GRYFFINDOR HOUSE","SLYTHERIN HOUSE","HUFFLEPUFF HOUSE","RAVENCLAW HOUSE"};
		
		y = new JLabel();
		c1 = new JLabel();
		c2 = new JLabel();
		c3 = new JLabel();
		c4 = new JLabel();
		x = new JLabel();
		proceed = new JButton();
		proceed.setBackground(Color.white);
		ch1 = new JComboBox(houses);
		ch2 = new JComboBox(houses);
		ch3 = new JComboBox(houses);
		ch4 = new JComboBox(houses);
		
		y.setText("CHOOSE THE HOUSES");
		y.setFont(new Font("JF Flat", Font.BOLD, 45));
		y.setForeground(Color.white);
		y.setBounds(40,80, 500, 40);
		
		ch1.setBounds(100, 200, 250, 40);
		ch2.setBounds(100, 300, 250, 40);
		ch3.setBounds(100, 400, 250, 40);
		ch4.setBounds(100, 500, 250, 40);
		
		c1.setText(p1+"Belongs To :");
		c2.setText(p2+"Belongs To :");
		c3.setText(p3+"Belongs To :");
		c4.setText(p4+"Belongs To :");
		
		
		
		c1.setBounds(80, 180, 300, 30);
		c2.setBounds(80, 280, 300, 30);
		c3.setBounds(80, 380, 300, 30);	
		c4.setBounds(80, 480, 300, 30);
		
		c1.setForeground(Color.white);
		c2.setForeground(Color.white);
		c3.setForeground(Color.white);
		c4.setForeground(Color.white);
		
		c1.setFont(new Font("JF Flat", Font.BOLD, 20));
		c2.setFont(new Font("JF Flat", Font.BOLD, 20));
		c3.setFont(new Font("JF Flat", Font.BOLD, 20));
		c4.setFont(new Font("JF Flat", Font.BOLD, 20));
		
		proceed.setText("PROCEED");
		proceed.setFont(new Font("JF Flat", Font.BOLD, 25));
		proceed.setBounds(120, 580, 200, 50);
		
		
		
		
		
		
		x.setIcon(new ImageIcon("his.jpg"));
		x.setBounds(0, 0, 1100, 700);
		
		getContentPane().add(y);
		getContentPane().add(c1);
		getContentPane().add(c2);
		getContentPane().add(c3);
		getContentPane().add(c4);
		getContentPane().add(proceed);
		getContentPane().add(ch1);
		getContentPane().add(ch2);
		getContentPane().add(ch3);
		getContentPane().add(ch4);
		getContentPane().add(x);
		
		/*proceed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ThirdFrame x = new ThirdFrame();
				FourthFrame1 y = new FourthFrame1();
				x.setVisible(false);
				y.setVisible(true);
				System.out.println(ch1.getSelectedItem());
			}

		});*/
		
		
		
		
	}
	
	
	
	

	public static void main(String[] args) {
		
		ThirdFrame x = new ThirdFrame();
		x.setVisible(true);

	}

}
