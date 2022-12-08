package com.example.asm_tkgd.databases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class login_database extends SQLiteOpenHelper {
    private static login_database instance;
    public static synchronized login_database getInstance(Context context){
        if(instance == null) instance=new login_database(context); return instance;

    }
    public login_database(Context context) {super(context,"QUANLIDANGNHAP",null,4);}
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTK="CREATE TABLE TK (id integer primary key autoincrement,user text,pass text)";
        sqLiteDatabase.execSQL(createTK);
        String data= "INSERT INTO TK VALUES(1,'admin','uxui')";
        sqLiteDatabase.execSQL(data);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        if (i!=i1){
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS TK");
            onCreate(sqLiteDatabase);
        }
    }
}
