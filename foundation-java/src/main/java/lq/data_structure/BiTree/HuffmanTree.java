package lq.data_structure.BiTree;

/**
 * @description 哈夫曼树结点结构
 * 
 * @author liuquan
 * @date 2015年12月30日
 */
class HuffmanNode {
	private int weight; // 结点权值
	private int flag; // 结点是否加入哈夫曼树标志
	private HuffmanNode parent, lchild, rchild; // 父结点，左右孩子结点
	
	public HuffmanNode(){
		this(0);
	}
	
	// 构造一个具有权值的结点
	public HuffmanNode(int weight){
		this.weight = weight;
		flag = 0;
		parent = lchild = rchild = null;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public HuffmanNode getParent() {
		return parent;
	}

	public void setParent(HuffmanNode parent) {
		this.parent = parent;
	}

	public HuffmanNode getLchild() {
		return lchild;
	}

	public void setLchild(HuffmanNode lchild) {
		this.lchild = lchild;
	}

	public HuffmanNode getRchild() {
		return rchild;
	}

	public void setRchild(HuffmanNode rchild) {
		this.rchild = rchild;
	}

}

/**
 * @description 构造哈夫曼树和哈夫曼编码 
 * 
 * @author liuquan
 * @date  2015年12月30日
 */
public class HuffmanTree {
	
	// W存放n个具有权值的结点
	public int[][] huffmanCoding(int[] W){
		int n = W.length; //字符个数
		int m = 2 * n - 1; //哈夫曼树的结点
		HuffmanNode[] HN = new HuffmanNode[m]; 
		for(int i = 0; i < n; i++){
			HN[i] = new HuffmanNode(W[i]);  			
		}
		
		//创建哈夫曼树
		for(int i = n; i < m; i++){
			// 在HN[]中选择不再哈夫曼树weight最小的两个结点min1和min2
			HuffmanNode min1 = selectMin(HN, i-1);
			min1.setFlag(1);
			HuffmanNode min2 = selectMin(HN, i-1);
			min2.setFlag(1);
			// 构造min1和min2的父结点，并修改父结点的权值
			HN[i] = new HuffmanNode();
			min1.setParent(HN[i]);
			min2.setParent(HN[i]);
			HN[i].setLchild(min1);
			HN[i].setRchild(min2);
			HN[i].setWeight(min1.getWeight() + min2.getWeight()); 
		}
		
		// 从叶子结点到根 逆向求每个字符的哈夫曼编码
		int[][] HuffCode = new int[n][n];
		for(int j = 0; j < n; j++){
			int start = n - 1;
			// 从叶子到根逆向求编码 （存储时倒序存，输出时顺序输，-1为开始标志 ）
			for(HuffmanNode c = HN[j], p = c.getParent(); p != null; c=p, p = p.getParent()){				
				if(p.getLchild().equals(c)){
					HuffCode[j][start--] = 0;
				}
				else{
					HuffCode[j][start--] = 1;
				}
				// 编码开始标志是-1
				HuffCode[j][start] = -1;
			}
		}
		return HuffCode;
	}

	/**
	 * @description 在HN[0……i-1]选择不在哈夫曼树中且weight最小的结点
	 * @param HN 
	 * @param end
	 * @return
	 * @author liuquan
	 * @date  2015年12月30日
	 */
	private HuffmanNode selectMin(HuffmanNode[] HN, int end) {
		HuffmanNode min = HN[end]; //先假设最后一个元素为最小值
		for(int i = 0; i < end; i++){
			HuffmanNode h = HN[i];
			// 不在哈夫曼树中且权值最小的结点
			if(h.getFlag() == 0 && h.getWeight() < min.getWeight()){
				min = h;
			}
		}
		return min;
	}
	
	public static void main(String[] args) {
		int[] W = {23, 11, 5, 3, 29, 14, 7, 8};
		HuffmanTree T = new HuffmanTree();
		//求哈夫曼编码
		int[][] HN = T.huffmanCoding(W);
		System.out.println("哈夫曼编码为：");
		
		for(int i = 0; i < HN.length; i++){
			System.out.print("权重：" + W[i] + " ");
			
			for(int j = 0; j < HN[i].length; j++){
				if(HN[i][j] == -1){
					for(int k = j + 1; k < HN[i].length; k++){
						System.out.print(HN[i][k]);
					}
					break;
				}
			}
			System.out.println();
		}
	}	
	
}
 



