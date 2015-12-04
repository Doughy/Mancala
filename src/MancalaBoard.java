import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.util.Random;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Builds the Mancala Board game and repaints it
 * allow 2 people to play
 * @author Fighting Mongoose
 */
@SuppressWarnings("serial")
public class MancalaBoard extends JPanel implements MouseListener {
	
	private Color backgroundColor, pitColor, fontColor, marbleColor, marbleOutlineColor;
	private Mancala game=null;
	private int[][] board=null;
	private JFrame frame;
	
	/**
	 * Constructor for the Mancala Board GUI
	 * @param backgroundColor the background color for the board
	 * @param pitColor the pit color for the board
	 * @param fontColor the font color for the board
	 * @param marbleColor the marble color for the board
	 * @param marbleOutlineColor the marble outline color for the board
	 * @param game the game which is used to control the data movement of the marbles
	 */
	public MancalaBoard(Color backgroundColor, Color pitColor, Color fontColor, Color marbleColor, Color marbleOutlineColor, Mancala game) {
		this.backgroundColor=backgroundColor;
		this.pitColor=pitColor;
		this.fontColor=fontColor;
		this.marbleColor=marbleColor;
		this.marbleOutlineColor=marbleOutlineColor;
		this.game=game;
		board=game.getBoard();
	}
	
	/**
	 * paintComponent that draws the entire Mancala Board with the marbles updated
	 */
	public void paintComponent(Graphics g) {
		int randomX, randomY;
		super.paintComponent(g);
		this.setBackground(backgroundColor);
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(pitColor);
		
		//used to draw the arrows
		g2.setStroke(new BasicStroke(20));
		g2.drawLine(225, 50, 925, 50);
		g2.drawLine(200, 650, 905, 650);
		g2.drawLine(200, 50, 225, 25);
		g2.drawLine(200, 50, 225, 75);
		g2.drawLine(925, 650, 900, 625);
		g2.drawLine(925, 650, 900, 675);
		g2.setStroke(new BasicStroke(5));
		
		//draws player 1's pits and the marbles inside them
		for(int i=0;i<=5;i++){
			g2.setColor(pitColor);
			g2.fillRoundRect((135*i)+160, 425, 125, 125, 75, 75);
			for(int j=0;j<board[1][i];j++){
				randomX=random(135*i+160);
				randomY=random(425);
				g2.setColor(marbleOutlineColor);
				g2.draw(new Ellipse2D.Double(randomX, randomY, 35, 35));
				g2.setColor(marbleColor);
				g2.fill(new Ellipse2D.Double(randomX, randomY, 35, 35));
			}
		}
				
		//draws player 1's pits and the marbles inside them
		for(int i=0;i<=5;i++){
			g2.setColor(pitColor);
			g2.fillRoundRect((135*i)+160, 150, 125, 125, 75, 75);
			for(int j=0;j<board[0][i];j++){
				randomX=random(135*i+160);
				randomY=random(150);
				g2.setColor(marbleOutlineColor);
				g2.draw(new Ellipse2D.Double(randomX, randomY, 35, 35));
				g2.setColor(marbleColor);
				g2.fill(new Ellipse2D.Double(randomX, randomY, 35, 35));
			}
		}
		
		//draws the marbles in player 2's Mancala
		g2.fillRoundRect(50, 150, 100, 400, 75, 75);
		if(game.getPlayer2Mancala()>0)
			for(int i=0;i<game.getPlayer2Mancala();i++){
				randomX=randomMancalaX(53);
				randomY=randomMancalaY(155);
				g2.setColor(marbleOutlineColor);
				g2.draw(new Ellipse2D.Double(randomX, randomY, 35, 35));
				g2.setColor(marbleColor);
				g2.fill(new Ellipse2D.Double(randomX, randomY, 35, 35));
			}
		
		//draws the marbles in player 1's Mancala
		g2.fillRoundRect(970, 150, 100, 400, 75, 75);
		if(game.getPlayer1Mancala()>0)
			for(int i=0;i<game.getPlayer1Mancala();i++){
				randomX=randomMancalaX(973);
				randomY=randomMancalaY(155);
				g2.setColor(marbleOutlineColor);
				g2.draw(new Ellipse2D.Double(randomX, randomY, 35, 35));
				g2.setColor(marbleColor);
				g2.fill(new Ellipse2D.Double(randomX, randomY, 35, 35));
			}
		//draws the center Mancala game title
		g2.setColor(fontColor);
		g2.setFont(new Font("Helvetica", Font.ITALIC, 120));
		g2.drawString("Mancala", 350, 400);

		//draws both the A and B pit numbers for the players
		g2.setFont(new Font("Helvetica", Font.ITALIC, 60));
		for(int i=0;i<=5;i++){
			g2.drawString("A"+(i+1), (135*i)+185, 600);
		}
		for(int i=5;i>=0;i--){
			g2.drawString("B"+(i+1), (135*i)+185, 150);
		}
		
		//draws the undo button and the amount
		g2.drawRoundRect(950, 650, 200, 50, 25, 25);
		g2.drawString("Undo "+game.getUndoAmount(), 950, 700);
		
		//draws the player turn string
		if(game.getPlayerTurn()==0)
			g2.drawString("Player 2's Turn", 50, 725);
		else if(game.getPlayerTurn()==1)
			g2.drawString("Player 1's Turn", 50, 725);
	}

