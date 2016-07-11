package com.cnhubei.systemupdate.download.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 数据库操作类
 *
 * @author Administrator
 */
public class DBOpenHelper extends SQLiteOpenHelper {
    private static final String DBNAME = "down.db";
    private static final int VERSION = 4;

    public static final  String TABLE_NAME_DOWNLOADITEM = "downloaditem";

    /**
     * 构造器
     *
     * @param context
     */
    public DBOpenHelper(Context context) {
        super(context, DBNAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS "+TABLE_NAME_DOWNLOADITEM+" (downloadurl varchar(100) primary key, filepath varchar(100), filename varchar(100), size INTEGER,offsize INTEGER,downloadstatus INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME_DOWNLOADITEM);
        onCreate(db);
    }
}