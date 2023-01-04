package info.palomatica.inflater;

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
    private LayoutInflater layoutInflater;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        llAnadir = findViewById(R.id.llElementos);
        layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void onClickAnadir(View view)
    {
        RelativeLayout rlElemento = (RelativeLayout) layoutInflater.inflate(R.layout.item_elemento, null);
        ImageView ivIzquierda = rlElemento.findViewById(R.id.ivIzquierda);
        ivIzquierda.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                view.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.girar));
            }
        });
        ImageView ivCentro = rlElemento.findViewById(R.id.ivCentro);
        ivCentro.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                view.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.girar));
            }
        });



        ImageView ivDerecha = rlElemento.findViewById(R.id.ivDerecha);
        ivDerecha.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                view.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.girar));
            }
        });
        Random random = new Random();
        int[] imagenes = {R.drawable.ic_android, R.drawable.ic_correr, R.drawable.ic_sol, R.drawable.balon};
        ivIzquierda.setImageResource(imagenes[random.nextInt(imagenes.length)]);
        ivCentro.setImageResource(imagenes[random.nextInt(imagenes.length)]);
        ivDerecha.setImageResource(imagenes[random.nextInt(imagenes.length)]);
        llAnadir.addView(rlElemento, 0);
    }
}
