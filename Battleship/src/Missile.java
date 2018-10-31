
/*
 * Primary Author: Eylam Tagor
 * Date of Completion: 5/10/18
 * Revision Number: 3
 */

public class Missile extends GamePiece {

	// Field
	private int typeHit = 0;

	// Constructor with x-y coordinates and type of hit
	public Missile(int x, int y, int a) {
		super(x, y);
		typeHit = a;
	}

	// Sets the type of hit
	public void setType(int type) {
		typeHit = type;
	}

	// Returns the type of hit
	public int getType() {
		return typeHit;
	}
	
}
