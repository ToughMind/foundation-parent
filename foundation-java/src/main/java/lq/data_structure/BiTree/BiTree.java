package lq.data_structure.BiTree;

import Queue.LinkQueue;
import Stack.LinkStack;

/**
 * @Description 链式二叉树的存储结点
 * @author liuquan
 * @time 2015年12月2日 下午11:20:38
 */
class BiTreeNode {
	private Object data;
	private BiTreeNode lchild, rchild;

	public BiTreeNode() {
		this(null);
	}

	public BiTreeNode(Object data) {
		this(data, null, null);
	}

	public BiTreeNode(Object data, BiTreeNode lchild, BiTreeNode rchild) {
		this.data = data;
		this.lchild = lchild;
		this.rchild = rchild;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public BiTreeNode getLchild() {
		return lchild;
	}

	public void setLchild(BiTreeNode lchild) {
		this.lchild = lchild;
	}

	public BiTreeNode getRchild() {
		return rchild;
	}

	public void setRchild(BiTreeNode rchild) {
		this.rchild = rchild;
	}
}

/**
 * @Description 链式二叉树结构，相关操作
 * @author liuquan
 * @time 2015年12月2日 下午11:20:26
 */
public class BiTree {
	private BiTreeNode root;

	public BiTree() {
		this.root = null;
	}

	public BiTree(BiTreeNode root) {
		this.root = root;
	} 
	
	/*格外注意 这里index是static的，是共享的，*/
	private static int index = 0;
	/**
	 * @description 由标明空子树的先根遍历序列创建一颗二叉树
	 * 				可知由二叉树的先根遍历序列不能唯一确定一棵二叉树，如果能够在先根序列中加入空子树的信息即可。
	 * @param preStr
	 * @author liuquan
	 * @date  2015年12月29日
	 */
	public BiTree(String preStr){
		char c = preStr.charAt(index++);
		if(c != '#'){
			this.root = new BiTreeNode(Character.valueOf(c));
			this.root.setLchild(new BiTree(preStr).getRoot());
			this.root.setRchild(new BiTree(preStr).getRoot());
		}  
		else
			this.root = null; 
	}
		

	/**
	 * @description 先根遍历和中根遍历序列创建一颗二叉树算法
	 * 				1.取先根遍历序列的第一个结点作为根结点
	 * 				2.在中根遍历序列中寻找根结点，确定位置假设为i（0≤i≤count-1）
	 * 				3.在中根遍历序列中确定：根结点之前的i个结点构成左子树的中跟遍历序列，根结点之后的count-i-1个结点构成右子树的中跟遍历序列
	 * 				4.在先根遍历序列中确定：根结点之后i个结点构成左子树的先根序列，剩下的count-i-1个结点构成右子树的先根遍历
	 * 				5.根据3.和4.进行递归算法
	 * @param preOrder 先根遍历序列
	 * @param inOrder 中根遍历序列  
	 * @param preIndex 新的先根遍历序列在preOrder的开始位置
	 * @param inIndex 新的中根遍历序列在inOrder的开始位置
	 * @param count 树中结点个数
	 * @time 2015年12月2日 下午11:21:26
	 */
	public BiTree(String preOrder, String inOrder, int preIndex, int inIndex, int count) {
		if(count > 0){
			// 取先根遍历序列的第一个结点作为根结点
			char r = preOrder.charAt(preIndex);
			// 寻找根结点在中根遍历序列中的位置
			int i = 0;
			for(; i < count; i++){
				if(r == inOrder.charAt(i + inIndex)){
					break;
				}
			}			 
			this.root = new BiTreeNode(Character.valueOf(r)); //建立树的根结点
			//建立树的左子树
			this.root.setLchild(new BiTree(preOrder, inOrder, preIndex + 1, inIndex, i).root);
			//建立树的右子树
			this.root.setRchild(new BiTree(preOrder, inOrder, preIndex + i + 1, inIndex + i + 1, count - i - 1).root);
		}
	}
	
	
	public BiTreeNode getRoot() {
		return root;
	}

	public void setRoot(BiTreeNode root) {
		this.root = root;
	}

