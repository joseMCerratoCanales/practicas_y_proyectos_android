package info.palomatica.puzlecolores;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity
{

    private TextView tv1;
    private TextView tv2;
    private TextView tv3;

    private String[] colores;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PoolPlayer.initializePlayer(this);

        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);
        tv3 = findViewById(R.id.tv3);

        colores = getResources().getStringArray(R.array.colores);

        asignaColor(tv1);
        asignaColor(tv2);
        asignaColor(tv3);
    }


    private void asignaColor(TextView tv)
    {
        Random random = new Random();
        int indexColor = random.nextInt(colores.length);
        tv.setBackgroundColor(Color.parseColor(colores[indexColor]));
        tv.setTag(indexColor);
    }

    public void onClickPanel(View view)
    {
        PoolPlayer.playSound(R.raw.shiny);

        view.startAnimation(AnimationUtils.loadAnimation(this, R.anim.girar));



        int indexColor = (int)view.getTag();

        if(indexColor == colores.length - 1)
        {
            indexColor = 0;
        }
        else
        {
            indexColor++;
        }
        view.setBackgroundColor(Color.parseColor(colores[indexColor]));
        view.setTag(indexColor);
        if(todosIguales())
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(R.string.fin);
            builder.setMessage(R.string.otraPartida);
            builder.setCancelable(false);
            builder.setPositiveButton(R.string.si ,new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialogInterface, int i)
                {
                    recreate();
                }
            });
            builder.setNegativeButton(R.string.no ,new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialogInterface, int i)
                {
                    finish();
                }
            });

            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }

    }

    private boolean todosIguales()
    {
        ConstraintLayout clPaneles = findViewById(R.id.clPaneles);
        int iPrimero = (int)tv1.getTag();
        TextView tv;
        for (int i = 1; i < clPaneles.getChildCount(); i++)
        {
            if(clPaneles.getChildAt(i) instanceof TextView)
            {
                tv = (TextView) clPaneles.getChildAt(i);
                int index = (int)tv.getTag();
                if(index != iPrimero)
                {
                    return false;
                }

            }
        }

        PoolPlayer.playSound(R.raw.record);
        return true;
    }
}
