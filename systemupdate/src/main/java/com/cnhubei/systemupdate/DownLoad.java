package com.cnhubei.systemupdate;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;

import com.cnhubei.systemupdate.service.DownLoadService;

public class DownLoad {
    /**
     * 使用之前请先调用init方法初始化
     */
    private static DownLoad downLoad = null;

    private static Activity context;

    DownloadManager downManager ;

    public static void init(Activity context){
        DownLoad.context = context;
    }

    public static Context getContext() {
        return context;
    }

    public static DownLoad getDownLoad() {
        if (downLoad == null) {
            synchronized (DownloadManager.class) {
                if (downLoad == null) {
                    downLoad = new DownLoad();
                }
            }
        }
        return downLoad;
    }

    public void startServices(String url){
        Intent intent = new Intent(context, DownLoadService.class);
        intent.putExtra("url", url);
        context.startService(intent);
    }
}
