package com.example.qlnv;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.qlnv.model.NhanVien;
import com.example.qlnv.model.Phong;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    private final static String DB_NAME = "QLNV";
    private final static String PHONG_TBL = "phong";
    private final static String NV_TBL = "nhanVien";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query1 = "CREATE TABLE " + PHONG_TBL + "(id INTEGER PRIMARY KEY AUTOINCREMENT, ten TEXT)";
        String query2 = "CREATE TABLE " + NV_TBL + "(id INTEGER PRIMARY KEY AUTOINCREMENT, ten TEXT, tuoi INTEGER, " +
                "gioiTinh INTEGER, chuyenMon TEXT, phongId INTEGER)";

        sqLiteDatabase.execSQL(query1);
        sqLiteDatabase.execSQL(query2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + PHONG_TBL);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + NV_TBL);
        onCreate(sqLiteDatabase);
    }

    public void addPhong(Phong phong){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("ten", phong.getTen());

        db.insert(PHONG_TBL, null, contentValues);
    }

    public ArrayList<Phong> getListPhong(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<Phong> list = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM " + PHONG_TBL, null);

        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()){
            Phong phong = new Phong(cursor.getInt(0), cursor.getString(1));
            list.add(phong);
        }

        cursor.close();
        return list;
    }


    public String getTenPhong(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT ten FROM " + PHONG_TBL + " WHERE id=?", new String[]{String.valueOf(id)} );

        cursor.moveToFirst();
        return cursor.getString(0);
    }

    public void addNhanVien(NhanVien nhanVien){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("ten", nhanVien.getTen());
        contentValues.put("tuoi", nhanVien.getTuoi());
        contentValues.put("gioiTinh", nhanVien.getGioiTinh());
        contentValues.put("chuyenMon", nhanVien.getChuyenMon());
        contentValues.put("phongId", nhanVien.getPhongId());

        db.insert(NV_TBL, null, contentValues);
    }

    public ArrayList<NhanVien> getNhanVien(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<NhanVien> list = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM " + NV_TBL, null);

        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()){
          NhanVien nhanVien = new NhanVien(cursor.getInt(0), cursor.getString(1), cursor.getInt(2), cursor.getInt(3), cursor.getString(4), cursor.getInt(5));
            list.add(nhanVien);
        }

        cursor.close();
        return list;
    }

    public ArrayList<NhanVien> getNhanVienByPhong(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<NhanVien> list = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM " + NV_TBL + " WHERE phongId=?", new String[]{String.valueOf(id)});

        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()){
            NhanVien nhanVien = new NhanVien(cursor.getInt(0), cursor.getString(1), cursor.getInt(2), cursor.getInt(3), cursor.getString(4), cursor.getInt(5));
            list.add(nhanVien);
        }

        cursor.close();
        return list;
    }

    public NhanVien getNhanVienById(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + NV_TBL + " WHERE id=?", new String[]{String.valueOf(id)});

        cursor.moveToFirst();
        NhanVien nhanVien = new NhanVien(cursor.getInt(0), cursor.getString(1), cursor.getInt(2), cursor.getInt(3), cursor.getString(4), cursor.getInt(5));
        return nhanVien;
    }

    public void editNhanVien(NhanVien nhanVien){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("id", nhanVien.getId());
        contentValues.put("ten", nhanVien.getTen());
        contentValues.put("tuoi", nhanVien.getTuoi());
        contentValues.put("gioiTinh", nhanVien.getGioiTinh());
        contentValues.put("chuyenMon", nhanVien.getChuyenMon());
        contentValues.put("phongId", nhanVien.getPhongId());

        db.update(NV_TBL, contentValues, "id=?", new String[]{String.valueOf(nhanVien.getId())});
    }
}
