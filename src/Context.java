import java.awt.Color;


public class Context {
	private BoardLayout boardLayout;
	public Context(BoardLayout boardLayout){
		this.boardLayout=boardLayout;
	}
	public Color getPitColor(){
		return boardLayout.getPitColor();
	}
	public Color getBackgroundColor(){
		return boardLayout.getBackgroundColor();
	}
	public Color getFontColor(){
		return boardLayout.getFontColor();
	}
	public Color getMarbleColor(){
		return boardLayout.getMarbleColor();
	}
	public Color getMarbleOutlineColor(){
		return boardLayout.getMarbleOutlineColor();
	}
}