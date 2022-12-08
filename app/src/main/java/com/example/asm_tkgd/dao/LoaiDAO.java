package com.example.asm_tkgd.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.asm_tkgd.databases.DbHelper;
import com.example.asm_tkgd.models.Loai;

import java.util.ArrayList;

public class LoaiDAO  {
    private DbHelper dbHelper;
    public  LoaiDAO (Context context){
        dbHelper = new DbHelper(context);
    }

// lấy danh sách loại theo trang thái
//    0:thu - chi:1
    public ArrayList<Loai> layDSLoai (int trangThai){
        ArrayList<Loai> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM LOAI WHERE trangThai = ?",new String[]{String.valueOf(trangThai)});
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            do {
                list.add(new Loai (cursor.getInt(0),cursor.getString(1),cursor.getInt(2))) ;

            }while (cursor.moveToNext());
        }
        return list;
    }

    public boolean themLoai(Loai loai){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tenLoai",loai.getTenLoai());
        contentValues.put("trangThai",loai.getTrangThai());
        Long check = sqLiteDatabase.insert("LOAI", null,contentValues);
        if(check== -1)
            return false;
        return true;
    }

    public boolean capNhatLoai(Loai loai){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tenLoai",loai.getTenLoai());
        contentValues.put("trangThai",loai.getTrangThai());
        int check = sqLiteDatabase.update("LOAI",contentValues,"idLoai = ?",new String[]{String.valueOf(loai.getIdLoai())});
        if (check == -1)
            return false;

        return true;


    }

    public Boolean xoaLoai (int idloai){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        int check = sqLiteDatabase.delete("LOAI","idLoai = ?",new String[]{String.valueOf(idloai)});
        if(check == -1)
            return false;

        return true;
    }

}
