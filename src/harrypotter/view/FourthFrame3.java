package harrypotter.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class FourthFrame3 extends JFrame {

	private JLabel x;
	private JButton proceed;
	private JLabel y;
	private JLabel Z;
	private ArrayList<JCheckBox> s;
	public JLabel getY2() {
		return y;
	}
	public JButton getProceed() {
		return proceed;
	}

	public ArrayList<JCheckBox> getS() {
		return s;
	}


	public FourthFrame3() {

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
		s = new ArrayList<JCheckBox>();
		Z = new JLabel();
		y = new JLabel();
		proceed = new JButton();
		proceed.setBackground(Color.white);
		x = new JLabel();
		JCheckBox s1 = new JCheckBox();
		JCheckBox s2 = new JCheckBox();
		JCheckBox s3 = new JCheckBox();
		JCheckBox s4 = new JCheckBox();
		JCheckBox s5 = new JCheckBox();
		JCheckBox s6 = new JCheckBox();
		JCheckBox s7 = new JCheckBox();
		JCheckBox s8 = new JCheckBox();
		JCheckBox s9 = new JCheckBox();
		JCheckBox s10 = new JCheckBox();
		JCheckBox s11 = new JCheckBox();
		JCheckBox s12 = new JCheckBox();
		JCheckBox s13 = new JCheckBox();
		JCheckBox s14 = new JCheckBox();
		JCheckBox s15 = new JCheckBox();
		JCheckBox s16 = new JCheckBox();
		JCheckBox s17 = new JCheckBox();
		JCheckBox s18 = new JCheckBox();
		JCheckBox s19 = new JCheckBox();
		JCheckBox s20 = new JCheckBox();
		JCheckBox s21 = new JCheckBox();		
		s1.setOpaque(false);
		s2.setOpaque(false);
		s3.setOpaque(false);
		s4.setOpaque(false);
		s5.setOpaque(false);
		s6.setOpaque(false);
		s7.setOpaque(false);
		s8.setOpaque(false);
		s9.setOpaque(false);
		s10.setOpaque(false);
		s11.setOpaque(false);
		s12.setOpaque(false);
		s13.setOpaque(false);
		s14.setOpaque(false);
		s15.setOpaque(false);
		s16.setOpaque(false);
		s17.setOpaque(false);
		s18.setOpaque(false);
		s19.setOpaque(false);
		s20.setOpaque(false);
		s21.setOpaque(false);
		
		s.add(s1);
		s.add(s2);
		s.add(s3);
		s.add(s4);
		s.add(s5);
		s.add(s6);
		s.add(s7);
		s.add(s8);
		s.add(s9);
		s.add(s10);
		s.add(s11);
		s.add(s12);
		s.add(s13);
		s.add(s14);
		s.add(s15);
		s.add(s16);
		s.add(s17);
		s.add(s18);
		s.add(s19);
		s.add(s20);
		s.add(s21);

		x.setIcon(new ImageIcon("ron.jpg"));
		x.setBounds(0, 0, 1100, 700);
		
		s1.setToolTipText("DMG  Cost:150  CoolDown:5  Damage:300");
		s2.setToolTipText("DMG  Cost:100  CoolDown:2  Damage:100");
		s3.setToolTipText("DMG  Cost:400  CoolDown:1  Damage:200");
		s4.setToolTipText("DMG  Cost:50  CoolDown:2  Damage:100");
		s5.setToolTipText("DMG  Cost:150  CoolDown:4  Damage:250");
		s6.setToolTipText("DMG  Cost:200  CoolDown:5  Damage:300");
		s7.setToolTipText("DMG  Cost:300  CoolDown:3  Damage:350");
		s8.setToolTipText("DMG  Cost:500  CoolDown:10  Damage:650");
		s9.setToolTipText("DMG  Cost:400  CoolDown:6  Damage:500");
		s10.setToolTipText("DMG  Cost:300  CoolDown:2  Damage:300");
		s11.setToolTipText("DMG  Cost:200  CoolDown:7  Damage:400");
		s12.setToolTipText("HEL  Cost:50  CoolDown:2  Heal:100");
		s13.setToolTipText("HEL  Cost:150  CoolDown:8  Heal:550");
		s14.setToolTipText("HEL  Cost:200  CoolDown:4  Heal:200");
		s15.setToolTipText("HEL  Cost:300  CoolDown:1  Heal:100");
		s16.setToolTipText("HEL  Cost:100  CoolDown:3  Heal:200");
		s17.setToolTipText("HEL  Cost:50  CoolDown:1  Heal:50");
		s18.setToolTipText("REL  Cost:100  CoolDown:1  Range:1");
		s19.setToolTipText("REL  Cost:400  CoolDown:10  Range:10");
		s20.setToolTipText("REL  Cost:300  CoolDown:5  Range:5");	
		s21.setToolTipText("REL  Cost:200  CoolDown:3  Range:3");
		
		s1.setText("Sectumsempra");
		s2.setText("Reducto");
		s3.setText("Piertotum Locomotor");
		s4.setText("Oppugno");
		s5.setText("Incendio");
		s6.setText("Expulso");
		s7.setText("Bombarda");
		s8.setText("Avada Kedavra");
		s9.setText("Crucio");
		s10.setText("Igni");
		s11.setText("Kamehameha");
		s12.setText("Cheering Charm");
		s13.setText("Expecto Patronum");
		s14.setText("Ferula");
		s15.setText("Protego Horribilis");
		s16.setText("Rennervate");
		s17.setText("Quen");
		s18.setText("Accio");
		s19.setText("Imperio");
		s20.setText("Wingardium Leviosa");
		s21.setText("Axii");

		s1.setForeground(Color.white);
		s2.setForeground(Color.white);
		s3.setForeground(Color.white);
		s4.setForeground(Color.white);
		s5.setForeground(Color.white);
		s6.setForeground(Color.white);
		s7.setForeground(Color.white);
		s8.setForeground(Color.white);
		s9.setForeground(Color.white);
		s10.setForeground(Color.white);
		s11.setForeground(Color.white);
		s12.setForeground(Color.white);
		s13.setForeground(Color.white);
		s14.setForeground(Color.white);
		s15.setForeground(Color.white);
		s16.setForeground(Color.white);
		s17.setForeground(Color.white);
		s18.setForeground(Color.white);
		s19.setForeground(Color.white);
		s20.setForeground(Color.white);
		s21.setForeground(Color.white);

		s1.setBounds(700, 140, 300, 30);
		s2.setBounds(700, 160, 300, 30);
		s3.setBounds(700, 180, 300, 30);
		s4.setBounds(700, 200, 300, 30);
		s5.setBounds(700, 220, 300, 30);
		s6.setBounds(700, 240, 300, 30);
		s7.setBounds(700, 260, 300, 30);
		s8.setBounds(700, 280, 300, 30);
		s9.setBounds(700, 300, 300, 30);
		s10.setBounds(700, 320, 300, 30);
		s11.setBounds(700, 340, 300, 30);
		s12.setBounds(700, 360, 300, 30);
		s13.setBounds(700, 380, 300, 30);
		s14.setBounds(700, 400, 300, 30);
		s15.setBounds(700, 420, 300, 30);
		s16.setBounds(700, 440, 300, 30);
		s17.setBounds(700, 460, 300, 30);
		s18.setBounds(700, 480, 300, 30);
		s19.setBounds(700, 500, 300, 30);
		s20.setBounds(700, 520, 300, 30);
		s21.setBounds(700, 540, 300, 30);

		s1.setFont(new Font("JF Flat", Font.BOLD, 20));
		s2.setFont(new Font("JF Flat", Font.BOLD, 20));
		s3.setFont(new Font("JF Flat", Font.BOLD, 20));
		s4.setFont(new Font("JF Flat", Font.BOLD, 20));
		s5.setFont(new Font("JF Flat", Font.BOLD, 20));
		s6.setFont(new Font("JF Flat", Font.BOLD, 20));
		s7.setFont(new Font("JF Flat", Font.BOLD, 20));
		s8.setFont(new Font("JF Flat", Font.BOLD, 20));
		s9.setFont(new Font("JF Flat", Font.BOLD, 20));
		s10.setFont(new Font("JF Flat", Font.BOLD, 20));
		s11.setFont(new Font("JF Flat", Font.BOLD, 20));
		s12.setFont(new Font("JF Flat", Font.BOLD, 20));
		s13.setFont(new Font("JF Flat", Font.BOLD, 20));
		s14.setFont(new Font("JF Flat", Font.BOLD, 20));
		s15.setFont(new Font("JF Flat", Font.BOLD, 20));
		s16.setFont(new Font("JF Flat", Font.BOLD, 20));
		s17.setFont(new Font("JF Flat", Font.BOLD, 20));
		s18.setFont(new Font("JF Flat", Font.BOLD, 20));
		s19.setFont(new Font("JF Flat", Font.BOLD, 20));
		s20.setFont(new Font("JF Flat", Font.BOLD, 20));
		s21.setFont(new Font("JF Flat", Font.BOLD, 20));

		proceed.setText("PROCEED");
		proceed.setFont(new Font("JF Flat", Font.BOLD, 25));
		proceed.setBounds(700, 600, 200, 50);

		y.setText("CHAMPION III SPELLS");
		y.setFont(new Font("JF Flat", Font.BOLD, 45));
		y.setForeground(Color.white);
		y.setBounds(655, 55, 500, 40);

		Z.setText("(HINT:YOU MUST CHOOSE 3 SPELLS)");
		Z.setFont(new Font("JF Flat", Font.BOLD, 20));
		Z.setForeground(Color.white);
		Z.setBounds(655, 90, 500, 40);

		getContentPane().add(Z);
		getContentPane().add(y);
		getContentPane().add(proceed);
		getContentPane().add(s1);
		getContentPane().add(s2);
		getContentPane().add(s3);
		getContentPane().add(s4);
		getContentPane().add(s5);
		getContentPane().add(s6);
		getContentPane().add(s7);
		getContentPane().add(s8);
		getContentPane().add(s9);
		getContentPane().add(s10);
		getContentPane().add(s11);
		getContentPane().add(s12);
		getContentPane().add(s13);
		getContentPane().add(s14);
		getContentPane().add(s15);
		getContentPane().add(s16);
		getContentPane().add(s17);
		getContentPane().add(s18);
		getContentPane().add(s19);
		getContentPane().add(s20);
		getContentPane().add(s21);
		getContentPane().add(x);
		
		/*proceed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FourthFrame3 x = new FourthFrame3();
				FourthFrame4 y = new FourthFrame4();
				x.setVisible(false);
				y.setVisible(true);

			}
		});*/
		

	}

	public static void main(String[] args) {

		FourthFrame3 x = new FourthFrame3();
		x.setVisible(true);

	}

}
