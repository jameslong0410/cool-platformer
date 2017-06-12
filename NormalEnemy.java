/******
* name: Patrick Au, James Long
* date: March 2017
* code: ICS4U1
* note: NormalEnemy character - avoids walking off ledges
*******/
import java.awt.*;

class NormalEnemy extends Enemy
{
	NormalEnemy(double x, double y)
	{
		super(x, y);
	}	// end constructor(double,double)

	public void advance()
	{
		if(frozen) return;
		// Directions are RELATIVE here ie. "R" in "Up" Field <--> "L" in "Down" (Normal) Field
		// Stops at the edge of a cliff
		updateField();
		if(movingRel(RIGHT))
		{
			if(!checkFarR(getField(), LOW) || checkFarR(getField(), ADJ))
				setKey((getField() - RIGHT + 4) % 4, false); // Releases "Right"
		}
		else if(movingRel(LEFT))
		{
			if(!checkFarL(getField(), LOW) || checkFarL(getField(), ADJ))
				setKey((getField() - LEFT + 4) % 4, false); // Releases "Left"
		}
		else if(checkBlock(getField()) && (checkFarL(getField(), LOW) || checkFarR(getField(), LOW)))
		{	// Begin Moving
			boolean moveL = !checkFarL(getField(), ADJ) && checkFarL(getField(), LOW);
			setKey((getField() - LEFT  + 4) % 4, moveL); // Toggles "Left"
			setKey((getField() - RIGHT + 4) % 4, moveL ^ true); // Toggles "Right"
		}
		updateVectors();
		this.vel.add(this.acc);
		move(this.vel);
	}	// end method advance

	@Override // Superclass: Entity
	public void draw(Graphics g)
	{	// Hardcode image for Demo
		g2D = (Graphics2D)g;
		g2D.drawImage(Images.sprites[1], (int)Math.round(pos.X), (int)Math.round(pos.Y), Block.getLen(), Block.getLen(), null);
	}	// end method draw

	@Override // Superclass: Entity
	public void updateField()
	{	// For now, only Fields influence Acceleration, so hardcode
		if(getField() != prevField)
			for(int i = 0; i < 4; ++i)
				setKey(i, false); // Release All
		super.updateField();
	}	// end method updateField
}	// end class Entity
