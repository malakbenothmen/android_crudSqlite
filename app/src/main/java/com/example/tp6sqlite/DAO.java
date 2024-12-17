package com.example.tp6sqlite;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DAO {
    protected SQLiteDatabase db=null;
    protected MyDataBaseHelper dbHelper=null;
    public DAO(Context context) {
        dbHelper= new MyDataBaseHelper(context);  }

    public SQLiteDatabase openWrite(){
        db=dbHelper.getWritableDatabase();
        return db; }
    public SQLiteDatabase openRead(){
        db=dbHelper.getReadableDatabase();
        return db;}
    public  void close(){
        db.close();}

    public void addOffre(Offre o){
        db = openWrite();
        ContentValues values = new ContentValues();
        values.put(MyDataBaseHelper.KEY_POSTE, o.getPoste());
        values.put(MyDataBaseHelper.KEY_DESCRIPTION, o.getDescription());
        long result = db.insert(MyDataBaseHelper.TABLE_OFFRE, null, values);
        db.close();

        if (result == -1) {
            Log.e("DB_ERROR", "Insertion échouée");
        } else {
            Log.d("DB_SUCCESS", "Insertion réussie");
        }
    }


    public Offre searchOffreById(int id) {
        db = openRead();
        @SuppressLint("Recycle") Cursor cursor = db.query(MyDataBaseHelper.TABLE_OFFRE, new String[]{MyDataBaseHelper.KEY_ID,
                MyDataBaseHelper.KEY_POSTE, MyDataBaseHelper.KEY_DESCRIPTION}, MyDataBaseHelper.KEY_ID + "=?", new String[]
                {String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        Offre offre = new Offre(cursor.getShort(0), cursor.getString(1), cursor.getString(2));
        close();
        return offre;
    }



    public List<Offre> allOffres(){
        List<Offre> offreList = new ArrayList<>();

        String querySelect = "SELECT * FROM " + MyDataBaseHelper.TABLE_OFFRE;
        db = openRead();
        Cursor cursor = db.rawQuery(querySelect, null);

        if (cursor.moveToFirst()) {
            do {
                Offre offre = new Offre(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
                offreList.add(offre);
            } while (cursor.moveToNext());
        }
        cursor.close();
        close();
        return offreList;
    }




    public int updateOffre(Offre o) {

        db = openWrite();
        if (db == null) {
            return -1;
        }

        ContentValues values = new ContentValues();
        values.put(MyDataBaseHelper.KEY_POSTE, o.getPoste());
        values.put(MyDataBaseHelper.KEY_DESCRIPTION, o.getDescription());

        int rowsUpdated = db.update(MyDataBaseHelper.TABLE_OFFRE, values,
                MyDataBaseHelper.KEY_ID + " = ?", new String[]{String.valueOf(o.getId())});

        db.close();
        return rowsUpdated; // Retourne le nombre de lignes mises à jour
    }


    public void deleteOffre(Offre o){
        db=openWrite();
        db.delete(MyDataBaseHelper.TABLE_OFFRE,
                MyDataBaseHelper.KEY_ID+ "= ?",
                new String[]{String.valueOf(o.getId())});
        db.close();
    }





    }
