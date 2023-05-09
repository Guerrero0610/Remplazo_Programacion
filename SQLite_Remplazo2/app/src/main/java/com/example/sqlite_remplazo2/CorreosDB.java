package com.example.sqlite_remplazo2;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.opengl.GLDebugHelper;

import androidx.annotation.Nullable;

import com.example.sqlite_remplazo2.entidades.Correos;

import java.util.ArrayList;

public class CorreosDB extends SQLiteOpenHelper {

    public CorreosDB(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase BaseDatos) {
        BaseDatos.execSQL("create table usuario(correo text primary key, asunto text, contenido text, nombre text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
    public ArrayList<Correos> monstrarCorreos(){
        CorreosDB admin = new CorreosDB(this, "BaseDatos",null,1);
        SQLiteDatabase BaseDatos = admin.getWritableDatabase();

        ArrayList<Correos> listaCorreos = new ArrayList<>();
        Correos correos = null;
        Cursor cursorCorreos = null;

        cursorCorreos = BaseDatos.rawQuery("SELECT*FROM" + usuario, null);

        if(cursorCorreos.moveToFirst()){
            do {
                correos = new Correos();
                correos.setCorreo(String.valueOf(cursorCorreos.getInt(0)));
                correos.setAsunto(String.valueOf(cursorCorreos.getInt(0)));
                correos.setContenido(String.valueOf(cursorCorreos.getInt(0)));
                correos.setNombre(String.valueOf(cursorCorreos.getInt(0)));
                listaCorreos.add(correos);
            }while  (cursorCorreos.moveToNext());
        }
        cursorCorreos.close();

        return listaCorreos;
    }
}
