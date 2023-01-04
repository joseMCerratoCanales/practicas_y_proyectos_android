package com.example.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder>
{
    private ArrayList<String> alTextos;
    private LayoutInflater layoutInflater;
    private ItemClickListener itemClickListener;

    //Constructor
    MyRecyclerViewAdapter(Context context, ArrayList<String> alTextos) //ArrayList puede ser con objetos complejos
    {
        layoutInflater = LayoutInflater.from(context);
        this.alTextos = alTextos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = layoutInflater.inflate(R.layout.item, parent, false);
        return new ViewHolder(view); //Devuelve un view con la vista que acabamos de inflar
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        //Con este metodo recogemos el texto
        String texto = alTextos.get(position);
        holder.tvTexto.setText(texto);
    }

    @Override
    public int getItemCount() //Devuelve el numero de elementos que tiene el arrayList
    {
        return alTextos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        //Atributos o items que va a guardar el ViewHolder( por defecto o publicos)
        TextView tvTexto;
        ImageButton ibButton;

        //Constructor
        ViewHolder(View itemView)
        {
            super(itemView);
            tvTexto=itemView.findViewById(R.id.tvTexto);
            ibButton=itemView.findViewById(R.id.ibButton);

            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View view)
        {
            if(itemClickListener != null)
            {
                itemClickListener.onItemClick(view, getAdapterPosition());
            }
        }
    }

    String getItem(int position)
    {
        return alTextos.get(position);
    }

    void setItemClickListener(ItemClickListener itemClickListener)
    {
        this.itemClickListener = itemClickListener;
    }

    public interface ItemClickListener
    {
        void onItemClick(View view, int position);
    }
}
