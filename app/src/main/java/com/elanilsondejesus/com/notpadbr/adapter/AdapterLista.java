package com.elanilsondejesus.com.notpadbr.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
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
import com.elanilsondejesus.com.notpadbr.activity.VisualizarListaActivity;
import com.elanilsondejesus.com.notpadbr.fragment.NotasFragment;
import com.elanilsondejesus.com.notpadbr.model.Lista;
import com.elanilsondejesus.com.notpadbr.model.Nota;

import java.util.List;

public class AdapterLista extends RecyclerView.Adapter<AdapterLista.MyViewHolder> {
    List<Lista> listas;
    Context context;
    Lista lista;
    private Dialog dialox;

    public AdapterLista(List<Lista> listas, Context context) {
        this.listas = listas;
        this.context = context;
    }

    public AdapterLista(List<Lista> listas, Context context, Dialog dialox) {
        this.listas = listas;
        this.context = context;
        this.dialox = dialox;
    }

    public List<Lista> getListas(){
        return this.listas;
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
         lista = listas.get(position);
        holder.titulo.setText(lista.getTitulo());
        holder.data.setText(lista.getData());
    }

    @Override
    public int getItemCount() {
        return listas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, PopupMenu.OnMenuItemClickListener{
        private TextView titulo, data;
        private LinearLayout layout;
        private ImageButton menu;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.textViewTituloLista);
            data = itemView.findViewById(R.id.textViewDataLista);
            layout = itemView.findViewById(R.id.layoutLista);
            menu = itemView.findViewById(R.id.imageButtonMenuLista);
//            menu.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    menuPopUp(view);
//                    // Toast.makeText(context, "okk", Toast.LENGTH_SHORT).show();
//                }
//            });
//            layout.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    //Recuperar tarefa para edicao
//
//                    Intent intent = new Intent(context, VisualizarListaActivity.class);
//                    intent.putExtra("idlista",lista.getId());
//                    context.startActivity(intent);
//
//
//                }
//            });
//            layout.setOnLongClickListener(new View.OnLongClickListener() {
//                @Override
//                public boolean onLongClick(View view) {
//                    Toast.makeText(context, "long", Toast.LENGTH_SHORT).show();
//                   // opcoes(nota);
//                    opcoes(lista);
//                    return true;
//                }
//            });
        }
        public void opcoes(final Lista lista){
            dialox.setContentView(R.layout.opcoes);
            dialox.getWindow().setBackgroundDrawable( new ColorDrawable(Color.TRANSPARENT));
            final TextView campoDeletar = dialox.findViewById(R.id.textViewDeletarNota2);
            Button salvarnovoPeso = dialox.findViewById(R.id.buttonSalvarNovoPEsoAtual);
            campoDeletar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                  //  deletar(lista);
                  //  deletarItensDeLista(lista);
                    dialox.dismiss();
                }
            });

            salvarnovoPeso.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // String titulo = campoTitulo.getText().toString();

                }


            });

            dialox.show();


        }
        @Override
        public void onClick(View view) {

        }

        private void menuPopUp(View view) {
            PopupMenu popupMenu = new PopupMenu(view.getContext(), view);
            popupMenu.inflate(R.menu.menu_opcoes);
            popupMenu.setOnMenuItemClickListener(this);
            popupMenu.show();
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {

            }
            return false;
        }
    }
}
