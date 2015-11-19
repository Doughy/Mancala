import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class makeMancalaBoard extends JPanel implements MouseListener {
	
	public JFrame alpha=new JFrame("Mancala");
	public int number=5;
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.setBackground(startBox.getBackgroundColor());
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(startBox.getPitColor());
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
		for(int i=0;i<=5;i++){
			g2.fillRoundRect((135*i)+160, 150, 125, 125, 75, 75);
		}
		for(int i=0;i<=5;i++){
			g2.fillRoundRect((135*i)+160, 425, 125, 125, 75, 75);
		}
		g2.setColor(startBox.getFontColor());
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
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getX()>950 && e.getX()<1100 && e.getY()>700 && e.getY()<750)
			number++;
		repaint();
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
