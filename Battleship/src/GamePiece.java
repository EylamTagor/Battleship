
/*
 * Primary Author: Eylam Tagor
 * Date of Completion: 5/9/18
 * Revision Number: 2
 */

public class GamePiece {

	public int x, y;

	// Constructor with x and y coordinates
	public GamePiece(int x, int y) {
		this.x = x;
		this.y = y;
	}

	// Changes the position of the game piece
	public void changePosition(int x, int y) {
		this.x = x;
		this.y = y;
	}

}