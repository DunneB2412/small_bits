package com.company.Exercise5.slider;

public enum Dir {
	UP, DOWN, LEFT, RIGHT, UNKNOWN;
	public String toString()
	{
		switch(this)
		{
		case UP:
			return("up");
		case DOWN:
			return("down");
		case LEFT:
			return("left");
		case RIGHT:
			return("right");
		default:
			return null;
		}
	}
	Pos nextPos(Pos empty)
	{
		switch(this)
		{
		case UP:
			return(new Pos(empty.col,empty.row-1));
		case DOWN:
			return(new Pos(empty.col,empty.row+1));
		case LEFT:
			return(new Pos(empty.col-1,empty.row));
		case RIGHT:
			return(new Pos(empty.col+1,empty.row));
		default:
			return null;
		}
	}
}

