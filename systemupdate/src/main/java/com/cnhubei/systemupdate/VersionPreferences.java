package com.cnhubei.systemupdate;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

public class VersionPreferences {
    private static Context context;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    static final String share_name_version = "version_share";

    public static void init(Context mContext) {
        context = mContext;
    }

    private VersionPreferences() {
        this.sharedPreferences = context.getSharedPreferences(share_name_version,
                Activity.MODE_PRIVATE);
        this.editor = sharedPreferences.edit();
    }

    private static VersionPreferences instance;

    public static VersionPreferences getInstance() {
        if (instance == null) {
            synchronized (VersionPreferences.class) {
                if (instance == null) {
                    instance = new VersionPreferences();
                }
            }
        }
        return instance;
    }


    //忽略该版本更新
    public Boolean getIgnore(String serviceVersionNmae) {
        boolean ignore = sharedPreferences.getBoolean("ignore", false);
        String ignoreVersionNmae = sharedPreferences.getString("ignoreVersionNmae","");
        if(ignore&&!TextUtils.isEmpty(ignoreVersionNmae)&&ignoreVersionNmae.equals(serviceVersionNmae)){
            return true;
        }
        return false;
    }
    public void setIgnore(Boolean isIgnore,String ignoreVersionNmae) {
        editor.putBoolean("ignore", isIgnore);
        editor.commit();

        editor.putString("ignoreVersionNmae", ignoreVersionNmae);
        editor.commit();
    }


    public void clearPerferences() {
        editor.clear().commit();
    }
}
