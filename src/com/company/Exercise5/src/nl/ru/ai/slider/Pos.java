package nl.ru.ai.slider;

public class Pos implements Comparable<Pos>
{
	public int col;
	public int row;
	public Pos(int col, int row)
	{
		this.col=col;
		this.row=row;
	}
	@Override
	public int compareTo(Pos other)
	{
		int xDif= col-other.col;
		if (xDif<0)
			xDif=xDif*-1;
		int yDif= row-other.row;
		if (yDif<0)
			yDif=yDif*-1;
		
		return xDif+yDif;
	}
	@Override
	public String toString()
	{
		return ("("+col+","+row+")");
	}
	

}
