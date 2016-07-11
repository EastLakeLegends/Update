package com.cnhubei.systemupdate;

import android.app.Activity;
import android.text.TextUtils;

/**
 * Created by mxp on 2016/5/23.
 */
public class VersionManager {
    public static void VersionCheck(Activity context, VersionBean bean) {
        if (context == null || context.isFinishing()) {
            return;
        }
        //当前版本为最新版本
        if (bean == null || TextUtils.isEmpty(bean.content) || TextUtils.isEmpty(bean.url)||TextUtils.isEmpty(bean.versionname)) {
            VersionPreferences.getInstance().clearPerferences();
            return;
        }

        if(!bean.url.toLowerCase().startsWith("http")){
            bean.url = "http://"+bean.url;
        }
        //初始化变量环境
        DownLoad.init(context);
        VersionPreferences.init(context.getApplication());

        //已忽略该版本
        if (VersionPreferences.getInstance().getIgnore(bean.versionname)&&!bean.isCheckUpdater()) {//如果忽略掉该版本，则不提示更新
            return;
        }

        VersionShow.showAppVersionCheckDialog(context, bean);
    }

}
