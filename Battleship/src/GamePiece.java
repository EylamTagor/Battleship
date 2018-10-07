/*
 * Primary author: Eylam Tagor
 * Date of completion: 5/17/18
 * Revision Number: 2
 */

public class GamePiece {

	// Fields
	public int x, y;

	// Constructor:
	public GamePiece(int a, int b) {
		x = a;
		y = b;
	}

	// Methods:

	// Changes the coordinates of the gamepiece object
	public void changePosition(int x, int y) {
		this.x = x;
		this.y = y;
	}

}
