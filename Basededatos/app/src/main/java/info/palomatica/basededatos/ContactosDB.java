package info.palomatica.basededatos;

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
        super(context, "contactos.db",null,1);

        db = getWritableDatabase();
    }


    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("create table contactos (_id integer primary key autoincrement," +
                "nombre text," +
                "telefono text);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
    {

    }


    public int insertarContacto(Contacto contacto)
    {
        ContentValues cv = new ContentValues();

        cv.put("nombre", contacto.getNombre());
        cv.put("telefono", contacto.getTelefono());

        db.insert("contactos", null, cv);

        String selectId = "select max(_id) from contactos;";
        Cursor cursor = db.rawQuery(selectId, null);
        cursor.moveToFirst();
        int id = cursor.getInt(0);
        return id;
    }




    public ArrayList<Contacto> getContactos()
    {
        Cursor cursor = db.rawQuery("select _id, nombre, telefono from contactos order by _id", null);
        ArrayList<Contacto> alContactos = new ArrayList<>(cursor.getCount());
        Contacto contacto;
        if(cursor.moveToFirst())
        {
            do
            {
                contacto = new Contacto();
                contacto.setId(cursor.getInt(0));
                contacto.setNombre(cursor.getString(1));
                contacto.setTelefono(cursor.getString(2));

                alContactos.add(contacto);
            }
            while(cursor.moveToNext());
        }
        cursor.close();
        return alContactos;
    }


    public void eliminaContacto(int id)
    {
        db.execSQL("delete from contactos where _id = " + id);
    }


}
