package lq.data_structure.Sort;

import java.util.Arrays;

/**
 * @Description 各种排序算法，都是以数组的存储形式
 * @author liuquan
 * @time 2016年1月3日 下午1:39:28
 */
public class Sort { 

	public static void display(int[] a) { 
		for(int i = 0; i < a.length; i++){
			System.out.print(a[i]+" ");
		} 
		System.out.println();
	}
	
	
	/**
	 * @description 不带监视哨的直接插入排序算法
	 * 
	 * @time 2016年1月3日 下午1:39:56
	 */
	public static int[] insertSort(int[] before){
		int[] a= Arrays.copyOf(before, before.length); 
		int temp, i, j;
		// 循环n-1次  从下标 1 到 n-1
		for(i = 1; i < a.length; i++){
			temp = a[i];
			// 从i-1逆序比较，若temp<元素，则此元素后移（temp≥元素为判断条件）
			for(j = i - 1;j >= 0 && temp < a[j]; j--){ 
				a[j + 1] = a[j];
			}
			a[j + 1] = temp; // eg：若temp比前面元素都小，到此代码处时j=-1了
		}	
		return a;
	}

	/**
	 * @description 带有监视哨的插入排序算法 
	 * @time 2016年1月3日 下午2:10:34
	 */
	public static int[] insertSortWithGuard(int[] before) {   
		int[] a= Arrays.copyOf(before, before.length); 
		int i, j;
		// 循环n-1次  从下标 1 到 n-1
		for(i = 1; i < a.length; i++){
			// 将待插入的第i条元素暂存在a[0]中，同时a[0]设置为监视哨
			a[0] = a[i];
			// 从i-1逆序比较，若temp<元素，则此元素后移（temp≥元素为判断条件）
			for(j = i - 1;a[0] < a[j]; j--){ 
				a[j + 1] = a[j];
			}
			a[j + 1] = a[0]; // eg：若temp比前面元素都小，到此代码处时j=-1了
		}	
		return a;
	}
	 
	/**
	 * @description 希尔排序算法 
	 * @param d d[]为增量数组
	 * @param before 需要排序的元素
	 * @return
	 * @time 2016年1月3日 下午3:01:42
	 */
	public static int[] shellSort(int[] d, int[] before){
		int[] a= Arrays.copyOf(before, before.length); 
		int temp, i, j;
		//控制增量，增量减半，若干趟扫描
		for(int k = 0; k < d.length; k++){			
			int dk = d[k];
			//一趟中若干个子表，每个元素在自己所属的子表内进行直接插入排序  以下是直接插入排序，只不过增量不一定是1
			for(i = dk; i < a.length; i++){
				temp = a[i];
				for(j = i - dk; j >= 0 && temp < a[j]; j -= dk){
					a[j + dk] = a[j]; 
				}
				a[j + dk] = temp;
			}
		}
		return a;		
	}
	
	/**
	 * @description 冒泡排序算法 
	 * @param before
	 * @return
	 * @time 2016年1月3日 下午9:06:50
	 */
	public static int[] bubbleSort(int[] before){
		int[] a= Arrays.copyOf(before, before.length); 
		boolean flag = true;
		for(int i = 1; i < a.length && flag; i++){
			flag = false; //记录未交换
			for(int j = 0; j < a.length - 1; j++){
				if(a[j] > a[j + 1]){
					int temp = a[j];
					a[j] = a[j + 1];
					a[j + 1] = temp;
					flag = true;
				}
			}			
		}
		return a;
	}
	
	/**
	 * @description 快速排序算法。这里说明一下，为了进入方法的数组before不被改变，所以就先赋值给a，
	 * 				然后再调用快速排序算法的代码改变了数组a，因为用到了递归，所有不能跟前面的代码相同处理				
	 * @return
	 * @time 2016年1月4日 上午1:03:21
	 */
	public static int[] qSort(int[] before) {
		int[] a= Arrays.copyOf(before, before.length);  
		qSort(a, 0, a.length - 1);
		return a;
	}

	/**
	 * @description 快速排序算法
	 * @param a
	 * @param low
	 * @param high
	 * @time 2016年1月4日 上午1:28:34
	 */
	private static void qSort(int[] a, int low, int high) {
		int i = low, j = high;
		if(i < j){
			int pivot = a[i]; //设定支点的值,初始位置是low
			int pivotLoc;  // 一趟排序后支点的位置
			
			// 一趟快排的算法，此时在支点前的数据≤支点，支点后的数据≥支点
			while(i < j){
				//high为起点下标 从后往前和pivot支点比较
				while(i < j && pivot <= a[j]){
					j --;
				}
				if(i < j){ //说明上面跳出循环是因为有个high使得pivot > a[high]了
					a[i] = a[j];
					i++;
				}
				//low为起点下标 从前往后和pivot支点比较
				while(i < j && pivot > a[i]){
					i++;
				}
				if(i < j){ 
					a[j] = a[i];
					j--;
				}
			}
			a[i] = pivot; //此时low和high相等 
			pivotLoc = i;
			qSort(a, low, pivotLoc - 1);
			qSort(a, pivotLoc + 1, high);
		}		
	}
	
