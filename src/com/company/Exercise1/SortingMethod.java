package com.company.Exercise1;

public enum SortingMethod {
  BUBBLE_SORT, INSERTION_SORT, SELECTION_SORT, HEAP_SORT;
  public String toString()
  {
    switch(this)
    {
      case BUBBLE_SORT:
        return "Bubble sort";
      case INSERTION_SORT:
        return "Insertion sort";
      case SELECTION_SORT:
        return "Selection sort";
      case HEAP_SORT:
    	return "Heap sort";  
      default:
        return null;
    }
  }
}
