package lq.data_structure.Graph;

/**
 * @description 最小生成树的描述
 * 
 * @author liuquan
 * @date 2015年12月30日
 */
public class MiniSpanTree {
	private final static int INFINITY = Integer.MAX_VALUE;

	/**
	 * @description 内部类辅助记录从顶点集U到V-U的代价最小的边
	 * 
	 * @author liuquan
	 * @date 2015年12月30日
	 */
	private class CloseEdge {
		Object adjVex;
		int lowCost;

		public CloseEdge(Object adjVex, int lowCost) {
			super();
			this.adjVex = adjVex;
			this.lowCost = lowCost;
		}
	}

	/**
	 * @description 用普里姆算法从第u个顶点出发构造网G的最小生成树T，返回由生成树边组成的二维数组
	 * @param G
	 * @param u
	 * @return
	 * @throws Exception
	 * @author liuquan
	 * @date 2015年12月30日
	 */
	public Object[][] PRIM(MGraph G, Object u) throws Exception {
		Object[][] tree = new Object[G.getVexNum() - 1][2];
		int count = 0;
		CloseEdge[] closeEdge = new CloseEdge[G.getVexNum()];
		int k = G.locateVex(u);

		// 初始化辅助数组
		for (int j = 0; j < G.getVexNum(); j++) {
			if (j != k)
				closeEdge[j] = new CloseEdge(u, G.getArcs()[k][j]);
		}
		closeEdge[k] = new CloseEdge(u, 0); // 初始化U={ u }
		// 选择其余G。vexNum - 1 个顶点
		for (int i = 1; i < G.getVexNum(); i++) {
			// 求出下一个要并入U的顶点：第k个顶点
			k = getMinMum(closeEdge);

			tree[count][0] = closeEdge[k].adjVex; // 原U中的顶点
			tree[count][1] = G.getVexs()[k]; // 要加入U中的顶点
			count++;
			closeEdge[k].lowCost = 0; // 第k个顶点并入U后，其辅助数组值置为0

			/*
			 * 新顶点并入U后重新选择 因为并入了一个新的顶点到U中，那么对于每个在V-U的顶点到U的最小距离可能就会改变
			 * 如原本U中有v0，V-U中有v1
			 * ，v2,v3，那么closeEdge[1,2,3]有个值，现在v1并入了U中，V-U中还有v2，v3，
			 * 那么closeEdge[1]
			 * 置0，closeEdge[2,3]就要考虑v2——v1和v3——v1的距离并和原来的值做个比较，再取其最小的值
			 */
			for (int j = 0; j < G.getVexNum(); j++) {
				if (G.getArcs()[k][j] < closeEdge[j].lowCost)
					closeEdge[j] = new CloseEdge(G.getVex(k), G.getArcs()[k][j]);
			}
		}
		return tree;
	}

	/**
	 * @description 在closeEdge中选出lowCost最小且不为0的顶点
	 * @param closeEdge
	 * @return
	 * @author liuquan
	 * @date 2015年12月30日
	 */
	private int getMinMum(CloseEdge[] closeEdge) {
		int min = INFINITY;
		int v = -1;
		for (int i = 0; i < closeEdge.length; i++) {
			if (closeEdge[i].lowCost != 0 && closeEdge[i].lowCost < min) {
				min = closeEdge[i].lowCost;
				v = i;
			}
		}
		return v;
	}

	// 测试
	public static void main(String[] args) throws Exception {
		Object[] vexs = { "v0", "v1", "v2", "v3", "v4", "v5" };
		int[][] arcs = { { INFINITY, 7, 1, 5, INFINITY, INFINITY },
				{ 7, INFINITY, 6, INFINITY, 3, INFINITY },
				{ 1, 6, INFINITY, 7, 6, 4 },
				{ 5, INFINITY, 7, INFINITY, INFINITY, 2 },
				{ INFINITY, 3, 6, INFINITY, INFINITY, 7 },
				{ INFINITY, INFINITY, 4, 2, 7, INFINITY }, };
		MGraph G = new MGraph(GraphKind.UDG, 6, 10, vexs, arcs);
		Object[][] tree = new MiniSpanTree().PRIM(G, "v1");
		for(int i = 0; i < tree.length; i++)
			System.out.println(tree[i][0]+ "-" + tree[i][1]);
	}

}
