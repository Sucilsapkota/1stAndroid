package com.example.sucil.sqlitedbase;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper{

    public static final String DATABASE_NAME = "myDatabase";
    public static final String TABLE_NAME = "myTable";
    public static final int DATABASE_VERSION = 1;

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table "+TABLE_NAME+" (id integer primary key autoincrement, name varchar(50), address varchar(100))";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "drop table if exists "+TABLE_NAME;
        db.execSQL(sql);
        onCreate(db);
    }
    public void insert(Students s){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "insert into "+TABLE_NAME+" (name,address) values ('"+s.getName()+"','"+s.getAddress()+"')";
        db.execSQL(sql);
        db.close();
    }

    public ArrayList<Students> read(){
        ArrayList<Students> studentList = new ArrayList<Students>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "select * from "+TABLE_NAME;
        Cursor cursor = db.rawQuery(sql,null);
        if(cursor.moveToFirst()){
            do{
                studentList.add(new Students(cursor.getString(1),cursor.getString(2)));
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return studentList;
    }
}

