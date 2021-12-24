package com.example.formresepbaking;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbMain extends SQLiteOpenHelper {
    public static final String db_name="db_baking";
    public static final String table_name="tb_resep_baking";
    public static final int VER=1;

    public static final String row_id ="idresep";
    public static final String row_namaresep ="namaresep";
    public static final String row_ingredients ="ingredients";
    public static final String row_nutrition ="nutrition";
    public static final String row_stepbystep ="stepbystep";
    public static final String row_lamamemasak ="lamamemasak";
    public static final String row_jeniskue ="jeniskue";
    public static final String row_waktu ="waktu";
    public static final String row_kategori ="kategori";

    private SQLiteDatabase db;

    public DbMain(Context context) {
        super(context, db_name, null, VER);
        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query ="CREATE TABLE " + table_name + "("
                + row_id + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + row_namaresep + " TEXT,"
                + row_ingredients + " TEXT,"
                + row_nutrition + " TEXT,"
                + row_stepbystep + " TEXT,"
                + row_lamamemasak + " INTEGER,"
                + row_jeniskue + " TEXT,"
                + row_waktu + " TEXT,"
                + row_kategori + " TEXT)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ table_name);
        onCreate(db);
    }

    public void insertData(ContentValues values){
        db.insert(table_name,null,values);
    }

    public void updateData(ContentValues values, long id){
        db.update(table_name,values,row_id + "=" + id, null);
    }

    public void deleteData(long id){
        db.delete(table_name, row_id + "=" + id, null);
    }

    public Cursor getAllData(){
        return db.query(table_name,null,null,null,null, null,null);
    }

    public Cursor getData(long id){
        return db.rawQuery("SELECT*FROM " + table_name + " WHERE " + row_id + "=" + id, null);
    }
}
