package com.elanilsondejesus.com.notpadbr.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.elanilsondejesus.com.notpadbr.R;
import com.elanilsondejesus.com.notpadbr.activity.TemaActivity;
import com.elanilsondejesus.com.notpadbr.activity.VisualizacaoActivity;
import com.elanilsondejesus.com.notpadbr.activity.VisualizarTemaMainActivity;
import com.elanilsondejesus.com.notpadbr.helper.DAOLista;
import com.elanilsondejesus.com.notpadbr.helper.DAONota;
import com.elanilsondejesus.com.notpadbr.model.Lista;
import com.elanilsondejesus.com.notpadbr.model.Nota;
import com.elanilsondejesus.com.notpadbr.model.Tema;

import java.util.List;

import dmax.dialog.SpotsDialog;

public class AdapterTema extends RecyclerView.Adapter<AdapterTema.MyViewHolder> {
   List<Tema> temas;
   Context context;
   Long id;
   Long idpasta;
   String listaa;
    private Dialog dialox;
    private Dialog dialog;
/*
Dialog e passando como paramento para que passa funcionar aqui dentro
e na activity tema o mesmo dialog recebe um contexto
 */
    public AdapterTema(List<Tema> temas, Context context, Long id, Dialog dialox ,Long idpasta, String listaa) {
        this.temas = temas;
        this.context = context;
        this.id = id;
        this.dialox = dialox;
        this.idpasta = idpasta;
        this.listaa = listaa;

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

        holder.buttonvisualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Intent intent = new Intent(context, VisualizarTemaMainActivity.class);
              intent.putExtra("imagem",tema.getImagem());
                Toast.makeText(context, ""+tema.getImagem(), Toast.LENGTH_SHORT).show();
              context.startActivity(intent);
            }
        });
        holder.buttonAplicar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  caminho da imagem salva no bd
                DAONota dao = new DAONota(context);
                DAOLista daoLista = new DAOLista(context);
                int caminho = tema.getImagem();

                /*
                id vai de amazenado em notecole e por sua vez vai ser atualizado a tabela
                 */


                if(caminho != 0 && listaa==null){
                    for(Nota nota: dao.listar()){
                        if(nota.getId() == id){
                            Toast.makeText(context, "ID:"+nota.getId(), Toast.LENGTH_SHORT).show();

                            Nota  notecolo = new Nota();
                            notecolo.setId(nota.getId());
                            notecolo.setIdPasta(idpasta);
                            notecolo.setCaminhoImg(caminho);
                            notecolo.setTitulo(nota.getTitulo());
                            notecolo.setTexto(nota.getTexto());
                            notecolo.setData(nota.getData());
                            notecolo.setCordeFundo(nota.getCordeFundo());
                            notecolo.setStatus(nota.getStatus());
                            if(dao.atualizar(notecolo)){
                               // Toast.makeText(context, "Atualizado", Toast.LENGTH_SHORT).show();
                                carregar();





                            }else{
                                Toast.makeText(context, "Erro ao Atualizar", Toast.LENGTH_SHORT).show();

                            }
                        }

                    }


                } // se não contem lista é uma nota e vai enterar
                if(caminho != 0 && listaa!= null){ // se conter lista vai entra pq e lista
                    for(Lista lista: daoLista.listar()){
                        if(lista.getId() == id){
                            Toast.makeText(context, "ID:"+lista.getId(), Toast.LENGTH_SHORT).show();

                            Lista list = new Lista();
                            list.setId(lista.getId());
//                            notecolo.setIdPasta(idpasta);
                            list.setCaminhoImg(caminho);
                            list.setTitulo(lista.getTitulo());
//                            notecolo.setTexto(lista.getTexto());
                            list.setData(lista.getData());
//                            notecolo.setCordeFundo(lista.getCordeFundo());
//                            notecolo.setStatus(lista.getStatus());
                            if(daoLista.atualizar(list)){
                                // Toast.makeText(context, "Atualizado", Toast.LENGTH_SHORT).show();
                                carregar();





                            }else{
                                Toast.makeText(context, "Erro ao Atualizar", Toast.LENGTH_SHORT).show();

                            }
                        }

                    }


                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return temas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView imagem;
        private Button buttonvisualizar, buttonAplicar ;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imagem = itemView.findViewById(R.id.imageViewTema);
            buttonvisualizar = itemView.findViewById(R.id.buttonVisualizar);
            buttonAplicar = itemView.findViewById(R.id.buttonAplicar);
        }
    }
    public void confirmacaoTemaSelecionado(){
        /*
        declaramos aqui dialog para que seja iniciado
         */


        dialox = new Dialog(context);

        dialox.setContentView(R.layout.confirmarcao);
        dialox.getWindow().setBackgroundDrawable( new ColorDrawable(Color.TRANSPARENT));
        dialox.show();
        /// criar aquele tempo da tela do splash
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {


        dialox.dismiss();
            }
        },1500);

        }

        public void carregar(){
            dialox = new SpotsDialog.Builder().setContext(context)
                    .setMessage("Carregando dados")
                    .setCancelable(false)
                    .build();
            dialox.show();
            /// criar aquele tempo da tela do splash
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    dialox.dismiss();
                    confirmacaoTemaSelecionado();

                }
            },1500);
        }



}
