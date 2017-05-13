package lq.test.log;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lq.common.utils.controller.BaseAjaxController;
import lq.common.utils.domain.vo.AjaxResult;

/**
 * 配合log4j配置文件进行测试。
 * 
 * @author 刘泉
 * @data 2016年10月17日 下午9:18:39
 */
@Controller
@RequestMapping("/log")
public class TestLog extends BaseAjaxController {

	@RequestMapping("/test")
	public AjaxResult fun1() {
		StringBuilder sb = new StringBuilder("good boy!");
		for (int i =1 ; i < 10 ; i ++) {
			sb.append(sb);
		}
		super.logger.debug(sb.toString());
		super.logger.info(sb.toString());
		super.logger.warn(sb.toString());
		super.logger.error(sb.toString());
		//Logger.getLogger(getClass()).log(TDTLevel.REMIND, sb.toString());
		return initFailureResult(sb.toString());
	}
}
