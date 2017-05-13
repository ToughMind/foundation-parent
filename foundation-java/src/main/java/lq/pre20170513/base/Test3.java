package lq.pre20170513.base;

/**
 * 第1个人10，第2个比第1个人大2岁，依次递推，请用递归方式计算出第8个人多大？
 * 十进制转二进制
 *
 */
public class Test3 {
	public static void main(String[] args) {
		System.out.println(f1(8));
		StringBuffer result = new StringBuffer(); 
		f2(8, result);
		System.out.println(result);
	}
	
	public static int f1(int n){
		if(n==1)
			return 10;
		return f1(n-1)+2;
	}
	
	public static void f2(int n,StringBuffer result){
		if(n/2 != 0)
			f2(n/2, result);
		result.append(n%2); 
	}
}
