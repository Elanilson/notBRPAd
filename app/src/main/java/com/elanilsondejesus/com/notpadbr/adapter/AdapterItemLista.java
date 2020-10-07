package com.elanilsondejesus.com.notpadbr.adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.elanilsondejesus.com.notpadbr.R;
import com.elanilsondejesus.com.notpadbr.activity.VisualizacaoActivity;
import com.elanilsondejesus.com.notpadbr.activity.VisualizarListaActivity;
import com.elanilsondejesus.com.notpadbr.helper.DAOItemLista;
import com.elanilsondejesus.com.notpadbr.model.ItemLista;
import com.elanilsondejesus.com.notpadbr.model.Lista;

import java.util.List;

public class AdapterItemLista extends RecyclerView.Adapter<AdapterItemLista.MyViewHolder> {
    List<ItemLista> itens;
    Context context;
    Boolean check = false;



    public AdapterItemLista(List<ItemLista> itens, Context context) {
        this.itens = itens;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

       View lista = LayoutInflater.from(parent.getContext())
               .inflate(R.layout.layout_item_lista,parent,false);


        return new MyViewHolder(lista);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {

        final ItemLista itemLista = itens.get(position);
        holder.titulo.setText(itemLista.getTitulo());
        holder.marcado.setChecked(itemLista.getStatus());

        holder.marcado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DAOItemLista dao = new DAOItemLista(context.getApplicationContext());


                if(holder.marcado.isChecked()){
                    itemLista.setMarcado(1);
                    if(dao.atualizar(itemLista)){
                        holder.titulo.setPaintFlags(holder.titulo.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

                        Toast.makeText(context, "marcado", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    itemLista.setMarcado(0);
                    if(dao.atualizar(itemLista)){
                        // retira o strikethrough adicionado
                        holder.titulo.setPaintFlags(0);
                        Toast.makeText(context, "desmarcado", Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });

        if (holder.marcado.isChecked()){
            /*
             strikethrough
             atribui aquele ricos  de palavra cortada
             */

            holder.titulo.setPaintFlags(holder.titulo.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        }
        holder.opcoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  opcoes(itemLista);

                VisualizarListaActivity vi = new VisualizarListaActivity();
                vi.ativatOpcoes(true,context,itemLista);
                Toast.makeText(context, "click", Toast.LENGTH_SHORT).show();



            }
        });

    }




    @Override
    public int getItemCount() {
        return itens.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView titulo;
        private CheckBox marcado;
        private ImageButton opcoes;
        private Spinner spinner;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.textViewTituloItemLista);
            marcado = itemView.findViewById(R.id.checkBoxItem);
            opcoes = itemView.findViewById(R.id.imageButtonOpcoes);
            spinner = itemView.findViewById(R.id.spinneropcoes);
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(context,
                    R.array.planets_array, android.R.layout.simple_spinner_dropdown_item);
          //  adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
            spinner.setAdapter(adapter);

            if(marcado.isChecked()){
                Toast.makeText(context, "teste4", Toast.LENGTH_SHORT).show();

            }

        }
    }

}
