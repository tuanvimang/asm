package com.example.asm_tkgd.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.asm_tkgd.databases.login_database;
import com.example.asm_tkgd.models.TaiKhoan;

public class LoginDAO {
    private login_database database;
    public LoginDAO (Context context){
        database = new login_database(context);
    }



    public boolean them(TaiKhoan taiKhoan){
        SQLiteDatabase sqLiteDatabase = database.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("user",taiKhoan.getUser());
        contentValues.put("pass",taiKhoan.getPass());
        Long check = sqLiteDatabase.insert("TK",null,contentValues);
        if(check == -1) return false;

        return true;

    }


    public TaiKhoan login (String user, String pass){
        TaiKhoan taiKhoan = null;
        SQLiteDatabase sqLiteDatabase = database.getReadableDatabase();
        String sql = "SELECT id,user,pass FROM TK WHERE user=?";
        Cursor cursor = sqLiteDatabase.rawQuery(sql,new String[]{user});
        try {
            if(!cursor.moveToFirst()){
                while (!cursor.isAfterLast()){
                    Integer _id = cursor.getInt(0);
                    String _user = cursor.getString(1);
                    String _pass = cursor.getString(2);
                    if(!pass.equals(_pass)){
                        break;
                    }
                    taiKhoan = new TaiKhoan(_id,_user,_pass);
                    cursor.moveToNext();
                }
            }
        }catch (Exception e){
            Log.d(">>>>>>>TAG","getALL: "+e.getMessage());
        }finally {
            if(cursor !=null && !cursor.isClosed()) cursor.close();
        }
        return taiKhoan;
    }


    public TaiKhoan login2(String user, String pass){
        TaiKhoan taiKhoan = null;
        SQLiteDatabase sqLiteDatabase = database.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM TK WHERE user=?",new String[]{String.valueOf(user)});
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            do{
                Integer _id = cursor.getInt(0);
                String _user = cursor.getString(1);
                String _pass = cursor.getString(2);
                if(!pass.equals(_pass)){
                    break;
                }
                taiKhoan = new TaiKhoan(_id,_user,_pass);
            }while (cursor.moveToNext());
        }
        return taiKhoan;
    }
}
