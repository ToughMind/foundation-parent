package lq.record.simple.mvc.entity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 类似struts2的Acton接口
 * 
 * @author 刘泉
 * @date 2016年8月4日 下午7:22:33
 */
public interface Action {

	public String execute(HttpServletRequest request, HttpServletResponse response);

}