package record.simple.mvc.entity;

import java.util.Map;

/**
 * 匹配xml配置文件中的<action标签>，包括了action名、类名、result集
 * 
 * @author 刘泉
 * @date 2016年8月4日 下午7:42:21
 */
public class ActionMapping {

	private String actionName;
	private String className;
	private Map<String, ResultMapping> resultMapping;

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public Map<String, ResultMapping> getResultMapping() {
		return resultMapping;
	}

	public void setResultMapping(Map<String, ResultMapping> resultMapping) {
		this.resultMapping = resultMapping;
	}

}