package harrypotter.controller;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import harrypotter.exceptions.InCooldownException;
import harrypotter.exceptions.InvalidTargetCellException;
import harrypotter.exceptions.NotEnoughIPException;
import harrypotter.exceptions.OutOfBordersException;
import harrypotter.exceptions.OutOfRangeException;
import harrypotter.model.character.Champion;
import harrypotter.model.character.GryffindorWizard;
import harrypotter.model.character.HufflepuffWizard;
import harrypotter.model.character.RavenclawWizard;
import harrypotter.model.character.SlytherinWizard;
import harrypotter.model.character.Wizard;
import harrypotter.model.magic.Collectible;
import harrypotter.model.magic.DamagingSpell;
import harrypotter.model.magic.HealingSpell;
import harrypotter.model.magic.Potion;
import harrypotter.model.magic.RelocatingSpell;
import harrypotter.model.magic.Spell;
import harrypotter.model.tournament.FirstTask;
import harrypotter.model.tournament.SecondTask;
import harrypotter.model.tournament.Task;
import harrypotter.model.tournament.ThirdTask;
import harrypotter.model.tournament.Tournament;
import harrypotter.model.world.Cell;
import harrypotter.model.world.ChampionCell;
import harrypotter.model.world.Direction;
import harrypotter.model.world.EmptyCell;
import harrypotter.model.world.Merperson;
import harrypotter.model.world.ObstacleCell;
import harrypotter.model.world.PhysicalObstacle;
import harrypotter.model.world.TreasureCell;
import harrypotter.model.world.WallCell;
import harrypotter.view.FifthFrame;
import harrypotter.view.FirstFrame;
import harrypotter.view.FourthFrame1;
import harrypotter.view.FourthFrame2;
import harrypotter.view.FourthFrame3;
import harrypotter.view.FourthFrame4;
import harrypotter.view.SecondFrame;
import harrypotter.view.ThirdFrame;

public class Game {
	private FirstFrame firstFrame;
	private SecondFrame secondFrame;
	private ThirdFrame thirdFrame;
	private FourthFrame1 fourthFrame1;
	private FourthFrame2 fourthFrame2;
	private FourthFrame3 fourthFrame3;
	private FourthFrame4 fourthFrame4;
	private FifthFrame fifthFrame;
	private Tournament tournament;
	private String[] players;
	private JButton[][] cells;
	private JLabel currentChampName;
	private JLabel currentChampIp;
	private JLabel currentChampHp;
	private JLabel currentChampMoves;
	private JComboBox z;
	private JList potions;
	private Task currentTask;
	private JLabel w1;
	private JLabel w2;
	private JLabel w3;
	private JLabel w4;

	public Task getCurrentTask() {
		return currentTask;
	}

