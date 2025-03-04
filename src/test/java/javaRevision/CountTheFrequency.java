package javaRevision;

import java.util.ArrayList;

public class CountTheFrequency {

	public static void main(String[] args) {
		
		int a[] = {1,2,2,3,4,4,4,4,8,3,5,6,5,1,9,1};
		
		ArrayList<Integer> b1 = new ArrayList<Integer>();
		
		for(int i=0;i<a.length;i++)
		{
			
			
		if(!b1.contains(a[i]))
		{
			b1.add(a[i]);
			int k = 1;
			for(int j=i+1;j<a.length;j++)
			{
				if(a[i]==a[j])
				{
					k++;
				}	
			}
			System.out.println("The count of " + a[i] + " is " + k); 
		}
		
		}
		
		}
	}
