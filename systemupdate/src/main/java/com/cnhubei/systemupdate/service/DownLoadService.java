package com.cnhubei.systemupdate.service;

import android.app.Activity;
import android.app.DownloadManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Environment;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.io.File;


/**
 * Created mxp fy on 2016/5/30.
 */
public class DownLoadService extends Service {

    DownloadManager downManager ;

    private DownLoadCompleteReceiver receiver;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //调用系统下载更新，下载成功后接受安装广播
        IntentFilter filter = new IntentFilter();
        filter.addAction(DownloadManager.ACTION_DOWNLOAD_COMPLETE);
        receiver = new DownLoadCompleteReceiver();
        registerReceiver(receiver, filter);

        String url = intent.getStringExtra("url");
        startDownload(url);
        return Service.START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }

    private void startDownload(String url) {
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        //设置在什么网络情况下进行下载
        request.setAllowedNetworkTypes(request.NETWORK_WIFI);
        //设置通知栏标题
        request.setNotificationVisibility(request.VISIBILITY_VISIBLE);
        request.setTitle("动向新闻海外版下载");
        request.setMimeType("application/vnd.android.package-archive");
        request.setAllowedOverRoaming(false);

        saveSimpleCacheObject(url);
        String path = Environment.DIRECTORY_DOWNLOADS;
        String app = url.substring(url.lastIndexOf("/") + 1);

        File file = new File(path, app);
        try{
            //修改data/data目录权限
            if(file.getAbsolutePath().indexOf("data/data/")>-1){
                String[] command = {"chmod", "755", file.getAbsolutePath()};
                ProcessBuilder builder = new ProcessBuilder(command);
                builder.start();
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        //设置文件存放目录
        request.setDestinationInExternalPublicDir(path, app);
        downManager = (DownloadManager)this.getSystemService(Context.DOWNLOAD_SERVICE);
        downManager.enqueue(request);
    }

    private void saveSimpleCacheObject(String url){
        SharedPreferences sf = getSharedPreferences("image", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sf.edit();
        editor.putString("url", url);
        editor.commit();
    }
}
