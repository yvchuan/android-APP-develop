package com.example.cdmuseum.db;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 配方药数据表
 */
public class PeifangHelper extends SQLiteOpenHelper {

    public static final String CREATE_USER = "create table Peifang ("
            + "id integer primary key autoincrement, "
            + "chanpinname text,"
            + "chanpinprice text,"
            + "path text,"
            + "xiangqing text)";

    public PeifangHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
