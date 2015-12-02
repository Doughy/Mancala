import java.awt.Color;

/**
 * Black and white layout for the board design
 * @author Fighting Mongoose
 * Implements the BoardLayout.java
 */
public class BlackWhiteLayout implements BoardLayout {
	/**
	 * Gets the Black and White layouts pit color
	 */
	@Override
	public Color getPitColor(){
		return Color.WHITE;
	}
	
	/**
	 * Gets the Black and White layouts background color
	 */
	@Override
	public Color getBackgroundColor(){
		return Color.BLACK;
	}
	
	/**
	 * Gets the Black and White layouts font color
	 */
	@Override
	public Color getFontColor(){
		return Color.WHITE;
	}
	
	/**
	 * Gets the Black and White layouts marble color
	 */
	@Override
	public Color getMarbleColor() {
		return Color.WHITE;
	}
	
	/**
	 * Gets the Black and White layouts marble outline color
	 */
	@Override
	public Color getMarbleOutlineColor() {
		return Color.BLACK;
	}
}
