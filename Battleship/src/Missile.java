/*
 * Primary author: Eylam Tagor
 * Date of completion: 5/17/18
 * Revision Number: 3
 */

public class Missile extends GamePiece {

	// Fields:
	private int typeHit = 0;

	// Constructor:
	public Missile(int x, int y, int a) {
		super(x, y);
		typeHit = a;
	}

	// Accessor/Mutator Methods:

	// Returns the missile type (hit/miss)
	public int getType() {
		return typeHit;
	}

	// Sets the missile's type
	public void setType(int type) {
		typeHit = type;
	}

}
