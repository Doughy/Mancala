import java.awt.Color;

/**
 * Context methods for Strategy Pattern
 * @author Zero
 */
public class Context {
	private BoardLayout boardLayout;
	
	/**
	 * Context constructor
	 */
	public Context(BoardLayout boardLayout){
		this.boardLayout=boardLayout;
	}
	
	/**
	 * Context that gets the layouts pit color
	 */
	public Color getPitColor(){
		return boardLayout.getPitColor();
	}
	
	/**
	 * Context that gets the layouts background color
	 */
	public Color getBackgroundColor(){
		return boardLayout.getBackgroundColor();
	}
	
	/**
	 * Context that gets the layouts font color
	 */
	public Color getFontColor(){
		return boardLayout.getFontColor();
	}
	
	/**
	 * Context that gets the layouts marble color
	 */
	public Color getMarbleColor(){
		return boardLayout.getMarbleColor();
	}
	
	/**
	 * Context that gets the layouts marble outline color
	 */
	public Color getMarbleOutlineColor(){
		return boardLayout.getMarbleOutlineColor();
	}
}