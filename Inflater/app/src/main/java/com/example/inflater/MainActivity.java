package com.example.inflater;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.Random;

public class MainActivity extends AppCompatActivity
{
    private LinearLayout llAnadir;
    private LayoutInflater layoutinflater;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        llAnadir = findViewById(R.id.llAnadir);
        layoutinflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void onclickAnadir(final View view)
    {
        RelativeLayout rlElemento = (RelativeLayout) layoutinflater.inflate(R.layout.item_elemento, null);//Devuelve en este caso un relative layout (item_elemento) asi que se pondra una variable de tipo Relative Layout + nombre variable, tambien es necesario castearlo
        //Modificar las imagenes por cada click
        //Ponemos el contenedor y buscamos el identificador del elemento dentro del RelativeLayout donde se encuentra ese elemento
        ImageView ivIzquierda = rlElemento.findViewById(R.id.ivIzquierda);
        ivIzquierda.setImageResource(R.drawable.ic_android);
        ImageView ivCentro = rlElemento.findViewById(R.id.ivCentro);
        ivCentro.setImageResource(R.drawable.ic_android);
        ImageView ivDerecha = rlElemento.findViewById(R.id.ivDerecha);
        ivDerecha.setImageResource(R.drawable.ic_android);

        //Añadiendo un onClickListener se puede hacer boton a la imagen

        ivCentro.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                view.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.girar));
            }
        });
        ivIzquierda.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                view.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.girar));
            }
        });
        ivDerecha.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                view.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.girar));
            }
        });

        //Aleatorios
        Random random = new Random();
        int[] imagenes = {R.drawable.ic_android,R.drawable.ic_correr,R.drawable.ic_sol};
        ivIzquierda.setImageResource(imagenes[random.nextInt(imagenes.length)]);
        ivCentro.setImageResource(imagenes[random.nextInt(imagenes.length)]);
        ivDerecha.setImageResource(imagenes[random.nextInt(imagenes.length)]);


        llAnadir.addView(rlElemento,0);


    }
}
