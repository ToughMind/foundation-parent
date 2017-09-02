import lq.data_structure.Search.BSTree;
import lq.data_structure.Search.Search;
import lq.data_structure.Sort.Sort;

public class Test {
	
	public static void main(String[] args) {
		int[] a = new int[]{52, 39, 67, 95, 70, 8, 25, 52};
		
		Sort.display(a);
		System.out.println("顺序查找" );
		System.out.println("95的位置是：" + Search.seqSearch(a, 95));
		System.out.println("30的位置是：" + Search.seqSearch(a, 30));
		System.out.println();
		
		
		int[] b = Sort.qSort(a);
		Sort.display(b);
		System.out.println("二分查找"); 
		System.out.println("95的位置是：" + Search.binarySearch(b, 95));
		System.out.println("30的位置是：" + Search.seqSearchWithGuard(b, 30));
		System.out.println();
		
		System.out.println("二叉排序树 ");
		// 构建二叉排序树
		BSTree bs = new BSTree();
		for(int i = 0; i < a.length; i++){
			bs.insertBST(bs.getRoot(), a[i]);
		}
		System.out.println("二叉树中根遍历：");
		bs.inOrderTraverse(bs.getRoot());
		System.out.println();
		System.out.println("95的位置是：" + bs.searchBST(bs.getRoot(), 95));
		System.out.println("30的位置是：" + bs.searchBST(bs.getRoot(), 30));
		System.out.println("删除结点50后的二叉树中根遍历：");
		bs.removeBST(bs.getRoot(), 52, null);
		bs.inOrderTraverse(bs.getRoot());
	}
}
