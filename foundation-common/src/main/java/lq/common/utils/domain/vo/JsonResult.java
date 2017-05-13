package lq.common.utils.domain.vo;

import java.util.LinkedHashMap;

/**
 * 公共基类：返回JSON格式数据的统一封装类。
 * 
 * @author 刘泉
 * @date 2016年10月13日 下午9:01:23
 */
public class JsonResult extends LinkedHashMap<String, Object> {

	private static final long serialVersionUID = 4004136263013658272L;

	/**
	 * 响应码，{@code ResponseCode}
	 */
	public static final String CODE = "code";

	/**
	 * 错误文案
	 */
	public static final String ERROR_MESSAGE = "errorMsg";

	/**
	 * 响应的数据
	 */
	public static final String DATA = "data";

	/**
	 * 额外信息
	 */
	public static final String OTHER = "other";

	public JsonResult() {
		setCode(ResponseCode.OK);
	}

	public JsonResult(ResponseCode responseCode) {
		setCode(responseCode);
	}

	public JsonResult(ResponseCode responseCode, String errorMsg) {
		setCode(responseCode);
		put(ERROR_MESSAGE, errorMsg);
	}

	public void setCode(ResponseCode responseCode) {
		put(CODE, responseCode.getCode());
	}

	public ResponseCode getCode() {
		return (ResponseCode) get(CODE);
	}

	public void setErrorMsg(String errorMsg) {
		put(ERROR_MESSAGE, errorMsg);
	}

	public String getErrorMsg() {
		return (String) get(ERROR_MESSAGE);
	}

	public void setData(Object data) {
		put(DATA, data);
	}

	public Object getData() {
		return (String) get(DATA);
	}

	public void setOther(Object other) {
		put(OTHER, other);
	}

	public Object getOther() {
		return (String) get(OTHER);
	}

}
