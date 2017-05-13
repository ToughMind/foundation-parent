package lq.pre20170513.base;

/**
 * 一个整数，大于0，不用循环和本地变量，按照 n， 2n， 4n， 8n 的顺序递增，当值大于5000
 * 时，把值按照指定顺序输出来。
 * 例： n=1237
 * 则输出为：
 * 1237，
 * 2474，
 * 4948，
 * 9896，
 * 9896，
 * 4948，
 * 2474，
 * 1237，
 */
public class Test1 {
	
	public static void main(String[] args) {
		doubleNum(1237);
	}
	
	public static void doubleNum(int n){
		System.out.println(n);
		if(n <= 5000)
			doubleNum(2*n);
		System.out.println(n); 
	}
}
