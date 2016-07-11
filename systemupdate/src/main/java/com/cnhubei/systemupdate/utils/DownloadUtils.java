package com.cnhubei.systemupdate.utils;

import android.content.Intent;
import android.net.Uri;

import com.cnhubei.systemupdate.DownLoad;

import java.io.File;

/**
 * Created by dy on 2016/5/3.
 */
public class DownloadUtils {
    /**
     * 安装应用，需要把异常抛出
     *
     * @param file
     * @throws Exception
     */
    public static void installApp(File file) throws Exception {
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

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        DownLoad.getContext().startActivity(intent);
    }

}
