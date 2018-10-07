/*
 * Primary author: Eylam Tagor
 * Date of completion: 5/17/18
 * Revision Number: 3
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

	// Methods:

	// Sets ship's length (5, 4, 3, 3, or 2)
	public void setLength(int x) {
		this.length = x;
	}

	// Changes/adds ship's x and y coordinates
	public void addPosition(int x, int y) {
		super.changePosition(x, y);
	}

	// Changes ship's rotation
	public void changeOrientation() {
		isHorizontal = !isHorizontal;
	}

	// Returns length of ship
	public int getLength() {
		return length;
	}

}