	/**
	 * @description 直接选择排序算法 
	 * @return
	 * @time 2016年1月5日 上午1:28:19
	 */
	public static int[] selectSort(int[] before){
		int[] a = Arrays.copyOf(before, before.length);
		for(int i = 0; i < a.length - 1; i++){
			// 每趟从a[i]开始的自序列中寻找最小关键字值的元素下标
			int min = i;
			for(int j = i + 1; j < a.length; j++){
				if(a[j] < a[min]){
					min = j;
				}
			}
			if(min != i){
				int temp = a[i];
				a[i] = a[min];
				a[min] = temp;
			}
		}
		return a;
	}
	
	/**
	 * @description 树形选择排序算法，构造胜者树的过程，取根结点是最小关键值 
	 * @return
	 * @time 2016年1月5日 上午1:28:19
	 */
	public static int[] tournamentSort(int[] before){
		int[] a= Arrays.copyOf(before, before.length);
		TreeNode[] tree; //胜者树结点数组
		int leafSize = 1; //胜者树叶子结点数
		//得到叶子结点的个数，该个数必须是2的次幂
		while(leafSize < a.length){
			leafSize *= 2;
		}
		int TreeSize =  2 * leafSize - 1; //胜者树的所有结点数
		int loadIndex = leafSize - 1; //叶子结点存放位置的起始位置
		tree = new TreeNode[TreeSize];
		
		int j = 0;
		//把待排序结点复制到胜者树的叶子结点中
		for(int i = loadIndex; i < TreeSize; i++){
			tree[i] = new TreeNode();
			tree[i].setIndex(i);
			if(j < a.length){
				tree[i].setActive(1);
				tree[i].setData(a[j++]);
			}
			else{
				tree[i].setActive(0);
			}
		}
		int i = loadIndex;  //进行初始化，比较查找关键字值最小的结点
		while(i > 0){
			j = i;
			//处理各对比赛者
			while(j < TreeSize - 1){
				if(tree[j + 1].getActive() == 0 || (tree[j].getData() <= tree[j + 1].getData())){
					tree[(j - 1)/2] = tree[j];  //左孩子胜出
				}
				else{ 
					tree[(j - 1)/2] = tree[j + 1]; //右孩子胜出
				}
				j += 2; //下一对比赛者 
			}
			i = (i - 1)/2; //处理上层结点，类似下一轮比赛（已经决出一轮了）
		}
		
		//处理剩余的n-1个元素
		for(i = 0; i < a.length - 1; i++){
			a[i] = tree[0].getData(); //将胜者树的根（最小值）存入数组
			tree[tree[0].getIndex()].setActive(0); //冠军不再参加比赛
			updateTree(tree, tree[0].getIndex()); //调整有胜者树
		}
		//最后一个元素只需赋值就结束了 不需要再调整（即再进行下一轮比赛）
		a[a.length - 1] = tree[0].getData(); 
		return a;
	}
	
	/**
	 * @description 树形选择排序的调整算法
	 * 				从当前最小关键字的叶子结点开始到根结点路径上的所有结点关键字的修改
	 * @param tree
	 * @param i i是当前最小关键字的下标 
	 * @author liuquan
	 * @date  2016年1月5日
	 */
	private static void updateTree(TreeNode[] tree, int i){
		//因为i是此时最小的关键字（已是冠军），所以在叶子结点中要将其除去比赛资格，对手直接晋级（升为父结点）
		if(i % 2 == 0){ //i为偶数，自己是右结点，对手是左结点,左结点晋级
			tree[(i - 1)/2] = tree[i - 1];			
		}
		else{
			tree[(i - 1)/2] = tree[i + 1];
		}
		i = (i - 1) / 2;
		
		int j = 0;
		while(i > 0){
			if(i % 2 == 0){ //i为偶数，自己是右结点，对手是左结点 
				j = i - 1;
			}
			else{
				j = i + 1;
			}
			
			//比赛对手中有一个为空
			if(tree[i].getActive() == 0 || tree[j].getActive() == 0){
				if(tree[i].getActive() == 1){
					tree[(i - 1) / 2] = tree[i];
				}
				else{
					tree[(i - 1) / 2] = tree[j];
				}
			}
			
			//比赛对手都在
			if(tree[i].getData() < tree[j].getData()){
				tree[(i - 1) / 2] = tree[i];
			}
			else{
				tree[(i - 1) / 2] = tree[j];
			}
			
			i = (i - 1) / 2;	 
		}
	}
	 
