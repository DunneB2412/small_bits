package nl.ru.ai.exercise1;


import java.util.ArrayList;
import java.util.Random;

public class test {
	private static <T extends Comparable<T>> void swap(ArrayList<T> database, int i, int j)
	  {
	    assert database!=null : "Array should be initialized";
	    assert i>=0&&i<database.size() : "First index is invalid";
	    assert j>=0&&j<database.size() : "Second index is invalid";
	    T help=database.get(i);
	    database.set(i,database.get(j));
	    database.set(j, help);
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
			 System.out.println("new heap @ "+i+" "+array);
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
			  System.out.println("========================");
			  int pos;
			  if(!indexQueDown.isEmpty())
			  {
				  pushDown(array, indexQueDown.get(0), array.size());
				   pos= indexQueDown.get(0);
				  indexQueUp.add(0, pos);
				  System.out.println("que up"+indexQueUp);
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
			 System.out.println(array);
			 System.out.println("que down"+indexQueDown);
			 System.out.println("que up"+indexQueUp);
			 System.out.println("========================");
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
		  System.out.println("index="+index);
		  int parent=(index-1)/2;
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
			  if (child+branch<limit&& (array.get(index).compareTo(array.get(child+branch))<0))
			  {
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

	public static void main(String[] args) {
		ArrayList<Integer> test = new ArrayList<>();
		Random random= new Random();
		//for (int i=0;i<100;i++)
		//{
		//	test.add(random.nextInt()%50);
		//}
		test.add(2);
		test.add(28);
		test.add(-5);
		test.add(6);
		test.add(3);
		test.add(-5);
		test.add(0);
		test.add(1);
		test.add(-9);
		test.add(7);
		test.add(30);
		test.add(-7);
		test.add(7);
		test.add(12);
		test.add(42);
		test.add(3);
		heapSort(test);
		System.out.println(test);
		
		 //2, 28, -5, 6, 12, 3
		// TODO Auto-generated method stub

	}

}
