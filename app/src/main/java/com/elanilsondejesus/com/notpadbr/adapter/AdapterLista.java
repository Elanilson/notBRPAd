package com.elanilsondejesus.com.notpadbr.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.elanilsondejesus.com.notpadbr.R;
import com.elanilsondejesus.com.notpadbr.model.Lista;
import com.elanilsondejesus.com.notpadbr.model.Nota;

import java.util.List;

public class AdapterLista extends RecyclerView.Adapter<AdapterLista.MyViewHolder> {
    List<Lista> listas;

    public AdapterLista(List<Lista> lista) {
        this.listas = lista;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

       View lista = LayoutInflater.from(parent.getContext())
               .inflate(R.layout.layout_lista,parent,false);
        return new MyViewHolder(lista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Lista lista = listas.get(position);
        holder.titulo.setText(lista.getId()+"");
        holder.data.setText(lista.getData());
    }

    @Override
    public int getItemCount() {
        return listas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView titulo, data;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.textViewTituloLista);
            data = itemView.findViewById(R.id.textViewDataLista);
        }
    }
}
