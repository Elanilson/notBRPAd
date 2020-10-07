package com.elanilsondejesus.com.notpadbr.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.elanilsondejesus.com.notpadbr.R;
import com.elanilsondejesus.com.notpadbr.activity.VisualizacaoActivity;
import com.elanilsondejesus.com.notpadbr.model.Nota;
import com.elanilsondejesus.com.notpadbr.model.Tema;

import java.util.List;

public class AdapterTema extends RecyclerView.Adapter<AdapterTema.MyViewHolder> {
   List<Tema> temas;
   Context context;

    public AdapterTema(List<Tema> temas, Context context) {
        this.temas = temas;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

       View lista = LayoutInflater.from(parent.getContext())
               .inflate(R.layout.layout_temas,parent,false);
        return new MyViewHolder(lista);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        final Tema tema = temas.get(position);
        holder.imagem.setImageResource(tema.getImagem());

        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Intent intent = new Intent(context,VisualizacaoActivity.class);
              intent.putExtra("imagem",tema.getImagem());
                Toast.makeText(context, ""+tema.getImagem(), Toast.LENGTH_SHORT).show();
              context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return temas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView imagem;
        private ImageButton button;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imagem = itemView.findViewById(R.id.imageViewTema);
            button = itemView.findViewById(R.id.imageButtonAplicar);
        }
    }
}
