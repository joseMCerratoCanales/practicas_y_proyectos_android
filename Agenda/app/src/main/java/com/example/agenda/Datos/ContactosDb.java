package com.example.agenda.Datos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class ContactosDb extends SQLiteOpenHelper
{
    private SQLiteDatabase db;
    private static ContactosDb contactosDb = null;

    public static final int CATEGORIA_FAMILIA = 1;
    public static final int CATEGORIA_TRABAJO = 2;
    public static final int CATEGORIA_AMIGOS = 3;
    public static final int CATEGORIA_TODOS = 4;


    public static ContactosDb getInstance(Context context)
    {
        if(contactosDb == null)
        {
            contactosDb = new ContactosDb(context);
        }
        return contactosDb;
    }
    public ContactosDb(Context context)
    {
        super(context, "Contactos.db", null, 1);

        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("create table contactos (_id integer primary key autoincrement," +
                "nombre text,"+
                "telefono text,"+
                "categoria integer,"+
                "telefono2 text,"+
                "email text,"+
                "direccion text,"+
                "aux text);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

    }
    public void insertarContacto(Contacto contacto)
    {
        ContentValues cv = new ContentValues();
        
        cv.put("nombre",contacto.getNombre());
        cv.put("telefono", contacto.getTelefono());
        cv.put("categoria", contacto.getCategoria());
        cv.put("telefono2", contacto.getTelefono2());
        cv.put("email",contacto.getEmail());
        cv.put("direccion",contacto.getDireccion());
        cv.put("aux",contacto.getAux());

        db.insert("contactos",null,cv);
    }
    public ArrayList<Contacto> getContactos(int categoria)
    {
        Cursor cursor = null;
        ArrayList<Contacto> alContactos = new ArrayList<>(cursor.getCount());
        Contacto contacto;
        if(categoria <= 3)
        {
            cursor = db.rawQuery("Select * from contactos where categoria =" + categoria,null);
        }
        else if(categoria == CATEGORIA_TODOS)
        {
            cursor = db.rawQuery("Select * from contactos",null);
        }
        while(cursor.moveToNext())
        {
            contacto = new Contacto();
            contacto.setId(cursor.getInt(0));
            contacto.setNombre(cursor.getString(1));
            contacto.setTelefono(cursor.getString(2));
            contacto.setCategoria(cursor.getInt(3));
            contacto.setTelefono2(cursor.getString(4));
            contacto.setEmail(cursor.getString(5));
            contacto.setDireccion(cursor.getString(6));
            contacto.setAux(cursor.getString(7));

            alContactos.add(contacto);
        }
        return alContactos;
    }
    public void eliminaContacto(int id)
    {
        db.delete("contactos","_id="+ id,null);
    }
    public void modificaContactos(Contacto contacto)
    {
        ContentValues cv = new ContentValues();

        //columnas a modificar
        cv.put("nombre",contacto.getNombre());
        cv.put("telefono",contacto.getTelefono());
        cv.put("email",contacto.getEmail());
        cv.put("categoria",contacto.getCategoria());

        //metodo para modificar en la BBDD
        db.update("contactos",cv,"_id = "+contacto.getId(),null);
    }
}
