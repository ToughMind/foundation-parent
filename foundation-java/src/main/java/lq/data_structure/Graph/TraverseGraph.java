package lq.data_structure.Graph;

import Queue.LinkQueue;

/**
 * @description 图的遍历(广度优先搜索 + 深度优先搜索) 
 * 
 * @author liuquan
 * @date  2015年12月30日
 */
public class TraverseGraph {
	// 访问标志数组
	private static boolean[] visited;
	
	/**
	 * @description 广度优先搜索 
	 * @param G
	 * @throws Exception
	 * @author liuquan
	 * @date  2015年12月30日
	 */
	public static void BFSTraverse(IGraph G) throws Exception{
		visited = new boolean[G.getVexNum()];
		// 初始化标志数组
		for(int v = 0; v < G.getVexNum(); v++)
			visited[v] = false;
		
		for(int v = 0; v < G.getVexNum(); v++)
			if( !visited[v])
				BFS(G, v);
	}

	private static void BFS(IGraph G, int v) throws Exception{ 
		visited[v] = true;
		System.out.println(G.getVex(v).toString() + " ");
		LinkQueue Q = new LinkQueue(); //辅助队列
		Q.offer(v); //v入队列
		while( !Q.isEmpty()){
			//队头元素出列并赋值给u
			int u = (Integer)Q.poll();
			for(int w = G.firstAdjVex(u); w >= 0; w = G.nextAdjVex(u, w)){
				if( !visited[w]){
					visited[w] = true;
					System.out.println(G.getVex(w).toString() + " ");
					Q.offer(w);
				}
			}
		}
	}
	
	/**
	 * @description 深度优先搜索 
	 * @param G
	 * @throws Exception
	 * @author liuquan
	 * @date  2015年12月30日
	 */
	public static void DFSTraverse(IGraph G) throws Exception{
		visited = new boolean[G.getVexNum()];
		// 初始化标志数组
		for(int v = 0; v < G.getVexNum(); v++)
			visited[v] = false;
		
		for(int v = 0; v < G.getVexNum(); v++)
			if( !visited[v])
				DFS(G, v);
	}
	
	
	
	private static void DFS(IGraph G, int v) throws Exception{ 
		visited[v] = true;
		System.out.println(G.getVex(v).toString() + " ");
		for(int w = G.firstAdjVex(v); w >= 0; w = G.nextAdjVex(v, w)){
			if( !visited[w]){ 
				DFS(G, w);
			} 
		}
	}

	public static void main(String[] args) throws Exception {
		IGraph g = new ALGraph();
		g.createGraph(); 
		((ALGraph)g).display();
		System.out.println("广度遍历结果是：");
		BFSTraverse(g);
		System.out.println("深度遍历结果是：");
		DFSTraverse(g);		
	}
	
}
