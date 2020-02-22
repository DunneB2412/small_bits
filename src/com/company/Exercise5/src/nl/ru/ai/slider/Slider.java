package nl.ru.ai.slider;

import java.util.ArrayList;



public class Slider { /* brian, dimitar. ps the board is randomly generated and can sometimes take a lot of steps.
	                   * anything over 30 steps takes for ever to canculate, also i couldn't fix the bug where 
	                   * puzzle.movedPiece alwas showed a zero so i broke it some more to ake it describe the movement of
	                   * the empty space
	                  */
	public static final int WIDTH=2;
	public static final int HEIGHT=2;
	public static long comparasons=0;

	public static void main(String[] args) {
		Puzzle complete= new Puzzle();
		
		complete.complete();
		ArrayList<Candidate> candidates= new ArrayList<Candidate>();
		Puzzle board=new Puzzle(complete.board,complete.empty,Dir.UNKNOWN);
		board.scramble(10);
		candidates.add(new Candidate(board,-1,Dir.UNKNOWN));
		System.out.println("starting position");
		candidates.get(0).puzzle.show();
		boolean found=false;
		int c=0;
		while(!found&&c<candidates.size())
		{
			if(complete.equalsBoard(candidates.get(c).puzzle.board))
			{
				found=true;
				System.out.println("wooop!!! fin");
				System.out.println(showPath(candidates, c)+" steps");
				System.out.println(comparasons+" comparasons");
				break;
			}
			for(int i=0;i<4;i++)
			{
				if(candidates.get(c).puzzle.can(Dir.values()[i])&&candidates.get(c).puzzle.formingMove!=Dir.values()[i]) 
				{
					add(board,candidates,c,Dir.values()[i]);
					board.coppyFrom(candidates.get(c).puzzle.board);
				}
			}
			c++;
		}
	}

	private static void add(Puzzle board, ArrayList<Candidate> candidates, int c, Dir dir)
	{
		assert(board!=null):"there must be a board";
		assert(dir!=null):"dir should be initalised";
		assert(dir!=Dir.UNKNOWN):"there must be a target direction";
		board.coppyFrom(candidates.get(c).puzzle.board);
		board.swap(candidates.get(c).puzzle.empty, dir.nextPos(candidates.get(c).puzzle.empty));
		Candidate nextCan=new Candidate(board, c,dir);
		if(isNew(candidates,nextCan))
			/*
		if(nextCan.puzzle.uristic<candidates.get(c).puzzle.uristic)
		{
			nextCan.puzzle.show();
			candidates.add(nextCan);
		}*/
		if(isNew(candidates,nextCan))
		{
			//nextCan.puzzle.show();
			candidates.add(nextCan);
		}
	}
	/**
	 * checks the array list and sees if it is a new configuration
	 * @param candidates
	 * @param nextCan
	 * @return
	 */
	private static boolean isNew(ArrayList<Candidate> candidates, Candidate nextCan)
	{
		for (int c=0;c<candidates.size();c++)
			if(candidates.get(c).puzzle.equalsBoard(nextCan.puzzle.board))
				return false;
		return true;
	}
	 /**
	  * shows the path to the target as co-ordinates
	  * @param candidates 
	  * @param c index of candidate to look at
	  */
	 private static int showPath(ArrayList<Candidate> candidates,int c)
	 {
		 assert(candidates!=null):"array list shold be initalised";
		 if(c==-1)
			 return 0;
		 int a=showPath(candidates, candidates.get(c).parentIndex)+1;
		 System.out.print("moved "+candidates.get(c).puzzle.movedPiece+" ");// i honistly have no idea why puzzle.movedPiece doesn't work
		 System.out.println(candidates.get(c).puzzle.formingMove);//(Dir.values()[(candidates.get(c).puzzle.formingMove.ordinal()+3)%4])); ,so i'm going to break it more so it looks like it works
		 candidates.get(c).puzzle.show();
		 return a;
	 }
}
