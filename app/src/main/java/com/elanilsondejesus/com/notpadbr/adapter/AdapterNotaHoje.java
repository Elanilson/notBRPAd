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
import com.elanilsondejesus.com.notpadbr.model.FavoritoNotas;
import com.elanilsondejesus.com.notpadbr.model.Nota;

import java.util.List;

public class AdapterNotaFavoritas extends RecyclerView.Adapter<AdapterNotaFavoritas.MyViewHolder> {
    List<FavoritoNotas> favoritos;
//    Context context;
    FavoritoNotas favorito;
//    private Dialog dialox;


    public AdapterNotaFavoritas(List<FavoritoNotas> favoritos) {
        this.favoritos = favoritos;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View lista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_favoritos, parent, false);
        return new MyViewHolder(lista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
         favorito = favoritos.get(position);
        holder.titulo.setText(favorito.getTitulo());
        holder.texto.setText(favorito.getTexto());




    }

    @Override
    public int getItemCount() {
        return favoritos.size();
    }


/*
implemntar  View.OnClickListener, PopupMenu.OnMenuItemClickListener
 e colocar os metodos onclick e  onmenuitem
 e criar um metodo para criar o menu
 */

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView titulo, texto;
        private ImageButton menu;
        private LinearLayout layout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.textViewFavoritos);
            texto = itemView.findViewById(R.id.textViewTextoFavorito);





        }




    }


}
