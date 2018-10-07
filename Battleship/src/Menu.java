
/*
 * Primary Author: Brian La
 * Date of Completion: 5/13/18
 * Revision Number: 4
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Menu extends JPanel implements MouseListener, MouseMotionListener {

	// Fields
	private Rectangle battleship, pvp, rule;
	private Rectangle back;
	private boolean rules = false;
	private Font font = new Font("SansSerif", Font.PLAIN, 16);
	private Font title = new Font("SansSerif", Font.PLAIN, 25);

	public Menu() {

		// Adding Mouse Listener and Mouse Motion Listener
		addMouseListener(this);
		addMouseMotionListener(this);

		// Creates the title screen
		battleship = new Rectangle(200, 30, 600, 50);
		pvp = new Rectangle(350, 150, 300, 50);
		rule = new Rectangle(350, 250, 300, 50);
		back = new Rectangle(900, 50, 50, 50);
	}

	public void paintComponent(Graphics g) {

		super.paintComponents(g);
		// If the screen is on menu
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, 3000, 3000);
		if (rules) {
			g.setColor(Color.BLACK);
			g.setFont(title);
			g.drawString("Setting Up the Board", 175, 50);
			g.drawString("Playing the Game", 600, 50);

			g.setFont(font);
			g.drawString("1. Drag and Drop Your Ships on to the Board", 100, 125);
			g.drawString("2. Click Your Ships to Rotate Them", 100, 175);
			g.drawString("3. Click Hide Ships to Conceal the Positions", 100, 225);
			g.drawString("4. Click End Turn to Finalize the Set-Up", 100, 275);

			g.drawString("1. Whenever it is Your Turn, click Start Turn", 500, 125);
			g.drawString("2. Click on a Square on Your Opponent's Board", 500, 165);
			g.drawString("3. If the Missile is Red, You Hit and Sunk a Ship", 500, 205);
			g.drawString("4. If the Missile is Orange, You Hit a Ship and You Can Hit Again", 500, 245);
			g.drawString("5. If the Missile is White, You Missed", 500, 285);
			g.drawString("6. End Your Turn and Pass the Computer to the Other Player", 500, 325);
			g.drawString("7. Repeat Until All 5 Ships are Hit", 500, 365);

			g.setColor(Color.BLACK);
			g.drawRect(back.x, back.y, back.width, back.height);
			g.drawString("Back", back.x + back.x / 150, back.y + back.y * 2 / 3);
		} else {
			g.setColor(Color.GRAY);
			g.fillRect(battleship.x, battleship.y, battleship.width, battleship.height);
			g.fillRect(pvp.x, pvp.y, pvp.width, pvp.height);
			g.fillRect(rule.x, rule.y, rule.width, rule.height);

			g.setColor(Color.BLACK);
			g.setFont(title);
			g.drawString("BATTLESHIP", battleship.x + battleship.width * 6 / 16,
					battleship.y + battleship.height * 2 / 3);
			g.setFont(font);
			g.drawString("Start Game", pvp.x + pvp.width * 15 / 40, pvp.y + pvp.height * 2 / 3);
			g.drawString("How To Play", rule.x + rule.width * 7 / 20, rule.y + rule.height * 2 / 3);
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int x, y;

		x = e.getX();
		y = e.getY();
		if (rules) {
			if (back.contains(x, y)) {
				rules = false;
				repaint();
			}
		} else {
			if (pvp.contains(x, y)) {
				Main.gameWindow.setSize(1000, 410);
				Main.gameWindow.setVisible(true);
				Main.gameWindow.setResizable(false);
				Main.gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				Main.board1 = new Board(0);
				Main.board2 = new Board(1);

				Main.gameWindow.setLayout(new GridLayout(1, 2));
				Main.gameWindow.getContentPane().add(Main.board1);
				Main.gameWindow.getContentPane().add(Main.board2);
				Main.menuWindow.dispose();
			} else if (rule.contains(x, y)) {
				rules = true;
				repaint();
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
