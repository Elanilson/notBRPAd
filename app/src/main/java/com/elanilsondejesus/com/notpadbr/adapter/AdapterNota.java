package com.elanilsondejesus.com.notpadbr.adapter;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.text.PrecomputedText;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.text.PrecomputedTextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.elanilsondejesus.com.notpadbr.R;
import com.elanilsondejesus.com.notpadbr.fragment.NotasFragment;
import com.elanilsondejesus.com.notpadbr.helper.RecyclerItemClickListener;
import com.elanilsondejesus.com.notpadbr.model.Nota;

import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.Result;

public class AdapterNota extends RecyclerView.Adapter<AdapterNota.MyViewHolder> {
    List<Nota> notas;
    Context context;
    Nota nota;
    private Dialog dialox;


    public AdapterNota(List<Nota> notas, Context context) {
        this.notas = notas;
        this.context = context;
    }

    public AdapterNota(List<Nota> notas, Context context, Dialog dialox) {
        this.notas = notas;
        this.context = context;
        this.dialox = dialox;
    }

    public List<Nota> getNotas() {
        return this.notas;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View lista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_nota, parent, false);
        return new MyViewHolder(lista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
         nota = notas.get(position);
        holder.titulo.setText(nota.getTitulo());
        holder.data.setText(nota.getData());
        holder.layout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Toast.makeText(context, "Nota:"+nota.getTitulo(), Toast.LENGTH_SHORT).show();
                return true;
            }
        });



    }

    @Override
    public int getItemCount() {
        return notas.size();
    }


/*
implemntar  View.OnClickListener, PopupMenu.OnMenuItemClickListener
 e colocar os metodos onclick e  onmenuitem
 e criar um metodo para criar o menu
 */

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, PopupMenu.OnMenuItemClickListener {
        private TextView titulo, data;
        private ImageButton menu;
        private LinearLayout layout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.textViewTitulo);
            data = itemView.findViewById(R.id.textViewData);
            menu = itemView.findViewById(R.id.imageButtonMenu);
            layout = itemView.findViewById(R.id.layoutNota);
//            menu.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    menuPopUp(view);
//                   // Toast.makeText(context, "okk", Toast.LENGTH_SHORT).show();
//                }
//            });
//            layout.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                 //   NotasFragment not = new NotasFragment();
//                   // not.enviarDadosSelecionado(nota,context);
////                    not.configurandoClickRecycleview(context);
//
//
//                }
//            });
//            layout.setOnLongClickListener(new View.OnLongClickListener() {
//                @Override
//                public boolean onLongClick(View view) {
//                    Toast.makeText(context, "long", Toast.LENGTH_SHORT).show();
//                    opcoes(nota);
//                    return true;
//                }
//            });




        }
        public void opcoes(final Nota nota) {
            dialox.setContentView(R.layout.opcoes);
            dialox.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            final TextView campoDeletar = dialox.findViewById(R.id.textViewDeletarNota2);
            Button salvarnovoPeso = dialox.findViewById(R.id.buttonSalvarNovoPEsoAtual);
            campoDeletar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   // deletar(nota);
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
//            menuPopUp(view);
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
