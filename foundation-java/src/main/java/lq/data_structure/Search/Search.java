package lq.data_structure.Search;

/**
 * @description 顺序查找,二分查找
 * 
 * @author liuquan
 * @date  2016年1月6日
 */
public class Search {
	/**
	 * @description 不带监视哨的顺序查找算法 
	 * @param a
	 * @param key
	 * @return
	 * @author liuquan
	 * @date  2016年1月6日
	 */
	public static int seqSearch(int[] a, int key){
		int i = 0, n = a.length;
		while(i < n && a[i] != key){
			i++;
		}
		if(i < n){ //查找成功，返回下标
			return i;
		}
		else{
			return -1;
		}
	}
	
	/**
	 * @description 从顺序表a[1]到a[n-1]的顺序表中顺序查找出关键字值为key的数据元素 
	 * @param key
	 * @return
	 * @author liuquan
	 * @date  2016年1月6日
	 */
	public static int seqSearchWithGuard(int[] a, int key){
		int i = a.length - 1;
		a[0] = key;
		while(a[i] != key){
			i--;
		}
		if(i > 0){
			return i;
		}
		else{
			return -1;
		}
	}
	
	/**
	 * @description 二分查找算法 
	 * @param key
	 * @return
	 * @author liuquan
	 * @date  2016年1月6日
	 */
	public static int binarySearch(int[] a, int key){
		if(a.length > 0){
			int low = 0, high = a.length - 1;
			
			while(low <= high){
				int mid = (low + high) / 2; //中间位置，当前比较的元素位置
				if(a[mid] == key){
					return mid;
				}
				else if(a[mid] > key){
					high = mid - 1;
				}
				else{
					low = mid + 1;
				}
			}
		}
		return -1;
	}
	
}
