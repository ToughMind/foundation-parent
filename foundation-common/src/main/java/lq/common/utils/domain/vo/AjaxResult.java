package lq.common.utils.domain.vo;

/**
 * 页面交互响应对象：AJAX请求返回数据的统一封装类。
 * 
 * @author 刘泉
 * @date 2016年10月13日 下午8:59:10
 */
public class AjaxResult extends JsonResult {

	private static final long serialVersionUID = 5451316194035153776L;

	public AjaxResult() {
		super();
	}

	public AjaxResult(ResponseCode responseCode) {
		super(responseCode);
	}

	public AjaxResult(ResponseCode responseCode, String errorMsg) {
		super(responseCode, errorMsg);
	}

}
