package com.elanilsondejesus.com.notpadbr.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.elanilsondejesus.com.notpadbr.R;
import com.elanilsondejesus.com.notpadbr.helper.DAOItemLista;
import com.elanilsondejesus.com.notpadbr.model.ItemLista;
import com.elanilsondejesus.com.notpadbr.model.Lista;

import java.util.List;

public class AdapterItemLista extends RecyclerView.Adapter<AdapterItemLista.MyViewHolder> {
    List<ItemLista> itens;
    Context context;
    Boolean check = false;


    public AdapterItemLista(List<ItemLista> itens, Context context) {
        this.itens = itens;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

       View lista = LayoutInflater.from(parent.getContext())
               .inflate(R.layout.layout_item_lista,parent,false);

        return new MyViewHolder(lista);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {

        final ItemLista itemLista = itens.get(position);
        holder.titulo.setText(itemLista.getTitulo());
        holder.marcado.setChecked(itemLista.getStatus());

        holder.marcado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DAOItemLista dao = new DAOItemLista(context.getApplicationContext());


                if(holder.marcado.isChecked()){
                    itemLista.setMarcado(1);
                    if(dao.atualizar(itemLista)){
                        holder.titulo.setPaintFlags(holder.titulo.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

                        Toast.makeText(context, "marcado", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    itemLista.setMarcado(0);
                    if(dao.atualizar(itemLista)){
                        // retira o strikethrough adicionado
                        holder.titulo.setPaintFlags(0);
                        Toast.makeText(context, "desmarcado", Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });

        if (holder.marcado.isChecked()){
            /*
             strikethrough
             atribui aquele ricos  de palavra cortada
             */

            holder.titulo.setPaintFlags(holder.titulo.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        }

    }


    @Override
    public int getItemCount() {
        return itens.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView titulo;
        private CheckBox marcado;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.textViewTituloItemLista);
            marcado = itemView.findViewById(R.id.checkBoxItem);

            if(marcado.isChecked()){
                Toast.makeText(context, "teste4", Toast.LENGTH_SHORT).show();

            }

        }
    }

}
