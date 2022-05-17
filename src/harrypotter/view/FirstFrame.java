package harrypotter.view;

import java.awt.Color;
import java.awt.Cursor;
import javax.swing.*;

import java.awt.event.*;
import java.awt.*;
import java.util.*;

import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class FirstFrame extends JFrame implements ActionListener {

	private JButton start;
	private JButton exit;
	private JLabel y;

	public FirstFrame() {

		super();
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
		// setExtendedState(MAXIMIZED_BOTH);
		// setResizable(false);

		y = new JLabel();
		start = new JButton();
		exit = new JButton();

		y.setBounds(0, 0, 1100, 700);
		y.setIcon(new ImageIcon("2ff.jpg"));

		exit.setText("EXIT");
		exit.setFont(new Font("JF Flat", Font.BOLD, 25));
		exit.setBounds(670, 510, 150, 60); // 2f
		exit.setBackground(Color.white);
		start.setText("PRESS HERE TO START");
		start.setBackground(Color.white);
		start.setFont(new Font("JF Flat", Font.BOLD, 25));
		start.setBounds(550, 420, 400, 60); // 2f
		// start.setBounds(110, 430, 400, 60); //1f
		start.setName("st");
		exit.setName("ex");

		getContentPane().add(exit);
		getContentPane().add(start);
		getContentPane().add(y);

		/*
		 * start.addActionListener(new ActionListener() { public void
		 * actionPerformed(ActionEvent e) { FirstFrame x = new FirstFrame();
		 * SecondFrame y = new SecondFrame(); x.setVisible(false);
		 * y.setVisible(true);
		 * 
		 * } });
		 * 
		 * 
		 * exit.addActionListener(new ActionListener() { public void
		 * actionPerformed(ActionEvent e) { System.exit(0);
		 * 
		 * } });
		 */
	}

	public JButton getStart() {
		return start;
	}

	public JButton getExit() {
		return exit;
	}

	public static void main(String[] args) {
		FirstFrame f = new FirstFrame();
		f.setVisible(true);

	}

	public void init() {
		start = new JButton();
	}

	public void actionPerformed(ActionEvent e) {

	}

}
