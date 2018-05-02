package com.cxg.productionorder.activity.provider;

import android.content.Context;

/**
 * Description: data provide factory
 * author: xg.chen
 * time: 2018/4/20
 * version: 1.0
 */

public class DataProviderFactory {

    public static Context ctx;
    public static Context getContext() {
        return ctx;
    }
    public static void setContext(Context ctx) {
        DataProviderFactory.ctx = ctx;
    }
    public static IDataProvider getProvider() {
        return WebService.getInstance();
    }

    public static IDataProvider getProvider(Context ctx) {
        DataProviderFactory.ctx = ctx;
        return getProvider();
    }

}
