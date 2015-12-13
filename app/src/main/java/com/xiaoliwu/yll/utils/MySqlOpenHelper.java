package com.xiaoliwu.yll.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2015/9/19.
 */
public class MySqlOpenHelper extends SQLiteOpenHelper {
    private static final String DATABASENAME = "xiaoliwu.db";
    private static int version = 1;
    public MySqlOpenHelper(Context context) {
        super(context, DATABASENAME, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table yuhaibo(id integer primary key autoincrement,imagepath text,title text,path text)");
        db.execSQL("create table yuhaibo1(id integer primary key autoincrement,imagepath text,title text,path integer)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
