package com.cxg.productionorder.activity.provider;

import android.app.Activity;

import com.cxg.productionorder.activity.pojo.ZpoGminfo;
import com.cxg.productionorder.activity.pojo.ZpoGsmvt;
import com.cxg.productionorder.activity.pojo.Ztwm004;

import java.util.List;
import java.util.Map;

/**
 * Description: app服务获取数据之公共接口
 * author: xg.chen
 * time: 2018/4/19
 * version: 1.0
 */

public interface IDataProvider {

    /*更新定时任务*/
    void startDateUpdateTasks(Activity activity);

    /*根据扫入的条形码获取生产订单信息*/
    List<Object> getOrderTask(String string);

    /*获取单位*/
    Map<String,String> getMeins();

    /*确认入库订单*/
    List<Object> creatProductionOrder(ZpoGminfo zpoGminfo, ZpoGsmvt zpoGsmvt, Ztwm004 ztwm004, String ivMtart, String ivRaube);
}
