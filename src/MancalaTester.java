import java.util.Scanner;

public class MancalaTester {
	public static void main(String args[])
	{
		Scanner in=new Scanner(System.in);
		Mancala game=new Mancala();
		System.out.println("Please enter number of stones to play with");
		int stones=in.nextInt();
		game.setStones(stones);
		boolean player1=true;
		int position=0;
		while(!game.gameOver())
		{
			game.printBoard();
			if(player1)
			{
				System.out.println("Player A please enter space to pick");
				position=in.nextInt()-1;
				player1=game.move(1, position);
			}
			else
			{
				System.out.println("Player B please enter space to pick");
				position=6-(in.nextInt());
				player1=!game.move(0, position);
			}
		}
		
		
	}
}
