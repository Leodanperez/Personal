package com.example.personal.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.personal.db.AdminSQLiteOpenHelper;
import com.example.personal.models.PersonaModel;

import java.util.ArrayList;
import java.util.List;

public class PersonaDAO {
    AdminSQLiteOpenHelper adminSQLiteOpenHelper;
    SQLiteDatabase sqLiteDatabase;

    public PersonaDAO (Context context){
        this.adminSQLiteOpenHelper = new AdminSQLiteOpenHelper(context);
        this.sqLiteDatabase = adminSQLiteOpenHelper.getWritableDatabase();
    }

    public Long registerPersona(PersonaModel personaModel){
        ContentValues valores = new ContentValues();
        valores.put(PersonaModel.CODIGO_FIEL,personaModel.getCodigo());
        valores.put(PersonaModel.NAME_FIELD,personaModel.getNombre());
        valores.put(PersonaModel.APELLIDO_FIELD,personaModel.getApellido());
        valores.put(PersonaModel.DNI_FIELD,personaModel.getDni());
        valores.put(PersonaModel.EDAD_FIELD,personaModel.getEdad());
        openDB();
        Long estado = sqLiteDatabase.insert(PersonaModel.TABLE_NAME,null,valores);
        sqLiteDatabase.close();
        return estado;
    }

    public int updatePersona(PersonaModel personaModel){
        ContentValues valores = new ContentValues();
        valores.put(PersonaModel.CODIGO_FIEL,personaModel.getCodigo());
        valores.put(PersonaModel.NAME_FIELD,personaModel.getNombre());
        valores.put(PersonaModel.APELLIDO_FIELD,personaModel.getApellido());
        valores.put(PersonaModel.DNI_FIELD,personaModel.getDni());
        valores.put(PersonaModel.EDAD_FIELD,personaModel.getEdad());

        String whereClause = "codigo=?";
        String[] whereArgs = {String.valueOf(personaModel.getCodigo())};
        openDB();
        int estado = sqLiteDatabase.update(PersonaModel.TABLE_NAME,valores,whereClause,whereArgs);
        sqLiteDatabase.close();
        return estado;
    }

    public int deletePersona(int codPersona){
        String whereClause = "codigo=?";
        String[] whereArgs = {String.valueOf(codPersona)};
        openDB();
        int stado = sqLiteDatabase.delete(PersonaModel.TABLE_NAME,whereClause,whereArgs);
        sqLiteDatabase.close();
        return stado;
    }

    public List<PersonaModel> listPersona(){
        List<PersonaModel> listProduct = new ArrayList<>();
        String[] column={
                PersonaModel.CODIGO_FIEL,
                PersonaModel.NAME_FIELD,
                PersonaModel.APELLIDO_FIELD,
                PersonaModel.DNI_FIELD,
                PersonaModel.EDAD_FIELD,
        };
        openDB();
        Cursor cursor = sqLiteDatabase.query(PersonaModel.TABLE_NAME,column,null,
                null,null,null,null);
        while (cursor.moveToNext()){
            listProduct.add(conevertCursorProduct(cursor));
        }
        sqLiteDatabase.close();
        return listProduct;
    }

    private PersonaModel conevertCursorProduct(Cursor cursor) {
        PersonaModel persona = new PersonaModel();
        //valores de nuestra columna
        persona.setCodigo(cursor.getInt(0));
        persona.setNombre(cursor.getString(1));
        persona.setApellido(cursor.getString(2));
        persona.setDni(cursor.getString(3));
        persona.setEdad(cursor.getString(4));
        return persona;
    }

    private void openDB() {
        if (!sqLiteDatabase.isOpen())
        {
            this.sqLiteDatabase = adminSQLiteOpenHelper.getWritableDatabase();
        }
    }
}
