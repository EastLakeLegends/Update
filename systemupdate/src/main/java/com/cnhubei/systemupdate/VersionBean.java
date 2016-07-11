package com.cnhubei.systemupdate;

import java.io.Serializable;

/**
 * 版本更新数据
 * 在线升级模块功能：
 * 1.显示新版本号。
 * 2.显示新版本特性。
 * 3.可以忽略更新。
 * 4.可以配置强制更新。
 * 5. 支持断点续传。
 */
public class VersionBean implements Serializable {
    public String versionname;//版本号，用已忽略版本号使用
    public String url;//下载地址
    public int filesize = 0;//文件大小，精确到byte  暂时不用
    public int force = 0;//0非强制升级，1强制升级
    public String content;//新版本描述

    private boolean isCheckUpdater = false;//是否是检查更新



    public VersionBean(String versionname, String url, String content, int force, int filesize) {
        this.versionname = versionname;
        this.url = url;
        this.content = content;
        this.force = force;
        this.filesize = filesize;
    }

    public boolean isCheckUpdater() {
        return isCheckUpdater;
    }
    public void setCheckUpdater(boolean checkUpdater) {
        isCheckUpdater = checkUpdater;
    }

    //是否强制升级
    public boolean isForce() {
        return force == 1;
    }

    public static VersionBean getTestVersion() {
        String versionname = "V 1.1.0";//版本号，用已忽略版本号使用

        String url = "http://shouji.360tpcdn.com/160420/73153eaf51bbf128d9b7feafec46072f/com.kugou.android_8066.apk";//下载地址
        int filesize = 0;//文件大小，精确到byte  暂时不用
        int force = 0;//0非强制升级，1强制升级
        String content = "【 酷 狗 8.0全新上线 】\n" +
                "在过去的1572万秒，我们每 一秒的努力造就 了酷狗音乐8.0。她蜕下了旧装，换上了新颜，带着全新的操作体验、专业的音效品质、新颖的音乐社交，再次焕然一新地来到了你的面前。始终不变的，只有音乐，和初心。\n";//新版本描述

        VersionBean bean = new VersionBean(versionname, url, content, force, filesize);
        return bean;
    }

}
