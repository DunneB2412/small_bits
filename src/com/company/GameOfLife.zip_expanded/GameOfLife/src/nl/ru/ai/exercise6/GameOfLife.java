package nl.ru.ai.exercise6;

import nl.ru.ai.gameoflife.Cell;
import static nl.ru.ai.gameoflife.Universe.*;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileOutputStream;
//import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class GameOfLife
{
	/**
	 *  adjusts a rgb tag for r|g|b within limit of 255
	 * @param tag the tag for r|g|b
	 * @param dir steps it taks
	 * @param Key the amount of stps it taks
	 * @return a new tag for r|g|b
	 */
	private static int rgbAdjust(int tag, int dir, int Key, int[][][] universAge, int X, int Y)
	{
		assert (tag>=0&&tag<=255):"out of bound arguments";
		for(int i=0;i<Key;i++)
		{
			if (tag==0)dir=1;
			if (tag==255)dir=-1;
			tag+=dir;
		} 
		if (tag==255) return 255;
		return tag*17%255;
	}
	/**
	 * switches between posssible age colours
	 * @param universeAge the age of a given cell
	 * @param X x pos of cell
	 * @param Y y pos of cell
	 * @return the corrosponding colour
	 */
	private static Color color(int[][][] universeAge, int X, int Y)
	{
		int red=rgbAdjust(universeAge[X][Y][2], universeAge[X][Y][1], 250,universeAge,X,Y);
		int green=rgbAdjust(universeAge[X][Y][2], universeAge[X][Y][1], 180, universeAge,X,Y);
		int blue=255-rgbAdjust(universeAge[X][Y][2], universeAge[X][Y][1], 0, universeAge,X,Y);
		assert true;
		int rgbInitalise=200-universeAge[X][Y][0]*5;
		if (rgbInitalise>0) return new Color(rgbInitalise,rgbInitalise,255);
		return new Color(red,green,blue);
		
	}
	/**
	 * saves the input map as a configuration for later use
	 * @param universe the input map
	 * @param destination the output file
	 */ 
	private static void saveMap(Cell[][] universe, String destination)
	{
		assert (universe!=null&& destination!=null):"argument missing";
		try
		{
			OutputStreamWriter writer= new OutputStreamWriter(new FileOutputStream(destination));
			 for(int y=0;y<40;y++)
			  {
				  for(int x=0; x<60;x++)
				  {
					  if (universe[x][y]==Cell.LIVE)
					  {
						  if(y==0||y==39||x==0||x==59)
							{
							  writer.write('.');
							}
						  writer.write('*');
					  }
					  else
					  {
						  writer.write('.');
					  }
				  }
				  writer.write(System.getProperty( "line.separator" ));
			  }
			 writer.close();
		}
		catch (IOException e)
		{
			throw new IllegalArgumentException("something went wrong when saving the map");
		}
	}
	/**
	 * counts the number of cells in the 3x3 block around the cell and shows if it should be alive
	 * @param universe the map which is being checked
	 * @param X the x corordinate which the cell being checked is at
	 * @param Y the y corodinte "..."
	 * @return the cell state
	 */
	private static Cell checkSellHelth(Cell[][] universe,int[][][] universeAge, int X, int Y)
	{
		assert (universe!=null && X>=0&&X<60&&Y>=0&&Y<40):"cant find the cell in the universe";
		assert (universeAge!=null):"cant find the cell age";		  
		int cellCount=0;
		for (int y=(Y-1);y<=(Y+1);y++)
		{
			for(int x=(X-1);x<=(X+1);x++)
			{
				if (x>=0&&x<=59&&y>=0&&y<=39 && !(x==X&&y==Y))
				{	
					if (universe[x][y]==Cell.LIVE)
					{
						cellCount++;
					
					}
				}
			}
		}
		if (universe[X][Y]==Cell.LIVE && cellCount>=2&&cellCount<=3) 
		{
			if (universeAge[X][Y][2]==0||universeAge[X][Y][2]==255) universeAge[X][Y][1]=universeAge[X][Y][1]*-1;
			universeAge[X][Y][2]+=universeAge[X][Y][1];
			universeAge[X][Y][0]++;
			return Cell.LIVE;
		}	
		else if (universe[X][Y]==Cell.DEAD && cellCount==3)
		{
			universeAge[X][Y][2]++;
			universeAge[X][Y][0]++;
			return Cell.LIVE;
		}
		else
		{
			universeAge[X][Y][0]=0;
			universeAge[X][Y][1]=1;
			universeAge[X][Y][2]=0;
			return Cell.DEAD;
		}
	}
	/**
	 * takes in a file name and makes sure that it has a file type
	 * @param name; the name of the file with/without .(type) at the end
	 * @return file name with a specified type
	 */
	private static String fileType(String name)
	{
		assert (name!=null):"there is no name";
		String fileType=".txt";
		for (int i=1;i<=4;i++)
		{
			if (name.charAt(name.length()-(i))!=fileType.charAt(fileType.length()-(i)))
			{
				System.out.println(name+fileType);
				return name+fileType;
			}
		}
		return name;	
	}

  public static void main(String[] args)
  {
	  Scanner scanner=new Scanner(System.in);
	  System.out.println("give a file name");
	  String file=fileType(scanner.next());
	  System.out.println("how many itirations, negative numbers are interprated as un ending");
	  int iterations=scanner.nextInt();
	  int universeAge[][][]= new int[60][40][3];
	  Cell universe[][]=readUniverseFile(file, universeAge); 	  
	  showUniverse(universe, universeAge);
	  int n=0;
	  while (n!=iterations)
	  { 
		  sleep(100);
		  universe=nextGeneration(universe, universeAge);
		  showUniverse(universe, universeAge);
		  n++;
	  }
	  System.out.println("do you want to save this as a configuration? true for yes");
	  boolean save=scanner.nextBoolean();
	  if (save)
	  {
		  System.out.println("name a file to save it to");
		  saveMap(universe, fileType(scanner.next()));
	  }
	  scanner.close();
  }
  
  /**
   * reads a file and constructs a map based on the file
   * @param fileName; the name of the target file
   * @return an array of live or dead files
   */
  static Cell[][] readUniverseFile(String fileName, int[][][] universeAge)
  {	 
	assert (fileName!=null):"there is no configureation";
	try 
	{	
		BufferedReader reader = new BufferedReader(new FileReader(fileName));
	 
		String line;
		int lineNo=0;
		Cell map[][]= new Cell[60][40];
		while((line=reader.readLine())!=null)
		{
			if (line.length()!=60)
			{
				reader.close();
				throw new IllegalArgumentException("there is a line that is not 60");				
			}
			for (int x=0;x<line.length();x++ )
			{
				if(line.charAt(x)=='*')
				{
					universeAge[x][lineNo][2]=1;
					universeAge[x][lineNo][1]=1;
					if(lineNo==0||lineNo==39||x==0||x==59)
					{
						reader.close();
						throw new IllegalArgumentException("cells cannot exist at the border");
					}
					universeAge[x][lineNo][0]=1;
					map[x][lineNo]=Cell.LIVE;
				}
				else if(line.charAt(x)=='.')
				{
					universeAge[x][lineNo][0]=0;
					map[x][lineNo]=Cell.DEAD;
				}
				else
				{
					reader.close();
					throw new IllegalArgumentException("invalid character exists in the file");
				}
			}
			lineNo++;
		}
		if (lineNo!=40)
		{
			reader.close();
			throw new IllegalArgumentException("hight of configuration must be 40");				
		}
		reader.close();
		return map;
	}
	catch (IOException e) 
	{
		throw new IllegalArgumentException("something went wrong when reading the map");
	}
  }
/**
 * updates the display of the game
 * @param universe the current state of the game
 */
  private static void showUniverse(Cell[][] universe, int[][][] universeAge)
  {
	  assert(universe!=null):"there is no map to display";
	  for(int y=0;y<40;y++)
	  {
		  for(int x=0; x<60;x++)
		  {
			  updateScreen(y, x, universe[x][y],color(universeAge,x,y));
		  }
	  }    
  }
/**
 * calculats the next generation for a given map state
 * @param universe the map state from which the next generation is found
 * @return the next generation's map state
 */
  private static Cell[][] nextGeneration(Cell[][] universe, int[][][] universeAge)
  {
	  assert (universe!=null):"cant compute a next generation of nothing";
	  Cell nextGen[][]=new Cell[60][40];
	  for(int y=0;y<40;y++)
	  {
		  for(int x=0; x<60;x++)
		  {			 
			  nextGen[x][y]=checkSellHelth(universe, universeAge, x, y);
		  }
	  }
	  return nextGen;
  }

} 
