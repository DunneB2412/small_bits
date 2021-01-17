package com.company.Exercise1;

public class Track implements Comparable<Track>
{
  public String artist;  // name of artist
  public String cd;      // cd title
  public int year;       // year production
  public int track;      // track number
  public String title;   // track title
  public String tags;    // track tags
  public Length time;    // track length
  public String country; // artist country
/**
 * Compare this track with an other
 * @return -1 if this track is smaller, 0 if equal and 1 if this track is larger
 */
  public int compareTo(Track other)
  {
	  /*
	  int n = artist.toLowerCase().compareTo(other.artist.toLowerCase());
	  if (n==0) n = cd.toLowerCase().compareTo(other.cd.toLowerCase());
	  if (n==0)  n = track-other.track;
	  return n;  
	  */
	  return time.compareTo(other.time);
  }
}
