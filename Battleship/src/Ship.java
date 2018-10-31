
/*
 * Primary Author: Eylam Tagor
 * Date of Completion: 5/10/18
 * Revision Number: 4
 */

public class Ship extends GamePiece {

	// Fields
	public int length;
	public boolean isHorizontal;

	// No-args constructor
	public Ship() {
		super(0, 0);
		isHorizontal = true;
	}

	// Sets the length
	public void setLength(int x) {
		this.length = x;
	}

	// Returns the length of battleship
	public int getLength() {
		return length;
	}

	// Changes the position of the ship
	public void addPosition(int x, int y) {
		super.changePosition(x, y);
	}

	// Changes the orientation
	public void changeOrientation() {
		isHorizontal = !isHorizontal;
	}

}
