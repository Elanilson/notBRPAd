package com.elanilsondejesus.com.notpadbr.adapter;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.elanilsondejesus.com.notpadbr.R;
import com.elanilsondejesus.com.notpadbr.model.Nota;
import com.elanilsondejesus.com.notpadbr.model.Pasta;

import java.util.List;

public class AdapterPasta extends RecyclerView.Adapter<AdapterPasta.MyViewHolder> {
    List<Pasta> pastas;


    public AdapterPasta(List<Pasta> pastas) {
        this.pastas = pastas;
    }




    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View lista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_pasta, parent, false);
        return new MyViewHolder(lista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
         Pasta pasta = pastas.get(position);
        holder.titulo.setText(pasta.getTitulo());
        holder.data.setText(pasta.getData());




    }

    @Override
    public int getItemCount() {
        return pastas.size();
    }


/*
implemntar  View.OnClickListener, PopupMenu.OnMenuItemClickListener
 e colocar os metodos onclick e  onmenuitem
 e criar um metodo para criar o menu
 */

    class MyViewHolder extends RecyclerView.ViewHolder  {
        private TextView titulo, data;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.textViewTituloPasta);
            data = itemView.findViewById(R.id.textViewDataPasta);



        }


    }


}