	/**
	 * Random method to get the spot where the marbles need to be made
	 * @param x the starting location for the marble
	 * @return the random spot for the marble
	 */
	private int random(int x) {
		Random random=new Random();
		int low=x+5;
		int high=x+85;
		return random.nextInt(high-low)+low;
	}
	
	/**
	 * Random method to get the spot where the marbles need to be made in the
	 * x for the Mancala
	 * @param x the starting location for the marble
	 * @return the random spot for the marble
	 */
	private int randomMancalaX(int x){
		Random random=new Random();
		int low=x+5;
		int high=x+55;
		return random.nextInt(high-low)+low;
	}
	
	/**
	 * Random method to get the spot where the marbles need to be made in the
	 * y for the Mancala
	 * @param y the starting location for the marble
	 * @return the random spot for the marble
	 */
	private int randomMancalaY(int y){
		Random random=new Random();
		int low=y+5;
		int high=y+355;
		return random.nextInt(high-low)+low;
	}
	
	/**
	 * mouseClick operation for controlling if the
	 * board is clicked and where to make a move on the
	 * game board
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println(e.getX());
		//Controls for if a player goes on the wrong turn
		if((game.getPlayerTurn())!=1 && e.getX()>160 && e.getX()<960 && e.getY()>460 && e.getY()<590)
			wrongTurn();
		if((game.getPlayerTurn())!=0 && e.getX()>160 && e.getX()<960 && e.getY()>185 && e.getY()<315)
			wrongTurn();
		
		//calls the move method if it is player 1's turn
		if((game.getPlayerTurn())==1)
			for(int i=0; i<=5;i++){
				if(e.getX()>((135*i)+160) && e.getX()<((135*i)+285)&& e.getY()>460 &&e.getY()<590)
					game.move(1, i);
			}
		
		//calls the move method if it is player 2's turn
		if((game.getPlayerTurn())==0)
			for(int i=5; i>=0; i--){
				if(e.getX()>((135*i)+160) && e.getX()<((135*i)+285)&& e.getY()>185 &&e.getY()<315)
					game.move(0, i);
			}
		
		//calls the undo method if clicked
		if(e.getX()>950 && e.getX()<1150 && e.getY()>700 && e.getY()<750)
			game.undoMove();
		
		//prints the board in the command line
		game.printBoard();
		
		//checks if the game is over, if it is ends the game
		if(game.isGameOver()!=0)
			gameOver();
		repaint();
	}

	/**
	 * Pops up a dialog box if a player tries to make a move
	 * and it is not their turn
	 */
	private void wrongTurn() {
		frame = new JFrame("Wrong Turn");
		Container wrongTurnDialog=new Container();
		JLabel wrongTurn = new JLabel("Not your turn!");
		Box box1 = Box.createHorizontalBox();
		Box box2 = Box.createHorizontalBox();
		JButton closeButton = new JButton("Close");
		closeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		box1.add(wrongTurn);
		box2.add(closeButton);
		wrongTurnDialog.setLayout(new BoxLayout(wrongTurnDialog, BoxLayout.Y_AXIS));
		wrongTurnDialog.add(box1);
		wrongTurnDialog.add(box2);
		frame.add(wrongTurnDialog);
		frame.pack();
		frame.setVisible(true);
		wrongTurnDialog.setVisible(true);
	}
	
	/**
	 * Pops up a dialog box if the game is over
	 * and tells the winner
	 * when the button is clicked ends the game
	 */
	private void gameOver() {
		JFrame frame = new JFrame("Game Over!");
		Container gameOverDialog=new Container();
		JLabel winnerAlert = new JLabel("WINNER IS PLAYER "+game.isGameOver());
		Box box1 = Box.createHorizontalBox();
		Box box2 = Box.createHorizontalBox();
		JButton closeButton = new JButton("End Game");
		closeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		box1.add(winnerAlert);
		box2.add(closeButton);
		gameOverDialog.setLayout(new BoxLayout(gameOverDialog, BoxLayout.Y_AXIS));
		gameOverDialog.add(box1);
		gameOverDialog.add(box2);
		frame.add(gameOverDialog);
		frame.pack();
		frame.setVisible(true);
		gameOverDialog.setVisible(true);
	}

	/**
	 * mouseEntered unused
	 */
	@Override
	public void mouseEntered(MouseEvent e) {}

	/**
	 * mouseExited unused
	 */
	@Override
	public void mouseExited(MouseEvent e) {}

	/**
	 * mousePressed unused
	 */
	@Override
	public void mousePressed(MouseEvent e) {}

	/**
	 * mouseReleased unused
	 */
	@Override
	public void mouseReleased(MouseEvent e) {}
}