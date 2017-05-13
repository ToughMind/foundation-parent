package lq.data_structure.Graph;

/**
 * @description 求最短路径的问题 迪杰斯特拉  算法
 * 				(1)令S = ｛v｝，其中v为源点，并设定D[i] 的初始值为：D[i] = |v，vi|
 *				(2)选择顶点vj使得：D[j] = min｛D[i]｝（i∈V-S），并将vj并入到S中
 *   			(3)对集合V-S的所有顶点vk，若D[j] + |vj，vk| < D[k]，则修改D[k]的值
 *				(4)重复(2)、(3)的操作共n-1次，由此求得的所有最短路径是按路径长度递增的序列 
 * @author liuquan
 * @date  2015年12月31日
 */
public class ShortestPath_DIJ {
	// v0到其余顶点的最短路径，若p[v][w]为true，则w是从v0到v当前求得最短路径上的顶点
	private boolean[][] P;
	
	// v0到其余顶点的最小带权长度 （是变化的，一旦发现更小的，就重新赋值，到最后就是最短路径长度了）
	private int[] D;
	
	private final static int INFINITY = Integer.MAX_VALUE;
	
	
	
	public boolean[][] getP() {
		return P;
	}

	public int[] getD() {
		return D;
	}

	/**
	 * @description 用迪杰斯特拉算法求有向网的v0到其余顶点的最短路径p[v]及其权值D[v]
	 * @param G
	 * @param v0
	 * @author liuquan
	 * @date  2015年12月31日
	 */
	public void DIJ(MGraph G, int v0){
		int vexNum = G.getVexNum(); // 顶点数
		this.P = new boolean[vexNum][vexNum];
		this.D = new int[vexNum];
		//finish[v]为true当且仅当v属于S，即已经求得从v0到v的最短路径
		boolean[] finish = new boolean[vexNum];
		
		//初始化所有数据
		for(int v = 0; v < vexNum; v++){
			finish[v] = false;
			D[v] = G.getArcs()[v0][v];
			for(int w = 0; w < vexNum; w++){
				P[v][w] = false;
			}
			if(D[v] < INFINITY){
				P[v][v0] = true;
				P[v][v] = true;
			}
		}
		
		D[v0] = 0; //从v0开始，并入S集
		finish[v0] = true;
		
		int v = -1 ; // 这里的赋值没有什么实际意义，只是为了保证编译正确
		//开始主循环，每次求得v0到某个v顶点的最短路径，并将v加入到S集.循环n-1次
		for(int i = 1; i < vexNum; i++){
			int min = INFINITY; //当前所知离v0最近的距离
			for(int w = 0; w < vexNum; w++){
				if( !finish[w]){
					if(D[w] < min){
						v = w;
						min = D[w];
					}
				}
			}
			finish[v] = true; //离v0最近的v并入S
			
			//更新当前最短路径和距离
			for(int w = 0; w < vexNum; w++){
				if( !finish[w] && G.getArcs()[v][w] < INFINITY && (min + G.getArcs()[v][w] < D[w])){
					D[w] = min + G.getArcs()[v][w];
					//下面两句这么理解，现在路径是v0-v-w，所以经过了v点，那么v0到v的最小路径自然要给w，同时再加上w点（P[W][W] = true）
					System.arraycopy(P[v], 0, P[w], 0, P[v].length);
					P[w][w] = true;
				}
			}
			
		}
	}
	
	// 测试
	public static void main(String[] args) throws Exception {
		Object[] vexs = { "v0", "v1", "v2", "v3", "v4", "v5" };
		int[][] arcs = { { INFINITY, INFINITY, 10, INFINITY, 30, 100 },
				{ INFINITY, INFINITY, 5, INFINITY, INFINITY, INFINITY },
				{ INFINITY, INFINITY, INFINITY, 50, INFINITY, INFINITY },
				{ INFINITY, INFINITY, INFINITY, INFINITY, INFINITY, 10 },
				{ INFINITY, INFINITY, INFINITY, 20, INFINITY, 60 },
				{ INFINITY, INFINITY, INFINITY, INFINITY, INFINITY, INFINITY } };
		MGraph G = new MGraph(GraphKind.DN, 6, 8 , vexs, arcs);
		ShortestPath_DIJ dij = new ShortestPath_DIJ();
		dij.DIJ(G, 0);
		
		for(int i = 0; i < vexs.length; i++){
			System.out.println("从v0出发，到" + G.getVex(i) + "的最短路径长度是：" + (dij.getD()[i] == INFINITY ? "∞" : dij.getD()[i]) );
			System.out.print("经过结点有：");
			for(int j = 0; j < vexs.length; j++){
				if(dij.getP()[i][j]) //即从v0到i位置的顶点经过了j位置的顶点
					System.out.print(G.getVex(j) + " ");
			}
			System.out.println();
		}
	}
		
}
