package com.example.agenda;

import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.agenda.Datos.Contacto;
import com.example.agenda.Datos.ContactosDb;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    private LinearLayout llContactos;
    ContactosDb contactosDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        llContactos.findViewById(R.id.llContactos);

        FloatingActionButton fab = findViewById(R.id.fab);
        obtenerDatos();
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        Preferencias.setCategoria(this,4);
        int categoria = Preferencias.getCategoria(this);

        /*
        //Dar persistencia a algun objeto (preferencias de usuario) - Utilizar preferentemente una clase aparte

        //1º Instanciar objeto, argumentos -> nombre del fichero, modo de utilización(private normalmente)
        SharedPreferences pref = getSharedPreferences("preferencias",MODE_PRIVATE); //->directorio sharedprefs, guarda en xml

        //Instanciar el editor
        SharedPreferences.Editor editor = pref.edit();

        //en cada put ponemos la clave y el valor que por defecto convenga, crear una constante con la clave mjr
        editor.putInt("categoria",2);
        editor.putString("favorito", "pepe");

        //Con apply escribimos
        editor.apply();

        pref.getInt("categoria",-1);
        pref.getString("favorito", null);
        */

        ContactosDb contactosDb = ContactosDb.getInstance(this);
    }

    private void obtenerDatos()
    {
        llContactos.removeAllViews();

        ArrayList<Contacto> alContactos = contactosDb.getContactos();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
