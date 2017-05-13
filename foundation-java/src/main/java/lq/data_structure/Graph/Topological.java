package lq.data_structure.Graph;

import Stack.LinkStack;

/**
 * @Description 拓扑排序算法 
 * @author liuquan
 * @time 2016年1月1日 下午3:58:13
 */
public class Topological {
		
	/**
	 * @description 求各个顶点入度的算法 
	 * @param G
	 * @return
	 * @throws Exception
	 * @time 2016年1月1日 下午4:02:49
	 */
	public static int[] findInDegree(ALGraph G) throws Exception{
		 int[] indegree  = new int[G.getVexNum()];
		 for(int i = 0; i < G.getVexNum(); i++)
			 for(ArcNode arc = G.getVexs()[i].getFirstArc(); arc != null; arc = arc.getNextArc())
				 ++ indegree[arc.getAdjVex()]; //入度增1
		 
		 return indegree; 
	}
	
	/**
	 * @description 若G无回路，则输出G的顶点的一个拓扑排序序列并返回true 
	 * @param G
	 * @return
	 * @throws Exception
	 * @time 2016年1月1日 下午4:13:04
	 */
	public static boolean topologicalSort(ALGraph G) throws Exception{
		int count = 0; //输出顶点计数
		int[] indegree = findInDegree(G); //求各个顶点的入度
		LinkStack S = new LinkStack();  //零入度的 顶点栈
		for(int i = 0; i < G.getVexNum(); i++)
			if(indegree[i] == 0)
				S.push(i);  //入度为0的进栈
		while( !S.isEmpty()){
			int i = (Integer) S.pop();
			System.out.print(G.getVex(i) + " "); //输出v号顶点并计数
			++count;
			for(ArcNode arc = G.getVexs()[i].getFirstArc(); arc != null; arc = arc.getNextArc()){
				int k = arc.getAdjVex();
				if(--indegree[k] == 0) // 对k号顶点的每个邻接点的入度减1
					S.push(k);
			} 
		}
		if(count < G.getVexNum())
			return false;  //该有向图有回路
		else 
			return true;
	}
	
	// 测试
	public static void main(String[] args) throws Exception {
		ArcNode ab = new ArcNode(1);
		VNode A = new VNode("A", ab);
		
		ArcNode bc = new ArcNode(2);
		ArcNode be = new ArcNode(4, 0, bc);
		VNode B = new VNode("B", be);
		
		ArcNode cd = new ArcNode(3);
		VNode C = new VNode("C", cd);
		 
		VNode D = new VNode("D");
		
		ArcNode ed = new ArcNode(3);
		VNode E = new VNode("E", ed);
		
		ArcNode fa = new ArcNode(0);
		ArcNode fb = new ArcNode(1, 0, fa);
		ArcNode fe = new ArcNode(4, 0, fb);
 		VNode F = new VNode("F", fb);
		
		VNode[] vexs = {A, B, C, D, E, F};
		ALGraph G = new ALGraph(GraphKind.DG, 6, 8, vexs);
		System.out.println(topologicalSort(G));  
	}
}
