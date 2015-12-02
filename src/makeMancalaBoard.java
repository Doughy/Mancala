import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.Random;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class makeMancalaBoard extends JPanel implements MouseListener {
	
	public JFrame alpha=new JFrame("Mancala");
	public Color backgroundColor, pitColor, fontColor, marbleColor, marbleOutlineColor;
	public int number=5;
	public int mancalaA=100;
	public int mancalaB=100;
	public int[][] board= {{4,2,5,0,1,10},{10,2,0,1,2,3}};
	
	public makeMancalaBoard(Color backgroundColor, Color pitColor, Color fontColor, Color marbleColor, Color marbleOutlineColor) {
		this.backgroundColor=backgroundColor;
		this.pitColor=pitColor;
		this.fontColor=fontColor;
		this.marbleColor=marbleColor;
		this.marbleOutlineColor=marbleOutlineColor;
	}
	
	public void paintComponent(Graphics g) {
		int randomX, randomY;
		super.paintComponent(g);
		this.setBackground(backgroundColor);
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(pitColor);
		g2.fillRoundRect(50, 150, 100, 400, 75, 75);
		g2.drawRoundRect(950, 650, 150, 50, 25, 25);
		g2.fillRoundRect(970, 150, 100, 400, 75, 75);
		g2.setStroke(new BasicStroke(20));
		g2.drawLine(225, 50, 925, 50);
		g2.drawLine(200, 650, 905, 650);
		g2.drawLine(200, 50, 225, 25);
		g2.drawLine(200, 50, 225, 75);
		g2.drawLine(925, 650, 900, 625);
		g2.drawLine(925, 650, 900, 675);
		g2.setStroke(new BasicStroke(5));
		for(int i=0;i<=5;i++){
			g2.setColor(pitColor);
			g2.fillRoundRect((135*i)+160, 150, 125, 125, 75, 75);
			for(int j=0;j<board[0][i];j++){
				randomX=random(135*i+160);
				randomY=random(150);
				g2.setColor(backgroundColor);
				g2.draw(new Ellipse2D.Double(randomX, randomY, 35, 35));
				g2.setColor(fontColor);
				g2.fill(new Ellipse2D.Double(randomX, randomY, 35, 35));
			}
		}
		for(int i=0;i<=5;i++){
			g2.setColor(pitColor);
			g2.fillRoundRect((135*i)+160, 425, 125, 125, 75, 75);
			for(int j=0;j<board[1][i];j++){
				randomX=random(135*i+160);
				randomY=random(425);
				g2.setColor(backgroundColor);
				g2.draw(new Ellipse2D.Double(randomX, randomY, 35, 35));
				g2.setColor(fontColor);
				g2.fill(new Ellipse2D.Double(randomX, randomY, 35, 35));
			}
		}
		
		for(int i=0;i<=mancalaA;i++){
			g2.setColor(pitColor);
			randomX=randomMancalaX(53);
			randomY=randomMancalaY(155);
			g2.setColor(backgroundColor);
			g2.draw(new Ellipse2D.Double(randomX, randomY, 35, 35));
			g2.setColor(fontColor);
			g2.fill(new Ellipse2D.Double(randomX, randomY, 35, 35));
		}
		
		for(int i=0;i<=mancalaB;i++){
			g2.setColor(pitColor);
			randomX=randomMancalaX(973);
			randomY=randomMancalaY(155);
			g2.setColor(backgroundColor);
			g2.draw(new Ellipse2D.Double(randomX, randomY, 35, 35));
			g2.setColor(fontColor);
			g2.fill(new Ellipse2D.Double(randomX, randomY, 35, 35));
		}
		
		g2.setColor(fontColor);
		g2.setFont(new Font("Helvetica", Font.ITALIC, 120));
		g2.drawString("Mancala", 350, 400);

		g2.setFont(new Font("Helvetica", Font.ITALIC, 60));
		for(int i=0;i<=5;i++){
			g2.drawString("A"+(i+1), (135*i)+185, 600);
		}
		for(int i=5;i>=0;i--){
			g2.drawString("B"+(i+1), (135*i)+185, 150);
		}
		g2.drawString(""+startBox.stoneNumber(), 50, 50);
		g2.drawString(""+number, 100, 50);
		g2.drawString("Undo", 950, 700);
		g2.setStroke(new BasicStroke(5));
	}

	private int random(int x) {
		Random random=new Random();
		int low=x+5;
		int high=x+85;
		return random.nextInt(high-low)+low;
	}
	
	private int randomMancalaX(int x){
		Random random=new Random();
		int low=x+5;
		int high=x+55;
		return random.nextInt(high-low)+low;
	}
	
	private int randomMancalaY(int y){
		Random random=new Random();
		int low=y+5;
		int high=y+355;
		return random.nextInt(high-low)+low;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("x="+e.getX());
		System.out.println("y="+e.getY());
		if((startBox.game.getPlayerTurn())!=1 && e.getX()>160 && e.getX()<960 && e.getY()>460 && e.getY()<590)
			wrongTurn();
		if((startBox.game.getPlayerTurn())!=0 && e.getX()>160 && e.getX()<960 && e.getY()>185 && e.getY()<315)
			wrongTurn();
		if((startBox.game.getPlayerTurn())==1)
			for(int i=0; i<=5;i++){
				if(e.getX()>((135*i)+160) && e.getX()<((135*i)+285)&& e.getY()>460 &&e.getY()<590)
					startBox.game.move(1, i);
			}
		if((startBox.game.getPlayerTurn())==0)
			for(int i=5; i>=0; i--){
				if(e.getX()>((135*i)+160) && e.getX()<((135*i)+285)&& e.getY()>185 &&e.getY()<315)
					startBox.game.move(0, i);
			}
		if(e.getX()>950 && e.getX()<1100 && e.getY()>700 && e.getY()<750)
			number++;
		startBox.game.printBoard();
		repaint();
	}

	private void wrongTurn() {
		JFrame frame = new JFrame("Wrong Turn");
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
	
	private void gameOver() {
		JFrame frame = new JFrame("Game Over!");
		Container gameOverDialog=new Container();
		JLabel winnerAlert = new JLabel("WINNER IS");
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

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}
}
