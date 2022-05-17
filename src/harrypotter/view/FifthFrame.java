package harrypotter.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

public class FifthFrame extends JFrame implements ActionListener {

	private JLabel x;

	private JPanel y1;
	private JButton g;
	private JButton left;
	private JButton right;
	private JButton up;
	private JButton down;
	private JButton usePotion;
	private JButton trait;
	private JButton spell;
	private JPanel info;
	private JLabel name;
	private JLabel hp;
	private JLabel ip;
	private JLabel moves;
	private JLabel i;
	private JLabel c1;
	private JList potions;
	private JLabel inventory;
	private JLabel winners;
	public JButton getDown() {
		return down;
	}

	public JLabel getName2() {
		return name;
	}

	public JLabel getHp() {
		return hp;
	}

	public JLabel getIp() {
		return ip;
	}

	public JLabel getMoves() {
		return moves;
	}

	public JPanel getY1() {
		return y1;
	}

	public JButton getLeft() {
		return left;
	}

	public JButton getRight() {
		return right;
	}

	public JButton getUp() {
		return up;
	}

	public JButton getUsePotion() {
		return usePotion;
	}

	public JButton getTrait() {
		return trait;
	}

	public JButton getSpell() {
		return spell;
	}
	public JLabel getX2() {
		return x;
	}
	public FifthFrame() {

		java.awt.Toolkit toolkit = java.awt.Toolkit.getDefaultToolkit();
		Image image = toolkit.getImage("wwwa.png");
		Cursor a = toolkit.createCustomCursor(image, new Point(this.getX(),
				this.getY()), "");
		this.setCursor(a);

		setTitle("Harry Potter: The Return of the Triwizard Tournament");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1100, 700);
		setLayout(null);
		setLocationRelativeTo(null);
		setResizable(false);
		
		inventory= new JLabel();
		winners= new JLabel();
		i = new JLabel();
		c1 = new JLabel();
		name = new JLabel();
		hp = new JLabel();
		ip = new JLabel();
		info = new JPanel();
		trait = new JButton();
		trait.setBackground(Color.white);
		spell = new JButton();
		spell.setBackground(Color.white);
		left = new JButton();
		left.setBackground(Color.white);
		right = new JButton();
		right.setBackground(Color.white);
		up = new JButton();
		up.setBackground(Color.white);
		down = new JButton();
		down.setBackground(Color.white);
		usePotion = new JButton();
		usePotion.setBackground(Color.white);
		usePotion.setText("USE POTION");
		usePotion.setBounds(15, 280, 145, 50);
		usePotion.setFont(new Font("JF Flat", Font.BOLD, 20));
		x = new JLabel();
		y1 = new JPanel();
		moves = new JLabel();
		potions = new JList();
		moves.setText("Remaining Moves:");
		i.setText("Current Champion:");
		i.setFont(new Font("JF Flat", Font.BOLD, 20));
		i.setForeground(Color.white);
		i.setBounds(835, 40, 180, 30);
		
		inventory.setText("Inventory:");
		winners.setText("Winners:");
		inventory.setFont(new Font("JF Flat", Font.BOLD, 20));
		inventory.setForeground(Color.white);
		inventory.setBounds(10, 40, 180, 30);
		winners.setFont(new Font("JF Flat", Font.BOLD, 20));
		winners.setForeground(Color.white);
		winners.setBounds(10, 430, 180, 30);

		
		c1.setText("Choose a Spell To Cast");
		c1.setFont(new Font("JF Flat", Font.BOLD, 20));
		c1.setForeground(Color.white);
		c1.setBounds(835, 220, 280, 30);

		info.setLayout(null);
		name.setText("NAME: ");
		hp.setText("HP: ");
		ip.setText("IP: ");
		name.setBounds(840, 75, 170, 30);
		hp.setBounds(840, 105, 170, 30);
		ip.setBounds(840, 135, 170, 30);
		moves.setBounds(840, 165, 200, 30);

		name.setFont(new Font("JF Flat", Font.BOLD, 20));
		hp.setFont(new Font("JF Flat", Font.BOLD, 20));
		ip.setFont(new Font("JF Flat", Font.BOLD, 20));
		moves.setFont(new Font("JF Flat", Font.BOLD, 20));
		info.add(name);
		info.add(hp);
		info.add(ip);
		info.add(moves);
		info.setBounds(835, 70, 200, 130);

		trait.setBounds(835, 590, 190, 50);
		spell.setBounds(865, 290, 150, 50);
		trait.setText("ACTIVATE TRAIT");
		spell.setText("CAST SPELL");
		trait.setFont(new Font("JF Flat", Font.BOLD, 20));
		spell.setFont(new Font("JF Flat", Font.BOLD, 20));

		up.setBounds(900, 490, 50, 50);
		down.setBounds(900, 540, 50, 50);
		left.setBounds(850, 540, 50, 50);
		right.setBounds(950, 540, 50, 50);

		up.setIcon(new ImageIcon(
				"up.jpg"));
		left.setIcon(new ImageIcon(
				"left.jpg"));
		right.setIcon(new ImageIcon(
				"righ.jpg"));
		down.setIcon(new ImageIcon(
				"down.jpg"));

		x.setBounds(0, 0, 1100, 700);
		x.setIcon(new ImageIcon("1st.jpg"));

		y1.setLayout(new GridLayout(10, 10));
		y1.setBounds(200, 40, 600, 600);
		y1.setBackground(new Color(0, 0, 0, 0));
		/*
		 * for(int r = 0; r < 10; r++) { for(int c = 0; c < 10; c++) { JButton
		 * temp = new JButton(); cells[r][c]= temp; y1.add(temp); } }
		 */
		getContentPane().add(inventory);
		getContentPane().add(winners);
		getContentPane().add(c1);
		getContentPane().add(i);
		getContentPane().add(ip);
		getContentPane().add(hp);
		getContentPane().add(name);
		getContentPane().add(moves);
		getContentPane().add(info);
		// getContentPane().add(z);
		getContentPane().add(usePotion);
		getContentPane().add(trait);
		getContentPane().add(spell);
		getContentPane().add(up);
		getContentPane().add(down);
		getContentPane().add(left);
		getContentPane().add(right);
		getContentPane().add(y1);
		getContentPane().add(x);
		//getContentPane().add(usePotion, 1);
		// getContentPane().add(potions,1);
	}

	public static void main(String[] args) {
		FifthFrame x = new FifthFrame();
		x.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
