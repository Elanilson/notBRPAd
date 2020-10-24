package com.elanilsondejesus.com.notpadbr.fragment;

import android.app.Dialog;
import android.content.Context;
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
import android.widget.Toast;

import com.elanilsondejesus.com.notpadbr.R;
import com.elanilsondejesus.com.notpadbr.activity.EditorActivity;
import com.elanilsondejesus.com.notpadbr.activity.VisualizacaoActivity;
import com.elanilsondejesus.com.notpadbr.activity.VisualizarListaActivity;
import com.elanilsondejesus.com.notpadbr.adapter.AdapterListHome;
import com.elanilsondejesus.com.notpadbr.adapter.AdapterNotaFavoritas;
import com.elanilsondejesus.com.notpadbr.adapter.AdapterNotaHoje;
import com.elanilsondejesus.com.notpadbr.helper.DAOLista;
import com.elanilsondejesus.com.notpadbr.helper.DAONota;
import com.elanilsondejesus.com.notpadbr.helper.DataUtils;
import com.elanilsondejesus.com.notpadbr.helper.RecyclerItemClickListener;
import com.elanilsondejesus.com.notpadbr.model.HojeNotas;
import com.elanilsondejesus.com.notpadbr.model.Lista;
import com.elanilsondejesus.com.notpadbr.model.Nota;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PrincipalFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PrincipalFragment extends Fragment {
    private  Context context;
    private  View view;
   private AdapterNotaFavoritas adapterNotaFavoritas;
   private AdapterNotaHoje adapterNotaHoje;
   private AdapterListHome adapterListHome;
   private RecyclerView recyclerView,recyclerViewFavorito,recyclerViewHoje;
   private List<Nota> listaFavoritos = new ArrayList<>();
   private List<Nota> listHoje = new ArrayList<>();
   private List<Lista> listHomes = new ArrayList<>();
   private Lista listaSelecionada  = new Lista();
   private Nota notaSelecionada = new Nota();
    private Dialog dialox;




    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PrincipalFragment() {
        // Required empty public constructor
    }
    public PrincipalFragment(Context context) {
        // Required empty public constructor
        this.context = context;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PrincipalFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PrincipalFragment newInstance(String param1, String param2) {
        PrincipalFragment fragment = new PrincipalFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)   {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_principal, container, false);
        criarNotasTeste();
        criarNotashoje();
        criarListHome();
        iniciarRecycleviewEdefinirLayoutHoje(listHoje);
        iniciarRecycleviewEdefinirLayoutFavoritos(listaFavoritos);
        iniciarRecycleviewEdefinirLayoutList(listHomes);
        configurandoClickRecycleview();
        configurandoClickRecycleviewFavorito();
        configurandoClickRecycleviewHoje();
        dialox = new Dialog(getActivity());



        return view;
    }
    public void iniciarRecycleviewEdefinirLayoutFavoritos(List<Nota>favorito) {

        recyclerViewFavorito = view.findViewById(R.id.recyclerviewFavoritos);
        LinearLayoutManager layout = new LinearLayoutManager(getActivity());
        layout.setOrientation(RecyclerView.HORIZONTAL);
        recyclerViewFavorito.setLayoutManager(layout);

        adapterNotaFavoritas = new AdapterNotaFavoritas(favorito);
        recyclerViewFavorito.setHasFixedSize(true);
        recyclerViewFavorito.setAdapter(adapterNotaFavoritas);

    }

    public void configurandoClickRecycleview(){
        //Adicionar evento de clique
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(
                getActivity(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //Recuperar tarefa para edicao
                listaSelecionada = listHomes.get(position);
                notaSelecionada = listaFavoritos.get(position);

                if(listaSelecionada.getId() == null ){
                    adicionarLista();
               //     Toast.makeText(getActivity(), "index: "+listaSelecionada.getId(), Toast.LENGTH_SHORT).show();

                }else {
                    Intent intent = new Intent(getActivity(), VisualizarListaActivity.class);
                    intent.putExtra("idlista", listaSelecionada.getId());
                    startActivity(intent);
                }
                if(view.getId() == R.id.recyclerviewFavoritos){

                Toast.makeText(getActivity(), "Nota", Toast.LENGTH_SHORT).show();
                }





            }

            @Override
            public void onLongItemClick(View view, int position) {
                //Recuperar tarefa para edicao
//                listaSelecionada = listas.get(position);
//                opcoes(listaSelecionada);

            }

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


            }
        }
        ));
    }
    public void configurandoClickRecycleviewFavorito(){
        //Adicionar evento de clique
        recyclerViewFavorito.addOnItemTouchListener(new RecyclerItemClickListener(
                getActivity(), recyclerViewFavorito, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //Recuperar tarefa para edicao
                notaSelecionada = listaFavoritos.get(position);


                if(position == 0L){
                    Intent intent = new Intent(getActivity(), EditorActivity.class);
                    intent.putExtra("enviarDados", false);
                    startActivity(intent);
                }else{
//                    //Recuperar tarefa para edicao
                    enviarDadosSelecionado(notaSelecionada);

                    Toast.makeText(getActivity(), "else:", Toast.LENGTH_SHORT).show();

//
                }






            }

            @Override
            public void onLongItemClick(View view, int position) {
                //Recuperar tarefa para edicao
//                listaSelecionada = listas.get(position);
//                opcoes(listaSelecionada);

            }

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


            }
        }
        ));
    }
    public void configurandoClickRecycleviewHoje(){
        //Adicionar evento de clique
        recyclerViewHoje.addOnItemTouchListener(new RecyclerItemClickListener(
                getActivity(), recyclerViewHoje, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //Recuperar tarefa para edicao
                notaSelecionada = listHoje.get(position);

                Toast.makeText(getActivity(), "else:"+position, Toast.LENGTH_SHORT).show();

                if(notaSelecionada != null && notaSelecionada.getId() != null ){
//                    //Recuperar tarefa para edicao
                    enviarDadosSelecionado(notaSelecionada);

                    Toast.makeText(getActivity(), "else:", Toast.LENGTH_SHORT).show();

//
                }






            }

            @Override
            public void onLongItemClick(View view, int position) {
                //Recuperar tarefa para edicao
//                listaSelecionada = listas.get(position);
//                opcoes(listaSelecionada);

            }

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


            }
        }
        ));
    }
    public void enviarDadosSelecionado(Nota nota) {
        Toast.makeText(getActivity(), "visualizarrr", Toast.LENGTH_SHORT).show();

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
        intent.putExtra("caminhoImg", nota.getCaminhoImg());
        intent.putExtra("status", nota.getStatus());
        //Toast.makeText(getActivity(), "cami: "+nota.getCaminhoImg(), Toast.LENGTH_SHORT).show();

        startActivity(intent);
    }
    public void adicionarLista(){
        dialox.setContentView(R.layout.dialogadiconarlista);
        dialox.getWindow().setBackgroundDrawable( new ColorDrawable(Color.TRANSPARENT));
        final EditText campoTitulo = dialox.findViewById(R.id.editTextTextNomeLista);
        Button salvarnovoPeso = dialox.findViewById(R.id.buttonsalvarLista);

        salvarnovoPeso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String titulo = campoTitulo.getText().toString();
                salvar(titulo);
                criarListHome();
                dialox.dismiss();
            }


        });

        dialox.show();


    }
    public void salvar(String nome){ /// salva lista
        int contador =0;

        DAOLista dao = new DAOLista(getActivity());
        Lista lista = new Lista();
        if(nome != null || !nome.isEmpty()){
            lista.setTitulo(nome);
        }

       // listHomes.clear(); /// limpar lista
        lista.setData(DataUtils.getDataAtual());
        if(dao.salvar(lista)){
/*
depois de salvar  vamos listar novemente agora comparando como o tamanho da lista
para que passa pega a ultima lista criada que possa ser saberta/inflada
 */
                   // Toast.makeText(getActivity(), "tamanho: "+dao.listar().size(), Toast.LENGTH_SHORT).show();
            for(Lista list: dao.listar()){
                contador++;
                /*
                o contador vai sendo incrementado ate que seja listado dos os itens
                nesse caso vamos ter o numero total di itens na lista , com esse valor
                pode pegar a ultima lista criada
                 */
                if(contador == dao.listar().size()){
                    Toast.makeText(getActivity(), "id: "+list.getId(), Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(getActivity(), VisualizarListaActivity.class);
                    intent.putExtra("idlista",list.getId());
                    startActivity(intent);

                }
            }


            Toast.makeText(getActivity(), "Sucesso ao salvar", Toast.LENGTH_SHORT).show();

        }else{
            Toast.makeText(getActivity(), "Erro ao salvar", Toast.LENGTH_SHORT).show();

        }
    }
    public void iniciarRecycleviewEdefinirLayoutHoje(List<Nota> hojeNotas) {

        recyclerViewHoje = view.findViewById(R.id.recyclerviewHoje);
        LinearLayoutManager layout = new LinearLayoutManager(getActivity());
        layout.setOrientation(RecyclerView.HORIZONTAL);
        recyclerViewHoje.setLayoutManager(layout);

        adapterNotaHoje = new AdapterNotaHoje(hojeNotas);
        recyclerViewHoje.setHasFixedSize(true);
        recyclerViewHoje.setAdapter(adapterNotaHoje);

    }
    public void iniciarRecycleviewEdefinirLayoutList(List<Lista> listHome) {

        recyclerView = view.findViewById(R.id.recyclerviewListHome);
        LinearLayoutManager layout = new LinearLayoutManager(getActivity());
        layout.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView.setLayoutManager(layout);

        adapterListHome = new AdapterListHome(listHome,getActivity());
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapterListHome);

    }





    public void criarNotasTeste() {
        listaFavoritos.clear();
        Nota fatorito = new Nota(0L,"Adicione aos favoritos","acesse de maneira rápidas suas notas!",true);
        listaFavoritos.add(fatorito);
        DAONota dao = new DAONota(getActivity());
        for(Nota not: dao.listar()){
            listaFavoritos.add(not);

        }


    }
    public void criarNotashoje() {
        listHoje.clear();
        boolean temNotaHoje = true;
     DAONota dao = new DAONota(getActivity());
     for(Nota nota: dao.listar()){

         if(nota.getData().contains(DataUtils.getDataAtual())){
             listHoje.add(nota);
             temNotaHoje= false;

         }

     }
         if(temNotaHoje ? true: false) {
             HojeNotas hojeNotas = new HojeNotas("Nenhuma nota",":(");
             listHoje.add(hojeNotas);

         }




        Toast.makeText(getActivity(), "carrega", Toast.LENGTH_SHORT).show();

    }

    public void criarListHome() {
        listHomes.clear();
        Lista lista = new Lista("Adicionar lista ","",true);
        listHomes.add(lista);
        DAOLista dao = new DAOLista(getActivity());
        for(Lista list:dao.listar()){
            listHomes.add(list);

        }

    }

    @Override
    public void onPause() {
        super.onPause();
        listHoje.clear();
        listaFavoritos.clear();
        listHomes.clear();
    }

    @Override
    public void onStart() {
        super.onStart();
        criarNotasTeste();
        criarNotashoje();
        criarListHome();
    }
}