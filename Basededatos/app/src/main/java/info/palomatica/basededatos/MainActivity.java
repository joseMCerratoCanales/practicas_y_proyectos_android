package info.palomatica.basededatos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity
{

    private ContactosDB contactosDB;
    private LinearLayout llContactos;
    private EditText etNombre;
    private EditText etTelefono;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contactosDB = ContactosDB.getInstance(this);
        llContactos = findViewById(R.id.llContactos);
        etNombre = findViewById(R.id.etNombre);
        etTelefono = findViewById(R.id.etTelefono);

        dibujarContactos();

    }

    private void dibujarContactos()
    {
        ArrayList<Contacto> alContactos = contactosDB.getContactos();
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        for (Contacto contacto : alContactos)
        {
            LinearLayout llItemContacto = (LinearLayout) inflater.inflate(R.layout.item_contacto, null);
            llItemContacto.setOnClickListener((view) ->
            {
                llamar(contacto.getTelefono());
            });


            TextView tvNombre = llItemContacto.findViewById(R.id.tvNombre);
            TextView tvTelefono = llItemContacto.findViewById(R.id.tvTelefono);
            ImageButton ibBorrar = llItemContacto.findViewById(R.id.ibBorrar);

            ibBorrar.setOnClickListener((view) ->
            {
                contactosDB.eliminaContacto(contacto.getId());
                llContactos.removeView(llItemContacto);
            });


            tvNombre.setText(contacto.getNombre());
            tvTelefono.setText(contacto.getTelefono());

            llContactos.addView(llItemContacto);
        }
    }

    private void llamar(String telefono)
    {
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + telefono));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            if (checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
            {
                ActivityCompat.requestPermissions(this,
                        new String[] {Manifest.permission.CALL_PHONE}, 666);
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

    public void onClickAnadir(View view)
    {
        String nombre = etNombre.getText().toString();
        String telefono = etTelefono.getText().toString();

        Contacto nuevoContacto = new Contacto(nombre, telefono);
        contactosDB.insertarContacto(nuevoContacto);

        llContactos.removeAllViews();
        dibujarContactos();
    }
}
