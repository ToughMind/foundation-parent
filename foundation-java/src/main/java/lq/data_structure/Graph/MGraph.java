package lq.data_structure.Graph;

import java.util.Scanner;

/**
 * @description 图的邻接矩阵的描述 
 * 
 * @author liuquan
 * @date  2015年12月30日
 */
public class MGraph implements IGraph{
	public final static int INFINITY = Integer.MAX_VALUE;
	private GraphKind kind; // 图的种类标志
	private int vexNum, arcNum; // 图的当前顶点数和边数
	private Object[] vexs; // 顶点
	private int[][] arcs;  // 邻接矩阵
	
	public MGraph(){
		this(null, 0, 0, null, null);
	} 
	
	public GraphKind getKind() {
		return kind;
	} 

	public Object[] getVexs() {
		return vexs;
	}
   
	public int[][] getArcs() {
		return arcs;
	}



	public MGraph(GraphKind kind, int vexNum, int arcNum, Object[] vexs, int[][] arcs) { 
		this.kind = kind;
		this.vexNum = vexNum;
		this.arcNum = arcNum;
		this.vexs = vexs;
		this.arcs = arcs;
	}


	/**
	 * @description 图的创建算法 图的类型共有4种 
	 * 
	 * @author liuquan
	 * @date  2015年12月30日
	 */
	public void createGraph() {
		Scanner sc = new Scanner(System.in);
		System.out.println("请输入图的类型：（UDG、DG、UDN、DN）");
		GraphKind kind = GraphKind.valueOf(sc.next());
		switch (kind) {
		case UDG:
			createUDG();
			break;
		case DG:
			createDG();
			break;
		case UDN:
			createUDN();
			break;
		case DN:
			createDN();
			break;
		default:
			break;
		}
	}


	/**
	 * @description 创建有向网
	 * 
	 * @author liuquan
	 * @date  2015年12月30日
	 */
	private void createDN() {
		Scanner sc = new Scanner(System.in);
		System.out.println("请分别输出图的顶点数、图的边数：");
		vexNum = sc.nextInt();
		arcNum = sc.nextInt();
		vexs = new Object[vexNum];
		System.out.println("请分别输出图的各个顶点：");
		for(int v = 0; v < vexNum; v++){
			vexs[v] = sc.next();
		}
		arcs = new int[vexNum][vexNum];
		// 初始化邻接矩阵
		for(int v = 0; v < vexNum; v++){
			for(int u = 0; u < vexNum; u++){
				arcs[v][u] = INFINITY;
			}
		}
		System.out.println("请输入各个边的两个顶点及其权值");
		for(int k = 0; k < arcNum; k++){
			int v = locateVex(sc.next());
			int u = locateVex(sc.next());
			arcs[v][u] = sc.nextInt();
		}
	}


	/**
	 * @description 创建无向网 
	 * 
	 * @author liuquan
	 * @date  2015年12月30日
	 */
	private void createUDN() { 
		Scanner sc = new Scanner(System.in);
		System.out.println("请分别输出图的顶点数、图的边数：");
		vexNum = sc.nextInt();
		arcNum = sc.nextInt();
		vexs = new Object[vexNum];
		System.out.println("请分别输出图的各个顶点：");
		for(int v = 0; v < vexNum; v++){
			vexs[v] = sc.next();
		}
		arcs = new int[vexNum][vexNum];
		// 初始化邻接矩阵
		for(int v = 0; v < vexNum; v++){
			for(int u = 0; u < vexNum; u++){
				arcs[v][u] = INFINITY;
			}
		}
		System.out.println("请输入各个边的两个顶点及其权值");
		for(int k = 0; k < arcNum; k++){
			int v = locateVex(sc.next());
			int u = locateVex(sc.next());
			arcs[v][u] = arcs[u][v] = sc.nextInt();
		}
	}


	/**
	 * @description 创建有向图 
	 * 
	 * @author liuquan
	 * @date  2015年12月30日
	 */ 
	private void createDG() {
		// 略 
	}


	/**
	 * @description 创建无向图 
	 * 
	 * @author liuquan
	 * @date  2015年12月30日
	 */
	private void createUDG() {
		// 略
	}


	public int getVexNum() { 
		return vexNum;
	}

	public int getArcNum() { 
		
		return arcNum;
	}

	/**
	 * @description 根据顶点在数组中的位置返回顶点 
	 * @param v
	 * @return
	 * @throws Exception
	 * @author liuquan
	 * @date  2015年12月30日
	 */
	public Object getVex(int v) throws Exception {		  
		if(v < 0 && v >= vexNum)
			throw new Exception("第" + v + "个顶点不存在");
		return vexs[v];
	}

	/**
	 * @description 顶点定位 根据顶点名返回其在数组中的位置 
	 * @param vex
	 * @return
	 * @author liuquan
	 * @date  2015年12月30日
	 */	
	public int locateVex(Object vex) {  
		for(int v = 0; v < vexNum; v++){
			if(vexs[v].equals(vex))
				return v;
		}
		return -1;
	}

	/**
	 * @description 查找第一个邻接点 
	 * @param v
	 * @return
	 * @author liuquan
	 * @throws Exception 
	 * @date  2015年12月30日
	 */
	public int firstAdjVex(int v) throws Exception { 
		if(v < 0 && v >= vexNum)
			throw new Exception("第" + v + "个顶点不存在");
		
		for(int j = 0; j < vexNum; j++){
			if(arcs[v][j] != 0 && arcs[v][j] < INFINITY){
				return j;
			}
		}
		return -1;
	}

	/**
	 * @description 查找v的相对于w位置的邻接点的下一个邻接点，如 v-a-b-w-c（a,b,w,c都是v的邻接点）,那么返回的就是c
	 * @param v
	 * @param w
	 * @return
	 * @author liuquan
	 * @throws Exception 
	 * @date  2015年12月30日
	 */
	public int nextAdjVex(int v, int w) throws Exception { 
		if(v < 0 && v >= vexNum)
			throw new Exception("第" + v + "个顶点不存在");
		
		for(int j = w + 1; j < vexNum; j++){
			if(arcs[v][j] != 0 && arcs[v][j] < INFINITY){
				return j;
			}
		}
		return -1; 
	}	
	
	public void display(){
		for(int i = 0; i < vexNum; i++){
			for(int j = 0; j< vexNum; j++){
				if(arcs[i][j] == INFINITY) 
					System.out.print("+ "); 
				else
					System.out.print(arcs[i][j] + " ");
			}
			System.out.println();
		}
	} 
	 
}


