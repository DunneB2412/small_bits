package nl.ru.ai.exercise0;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Exercise0 //brian 1016603, dimitar 1018291
{
	private static final int MAX_NR_OF_TRACKS=5000;
  /**
   * Program main entry point
   * @param args arguments to the program (ignored)
 * @throws FileNotFoundException 
   */
  public static void main(String [] args) throws FileNotFoundException 
  {
	  Scanner scanner= new Scanner(System.in);
	  Length length1=fromString("12:34");
	  Length length2=fromString("23:45");
	  Length sum=add(length1,length2);
	  System.out.println("test. "+toString(sum));
	  Track [] database=new Track[MAX_NR_OF_TRACKS]; 
	  String command="";
	  int nr=readDatabase(database,fileTXT(input(scanner,"give an arcive file to read", 1)[0]));
	  do
	  {
		  String[] input=input(scanner,"what do you want to do?" , 2);
		  command=input[0];
		  switch(command)
		  {
		  case("track"):
			  if (input[1]==null)input[1]=input(scanner,"give a track title to serch for", 1)[0];
			  serch(database,nr,input[1],"title","artist,cd,year,track,title,time,tags,country");
		  	  break;
		  case("artist"):
			  if (input[1]==null)input[1]=input(scanner,"give a artist name to serch for", 1)[0];
		  	  serch(database,nr,input[1],"artist","artist,cd,year,track,title,time,tags,country");
		  	  break;
		  case("cds"):
			  if (input[1]==null)input[1]=input(scanner,"give a artist name", 1)[0];
			  artistsCds(database, nr, input[1]);
			  break;
		  case("#cds"):
			  Albom[] cds=new Albom[nr];
			  System.out.println(collectCds(database, nr, cds)+" cds");
		  	  break;
		  case("time"):
			  totalTime(database, nr);
		  	  break;
		  case("cd?"):
			  if (input[1]==null)input[1]=input(scanner,"give a track name and find what cd it's from", 1)[0];
			  serch(database, nr, input[1],"title","cd");
			  break;
		  case("tag"):
			  if (input[1]==null)input[1]=input(scanner,"give a tag to search", 1)[0];
			  serch(database, nr, input[1], "tags","artist,cd,year,track,title,time,tags,country");
			  break;
		  case("stop"):
			  stop();
		  break;
		  default:
			  System.out.println("dont know how to "+command);
		  }	  
	  }while (!command.equals("stop"));	  
  }
  /**
   * searches for cds which were from a common artist
   * @param database array of tracks
   * @param nr length of database
   * @param artist user serch input
   */
  private static void artistsCds(Track[] database, int nr, String artist)
  {
	  assert(database!=null):"database should be initalised";
	  assert(artist!=null||artist==""):"should have a target artist";
	  Albom[] cds=new Albom[nr];
	  int cdsNo=0;
	  int trackNo=0;
	  nr=collectCds(database, nr, cds);
	  for (int i=0;i<nr;i++) if(cds[i].tracks[0].artist.indexOf(artist)>=0)
	  {
		  cdsNo++;
		  System.out.println(cds[i].name+":"+cds[i].tracks[0].year+": by"+cds[i].tracks[0].artist);
		  int t=0;
		  while (cds[i].tracks[t]!=null)
		  {
			  printTrackInfo(cds[i].tracks[t], "title,track",false);
			  t++;
			  trackNo++;
		  }
		  System.out.println("==================");
	  }
	  System.out.println(cdsNo+" cds found, "+trackNo+" tracks found.");
  }
  /**
   * uses user serch input to find any matches and displays them
   * @param database array of tracks
   * @param nr length of database
   * @param target user serch input
   * @param field the field to which the serch should apply
   * @param fieldsOut the fields that will be displayed on output
   */
  private static void serch(Track[] database, int nr, String target, String field, String fieldsOut)
  {
	  assert(target!=null):"there must me a target";
	  assert(database!=null):"database bust be initalised";
	  int index=0;
	  for (int i=0; i<nr; i++)
	  {
		  String fields=scanTrackInfo(database[i], target);
		  if(fields.indexOf(field)>=0)
		  {
			  printTrackInfo(database[i],fieldsOut, true);
			  index++;
		  }
	 }
	  System.out.println(index+" results.");
	  System.out.println("==================");	  
  }
/**
   * finds all the cds that an artist published in the database and saves them to an array of alboms
   * @param database
   * @param nr lenght of database array
   * @param discryption how the results should be described
   * @param artist name of artist to check for (if any)
   * @param artistsCD if checking for artist
   */
  private static int collectCds(Track[] database, int nr, Albom[] cds) 
  {
	  assert (database!=null):"database should be initalised";
	  int index=0;
	  for (int i=0; i<nr; i++)
	  {
		  int pos=albomNo(cds,database[i],index);
		  if (pos==index)
		  {
			  cds[pos]=new Albom(database[i].cd);
			  index++;
		  }
		  addTrack(cds[pos].tracks, database[i]);
	  } 
	  return index;
  }
  /**
   * atts element to array of tracks
   * @param tracks array of tracks
   * @param track track to be added
   */
  private static void addTrack(Track[] tracks, Track track) 
  {
	  assert(tracks!=null):"tracks should be initalised";
	  assert(track!=null):"track should be initalised";
	  int i=0;
	  while(tracks[i]!=null)i++;
	  tracks[i]=track;
  }
/**
   *  serches throught albom array and checks if the albom has the same name and artist
   * @param cds albom array
   * @param track track being checked
   * @param index current knwon length of cds array
   * @return position index of cd the track exists in
   */
  private static int albomNo(Albom[] cds, Track track, int index) 
  {
	  assert(cds!=null&&track!=null):"arrays  should be initalised";
	  int i;
	  for (i=0;i<index;i++)
	  {
		  if (track.cd.equals(cds[i].name)&&track.artist.equals(cds[i].tracks[0].artist))
			  return i;
	  }
	  return i;
  }
/**
   * prints the total length of the songs colectivly in the database
   * @param database
   * @param nr the no of items in the database
   */
  private static void totalTime(Track[] database, int nr) 
  {
	  assert (database!=null):"database should be initalised";
	  Length time= new Length(0,0);
	  for (int i=0;i<nr;i++) 
		  time=add(time, database[i].time);
	  System.out.println("the songs are "+toString(time)+" long colectivly");
  }
/**
   * if you are sure you want it all to end
   */
  private static void stop() 
  {
	assert true;  
	System.out.println("good bie");
	System.exit(1);//big oof
  }
/**
   * Calculate the sum of the specified length arguments
   * @param a one time to be added
   * @param b the other time to be added to a
   * @return sum
   */
  static Length add(Length a, Length b)
  {
    return timeFormat(new Length(a.minutes+b.minutes, a.seconds+b.seconds));
  }
  /**
   * Converts the given string in the format m..m:ss to a Length object
   * @param string a string in the form minute:seccond
   * @return corresponding length as a Length
   */
  static Length fromString(String string)
  {
	  assert true;
	  Matcher matcher = Pattern.compile("(\\d+):(\\d+)").matcher(string);
	  if (!matcher.matches())
	  {
		  System.out.println("invalid format for time minute:seccond, setting to 0");
		  return new Length(0,0);
	  }	  
    return timeFormat(new Length(Integer.parseInt(matcher.group(1)),Integer.parseInt(matcher.group(2))));
  }
  /**
   * Converts a given length object into a string in the format m..m:ss
   * @param length
   * @return string representation
   */
  static String toString(Length length)
  {
	  assert true;
    return (length.minutes+":"+length.seconds);
  }
  /**
   * Reads the cd database from the file 'Nummers.txt' into the specified track array
   * @param database
   * @return number of tracks read
 * @throws FileNotFoundException 
   */
  static int readDatabase(Track[] database, String file) throws FileNotFoundException
  {
	  assert(database.length==MAX_NR_OF_TRACKS):("database should be "+MAX_NR_OF_TRACKS+" in lenght");
	  Scanner scanner= new Scanner(new FileInputStream(file));
	  int nr=0;
	  while(scanner.hasNextLine()&& nr<=MAX_NR_OF_TRACKS)
	  {
		  database[nr]=new Track();
		  database[nr].artist=scanner.nextLine();
		  database[nr].cd=scanner.nextLine();
		  database[nr].year=Integer.parseInt(scanner.nextLine());
		  database[nr].track=Integer.parseInt(scanner.nextLine());
		  database[nr].title=scanner.nextLine();
		  database[nr].tags=scanner.nextLine();
		  database[nr].time=fromString(scanner.nextLine());
		  database[nr].country=scanner.nextLine();
		  nr++;
	  }
	  scanner.close();
    return nr;
  }
  /**
   * formats a time such that the secconds are reduced to minutes and secconds
   * @param time time that may need to be formated
   * @return alocats the adjusted time to the output (probibly redundant and sortive ugly plz tel me in feedback)
   */
  private static Length timeFormat(Length time)
  {
	  assert true;// don't think it should brake
	  if (time.seconds>59)
	  {
		  time.minutes+=time.seconds/60;
		  time.seconds=time.seconds%60;
	  }
	  return time;
  }
  /**
   * gets a user input to a message
   * @param scanner scanner
   * @param message the message displayed to the user
   * @param noOfinput the number of expected inputs
   * @return the input
   */
  private static String[] input(Scanner scanner, String message, int noOfinput)
  {
	  assert(scanner!=null):"scanner must be initiated";
	  assert(message.length()>=1):"its nice to let people know what you want";
	  assert(noOfinput>0):"there should be atleast one input";
	  System.out.print(message);
	  if (noOfinput>1) System.out.print(" different components can be seperated with ';' or ':', eg <command;arg>");
	  System.out.println("");
	  System.out.print(">>>");
	  String[] input= new String[noOfinput];	
	  String line=scanner.nextLine();
	  int i=0;
	  input[0]="";
	  for (int c=0;c<line.length();c++)
	  {
		  if(line.charAt(c)==';'||line.charAt(c)==':')
		  {
			  i++;
			  input[i]="";
			  if(i>input.length-1) throw new IllegalArgumentException("To many inputs, expected:"+noOfinput+" or less.");
		  }
		  else input[i]+=line.charAt(c);
	  }
	  return input;
  }
  /**
   * makes sure the file name has .txt at the end in case it was left out
   * @param name input name
   * @return file with a shiny .txt at the end
   */
  private static String fileTXT(String name)
  {
	  assert (name.length()>=1):"file should have a name";
	  String fileType=".txt";
	  if (name.length()<4) return name+fileType;
	  for (int i=1;i<=4;i++)
	  {
		  if (name.charAt(name.length()-(i))!=fileType.charAt(fileType.length()-(i)))
		  {
			  System.out.println("fixed:"+name+fileType);
			  return name+fileType;
		  }
	  }
	  return name;
  }
  /**
   * prints to the console imformation about a track
   * @param track
   * @param fields which fields should be displayed
   */
  private static void printTrackInfo(Track track, String fields, Boolean printLines)
  {
	  assert(track!=null):"track should be initalised";
	  assert(fields!=null&&fields.length()>0):"cant compare nothing";
	  if(printLines) System.out.println("==================");
	  if (fields.indexOf("artist")>=0)System.out.println("artist:"+track.artist);
	  if (fields.indexOf("cd")>=0)System.out.println("cd name:"+track.cd);
	  if (fields.indexOf("year")>=0)System.out.println("year of releas:"+track.year);
	  if (fields.indexOf("track")>=0)System.out.println("track number:"+track.track);
	  if (fields.indexOf("title")>=0)System.out.println("track title:"+track.title);
	  if (fields.indexOf("time")>=0)System.out.println("lenght:"+toString(track.time));
	  if (fields.indexOf("tags")>=0)System.out.println("tags:"+track.tags);
	  if (fields.indexOf("country")>=0)System.out.println("cuntry of releas:"+track.country);
	  if(printLines) System.out.println("==================");
  }
  /**
   * checks if target exists in as part of a track's info
   * @param track track being checked
   * @param target user serch input
   * @return which piece of imformation the serch triggered
   */
  private static String scanTrackInfo(Track track, String target)
  {
	  assert(track!=null):"track should be initalised";
	  assert(target!=null):"target should be initalised";
	  String fieldsMatched= "";
	  if (track.artist.indexOf(target)>=0) fieldsMatched+="artist ";
	  if (track.cd.indexOf(target)>=0) fieldsMatched+="cd ";
	  if ((""+track.year).indexOf(target)>=0) fieldsMatched+="year ";
	  if (track.title.indexOf(target)>=0) fieldsMatched+="title ";
	  if (track.tags.indexOf(target)>=0) fieldsMatched+="tags ";
	  if (track.country.indexOf(target)>=0) fieldsMatched+="country ";
	  return fieldsMatched;
  }
 
}

