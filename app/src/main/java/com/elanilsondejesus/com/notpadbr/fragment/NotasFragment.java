package com.elanilsondejesus.com.notpadbr.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.elanilsondejesus.com.notpadbr.R;
import com.elanilsondejesus.com.notpadbr.activity.EditorActivity;
import com.elanilsondejesus.com.notpadbr.activity.PesquisaActivity;
import com.elanilsondejesus.com.notpadbr.activity.VisualizacaoActivity;
import com.elanilsondejesus.com.notpadbr.adapter.AdapterNota;
import com.elanilsondejesus.com.notpadbr.helper.DAONota;
import com.elanilsondejesus.com.notpadbr.helper.RecyclerItemClickListener;
import com.elanilsondejesus.com.notpadbr.model.Nota;
import com.github.clans.fab.FloatingActionButton;


import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NotasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NotasFragment extends Fragment {

    private View view;
    private Nota nota = new Nota();
    private List<Nota> notas = new ArrayList<>();
    private RecyclerView recyclerView;
    private AdapterNota adapter;
    private Dialog dialox;
    private Nota notaSelecionada = null;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public NotasFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NotasFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NotasFragment newInstance(String param1, String param2) {
        NotasFragment fragment = new NotasFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_notas, container, false);
        // criarNotasTeste();

        inicializarComponentes();
        dialox = new Dialog(getActivity());
        floatingActionButtonADD();
        floatingActionButtonPesquisa();
        carregarNotas();

        iniciarRecycleviewEdefinirLayout(notas);
        configurandoClickRecycleview();




        return view;
    }

    public void inicializarComponentes() {

    }

    public void iniciarRecycleviewEdefinirLayout(List<Nota> notas) {

        recyclerView = view.findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new AdapterNota(notas, getActivity(),dialox);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

    }

    public void criarNotasTeste() {
        Nota nota = new Nota("Fazer compras");
        notas.add(nota);
        nota = new Nota("Fazer compras");
        notas.add(nota);
        nota = new Nota("Fazer compras");
        notas.add(nota);
        nota = new Nota("Fazer compras");
        notas.add(nota);
        nota = new Nota("Fazer compras");
        notas.add(nota);
        nota = new Nota("Fazer compras");
        notas.add(nota);
        nota = new Nota("Fazer compras");
        notas.add(nota);
        nota = new Nota("Fazer compras");
        notas.add(nota);
        nota = new Nota("Fazer compras");
        notas.add(nota);
        nota = new Nota("Fazer compras");
        notas.add(nota);
        nota = new Nota("Fazer compras");
        notas.add(nota);
        nota = new Nota("Fazer compras");
        notas.add(nota);
        nota = new Nota("Fazer compras");
        notas.add(nota);
        nota = new Nota("Fazer compras");
        notas.add(nota);
        nota = new Nota("Fazer compras");
        notas.add(nota);
        nota = new Nota("Fazer compras");
        notas.add(nota);
        nota = new Nota("Fazer compras");
        notas.add(nota);
        nota = new Nota("Fazer compras");
        notas.add(nota);
        nota = new Nota("Fazer compras");
        notas.add(nota);
        nota = new Nota("Fazer compras");
        notas.add(nota);
        nota = new Nota("Fazer compras");
        notas.add(nota);
        nota = new Nota("Fazer compras");
        notas.add(nota);
        nota = new Nota("Fazer compras");
        notas.add(nota);
        nota = new Nota("Fazer compras");
        notas.add(nota);
        nota = new Nota("Fazer compras");
        notas.add(nota);
        nota = new Nota("Fazer compras");
        notas.add(nota);
        nota = new Nota("Fazer compras");
        notas.add(nota);
        nota = new Nota("Fazer compras");
        notas.add(nota);
        nota = new Nota("Fazer compras");
        notas.add(nota);
        nota = new Nota("Fazer compras");
        notas.add(nota);
        nota = new Nota("Fazer compras");
        notas.add(nota);
        nota = new Nota("Fazer compras");
        notas.add(nota);
        nota = new Nota("Fazer compras");
        notas.add(nota);
        nota = new Nota("Fazer compras");
        notas.add(nota);
        nota = new Nota("Fazer compras");
        notas.add(nota);
        nota = new Nota("Fazer compras");
        notas.add(nota);
        nota = new Nota("Fazer compras");
        notas.add(nota);
        nota = new Nota("Fazer compras");
        notas.add(nota);
        nota = new Nota("Fazer compras");
        notas.add(nota);
        nota = new Nota("Fazer compras");
        notas.add(nota);
        nota = new Nota("Fazer compras");
        notas.add(nota);
        nota = new Nota("Fazer compras");
        notas.add(nota);
        nota = new Nota("Fazer compras");
        notas.add(nota);

        Toast.makeText(getActivity(), "carrega", Toast.LENGTH_SHORT).show();

    }

    public void configurandoClickRecycleview() {
        //Adicionar evento de clique
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(
                getActivity(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //Recuperar tarefa para edicao
                notaSelecionada = notas.get(position);
                 enviarDadosSelecionado(notaSelecionada);
//

            }

            @Override
            public void onLongItemClick(View view, int position) {
                //Recuperar tarefa para edicao
                 notaSelecionada = notas.get(position);
//                        dialogCustom(notaSelecionada);
                //teste(notaSelecionada);
                opcoes(notaSelecionada);

            }

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        }
        ));
    }
    public void enviarDadosEditar(){
        // enviar dados para serem editados/alterados
     if(notaSelecionada != null){
         Intent intent = new Intent(getActivity(),EditorActivity.class);
         intent.putExtra("id",notaSelecionada.getId());
         intent.putExtra("titulo",notaSelecionada.getTitulo());
         intent.putExtra("texto",notaSelecionada.getTexto());
         intent.putExtra("data",notaSelecionada.getData());
         intent.putExtra("cor",notaSelecionada.getCordeFundo());
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


    public void floatingActionButtonADD() {
        //recyclerView = view.findViewById(R.id.recyclerView);
        FloatingActionButton fab = view.findViewById(R.id.fabmenu_itemadd);
//        fab.attachToRecyclerView(recyclerView);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), EditorActivity.class);
                intent.putExtra("enviarDados", false);
                startActivity(intent);
            }
        });
    }


    public void floatingActionButtonPesquisa() {
        // recyclerView = view.findViewById(R.id.recyclerView);
        FloatingActionButton fab = view.findViewById(R.id.fabmenu_Pesquisaitem);
        //fab.attachToRecyclerView(recyclerView);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PesquisaActivity.class);
                intent.putExtra("exibir", false);
                startActivity(intent);
            }
        });
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
        Intent intent = new Intent(getActivity(), VisualizacaoActivity.class);
        intent.putExtra("id", nota.getId());
        intent.putExtra("titulo", nota.getTitulo());
        intent.putExtra("texto", nota.getTexto());
        intent.putExtra("data", nota.getData());
        intent.putExtra("cor", nota.getCordeFundo());
        intent.putExtra("caminhoImg", nota.getCaminhoImg());
        intent.putExtra("status", nota.getStatus());
        //Toast.makeText(getActivity(), "cami: "+nota.getCaminhoImg(), Toast.LENGTH_SHORT).show();

       startActivity(intent);
    }

    public void carregarNotas() {
        DAONota dao = new DAONota(getActivity());
        for (Nota not : dao.listar()) {
            notas.add(not);
            nota.setCaminhoImg(not.getCaminhoImg());

        }

    }

    public void deletar(final Nota notaSelecionada) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Nota: "+notaSelecionada.getTitulo());
        builder.setMessage("Deseja realemente excluir ?");
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DAONota dao = new DAONota(getActivity());
                notaSelecionada.setStatus(0);
                if (dao.atualizar(notaSelecionada)) {
                    recarregar();

//            adapter.notifyDataSetChanged();
                    Toast.makeText(getActivity(), "Excluido so sucesso", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "Erro ao Excluido ", Toast.LENGTH_SHORT).show();

                }
            }
        });
        builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {


            }
        });
        builder.create();
        builder.show();


    }

    public void pesquisarNotas(String texto) {
        //Log.d("pesquisa",  texto );

        List<Nota> listarNostasBusca = new ArrayList<>();

        for (Nota not : notas) {

            if (not.getTitulo() != null) {
                String nome = not.getTitulo().toLowerCase();
                String mensagem = not.getTexto().toLowerCase();

                if (nome.contains(texto) || mensagem.contains(texto)) {
                    listarNostasBusca.add(not);
                }
            } else {
                String nome = not.getTitulo().toLowerCase();
                String mensagem = not.getTexto().toLowerCase();

                if (nome.contains(texto) || mensagem.contains(texto)) {
                    listarNostasBusca.add(not);
                }
            }


        }

        adapter = new AdapterNota(listarNostasBusca, getActivity());
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    public void recarregar() {
        carregarNotas();
        List<Nota> notas = new ArrayList<>();
        DAONota dao = new DAONota(getActivity());
        for (Nota nota : dao.listar()) {
            notas.add(nota);
        }
        iniciarRecycleviewEdefinirLayout(notas);
        adapter.notifyDataSetChanged();
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
                Intent intent = new Intent(getActivity(), EditorActivity.class);
                intent.putExtra("enviarDados", false);
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


    @Override
    public void onPause() {
        super.onPause();
        notas.clear();


    }

    public void compartilhar() {

        Intent intent = new Intent(Intent.ACTION_SEND);


        intent.putExtra(Intent.EXTRA_SUBJECT,notaSelecionada.getTitulo());
        intent.putExtra(Intent.EXTRA_TEXT, notaSelecionada.getTexto());
        intent.setType("text/plain");
        startActivity(Intent.createChooser(intent, "Compartilhar: "+notaSelecionada.getTitulo()));




    }


    @Override
    public void onStart() {
        super.onStart();

        recarregar();

//        DAONota not = new DAONota(getActivity());
//        notas.clear();
//        for(Nota  nota: not.listar()){
//            notas.add(nota);
//        }


    }



}