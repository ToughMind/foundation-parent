package lq.pre20170513.algorithm;

 

public class Test2 {
	public static void main(String[] args) {
		int[] a = {4,9,36,15,74,232,58,15,20};
		//升序，小在前
		for(int i = 0; i < a.length; i++){
			for(int j = i+1;j<a.length;j++){
				if(a[i] > a[j]){
					int temp = a[i];
					a[i] = a[j];
					a[j] = temp;
				}
			}
		}
		 
		for(int i = 0 ; i < a.length;i++){
			int temp = a[i]; 
			int j = i - 1;
			for( j = i-1; j >= 0 && a[j] > temp;j--){
				a[j+1] =  a[j];
			}
			a[j+1] = temp;			
		}
			
		int low = 0;
		int high = a.length-1; 
		int temp = a[low];
		
		
		
		for(int i = 0 ; i < a.length;i++){
			System.out.println(a[i]);
		}
	}
	
	
	public void sort(int[] a ,int low,int high){
		if(low >= high)
			return;
		int temp = a[low];
		int left = low +1;
		int right = high;
		while(left <right){
			while(left < right &&left<=high){
				if(a[left] > temp)
					break;
				left++;
			}
		}
	}
}
