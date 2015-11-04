
public class Mancala {
	private int mancalaA;//player A's Mancala count
	private int mancalaB;//player B's Mancala count
	private int board[][];//2d array for mancala board
	private static final int ROWS=2;//rows of board
	private static final int COLUMNS=6;//columns of board
	
	/**
	 * this constructs a blank Mancala board
	 * with zero stones in each pit. 
	 */
	public Mancala()
	{
		mancalaA=0;
		mancalaB=0;
		board=new int[ROWS][COLUMNS];
	}
	
	/**
	 * this method sets the starting number of
	 * stones in a pit.
	 * @param numStones number of stones in each pit
	 */
	public void setStones(int numStones)
	{
		for(int y=0; y < ROWS; y++)
		{
			for(int x=0; x < COLUMNS; x++)
			{
				board[y][x]=numStones;
			}
		}
	}
	
	/**
	 * this method checks if there are stones in a pit
	 * @param x board's x position
	 * @param y board's y position
	 * @return TRUE if it has stones; FALSE if there are no stones
	 */
	public boolean hasStones(int x, int y)
	{
		return !(board[y][x]==0);
	}
	
	/**
	 * this method returns the stones from a pit
	 * and sets that pit to zero
	 * @param x board's x position
	 * @param y board's y position
	 * @return number of stones in pit
	 */
	public int getStones(int x, int y)
	{
		int stonesInPit=board[y][x];
		board[y][x]=0;
		return stonesInPit;
	}
	
	public int aCapture(int stones)
	{
		mancalaA++;
		return --stones;
	}
	
	public int bCapture(int stones)
	{
		mancalaB++;
		return --stones;
	}
	
	/**
	 * this method is used to move the stones around
	 * the board by a player
	 * @param playerNum 0 or 1 corresponds to player B or player A
	 * @param pitPosition 0-5 corresponds to the players pits' index number
	 */
	public boolean move(int  playerNum, int pitPosition)
	{
		boolean goAgain=false;
		int y=playerNum;
		int x=pitPosition;
		
		if(hasStones(x,y))
		{
			int stonesToPlace=getStones(x,y);
			while(stonesToPlace>0)
			{
				if(x==COLUMNS-1 && y==1)
				{
					if(playerNum==1)
					{
						stonesToPlace=aCapture(stonesToPlace);
						goAgain=(stonesToPlace==0);
					}
					y=0;
					x++;

				}
				else if(x==0 && y==0)
				{
					if(playerNum==0)
					{
						stonesToPlace=bCapture(stonesToPlace);
						goAgain=(stonesToPlace==0);
					}
					y=1;
					x--;
				}
				else
				{
					x= x + (y * 2 - 1);
					board[y][x]++;
					stonesToPlace--;
				}
			}
		}
		return goAgain;
	}
	
	public boolean gameOver()
	{
		boolean over=false;
		
		for(int y=0;y<ROWS ;y++)
		{
			int sum=0;
			for(int x=0;x<COLUMNS;x++)
			{
				sum+=board[y][x];
			}
			if(sum==0)
				over=true;
		}
		
		return over;
	}
	
	public void printBoard()
	{
		System.out.println("B6 B5 B4 B3 B2 B1");
		for(int y=0;y<ROWS;y++)
		{
			for(int x=0;x<COLUMNS;x++)
			{
				System.out.print(String.format("%2d",board[y][x])+" ");
			}
			if(y==0)
				System.out.println("\nB: "+mancalaB+"             A:"+mancalaA);
		}
		System.out.println("\nA1 A2 A3 A4 A5 A6");
	}
}
