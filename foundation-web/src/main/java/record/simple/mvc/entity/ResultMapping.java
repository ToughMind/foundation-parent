package record.simple.mvc.entity;

/**
 * 匹配xml配置文件中的result标签
 * 
 * @author 刘泉
 * @date 2016年8月4日 下午7:43:18
 */
public class ResultMapping {

	private String resultName;
	private String isRedirect = "false";
	private String resultPath;

	public String getResultName() {
		return resultName;
	}

	public void setResultName(String resultName) {
		this.resultName = resultName;
	}

	public String getIsRedirect() {
		return isRedirect;
	}

	public void setIsRedirect(String isRedirect) {
		this.isRedirect = isRedirect;
	}

	public String getResultPath() {
		return resultPath;
	}

	public void setResultPath(String resultPath) {
		this.resultPath = resultPath;
	}

}