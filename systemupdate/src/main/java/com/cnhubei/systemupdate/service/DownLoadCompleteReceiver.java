package com.cnhubei.systemupdate.service;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Environment;
import android.widget.Toast;

import com.cnhubei.systemupdate.DownLoad;

import java.io.File;

/**
 * Created by fy on 2016/5/23.
 */
public class DownLoadCompleteReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals(DownloadManager.ACTION_DOWNLOAD_COMPLETE)){
            Toast.makeText(context, "下载完成！", Toast.LENGTH_SHORT).show();

            String url = readSimpleCacheObject(context);

            String app = url.substring(url.lastIndexOf("/") + 1);

            if (app != null && !app.equals("")){
                File file = new File(Environment.getExternalStorageDirectory() + "/Download/" + app);

                intent = new Intent(Intent.ACTION_VIEW);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
                DownLoad.getContext().startActivity(intent);
            }
        }else if(intent.getAction().equals(DownloadManager.ACTION_NOTIFICATION_CLICKED)){
            Toast.makeText(context, "正在下载，请勿点击", Toast.LENGTH_SHORT).show();
        }
    }

    private String readSimpleCacheObject(Context context){
        SharedPreferences sf = context.getSharedPreferences("image", Activity.MODE_PRIVATE);
        String url = sf.getString("url", "");
        return url;
    }
}
