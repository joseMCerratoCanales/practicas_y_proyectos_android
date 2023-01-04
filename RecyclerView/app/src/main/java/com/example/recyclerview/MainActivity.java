package com.example.recyclerview;

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
    MyRecyclerViewAdapter adapter;
    private ArrayList<String> alTextos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Ejemplo de adición
        for (int i=0; i<50;i++)
        {
            alTextos.add("asdasdasda");
            alTextos.add("gfhgfh");
            alTextos.add("fsdhfshsfh");
        }

        rvItems = findViewById(R.id.rvItems);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rvItems.getContext(),new LinearLayoutManager(this).getOrientation());
        rvItems.addItemDecoration(dividerItemDecoration);
        rvItems.setLayoutManager(new LinearLayoutManager(this));

        adapter = new MyRecyclerViewAdapter(this, alTextos);
        adapter.setItemClickListener(this);

        rvItems.setAdapter(adapter);


    }

    @Override
    public void onItemClick(View view, int position)
    {
        Toast.makeText(this, "CLICKED. Posicion: " + position + "; Texto: " + adapter.getItem(position), Toast.LENGTH_SHORT).show();
        alTextos.remove(position);

        //cada vez que modificamos algo hay que notificar al RecyclerView que lo cambie
        adapter.notifyDataSetChanged();
    }
}
