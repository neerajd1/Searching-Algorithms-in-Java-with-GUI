package org.neeraj.algorithms.searching;

public class JumpSearch implements Search{

	@Override
	public int search(Comparable[] array, Comparable data) {
		// TODO Auto-generated method stub
		
		int n = array.length;
        int step = (int)Math.floor(Math.sqrt(n));
        int prev = 0;
        while (array[Math.min(step, n)-1].compareTo(data) < 0)
        {
            prev = step;
            step += (int)Math.floor(Math.sqrt(n));
            if (prev >= n)
                return -1;
        }
 
        while (array[prev].compareTo(data) < 0)
        {
            prev++;
 
            if (prev == Math.min(step, n))
                return -1;
        }
 
        if (array[prev].compareTo(data) == 0)
            return prev;
 
        return -1;
	}

}
