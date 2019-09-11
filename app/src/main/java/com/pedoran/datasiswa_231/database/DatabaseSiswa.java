package com.pedoran.datasiswa_231.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseSiswa extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "UserInfo";
    private static final String TABLE_NAME = "tbl_siswa";
    private static final String KEY_NAME = "name";
    private static final String KEY_NIS = "nis";
    private static final String KEY_JENKEL = "jenkel";
    private static final String KEY_BIRTHDAY = "tanggal_lahir";
    private static final String KEY_ALAMAT = "alamat";

    public DatabaseSiswa(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createUserTable = "Create Table "+TABLE_NAME+"("+KEY_NIS+" TEXT PRIMARY KEY,"+KEY_NAME+" TEXT,"+KEY_BIRTHDAY+" TEXT,"+KEY_JENKEL+" TEXT,"+KEY_ALAMAT+" TEXT"+")";
        sqLiteDatabase.execSQL(createUserTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sql = "Drop Table If Exists "+TABLE_NAME;
        sqLiteDatabase.execSQL(sql);
        onCreate(sqLiteDatabase);
    }
    //select oleh varo
    public List<Siswa> selectUserData(){
        ArrayList<Siswa> userList = new ArrayList<Siswa>();

        SQLiteDatabase db = getWritableDatabase();
        String[] columns = {KEY_NIS,KEY_NAME,KEY_BIRTHDAY,KEY_JENKEL,KEY_ALAMAT};

        Cursor c = db.query(TABLE_NAME,columns,null,null,null,null,null);

        while(c.moveToNext()){
            String nis = c.getString(0);
            String name = c.getString(1);
            String birthday = c.getString(2);
            String jenkel = c.getString(3);
            String alamat = c.getString(4);

            Siswa s = new Siswa();
            s.setNis(nis);
            s.setNama(name);
            s.setTanggalLahir(birthday);
            s.setJenisKelamin(jenkel);
            s.setAlamat(alamat);
            userList.add(s);
        }

        return userList;
    }
    //update oleh varo
    public void update(Siswa siswa){
        SQLiteDatabase db = getReadableDatabase();
        ContentValues val = new ContentValues();
        val.put(KEY_NAME,siswa.getNama());
        val.put(KEY_BIRTHDAY,siswa.getTanggalLahir());
        val.put(KEY_JENKEL,siswa.getJenisKelamin());
        val.put(KEY_ALAMAT,siswa.getAlamat());
        String whereClause = KEY_NIS+"='"+siswa.getNis()+"'";
        db.update(TABLE_NAME,val,whereClause,null);
    }
    //insert oleh tri
    public void insert(Siswa siswa){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues val = new ContentValues();
        val.put(KEY_NIS,siswa.getNis());
        val.put(KEY_NAME,siswa.getNama());
        val.put(KEY_BIRTHDAY,siswa.getTanggalLahir());
        val.put(KEY_JENKEL,siswa.getJenisKelamin());
        val.put(KEY_ALAMAT,siswa.getAlamat());

        db.insert(TABLE_NAME,null,val);
    }

    //delete oleh tri
    public void delete(String nis){
        SQLiteDatabase db = getWritableDatabase();
        String whereClause = KEY_NIS+"='"+nis+"'";
        db.delete(TABLE_NAME,whereClause,null);
    }
}
