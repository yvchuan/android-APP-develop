package com.example.cdmuseum.db;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 中药材数据库
 */
public class MedicHelper extends SQLiteOpenHelper {

    public static final String CREATE_USER = "create table Medic("
            + "id integer primary key autoincrement, "
            + "caiming text, "
            + "path text, "
            + "price text, "
            + "jieshao text)";


    public MedicHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
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
