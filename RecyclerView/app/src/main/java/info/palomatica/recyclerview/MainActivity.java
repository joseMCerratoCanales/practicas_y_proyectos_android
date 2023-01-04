package info.palomatica.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MyRecyclerViewAdapter.ItemClickListener
{

    private RecyclerView rvItems;
    private MyRecyclerViewAdapter adapter;
    private ArrayList<String> alTextos;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        alTextos = new ArrayList<>();

        for(int i = 0; i < 10000; i++)
        {
            alTextos.add("dssdfsdf");
            alTextos.add("esdfsdfsd");
            alTextos.add("dfggfd");
            alTextos.add("vcbvbvcb");
            alTextos.add("43534543");
            alTextos.add("mmmmmmmmmm");
        }

        rvItems = findViewById(R.id.rvItems);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rvItems.getContext(),
                new LinearLayoutManager(this).getOrientation());


        rvItems.addItemDecoration(dividerItemDecoration);

        rvItems.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyRecyclerViewAdapter(this, alTextos);
        adapter.setItemClickListener(this);
        rvItems.setAdapter(adapter);



    }

    @Override
    public void onItemClick(View view, int position)
    {
        Toast.makeText(this, "Click. Texto: " + adapter.getItem(position) + " Posición: " + position, Toast.LENGTH_SHORT).show();
        alTextos.remove(position);
    }
}
