package com.elanilsondejesus.com.notpadbr.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.elanilsondejesus.com.notpadbr.R;
import com.elanilsondejesus.com.notpadbr.adapter.AdapterItemLista;
import com.elanilsondejesus.com.notpadbr.adapter.AdapterNota;
import com.elanilsondejesus.com.notpadbr.helper.DAOItemLista;
import com.elanilsondejesus.com.notpadbr.helper.DAONota;
import com.elanilsondejesus.com.notpadbr.helper.RecyclerItemClickListener;
import com.elanilsondejesus.com.notpadbr.model.ItemLista;
import com.elanilsondejesus.com.notpadbr.model.Nota;
import com.elanilsondejesus.com.notpadbr.model.Pasta;
import com.github.clans.fab.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class VisualizarPastaActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private Nota nota = new Nota();
    private Pasta pasta = new Pasta();
    private AdapterNota adapter ;
    private List<Nota> notas = new ArrayList<>();
    private  Long idPasta=null;
    private Nota notaSelecionada = null;
    private Dialog dialox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_pasta);



        recebendoDados();
        carregarItens();
        iniciarRecycleviewEdefinirLayout(notas);
        configurandoClickRecycleview();
        floatingActionButtonADD();
        dialox = new Dialog(VisualizarPastaActivity.this);


    }
    public void iniciarRecycleviewEdefinirLayout(List<Nota> notas){

        recyclerView = findViewById(R.id.recyclerviewVisuPasta);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new AdapterNota(notas,this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

    }

    public void configurandoClickRecycleview(){
        //Adicionar evento de clique
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(
               getApplicationContext(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //Recuperar tarefa para edicao
                notaSelecionada = notas.get(position);
                enviarDadosSelecionado(notaSelecionada);

            }

            @Override
            public void onLongItemClick(View view, int position) {
                //Recuperar tarefa para edicao
                notaSelecionada = notas.get(position);

                opcoes(notaSelecionada);
            }

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        }
        ));
    }
    public void opcoes(final Nota nota) {
        dialox.setContentView(R.layout.opcoes);
        dialox.setCancelable(false);
        dialox.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        final TextView campoDeletar = dialox.findViewById(R.id.textViewDeletarNota2);
        final TextView campoAdicionar = dialox.findViewById(R.id.textViewAdionarNota);
        final TextView campoEditar = dialox.findViewById(R.id.textViewEditarNota);
        final TextView campoCompartilhar = dialox.findViewById(R.id.textViewCompartilharNota);
        Button cancelar = dialox.findViewById(R.id.buttonSalvarNovoPEsoAtual);
        campoCompartilhar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                compartilhar();
                dialox.dismiss();
            }
        });
        campoEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enviarDadosEditar();
                dialox.dismiss();
            }
        });
        campoAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), EditorActivity.class);
                intent.putExtra("enviarDados", false);
                intent.putExtra("idPasta", idPasta);
                startActivity(intent);
                dialox.dismiss();
            }
        });
        campoDeletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deletar(nota);
                dialox.dismiss();
            }
        });

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // String titulo = campoTitulo.getText().toString();
                dialox.dismiss();

            }


        });

        dialox.show();


    }
    public void deletar(final Nota notaSelecionada) {

         AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Nota: "+notaSelecionada.getTitulo());
        builder.setMessage("Deseja realemente excluir ?");
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DAONota dao = new DAONota(getApplicationContext());
                notaSelecionada.setStatus(0);
                if (dao.atualizar(notaSelecionada)) {
                    recarregar();

//            adapter.notifyDataSetChanged();
                    Toast.makeText(getApplicationContext(), "Excluido so sucesso", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Erro ao Excluido ", Toast.LENGTH_SHORT).show();

                }
            }
        });
        builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {


            }
        });

        Dialog dialog =  builder.create();
        dialog.show();
      //  builder.show();


    }
    public void enviarDadosEditar(){
        // enviar dados para serem editados/alterados
        if(notaSelecionada != null){
            Intent intent = new Intent(VisualizarPastaActivity.this,EditorActivity.class);
            intent.putExtra("id",notaSelecionada.getId());
            intent.putExtra("idPasta",idPasta);
            intent.putExtra("titulo",notaSelecionada.getTitulo());
            intent.putExtra("texto",notaSelecionada.getTexto());
            intent.putExtra("data",notaSelecionada.getData());
            intent.putExtra("caminhoImg",notaSelecionada.getCaminhoImg());
            intent.putExtra("editar",true);
            startActivity(intent);
            try {
                finalize();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
    }
    public void compartilhar() {

        Intent intent = new Intent(Intent.ACTION_SEND);


        intent.putExtra(Intent.EXTRA_SUBJECT,notaSelecionada.getTitulo());
        intent.putExtra(Intent.EXTRA_TEXT, notaSelecionada.getTexto());
        intent.setType("text/plain");
        startActivity(Intent.createChooser(intent, "Compartilhar: "+notaSelecionada.getTitulo()));




    }
    public void enviarDadosSelecionado(Nota nota) {
        /*
        quando o usuario clicar na nota essa nota selecionada vai enviar os seus dados para outra tela
        para que seja apresentada ao usuario
         */
        /*
        o conteudo é enviado como parametro pelo contrutor e esse memtodi aqui
        ele recebe o contexto enviado e é utilizado para iniciar a intent
         */
        Intent intent = new Intent(getApplicationContext(), VisualizacaoActivity.class);
        intent.putExtra("id", nota.getId());
        intent.putExtra("idpasta", nota.getIdPasta());
        intent.putExtra("titulo", nota.getTitulo());
        intent.putExtra("texto", nota.getTexto());
        intent.putExtra("data", nota.getData());
        intent.putExtra("caminhoImg", nota.getCaminhoImg());
        intent.putExtra("status", nota.getStatus());
        //Toast.makeText(getActivity(), "cami: "+nota.getCaminhoImg(), Toast.LENGTH_SHORT).show();

        startActivity(intent);
    }
    public void floatingActionButtonADD() {
        //recyclerView = view.findViewById(R.id.recyclerView);
        FloatingActionButton fab =findViewById(R.id.fabmenuPasta_itemadd);
//        fab.attachToRecyclerView(recyclerView);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), EditorActivity.class);
                intent.putExtra("enviarDados", false);
                intent.putExtra("idPasta", idPasta);
                startActivity(intent);
            }
        });
    }
    public void carregarItens(){
        DAONota dao = new DAONota(getApplicationContext());
        for(Nota not: dao.listarItemPasta(idPasta)){

                notas.add(not);
            if(not.getId() == idPasta ){
            }

        }


    }
    public void recarregar() {
        carregarItens();
        List<Nota> notas = new ArrayList<>();
        DAONota dao = new DAONota(getApplicationContext());
        for (Nota nota : dao.listarItemPasta(idPasta)) {
            notas.add(nota);
        }
        iniciarRecycleviewEdefinirLayout(notas);
        adapter.notifyDataSetChanged();
    }
    public void recebendoDados(){
        Bundle bundle = getIntent().getExtras();
        Long id = bundle.getLong("idpasta");

        nota.setId(id);
        idPasta=id;
        pasta.setId(id);

    }

    @Override
    public void onPause() {
        super.onPause();
        notas.clear();


    }
    @Override
    public void onStart() {
        super.onStart();

        recarregar();



    }
}