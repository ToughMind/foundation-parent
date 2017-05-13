package lq.common.utils.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import lq.common.utils.domain.vo.AjaxResult;
import lq.common.utils.domain.vo.ResponseCode;

/**
 * 抽象页面控制器基类：所有异步请求Controller的基类。
 * 
 * @author 刘泉
 * @date 2016年10月13日 下午8:53:18
 */
public abstract class BaseAjaxController {
	
	protected final Logger logger;
	
	public BaseAjaxController() {
		logger = LoggerFactory.getLogger(getClass());
	}	

	protected long getUserId(HttpServletRequest request) {
		return 0001;
	}

	protected String getUserName() {
		return "刘泉";
	}

	public AjaxResult initSuccessResult() {
		return new AjaxResult(ResponseCode.OK);
	}

	public AjaxResult initSuccessResult(Object data) {
		AjaxResult ajaxResult = new AjaxResult(ResponseCode.OK);
		if (data != null) {
			ajaxResult.setData(data);
		}
		return ajaxResult;
	}

	public AjaxResult initFailureResult() {
		return new AjaxResult(ResponseCode.FAIL);
	}

	public AjaxResult initFailureResult(String errorMsg) {
		AjaxResult ajaxResult = new AjaxResult(ResponseCode.FAIL);
		ajaxResult.setErrorMsg(errorMsg);
		return ajaxResult;
	}

	public AjaxResult initFailureResult(ResponseCode code, String errorMsg) {
		AjaxResult ajaxResult = new AjaxResult(code);
		ajaxResult.setErrorMsg(errorMsg);
		return ajaxResult;
	}

	public AjaxResult initFailureResult(String errorMsg, Object data) {
		AjaxResult ajaxResult = new AjaxResult(ResponseCode.FAIL);
		ajaxResult.setErrorMsg(errorMsg);
		if (data != null) {
			ajaxResult.setData(data);
		}
		return ajaxResult;
	}

	public AjaxResult initFailureResult(ResponseCode code, String errorMsg, Object data) {
		AjaxResult ajaxResult = new AjaxResult(code);
		ajaxResult.setErrorMsg(errorMsg);
		if (data != null) {
			ajaxResult.setData(data);
		}
		return ajaxResult;
	}

	public AjaxResult initResult(boolean result) {
		return initResult(result, null);
	}

	public AjaxResult initResult(boolean result, String errorMsg) {
		return initResult(result, errorMsg, null);
	}

	/**
	 * 看似与上面方法重复，其实不然，知识点在JVM。 若实参Sring，则匹配上面方法；若实参是除String之外的Object，则匹配此方法。
	 * 
	 * @author 刘泉
	 * @date 2016年10月13日 下午9:34:01
	 */
	public AjaxResult initResult(boolean result, Object data) {
		return initResult(result, null, data);
	}

	public AjaxResult initResult(boolean result, String errorMsg, Object data) {
		AjaxResult ajaxResult = null;
		if (result)
			ajaxResult = new AjaxResult(ResponseCode.OK);
		else
			ajaxResult = new AjaxResult(ResponseCode.FAIL);
		if (errorMsg != null)
			ajaxResult.setErrorMsg(errorMsg);
		if (data != null)
			ajaxResult.setData(data);
		return ajaxResult;
	}

	/**
	 * 异常处理
	 * 
	 * @author 刘泉
	 * @date 2016年10月13日 下午9:26:39
	 */
	@ExceptionHandler
	@ResponseBody
	public AjaxResult exceptionHandler(Exception e) {
		logger.error("Controller: exception happended.", e);
		return new AjaxResult(ResponseCode.FAIL);
	}

}
