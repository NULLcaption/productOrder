package com.cxg.productionorder.activity.provider;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.cxg.productionorder.activity.pojo.ZpoGminfo;
import com.cxg.productionorder.activity.pojo.ZpoGsmvt;
import com.cxg.productionorder.activity.pojo.Ztwm004;
import com.cxg.productionorder.activity.utils.Helpers;
import com.cxg.productionorder.activity.utils.WebserviceUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description: 通过webservice获取数据之接口
 * author: xg.chen
 * time: 2018/4/19
 * version: 1.0
 */

public class WebService implements IDataProvider {

    private static WebService instance;
    private static final int TIME_OUT = 30000;
    private static final String TAG = "WebService";

    private WebService() {
        super();
    }

    public static IDataProvider getInstance() {
        if (instance == null) instance = new WebService();
        return instance;
    }

    @Override
    public void startDateUpdateTasks(Activity activity) {
        Context ctx = DataProviderFactory.getContext();
        if (ctx != null) {
            SharedPreferences sp = ctx.getSharedPreferences("XPPWebService", Context.MODE_PRIVATE);

            if (sp.contains("lastUpdate")) {
                Date now = new Date();
                String str1 = sp.getString("lastUpdate", "");
                String str2 = Helpers.getDateStrWithoutTime(now);
                if (str1.startsWith(str2)) {
                    Log.d(TAG, "No updates needed at this time.");
                    return;
                }
            }
        }
    }

    @Override
    public List<Object> getOrderTask(String zipcode) {
        try{
            List<Object> list = WebserviceUtils.callWebService001(WebserviceUtils.URL_001, WebserviceUtils.METHOD_NAME_001, zipcode);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Map<String, String> getMeins() {
        Map<String,String> map = new HashMap<>();
        try {
            map = WebserviceUtils.callWebServiceFor002(WebserviceUtils.URL_002, WebserviceUtils.METHOD_NAME_002);
            return map;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    @Override
    public List<Object> creatProductionOrder(ZpoGminfo zpoGminfo, ZpoGsmvt zpoGsmvt, Ztwm004 ztwm004, String ivMtart, String ivRaube) {
        try{
            List<Object> list = WebserviceUtils.callWebService003(WebserviceUtils.URL_003, WebserviceUtils.METHOD_NAME_003, zpoGminfo, zpoGsmvt, ztwm004, ivMtart, ivRaube);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
