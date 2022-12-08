package com.example.asm_tkgd.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.asm_tkgd.databases.DbHelper;

public class ThongKeDao {
    private DbHelper dbHelper;
    public ThongKeDao(Context context){
        dbHelper= new DbHelper(context);
    }
    public float[] getThongTinThuChi() {
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        int thu = 0, chi = 0;

        //select sum(tien)
        //from khoan
        //where idLoai in (select idLoai from Loai where trangthai = 0)
        Cursor cursorThu = sqLiteDatabase.rawQuery("select sum(tien) from KHOAN where idLoai in (select idLoai from LOAI where trangthai = 0) ", null);

        if (cursorThu.getCount() != 0) {
            cursorThu.moveToFirst();
            thu = cursorThu.getInt(0);
        }

        //select sum(tien)
        //from khoan
        //where idLoai in (select idLoai from Loai where trangthai = 1)
        Cursor cursorChi = sqLiteDatabase.rawQuery("select sum(tien) from KHOAN where idLoai in (select idLoai from LOAI where trangthai = 1)", null);

        if (cursorChi.getCount() != 0) {
            cursorChi.moveToFirst();
            chi = cursorChi.getInt(0);
        }

        float[] ketQua = new float[]{thu, chi};
        return ketQua;
    }

}
