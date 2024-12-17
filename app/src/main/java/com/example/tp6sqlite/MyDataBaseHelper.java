package com.example.tp6sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDataBaseHelper extends SQLiteOpenHelper {

    public  static  final int DATABASE_VERSION = 2 ;
    public  static  final  String DATABASE_NAME = "demo_DB";
    public  static  final  String TABLE_OFFRE = "OFFRE";

    public static final  String KEY_ID="ID";
    public static final  String KEY_POSTE="POSTE";
    public static final  String KEY_DESCRIPTION="DESCRIPTION";

    public MyDataBaseHelper (Context context)
    {
        super(context,DATABASE_NAME, null,DATABASE_VERSION);

    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_OFFRE_TABLE =
                "CREATE TABLE " + TABLE_OFFRE + " ("
                        + KEY_ID + " INTEGER PRIMARY KEY, "
                        + KEY_POSTE + " TEXT, "
                        + KEY_DESCRIPTION + " TEXT)";
        db.execSQL(CREATE_OFFRE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_OFFRE);
        onCreate(db);

    }
}
