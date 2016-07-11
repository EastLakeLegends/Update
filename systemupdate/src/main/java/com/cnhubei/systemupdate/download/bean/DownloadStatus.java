package com.cnhubei.systemupdate.download.bean;

/**
 * Created by Administrator on 2016/3/4 10 10:10
 * 邮箱：dymh21342@163.com
 */
public class DownloadStatus {
    public static final int DOWNLOAD_STATUS_INIT = -1;//初始化状态，还未加入下载队列
    public static final int DOWNLOAD_STATUS_PREPARE = 0;//已经点了开始，等待中
    public static final int DOWNLOAD_STATUS_START= 1;//开始下载
    public static final int DOWNLOAD_STATUS_DOWNLOADING = 2;//下载中
    public static final int DOWNLOAD_STATUS_CANCEL = 3;//取消
    public static final int DOWNLOAD_STATUS_ERROR = 4;//下载出错
    public static final int DOWNLOAD_STATUS_COMPLETED = 5;//下载完成
    public static final int DOWNLOAD_STATUS_PAUSE = 6;//下载暂停
}
