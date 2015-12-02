import java.awt.Color;
import java.awt.Container;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class startBox extends JDialog {
	private Container frame;
	public Color pitColor=null;
	public Color backgroundColor=null;
	public Color fontColor=null;
	public Color marbleColor=null;
	public Color marbleOutlineColor=null;
	private static int stoneCount;
	private String layout;
	private int width = 250;
	private int height = 150;
	public JFrame mancalaBoard=new JFrame("Mancala");
	public static Mancala game=new Mancala();
	public String[] layouts={"Red and Blue","Black and White"};

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public startBox() {
		stoneCount = 3;
		layout = layouts[0];
		JComboBox choices = new JComboBox();
		choices.addItem("Choose Stone Count");
		choices.addItem(3);
		choices.addItem(4);

		choices.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				stoneCount=(int) choices.getSelectedItem();
			}
		});

		JComboBox layoutChoices = new JComboBox();
		layoutChoices.addItem("Choose Layout Design");
		layoutChoices.addItem(layouts[0]);
		layoutChoices.addItem(layouts[1]);
		
		layoutChoices.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (layouts[0].equals(layoutChoices.getSelectedItem())) {
					Context context = new Context(new RedBlueLayout());	
					pitColor=context.getPitColor();
					backgroundColor=context.getBackgroundColor();
					fontColor=context.getFontColor();
					marbleColor=context.getMarbleColor();
					marbleOutlineColor=context.getMarbleOutlineColor();
					;
				} else if (layouts[1].equals(layoutChoices.getSelectedItem())) {
					Context context = new Context(new BlackWhiteLayout());
					pitColor=context.getPitColor();
					backgroundColor=context.getBackgroundColor();
					fontColor=context.getFontColor();
					marbleColor=context.getMarbleColor();
					marbleOutlineColor=context.getMarbleOutlineColor();
					;
				} else{
					Context context = new Context(new RedBlueLayout());
					pitColor=context.getPitColor();
					backgroundColor=context.getBackgroundColor();
					fontColor=context.getFontColor();
					marbleColor=context.getMarbleColor();
					marbleOutlineColor=context.getMarbleOutlineColor();
					;
				}
			}
		});

		frame = getContentPane();
		setSize(width, height);
		JButton start = new JButton("Start Game");
		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				Scanner in=new Scanner(System.in);
				makeMancalaBoard showMancalaBoard = new makeMancalaBoard(backgroundColor, pitColor, fontColor, marbleColor, marbleOutlineColor);
				mancalaBoard.add(showMancalaBoard);
				mancalaBoard.setSize(1150, 800);
				mancalaBoard.setDefaultCloseOperation(EXIT_ON_CLOSE);
				mancalaBoard.setResizable(false);
				mancalaBoard.addMouseListener(showMancalaBoard);
				mancalaBoard.setVisible(true);
				game.setStones(stoneNumber());
				boolean player1=true;
				dispose();
				int position=0;
				game.printBoard();
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
}
