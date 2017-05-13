package lq.test.urlmapper;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lq.common.utils.controller.BaseAjaxController;
import lq.common.utils.domain.vo.AjaxResult;
import lq.web.domain.vo.UserVO;
import lq.web.service.UserWebService;

/**
 * 测试各种URL路径映射。
 * 
 * @author 刘泉
 * @date 2016年10月13日 下午5:13:07
 */
@Controller
@RequestMapping("/url")
public class UrlMapperController extends BaseAjaxController {

	@Resource
	private UserWebService userWebService;

	/**
	 * 普通的URL路径映射。 也可写成 @RequestMapping(value = {"/user","/user/id"})。
	 * 其匹配力度大于URI模板模式。
	 * <ul>
	 * 匹配用例:
	 * </ul>
	 * <li>localhost:9080/url/user</li>
	 * <li>localhost:9080/url/user.josn</li>
	 * <li>localhost:9080/url/user/id</li>
	 * <li>localhost:9080/url/user/id.josn</li>
	 * <li>localhost:9080/url/user/</li>
	 * <li>localhost:9080/url/user//</li>
	 * 
	 * @author 刘泉
	 * @date 2016年10月13日 下午5:18:14
	 */
	@RequestMapping({ "/user", "/user/id" })
	public AjaxResult fun1(HttpServletRequest request) {
		super.logger.info(" ===> [op: mapper] Controller: 普通匹配，多个路径匹配到同一个控制器.");
		UserVO vo = userWebService.getById(1);
		request.setAttribute("user", vo);
		return initFailureResult("多个路径匹配到同一个控制器", vo);
	}

	/**
	 * URI模板模式的映射。 匹配力度大于ANT风格映射。
	 * <ul>
	 * 匹配用例:
	 * </ul>
	 * <li>localhost:9080/url/user2.josn</li>
	 * <li>localhost:9080/url/user2/good boy.json</li>
	 * <li>localhost:9080/url/user2/id.josn</li>
	 * 
	 * @author 刘泉
	 * @date 2016年10月13日 下午5:18:14
	 */
	@RequestMapping("/user2/{str}")
	public AjaxResult fun2(HttpServletRequest request, @PathVariable(value = "str", required = false) String str) {
		super.logger.info(" ===> [op: mapper] Controller: URI模板模式的映射.");
		return initFailureResult(str);
	}

	/**
	 * ANT风格的URL路径映射。 /**可匹配多级目录，/*只能匹配一级目录。
	 * <ul>
	 * 匹配用例:
	 * </ul>
	 * <li>localhost:9080/url/user3.josn</li>
	 * <li>localhost:9080/url/user31.json</li>
	 * <li>localhost:9080/url/user3a.josn</li>
	 * <li>localhost:9080/url/user3aa.josn</li>
	 * <li>localhost:9080/url/user3/a.josn</li>
	 * 
	 * @author 刘泉
	 * @date 2016年10月13日 下午5:18:14
	 */
	@RequestMapping({ "/user3/**", "/user3?" })
	public AjaxResult fun3() {
		super.logger.info(" ===> [op: mapper] Controller: ANT风格的URL路径映射.");
		return initFailureResult("ANT风格的URL路径映射");
	}

	/**
	 * 正则表达式风格的URL路径映射。
	 * <ul>
	 * 匹配用例:
	 * </ul>
	 * <li>localhost:9080/url/user4.josn</li>
	 * <li>localhost:9080/url/user4/111.json</li>
	 * <li>localhost:9080/url/user4/111-222.josn</li>
	 * <li>localhost:9080/url/user4/aaa-222.josn</li>
	 * <li>localhost:9080/url/111.josn</li>
	 * <li>localhost:9080/url/aaa.josn</li>
	 * 
	 * @author 刘泉
	 * @date 2016年10月13日 下午5:18:14
	 */
	@RequestMapping("/user4/{code1:\\d+}-{code2:\\d+}")
	public AjaxResult fun4(@PathVariable(value = "code1", required = false) int code1,
			@PathVariable(value = "code2", required = false) int code2) {
		super.logger.info(" ===> [op: mapper] Controller: 正则表达式风格的URL路径映射.");
		return initFailureResult("code1=" + code1 + ", code2=" + code2);
	}

	/**
	 * 请求参数限定带有XX参数。
	 * <ul>
	 * 匹配用例:
	 * </ul>
	 * <li>localhost:9080/url/user5.josn</li>
	 * <li>localhost:9080/url/user5.json/create</li>
	 * <li>localhost:9080/url/user5.json?create</li>
	 * <li>localhost:9080/url/user5.json?create=1</li>
	 * 
	 * @author 刘泉
	 * @date 2016年10月13日 下午5:18:14
	 */
	@RequestMapping(value = "/user5", params = "create", method = RequestMethod.GET)
	public AjaxResult fun5() {
		super.logger.info(" ===> [op: mapper] Controller: 请求参数数据限定.");
		return initFailureResult("请求参数数据限定，参数必须带create");
	}

	/**
	 * 请求参数限定不带有XX参数。
	 * <ul>
	 * 匹配用例:
	 * </ul>
	 * <li>localhost:9080/url/user6.josn</li>
	 * <li>localhost:9080/url/user6.json/create</li>
	 * <li>localhost:9080/url/user6.json?aaa</li>
	 * <li>localhost:9080/url/user6.json?aaa=1</li>
	 * 
	 * @author 刘泉
	 * @date 2016年10月13日 下午5:18:14
	 */
	@RequestMapping(value = "/user6", params = "!create", method = RequestMethod.GET)
	public AjaxResult fun6() {
		super.logger.info(" ===> [op: mapper] Controller: 请求参数限定不带有XX参数.");
		return initFailureResult("请求参数数据限定，参数不可以带有create");
	}

	/**
	 * 请求参数限定带有XX参数且对值也有要求。 params={"flag=create", "flag!=delete"}是“且”的关系。
	 * <ul>
	 * 匹配用例:
	 * </ul>
	 * <li>localhost:9080/url/user6.josn</li>
	 * <li>localhost:9080/url/user6.json?create</li>
	 * <li>localhost:9080/url/user6.json?flag=create</li>
	 * <li>localhost:9080/url/user6.json?flag=delete</li>
	 * 
	 * @author 刘泉
	 * @date 2016年10月13日 下午5:18:14
	 */
	@RequestMapping(value = "/user7", params = { "flag=create", "flag!=delete" }, method = RequestMethod.GET)
	public AjaxResult fun7() {
		super.logger.info(" ===> [op: mapper] Controller: 请求参数限定带有XX参数且对值也有要求.");
		return initFailureResult("请求参数数据限定，参数带有flg且值为create");
	}

	/**
	 * 请求头限定。 params={"flag=create", "flag!=delete"}是“且”的关系。
	 * 匹配请求头中包含Accept、Content-Type，Accept=text/*且Content-Type=application/json的路径。
	 * 
	 * 
	 * @author 刘泉
	 * @date 2016年10月13日 下午5:18:14
	 */
	@RequestMapping(value = "/user8", headers = { "Accept=*/*",
			"Content-Type=application/json" }, method = RequestMethod.GET)
	public AjaxResult fun8() {
		super.logger.info(" ===> [op: mapper] Controller: 请求头限定.");
		return initFailureResult("匹配请求头中包含Accept、Content-Type，Accept=text/*且Content-Type=application/json的路径");
	}

	@RequestMapping("/user9")
	public AjaxResult fun9(@RequestBody UserVO userVO, long id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userVO", userVO);
		map.put("id", id);
		return initFailureResult();
	}
}
