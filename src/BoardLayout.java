import java.awt.Color;

/**
 * BoardLayout interface
 * @author Fighting Mongoose
 */
public interface BoardLayout {
	
	/**
	 * Gets the layouts pit color
	 */
	public Color getPitColor();
	
	/**
	 * Gets the layouts background color
	 */
	public Color getBackgroundColor();
	
	/**
	 * Gets the layouts font color
	 */
	public Color getFontColor();
	
	/**
	 * Gets the layouts marble color
	 */
	public Color getMarbleColor();
	
	/**
	 * Gets the layouts marble outline color
	 */
	public Color getMarbleOutlineColor();
}
