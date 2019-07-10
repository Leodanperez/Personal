package com.example.personal.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.personal.models.PersonaModel;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "personal.db";
    public static final int DB_VERSION = 1;

    String tabla_persona="CREATE TABLE "+ PersonaModel.TABLE_NAME+" ( " +
            PersonaModel.CODIGO_FIEL+" INTEGER primary key, "+
            PersonaModel.NAME_FIELD+" text not null, "+
            PersonaModel.APELLIDO_FIELD+" text not null, "+
            PersonaModel.DNI_FIELD+" text not null, "+
            PersonaModel.EDAD_FIELD+" int not null )";


    public AdminSQLiteOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(tabla_persona);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
