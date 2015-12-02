import java.awt.Color;


public class RedBlueLayout implements BoardLayout{
	@Override
	public Color getPitColor(){
		return Color.RED;
	}
	@Override
	public Color getBackgroundColor(){
		return Color.BLUE;
	}
	@Override
	public Color getFontColor(){
		return Color.RED;
	}
	@Override
	public Color getMarbleColor(){
		return Color.RED;
	}
	@Override
	public Color getMarbleOutlineColor(){
		return Color.BLUE;
	}
}
