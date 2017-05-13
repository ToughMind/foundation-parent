package lq.data_structure.Graph;

import java.util.Scanner;

/**
 * @description 邻接表顶点结点结构
 * 
 * @author liuquan
 * @date 2015年12月30日
 */
class VNode {
	
	private Object data; // 顶点信息
	
	private ArcNode firstArc; // 指向第一条依附于该顶点的弧

	public VNode() {
		this(null, null);
	}

	public VNode(Object data) {
		this(data, null);
	}

	public VNode(Object data, ArcNode firstArc) {
		this.data = data;
		this.firstArc = firstArc;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public ArcNode getFirstArc() {
		return firstArc;
	}

	public void setFirstArc(ArcNode firstArc) {
		this.firstArc = firstArc;
	}
}

/**
 * @description 邻接表 边（弧）的结点类
 * 
 * @author liuquan
 * @date 2015年12月30日
 */
class ArcNode {
	
	private int adjVex; // 该弧所指向的顶点位置
	
	private int value; // 边的权值
	
	private ArcNode nextArc; // 指向下一条弧

	public ArcNode() {
		this(-1, 0, null);
	}

	public ArcNode(int adjVex) {
		this(adjVex, 0, null);
	}

	public ArcNode(int adjVex, int value) {
		this(adjVex, value, null);
	}

	public ArcNode(int adjVex, int value, ArcNode nextArc) {
		this.adjVex = adjVex;
		this.value = value;
		this.nextArc = nextArc;
	}

	public int getAdjVex() {
		return adjVex;
	}

	public void setAdjVex(int adjVex) {
		this.adjVex = adjVex;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public ArcNode getNextArc() {
		return nextArc;
	}

	public void setNextArc(ArcNode nextArc) {
		this.nextArc = nextArc;
	}
}

/**
 * @description 图的邻接表的描述 
 * 
 * @author liuquan
 * @date  2015年12月30日
 */
public class ALGraph implements IGraph{ 
	
	private GraphKind kind; // 图的种类标志
	
	private int vexNum, arcNum; // 图的当前顶点数和边数
	
	private VNode[] vexs; // 顶点  
	
	public ALGraph(){
		this(null, 0, 0, null);
	}	

	public ALGraph(GraphKind kind, int vexNum, int arcNum, VNode[] vexs) {
		this.kind = kind;
		this.vexNum = vexNum;
		this.arcNum = arcNum;
		this.vexs = vexs;
	}
	
 
	public GraphKind getKind() {
		return kind;
	}

	public VNode[] getVexs() {
		return vexs;
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
		vexs = new VNode[vexNum];
		System.out.println("请分别输出图的各个顶点：");
		for(int v = 0; v < vexNum; v++){
			vexs[v] = new VNode(sc.next());
		} 
		System.out.println("请输入各个边的两个顶点及其权值");
		for(int k = 0; k < arcNum; k++){
			int v = locateVex(sc.next()); //弧尾 指出的点
			int u = locateVex(sc.next()); //弧头 被指入的点
			int value = sc.nextInt();
			addArc(v, u, value);
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
		vexs = new VNode[vexNum];
		System.out.println("请分别输出图的各个顶点：");
		for(int v = 0; v < vexNum; v++){
			vexs[v] = new VNode(sc.next());
		} 
		System.out.println("请输入各个边的两个顶点及其权值");
		for(int k = 0; k < arcNum; k++){
			int v = locateVex(sc.next()); //弧尾 指出的点
			int u = locateVex(sc.next()); //弧头 被指入的点
			int value = sc.nextInt();
			addArc(v, u, value);
			addArc(u, v, value);
		}
	}
	

	/**
	 * @description 在位置v、u顶点之间，添加一条弧，其权值为value 
	 * @param v
	 * @param u
	 * @param value
	 * @author liuquan
	 * @date  2015年12月30日
	 */
	private void addArc(int v, int u, int value) {
		ArcNode arc = new ArcNode(u,value);
		arc.setNextArc(vexs[v].getFirstArc());
		vexs[v].setFirstArc(arc); 
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
		return vexs[v].getData();
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
			if(vexs[v].getData().equals(vex))
				return v;
		}
		return -1;
	} 

	/**
	 * @description 查找第一个邻接点  若没有邻接点则返回-1
	 * @param v
	 * @return
	 * @author liuquan
	 * @throws Exception 
	 * @date  2015年12月30日
	 */
	public int firstAdjVex(int v) throws Exception { 
		if(v < 0 && v >= vexNum)
			throw new Exception("第" + v + "个顶点不存在");
		
		VNode vex = vexs[v]; 
		if(vex.getFirstArc() != null)
			return vex.getFirstArc().getAdjVex();
		else 
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
		
		VNode vex = vexs[v];
		ArcNode arcvw = null;
		for(ArcNode arc = vex.getFirstArc(); arc != null; arc = arc.getNextArc()){
			if(arc.getAdjVex() == w){
				arcvw = arc;
				break;
			}
		}
		if(arcvw != null && arcvw.getNextArc() != null)
			return arcvw.getNextArc().getAdjVex();
		else
			return -1;
	}	
	
	public void display() throws Exception{
		for(int i = 0; i < vexNum; i++){
			VNode vex = vexs[i];
			System.out.print(vex.getData());
			for(ArcNode arc = vex.getFirstArc(); arc != null; arc = arc.getNextArc()){
				System.out.print("->" + getVex(arc.getAdjVex()) +  arc.getValue());
			}
			System.out.println();
		}
	} 
	
	public static void main(String[] args) throws Exception {
		ALGraph a = new ALGraph();
		a.createDN();
		a.display();
	}
}
