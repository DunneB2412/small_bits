package nl.ru.ai.exercise1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Exercise1 //brian 1016603 dimitar 1018291
{
	private static long time=0;
  private static int comparasons=0;
  private static final String DATABASE_FILENAME="songs.txt";
  /*
   * Here we go, woop woop
   */
  public static void main(String[] args)
  {
    try
    {
      ArrayList<Track> database= new ArrayList<Track>();	
      /*
       * Read database
       */
      readDatabase(database);
      System.out.printf("%d songs read from datatabase '%s'\n",database.size(),DATABASE_FILENAME);
      /*
       * Ask for sorting method
       */
      Scanner input=new Scanner(System.in);
      SortingMethod method=askForSortingMethod(input);
      /*
       * Sort 
       */
      System.out.printf("Sorting with %s\n",method);
      time=System.currentTimeMillis();
      switch(method) // note that for names there was background processes that effected the time 
      {
        case BUBBLE_SORT:
          bubbleSort(database);
          /*
           *  names:10867986 comparasons take1 4177ms take2 4023ms take3 4025ms 
           *  length:11378455 comparasons take1 335ms take2 466ms take3 313ms
           */
          break;
        case INSERTION_SORT:
          insertionSort(database); 
          /*
           * names:11290265 comparasons take1 2469ms take2 2244ms take3 2281ms
           * length:5585402 comparasons take1 286ms take2 328ms take3 239ms
           */
          break;
        case SELECTION_SORT:
          selectionSort(database); 
          /*
           * names:11383606 comparasons take1 2338ms take2 2394ms take3 2208ms
           * length:11383606 comparasons take1 363ms take2 306ms take3 282ms
           */
          break;
        case HEAP_SORT:
          heapSort(database);
          /*
           * 
           * 172789 comparasons, way less yeet
           */
        break;
        	
      }
      /*
       * bubble uses slightly less comparasons but takes more time, almost twice as long
       * though insertion uses slightly less comparasons than selection the time difference is within margion
       * of error and externam interferience
       * ultimatly this makes bubble somehow seem less eficient, though it has the potental to be more eficient
       */
      System.out.println("Sorted!");
      /*
       * Show result
       */
      dumpDatabase(database);
    }
    catch(FileNotFoundException exception)
    {
      System.out.printf("Error opening database file '%s': file not found\n",DATABASE_FILENAME);
    }
  }
  private static void dumpDatabase(ArrayList<Track> database)
  {
    for(int i=0;i<database.size();i++)
    {
      System.out.printf("%-30s %-36s %4d %s\n",database.get(i).artist,database.get(i).cd,database.get(i).track,database.get(i).time);
    }
    System.out.println(comparasons+" comparasons");
    System.out.println(System.currentTimeMillis()-time+"ms");
    comparasons=0;
  }
  /**
   * Ask the user for a sorting method
   * @param input scanner used for asking
   * @return sorting method
   */
  private static SortingMethod askForSortingMethod(Scanner input)
  {
    /*
     * Show possible sorting methods
     */
    for(SortingMethod method : SortingMethod.values())
      System.out.printf("%d : %s\n",method.ordinal(),method);
    /*
     * Loop until valid choice
     */
    SortingMethod choice=null;
    while(choice==null)
    {
      System.out.println("Enter choice: ");
      int selection=input.nextInt();
      if(selection>=0&&selection<SortingMethod.values().length)
        choice=SortingMethod.values()[selection];
      else
        System.out.println("Invalid choice, try again!");
    }
    return choice;
  }
  /**
   * Reads the cd database from the file 'songs.txt' into the specified track array
   * @param database this is the database that will be filled with the input.
   * @return number of tracks read
   * @throws FileNotFoundException 
   */
  static void readDatabase(ArrayList<Track> database) throws FileNotFoundException
  {
    FileInputStream inputStream=new FileInputStream(DATABASE_FILENAME);
    Scanner scanner=new Scanner(inputStream);
    while(scanner.hasNext())
    {
      Track track=new Track();
      track.artist=scanner.nextLine();
      track.cd=scanner.nextLine();
      track.year=scanner.nextInt();
      scanner.nextLine();
      track.track=scanner.nextInt();
      scanner.nextLine();
      track.title=scanner.nextLine();
      track.tags=scanner.nextLine();
      track.time=new Length(scanner.nextLine());
      track.country=scanner.nextLine();
      database.add(track);
    }
    scanner.close();
  }
  /*************** Auxiliary array routines from lecture ***************/
  /**
   * Checks if the slice of the specified array is sorted
   * @param database
   * @param slice
   * @return true if the slice of the array is in ascending order, false otherwise
   */
  static <T extends Comparable<T>> boolean isSorted(ArrayList<T> database, Slice slice)
  {
    assert database!=null : "Array should be initialized";
    assert slice.isValid() : "Slice should be valid";
    for(int i=slice.from;i<slice.upto-1;i++)
    {
    	comparasons++;
        if(database.get(i).compareTo(database.get(i+1))>0)
          return false;
    }
    return true;
  }
  /**
   * Find position in array slice where to insert new element
   * @param database
   * @param slice
   * @param t element for which the position should be returned
   * @return position where to insert
   */
  static <T extends Comparable<T>> int findInsertPosition(ArrayList<T> database, Slice slice, T t)
  {
    assert database!=null : "Array should be initialized";
    assert slice.isValid() : "Slice should be valid";
    assert isSorted(database,slice);
    for(int i=slice.from;i<slice.upto;i++)
    {
    	comparasons++;
      if(database.get(i).compareTo(t)>=0)
        return i;
    }
    return slice.upto;
  }
  /**
   * Insert an element to a sorted array and keep it sorted
   * @param database
   * @param index end of sorted section
   * @param t element to be added
   * @return new length
   */
  static <T extends Comparable<T>> int insert(ArrayList<T> database,int index, T t)
  {
    assert database!=null : "Array should be initialized";
    assert index>=0 : "index cannot be negative";
    //assert isSorted(database,new Slice(0,index)) : "Array should be sorted";
    int position=findInsertPosition(database,new Slice(0,index),t);
    database.remove(index);
    database.add(position, t);
    return index+1;
  }
  /**
   * Swap two elements in an array
   * @param database
   * @param i
   * @param j
   */
  private static <T extends Comparable<T>> void swap(ArrayList<T> database, int i, int j)
  {
    assert database!=null : "Array should be initialized";
    assert i>=0&&i<database.size() : "First index is invalid";
    assert j>=0&&j<database.size() : "Second index is invalid";
    T help=database.get(i);
    database.set(i,database.get(j));
    database.set(j, help);
  }
  /*************** Array based Sorting routines from lecture ***************/
  /**
   * Sorts an array in situ in ascending order using selection sort
   * @param database
   */
  static <T extends Comparable<T>> void selectionSort(ArrayList<T> database)
  {
    assert database!=null : "array should be initialized";
    for(int i=0;i<database.size();i++)
    {
      int j=indexOfSmallestValue(database,new Slice(i, database.size()));
      swap(database,i,j);
    }
  }
  /**
   * Finds index of smallest value in array slice
   * @param database
   * @param slice
   * @return index of smallest value
   */
  static <T extends Comparable<T>> int indexOfSmallestValue(ArrayList<T> database, Slice slice)
  {
    assert database!=null : "Array should be initialized";
    assert slice.isValid()&&slice.upto<=database.size() : "Slice should be valid";
    assert slice.upto-slice.from>0 : "Slice should be non-empty";
    int index=slice.from;
    for(int i=slice.from+1;i<slice.upto;i++)
    {
    	comparasons++;
      if(database.get(i).compareTo(database.get(index))<0)
        index=i;
    }
    return index;
  }
  /**
   * Sorts an array in situ in ascending order using bubble sort
   * @param database
   */
  static <T extends Comparable<T>> void bubbleSort(ArrayList<T> database)
  {
	int length = database.size();
    assert database!=null : "array should be initialized";
    while(!bubble(database,new Slice(0,length)))
        length--;
  }
  /**
   * Swap all adjacent pairs in the array slice that are not in the right order
   * @param database
   * @param slice
   * @return array slice is sorted
   */
  static <T extends Comparable<T>> boolean bubble(ArrayList<T> database, Slice slice)
  {
    assert database!=null : "Array should be initialized";
    assert slice.isValid()&&slice.upto<=database.size() : "Slice should fit";
    boolean isSorted=true;
    for(int i=slice.from;i<slice.upto-1;i++)
    {
    	comparasons++;
      if(database.get(i).compareTo(database.get(i+1))>0)
      {
        swap(database,i,i+1);
        isSorted=false;
      }
    }
    return isSorted;
  }
  /**
   * Sorts an array in situ in ascending order using insertion sort
   * @param database
   */
  static <T extends Comparable<T>> void insertionSort(ArrayList<T> database)
  {
    assert database!=null : "array should be initialized";
    for(int i=0;i<database.size();i++)
      insert(database,i,database.get(i));
  }

  /**
   * dose the heap sort!!!
   * @param database
   */
  static <T extends Comparable<T>> void heapSort(ArrayList<T> database) 
  {
	  assert(database!=null):"database should be initalised";
	  buildHeap(database);
	  System.out.println("heap built");
	  pickHeap(database);
  }
  /**
   * picks the element at the top of the heap and sends it to the end of the arraylist
   * @param array 
   */
  private static <T extends Comparable<T>> void pickHeap(ArrayList<T> array)
  {
	  assert (array!=null):"array should be initalised";
	  for(int i = array.size()-1; i>=0;i--)
	  {
		  swap(array, i, 0);
		 int pos= 0;
		 int next= pushDown(array, pos, i);
		 while (next!=pos)
		 {
			 pos=next;
			 next= pushDown(array, pos, i);
		 }
	  }
  }
  /**
   * makes a heap out of an array
   * @param array array to be turned into a heap
   */
  private static <T extends Comparable<T>> void buildHeap(ArrayList<T> array)
  {
	  assert (array!=null):"array should be initalised";
	  ArrayList<Integer> indexQueDown= new ArrayList<>();
	  ArrayList<Integer> indexQueUp= new ArrayList<>();
	  indexQueDown.add(0);
	  while (!indexQueDown.isEmpty()||!indexQueUp.isEmpty())
	  {
		  int pos;
		  if(!indexQueDown.isEmpty())
		  {
			  pushDown(array, indexQueDown.get(0), array.size());
			   pos= indexQueDown.get(0);
			  indexQueUp.add(0, pos);
			  indexQueDown.remove(indexQueDown.get(0));
			  if ((2*pos)+1 <array.size()-1) indexQueDown.add(0,(2*pos)+2);
			  if ((2*pos)+2 <array.size()-1) indexQueDown.add(0,(2*pos)+1);
		  }
		  if(!indexQueUp.isEmpty())
		  {
			 pos = pushUp(array, indexQueUp.get(0));
			 if(pos!=indexQueUp.get(0))
			 {
				 indexQueUp.add(pos);
			 }
			 indexQueUp.remove(indexQueUp.get(0));
		  }
	  }
  }
  /**
   * takes an index and moovs it up along the heap if needed
   * @param array array being sorted
   * @param index location element being checked
   * @return location where the element is at the end
   */
  private static <T extends Comparable<T>> int pushUp(ArrayList<T> array, int index)
  {
	  assert (array!=null):"array should be initalised";
	  assert (index>=0&& index<array.size()):" index should be within limits of array";
	  int parent=(index-1)/2;
	  comparasons++;
	  if (parent>=0&& (array.get(index).compareTo(array.get(parent))>0))
	  {
		  swap(array, index, parent);
		  return parent;
	  }
	  return index;
  }
  /**
   * takes an index of an element and checks for a most apropiate location if aplicable to push it down to
   * @param array array being sorted
   * @param index location of element
   * @param limit location at which elements past this point should not be tooched
   * @return the location the element is now
   */
  private static <T extends Comparable<T>> int pushDown(ArrayList<T> array, int index, int limit)
  {
	  assert (array!=null):" array should be initalised";
	  assert (index>=0&& index<array.size()):" index should be within limits of array";
	  assert (limit>=0&& limit<array.size()):" limit should be within limits of array"; 
	  int child=(2*index)+1;
	  ArrayList<T> help= new ArrayList<>(); // cause java let me use a regular variable	in this situation	  
	  for (int branch=0; branch<2; branch++)
	  {
		  comparasons++;
		  if (child+branch<limit&& (array.get(index).compareTo(array.get(child+branch))<0))
		  {
			  comparasons++;
			  if(!help.isEmpty()&&help.get(0).compareTo(array.get(child+branch))<0)
			  {
				 help.set(0, array.get(child+branch));
				 child++;  
			  }
			  else help.add(0, array.get(child+branch));
		  }			  
	  }
	  if (!help.isEmpty())
	  {	  
		  swap(array, index, child);
	  	  return child;
	  }
	  return index;
  }
}


