package com.cnhubei.systemupdate.download.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.cnhubei.systemupdate.download.bean.DownloadBean;


public class DownloadDBManager {
    private static Context context;

    public static void init(Context mContext) {
        context = mContext;
    }

    private static DownloadDBManager instance;

    private SQLiteDatabase db;


//    private DownloadDBManager() {
//        DBOpenHelper openHelper = new DBOpenHelper(context);
//        db = openHelper.getWritableDatabase();
//    }

    public static DownloadDBManager getDb() {
        if (instance == null) {
            synchronized (DownloadDBManager.class) {
                if (instance == null) {
                    instance = new DownloadDBManager();
                }
            }
        }
        return instance;
    }

    /**
     * 当文件下载完成后，删除对应的下载记录
     *
     * @param downloadurl
     */
    public void delete(String downloadurl) {
        db.execSQL("delete from downloaditem where downloadurl=?", new Object[]{downloadurl});
    }


    public void insertOrReplaceDownloadBean(DownloadBean bean) {
        String sql = "REPLACE INTO " + DBOpenHelper.TABLE_NAME_DOWNLOADITEM + "( downloadurl,filepath,filename,size,offsize,downloadstatus ) VALUES ('" +
                bean.downloadUrl + "','" + bean.saveDirPath + "','" + bean.fileName + "'," + bean.size + "," + bean.offsize + "," + bean.downladstatus +
                " )";
//        Log.i("xx","sql:"+sql);
        db.execSQL(sql);
    }

    public DownloadBean getDownloadTask(String downloadurl) {
        DownloadBean bean = null;
        try {
            Cursor cur = db.rawQuery("select * from " + DBOpenHelper.TABLE_NAME_DOWNLOADITEM + " where downloadurl = ?", new String[]{downloadurl});
            while (cur.moveToNext()) {
                bean = new DownloadBean();
                bean.saveDirPath = cur.getString(cur.getColumnIndex("filepath"));
                bean.fileName = cur.getString(cur.getColumnIndex("filename"));
                bean.size = cur.getInt(cur.getColumnIndex("size"));
                bean.offsize = cur.getInt(cur.getColumnIndex("offsize"));
                bean.downladstatus = cur.getInt(cur.getColumnIndex("downloadstatus"));
                bean.downloadUrl = cur.getString(cur.getColumnIndex("downloadurl"));
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return bean;
    }
}

