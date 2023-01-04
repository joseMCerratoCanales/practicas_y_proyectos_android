package com.example.programacionhilos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
    TextView tvProgreso;
    ProgressBar pbProgress;
    Button btnEmpezar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnEmpezar = findViewById(R.id.btnEmpezar);
        tvProgreso = findViewById(R.id.tvProgreso);
        pbProgress = findViewById(R.id.pbProgress);
    }

    public void onClickEmpezar(View view)
    {
        //Declarar tu propio proceso para inicializarlo
        //                               ||
        //long milisegundos=10000;      \||/
        new AsyncTaskProceso().execute(10000l);
    }

    //Extender un clase para facilitar trabajar con hilos (AsyncTask)
    private class AsyncTaskProceso extends AsyncTask<Long,Void,Integer>
    {
        //
        @Override
        protected void onPreExecute()
        {
            // super.onPreExecute(); --> no hace nada
            tvProgreso.setText(R.string.proecesando);
            pbProgress.setVisibility( View.VISIBLE);
            btnEmpezar.setEnabled(false); // este metodo inutiliza el boton hasta que se acabe el try catch
            //btnEmpezar.setVisibility(View.INVISIBLE);
        }

        //Lo que metamos aqui se lanzará en forma de Hilo
        @Override
        protected Integer doInBackground(Long... tiempo)
        {
            try
            {
                Thread.sleep(tiempo[0]);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            return null;
        }
        //Cuando se termine doInBackground
        @Override
        protected void onPostExecute(Integer integer)
        {
            // super.onPostExecute(aVoid); --> no hace nada

            pbProgress.setVisibility(View.INVISIBLE);
            tvProgreso.setText(R.string.terminado);
            btnEmpezar.setEnabled(true);
            //btnEmpezar.setVisibility(View.VISIBLE);
        }
    }
}
