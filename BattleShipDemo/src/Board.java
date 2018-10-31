
/*
 * Primary Author: Josh Sanyal
 * Date of Completion: 5/16/18
 * Revision Number: 12
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Board extends JPanel implements MouseListener, MouseMotionListener {

	// Fields
	private Ship[] ships = new Ship[5];
	private Missile[] missiles = new Missile[100];
	private Rectangle[] pieces = new Rectangle[5];
	private boolean[] clickedOnState = new boolean[5];
	private int turn = 0, id, numMissiles = 0, numHits = 0;
	private static int winner = 2;
	private static int currentPlayer = 0;
	private Rectangle endTurn, showShips, activeTurn;
	private boolean clicked = false, missileCreated = false;
	private boolean shipsVisible = true;
	private Font font = new Font("SansSerif", Font.PLAIN, 15);

	// Constructor with a playerID input
	public Board(int a) {
		id = a;

		// Defining the ships on the board
		for (int i = 0; i < 5; i++) {
			ships[i] = new Ship();
			ships[i].addPosition(350, 50 + 60 * i);
			if (i < 2) {
				ships[i].setLength(i + 2);
			} else {
				ships[i].setLength(i + 1);

			}
		}

		// Adding Mouse Listener and Mouse Motion Listener
		addMouseListener(this);
		addMouseMotionListener(this);

		// Defines the pieces (physical representations of the ships) and their clicked states
		for (int i = 0; i < 5; i++) {
			if (ships[i].isHorizontal) {
				pieces[i] = new Rectangle(ships[i].x - ships[i].x % 30, ships[i].y - ships[i].y % 30,
						30 * ships[i].length, 30);
			} else {
				pieces[i] = new Rectangle(ships[i].x - ships[i].x % 30, ships[i].y - ships[i].y % 30, 30,
						30 * ships[i].length);
			}
			clickedOnState[i] = false;
		}

		// Creates rectangles that are buttons/signs
		endTurn = new Rectangle(30, 340, 145, 30);
		showShips = new Rectangle(185, 340, 145, 30);
		activeTurn = new Rectangle(425, 30, 50, 50);
	}

	public void paintComponent(Graphics g) {

		super.paintComponents(g);

		// Draws background
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, 3000, 3000);

		// Draws board
		g.setColor(Color.GRAY);
		g.fillRect(30, 30, 300, 300);

		g.setColor(Color.BLACK);
		for (int i = 0; i < 11; i++) {
			g.drawLine(30 + 30 * i, 30, 30 + 30 * i, 330);
			g.drawLine(30, 30 + 30 * i, 330, 30 + 30 * i);

		}

		for (int i = 0; i < 10; i++) {
			g.drawString("" + (char) ('A' + i), 10, 45 + 30 * i);
			g.drawString("" + (i + 1), 45 + 30 * i, 20);
		}

		// Draws ships
		if (turn % 2 == id && shipsVisible) {
			for (int i = 0; i < 5; i++) {
				if (clickedOnState[i]) {
					g.setColor(Color.BLACK);
				} else {
					g.setColor(Color.GREEN);
				}
				
				pieces[i].x = pieces[i].x - pieces[i].x % 30;
				pieces[i].y = pieces[i].y - pieces[i].y % 30;
				g.fillRect(pieces[i].x, pieces[i].y, pieces[i].width, pieces[i].height);
				g.setColor(Color.BLACK);
				g.drawRect(pieces[i].x, pieces[i].y, pieces[i].width, pieces[i].height);

			}
		}

		// Draws missiles
		// Red is sunk, orage is hit, white is miss
		for (int i = 0; i < numMissiles; i++) {
			if (missiles[i].getType() == 2) {
				g.setColor(Color.RED);
			} else if (missiles[i].getType() == 1) {
				g.setColor(Color.ORANGE);
			} else {
				g.setColor(Color.WHITE);
			}
			g.fillOval(missiles[i].x, missiles[i].y, 30, 30);

		}

		// Draws buttons
		g.setFont(font);
		g.setColor(Color.RED);
		g.fillRect(endTurn.x, endTurn.y, endTurn.width, endTurn.height);
		g.fillRect(showShips.x, showShips.y, showShips.width, showShips.height);

		g.setColor(Color.BLACK);
		if (turn % 2 == id) {
			g.drawString("End Turn", endTurn.x + endTurn.width * 3 / 10, endTurn.y + endTurn.height * 2 / 3);
		} else {
			g.drawString("Start Turn", endTurn.x + endTurn.width * 3 / 10, endTurn.y + endTurn.height * 2 / 3);
		}

		if (shipsVisible) {
			g.drawString("Hide Ships", showShips.x + showShips.width * 11 / 40, showShips.y + showShips.height * 2 / 3);
		} else {
			g.drawString("Show Ships", showShips.x + showShips.width * 11 / 40, showShips.y + showShips.height * 2 / 3);
		}

		// Draws rectangles that show current player
		if (turn % 2 != id) {
			g.setColor(Color.GRAY);
		} else {
			g.setColor(Color.GREEN);
		}

		g.fillRect(activeTurn.x, activeTurn.y, activeTurn.width, activeTurn.height);

	}

	public void mouseClicked(MouseEvent e) {

		// x and y are set to the x and y of the mouse
		int x, y;

		x = e.getX();
		y = e.getY();

		// Lets the player change the orientation of the ship
		if (turn == id) {
			for (int i = 0; i < 5; i++) {
				if (pieces[i].contains(x, y)) {
					ships[i].changeOrientation();
					double z = pieces[i].getWidth();
					pieces[i].width = (int) pieces[i].getHeight();
					pieces[i].height = (int) z;
				}
			}
		}

		// If player tries to end their turn
		if (endTurn.contains(x, y)) {

			// Alerts the user if their board setup is invalid
			if (turn == id) {
				if (!checkValid()) {
					JOptionPane.showMessageDialog(null, "Improper Board Setup", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
			}

			// Alerts the user if their action is prohibited
			if (id == currentPlayer) {
				turn++;
				missileCreated = turn % 2 == id;
			} else {
				JOptionPane.showMessageDialog(null, "Not Your Turn", "Error", JOptionPane.ERROR_MESSAGE);
			}

			// Changes the current player
			if (currentPlayer == 0 && turn % 2 == 1) {
				currentPlayer = 1 - currentPlayer;
			} else if (currentPlayer == 1 && turn % 2 == 0) {
				currentPlayer = 1 - currentPlayer;
			}
		}

		// Allows user to show/hide ships
		if (showShips.contains(x, y)) {

			// Alerts the user if their action is prohibited
			if (id != currentPlayer) {
				JOptionPane.showMessageDialog(null, "Not Your Turn", "Error", JOptionPane.ERROR_MESSAGE);
			} else {
				shipsVisible = !shipsVisible;
			}
		}

		// Allows the player to create missiles until they miss
		if (turn % 2 != id && turn > 1 && x > 30 && x < 330 && y > 30 && y < 330 && !missileCreated) {
			boolean alreadyClicked = false, isHit = false;
			int[] sunk = new int[5];
			for (int i = 0; i < numMissiles; i++) {
				if (x - x % 30 == missiles[i].x && y - y % 30 == missiles[i].y) {
					alreadyClicked = true;
				}
			}

			// Checks if the user has already missed
			if (!alreadyClicked) {
				missiles[numMissiles] = new Missile(x - x % 30, y - y % 30, 0);
				missileCreated = true;
				for (int i = 0; i < 5; i++) {

					// Checks if they hit a battleship
					if (pieces[i].contains(x, y)) {

						// Checks if the battleship sunk
						
						// Runs through all the positions of the ship and checks if they were hit by a missile
						for (int j = 0; j < ships[i].getLength(); j++) {
							for (int k = 0; k < numMissiles + 1; k++) {
								if (ships[i].isHorizontal) {
									if (missiles[k].x == pieces[i].x + j * 30 && missiles[k].y == pieces[i].y) {
										isHit = true;
										sunk[j] = k;
										break;
									}
								} else {
									if (missiles[k].x == pieces[i].x && missiles[k].y == pieces[i].y + j * 30) {
										isHit = true;
										sunk[j] = k;
										break;
									}
								}
							}

							// Changes the missile's type of hit
							if (j == ships[i].getLength() - 1 && isHit) {
								for (int k = 0; k < ships[i].getLength(); k++) {
									missiles[sunk[k]].setType(2);
								}
							}
							if (!isHit) {
								missiles[numMissiles].setType(1);
								break;
							}
							isHit = false;
						}

						missileCreated = false;
						numHits++;

						// Checks if the player wins
						if (numHits == 17) {
							winner = id;
						}
						if (winner == 0 || winner == 1) {
							JOptionPane.showMessageDialog(null, "Player " + (2 - id) + " Wins", "Results",
									JOptionPane.DEFAULT_OPTION);
						}
					}
				}
				numMissiles++;
			}
		}
		repaint();
	}

	public void mousePressed(MouseEvent e) {
		// x and y are set to the x and y of the mouse
		int x, y;

		x = e.getX();
		y = e.getY();

		// Sets the clickedOnState of a ship true when it is pressed
		if (turn == id) {
			for (int i = 0; i < 5; i++) {
				if (pieces[i].contains(x, y)) {
					clickedOnState[i] = true;
				}

			}
		}

		repaint();

	}

	public void mouseReleased(MouseEvent e) {
		// x and y are set to the x and y of the mouse
		int x, y;

		x = e.getX();
		y = e.getY();

		// Sets the clickedOnState of a ship to false when it is released
		if (turn == id) {
			for (int i = 0; i < 5; i++) {
				if (pieces[i].contains(x, y)) {
					clickedOnState[i] = false;
				}

			}
			repaint();
		}
	}

	public void mouseDragged(MouseEvent e) {
		// x and y are set to the x and y of the mouse
		int x, y;

		x = e.getX();
		y = e.getY();

		// Allows the user to move their ships
		if (turn == id) {
			for (int i = 0; i < 5; i++) {
				if (clickedOnState[i]) {
					pieces[i].x = x;
					pieces[i].y = y;
					ships[i].x = x;
					ships[i].y = y;
				}

			}
			repaint();
		}

	}

	public void mouseMoved(MouseEvent e) {

	}

	// Method that returns true if the board setup is valid
	public boolean checkValid() {
		int counter = 0;
		
		// Runs through each space on the board and adds to the counter if a ships is on the space
		for (int x = 0; x < 10; x++) {
			for (int y = 0; y < 10; y++) {
				for (int j = 0; j < 5; j++) {
					if (pieces[j].contains(x * 30 + 45, y * 30 + 45)) {
						counter++;
						break;
					}
				}

			}
		}
		
		// If the counter equals 17, the setup is valid
		return (counter == 17);
	}

	public void mouseEntered(MouseEvent e) {

	}

	public void mouseExited(MouseEvent e) {

	}

}
