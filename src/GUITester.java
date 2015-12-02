import javax.swing.JFrame;


public class GUITester {
	public static void main(String[] args) {
		startBox startBox = new startBox();
		startBox.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		startBox.showStartUpBox();
	}
}
