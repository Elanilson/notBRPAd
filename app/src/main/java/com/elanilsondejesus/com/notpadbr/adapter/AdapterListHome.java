package com.elanilsondejesus.com.notpadbr.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.QuickContactBadge;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.elanilsondejesus.com.notpadbr.R;
import com.elanilsondejesus.com.notpadbr.fragment.ListasFragment;
import com.elanilsondejesus.com.notpadbr.fragment.PrincipalFragment;
import com.elanilsondejesus.com.notpadbr.model.Lista;

import java.util.List;

public class AdapterListHome extends RecyclerView.Adapter<AdapterListHome.MyViewHolder> {
    //List<ListHome>  listHomes;
    List<Lista> listas;
    Context context;
    Lista lista;


    public AdapterListHome(List<Lista> listas, Context context) {
        this.listas = listas;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View lista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_listhome, parent, false);
        return new MyViewHolder(lista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
         lista = listas.get(position);
        holder.titulo.setText(lista.getTitulo());
        holder.data.setText(lista.getData());
        if(lista.isAdicionar()){
        holder.imageButtonAdd.setVisibility(View.VISIBLE);
//
        }else{
            holder.imageButtonAdd.setVisibility(View.GONE);

        }
//        holder.imageButtonAdd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//
//            }
//        });




    }


    @Override
    public int getItemCount() {
        return listas.size();
    }


/*
implemntar  View.OnClickListener, PopupMenu.OnMenuItemClickListener
 e colocar os metodos onclick e  onmenuitem
 e criar um metodo para criar o menu
 */

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView titulo, data;
        private ImageButton imageButtonAdd;
        private LinearLayout layout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.textViewListhome);
            data = itemView.findViewById(R.id.textViewListData);
            imageButtonAdd = itemView.findViewById(R.id.imageButtonAdiconar);







        }




    }


}
