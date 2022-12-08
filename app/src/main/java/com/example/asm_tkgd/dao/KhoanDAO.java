package com.example.asm_tkgd.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.asm_tkgd.databases.DbHelper;
import com.example.asm_tkgd.models.Khoan;

import java.util.ArrayList;

public class KhoanDAO {
    private DbHelper dbHelper ;
    public KhoanDAO(Context context){
        dbHelper = new DbHelper(context);
    }


    public ArrayList<Khoan> layDSKhoan(int trangThai){
        ArrayList<Khoan> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase  = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("    SELECT khoan.*, loai.tenloai FROM LOAI loai, KHOAN khoan WHERE loai.idloai = khoan.idloai AND khoan.idloai IN (SELECT idloai FROM loai WHERE trangthai = ?)",new String[]{String.valueOf(trangThai)});
        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            do {
                list.add(new Khoan(cursor.getInt(0), cursor.getString(1), cursor.getInt(2), cursor.getString(3), cursor.getInt(4), cursor.getString(5)));
            }while (cursor.moveToNext());
        }

        return list;
    }

    public boolean themKhoan(Khoan khoan){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tenKhoan",khoan.getKhoan());
        contentValues.put("tien",khoan.getTien());
        contentValues.put("ngay",khoan.getNgay());
        contentValues.put("idLoai",khoan.getIdLoai());
        long check = sqLiteDatabase.insert("KHOAN",null,contentValues);
        if(check==-1){
            return false;
        }
        return true;
    }

    public boolean capNhapKhoan(Khoan khoan){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tenKhoan",khoan.getKhoan());
        contentValues.put("tien",khoan.getTien());
        contentValues.put("ngay",khoan.getNgay());
        contentValues.put("idLoai",khoan.getIdLoai());
        long check = sqLiteDatabase.update("KHOAN",contentValues,"idKhoan = ?",new String[]{String.valueOf(khoan.getIdKhoan())});
        if(check==-1){
            return false;
        }
        return true;
    }

    public boolean xoaCapNhat(int idKhoan){
        SQLiteDatabase sqLiteDatabase =  dbHelper.getWritableDatabase();
        long check = sqLiteDatabase.delete("KHOAN","idKhoan = ? ",new String[]{String.valueOf(idKhoan)});

        if(check==-1){
            return false;
        }
        return true;
    }
}
