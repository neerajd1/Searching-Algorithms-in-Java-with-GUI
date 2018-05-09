package org.neeraj.algorithms.searching;

public class BinarySearch implements Search {

	@Override
	public int  search(Comparable[] array, Comparable data) {
		// TODO Auto-generated method stub
		int loc=binarySearch(array,0,array.length-1,data);
		if(loc!=-1) {
			return loc;
		}
		return -1;
	}
	
	static int binarySearch(Comparable[] arr, int l, int r, Comparable x)
    {
        if (r>=l)
        {
            int mid = l + (r - l)/2;
 
            if (arr[mid].compareTo(x) ==0)
               return mid;
            if (arr[mid].compareTo(x) > 0)
               return binarySearch(arr, l, mid-1, x);
            return binarySearch(arr, mid+1, r, x);
        }
        return -1;
    }

}
