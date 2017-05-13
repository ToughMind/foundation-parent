package lq.record.simple.mvc.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lq.record.simple.mvc.entity.Action;

public class ListString implements Action {

    public String execute(HttpServletRequest request,
        HttpServletResponse response) {

        System.out.println("执行了ListString");

        List listString = new ArrayList();
        for (int i = 0; i < 10; i++) {
            listString.add("goodboy" + (i + 1));
        }
        request.setAttribute("listString", listString);

        return "toShow";
    }
}
