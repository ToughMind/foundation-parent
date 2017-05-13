package record.simple.mvc.entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * 管理xml配置文件映射的类，所有的解析结果都会封装到此类中
 * 
 * @author 刘泉
 * @date 2016年8月4日 下午7:44:32
 */
public class ActionMappingManager {

    private Map<String, ActionMapping> actionMappingMap = new HashMap<String, ActionMapping>();

    public ActionMappingManager(String[] configFileArray) {
        createActionMapping(configFileArray);
    }

    // 实例化此Manager对象的时候就已经将配置文件的信息转换成封装类，然后封装到此Manager对象的Map属性中
    private void createActionMapping(String[] configFileArray) {
        try {
            for (int i = 0; i < configFileArray.length; i++) {
                String configFile = configFileArray[i];

                SAXReader reader = new SAXReader();
                Document document = reader.read(
                    this.getClass().getResourceAsStream("../" + configFile));

                List<Element> actionElementList = document.getRootElement()
                    .element("actions").elements("action");
                for (int j = 0; j < actionElementList.size(); j++) {
                    ActionMapping actionMapping = new ActionMapping();
                    Element actionElement = actionElementList.get(j);
                    String actionValue = actionElement.attributeValue("name");
                    String classValue = actionElement.attributeValue("class");

                    actionMapping.setActionName(actionValue);
                    actionMapping.setClassName(classValue);

                    List<Element> resultElementList = actionElement
                        .elements("result");
                    if (resultElementList.size() > 0) {
                        actionMapping.setResultMapping(new HashMap());

                        for (int y = 0; y < resultElementList.size(); y++) {
                            ResultMapping resultMappping = new ResultMapping();

                            Element resultElement = resultElementList.get(y);
                            String resultName = resultElement
                                .attributeValue("name");
                            String resultPath = resultElement.getText().trim();

                            resultMappping.setResultName(resultName);
                            resultMappping.setResultPath(resultPath);
                            // 其实这里还要匹配字符串 如果是redirect则置为true
                            if (resultElement.attribute("type") != null) {
                                resultMappping.setIsRedirect("true");
                            }
                            actionMapping.getResultMapping().put(resultName,
                                resultMappping);
                        }

                    }
                    actionMappingMap.put(actionValue, actionMapping);
                }

            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    public ActionMapping getActionMapping(HttpServletRequest request) {
        String uri = request.getRequestURI();
        String contextPath = request.getContextPath();
        String actionPath = uri.substring(contextPath.length() + 1);
        String actionName = actionPath.substring(0, actionPath.indexOf("."));
        return actionMappingMap.get(actionName);
    }

}
