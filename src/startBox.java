import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;

/**
 * Startup popup box to ask the user how many stones
 * and the design layout wanted
 * @author Fighting Mongoose
 */
@SuppressWarnings("serial")
public class StartBox extends JDialog {
	private Container frame;
	private Color pitColor, backgroundColor, fontColor, marbleColor, marbleOutlineColor;
	private static int stoneCount=3;
	private int width = 250, height=150;
	private JFrame mancalaBoard=new JFrame("Mancala");
	private static Mancala game=new Mancala();
	private String[] layouts={"Red and Blue","Black and White"};

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public StartBox() {
		//comboBox for choosing the stone amount
		JComboBox choices = new JComboBox();
		choices.addItem("Choose Stone Count");
		choices.addItem(3);
		choices.addItem(4);
		
		//sets the stone choice to the stoneCount variable for use
		choices.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				stoneCount=(int) choices.getSelectedItem();
			}
		});

		//sets the context to default to the red and blue layout
		Context context = new Context(new RedBlueLayout());	
		pitColor=context.getPitColor();
		backgroundColor=context.getBackgroundColor();
		fontColor=context.getFontColor();
		marbleColor=context.getMarbleColor();
		marbleOutlineColor=context.getMarbleOutlineColor();
		
		//comboBox for the layout design choices
		JComboBox layoutChoices = new JComboBox();
		layoutChoices.addItem("Choose Layout Design");
		layoutChoices.addItem(layouts[0]);
		layoutChoices.addItem(layouts[1]);
		
		//actionListener to change the layout to a different style
		layoutChoices.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (layouts[1].equals(layoutChoices.getSelectedItem())) {
					Context context = new Context(new BlackWhiteLayout());
					pitColor=context.getPitColor();
					backgroundColor=context.getBackgroundColor();
					fontColor=context.getFontColor();
					marbleColor=context.getMarbleColor();
					marbleOutlineColor=context.getMarbleOutlineColor();
				} 
			}
		});

		//builds the frame 
		frame = getContentPane();
		setSize(width, height);
		JButton start = new JButton("Start Game");
		//start button action listener for the game, disposes this frame
		//and builds the mancala baord frame
		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				MancalaBoard showMancalaBoard = new MancalaBoard(backgroundColor, pitColor, fontColor, marbleColor, marbleOutlineColor, game);
				mancalaBoard.add(showMancalaBoard);
				mancalaBoard.setSize(1150, 800);
				mancalaBoard.setDefaultCloseOperation(EXIT_ON_CLOSE);
				mancalaBoard.setResizable(false);
				mancalaBoard.addMouseListener(showMancalaBoard);
				mancalaBoard.setVisible(true);
				game.setStones(stoneNumber());
				dispose();
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

	/**
	 * Makes the startup dialog box visible
	 */
	public void showStartUpBox() {setVisible(true);}
	
	/**
	 * to return the stone count for the board designs
	 * @return the stoneCount for the game
	 */
	private static int stoneNumber() {return stoneCount;}
}
