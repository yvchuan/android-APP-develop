package com.example.cdmuseum.db;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.cdmuseum.bean.Shoucang;

import java.util.ArrayList;
import java.util.List;

public class YuyueDbUtils {
    public static final String DB_NAME = "yuyue_dbname";
    public static final int VERSION = 1;
    private static YuyueDbUtils sqliteDB;
    private SQLiteDatabase db;

    private YuyueDbUtils(Context context) {
        YuyueHelper yuyueHelper = new YuyueHelper(context, DB_NAME, null, VERSION);
        db = yuyueHelper.getWritableDatabase();
    }

    public synchronized static YuyueDbUtils getInstance(Context context) {
        if (sqliteDB == null) {
            sqliteDB = new YuyueDbUtils(context);
        }
        return sqliteDB;
    }

    public void delete(Context context, String id) {
        YuyueHelper yuyueHelper = new YuyueHelper(context, DB_NAME, null, VERSION);
        db = yuyueHelper.getReadableDatabase();
        db.delete("yuyue", "id=?", new String[]{id});
    }

    public void change(Context context, Shoucang gouwu) {
        YuyueHelper yuyueHelper = new YuyueHelper(context, DB_NAME, null, VERSION);
        db = yuyueHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id", gouwu.getId());
        values.put("chanpinname", gouwu.getChanpinname());
        values.put("chanpinprice", gouwu.getChanpinprice());
        values.put("path", gouwu.getPath());
        values.put("xiangqing", gouwu.getXiangqing());
        db.update("Yuyue", values, "id=?", new String[]{gouwu.getId() + ""});
    }

    public int insert(Shoucang gouwu) {
        try {
            db.execSQL("insert into Yuyue (chanpinname,chanpinprice,path,xiangqing) values(?,?,?,?) ", new String[]{
                    gouwu.getChanpinname(),
                    gouwu.getChanpinprice(),
                    gouwu.getPath(),
                    gouwu.getXiangqing()});
            return 0;
        } catch (Exception e) {
            Log.d("����", e.getMessage().toString());
            return -1;
        }
    }

    public List<Shoucang> load() {
        List<Shoucang> list = new ArrayList<>();
        Cursor cursor = db
                .query("Yuyue", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                Shoucang gouwu = new Shoucang();
                gouwu.setId(cursor.getInt(cursor.getColumnIndex("id")));
                gouwu.setChanpinname(cursor.getString(cursor.getColumnIndex("chanpinname")));
                gouwu.setChanpinprice(cursor.getString(cursor.getColumnIndex("chanpinprice")));
                gouwu.setPath(cursor.getString(cursor.getColumnIndex("path")));
                gouwu.setXiangqing(cursor.getString(cursor.getColumnIndex("xiangqing")));
                list.add(gouwu);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }
}
