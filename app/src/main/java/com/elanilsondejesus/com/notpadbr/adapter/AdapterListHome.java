package com.elanilsondejesus.com.notpadbr.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.elanilsondejesus.com.notpadbr.R;
import com.elanilsondejesus.com.notpadbr.model.FavoritoNotas;
import com.elanilsondejesus.com.notpadbr.model.HojeNotas;

import java.util.List;

public class AdapterNotaHoje extends RecyclerView.Adapter<AdapterNotaHoje.MyViewHolder> {
    List<HojeNotas> hojeNotas;
//    Context context;
    HojeNotas hojeNota;
//    private Dialog dialox;


    public AdapterNotaHoje(List<HojeNotas> hojeNotas) {
        this.hojeNotas = hojeNotas;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View lista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.hoje_today, parent, false);
        return new MyViewHolder(lista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
         hojeNota = hojeNotas.get(position);
        holder.titulo.setText(hojeNota.getTitulo());
        holder.texto.setText(hojeNota.getTexto());




    }

    @Override
    public int getItemCount() {
        return hojeNotas.size();
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
            titulo = itemView.findViewById(R.id.textViewHoje);
            texto = itemView.findViewById(R.id.textViewTextoHoje);





        }




    }


}
