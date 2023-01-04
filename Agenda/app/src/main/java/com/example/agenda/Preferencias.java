package com.example.agenda;

import android.content.Context;
import android.content.SharedPreferences;

public class Preferencias
{
    private  static final String FICHERO_PREFERENCIAS = "preferencias";

    private static final String KEY_CATEGORIA = "key_categoria";
    private static SharedPreferences pref;
    private static SharedPreferences.Editor editor;
    public static void setCategoria(Context context, int categoria)
    {
        if(pref == null)
        {
            //Instanciar shared preferencias
            pref = context.getSharedPreferences(FICHERO_PREFERENCIAS, Context.MODE_PRIVATE);
        }
        //instanciar editor
        editor = pref.edit();
        editor.putInt(KEY_CATEGORIA, categoria);
        editor.apply();
    }
    public static final int getCategoria(Context context)
    {
        if(pref==null)
        {
            pref = context.getSharedPreferences(FICHERO_PREFERENCIAS,Context.MODE_PRIVATE);
        }
        return pref.getInt(KEY_CATEGORIA,-1);
    }
    public static String getFicheroPreferencias()
    {
        return FICHERO_PREFERENCIAS;
    }

    public static String getKeyCategoria()
    {
        return KEY_CATEGORIA;
    }

    public static void setPref(SharedPreferences pref)
    {
        Preferencias.pref = pref;
    }
}
