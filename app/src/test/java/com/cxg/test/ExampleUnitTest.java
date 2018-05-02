package com.cxg.test;


import com.cxg.productionorder.activity.pojo.ZpoGminfo;
import com.cxg.productionorder.activity.pojo.ZpoGsmvt;
import com.cxg.productionorder.activity.pojo.Ztwm004;
import com.cxg.productionorder.activity.utils.WebserviceUtils;

import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description: 单元测试模块
 * author: xg.chen
 * time: 2018/4/24
 * version: 1.0
 */

public class ExampleUnitTest {

    @Test
    public void init1() {
        String IvZipcode = "1000633419";
        List<Object> list = WebserviceUtils.callWebService001(WebserviceUtils.URL_001, WebserviceUtils.METHOD_NAME_001, IvZipcode);
        if (list!=null) {
            ZpoGminfo zpoGminfo = (ZpoGminfo) list.get(0);
            System.out.println(zpoGminfo);
            ZpoGsmvt zpoGsmvt = (ZpoGsmvt) list.get(1);
            System.out.println(zpoGsmvt);
            Ztwm004 ztwm004 = (Ztwm004) list.get(2);
            System.out.println(ztwm004);
            System.out.println(list.get(3));
            System.out.println(list.get(4));
            System.out.println(list.get(5));
            System.out.println(list.get(6));
        }


    }

    /**
     * Description: 测试获取单位
     * author: xg.chen
     * time: 2018/4/24
     * version: 1.0
     */
    @Test
    public void init2(){
        Map<String,String> map = WebserviceUtils.callWebServiceFor002(WebserviceUtils.URL_002, WebserviceUtils.METHOD_NAME_002);
        System.out.println(map);
    }

}