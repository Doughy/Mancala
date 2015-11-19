import javax.swing.JFrame;


public class GUITester {
	public static String[] layouts={"NormalLayout","myBlackandWhiteLayout"};
	public static void main(String[] args) {
		startBox startBox = new startBox(layouts);
		startBox.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		startBox.showStartUpBox();
	}
}