	/**
	 * @description 先根遍历 递归算法
	 * @param T 二叉树的根结点
	 * @time 2015年12月2日 下午11:21:56
	 */
	public void preRootTraverse(BiTreeNode T) {
		if (T != null) {
			System.out.print(T.getData() + " ");
			preRootTraverse(T.getLchild());
			preRootTraverse(T.getRchild());
		}
	}

	/**
	 * @description 中根遍历 递归算法
	 * @param T 二叉树的根结点
	 * @time 2015年12月2日 下午11:22:44
	 */
	public void inRootTraverse(BiTreeNode T) {
		if (T != null) {
			inRootTraverse(T.getLchild());
			System.out.print(T.getData() + " ");
			inRootTraverse(T.getRchild());
		}
	}

	/**
	 * @description 后根遍历 递归算法
	 * @param T 二叉树的根结点
	 * @time 2015年12月2日 下午11:22:56
	 */
	public void postRootTraverse(BiTreeNode T) {
		if (T != null) {
			postRootTraverse(T.getLchild());
			postRootTraverse(T.getRchild());
			System.out.print(T.getData() + " ");
		}
	}

	/**
	 * @description 先根遍历 非递归算法
	 * @time 2015年12月2日 下午11:23:33
	 */
	public void preRootTraverse() {
		BiTreeNode T = root;
		if (T != null) {			
			// 构造一个栈 存放临时数据
			LinkStack s = new LinkStack(); 
			s.push(T);
			while (!s.isEmpty()) {
				T = (BiTreeNode) s.pop();
				System.out.print(T.getData() + " ");
				while (T != null) {
					if (T.getLchild() != null)
						System.out.print(T.getLchild().getData() + " ");
					if (T.getRchild() != null)
						s.push(T.getRchild());
					T = T.getLchild();
				}
			}
		}
	}

	/**
	 * @description 中根遍历 非递归算法
	 * @time 2015年12月2日 下午11:24:10
	 */
	public void inRootTraverse() {
		BiTreeNode T = root;
		if (T != null) {
			LinkStack s = new LinkStack();
			s.push(T);
			while (!s.isEmpty()) {
				// 栈顶结点的所有左孩子结点入栈
				while (s.peek() != null) {
					s.push(((BiTreeNode) s.peek()).getLchild());
				}
				// 空结点出栈
				s.pop();
				if (!s.isEmpty()) {
					T = (BiTreeNode) s.pop();
					System.out.print(T.getData() + " ");
					s.push(T.getRchild());
				}
			}
		}
	}

	/**
	 * @description 后根遍历 非递归算法
	 *              flag用来标记栈顶元素是否被访问，true代表被访问。p指针指向遍历过程中最后一个被访问的结点，若当前结点的右孩子为空或者就是p指针
	 *              ，证明右子树遍历完成
	 * @time 2015年12月2日 下午11:24:22
	 */
	public void postRootTraverse() {
		BiTreeNode T = root;
		if (T != null) {
			LinkStack s = new LinkStack();
			s.push(T);
			boolean flag = false;
			BiTreeNode p = null;
			while (!s.isEmpty()) {
				// 栈顶结点的所有左孩子结点入栈
				while (s.peek() != null) {
					s.push(((BiTreeNode) s.peek()).getLchild());
				}
				// 空结点出栈
				s.pop();
				while (!s.isEmpty()) {
					T = (BiTreeNode) s.peek();
					if (T.getRchild() == null || T.getRchild() == p) {
						System.out.print(T.getData() + " ");
						s.pop();
						p = T;
						flag = true;
					} else {
						s.push(T.getRchild());
						flag = false;
					}
					if (!flag) {
						break;
					}
				}
			}
		}
	}

	/**
	 * @description 层次遍历
	 * @throws Exception
	 * @time 2015年12月2日 下午11:24:56
	 */
	public void levelTraverse() throws Exception {
		BiTreeNode T = root;
		if (T != null) {
			LinkQueue L = new LinkQueue(); // 构造队列
			L.offer(T);
			while (!L.isEmpty()) {
				T = (BiTreeNode) L.poll();
				System.out.print(T.getData() + " ");
				if (T.getLchild() != null)
					L.offer(T.getLchild());
				if (T.getRchild() != null)
					L.offer(T.getRchild());
			}
		}
	}

