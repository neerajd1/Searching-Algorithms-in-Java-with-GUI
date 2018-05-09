package org.neeraj.algorithms.searching;

public class LinearSearch implements Search{

	

	@Override
	public int search(Comparable[] array, Comparable data) {
		// TODO Auto-generated method stub
		for (int i = 0; i < array.length; i++)
        {
         
            if (array[i].compareTo(data)==0)
                return i;
        }
		return -1;
	}

}