	/**
	 * @description 树形选择排序的胜者树结点结构
	 * 
	 * @author liuquan
	 * @date  2016年1月5日
	 */
	private static class TreeNode{
		private int data; //数据域
		private int index; //待插入结点在满二叉树中的序号
		private int active; //参加选择标志,1表示参选，0表示不参选
		public int getData() {
			return data;
		}
		public void setData(int data) {
			this.data = data;
		}
		public int getIndex() {
			return index;
		}
		public void setIndex(int index) {
			this.index = index;
		}
		public int getActive() {
			return active;
		}
		public void setActive(int active) {
			this.active = active;
		}		
	}
	
	/**
	 * @description 堆排序算法
	 * @return
	 * @author liuquan
	 * @date  2016年1月5日
	 */
	public static int[] heapSort(int[] before){
		int[] a= Arrays.copyOf(before, before.length);
		int n = a.length;
		int temp;
		for(int i = n / 2 - 1; i >= 0; i--){ //创建初始堆
			sift(i, n, a);
		}
		for(int i = n - 1; i > 0; i--){ //每趟将最小关键字值交换到后面，再调整成堆
			temp = a[0];
			a[0] = a[i];
			a[i] = temp;
			sift(0, i, a);
		}
		return a;
	}
	
	/**
	 * @description 筛选法调整堆算法 ，以low为根结点的子树调整成小顶堆
	 * @param low
	 * @param high
	 * @author liuquan
	 * @date  2016年1月5日
	 */
	private static void sift(int low, int high, int[] a){
		int i = low; //子树的根结点
		int j = 2 * i + 1;
		int temp = a[i];
		while(j < high){
			//判断条件j < high - 1 表示有右结点，即j+1 < high
			if(j < high - 1 && a[j] > a[j + 1])
				j++;
			
			if(temp > a[j]){
				a[i] = a[j]; //孩子结点中的较小值上移
				i = j;
				j = 2 * i + 1;
			}
			else
				j = high + 1;
		}
		a[i] = temp;
	}
	

	/**
	 * @description 2-路归并排序算法  归并过程中引入数组temp[]，第一趟由a归并到temp，第二趟由temp归并到a，如此反复直到n个记录为一个有序表
	 * 				返回的是a[]。不论是偶数趟还是奇数趟，最后都会mergepas(temp, a, s, n); 数据都会在a中
	 * @return
	 * @author liuquan
	 * @date  2016年1月5日
	 */
	public static int[] mergeSort(int[] before){
		int[] a= Arrays.copyOf(before, before.length);
		int s = 1; //s为已排序的子序列长度
		int n = a.length;
		int[] temp = new int[n];
		while(s < n){
			mergepas(a, temp, s, n);
			s *= 2;
			mergepas(temp, a, s, n);
			s *= 2;
		}
		return a;
	}
	
	/**
	 * @description 一趟归并排序的算法
	 * @param a
	 * @param b
	 * @param s s是待归并的有序子序列的长度
	 * @param n n是待排序序列的长度
	 * @author liuquan
	 * @date  2016年1月5日
	 */
	private static void mergepas(int[] a, int[] b, int s, int n){
		int p = 0;  //p为每一对待合并表的第一个元素的下标
		//首先两两归并长度均为s的有序表
		while(p + 2 * s - 1 <= n - 1){
			merge(a, b, p, p + s - 1, p + 2 * s -1);
			p += 2 * s;
		}
		//归并最后两个长度不相等的有序表
		if(p * s - 1 < n - 1){
			merge(a, b, p, p + s - 1, n - 1);
		}
		else{ //只剩余一个有序表了,直接复制到b中
			for(int i = p; i <= n - 1; i++){
				b[i] = a[i];
			}
		}
	}
	
	
	/**
	 * @description 把两个相邻的有序表a[h……m]和a[m+1……t]归并成一个有序表 b[h……t] 
	 * @author liuquan
	 * @date  2016年1月5日
	 */
	private static void merge(int[] a, int[] b, int h, int m, int t){
		int i = h, j = m +1, k = h;
		//将a中两个相邻子序列归并到b中
		while(i <= m && j <= t){
			if(a[i] <= a[j]){
				b[k++] = a[i++];
			}
			else{
				b[k++] = a[j++];
			}
		}
		//将剩余子序列复制到b中
		while(i <= m){
			b[k++] = a[i++];
		}
		while(j <= t){
			b[k++] = a[j++];
		}
	}
	
}
 