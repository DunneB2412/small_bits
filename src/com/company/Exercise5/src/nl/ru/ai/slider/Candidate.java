package nl.ru.ai.slider;


public class Candidate {
	public Puzzle puzzle;
	public int parentIndex;
	public Candidate(Puzzle puzzle, int parentIndex,Dir dir)
	{
		this.puzzle=new Puzzle(puzzle.board,puzzle.empty,dir);
		this.parentIndex=parentIndex;
	}
}
