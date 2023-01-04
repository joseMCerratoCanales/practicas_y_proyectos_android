package info.palomatica.a2scrolls;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
    private Button btnAnadir;
    private Button btnReset;
    private RadioGroup rgColores;
    private RadioGroup rgAlineacion;
    private LinearLayout llDerecha;
    private LinearLayout llIzquierda;

    private int cont;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAnadir = findViewById(R.id.btnAnadir);
        btnReset = findViewById(R.id.btnReset);
        rgAlineacion = findViewById(R.id.rgAlineacion);
        rgColores = findViewById(R.id.rgColores);
        llDerecha = findViewById(R.id.llDerecha);
        llIzquierda = findViewById(R.id.llIzquierda);

        cont=0;

        iniciarListener();

    }
    public void iniciarListener()
    {

        btnAnadir.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {

                final Button button = new Button(MainActivity.this);
                button.setText(cont + "");

                switch (rgColores.getCheckedRadioButtonId()) {
                    case R.id.rcAzul:
                        button.setBackgroundColor(Color.BLUE);
                        break;
                    case R.id.rcRojo:
                        button.setBackgroundColor(Color.RED);
                        break;
                    case R.id.rcVerde:
                        button.setBackgroundColor(Color.GREEN);
                        break;
                }
                button.setOnClickListener(new View.OnClickListener()
                {
                    //Sumar 1 cada vez que se clicke
                    @Override
                    public void onClick(View v)
                    {
                        String numeroViejo = button.getText().toString();
                        int suma = Integer.parseInt(numeroViejo) + 1;
                        button.setText(suma + "");
                    }
                });
                if (rgAlineacion.getCheckedRadioButtonId() == R.id.rbDerecha)
                {
                    llDerecha.addView(button);
                }
                else
                {
                    llIzquierda.addView(button);
                }

            }
        });
        btnReset.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //Hallar numero de hijos por linearLayout
                for (int i=0; i<llDerecha.getChildCount();i++)
                {
                    //Como getChildAt devuelve una view, hay que castearlo, en este caso TextView, para poder cambiar el texto interior
                    ((TextView)llDerecha.getChildAt(i)).setText("0");
                }
                for (int i=0; i<llIzquierda.getChildCount();i++)
                {
                    ((TextView)llIzquierda.getChildAt(i)).setText("0");
                }
            }
        });
    }
}
