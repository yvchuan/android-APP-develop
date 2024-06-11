package com.example.cdmuseum.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 预约数据表
 */
public class YuyueHelper extends SQLiteOpenHelper {

    public static final String CREATE_USER = "create table Yuyue("
            + "id integer primary key autoincrement,"
            + "chanpinname text,"
            + "chanpinprice text,"
            + "path text,"
            + "xiangqing text)";

    public YuyueHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
// TODO Auto-generated method stub
        db.execSQL(CREATE_USER);//�����û���
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
