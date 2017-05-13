package record.simple.mvc.entity;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

/**
 * 控制器，将获取到的url拆分，然后通过xml配置文件匹配到标签，然后实例化类
 * 
 * @author 刘泉
 * @date 2016年8月4日 下午7:32:25
 */
public class ActionServlet extends HttpServlet {

    private final Logger logger = LoggerFactory.getLogger(ActionServlet.class);

    private ActionMappingManager actionMappingManager;

    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        ActionMapping actionMapping = actionMappingManager
            .getActionMapping(request);
        Action action = ActionManager.getAction(actionMapping.getClassName());
        String resultName = action.execute(request, response);
        ResultMapping resultMapping = actionMapping.getResultMapping()
            .get(resultName);
        if (resultMapping.getIsRedirect().equals("true")) {
            response.sendRedirect(resultMapping.getResultPath());
        } else {
            request.getRequestDispatcher(resultMapping.getResultPath().trim())
                .forward(request, response);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        this.doGet(request, response);
    }

    public void init() throws ServletException {
        System.out.println("ActionServlet init()无参");
        String configFileValue = this.getInitParameter("configFile");
        actionMappingManager = new ActionMappingManager(
            configFileValue.split(","));
    }

}
