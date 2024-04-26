package com.example.project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String databaseName = "dbUser.db";
    public DatabaseHelper(@Nullable Context context) {
        super(context, "dbUser.db", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase MyDatabase) {
        MyDatabase.execSQL("CREATE TABLE users(email TEXT PRIMARY KEY, password TEXT)");
        MyDatabase.execSQL("CREATE TABLE mahasiswa (nomor TEXT PRIMARY KEY, nama TEXT, tanggal_lahir TEXT, jenis_kelamin TEXT, alamat TEXT)");

    }
    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists users");
        MyDB.execSQL("drop Table if exists mahasiswa");
    }
    public Boolean insertData(String email, String password){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("password", password);
        long result = MyDatabase.insert("users", null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }
    public Boolean checkEmail(String email){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor cursor = MyDatabase.rawQuery("Select * from users where email = ?", new String[]{email});
        if(cursor.getCount() > 0) {
            return true;
        }else {
            return false;
        }
    }
    public Boolean checkEmailPassword(String email, String password){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor cursor = MyDatabase.rawQuery("Select * from users where email = ? and password = ?", new String[]{email, password});
        if (cursor.getCount() > 0) {
            return true;
        }else {
            return false;
        }
    }

    public boolean insertMahasiswaData(String nomor,String nama, String tanggalLahir, String jenisKelamin, String alamat) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nomor", nomor);
        contentValues.put("nama", nama);
        contentValues.put("tanggal_lahir", tanggalLahir);
        contentValues.put("jenis_kelamin", jenisKelamin);
        contentValues.put("alamat", alamat);
        long result = db.insert("mahasiswa", null, contentValues);
        db.close();
        return result != -1;
    }
    public Cursor getAllMahasiswaData() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM mahasiswa", null);
    }

    public Cursor getMahasiswaDetail(String nama) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"nomor", "nama", "tanggal_lahir", "jenis_kelamin", "alamat"};
        String selection = "nama = ?";
        String[] selectionArgs = {nama};
        return db.query("mahasiswa", columns, selection, selectionArgs, null, null, null);
    }

    public boolean deleteMahasiswa(String nama) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete("mahasiswa", "nama = ?", new String[]{nama});
        db.close();
        return result > 0;
    }

    public boolean updateData(String nomorBaru, String namaBaru, String tanggalLahirBaru, String jenisKelaminBaru, String alamatBaru) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nomor", nomorBaru);
        contentValues.put("nama", namaBaru);
        contentValues.put("tanggal_lahir", tanggalLahirBaru);
        contentValues.put("jenis_kelamin", jenisKelaminBaru);
        contentValues.put("alamat", alamatBaru);

        String whereClause = "nomor = ?";
        String[] whereArgs = {nomorBaru};

        int rowsAffected = db.update("mahasiswa", contentValues, whereClause, whereArgs);

        return rowsAffected > 0;
    }




}