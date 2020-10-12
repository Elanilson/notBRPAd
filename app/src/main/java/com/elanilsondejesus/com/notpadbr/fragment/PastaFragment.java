package com.elanilsondejesus.com.notpadbr.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.elanilsondejesus.com.notpadbr.R;
import com.elanilsondejesus.com.notpadbr.activity.EditorActivity;
import com.elanilsondejesus.com.notpadbr.activity.VisualizarListaActivity;
import com.elanilsondejesus.com.notpadbr.activity.VisualizarPastaActivity;
import com.elanilsondejesus.com.notpadbr.adapter.AdapterNota;
import com.elanilsondejesus.com.notpadbr.adapter.AdapterPasta;
import com.elanilsondejesus.com.notpadbr.helper.DAOLista;
import com.elanilsondejesus.com.notpadbr.helper.DAONota;
import com.elanilsondejesus.com.notpadbr.helper.DaoPasta;
import com.elanilsondejesus.com.notpadbr.helper.DataUtils;
import com.elanilsondejesus.com.notpadbr.helper.RecyclerItemClickListener;
import com.elanilsondejesus.com.notpadbr.model.Lista;
import com.elanilsondejesus.com.notpadbr.model.Nota;
import com.elanilsondejesus.com.notpadbr.model.Pasta;
import com.github.clans.fab.FloatingActionButton;


import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PastaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PastaFragment extends Fragment {
    private View view;
    private List<Pasta> pastas = new ArrayList<>();
    private RecyclerView recyclerView;
    private AdapterPasta adapter;
    private Dialog dialox;
    private Pasta pastaSelecionada;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PastaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PastaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PastaFragment newInstance(String param1, String param2) {
        PastaFragment fragment = new PastaFragment();
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
        view = inflater.inflate(R.layout.fragment_pasta, container, false);
        dialox = new Dialog(getActivity());
        carregarPastas();
        iniciarRecycleviewEdefinirLayout(pastas);
        configurandoClickRecycleview();
        floatingActionButton();

        return view;
    }

    public void iniciarRecycleviewEdefinirLayout(List<Pasta> pastas) {

        recyclerView = view.findViewById(R.id.recyclerviewPasta);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new AdapterPasta(pastas);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

    }
    public void configurandoClickRecycleview() {
        //Adicionar evento de clique
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(
                getActivity(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //Recuperar tarefa para edicao
                pastaSelecionada = pastas.get(position);
                Intent intent = new Intent(getActivity(), VisualizarPastaActivity.class);
                intent.putExtra("idpasta",pastaSelecionada.getId());
                startActivity(intent);

//

            }

            @Override
            public void onLongItemClick(View view, int position) {
                //Recuperar tarefa para edicao
                pastaSelecionada = pastas.get(position);
                opcoes(pastaSelecionada);
              //  editarPasta(pastaSelecionada);


            }

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        }
        ));
    }
    public void opcoes(final Pasta pasta) {
        dialox.setContentView(R.layout.opcoespasta);
        dialox.setCancelable(false);
        dialox.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        final TextView campoDeletar = dialox.findViewById(R.id.textViewDeletarPasta);
        final TextView campoAdicionar = dialox.findViewById(R.id.textViewCriarpasta);
        final TextView campoEditar = dialox.findViewById(R.id.textViewEditarPasta);

        Button cancelar = dialox.findViewById(R.id.buttonCancelarPasta);

        campoEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              editarPasta(pasta);
                //dialox.dismiss();
            }
        });
        campoAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adicionarPasta();
                //dialox.dismiss();
            }
        });
        campoDeletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deletar(pasta);
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
    public void deletar(final Pasta pasta) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Nota: "+pasta.getTitulo());
        builder.setMessage("Deseja realemente' excluir ?");
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
               DaoPasta dao = new DaoPasta(getActivity());

                if (dao.deletar(pasta)) {
                    recerrgarPasta();

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
    public void editarPasta(final Pasta pasta){
        dialox.setContentView(R.layout.dialogaddpasta);
        dialox.getWindow().setBackgroundDrawable( new ColorDrawable(Color.TRANSPARENT));
        final EditText campoTitulo = dialox.findViewById(R.id.editTextaddPasta);
        final TextView campoAlterar = dialox.findViewById(R.id.textViewAlteraTitulo);
        Button salvarPasta = dialox.findViewById(R.id.buttonsalvarPasta);
        campoAlterar.setText("Alterar Titulo:");
        salvarPasta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String titulo = campoTitulo.getText().toString();
                pasta.setTitulo(titulo);
               DaoPasta dao = new DaoPasta(getActivity());
               if(dao.atualizar(pasta)){
                   Toast.makeText(getActivity(), "Atualizado com sucesso", Toast.LENGTH_SHORT).show();

               }
                recerrgarPasta();
                dialox.dismiss();
            }


        });

        dialox.show();
    }
    public void carregarPastas() {
       DaoPasta dao = new DaoPasta(getActivity());
        for (Pasta past : dao.listar()) {
            pastas.add(past);


        }

    }

    public void floatingActionButton(){
        recyclerView = view.findViewById(R.id.recyclerviewPasta);
       com.melnykov.fab.FloatingActionButton fab =  view.findViewById(R.id.fabPastaAdd);
        fab.attachToRecyclerView(recyclerView);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adicionarPasta();
            }
        });
    }
    public void adicionarPasta(){
        dialox.setContentView(R.layout.dialogaddpasta);
        dialox.getWindow().setBackgroundDrawable( new ColorDrawable(Color.TRANSPARENT));
        final EditText campoTitulo = dialox.findViewById(R.id.editTextaddPasta);
        Button salvarPasta = dialox.findViewById(R.id.buttonsalvarPasta);

        salvarPasta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String titulo = campoTitulo.getText().toString();
                salvar(titulo);
                recerrgarPasta();
                dialox.dismiss();
            }


        });

        dialox.show();


    }
    public void recerrgarPasta(){
        DaoPasta dao = new DaoPasta(getActivity());
        carregarPastas();
        List<Pasta> pasta = new ArrayList<>();
        for(Pasta  past: dao.listar()){
            pasta.add(past);
        }
        iniciarRecycleviewEdefinirLayout(pasta);
        adapter.notifyDataSetChanged();
    }
    public void salvar(String nome){
       DaoPasta dao = new DaoPasta(getActivity());
        Pasta pasta = new Pasta();
        if(nome != null && !nome.isEmpty()){
            pasta.setTitulo(nome);
        }

        pastas.clear(); /// limpar lista
       pasta.setData(DataUtils.getDataAtual());
        if(dao.salvar(pasta)){

            Toast.makeText(getActivity(), "Sucesso ao salvar", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getActivity(), "Erro ao salvar", Toast.LENGTH_SHORT).show();

        }
    }

}