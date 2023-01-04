package com.example.basededatos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    LinearLayout llContactos;
    EditText etNombre;
    EditText etTelefono;
    ContactosDB contactosDB;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Acceder a la base de datos

        //ejemplo para insertar
        //contactosDB.insertarContacto(new Contacto("pepe", 900000));

        llContactos = findViewById(R.id.llContactos);
        etNombre = findViewById(R.id.etNombre);
        etTelefono = findViewById(R.id.etTelefono);
        contactosDB = ContactosDB.getInstance(this);
        obtenerDatos();
    }

    public void onClickAnadir(View view) {
        //El getText de un EditText devuelve un editable, es necesario hacer un .toString para leerlo
        if (etNombre.getText().toString().isEmpty() || etTelefono.getText().toString().isEmpty())
        {
            Toast.makeText(this, "Por favor introduzca datos que faltan", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Contacto newContacto = new Contacto(etNombre.getText().toString(), Integer.parseInt(etTelefono.getText().toString()));
            contactosDB.insertarContacto(newContacto);
            obtenerDatos();

            etNombre.setText("");
            etTelefono.setText("");
        }

    }

    //Crear un metodo para acceder a la BBDD
    public void obtenerDatos()
    {
        //Remueve todas las vistas antes de leer la BBDD, si no se duplicarian los datos(exponencialmente)
        llContactos.removeAllViews();

        ArrayList<Contacto> alContactos = contactosDB.getContactosDB();

        LayoutInflater layoutInflater = getLayoutInflater();

        for (final Contacto contacto : alContactos) {
            final RelativeLayout relativeLayout = (RelativeLayout) layoutInflater.inflate(R.layout.item_contacto, null);

            relativeLayout.setOnClickListener((view) -> {
                llamar(contacto.getTelefono());
            });
            //Para que encuentre el TextView correcto, hay que apuntar al contenedor donde se encuentra
            TextView tvNombre = relativeLayout.findViewById(R.id.tvNombre);
            TextView tvTelefono = relativeLayout.findViewById(R.id.tvTelefono);
            ImageButton ibBorrar = relativeLayout.findViewById(R.id.ibBorrar);

            //Se Asigna una etiqueta, para cada nueva view(contacto) creada
            relativeLayout.setTag(contacto.getId());

            //Asignar el nombre y el telefono desde el edit text
            tvNombre.setText(contacto.getNombre());
            tvTelefono.setText(String.valueOf(contacto.getTelefono()));

            //para borrar segun el contacto que se ponga
            ibBorrar.setOnClickListener((view) ->
            {
                contactosDB.borrarContacto(contacto.getId());
                llContactos.removeView(relativeLayout);
            });


            tvNombre.setText(contacto.getNombre());
            tvTelefono.setText(String.valueOf(contacto.getTelefono()));

            llContactos.addView(relativeLayout);
        }
    }

    private void llamar(int telefono) {
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tlf: " + telefono));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
            {

                ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CALL_PHONE},666);
                return;
            }
        }
        startActivity(intent);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

}
