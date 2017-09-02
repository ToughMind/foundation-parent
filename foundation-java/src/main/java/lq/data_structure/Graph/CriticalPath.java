package lq.data_structure.Graph;


import lq.data_structure.Stack.LinkStack;

/**
 * @Description AOE网的关键路径 
 * @author liuquan
 * @time 2016年1月2日 下午10:24:51
 */
public class CriticalPath {
	
	private LinkStack T = new LinkStack(); //拓扑排序的顶点栈
	private int[] ve, vl; //各顶点的最早发生时间和最迟发生时间
	
	/**
     * @description 求各个顶点入度的算法 
     * @param G
     * @return
     * @throws Exception
     * @time 2016年1月1日 下午4:02:49
     */
    public  int[] findInDegree(ALGraph G) throws Exception{
         int[] indegree  = new int[G.getVexNum()];
         for(int i = 0; i < G.getVexNum(); i++)
             for(ArcNode arc = G.getVexs()[i].getFirstArc(); arc != null; arc = arc.getNextArc())
                 ++ indegree[arc.getAdjVex()]; //入度增1

         return indegree; 
    }

    /**
     * @description 求各个顶点的最早发生时间ve，若G无回路，用栈T存放一个拓扑排序，且函数返回true
     * @param G
     * @return
     * @throws Exception
     * @time 2016年1月1日 下午4:13:04
     */
    public  boolean topologicalOrder(ALGraph G) throws Exception{
        int count = 0; //输出顶点计数
        int[] indegree = findInDegree(G); //求各个顶点的入度
        LinkStack S = new LinkStack();  //零入度的 顶点栈
        for(int i = 0; i < G.getVexNum(); i++)
            if(indegree[i] == 0)
                S.push(i);  //入度为0的进栈
        this.ve = new int[G.getVexNum()]; //初始化
        
        while( !S.isEmpty()){
            int i = (Integer) S.pop();
            this.T.push(i); //i号顶点入T栈并计数 
            ++count;
            for(ArcNode arc = G.getVexs()[i].getFirstArc(); arc != null; arc = arc.getNextArc()){
                int k = arc.getAdjVex();
                if(--indegree[k] == 0) // 对k号顶点的每个邻接点的入度减1
                    S.push(k);
                if(ve[i] + arc.getValue() > ve[k])
                	ve[k] = ve[i] + arc.getValue();
            } 
        }
        if(count < G.getVexNum())
            return false;  //该有向图有回路
        else 
            return true;
    }
    
    /**
     * @description 输出G的各项关键活动  
     * @param G
     * @return
     * @throws Exception
     * @time 2016年1月2日 下午10:53:20
     */
    public boolean criticalPath(ALGraph G) throws Exception{
    	if( !topologicalOrder(G))
    		return false;
    	 vl = new int[G.getVexNum()];
    	 // 初始化各顶点事件的最迟发生时间
    	 for(int i = 0; i < G.getVexNum(); i++){
    		 vl[i] = ve[G.getVexNum() - 1]; 
    	 }
    	 while( !T.isEmpty()){ // 按拓扑序列 逆序 求各顶点的vl值
    		 int i = (Integer) T.pop();
    		 for(ArcNode arc = G.getVexs()[i].getFirstArc(); arc != null; arc = arc.getNextArc()){
    			 int k = arc.getAdjVex();
    			 int value = arc.getValue();
    			 if(vl[k] - value < vl[i])
    				 vl[i] = vl[k] - value;
    		 }
    	 }
    	 
    	 for(int i = 0; i < G.getVexNum(); i++){
    		 //求ee，el和关键路径
    		 for(ArcNode arc = G.getVexs()[i].getFirstArc(); arc != null; arc = arc.getNextArc()){
    			 int k = arc.getAdjVex();
    			 int value = arc.getValue();
    			 int ee = ve[i];
    			 int el = vl[k] - value;  
    			 System.out.println(G.getVex(i) + "->" + G.getVex(k) + "\t最早开始时间：" + ee + "，最迟开始时间："+ el +"。" + (ee == el ? "YES" : "NO"));
    		 }
    	 } 
    	 return true;
    } 
    
    public static void main(String[] args) throws Exception {
		ArcNode v01 = new ArcNode(1, 6);
		ArcNode v02 = new ArcNode(2, 4, v01);
		ArcNode v03 = new ArcNode(3, 5, v02);
		VNode v0 = new VNode("v0", v03);
		
		ArcNode v14 = new ArcNode(4, 1);
		VNode v1 = new VNode("v1", v14);
		
		ArcNode v24 = new ArcNode(4, 1);
		VNode v2 = new VNode("v2", v24);
		
		ArcNode v35 = new ArcNode(5, 2);
		VNode v3 = new VNode("v3", v35);
		
		ArcNode v46 = new ArcNode(6, 9);
		ArcNode v47 = new ArcNode(7, 7, v46);
		VNode v4 = new VNode("v4", v47);
		
		ArcNode v57 = new ArcNode(7, 4);
		VNode v5 = new VNode("v5", v57);
		
		ArcNode v68 = new ArcNode(8, 2);
		VNode v6 = new VNode("v6", v68);
		
		ArcNode v78 = new ArcNode(8, 4);
		VNode v7 = new VNode("v7", v78);
		
		VNode v8 = new VNode("v8");
		
		VNode[] vexs = {v0, v1, v2, v3, v4, v5, v6, v7, v8};
		ALGraph G = new ALGraph(GraphKind.DG, 9, 11, vexs);
		new CriticalPath().criticalPath(G);   
	} 
}
