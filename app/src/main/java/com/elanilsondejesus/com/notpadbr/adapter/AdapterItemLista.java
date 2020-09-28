package com.elanilsondejesus.com.notpadbr.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.elanilsondejesus.com.notpadbr.R;
import com.elanilsondejesus.com.notpadbr.model.ItemLista;
import com.elanilsondejesus.com.notpadbr.model.Lista;

import java.util.List;

public class AdapterItemLista extends RecyclerView.Adapter<AdapterItemLista.MyViewHolder> {
    List<ItemLista> itens;

    public AdapterItemLista(List<ItemLista> itens) {
        this.itens = itens;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

       View lista = LayoutInflater.from(parent.getContext())
               .inflate(R.layout.layout_item_lista,parent,false);
        return new MyViewHolder(lista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ItemLista itemLista = itens.get(position);
        holder.titulo.setText(itemLista.getTitulo());
        holder.marcado.setChecked(itemLista.getMarcado());
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
        }
    }
}
