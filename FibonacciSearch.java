package org.neeraj.algorithms.searching;

public class FibonacciSearch implements Search{

	@Override
	public int search(Comparable[] array, Comparable data) {
		// TODO Auto-generated method stub
		int n=array.length;
		
		int fibMMm2 = 0;   // (m-2)'th Fibonacci No.
	    int fibMMm1 = 1;   // (m-1)'th Fibonacci No.
	    int fibM = fibMMm2 + fibMMm1;  // m'th Fibonacci
	 
	    /* fibM is going to store the smallest Fibonacci
	       Number greater than or equal to n */
	    while (fibM < n)
	    {
	        fibMMm2 = fibMMm1;
	        fibMMm1 = fibM;
	        fibM  = fibMMm2 + fibMMm1;
	    }
	 
	    // Marks the eliminated range from front
	    int offset = -1;
	 
	    /* while there are elements to be inspected. Note that
	       we compare arr[fibMm2] with x. When fibM becomes 1,
	       fibMm2 becomes 0 */
	    while (fibM > 1)
	    {
	        // Check if fibMm2 is a valid location
	        int i = min(offset+fibMMm2, n-1);
	 
	        /* If x is greater than the value at index fibMm2,
	           cut the subarray array from offset to i */
	        if (array[i].compareTo(data) < 0)
	        {
	            fibM  = fibMMm1;
	            fibMMm1 = fibMMm2;
	            fibMMm2 = fibM - fibMMm1;
	            offset = i;
	        }
	 
	        /* If x is greater than the value at index fibMm2,
	           cut the subarray after i+1  */
	        else if (array[i].compareTo(data) > 0)
	        {
	            fibM  = fibMMm2;
	            fibMMm1 = fibMMm1 - fibMMm2;
	            fibMMm2 = fibM - fibMMm1;
	        }
	 
	        /* element found. return index */
	        else return i;
	    }
	 
	    /* comparing the last element with x */
	    if(fibMMm1>0)if( array[offset+1].compareTo(data)==0)return offset+1;
	 
	    /*element not found. return -1 */
	    return -1;
		
	}
	int min(int x, int y) { return (x<=y)? x : y; }

}
