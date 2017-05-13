package lq.record.simple.mvc.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lq.record.simple.mvc.entity.Action;

public class ShowList implements Action {
    public String execute(HttpServletRequest request,
        HttpServletResponse response) {

        System.out.println("执行了ShowList");

        List userinfoList = new ArrayList();
        for (int i = 0; i < 5; i++) {
            userinfoList.add("good gril" + (i + 1));
        }
        request.setAttribute("userinfoList", userinfoList);

        return "toShow";
    }

}
