package com.example.cdmuseum.db;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


import com.example.cdmuseum.bean.Qiye;

import java.util.ArrayList;
import java.util.List;

/**
 * 中药材增删改查
 */
public class MedicDbutils {
    public static final String DB_NAME = "medic_dbname";
    public static final int VERSION = 1;
    private static MedicDbutils sqliteDB;
    private SQLiteDatabase db;

    private MedicDbutils(Context context) {
        MedicHelper openHelper = new MedicHelper(context, DB_NAME, null, VERSION);
        db = openHelper.getWritableDatabase();
    }

    public synchronized static MedicDbutils getInstance(Context context) {
        if (sqliteDB == null) {
            sqliteDB = new MedicDbutils(context);
        }
        return sqliteDB;
    }

    public void delete(Context context, String id) {
        MedicHelper openHelper = new MedicHelper(context, DB_NAME, null, VERSION);
        db = openHelper.getReadableDatabase();
        db.delete("Medic", "id=?", new String[]{id});
    }

    public void change(Context context, Qiye medic) {
        MedicHelper openHelper = new MedicHelper(context, DB_NAME, null, VERSION);
        db = openHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id", medic.getId());
        values.put("caiming", medic.getCaiming());
        values.put("path", medic.getPath());
        values.put("price", medic.getPrice());
        values.put("jieshao", medic.getPrice());
        db.update("Medic", values, "id=?", new String[]{medic.getId() + ""});
    }

    public int insert(Qiye medic) {
        try {
            db.execSQL("insert into Medic(caiming,path,price,jieshao) values(?,?,?,?)", new String[]{
                    medic.getCaiming(),
                    medic.getPath(),
                    medic.getPrice(),
                    medic.getJieshao()
            });
            return 0;
        } catch (Exception e) {
            Log.d("����", e.getMessage().toString());
            return -1;
        }
    }

    /**
     * 查询所有数据
     *
     * @return
     */
    public List<Qiye> load() {
        List<Qiye> list = new ArrayList<Qiye>();
        Cursor cursor = db.query("Medic", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                Qiye medic = new Qiye();
                medic.setId(cursor.getInt(cursor.getColumnIndex("id")));
                medic.setCaiming(cursor.getString(cursor.getColumnIndex("caiming")));
                medic.setPath(cursor.getString(cursor.getColumnIndex("path")));
                medic.setPrice(cursor.getString(cursor.getColumnIndex("price")));
                medic.setJieshao(cursor.getString(cursor.getColumnIndex("jieshao")));
                Log.e("app", "medic = " + medic.toString());
                list.add(medic);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }

    public List<Qiye> loadByName(String name) {
        List<Qiye> list = new ArrayList<Qiye>();
        Cursor cursor = db.query("Medic", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                if (cursor.getString(cursor.getColumnIndex("caiming")).indexOf(name) != -1) {
                    Qiye medic = new Qiye();
                    medic.setId(cursor.getInt(cursor.getColumnIndex("id")));
                    medic.setCaiming(cursor.getString(cursor.getColumnIndex("caiming")));
                    medic.setPath(cursor.getString(cursor.getColumnIndex("path")));
                    medic.setPrice(cursor.getString(cursor.getColumnIndex("price")));
                    medic.setJieshao(cursor.getString(cursor.getColumnIndex("jieshao")));
                    list.add(medic);
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }
}
