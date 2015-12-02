import java.awt.Color;

/**
 * Red and Blue layout for the board design
 * @author Fighting Mongoose
 * Implements the BoardLayout.java
 */
public class RedBlueLayout implements BoardLayout{
	/**
	 * Gets the Red and Blue layouts pit color
	 */
	@Override
	public Color getPitColor(){
		return Color.RED;
	}
	
	/**
	 * Gets the Red and Blue layouts background color
	 */
	@Override
	public Color getBackgroundColor(){
		return Color.BLUE;
	}
	
	/**
	 * Gets the Red and Blue layouts Font color
	 */
	@Override
	public Color getFontColor(){
		return Color.RED;
	}
	
	/**
	 * Gets the Red and Blue layouts marble color
	 */
	@Override
	public Color getMarbleColor(){
		return Color.RED;
	}
	
	/**
	 * Gets the Red and Blue layouts marble outline color
	 */
	@Override
	public Color getMarbleOutlineColor(){
		return Color.BLUE;
	}
}
