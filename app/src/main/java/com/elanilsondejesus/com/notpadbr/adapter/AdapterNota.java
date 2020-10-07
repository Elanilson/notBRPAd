package com.elanilsondejesus.com.notpadbr.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.elanilsondejesus.com.notpadbr.R;
import com.elanilsondejesus.com.notpadbr.model.Nota;

import java.util.List;

public class AdapterNota extends RecyclerView.Adapter<AdapterNota.MyViewHolder> {
    List<Nota> notas;
    Context context;

    public AdapterNota(List<Nota> notas, Context context) {
        this.notas = notas;
        this.context = context;
    }
    public List<Nota> getNotas(){
        return this.notas;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

       View lista = LayoutInflater.from(parent.getContext())
               .inflate(R.layout.layout_nota,parent,false);
        return new MyViewHolder(lista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Nota nota =notas.get(position);
        holder.titulo.setText(nota.getTitulo());
        holder.data.setText(nota.getData());
    }

    @Override
    public int getItemCount() {
        return notas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView titulo, data;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.textViewTitulo);
            data = itemView.findViewById(R.id.textViewData);
        }
    }
}
