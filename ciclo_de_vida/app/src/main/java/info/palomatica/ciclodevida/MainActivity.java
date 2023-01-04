package info.palomatica.ciclodevida;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;


public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("CICLO_VIDA", "onCreate()");
    }

    @Override
    protected void onRestart()
    {
        super.onRestart();
        Log.d("CICLO_VIDA", "onRestart()");
    }


    @Override
    protected void onStart()
    {
        super.onStart();
        Log.d("CICLO_VIDA", "onStart()");
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        Log.d("CICLO_VIDA", "onResume()");
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        Log.d("CICLO_VIDA", "onPause()");
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        Log.d("CICLO_VIDA", "onStop()");
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        Log.d("CICLO_VIDA", "onDestroy()");
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        Log.d("CICLO_VIDA", "onBackPressed()");
    }
}
