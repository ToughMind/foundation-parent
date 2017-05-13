/**
 * @(#)JsonTest.java, 2017年3月16日.
 *
 * Copyright 2017 Netease, Inc. All rights reserved.
 * NETEASE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package record.test;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;

import lq.java.domain.vo.UserVO;

/**
 * @author 刘泉 hzliuquan1@corp.netease.com
 * @date 2017年3月16日 下午4:37:44
 */
public class JsonTest {
    public static void main(String[] args) {
//        UserVO u = new UserVO();
//        List<UserVO> l1 = new ArrayList<UserVO>();
//        List<UserVO> l2 = new ArrayList<>();
//        List l3 = new ArrayList<>();
//        for (int i = 0; i < 5; i++) {
//            l1.add(u);
//            l2.add(u);
//            l3.add(u);
//        }
//        System.out.println(JSON.toJSONString(l1));
//        System.out.println(JSON.toJSON(l1));
//        System.out.println(JSON.toJSONString(l2));
//        System.out.println(JSON.toJSON(l2));
//        System.out.println(JSON.toJSONString(l3));
//        System.out.println(JSON.toJSON(l3));
       List<Integer> list = new ArrayList<>();
       for(int i = 0; i < 40; i++){
           list.add(i);
       }
       //List l = list.subList(40, 39);
       //System.out.println(l.size());
       System.out.println(-1/12);
        System.out.printf("可以啊");
    }
}
