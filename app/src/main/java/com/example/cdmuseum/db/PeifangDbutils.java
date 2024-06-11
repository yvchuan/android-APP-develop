package com.example.cdmuseum.db;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.cdmuseum.bean.Peifang;

import java.util.ArrayList;
import java.util.List;

/**
 * 配方药增删改查
 */
public class PeifangDbutils {
    public static final String DB_NAME = "peifang_dbname";
    public static final int VERSION = 1;
    private static PeifangDbutils sqliteDB;
    private SQLiteDatabase db;

    private PeifangDbutils(Context context) {
        PeifangHelper peifangHelper = new PeifangHelper(context, DB_NAME, null, VERSION);
        db = peifangHelper.getWritableDatabase();
    }

    public synchronized static PeifangDbutils getInstance(Context context) {
        if (sqliteDB == null) {
            sqliteDB = new PeifangDbutils(context);
        }
        return sqliteDB;
    }

    public void delete(Context context, String id) {
        PeifangHelper peifangHelper = new PeifangHelper(context, DB_NAME, null, VERSION);
        db = peifangHelper.getReadableDatabase();
        db.delete("Peifang", "id=?", new String[]{id});
    }

    public void change(Context context, Peifang gouwu) {
        YuyueHelper yuyueHelper = new YuyueHelper(context, DB_NAME, null, VERSION);
        db = yuyueHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id", gouwu.getId());
        values.put("chanpinname", gouwu.getChanpinname());
        values.put("chanpinprice", gouwu.getChanpinprice());
        values.put("path", gouwu.getPath());
        values.put("xiangqing", gouwu.getXiangqing());
        db.update("Peifang", values, "id=?", new String[]{gouwu.getId() + ""});
    }

    public int insert(Peifang gouwu) {
        try {
            db.execSQL("insert into Peifang (chanpinname,chanpinprice,path,xiangqing) values(?,?,?,?) ", new String[]{
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

    public List<Peifang> load() {
        List<Peifang> list = new ArrayList<>();
        Cursor cursor = db
                .query("Peifang", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                Peifang gouwu = new Peifang();
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
