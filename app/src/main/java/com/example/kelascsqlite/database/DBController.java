package com.example.kelascsqlite.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.kelascsqlite.adapter.TemanAdapter;

import java.util.ArrayList;
import java.util.HashMap;

//Ini adalah DDL

public class DBController extends SQLiteOpenHelper {
    public DBController(Context context) {
        super(context, "ProdiTI", null, 1); //membuat nama database
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table teman (id integer primary key,nama text, telpon text)"); //Bikin tabel
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { //Jika diupgrade
        db.execSQL("drop table if exists teman");
        onCreate(db);
    }

    //Ini adalah MDL
    public void insertData(HashMap<String,String> queryValues){
        SQLiteDatabase basisdata = this.getWritableDatabase();
        ContentValues nilai = new ContentValues();
        nilai.put("nama",queryValues.get("nama"));
        nilai.put("telpon",queryValues.get("telpon"));
        basisdata.insert("teman", null, nilai);
        basisdata.close();
    }

    public void updateData(HashMap<String,String> queryValues){
        SQLiteDatabase basisdata = this.getWritableDatabase();
        ContentValues nilai = new ContentValues();
        nilai.put("nama",queryValues.get("nama"));
        nilai.put("telpon",queryValues.get("telpon"));
        basisdata.update("teman",nilai,"id"+" =?", new String[]{queryValues.get("id")});
        basisdata.close();
    }

    public int deleteData(HashMap<String,String> queryValues){
        SQLiteDatabase basisdata = this.getWritableDatabase();
        return basisdata.delete("teman","id"+" =?", new String[]{queryValues.get("id")});
    }

    public ArrayList<HashMap<String,String>> getAllTeman(){
        ArrayList<HashMap<String,String>> daftarTeman;
        daftarTeman = new ArrayList<HashMap<String, String>>();
        String selectQuery = "Select * from teman";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()){
            do {
                HashMap<String,String> map = new HashMap<>();
                map.put("id",cursor.getString(0));
                map.put("nama",cursor.getString(1));
                map.put("telpon",cursor.getString(2));
                daftarTeman.add(map);
            } while (cursor.moveToNext());
        }
        db.close();
        return daftarTeman;
    }
}
