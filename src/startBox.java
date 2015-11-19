import java.awt.Color;
import java.awt.Container;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;

public class startBox extends JDialog implements ActionListener {
	private Container frame;
	public static Color pitColor=null;
	public static Color backgroundColor=null;
	public static Color fontColor=null;
	private static int stoneCount;
	private static String layout;
	private int width = 250;
	private int height = 150;
	public JFrame mancalaBoard=new JFrame("Mancala");

	public startBox(String[] layouts) {
		stoneCount = 3;
		layout = layouts[0];
		JComboBox choices = new JComboBox();
		choices.addItem("Choose Stone Count");
		choices.addActionListener(this);
		choices.addItem(3);
		choices.addItem(4);

		JComboBox layoutChoices = new JComboBox();
		layoutChoices.addItem("Choose Layout Design");
		layoutChoices.addItem(layouts[0]);
		layoutChoices.addItem(layouts[1]);
		
		layoutChoices.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (layouts[0].equals(layoutChoices.getSelectedItem())) {
					layout = layouts[0];
					pitColor=NormalLayout.getPitColor();
					backgroundColor=NormalLayout.getBackgroundColor();
					fontColor=NormalLayout.getFontColor();
					;
				} else if (layouts[1].equals(layoutChoices.getSelectedItem())) {
					layout = layouts[1];
					pitColor=BlackWhiteLayout.getPitColor();
					backgroundColor=BlackWhiteLayout.getBackgroundColor();
					fontColor=BlackWhiteLayout.getFontColor();
					;
				}
			}
		});

		frame = getContentPane();
		setSize(width, height);
		JButton start = new JButton("Start Game");
		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				makeMancalaBoard showMancalaBoard = new makeMancalaBoard();
				mancalaBoard.add(showMancalaBoard);
				mancalaBoard.setSize(1150, 800);
				mancalaBoard.setDefaultCloseOperation(EXIT_ON_CLOSE);
				mancalaBoard.setResizable(false);
				mancalaBoard.addMouseListener(showMancalaBoard);
				mancalaBoard.setVisible(true);
				dispose();
			}
		});

		Box box1 = Box.createHorizontalBox();
		box1.add(choices);

		Box box2 = Box.createHorizontalBox();
		box2.add(layoutChoices);
		
		Box box3 = Box.createHorizontalBox();
		box3.add(start);

		frame.setLayout(new BoxLayout(frame, BoxLayout.PAGE_AXIS));
		frame.add(box1);
		frame.add(box2);
		frame.add(box3);
		setResizable(false);
	}

	public void showStartUpBox() {setVisible(true);}
	
	public static int stoneNumber() {return stoneCount;}
	
	public static String getSelectedLayout() {return layout;}
	
	public static Color getPitColor(){return pitColor;}
	
	public static Color getBackgroundColor(){return backgroundColor;}
	
	public static Color getFontColor(){return fontColor;}

	@Override
	public void actionPerformed(ActionEvent e) {}
}
