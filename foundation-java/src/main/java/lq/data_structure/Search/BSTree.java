package lq.data_structure.Search;

/**
 * @description 二叉排序树结构 
 * 
 * @author liuquan
 * @date  2016年1月7日
 */
public class BSTree {
	private  BiTreeNode root;
	public BSTree(){
		root = null;
	}
	
	public BiTreeNode getRoot() {
		return root;
	}
	/**
	 * @description 中序遍历二叉树 
	 * @param p
	 * @author liuquan
	 * @date  2016年1月7日
	 */
	public void inOrderTraverse(BiTreeNode p){
		if(p != null){
			inOrderTraverse(p.getLchild());
			System.out.print(p.getData() + " ");
			inOrderTraverse(p.getRchild());
		}
	}  
	
	/**
	 * @description 在二叉排序树中查找关键字为key的结点，若查找成功，则返回该结点
	 * @param p
	 * @param key
	 * @return
	 * @author liuquan
	 * @date  2016年1月7日
	 */
	public Object searchBST(BiTreeNode p, int key){
		if(p != null){
			if(key == (int)p.getData()){
				return p;
			}
			if(key < (int)p.getData()){
				return searchBST(p.getLchild(), key);
			}
			else{
				return searchBST(p.getRchild(), key);
			}
		}
		return null;
	}

	/**
	 * @description 插入操作,若插入成功，则返回true。。
	 * 				先比较key值，若已存在，则不用插入；否则，将新结点插入到表中。新插入的结点一定是作为叶子结点添加到表中 
	 * @return
	 * @author liuquan
	 * @date  2016年1月7日
	 */
	public boolean insertBST(BiTreeNode p, int key){
		if(p == null){
			root = new BiTreeNode(key);
			return true;
		}
		
		//不插入关键字重复的结点
		if(key == (int)p.getData()){
			return false;
		}
		if(key < (int)p.getData()){ //往左子树插入
			if(p.getLchild() == null){
				p.setLchild(new BiTreeNode(key));
				return true;
			}
			else{
				return insertBST(p.getLchild(), key);
			}
		}
		else if(p.getRchild() == null){
			p.setRchild(new BiTreeNode(key));
			return true;
		}
		else{
			return insertBST(p.getRchild(), key);
		}
	}
	
	 
	/**
	 * @description 删除操作。若删除成功，则返回被删除的结点值
	 * 				1.若删除的是叶子结点，则直接删除该结点即可。若同时也是根结点，则删除后二叉排序树为空树
	 * 				2.若被删除结点只有左子树而无右子树 ，可直接将其左子树的根结点替代被删除结点的位置
	 * 				3.若删除结点只有右子树而无左子树，可直接将其右子树的根结点替代被删除结点的位置
	 *				4.若删除结点有左、右子树，可将被删除结点在中序遍历下的前驱结点（或者后继结点）去代替被删除的结点
	 * @param p 以p为根的二叉排序树
	 * @param key 
	 * @param parent 是p的父结点，根结点的父结点是null
	 * @return
	 * @author liuquan
	 * @date  2016年1月7日
	 */
	public int removeBST(BiTreeNode p, int key,BiTreeNode parent){
		if(p != null){
			if(key < (int)p.getData()){	//在左子树中删除
				return removeBST(p.getLchild(), key, p);
			}
			else if(key > (int)p.getData()){ //在右子树中删除
				return removeBST(p.getRchild(), key, p);
			}
			else if(p.getLchild()!=null && p.getRchild() != null){ //相等且有左右子树
				// 寻找p在中序遍历下的后继结点next
				BiTreeNode next = p.getRchild();
				//寻找右子树中的最左孩子就是p的中序后继结点
				while(next.getLchild() != null){
					next = next.getLchild();
				}
				p.setData(next.getData());
				return removeBST(p.getRchild(), (int)p.getData(), p);
			}
			else{ //p是1度或者叶子结点
				if(parent == null){ //p是根
					if(p.getLchild() != null){
						root = p.getLchild();
					}
					else{
						root = p.getRchild();
					}
					return (int) p.getData();
				}
				
				if(p == parent.getLchild()){ // p是左孩子
					if(p.getLchild() != null){
						parent.setLchild(p.getLchild());
					}
					else{
						parent.setLchild(p.getRchild());
					} 
				}
				
				else if(p == parent.getRchild()){ //p是右孩子
					if(p.getLchild() != null){
						parent.setRchild(p.getLchild());
					}
					else{
						parent.setRchild(p.getRchild());
					} 
				}
				return (int) p.getData();
			}			
		}
		return -1;
	}	
	
}

/**
 * @description 二叉排序树结点结构 
 * 
 * @author liuquan
 * @date  2016年1月7日
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

