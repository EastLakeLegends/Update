package com.cnhubei.systemupdate.download.bean;


/**
 * Created by dy on 2016/3/14 16 16:14
 * 邮箱：dymh21342@163.com
 * 每一个下载任务的下载属性
 */
public class DownloadBean {

    /**
     * 应用名称
     */
    public String name;


    //下载状态。add by dy 2016-03-04
    public int downladstatus = DownloadStatus.DOWNLOAD_STATUS_INIT;//默认状态
    /**
     * 应用来源链接URL
     */
    public String downloadUrl;
    /**
     * 应用包名
     */
    public String pakname;


    /**
     * 文件的大小
     * 字节数。在游戏Item，该字段为bytes
     * 默认0
     */
    public long size = 0;


    /**
     * 已下载大小
     */
    public long offsize = 0;


    //文件保存路径
    public String saveDirPath;

    //文件名
    public String fileName;    // File name when saving

    //错误码
    public String errorString;

    public String versionname;//本地版本号


    public String speed_Downling;


    public void setDownloadUrl(String downloadUrl){
        this.downloadUrl = downloadUrl;
    }

    public void setSaveDirPath(String saveDirPath){
        this.saveDirPath = saveDirPath;
    }

    public void setFileName(String fileName){
        this.fileName = fileName;
    }
    
}
