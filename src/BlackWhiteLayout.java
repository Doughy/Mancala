import java.awt.Color;


public class BlackWhiteLayout implements BoardLayout {
	@Override
	public Color getPitColor(){
		return Color.WHITE;
	}
	@Override
	public Color getBackgroundColor(){
		return Color.BLACK;
	}
	@Override
	public Color getFontColor(){
		return Color.WHITE;
	}
	@Override
	public Color getMarbleColor() {
		return Color.WHITE;
	}
	@Override
	public Color getMarbleOutlineColor() {
		return Color.BLACK;
	}
}
