package lq.data_structure.BiTree;
 
public class Test_BiTree {

	public static BiTree init() {
		BiTreeNode d = new BiTreeNode("D");
		BiTreeNode g = new BiTreeNode("G");
		BiTreeNode h = new BiTreeNode("H");
		BiTreeNode e = new BiTreeNode("E", g, null);
		BiTreeNode b = new BiTreeNode("B", d, e);
		BiTreeNode f = new BiTreeNode("F", null, h);
		BiTreeNode c = new BiTreeNode("C", f, null);
		BiTreeNode a = new BiTreeNode("A", b, c);
		return new BiTree(a);

	}

	public static void main(String[] args) throws Exception {
		BiTree bitree = init();   
		System.out.println(bitree.countNode(bitree.getRoot()));
		System.out.println(bitree.countNode8levelTraverse(bitree.getRoot()));
	 	
		// 根据前序、中序创建树
		String preOrder = "ABDEGCFH";
		String inOrder = "DBGEAFHC";
		BiTree T = new BiTree(preOrder, inOrder, 0, 0, preOrder.length());
		System.out.println("后根遍历：");
		T.postRootTraverse();
		
		// 根据标注了空子树的先序创建树
		System.out.println();
		String preStr = "AB##CD###";
		BiTree T2 = new BiTree(preStr);
		System.out.println("后根遍历：");
		T2.postRootTraverse(); 
		
		
		// 由完全二叉树的顺序存储结构建立二叉树存储结构
		System.out.println();
		String sqBiTree = "ABCDEFGH";
		BiTreeNode b = new BiTree().createBiTree(sqBiTree, 0);
		System.out.println("后根遍历：");
		new BiTree(b).postRootTraverse(); 
		
	} 
}