	public Game() throws IOException {
		firstFrame = new FirstFrame();
		firstFrame.setVisible(true);
		secondFrame = new SecondFrame();
		thirdFrame = new ThirdFrame();
		fourthFrame1 = new FourthFrame1();
		fourthFrame2 = new FourthFrame2();
		fourthFrame3 = new FourthFrame3();
		fourthFrame4 = new FourthFrame4();
		fifthFrame = new FifthFrame();
		cells = new JButton[10][10];
		firstFrame.getStart().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				secondFrame.setVisible(true);
				firstFrame.setVisible(false);
			}
		});
		secondFrame.getProceed().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				secondFrame.setVisible(false);
				players = new String[4];
				players[0] = secondFrame.getChamp1().getText();
				players[1] = secondFrame.getChamp2().getText();
				players[2] = secondFrame.getChamp3().getText();
				players[3] = secondFrame.getChamp4().getText();
				thirdFrame.getC1().setText(players[0] + " Belongs To :");
				thirdFrame.getC2().setText(players[1] + " Belongs To :");
				thirdFrame.getC3().setText(players[2] + " Belongs To :");
				thirdFrame.getC4().setText(players[3] + " Belongs To :");
				fourthFrame1.getY2().setText(players[0] + "'s" + " Spells");
				fourthFrame2.getY2().setText(players[1] + "'s" + " Spells");
				fourthFrame3.getY2().setText(players[2] + "'s" + " Spells");
				fourthFrame4.getY2().setText(players[3] + "'s" + " Spells");
				thirdFrame.setVisible(true);
			}
		});
		thirdFrame.getProceed().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				thirdFrame.setVisible(false);
				String[] h = new String[4];
				h[0] = (String) thirdFrame.getCh1().getSelectedItem();
				h[1] = (String) thirdFrame.getCh2().getSelectedItem();
				h[2] = (String) thirdFrame.getCh3().getSelectedItem();
				h[3] = (String) thirdFrame.getCh4().getSelectedItem();
				fourthFrame1.setVisible(true);
				for (int i = 0; i < 4; i++) {
					if (h[i].equals("GRYFFINDOR HOUSE"))
						tournament
								.addChampion(new GryffindorWizard(players[i]));
					if (h[i].equals("SLYTHERIN HOUSE"))
						tournament.addChampion(new SlytherinWizard(players[i]));
					if (h[i].equals("RAVENCLAW HOUSE"))
						tournament.addChampion(new RavenclawWizard(players[i]));
					if (h[i].equals("HUFFLEPUFF HOUSE"))
						tournament
								.addChampion(new HufflepuffWizard(players[i]));
				}
			}

		});
		fourthFrame1.getProceed().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<JCheckBox> s = fourthFrame1.getS();
				ArrayList<String> selected = new ArrayList<String>();
				for (JCheckBox c : s) {
					if (c.isSelected()) {
						selected.add(c.getText());
					}
				}
				if (selected.size() == 3) {
					Wizard c = (Wizard) tournament.getChampions().get(0);
					for (int i = 0; i < 3; i++) {
						Spell spell = null;
						switch (selected.get(i)) {
						case "Sectumsempra":
							spell = new DamagingSpell("SectumSempra", 150, 5,
									300);
							break;
						case "Reducto":
							spell = new DamagingSpell("Reducto", 100, 2, 100);
							break;
						case "Piertotum Locomotor":
							spell = new DamagingSpell("Piertotum Locomotor",
									400, 1, 200);
							break;
						case "Oppugno":
							spell = new DamagingSpell("Oppugno", 50, 2, 100);
							break;
						case "Incendio":
							spell = new DamagingSpell("Incendio", 150, 4, 250);
							break;
						case "Expulso":
							spell = new DamagingSpell("Expulso", 200, 5, 300);
							break;
						case "Bombarda":
							spell = new DamagingSpell("Bombarda", 300, 3, 350);
							break;
						case "Avada Kedavra":
							spell = new DamagingSpell("Avada Kedavra", 500, 10,
									650);
							break;
						case "Crucio":
							spell = new DamagingSpell("Crucio", 400, 6, 500);
							break;
						case "Igni":
							spell = new DamagingSpell("Igni", 300, 2, 300);
							break;
						case "Kamehameha":
							spell = new DamagingSpell("Kamehameha", 200, 7, 400);
							break;
						case "Cheering Charm":
							spell = new HealingSpell("Cheering Charm", 50, 2,
									100);
							break;
						case "Expecto Patronum":
							spell = new HealingSpell("Expecto Patronum", 150,
									8, 550);
							break;
						case "Ferula":
							spell = new HealingSpell("Ferula", 200, 4, 200);
							break;
						case "Protego Horribilis":
							spell = new HealingSpell("Protego Horribilis", 300,
									1, 100);
							break;
						case "Rennervate":
							spell = new HealingSpell("Rennervate", 100, 3, 200);
							break;
						case "Quen":
							spell = new HealingSpell("Quen", 50, 1, 50);
							break;
						case "Accio":
							spell = new RelocatingSpell("Accio", 100, 1, 1);
							break;
						case "Imperio":
							spell = new RelocatingSpell("Imperio", 400, 10, 10);
							break;
						case "Wingardium Leviosa":
							spell = new RelocatingSpell("Wingardium Leviosa",
									300, 5, 5);
							break;
						case "Axii":
							spell = new RelocatingSpell("Axii", 200, 3, 3);
							break;
						default:
							break;
						}
						c.getSpells().add(spell);
						fourthFrame1.setVisible(false);
						fourthFrame2.setVisible(true);
					}
				}
			}
		});
		fourthFrame2.getProceed().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<JCheckBox> s = fourthFrame2.getS();
				ArrayList<String> selected = new ArrayList<String>();
				for (JCheckBox c : s) {
					if (c.isSelected()) {
						selected.add(c.getText());
					}
				}
				if (selected.size() == 3) {
					Wizard c = (Wizard) tournament.getChampions().get(1);
					for (int i = 0; i < 3; i++) {
						Spell spell = null;
						switch (selected.get(i)) {
						case "Sectumsempra":
							spell = new DamagingSpell("SectumSempra", 150, 5,
									300);
							break;
						case "Reducto":
							spell = new DamagingSpell("Reducto", 100, 2, 100);
							break;
						case "Piertotum Locomotor":
							spell = new DamagingSpell("Piertotum Locomotor",
									400, 1, 200);
							break;
						case "Oppugno":
							spell = new DamagingSpell("Oppugno", 50, 2, 100);
							break;
						case "Incendio":
							spell = new DamagingSpell("Incendio", 150, 4, 250);
							break;
						case "Expulso":
							spell = new DamagingSpell("Expulso", 200, 5, 300);
							break;
						case "Bombarda":
							spell = new DamagingSpell("Bombarda", 300, 3, 350);
							break;
						case "Avada Kedavra":
							spell = new DamagingSpell("Avada Kedavra", 500, 10,
									650);
							break;
						case "Crucio":
							spell = new DamagingSpell("Crucio", 400, 6, 500);
							break;
						case "Igni":
							spell = new DamagingSpell("Igni", 300, 2, 300);
							break;
						case "Kamehameha":
							spell = new DamagingSpell("Kamehameha", 200, 7, 400);
							break;
						case "Cheering Charm":
							spell = new HealingSpell("Cheering Charm", 50, 2,
									100);
							break;
						case "Expecto Patronum":
							spell = new HealingSpell("Expecto Patronum", 150,
									8, 550);
							break;
						case "Ferula":
							spell = new HealingSpell("Ferula", 200, 4, 200);
							break;
						case "Protego Horribilis":
							spell = new HealingSpell("Protego Horribilis", 300,
									1, 100);
							break;
						case "Rennervate":
							spell = new HealingSpell("Rennervate", 100, 3, 200);
							break;
						case "Quen":
							spell = new HealingSpell("Quen", 50, 1, 50);
							break;
						case "Accio":
							spell = new RelocatingSpell("Accio", 100, 1, 1);
							break;
						case "Imperio":
							spell = new RelocatingSpell("Imperio", 400, 10, 10);
							break;
						case "Wingardium Leviosa":
							spell = new RelocatingSpell("Wingardium Leviosa",
									300, 5, 5);
							break;
						case "Axii":
							spell = new RelocatingSpell("Axii", 200, 3, 3);
							break;
						default:
							break;
						}
						c.getSpells().add(spell);
						fourthFrame2.setVisible(false);
						fourthFrame3.setVisible(true);
					}
				}
			}
		});

		fourthFrame3.getProceed().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<JCheckBox> s = fourthFrame3.getS();
				ArrayList<String> selected = new ArrayList<String>();
				for (JCheckBox c : s) {
					if (c.isSelected()) {
						selected.add(c.getText());
					}
				}
				if (selected.size() == 3) {
					Wizard c = (Wizard) tournament.getChampions().get(2);
					for (int i = 0; i < 3; i++) {
						Spell spell = null;
						switch (selected.get(i)) {
						case "Sectumsempra":
							spell = new DamagingSpell("SectumSempra", 150, 5,
									300);
							break;
						case "Reducto":
							spell = new DamagingSpell("Reducto", 100, 2, 100);
							break;
						case "Piertotum Locomotor":
							spell = new DamagingSpell("Piertotum Locomotor",
									400, 1, 200);
							break;
						case "Oppugno":
							spell = new DamagingSpell("Oppugno", 50, 2, 100);
							break;
						case "Incendio":
							spell = new DamagingSpell("Incendio", 150, 4, 250);
							break;
						case "Expulso":
							spell = new DamagingSpell("Expulso", 200, 5, 300);
							break;
						case "Bombarda":
							spell = new DamagingSpell("Bombarda", 300, 3, 350);
							break;
						case "Avada Kedavra":
							spell = new DamagingSpell("Avada Kedavra", 500, 10,
									650);
							break;
						case "Crucio":
							spell = new DamagingSpell("Crucio", 400, 6, 500);
							break;
						case "Igni":
							spell = new DamagingSpell("Igni", 300, 2, 300);
							break;
						case "Kamehameha":
							spell = new DamagingSpell("Kamehameha", 200, 7, 400);
							break;
						case "Cheering Charm":
							spell = new HealingSpell("Cheering Charm", 50, 2,
									100);
							break;
						case "Expecto Patronum":
							spell = new HealingSpell("Expecto Patronum", 150,
									8, 550);
							break;
						case "Ferula":
							spell = new HealingSpell("Ferula", 200, 4, 200);
							break;
						case "Protego Horribilis":
							spell = new HealingSpell("Protego Horribilis", 300,
									1, 100);
							break;
						case "Rennervate":
							spell = new HealingSpell("Rennervate", 100, 3, 200);
							break;
						case "Quen":
							spell = new HealingSpell("Quen", 50, 1, 50);
							break;
						case "Accio":
							spell = new RelocatingSpell("Accio", 100, 1, 1);
							break;
						case "Imperio":
							spell = new RelocatingSpell("Imperio", 400, 10, 10);
							break;
						case "Wingardium Leviosa":
							spell = new RelocatingSpell("Wingardium Leviosa",
									300, 5, 5);
							break;
						case "Axii":
							spell = new RelocatingSpell("Axii", 200, 3, 3);
							break;
						default:
							break;
						}
						c.getSpells().add(spell);
						fourthFrame3.setVisible(false);
						fourthFrame4.setVisible(true);
					}
				}
			}
		});
		firstFrame.getExit().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);

			}
		});
		fourthFrame4.getProceed().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<JCheckBox> s = fourthFrame4.getS();
				ArrayList<String> selected = new ArrayList<String>();
				for (JCheckBox c : s) {
					if (c.isSelected()) {
						selected.add(c.getText());
					}
				}
				if (selected.size() == 3) {
					Wizard c = (Wizard) tournament.getChampions().get(3);
					for (int i = 0; i < 3; i++) {
						Spell spell = null;
						switch (selected.get(i)) {
						case "Sectumsempra":
							spell = new DamagingSpell("SectumSempra", 150, 5,
									300);
							break;
						case "Reducto":
							spell = new DamagingSpell("Reducto", 100, 2, 100);
							break;
						case "Piertotum Locomotor":
							spell = new DamagingSpell("Piertotum Locomotor",
									400, 1, 200);
							break;
						case "Oppugno":
							spell = new DamagingSpell("Oppugno", 50, 2, 100);
							break;
						case "Incendio":
							spell = new DamagingSpell("Incendio", 150, 4, 250);
							break;
						case "Expulso":
							spell = new DamagingSpell("Expulso", 200, 5, 300);
							break;
						case "Bombarda":
							spell = new DamagingSpell("Bombarda", 300, 3, 350);
							break;
						case "Avada Kedavra":
							spell = new DamagingSpell("Avada Kedavra", 500, 10,
									650);
							break;
						case "Crucio":
							spell = new DamagingSpell("Crucio", 400, 6, 500);
							break;
						case "Igni":
							spell = new DamagingSpell("Igni", 300, 2, 300);
							break;
						case "Kamehameha":
							spell = new DamagingSpell("Kamehameha", 200, 7, 400);
							break;
						case "Cheering Charm":
							spell = new HealingSpell("Cheering Charm", 50, 2,
									100);
							break;
						case "Expecto Patronum":
							spell = new HealingSpell("Expecto Patronum", 150,
									8, 550);
							break;
						case "Ferula":
							spell = new HealingSpell("Ferula", 200, 4, 200);
							break;
						case "Protego Horribilis":
							spell = new HealingSpell("Protego Horribilis", 300,
									1, 100);
							break;
						case "Rennervate":
							spell = new HealingSpell("Rennervate", 100, 3, 200);
							break;
						case "Quen":
							spell = new HealingSpell("Quen", 50, 1, 50);
							break;
						case "Accio":
							spell = new RelocatingSpell("Accio", 100, 1, 1);
							break;
						case "Imperio":
							spell = new RelocatingSpell("Imperio", 400, 10, 10);
							break;
						case "Wingardium Leviosa":
							spell = new RelocatingSpell("Wingardium Leviosa",
									300, 5, 5);
							break;
						case "Axii":
							spell = new RelocatingSpell("Axii", 200, 3, 3);
							break;
						default:
							break;
						}
						c.getSpells().add(spell);
						try {
							tournament.beginTournament();
							currentTask = tournament.getFirstTask();
						} catch (IOException e2) {
						}
					}
					fourthFrame4.setVisible(false);
					fifthFrame.setVisible(true);
					fifthFrame.getName2().setText(
							"Name : "
									+ ((Wizard) getCurrentTask()
											.getCurrentChamp()).getName());
					fifthFrame.getIp().setText(
							"Ip : "
									+ ((Wizard) getCurrentTask()
											.getCurrentChamp()).getIp());
					fifthFrame.getHp().setText(
							"Hp : "
									+ ((Wizard) getCurrentTask()
											.getCurrentChamp()).getHp());
					fifthFrame.getMoves().setText(
							"Remaining Moves : "
									+ getCurrentTask().getAllowedMoves());
					ArrayList<Spell> spells = ((Wizard) tournament
							.getFirstTask().getCurrentChamp()).getSpells();
					String[] sp = new String[3];
					for (Spell s2 : spells) {
						sp[spells.indexOf(s2)] = s2.getName();
					}
					if (getCurrentTask().getCurrentChamp() instanceof GryffindorWizard) {
						fifthFrame
								.getTrait()
								.setToolTipText(
										"Trait Description: This Turn, The Champion Can Make 2 Moves Instead Of 1");
					}
					if (getCurrentTask().getCurrentChamp() instanceof SlytherinWizard
							&& ((getCurrentTask() instanceof FirstTask) || (getCurrentTask() instanceof SecondTask))) {
						fifthFrame
								.getTrait()
								.setToolTipText(
										"Trait Description: Traverse Two Cells In One Move");

					}
					if (getCurrentTask().getCurrentChamp() instanceof SlytherinWizard
							&& getCurrentTask() instanceof ThirdTask) {
						fifthFrame
								.getTrait()
								.setToolTipText(
										"Trait Description: Moving Through a Wall Given That The Cell He Ends Up in is Not Another Wall or Traverse Two Cells Instead Of One");

					}
					if (getCurrentTask().getCurrentChamp() instanceof HufflepuffWizard
							&& getCurrentTask() instanceof FirstTask) {
						fifthFrame
								.getTrait()
								.setToolTipText(
										"Trait Description: This Turn, The Dragon Doesn�t Attack");

					}
					if (getCurrentTask().getCurrentChamp() instanceof HufflepuffWizard
							&& getCurrentTask() instanceof SecondTask) {
						fifthFrame
								.getTrait()
								.setToolTipText(
										"Trait Description: This Turn, The Merpeople Won�t Do Any Damage");

					}

					if (getCurrentTask().getCurrentChamp() instanceof HufflepuffWizard
							&& getCurrentTask() instanceof ThirdTask) {
						fifthFrame
								.getTrait()
								.setToolTipText(
										"Trait Description: Attacks From Other Champions Will Only Deal Half The Damage");

					}
					if (getCurrentTask().getCurrentChamp() instanceof RavenclawWizard
							&& getCurrentTask() instanceof FirstTask) {
						fifthFrame
								.getTrait()
								.setToolTipText(
										"Trait Description: This Turn, the champion is shown where the dragon is going to attack");

					}
					if (getCurrentTask().getCurrentChamp() instanceof RavenclawWizard
							&& getCurrentTask() instanceof SecondTask) {
						fifthFrame
								.getTrait()
								.setToolTipText(
										"Trait Description: his turn, the champion is given a hint on where the target is hidden relative to the current position (left or right + up or down)");

					}
					if (getCurrentTask().getCurrentChamp() instanceof RavenclawWizard
							&& getCurrentTask() instanceof ThirdTask) {
						fifthFrame
								.getTrait()
								.setToolTipText(
										"Trait Description: This turn, the champion is given a hint on where the cup is hidden relative to the current position (left or right + up or down)");

					}

					DefaultListModel<String> model = new DefaultListModel<>();
					potions = new JList(model);
					potions.setBounds(10, 70, 150, 200);
					fifthFrame.getContentPane().add(potions, 1);
					z = new JComboBox(sp);
					z.setBounds(845, 250, 180, 30);
					fifthFrame.getContentPane().add(z, 1);
					fifthFrame.validate();
					fifthFrame.repaint();
					Cell[][] map = getCurrentTask().getMap();
					int count = 0;
					for (int i1 = 0; i1 < 10; i1++) {
						for (int j = 0; j < 10; j++) {
							Cell cell = map[i1][j];
							if (cell instanceof ChampionCell) {
								Wizard w = (Wizard) ((ChampionCell) cell)
										.getChamp();
								if (w instanceof GryffindorWizard) {
									count++;
									cells[i1][j] = new JButton();
									cells[i1][j].setIcon(new ImageIcon(
											"fgry.png"));
									String name = w.getName();
									int hp = w.getHp();
									int ip = w.getIp();
									cells[i1][j]
											.setToolTipText("Gryffindor Wizard Name: "
													+ name
													+ ", "
													+ "HP: "
													+ hp
													+ ", " + "IP: " + ip);
									if (getCurrentTask().getCurrentChamp()
											.equals(w)) {
										if(w instanceof GryffindorWizard)
											cells[i1][j].setIcon(new ImageIcon(
													"fgry.gif"));
										if(w instanceof SlytherinWizard)
											cells[i1][j].setIcon(new ImageIcon(
													"fsly.gif"));
										if(w instanceof HufflepuffWizard)
											cells[i1][j].setIcon(new ImageIcon(
													"fhuf.gif"));
										if(w instanceof RavenclawWizard)
											cells[i1][j].setIcon(new ImageIcon(
													"frav.gif"));
										
										//cells[i1][j].setBackground(Color.GREEN);
									}
									fifthFrame.getY1().add(cells[i1][j]);
								} else if (w instanceof SlytherinWizard) {
									count++;
									cells[i1][j] = new JButton();
									cells[i1][j].setIcon(new ImageIcon(
											"fsly.png"));
									String name = w.getName();
									int hp = w.getHp();
									int ip = w.getIp();
									cells[i1][j]
											.setToolTipText("Slytherin Wizard Name: "
													+ name
													+ ", "
													+ "HP: "
													+ hp
													+ ", " + "IP: " + ip);
									if (getCurrentTask().getCurrentChamp()
											.equals(w)) {
										if(w instanceof GryffindorWizard)
											cells[i1][j].setIcon(new ImageIcon(
													"fgry.gif"));
										if(w instanceof SlytherinWizard)
											cells[i1][j].setIcon(new ImageIcon(
													"fsly.gif"));
										if(w instanceof HufflepuffWizard)
											cells[i1][j].setIcon(new ImageIcon(
													"fhuf.gif"));
										if(w instanceof RavenclawWizard)
											cells[i1][j].setIcon(new ImageIcon(
													"frav.gif"));
										
										//cells[i1][j].setBackground(Color.GREEN);
									}
									fifthFrame.getY1().add(cells[i1][j]);
								} else if (w instanceof RavenclawWizard) {
									count++;
									cells[i1][j] = new JButton();
									cells[i1][j].setIcon(new ImageIcon(
											"frav.png"));
									String name = w.getName();
									int hp = w.getHp();
									int ip = w.getIp();
									cells[i1][j]
											.setToolTipText("Ravenclaw Wizard Name: "
													+ name
													+ ", "
													+ "HP: "
													+ hp
													+ ", " + "IP: " + ip);
									if (getCurrentTask().getCurrentChamp()
											.equals(w)) {
										if(w instanceof GryffindorWizard)
											cells[i1][j].setIcon(new ImageIcon(
													"fgry.gif"));
										if(w instanceof SlytherinWizard)
											cells[i1][j].setIcon(new ImageIcon(
													"fsly.gif"));
										if(w instanceof HufflepuffWizard)
											cells[i1][j].setIcon(new ImageIcon(
													"fhuf.gif"));
										if(w instanceof RavenclawWizard)
											cells[i1][j].setIcon(new ImageIcon(
													"frav.gif"));
										
										//cells[i1][j].setBackground(Color.GREEN);
									}
									fifthFrame.getY1().add(cells[i1][j]);
								} else if (w instanceof HufflepuffWizard) {
									count++;
									cells[i1][j] = new JButton();
									cells[i1][j].setIcon(new ImageIcon(
											"fhuf.png"));
									String name = w.getName();
									int hp = w.getHp();
									int ip = w.getIp();
									cells[i1][j]
											.setToolTipText("Hufflepuff Wizard Name: "
													+ name
													+ ", "
													+ "HP: "
													+ hp
													+ ", " + "IP: " + ip);
									if (getCurrentTask().getCurrentChamp()
											.equals(w)) {
										if(w instanceof GryffindorWizard)
											cells[i1][j].setIcon(new ImageIcon(
													"fgry.gif"));
										if(w instanceof SlytherinWizard)
											cells[i1][j].setIcon(new ImageIcon(
													"fsly.gif"));
										if(w instanceof HufflepuffWizard)
											cells[i1][j].setIcon(new ImageIcon(
													"fhuf.gif"));
										if(w instanceof RavenclawWizard)
											cells[i1][j].setIcon(new ImageIcon(
													"frav.gif"));
										
										//cells[i1][j].setBackground(Color.GREEN);
									}
									fifthFrame.getY1().add(cells[i1][j]);
								}
							} else if (cell instanceof WallCell) {
								count++;
								cells[i1][j] = new JButton();
								cells[i1][j]
										.setIcon(new ImageIcon("messi.png"));
								fifthFrame.getY1().add(cells[i1][j]);
							} else if (cell instanceof TreasureCell) {
								count++;
								cells[i1][j] = new JButton();
								//cells[i1][j].setBackground(Color.BLACK);
								fifthFrame.getY1().add(cells[i1][j]);
							} else if (cell instanceof ObstacleCell) {
								count++;
								cells[i1][j] = new JButton();
								int hp = ((ObstacleCell) cell).getObstacle()
										.getHp();
								cells[i1][j].setToolTipText("HP: " + hp);
								if (((ObstacleCell) cell).getObstacle() instanceof PhysicalObstacle) {
									cells[i1][j].setIcon(new ImageIcon(
											"obb.png"));
								} else if (((ObstacleCell) cell).getObstacle() instanceof Merperson) {
									int damage = ((Merperson) ((ObstacleCell) cell)
											.getObstacle()).getDamage();
									cells[i1][j].setIcon(new ImageIcon(
											"jmer.png"));
									cells[i1][j].setToolTipText("HP: " + hp
											+ ", " + "Damage: " + damage);
								}
								fifthFrame.getY1().add(cells[i1][j]);
							} else if (i1 == 4 && j == 4
									&& getCurrentTask() instanceof FirstTask) {
								count++;
								cells[i1][j] = new JButton();
								cells[i1][j].setIcon(new ImageIcon("egg1.png"));
								fifthFrame.getY1().add(cells[i1][j]);
							} else {
								count++;
								cells[i1][j] = new JButton();
								fifthFrame.getY1().add(cells[i1][j]);
							}

						}
					}
				}
			}
		});

		fifthFrame.getLeft().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Point> markedCells = null;
				if (getCurrentTask() instanceof FirstTask) {
					markedCells = ((FirstTask) getCurrentTask())
							.getMarkedCells();
				}
				Boolean ex = false;
				try {
					getCurrentTask().moveLeft();
				} catch (OutOfBordersException e1) {
					ex = true;
					JOptionPane.showMessageDialog(fifthFrame, e1.getMessage());

				} catch (InvalidTargetCellException e1) {
					ex = true;
					JOptionPane.showMessageDialog(fifthFrame, e1.getMessage());

				} catch (IOException e1) {
					ex = true;
				}
				updateMap();
				if (!ex && (!getCurrentTask().isTraitActivated())
						&& getCurrentTask() instanceof FirstTask) {
					for (Point point : markedCells) {
						// if(!(cells[(int) point.getX()][(int)
						// point.getY()].getBackground().toString().equals("java.awt.Color[r=0,g=255,b=255]")))
						cells[(int) point.getX()][(int) point.getY()].setOpaque(true);
						cells[(int) point.getX()][(int) point.getY()]
								.setBackground(Color.red);
					}
				}
			}

		});
		fifthFrame.getUp().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Point> markedCells = null;
				if (getCurrentTask() instanceof FirstTask)
					markedCells = ((FirstTask) getCurrentTask())
							.getMarkedCells();
				Boolean ex = false;
				try {
					getCurrentTask().moveForward();
				} catch (OutOfBordersException e1) {
					ex = true;
					JOptionPane.showMessageDialog(fifthFrame, e1.getMessage());

				} catch (InvalidTargetCellException e1) {
					ex = true;
					JOptionPane.showMessageDialog(fifthFrame, e1.getMessage());

				} catch (IOException e1) {
					ex = true;
				}
				updateMap();
				if (!ex && (!getCurrentTask().isTraitActivated())
						&& getCurrentTask() instanceof FirstTask) {
					for (Point point : markedCells) {
						cells[(int) point.getX()][(int) point.getY()].setOpaque(true);
						cells[(int) point.getX()][(int) point.getY()]
								.setBackground(Color.red);
					}

				}
			}

		});
		fifthFrame.getRight().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Point> markedCells = null;
				if (getCurrentTask() instanceof FirstTask)
					markedCells = ((FirstTask) getCurrentTask())
							.getMarkedCells();
				Boolean ex = false;
				try {
					getCurrentTask().moveRight();
				} catch (OutOfBordersException e1) {
					ex = true;
					JOptionPane.showMessageDialog(fifthFrame, e1.getMessage());

				} catch (InvalidTargetCellException e1) {
					ex = true;
					JOptionPane.showMessageDialog(fifthFrame, e1.getMessage());

				} catch (IOException e1) {
					ex = true;
				}
				updateMap();
				if (!ex && (!getCurrentTask().isTraitActivated())
						&& getCurrentTask() instanceof FirstTask) {
					for (Point point : markedCells) {
						cells[(int) point.getX()][(int) point.getY()].setOpaque(true);
						cells[(int) point.getX()][(int) point.getY()]
								.setBackground(Color.red);
					}
				}
			}

		});
		fifthFrame.getDown().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Point> markedCells = null;
				if (getCurrentTask() instanceof FirstTask)
					markedCells = ((FirstTask) getCurrentTask())
							.getMarkedCells();
				Boolean ex = false;
				try {
					getCurrentTask().moveBackward();
				} catch (OutOfBordersException e1) {
					ex = true;
					JOptionPane.showMessageDialog(fifthFrame, e1.getMessage());

				} catch (InvalidTargetCellException e1) {
					ex = true;
					JOptionPane.showMessageDialog(fifthFrame, e1.getMessage());
				} catch (IOException e1) {
					ex = true;
				}
				updateMap();
				if (!ex && (!getCurrentTask().isTraitActivated())
						&& getCurrentTask() instanceof FirstTask) {
					for (Point point : markedCells) {
						cells[(int) point.getX()][(int) point.getY()].setOpaque(true);
						cells[(int) point.getX()][(int) point.getY()]
								.setBackground(Color.red);
					}
				}
			}

		});
		fifthFrame.getTrait().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Point> markedCells = null;
				if (getCurrentTask() instanceof FirstTask)
					markedCells = ((FirstTask) getCurrentTask())
							.getMarkedCells();
				Boolean ex = false;
				if (getCurrentTask().getCurrentChamp() instanceof GryffindorWizard) {
					try {
						getCurrentTask().onGryffindorTrait();
					} catch (InCooldownException e1) {
						ex = true;
						JOptionPane.showMessageDialog(fifthFrame,
								e1.getMessage());

					}
					updateMap();
					if ((!ex) && (getCurrentTask().getAllowedMoves() == 0)
							&& getCurrentTask() instanceof FirstTask) {
						for (Point point : markedCells) {
							cells[(int) point.getX()][(int) point.getY()].setOpaque(true);
							cells[(int) point.getX()][(int) point.getY()]
									.setBackground(Color.red);
						}
					}
				}

			}

		});
		fifthFrame.getUsePotion().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Collectible> inventory = ((Wizard) getCurrentTask()
						.getCurrentChamp()).getInventory();
				Potion p = (Potion) inventory.get(potions.getSelectedIndex());
				getCurrentTask().usePotion(p);
				updateMap();
			}

		});

		fifthFrame.getTrait().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Boolean ex = false;
				if (getCurrentTask().getCurrentChamp() instanceof HufflepuffWizard) {
					try {
						getCurrentTask().onHufflepuffTrait();
					} catch (InCooldownException e1) {
						ex = true;
						JOptionPane.showMessageDialog(fifthFrame,
								e1.getMessage());
					}
					updateMap();
					Point location = ((Wizard) getCurrentTask()
							.getCurrentChamp()).getLocation();
					if (!ex){
						cells[(int) location.getX()][(int) location.getY()].setOpaque(true);
						cells[(int) location.getX()][(int) location.getY()]
								.setBackground(Color.CYAN);
					}

				}
			}
		});

		fifthFrame.getTrait().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (getCurrentTask().getCurrentChamp() instanceof RavenclawWizard) {
					try {
						if (getCurrentTask() instanceof FirstTask) {
							ArrayList<Point> markedCells = (ArrayList) getCurrentTask()
									.onRavenclawTrait();
							if (getCurrentTask().getCurrentChamp() instanceof RavenclawWizard
									&& getCurrentTask().isTraitActivated()) {
								if (getCurrentTask() instanceof FirstTask) {
									for (Point point : markedCells) {
										cells[(int) point.getX()][(int) point.getY()].setOpaque(true);
										cells[(int) point.getX()][(int) point
												.getY()]
												.setBackground(Color.red);
									}
								}
							}

						} else {
							ArrayList<Direction> hints = (ArrayList) getCurrentTask()
									.onRavenclawTrait();
							Point location = ((Point) ((Wizard) getCurrentTask()
									.getCurrentChamp()).getLocation());
							for (Direction d : hints) {
								switch (d) {
								case RIGHT:
									if (location.getY() < 9){
										cells[(int) location.getX()][(int) location.getY() + 1].setOpaque(true);
										cells[(int) location.getX()][(int) location.getY() + 1].setBackground(Color.green);
									}
									break;
								case LEFT:
									if (location.getY() > 0){
										cells[(int) location.getX()][(int) location.getY()-1].setOpaque(true);
									
										cells[(int) location.getX()][(int) location
												.getY() - 1]
												.setBackground(Color.green);
									}
									break;
								case BACKWARD:
									if (location.getX() < 9){
										cells[(int) location.getX()+1][(int) location.getY()].setOpaque(true);
									
										cells[(int) location.getX() + 1][(int) location
												.getY()]
												.setBackground(Color.green);
									}
									break;
								case FORWARD:
									if (location.getX() > 0){
										cells[(int) location.getX()-1][(int) location.getY()].setOpaque(true);
									
										cells[(int) location.getX() - 1][(int) location
												.getY()]
												.setBackground(Color.green);
									}
									break;
								default:
									break;
								}
							}
						}
					} catch (InCooldownException e1) {
						JOptionPane.showMessageDialog(fifthFrame,
								e1.getMessage());
					}
				}
			}
		});

		fifthFrame.getSpell().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Boolean ex = false;
				ArrayList<Point> markedCells = null;
				if (getCurrentTask() instanceof FirstTask)
					markedCells = ((FirstTask) getCurrentTask())
							.getMarkedCells();
				fifthFrame.validate();
				fifthFrame.repaint();
				z.validate();
				z.repaint();
				if (((z.getSelectedItem() + "").equals("SectumSempra"))
						|| ((z.getSelectedItem() + "").equals("Reducto"))
						|| ((z.getSelectedItem() + "")
								.equals("Piertotum Locomotor"))
						|| ((z.getSelectedItem() + "").equals("Oppugno"))
						|| ((z.getSelectedItem() + "").equals("Incendio"))
						|| ((z.getSelectedItem() + "").equals("Expulso"))
						|| ((z.getSelectedItem() + "").equals("Bombarda"))
						|| ((z.getSelectedItem() + "").equals("Avada Kedavra"))
						|| ((z.getSelectedItem() + "").equals("Crucio"))
						|| ((z.getSelectedItem() + "").equals("Igni"))
						|| ((z.getSelectedItem() + "").equals("Kamehameha"))) {
					Direction d = null;
					String s = JOptionPane
							.showInputDialog("Enter The Desired Spell Casting Direction");
					switch (s) {
					case "up":
						d = Direction.FORWARD;
						break;
					case "down":
						d = Direction.BACKWARD;
						break;
					case "left":
						d = Direction.LEFT;
						break;
					case "right":
						d = Direction.RIGHT;
						break;
					default:
						break;
					}
					try {
						getCurrentTask().castDamagingSpell(
								(DamagingSpell) ((Wizard) getCurrentTask()
										.getCurrentChamp()).getSpells().get(
										z.getSelectedIndex()), d);
						updateMap();

					} catch (IOException | OutOfBordersException
							| InvalidTargetCellException | InCooldownException
							| NotEnoughIPException e1) {
						ex = true;
						JOptionPane.showMessageDialog(fifthFrame,
								e1.getMessage());
					}

				}

				else if (((z.getSelectedItem() + "").equals("Cheering Charm"))
						|| ((z.getSelectedItem() + "")
								.equals("Expecto Patronum"))
						|| ((z.getSelectedItem() + "").equals("Ferula"))
						|| ((z.getSelectedItem() + "")
								.equals("Protego Horribilis"))
						|| ((z.getSelectedItem() + "").equals("Rennervate"))
						|| ((z.getSelectedItem() + "").equals("Quen"))) {
					try {
						getCurrentTask().castHealingSpell(
								(HealingSpell) ((Wizard) getCurrentTask()
										.getCurrentChamp()).getSpells().get(
										z.getSelectedIndex()));
						updateMap();

					} catch (IOException | InCooldownException
							| NotEnoughIPException e1) {
						JOptionPane.showMessageDialog(fifthFrame,
								e1.getMessage());
						ex = true;
					}
				}

				else if (((z.getSelectedItem() + "").equals("Accio"))
						|| ((z.getSelectedItem() + "").equals("Imperio"))
						|| ((z.getSelectedItem() + "")
								.equals("Wingardium Leviosa"))
						|| ((z.getSelectedItem() + "").equals("Axii"))) {
					Direction target = null;
					String s = JOptionPane
							.showInputDialog("Enter Direction Of The Obstacle");
					switch (s) {
					case "up":
						target = Direction.FORWARD;
						break;
					case "down":
						target = Direction.BACKWARD;
						break;
					case "left":
						target = Direction.LEFT;
						break;
					case "right":
						target = Direction.RIGHT;
						break;
					default:
						break;
					}

					Direction direction = null;
					String s1 = JOptionPane
							.showInputDialog("Enter The Direction To Relocate To");
					switch (s1) {
					case "up":
						direction = Direction.FORWARD;
						break;
					case "down":
						direction = Direction.BACKWARD;
						break;
					case "left":
						direction = Direction.LEFT;
						break;
					case "right":
						direction = Direction.RIGHT;
						break;
					default:
						break;
					}

					String range1 = JOptionPane
							.showInputDialog("Enter The Range Of Relocating");
					int range = Integer.parseInt(range1);
					try {
						getCurrentTask().castRelocatingSpell(
								(RelocatingSpell) ((Wizard) getCurrentTask()
										.getCurrentChamp()).getSpells().get(
										z.getSelectedIndex()), target,
								direction, range);
						updateMap();

					} catch (IOException | OutOfBordersException
							| InvalidTargetCellException | InCooldownException
							| NotEnoughIPException | OutOfRangeException e1) {
						ex = true;
						JOptionPane.showMessageDialog(fifthFrame,
								e1.getMessage());
					}
				}

				updateMap();
				fifthFrame.validate();
				fifthFrame.repaint();
				if (!ex && getCurrentTask() instanceof FirstTask) {
					for (Point point : markedCells) {
						cells[(int) point.getX()][(int) point.getY()].setOpaque(true);
						cells[(int) point.getX()][(int) point.getY()]
								.setBackground(Color.red);
					}
				}
			}
		});
		fifthFrame.getTrait().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Point> markedCells = null;
				if (getCurrentTask() instanceof FirstTask)
					markedCells = ((FirstTask) getCurrentTask())
							.getMarkedCells();
				Boolean ex = false;
				if (getCurrentTask().getCurrentChamp() instanceof SlytherinWizard) {

					Direction d = null;
					String s = JOptionPane
							.showInputDialog("Enter The Desired Trait Direction");
					switch (s) {
					case "up":
						d = Direction.FORWARD;
						break;
					case "down":
						d = Direction.BACKWARD;
						break;
					case "left":
						d = Direction.LEFT;
						break;
					case "right":
						d = Direction.RIGHT;
						break;
					default:
						break;
					}
					try {
						getCurrentTask().onSlytherinTrait(d);
					} catch (OutOfBordersException | InvalidTargetCellException
							| InCooldownException | IOException e1) {
						ex = true;
						JOptionPane.showMessageDialog(fifthFrame,
								e1.getMessage());

					}
					updateMap();
					if (!ex && getCurrentTask() instanceof FirstTask) {
						for (Point point : markedCells) {
							cells[(int) point.getX()][(int) point.getY()].setOpaque(true);
							cells[(int) point.getX()][(int) point.getY()]
									.setBackground(Color.red);
						}
					}
				}

			}
		});
		tournament = new Tournament();

	}

	public void updateMap() {
		fifthFrame.getName2().setText(
				"Name : "
						+ ((Wizard) getCurrentTask().getCurrentChamp())
								.getName());
		fifthFrame.getIp()
				.setText(
						"Ip : "
								+ ((Wizard) getCurrentTask().getCurrentChamp())
										.getIp());
		fifthFrame.getHp()
				.setText(
						"Hp : "
								+ ((Wizard) getCurrentTask().getCurrentChamp())
										.getHp());
		fifthFrame.getMoves().setText(
				"Remaining Moves : " + getCurrentTask().getAllowedMoves());
		fifthFrame.remove(z);
		fifthFrame.remove(potions);
		if (w1 != null)
			fifthFrame.remove(w1);
		if (w2 != null)
			fifthFrame.remove(w2);
		if (w3 != null)
			fifthFrame.remove(w3);
		if (w4 != null)
			fifthFrame.remove(w4);

		ArrayList<Spell> spells = ((Wizard) getCurrentTask().getCurrentChamp()).getSpells();
		String[] sp = new String[3];
		for (Spell s2 : spells) {
			sp[spells.indexOf(s2)] = s2.getName();
		}
		ArrayList<Collectible> inventory = ((Wizard) getCurrentTask()
				.getCurrentChamp()).getInventory();
		DefaultListModel<String> model = new DefaultListModel<>();
		potions = new JList(model);
		potions.setBounds(10, 70, 150, 200);
		for (Collectible c : inventory) {
			model.addElement(c.getName() + " : " + ((Potion) c).getAmount());
		}
		fifthFrame.getContentPane().add(potions, 1);
		z = new JComboBox(sp);
		z.setBounds(845, 250, 180, 30);
		fifthFrame.getContentPane().add(z, 1);
		Cell[][] map = getCurrentTask().getMap();
		int count = 0;
		fifthFrame.getY1().removeAll();
		// fifthFrame.getY1().setLayout(new GridLayout(10, 10));
		// fifthFrame.getY1().setBounds(170, 40, 600, 600);
		// fifthFrame.getY1().setBackground(new Color(0, 0, 0, 0));
		// // fifthFrame.getContentPane().add(fifthFrame.getY1());
		/*
		 * if(tournament.getFirstTask().getCurrentChamp() instanceof
		 * RavenclawWizard && tournament.getFirstTask().isTraitActivated()){
		 * ArrayList<Point> markedCells =
		 * tournament.getFirstTask().getMarkedCells(); for (Point point :
		 * markedCells) { cells[(int) point.getX()][(int)
		 * point.getX()].setBackground(Color.red); }}
		 */
		for (int i1 = 0; i1 < 10; i1++) {
			for (int j = 0; j < 10; j++) {
				Cell cell = map[i1][j];
				if (cell instanceof ChampionCell) {
					Wizard w = (Wizard) ((ChampionCell) cell).getChamp();
					if (w instanceof GryffindorWizard) {
						count++;
						cells[i1][j] = new JButton();
						cells[i1][j].setIcon(new ImageIcon("fgry.png"));
						String name = w.getName();
						int hp = w.getHp();
						int ip = w.getIp();
						cells[i1][j].setToolTipText("Gryffindor Wizard Name: "
								+ name + ", " + "HP: " + hp + ", " + "IP: "
								+ ip);
						if (getCurrentTask().getCurrentChamp().equals(w)) {
							if(w instanceof GryffindorWizard)
								cells[i1][j].setIcon(new ImageIcon(
										"fgry.gif"));
							if(w instanceof SlytherinWizard)
								cells[i1][j].setIcon(new ImageIcon(
										"fsly.gif"));
							if(w instanceof HufflepuffWizard)
								cells[i1][j].setIcon(new ImageIcon(
										"fhuf.gif"));
							if(w instanceof RavenclawWizard)
								cells[i1][j].setIcon(new ImageIcon(
										"frav.gif"));
							
							//cells[i1][j].setBackground(Color.GREEN);
						}
						fifthFrame.getY1().add(cells[i1][j]);
					} else if (w instanceof SlytherinWizard) {
						count++;
						cells[i1][j] = new JButton();
						cells[i1][j].setIcon(new ImageIcon("fsly.png"));
						String name = w.getName();
						int hp = w.getHp();
						int ip = w.getIp();
						cells[i1][j].setToolTipText("Slytherin Wizard Name: "
								+ name + ", " + "HP: " + hp + ", " + "IP: "
								+ ip);
						if (getCurrentTask().getCurrentChamp().equals(w)) {
							if(w instanceof GryffindorWizard)
								cells[i1][j].setIcon(new ImageIcon(
										"fgry.gif"));
							if(w instanceof SlytherinWizard)
								cells[i1][j].setIcon(new ImageIcon(
										"fsly.gif"));
							if(w instanceof HufflepuffWizard)
								cells[i1][j].setIcon(new ImageIcon(
										"fhuf.gif"));
							if(w instanceof RavenclawWizard)
								cells[i1][j].setIcon(new ImageIcon(
										"frav.gif"));
							
							//cells[i1][j].setBackground(Color.GREEN);
						}
						fifthFrame.getY1().add(cells[i1][j]);
					} else if (w instanceof RavenclawWizard) {
						count++;
						cells[i1][j] = new JButton();
						cells[i1][j].setIcon(new ImageIcon("frav.png"));
						String name = w.getName();
						int hp = w.getHp();
						int ip = w.getIp();
						cells[i1][j].setToolTipText("Ravenclaw Wizard Name: "
								+ name + ", " + "HP: " + hp + ", " + "IP: "
								+ ip);
						if (getCurrentTask().getCurrentChamp().equals(w)) {
							if(w instanceof GryffindorWizard)
								cells[i1][j].setIcon(new ImageIcon(
										"fgry.gif"));
							if(w instanceof SlytherinWizard)
								cells[i1][j].setIcon(new ImageIcon(
										"fsly.gif"));
							if(w instanceof HufflepuffWizard)
								cells[i1][j].setIcon(new ImageIcon(
										"fhuf.gif"));
							if(w instanceof RavenclawWizard)
								cells[i1][j].setIcon(new ImageIcon(
										"frav.gif"));
							
							//cells[i1][j].setBackground(Color.GREEN);
						}
						fifthFrame.getY1().add(cells[i1][j]);
					} else if (w instanceof HufflepuffWizard) {
						count++;
						cells[i1][j] = new JButton();
						cells[i1][j].setIcon(new ImageIcon("fhuf.png"));
						String name = w.getName();
						int hp = w.getHp();
						int ip = w.getIp();
						cells[i1][j].setToolTipText("Hufflepuff Wizard Name: "
								+ name + ", " + "HP: " + hp + ", " + "IP: "
								+ ip);
						if (getCurrentTask().getCurrentChamp().equals(w)) {
							if(w instanceof GryffindorWizard)
								cells[i1][j].setIcon(new ImageIcon(
										"fgry.gif"));
							if(w instanceof SlytherinWizard)
								cells[i1][j].setIcon(new ImageIcon(
										"fsly.gif"));
							if(w instanceof HufflepuffWizard)
								cells[i1][j].setIcon(new ImageIcon(
										"fhuf.gif"));
							if(w instanceof RavenclawWizard)
								cells[i1][j].setIcon(new ImageIcon(
										"frav.gif"));
							
							//cells[i1][j].setBackground(Color.GREEN);
						}
						fifthFrame.getY1().add(cells[i1][j]);
					}
				} else if (cell instanceof WallCell) {
					count++;
					cells[i1][j] = new JButton();
					cells[i1][j].setIcon(new ImageIcon("messi.jpg"));
					fifthFrame.getY1().add(cells[i1][j]);
				} else if (cell instanceof TreasureCell) {
					count++;
					cells[i1][j] = new JButton();
					//cells[i1][j].setBackground(Color.BLACK);
					fifthFrame.getY1().add(cells[i1][j]);
				} else if (cell instanceof ObstacleCell) {
					count++;
					cells[i1][j] = new JButton();
					int hp = ((ObstacleCell) cell).getObstacle().getHp();
					cells[i1][j].setToolTipText("HP: " + hp);
					if (((ObstacleCell) cell).getObstacle() instanceof PhysicalObstacle) {
						cells[i1][j].setIcon(new ImageIcon("obb.png"));
					} else if (((ObstacleCell) cell).getObstacle() instanceof Merperson) {
						int damage = ((Merperson) ((ObstacleCell) cell)
								.getObstacle()).getDamage();
						cells[i1][j].setIcon(new ImageIcon("jmer.png"));
						cells[i1][j].setToolTipText("HP: " + hp + ", "
								+ "Damage: " + damage);
					}
					fifthFrame.getY1().add(cells[i1][j]);
				} else if (i1 == 4 && j == 4
						&& getCurrentTask() instanceof FirstTask) {
					count++;
					cells[i1][j] = new JButton();
					cells[i1][j].setIcon(new ImageIcon("egg1.png"));
					fifthFrame.getY1().add(cells[i1][j]);
				} else {
					count++;
					cells[i1][j] = new JButton();
					fifthFrame.getY1().add(cells[i1][j]);
				}

			}
		}
		if (getCurrentTask().isTraitActivated()) {
			fifthFrame.getTrait().setOpaque(true);
			fifthFrame.getTrait().setBackground(Color.green);
		} else {
			fifthFrame.getTrait().setOpaque(true);
			fifthFrame.getTrait().setBackground(Color.white);
		} // Amgad
		if (getCurrentTask().getCurrentChamp() instanceof GryffindorWizard) {
			fifthFrame
					.getTrait()
					.setToolTipText(
							"Trait Description: This Turn, The Champion Can Make 2 Moves Instead Of 1");
		}
		if (getCurrentTask().getCurrentChamp() instanceof SlytherinWizard
				&& ((getCurrentTask() instanceof FirstTask) || (getCurrentTask() instanceof SecondTask))) {
			fifthFrame.getTrait().setToolTipText(
					"Trait Description: Traverse Two Cells In One Move");

		}
		if (getCurrentTask().getCurrentChamp() instanceof SlytherinWizard
				&& getCurrentTask() instanceof ThirdTask) {
			fifthFrame
					.getTrait()
					.setToolTipText(
							"Trait Description: Moving Through a Wall Given That The Cell He Ends Up in is Not Another Wall or Traverse Two Cells Instead Of One");

		}
		if (getCurrentTask().getCurrentChamp() instanceof HufflepuffWizard
				&& getCurrentTask() instanceof FirstTask) {
			fifthFrame.getTrait().setToolTipText(
					"Trait Description: This Turn, The Dragon Doesn't Attack");

		}
		if (getCurrentTask().getCurrentChamp() instanceof HufflepuffWizard
				&& getCurrentTask() instanceof SecondTask) {
			fifthFrame
					.getTrait()
					.setToolTipText(
							"Trait Description: This Turn, The Merpeople Won't Do Any Damage");

		}

		if (getCurrentTask().getCurrentChamp() instanceof HufflepuffWizard
				&& getCurrentTask() instanceof ThirdTask) {
			fifthFrame
					.getTrait()
					.setToolTipText(
							"Trait Description: Attacks From Other Champions Will Only Deal Half The Damage");

		}
		if (getCurrentTask().getCurrentChamp() instanceof RavenclawWizard
				&& getCurrentTask() instanceof FirstTask) {
			fifthFrame
					.getTrait()
					.setToolTipText(
							"Trait Description: This Turn, the champion is shown where the dragon is going to attack");

		}
		if (getCurrentTask().getCurrentChamp() instanceof RavenclawWizard
				&& getCurrentTask() instanceof SecondTask) {
			fifthFrame
					.getTrait()
					.setToolTipText(
							"Trait Description: his turn, the champion is given a hint on where the target is hidden relative to the current position (left or right + up or down)");

		}
		if (getCurrentTask().getCurrentChamp() instanceof RavenclawWizard
				&& getCurrentTask() instanceof ThirdTask) {
			fifthFrame
					.getTrait()
					.setToolTipText(
							"Trait Description: This turn, the champion is given a hint on where the cup is hidden relative to the current position (left or right + up or down)");

		}
		fifthFrame.validate();
		fifthFrame.repaint();
		if (getCurrentTask() instanceof FirstTask) {
			ArrayList<Champion> winners = ((FirstTask) getCurrentTask())
					.getWinners();
			int count2 = 0;
			if (!winners.isEmpty()) {
				for (Champion c : winners) {
					count2++;
					if (count2 == 1) {
						w1 = new JLabel();
						w1.setBounds(15, 480, 50, 50);
						if (c instanceof GryffindorWizard)
							w1.setIcon(new ImageIcon("fgry.png"));
						if (c instanceof SlytherinWizard)
							w1.setIcon(new ImageIcon("fsly.png"));
						if (c instanceof HufflepuffWizard)
							w1.setIcon(new ImageIcon("fhuf.png"));
						if (c instanceof RavenclawWizard)
							w1.setIcon(new ImageIcon("frav.png"));
						fifthFrame.add(w1, 1);
					}
					if (count2 == 2) {
						w2 = new JLabel();
						w2.setBounds(15, 550, 50, 50);
						if (c instanceof GryffindorWizard)
							w2.setIcon(new ImageIcon("fgry.png"));
						if (c instanceof SlytherinWizard)
							w2.setIcon(new ImageIcon("fsly.png"));
						if (c instanceof HufflepuffWizard)
							w2.setIcon(new ImageIcon("fhuf.png"));
						if (c instanceof RavenclawWizard)
							w2.setIcon(new ImageIcon("frav.png"));
						fifthFrame.add(w2, 1);
					}
					if (count2 == 3) {
						w3 = new JLabel();
						w3.setBounds(95, 480, 50, 50);
						if (c instanceof GryffindorWizard)
							w3.setIcon(new ImageIcon("fgry.png"));
						if (c instanceof SlytherinWizard)
							w3.setIcon(new ImageIcon("fsly.png"));
						if (c instanceof HufflepuffWizard)
							w3.setIcon(new ImageIcon("fhuf.png"));
						if (c instanceof RavenclawWizard)
							w3.setIcon(new ImageIcon("frav.png"));
						fifthFrame.add(w3, 1);
					}
					if (count2 == 4) {
						w4 = new JLabel();
						w4.setBounds(95, 550, 50, 50);
						if (c instanceof GryffindorWizard)
							w4.setIcon(new ImageIcon("fgry.png"));
						if (c instanceof SlytherinWizard)
							w4.setIcon(new ImageIcon("fsly.png"));
						if (c instanceof HufflepuffWizard)
							w4.setIcon(new ImageIcon("fhuf.png"));
						if (c instanceof RavenclawWizard)
							w4.setIcon(new ImageIcon("frav.png"));
						fifthFrame.add(w4, 1);
					}

				}
			}
		}
		if (getCurrentTask() instanceof SecondTask) {
			ArrayList<Champion> winners = ((SecondTask) getCurrentTask())
					.getWinners();
			int count2 = 0;
			if (!winners.isEmpty()) {
				for (Champion c : winners) {
					count2++;
					if (count2 == 1) {
						w1 = new JLabel();
						w1.setBounds(15, 480, 50, 50);
						if (c instanceof GryffindorWizard)
							w1.setIcon(new ImageIcon("fgry.png"));
						if (c instanceof SlytherinWizard)
							w1.setIcon(new ImageIcon("fsly.png"));
						if (c instanceof HufflepuffWizard)
							w1.setIcon(new ImageIcon("fhuf.png"));
						if (c instanceof RavenclawWizard)
							w1.setIcon(new ImageIcon("frav.png"));
						fifthFrame.add(w1, 1);
					}
					if (count2 == 2) {
						w2 = new JLabel();
						w2.setBounds(15, 550, 50, 50);
						if (c instanceof GryffindorWizard)
							w2.setIcon(new ImageIcon("fgry.png"));
						if (c instanceof SlytherinWizard)
							w2.setIcon(new ImageIcon("fsly.png"));
						if (c instanceof HufflepuffWizard)
							w2.setIcon(new ImageIcon("fhuf.png"));
						if (c instanceof RavenclawWizard)
							w2.setIcon(new ImageIcon("frav.png"));
						fifthFrame.add(w2, 1);
					}
					if (count2 == 3) {
						w3 = new JLabel();
						w3.setBounds(95, 480, 50, 50);
						if (c instanceof GryffindorWizard)
							w3.setIcon(new ImageIcon("fgry.png"));
						if (c instanceof SlytherinWizard)
							w3.setIcon(new ImageIcon("fsly.png"));
						if (c instanceof HufflepuffWizard)
							w3.setIcon(new ImageIcon("fhuf.png"));
						if (c instanceof RavenclawWizard)
							w3.setIcon(new ImageIcon("frav.png"));
						fifthFrame.add(w3, 1);
					}
					if (count2 == 4) {
						w4 = new JLabel();
						w4.setBounds(95, 550, 50, 50);
						if (c instanceof GryffindorWizard)
							w4.setIcon(new ImageIcon("fgry.png"));
						if (c instanceof SlytherinWizard)
							w4.setIcon(new ImageIcon("fsly.png"));
						if (c instanceof HufflepuffWizard)
							w4.setIcon(new ImageIcon("fhuf.png"));
						if (c instanceof RavenclawWizard)
							w4.setIcon(new ImageIcon("frav.png"));
						fifthFrame.add(w4, 1);
					}
				}
			}
		}

		if (getCurrentTask().getChampions().isEmpty()
				&& !(getCurrentTask() instanceof ThirdTask)) {
			try {
				if (getCurrentTask() instanceof FirstTask) {
					if (!((FirstTask) getCurrentTask()).getWinners().isEmpty()) {
						tournament.onFinishingFirstTask(tournament
								.getFirstTask().getWinners());
						fifthFrame.revalidate();
						fifthFrame.repaint();
						fifthFrame.getX2().setIcon(new ImageIcon("2nd.jpg"));
						currentTask = tournament.getSecondTask();
						JOptionPane.showMessageDialog(fifthFrame,
								"Welcome To The Second Task");
						updateMap();
						fifthFrame.validate();
						fifthFrame.repaint();
					} else {
						JOptionPane.showMessageDialog(fifthFrame, "GAME OVER!");
						System.exit(0);
					}
				} else if (getCurrentTask() instanceof SecondTask) {
					if (!((SecondTask) getCurrentTask()).getWinners().isEmpty()) {
						tournament.onFinishingSecondTask(tournament
								.getFirstTask().getWinners());
						fifthFrame.getX2().setIcon(new ImageIcon("3rd.jpg"));
						currentTask = tournament.getThirdTask();
						JOptionPane.showMessageDialog(fifthFrame,
								"Welcome To The Third Task");
						updateMap();
						fifthFrame.validate();
						fifthFrame.repaint();
					} else {
						JOptionPane.showMessageDialog(fifthFrame, "GAME OVER!");
						System.exit(0);
					}
				}

				updateMap();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(fifthFrame, e.getMessage());

			}
		} else if (getCurrentTask() instanceof ThirdTask) {
			if (((Wizard) tournament.getThirdTask().getCurrentChamp())
					.getLocation().getX() == 1
					&& ((Wizard) tournament.getThirdTask().getCurrentChamp())
							.getLocation().getY() == 4) {
				tournament.onFinishingThirdTask(tournament.getThirdTask()
						.getCurrentChamp());
				String s = "AND THE WINNER OF THE TRIWIZARD TOURNAMENT IS... "
						+ ((Wizard) tournament.getThirdTask().getCurrentChamp())
								.getName();
				JOptionPane.showMessageDialog(fifthFrame, s);
				System.exit(0);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		new Game();
		InputStream in = new FileInputStream("music.wav");
		AudioStream as = new AudioStream(in);
		AudioPlayer.player.start(as);
	}
}