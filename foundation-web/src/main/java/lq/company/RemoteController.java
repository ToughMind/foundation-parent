package lq.company;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lq.common.utils.controller.BaseAjaxController;

/**
 * 配合公司项目远程调用获取测试数据。
 * 
 * @author 刘泉
 * @data 2016年10月26日 下午3:31:46
 */
@Controller
@RequestMapping("/remote")
public class RemoteController extends BaseAjaxController {

	/**
	 * 获取优惠券个性化推荐的数据。
	 * 
	 * @author 刘泉
	 * @data 2016年10月26日 下午3:33:30
	 */
	@RequestMapping(value = "couponRcmd", produces = "application/json")
	@ResponseBody
	public String couponRcmd(String email, String cookie, String ip) {
		super.logger.info(" ===> [op: couponRcmd] Controller: remote for coupon rcmd data. email={}, cookie={}, ip={}",
				email, cookie, ip);
		return "{\"status\":\"200\",\"resultList\":[1,2,3],\"ipResultList\":[1,2,3],\"rcmdVer\":\"1.0.0\"}";
	}

	/**
	 * 获取支付个性化推荐的数据。
	 * 
	 * @author 刘泉
	 * @data 2016年10月26日 下午3:33:30
	 */
	@RequestMapping("payRcmd")
	public String payRcmd(List<Long> itemIds, String email, String ip) {
		super.logger.info(" ===> [op: orderRcmd] Controller: remote for pay rcmd data. itemIds={}, email={}, ip={}",
				itemIds, email, ip);
		return "{\"status\":\"200\",\"resultList\":[1,2,3],\"ipResultList\":[1,2,3],\"rcmdVer\":\"1.0.0\"}";
	}
}
