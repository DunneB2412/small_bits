package com.company.Exercise4;

public class Cord 
{
	public int x;
	public int y;
	public Cord(int ox, int oy)
	{
		x=ox;
		y=oy;
	}
	public String toString()
	{
		return String.format("%d:%d",x,y);
	}
}
