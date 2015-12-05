import java.util.Stack;

public class Mancala {
	private int mancalaA;//player A's Mancala count
	private int mancalaB;//player B's Mancala count
	private int board[][];//2d array for mancala board
	private static final int ROWS=2;//rows of board
	private static final int COLUMNS=6;//columns of board
	private int playerTurn;
	private Stack<int[][]> boards;
	private Stack<int[]> mancalaPits;
	private boolean wentAgain;
	private int aUndo;
	private int bUndo;
	
	/**
	 * this constructs a blank Mancala board
	 * with zero stones in each pit. 
	 */
	public Mancala()
	{
		mancalaA=0;
		mancalaB=0;
		board=new int[ROWS][COLUMNS];
		playerTurn=1;//A's turn
		boards=new Stack<int[][]>();
		mancalaPits=new Stack<int[]>();
		aUndo=3;
		bUndo=3;
		wentAgain=false;
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
		pushBoard();
	}
	
	public void pushBoard()
	{
		int temp[][]=new int[ROWS][];
		for(int i=0; i<ROWS; i++)
		{
			temp[i]=board[i].clone();
		}
		boards.push(temp);
		mancalaPits.push(new int[]{mancalaA, mancalaB});
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
	
	/**
	 * adds a stones to mancala A and returns the stones
	 * still left to place
	 * @param stones number of stones to place
	 * @return stones to still be placed
	 */
	public int aCapture(int stones)
	{
		mancalaA++;
		return --stones;
	}
	
	/**
	 * adds a stones to mancala B and returns the stones
	 * still left to place
	 * @param stones number of stones to place
	 * @return stones to still be placed
	 */
	public int bCapture(int stones)
	{
		mancalaB++;
		return --stones;
	}

	/**
	 * This method captures all the stones from the opposite pit.
	 * @param player This is the current player that receives captured stones
	 * @param pit This is the current pit which has the same x index as the opposite pit.
	 */
	public void captureAllFor(int player, int pit)
	{
		//Takes all the stones from the opposing pit.
		if(player == 0) // y = 0 is Player B. y = 1 is Player A
		{
			int stones = board[1][pit]+1;
			board[1][pit] = 0;
			mancalaB += stones;
		}
		else if(player == 1)
		{
			int stones = board[0][pit]+1;
			board[0][pit] = 0;
			mancalaA += stones;
		}
		else
		{
			System.out.println("Error has Occurred. Non-existing player called");
		}
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
		pushBoard();
		
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
					if(stonesToPlace==1 && board[y][x]==0 && playerNum==y)
					{
						captureAllFor(y,x);
					}
					else
					{
						board[y][x]++;
					}
					stonesToPlace--;
				}
			}
		}
		if(!goAgain)
			playerTurn = 1 - playerTurn;
		wentAgain=goAgain;
		return goAgain;
	}
	
	
	/**
	 * returns whether or not the game is over
	 * @return true or false if game is over or not
	 */
	public boolean gameOver()
	{
		boolean over=false;
		int sumA=0;
		int sumB=0;
		
		for(int x=0;x<COLUMNS ;x++)
		{
			sumA+=board[1][x];
			sumB+=board[0][x];
		}
		
		if(sumA==0 || sumB==0)
		{
			over=true;
			mancalaA+=sumB;
			mancalaB+=sumA;
		}
		
		return over;
	}
	
	/**
	 * returns 0 if game is not over
	 * 1 if player A wins
	 * 2 if player B wins
	 * @return over 0, 1, 2
	 */
	public int isGameOver()
	{
		int over=0;// 0 game not over; 1 game over
		if(gameOver())
		{
			if(mancalaA>mancalaB)
				over=1;//A wins
			else
				over=2;//B wins
		}
		return over;
			
	}
	
	/**
	 * return mancalaA pit
	 * @return mancalaA
	 */
	public int getPlayer1Mancala()
	{
		return mancalaA;
	}
	
	/**
	 * return mancalaB pit
	 * @return mancalaB
	 */
	public int getPlayer2Mancala()
	{
		return mancalaB;
	}
	
	/**
	 * return which player's turn it is
	 * 1 playerA
	 * 2 playerB
	 * @return playerTurn
	 */
	public int getPlayerTurn()
	{
		return playerTurn;
	}
	
	/**
	 * returns 2d array of board
	 * @return board
	 */
	public int[][] getBoard()
	{
		return board;
	}
	
	/**
	 * undos the previous move
	 */
	public void undoMove()
	{
		if(boards.size()>0)
		{
			if((playerTurn==0 && bUndo>0) || (playerTurn==1 && aUndo>0))
			{
				board=boards.pop();
				int[] mancalas=mancalaPits.pop();
				mancalaA=mancalas[0];
				mancalaB=mancalas[1];

				if(!wentAgain)
				{
					playerTurn=1-playerTurn;
				}
				if(playerTurn==0)
					aUndo--;
				else
					bUndo--;
			}
		}
	}
	
	/**
	 * returns the undo amount for current player
	 * @return undos
	 */
	public int getUndoAmount()
	{
		int undos=0;//will be player undos for this turn
		if(playerTurn==0)
			undos=aUndo;
		else
			undos=bUndo;
		return undos;
	}
	
	/**
	 * prints board
	 */
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
