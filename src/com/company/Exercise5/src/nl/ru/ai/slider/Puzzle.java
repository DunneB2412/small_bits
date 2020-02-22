package nl.ru.ai.slider;

import java.util.Random;

public class Puzzle {
	public int[][] board;
	public Pos empty;
	public Dir formingMove;
	public int movedPiece;
	//public int uristic;
	public int steps;
	public Puzzle()
	{
		board=new int[Slider.WIDTH][Slider.HEIGHT];
		empty=new Pos(Slider.WIDTH,Slider.HEIGHT);
	}
	public Puzzle(int[][] board, Pos empty, Dir dir)
	{
		this.board=new int[Slider.WIDTH][Slider.HEIGHT];
		coppyFrom(board);
		this.empty=new Pos(empty.col,empty.row);
		formingMove=Dir.values()[dir.ordinal()];
		//uristic=calculateU();
	}
	
	/**
	 * shows the board i a somewhat estetically pleasing manor
	 */
	void show()
	{
		assert true;
		bar(false);
		for(int i=0;i<Slider.HEIGHT;i++)
		{
			System.out.print("|");
			for(int e=0;e<Slider.WIDTH;e++)
			{
				if(board[e][i]==0)
					System.out.print("   |");
				else
					System.out.printf("%2d |",board[e][i]);
			}
			bar(true);
		}
		System.out.println("");
	}
	/**
	 * coppies the content of one board to another
	 * @param other
	 */
	void coppyFrom(int[][] other)
	{
		assert(other!=null):"other should be initalised";
		assert(other.length==board.length&&other[0].length==board[0].length):"other should be the same size as board";
		for(int y=0;y<Slider.HEIGHT;y++)
		{
			for(int x=0;x<Slider.WIDTH;x++)
			{
				board[x][y]=other[x][y];
			}
		}
	}
	/**
	 * estetic function to help print the board
	 */
	private static void bar(boolean nl)
	{
		assert true;
		if (nl)System.out.println("");
		System.out.print("=");
		for(int i=0;i<Slider.WIDTH;i++)
		 System.out.print("====");
		System.out.println("");
	}
	/**
	 * checks if the board of the puzzle is equal to another board
	 * @param other the other board
	 * @return boolean
	 */
	boolean equalsBoard(int[][] other)
	{
		for(int y=0;y<Slider.HEIGHT;y++)
		{
			for(int x=0;x<Slider.WIDTH;x++)
			{
				Slider.comparasons++;
				if(board[x][y]!=other[x][y])
					return false;
			}
		}
		return true;
	}
	/**
	 * rebuilds the board with a complete configuration.
	 */
	void complete()
	{
		assert true;
		int nr=1;
		for(int y=0;y<Slider.HEIGHT;y++)
		{
			for(int x=0;x<Slider.WIDTH;x++)
			{
				board[x][y]=nr;
				nr++;
			}
		}
		board[Slider.WIDTH-1][Slider.HEIGHT-1]=0;
		empty.col=Slider.WIDTH-1;
		empty.row=Slider.HEIGHT-1;
		//uristic=0;
	}
	/**
	 * scrambles the board
	 * @param scrambleFactor
	 */
	void scramble(int scrambleFactor)
	{
		assert true;
		Random random=new Random();
		for(int i=0;i<(Slider.HEIGHT*Slider.WIDTH*scrambleFactor);i++)
		{
			int r=random.nextInt(4);
			Dir dir=Dir.values()[r];
			if(can(dir))
				swap(empty,dir.nextPos(empty));
		}
		//uristic=calculateU();
	}
	/**
	 * swaps two positions
	 * @param a 
	 * @param b
	 */
	void swap(Pos a, Pos b)
	{
		assert(a!=null&&b!=null):" positions should be initalised";
		movedPiece=board[b.col][b.row];
		board[b.col][b.row]=board[a.col][a.row];
		board[a.col][a.row]=movedPiece;
		empty.col=b.col;
		empty.row=b.row;
	}
	/**
	 * checks if a moox in a direction is possible
	 * @param dir the direction being checked
	 * @return if it can
	 */
	boolean can(Dir dir)
	{
		assert(dir!=null):"direction must be specified";
		switch(dir)
		{
		case UP:
			if(empty.row-1>=0) return true;
		break;
		case DOWN:
			if(empty.row+1<Slider.HEIGHT) return true;
		break;
		case LEFT:
			if(empty.col-1>=0) return true;
		break;
		case RIGHT:
			if(empty.col+1<Slider.WIDTH) return true;
		break;
		default:
			return false;
		}
		return false;
	}
	int calculateU()
	{
		show();
		int dif=0;
		for (int i=1;i <Slider.WIDTH*Slider.HEIGHT;i++)
		{
			System.out.print(i+":");
			Pos pos=find(i);
			Pos target=new Pos((i-1)%Slider.WIDTH, (i-1)/Slider.HEIGHT);
			System.out.print(pos+"v"+target);
			int e=pos.compareTo(target);
			System.out.println(" ="+e);
			dif+=e;
		}
		System.out.println(dif);
		return dif;
	}
	Pos find(int target)
	{
		assert (target>=0&&target<Slider.WIDTH*Slider.HEIGHT);
		for(int y=0;y<Slider.HEIGHT;y++)
		{
			for(int x=0;x<Slider.WIDTH;x++)
			{
				Slider.comparasons++;
				if(board[x][y]==target)
					return new Pos(x, y);
			}
		}
		return null;
	}
}
