package lq.pre20170513.Thread;
import java.util.Vector;

public class Test {
	public static void main(String[] args) {
		/*
		 * String str = "liu,quan,try,your,best"; StringTokenizer tokener =new
		 * StringTokenizer(str, ","); String result[] = new
		 * String[tokener.countTokens()]; int i = 0;
		 * while(tokener.hasMoreTokens()){ result[i++] = tokener.nextToken(); }
		 * System.out.println(result[4]);
		 */ 
		
		/*ExecutorService pool = Executors.newFixedThreadPool(3);
		for(int i = 0; i < 10; i++){
			pool.execute(new Runnable() { 
				@Override
				public void run() { 
					System.out.println("newFixedThreadPool");
				}
			});
		}
		Executors.newCachedThreadPool().execute(new Runnable() { 
			@Override
			public void run() { 
				System.out.println("newCachedThreadPool");
			}
		});
		Executors.newSingleThreadExecutor().execute(new Runnable() { 
			@Override
			public void run() { 
				System.out.println("newSingleThreadExecutor");				
			}
		});	*/ 
		Vector newVector = new Vector();
		Vector v = new Vector();
		v.addElement("a");
		v.addElement("b");
		v.addElement("a");
		v.addElement("d");
		for(int i = 0; i < v.size(); i++){
			if(!newVector.contains(v.get(i))){ 
				newVector.add(v.get(i));
			}  
		}
		for(int i = 0; i < newVector.size(); i++){
			System.out.println(newVector.get(i));
		}
		
	}
	 
}
