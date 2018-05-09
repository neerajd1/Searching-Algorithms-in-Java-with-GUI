package org.neeraj.algorithms.searching;

public class ExponentialSearch implements Search {

	@Override
	public int search(Comparable[] array, Comparable data) {
		// TODO Auto-generated method stub
		int n=array.length;
		 // If x is present at firt location itself
        if (array[0].compareTo(data) == 0)
            return 0;
     
        // Find range for binary search by
        // repeated doubling
        int i = 1;
        while (i < n &&( array[i].compareTo(data) <= 0))
            i = i*2;
     
        // Call binary search for the found range.
        return BinarySearch.binarySearch(array, i/2,Math.min(i, n), data);
		
		
	}

}
