/*
 * Primary Author: Brian La
 * Date of Completion: 5/16/18
 * Revision Number: 7
 */

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JPanel {

	// Creates fields (windows and boards)
	public static JFrame menuWindow = new JFrame();
	public static JFrame gameWindow = new JFrame();
	public static Board board1, board2;
	public static Menu menu;

	public static void main(String[] args) {
		
		// Add menu to the window
		menu = new Menu();
		menuWindow.add(menu);
		
		// Sets frame size
		menuWindow.setSize(1000, 400);

		// Finally, make the window visible
		menuWindow.setVisible(true);
		menuWindow.setResizable(false);

	}

}