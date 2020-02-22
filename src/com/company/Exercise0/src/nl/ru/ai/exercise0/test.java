package nl.ru.ai.exercise0;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class test {


	public static void main(String[] args) {
		Matcher matcher = Pattern.compile("(\\d+):(\\d+)").matcher("512:2488");
		if (!matcher.matches())
			System.out.println("wrong time format");
		else
		{
			System.out.println(Integer.parseInt(matcher.group(1)));
			System.out.println(Integer.parseInt(matcher.group(2)));
		}
		System.out.println(123%60);
		System.out.println(123/60);
		// TODO Auto-generated method stub

	}

}
/*
switch(command)
		  {
		  case("open"):
			  if (input[1]==null)input[1]=input(scanner,"give an arcive file to read", 1)[0];  
		  	  nr=readDatabase(database,fileTXT(input[1])); 
		  	  System.out.println(nr+" tracks read");
		  	  break;
		  case("track"):
			  if (nr==0)
				  System.out.println("please read a database");
			  else
			  {
				  if (input[1]==null)input[1]=input(scanner,"give a track title to serch for", 1)[0];
				  trackByName(database,nr,input[1]);
			  }
		  	  break;
		  case("artist"):
		  case("cds"):
			  if (input[1]==null)input[1]=input(scanner,"give a artist name", 1)[0];
			  cdsNartist(database, nr, input[1]);
			  break;
		  case("#cds"):
			  differentCD(database, nr);
		  	  break;
		  case("time"):
			  totalTime(database, nr);
		  	  break;
		  case("cd?"):
		  case("tag"):
		  case("stop"):
			  stop();
		  break;
		  default:
			  System.out.println("dont know how to "+command);
		  }




*/