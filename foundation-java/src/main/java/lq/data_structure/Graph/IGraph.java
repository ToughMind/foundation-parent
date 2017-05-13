package lq.data_structure.Graph;

/**
 * @description 图的抽象数据类型描述
 * 
 * @author liuquan
 * @date  2015年12月30日
 */
public interface IGraph {
	
	public void createGraph();
	/**
	 * @description 返回图中顶点数
	 * @return
	 * @author liuquan
	 * @date  2015年12月30日
	 */
	public int getVexNum();  
	
	/**
	 * @description 返回图中边数
	 * @return
	 * @author liuquan
	 * @date  2015年12月30日
	 */
	public int getArcNum() ;
	
	/**
	 * @description 给定位置v，返回其对应的顶点值
	 * @param v
	 * @return
	 * @author liuquan
	 * @throws Exception 
	 * @date  2015年12月30日
	 */
	public Object getVex(int v) throws Exception;  
	
	/**
	 * @description 给定顶点的值vex，返回其在图中的位置 
	 * @param vex
	 * @return
	 * @author liuquan
	 * @date  2015年12月30日
	 */
	public int locateVex(Object vex);
	
	/**
	 * @description 返回v的第一个邻接点 
	 * @param v
	 * @return
	 * @author liuquan
	 * @throws Exception 
	 * @date  2015年12月30日
	 */
	public int firstAdjVex(int v) throws Exception;
	
	/**
	 * @description 返回v相对于w的下一个邻接点 
	 * @param v
	 * @param w
	 * @return
	 * @author liuquan
	 * @throws Exception 
	 * @date  2015年12月30日
	 */
	public int nextAdjVex(int v, int w) throws Exception;
	
}
