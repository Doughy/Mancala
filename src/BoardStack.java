import java.util.Stack;

public class BoardStack extends Stack<int[][]> 
{
	private Stack boards;

	public BoardStack()
	{
		boards=new Stack();
	}
	
	public boolean empty()
	{
		return boards.empty();
	}
	
	public int[][] peek()
	{
		return (int[][])boards.peek();
	}
	
	public int[][] pop()
	{
		return (int[][])boards.pop();
	}
	
	public  int[][] push(int[][] newBoard)
	{
		if(boards.size()==6)
			removeSixth();
		return (int[][])boards.push(newBoard);
	}
	
	public int search(int[][] board)
	{
		return boards.search(board);
	}
	
	public void removeSixth()
	{
		boards.remove(5);
	}
	
	
}