	/**
	 * @description 查找值为x的结点
	 * @param T 二叉树的根结点
	 * @param x 要比较的值
	 * @return
	 * @time 2015年12月2日 下午11:27:34
	 */
	public BiTreeNode searchNode(BiTreeNode T, Object x) {
		if (T != null) {
			// 对根结点进行判断
			if (T.getData().equals(x))
				return T;
			else {
				// 查找左子树
				BiTreeNode l = searchNode(T.getLchild(), x);
				// 若在左子树找到值为x的结点则返回该结点；否则，在右子树中查找该结点
				return l != null ? l : searchNode(T.getRchild(), x);
			}
		}
		return T;
	}

	/**
	 * @description 计算二叉树中结点的个数 采用先根遍历
	 * @param T 二叉树的根结点
	 * @return 结点个数
	 * @time 2015年12月2日 下午11:34:45
	 */
	public int countNode(BiTreeNode T) {
		// 采用先根遍历的方式对二叉树进行遍历
		int count = 0;
		if (T != null) {
			++count;
			count += countNode(T.getLchild());
			count += countNode(T.getRchild());
		}
		return count;
	}

	/**
	 * @description 计算二叉树中结点的个数 采用层次遍历
	 * @param T 二叉树的根结点
	 * @return 结点的个数
	 * @throws Exception
	 * @time 2015年12月2日 下午11:39:29
	 */
	public int countNode8levelTraverse(BiTreeNode T) throws Exception {
		int count = 0;
		if (T != null) {
			LinkQueue L = new LinkQueue();
			L.offer(T);
			while (!L.isEmpty()) {
				T = (BiTreeNode) L.poll();
				// 结点数增1
				++count;
				// 左孩子非空 入队列
				if (T.getLchild() != null)
					L.offer(T.getLchild());
				if (T.getRchild() != null)
					L.offer(T.getRchild());
			}
		}
		return count;
	}

	/**
	 * @description 计算二叉树的深度
	 * @return 二叉树的深度
	 * @param T
	 *            二叉树的根结点
	 * @time 2015年12月2日 下午11:44:35
	 */
	public int getDepth(BiTreeNode T) {
		if (T != null) {
			// 左子树的深度
			int lDepth = getDepth(T.getLchild());
			// 右子树的深度
			int rDepth = getDepth(T.getRchild());
			// 返回左子树和右子树中深度的最大值加1
			return 1 + (lDepth > rDepth ? lDepth : rDepth);
		}
		return 0;
	}

	/**
	 * @description 判断两颗二叉树是否相等的算法
	 * @return 是否相等
	 * @time 2015年12月2日 下午11:49:12
	 */
	public boolean isEqual(BiTreeNode T1, BiTreeNode T2) {
		if (T1 == null && T2 == null) {
			return true;
		}
		if (T1 != null && T2 != null)
			// 根结点的值是否相等
			if (T1.getData().equals(T2.getData()))
				// 左子树是否相等
				if (isEqual(T1.getLchild(), T2.getLchild()))
					// 右子树是否相等
					if (isEqual(T1.getRchild(), T2.getRchild()))
						return true;
		return false;
	}
	
	/**
	 * @description 由顺序存储的完全二叉树建立一棵二叉树，其中index标识结点在顺序存储结构中的位置 
	 * @param sqBiTree 顺序存储的完全二叉树序列
	 * @param index 标识结点在顺序存储结构中的位置 
	 * @return
	 * @author liuquan
	 * @date  2015年12月29日
	 */
	public BiTreeNode createBiTree(String sqBiTree, int index){
		BiTreeNode root = null;
		if(index < sqBiTree.length()){  //位置不越界
			root = new BiTreeNode(Character.valueOf(sqBiTree.charAt(index))); 
			root.setLchild(createBiTree(sqBiTree, 2 * index + 1));
			root.setRchild(createBiTree(sqBiTree, 2 * index + 2));
		}
		return root;
	}
}
