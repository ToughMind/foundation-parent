package lq.data_structure.Sort;

public class Test { 
	
	public static void main(String[] args) {
		int[] a = new int[]{52, 39, 67, 95, 70, 8, 25, 52};
		int[] b; 
		
		System.out.println("排序前的序列：");
		Sort.display(a);
		
		System.out.println("不带监视哨的插入排序算法");
		b = Sort.insertSort(a);
		Sort.display(b);		
		
		System.out.println("带有监视哨的插入排序算法(第一个元素不算，作为监视哨)");
		b = Sort.insertSortWithGuard(a);
		Sort.display(b);
		
		System.out.println("希尔排序算法");
		int[] d = new int[]{5, 3, 1};
		b = Sort.shellSort(d, a);
		Sort.display(b);
		
		System.out.println("冒泡排序算法");
		b = Sort.bubbleSort(a);
		Sort.display(b);
		
		System.out.println("快速排序算法");
		b = Sort.qSort(a);
		Sort.display(b);
		
		System.out.println("直接选择排序算法");
		b = Sort.selectSort(a);
		Sort.display(b);
		
		System.out.println("树形选择排序算法");
		b = Sort.tournamentSort(a);
		Sort.display(b);
		
		System.out.println("小顶堆排序算法，由于每趟都是把最小关键字放到了数组末尾，所以输出数组的时候是倒序的");
		b = Sort.heapSort(a);
		Sort.display(b);
		
		System.out.println("2-路归并排序算法");
		b = Sort.mergeSort(a);
		Sort.display(b);		 
		
	}
}
