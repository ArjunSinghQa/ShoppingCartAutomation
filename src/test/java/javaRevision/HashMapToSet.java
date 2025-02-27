package javaRevision;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class HashMapToSet {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		HashMap<Integer,String> data = new HashMap<Integer,String>();
		data.put(1, "Arjun");
		data.put(2, "Singh");
		data.put(3, "Hello kese ho aap");
		
		System.out.println(data);
		
		//changing map to set
		
		Set itsaset = data.entrySet();
		System.out.println(itsaset);
		
	    Iterator i = itsaset.iterator();
		
		while(i.hasNext()) {
			
			Map.Entry map =(Map.Entry)i.next();
			
			// above code is used because we want to print the keys and values as separate entity, other wise it will throw error on directly printing it in this case
			System.out.println(map.getKey());
			System.out.println(map.getValue());
				
		}
		
	}

}
