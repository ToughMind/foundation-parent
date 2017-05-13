package lq.data_structure.Graph;

/**
 * @description 求最短路径的问题 弗洛伊德  算法   注意其成员变量的含义和迪杰斯特拉算法的完全不一样
 * @author liuquan
 * @date  2015年12月31日
 */
public class ShortestPath_FLOYD {
	//顶点v和w之间的最短路径P[v][w],若P[v][w][u]为true，则u是从v到w当前求得最短路径上的顶点 
	private boolean[][][] P;

	//顶点v和w之间的最短路径的带权长度D[v][w]
	private int[][] D;
	
	public final static int INFINITY = Integer.MAX_VALUE;
	
	public boolean[][][] getP() {
		return P;
	}

	public int[][] getD() {
		return D;
	}

	 
	public void FLOYD(MGraph G){
		int vexNum = G.getVexNum();
		this.P = new boolean[vexNum][vexNum][vexNum];
		this.D = new int[vexNum][vexNum];
		
		//初始化所有数据
		for(int v = 0; v < vexNum; v++){
			for(int w = 0; w < vexNum; w++){
				D[v][w] = G.getArcs()[v][w];
				for(int u = 0; u < vexNum; u++)
					P[v][w][u] = false;
				
				if(D[v][w] < INFINITY) {  //从v到w有直接路径
					P[v][w][v] = true;
					P[v][w][w] = true;
				}
			}
		}
		
		for(int u = 0; u < vexNum; u++) 
			for(int v = 0; v < vexNum; v++) 
				for(int w = 1; w < vexNum; w++)
					if(D[v][u] < INFINITY && D[u][w] < INFINITY && D[v][u] + D[u][w] < D[v][w]){
						D[v][w] = D[v][u] + D[u][w];
						for(int i = 0; i< vexNum; i++)
							P[v][w][i] = P[v][u][i] || P[u][w][i]; 
					}
	}
 
	
	public static void main(String[] args) throws Exception {
		Object[] vexs = { "v0", "v1", "v2"};
		int[][] arcs = { { INFINITY, 4, 11}, 
				{ 6, INFINITY, 2}, 
				{ 3, INFINITY, INFINITY} };
		MGraph G = new MGraph(GraphKind.DN, 3, 5, vexs, arcs);
		ShortestPath_FLOYD floyd = new ShortestPath_FLOYD();
		floyd.FLOYD(G); 
		
		for(int i = 0; i < vexs.length; i++){
			System.out.println("从" + G.getVex(i) + "出发：");
			for(int j = 0; j < vexs.length; j++){
				System.out.println("到" + G.getVex(j) + "的最短路径长度是：" + (floyd.getD()[i][j] == INFINITY ? "∞" : floyd.getD()[i][j]) );
				System.out.print("经过结点有：");
				for(int k = 0; k < vexs.length; k++){
					if(floyd.getP()[i][j][k])  //即从vi到vj经过了k位置的顶点
						System.out.print(G.getVex(k) + " ");					 
				}
				System.out.println();
			}   			
		}
	}
	
}
