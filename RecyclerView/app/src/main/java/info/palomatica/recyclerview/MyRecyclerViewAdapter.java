package info.palomatica.recyclerview;

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

    MyRecyclerViewAdapter(Context context, ArrayList<String> alTextos)
    {
        layoutInflater = LayoutInflater.from(context);
        this.alTextos = alTextos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = layoutInflater.inflate(R.layout.item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        String texto = alTextos.get(position);
        holder.tvTexto.setText(texto);
    }

    @Override
    public int getItemCount()
    {
        return alTextos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {

        TextView tvTexto;
        ImageButton ibBoton;


        ViewHolder (View itemView)
        {
            super(itemView);
            tvTexto = itemView.findViewById(R.id.tvTexto);
            ibBoton = itemView.findViewById(R.id.ibBoton);
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
