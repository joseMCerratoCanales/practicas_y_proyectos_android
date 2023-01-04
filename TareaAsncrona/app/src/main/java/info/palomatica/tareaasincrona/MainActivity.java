package info.palomatica.tareaasincrona;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{

    private ProgressBar pb;
    private TextView tvEstadoProceso;
    private Button btEmpezar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pb = findViewById(R.id.pb);
        tvEstadoProceso = findViewById(R.id.tvEstadoProceso);
        btEmpezar = findViewById(R.id.btEmpezar);
    }

    public void onClickEmpezar(View view)
    {

        long milisegundos = 10000;
        new AsyncTaskProceso().execute(milisegundos);



    }


    private class AsyncTaskProceso extends AsyncTask<Long,Void,Integer>
    {

        @Override
        protected void onPreExecute()
        {
            pb.setVisibility(View.VISIBLE);
            tvEstadoProceso.setText(R.string.procesando);
            btEmpezar.setEnabled(false);
        }


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

        @Override
        protected void onPostExecute(Integer integer)
        {
            pb.setVisibility(View.INVISIBLE);
            tvEstadoProceso.setText(R.string.procesoTerminado);
            btEmpezar.setEnabled(true);
        }


    }




}
