package com.example.basededatos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import java.util.ArrayList;

public class ContactosDB extends SQLiteOpenHelper
{
    private SQLiteDatabase db;
    private static ContactosDB contactosDB = null;

    //crearnos un metodo static para instanciar la BBDD
    public static ContactosDB getInstance(Context context)
    {

        if(contactosDB == null)
        {
            contactosDB = new ContactosDB(context);
        }
        return contactosDB;
    }

    private ContactosDB(Context context)
    {
        super(context, "contactos.db", null,1); //contexto , nombre de la BBDD, null, version de la BBDD

        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        //Solo se va a ejecutar cuando no exista el fichero de DataBase
        //Instrucciones en las que se va a basar la BBDD (create table, insert, etc)

        db.execSQL("create table Contactos (_id integer primary key autoincrement, nombre text, telefono integer);"); //****Obligatorio id con esa sintaxis****

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {


    }
    //Añadir la funcionalidad
    public void insertarContacto(Contacto contacto)
    {
        //Creamos una clase contenedora de valores
        ContentValues cv = new ContentValues();
        cv.put("nombre", contacto.getNombre());
        cv.put("telefono", contacto.getTelefono());

        //sintaxis para insertar
        db.insert("Contactos", null, cv);
    }
    public ArrayList<Contacto> getContactosDB()
    {
       Cursor cursor = db.rawQuery("select _id, nombre, telefono from Contactos order by nombre", null);

       //Recorrer las filas e instanciar por cada fila para poblar el arrayList del cual se hara el return final
        ArrayList<Contacto> alContactos = new ArrayList<>(cursor.getCount()); // pasamos este count al ArrayList para optimizar futuras acciones sobre la BBDD
        Contacto contacto;
        if(cursor.moveToFirst())//Es importante meter este metodo al principio para situar el cursor en primer lugar
        {
            do
            {
                contacto = new Contacto();
                contacto.setId(cursor.getInt(0));
                contacto.setNombre(cursor.getString(1));
                contacto.setTelefono(cursor.getInt(2));

                alContactos.add(contacto);
            }
            while (cursor.moveToNext());
        }
        else
        {
            //no hay contactos
        }
        cursor.close();
        return alContactos;
    }
    public void borrarContacto(int id)
    {
        db.delete("Contactos","_id=" + id, null);
    }
}
