
package javaRevision;

import java.util.HashSet;
import java.util.Set;

public class CountWordsinString {

	public static void main(String[] args) {
		String[] name = {"My", "name", "is", "arjun", "My","arjun", "bangari", "singh", "arjun","singh","My"};
		int num = name.length;
		Set<String> nameduplicate = new HashSet<String>();
		
		
		for(int i=0;i<num;i++) {
			int k=1;
			if(!(nameduplicate.contains(name[i])))
			{
				nameduplicate.add(name[i]);
				for(int j=i+1;j<num;j++)
				{
					if(name[i].equals(name[j]))
					{
						k++;
					}
				}
				System.out.print(name[i]+ k  );
			}
			 
		}}}
	