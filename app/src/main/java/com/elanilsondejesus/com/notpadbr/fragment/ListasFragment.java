package com.elanilsondejesus.com.notpadbr.fragment;

import android.app.Dialog;
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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.elanilsondejesus.com.notpadbr.R;
import com.elanilsondejesus.com.notpadbr.activity.EditorActivity;
import com.elanilsondejesus.com.notpadbr.activity.VisualizarListaActivity;
import com.elanilsondejesus.com.notpadbr.adapter.AdapterLista;
import com.elanilsondejesus.com.notpadbr.adapter.AdapterNota;
import com.elanilsondejesus.com.notpadbr.helper.DAOItemLista;
import com.elanilsondejesus.com.notpadbr.helper.DAOLista;
import com.elanilsondejesus.com.notpadbr.helper.DAONota;
import com.elanilsondejesus.com.notpadbr.helper.DataUtils;
import com.elanilsondejesus.com.notpadbr.helper.RecyclerItemClickListener;
import com.elanilsondejesus.com.notpadbr.model.ItemLista;
import com.elanilsondejesus.com.notpadbr.model.Lista;
import com.elanilsondejesus.com.notpadbr.model.Nota;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.melnykov.fab.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListasFragment extends Fragment {
    private View view;
    private RecyclerView recyclerView;
    private AdapterLista adapter;
    private List<Lista> listas = new ArrayList<>();
    private Lista lista = new Lista();
    private Dialog dialox;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ListasFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListasFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListasFragment newInstance(String param1, String param2) {
        ListasFragment fragment = new ListasFragment();
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
        view= inflater.inflate(R.layout.fragment_listas, container, false);
            floatingActionButton();
            carregarListas();
            iniciarRecycleviewEdefinirLayout(listas);
            configurandoClickRecycleview();
        dialox = new Dialog(getActivity());
        return view;
    }
//    public void dialogCustom (){
//        final BottomSheetDialog sheetDialog = new BottomSheetDialog( getActivity(),
//                R.style.Theme_Design_BottomSheetDialog);
//
//        View sheetView = LayoutInflater.from(getActivity())
//                .inflate(R.layout.dialogadiconarlista,(LinearLayout)view.findViewById(R.id.layoutSheet));
//
//        final EditText nome;
//        nome = sheetView.findViewById(R.id.editTextTextNomeLista);
//
//
//        sheetView.findViewById(R.id.buttonsalvarLista).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // recebe o nome da lista
//                // passa o nome da lista e salva
//                String nomeLista ="";
//
//                nomeLista =nome.getText().toString();
//                salvar(nomeLista);
//                recerrgarLista();
//                sheetDialog.dismiss();
//            }
//        });
//
//
//        sheetDialog.setContentView(sheetView);
//        sheetDialog.show();
//
//    }
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
                recerrgarLista();
                dialox.dismiss();
            }


        });

        dialox.show();


    }
    public void salvar(String nome){
        DAOLista dao = new DAOLista(getActivity());
        Lista lista = new Lista();
       if(nome != null || !nome.isEmpty()){
           lista.setTitulo(nome);
       }
        listas.clear(); /// limpar lista
       lista.setData(DataUtils.getDataAtual());
        if(dao.salvar(lista)){

            Toast.makeText(getActivity(), "Sucesso ao salvar", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getActivity(), "Erro ao salvar", Toast.LENGTH_SHORT).show();

        }
    }
    public void iniciarRecycleviewEdefinirLayout(List<Lista> listas){

        recyclerView = view.findViewById(R.id.recyclerViewLista);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new AdapterLista(listas);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

    }
    public void carregarListas(){
       DAOLista dao = new DAOLista(getActivity());
        for(Lista not: dao.listar()){
            listas.add(not);

        }

    }
    public void floatingActionButton(){
        recyclerView = view.findViewById(R.id.recyclerViewLista);
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fabButtonAdiconaritem);
        fab.attachToRecyclerView(recyclerView);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                adicionarLista();


            }
        });
    }
    public void configurandoClickRecycleview(){
        //Adicionar evento de clique
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(
                getActivity(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //Recuperar tarefa para edicao
                Lista lista = listas.get(position);
                Intent intent = new Intent(getActivity(), VisualizarListaActivity.class);
             intent.putExtra("idlista",lista.getId());
              startActivity(intent);

            }

            @Override
            public void onLongItemClick(View view, int position) {
                //Recuperar tarefa para edicao
                Lista lista = listas.get(position);
                opcoes(lista);

            }

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        }
        ));
    }
    public void recerrgarLista(){
        DAOLista dao = new DAOLista(getActivity());
        carregarListas();
        List<Lista> lista = new ArrayList<>();
        for(Lista  list: dao.listar()){
            lista.add(list);
        }
        iniciarRecycleviewEdefinirLayout(lista);
        adapter.notifyDataSetChanged();
    }
    public void opcoes(final Lista lista){
        dialox.setContentView(R.layout.opcoes);
        dialox.getWindow().setBackgroundDrawable( new ColorDrawable(Color.TRANSPARENT));
        final TextView campoDeletar = dialox.findViewById(R.id.textViewDeletarNota2);
        Button salvarnovoPeso = dialox.findViewById(R.id.buttonSalvarNovoPEsoAtual);
        campoDeletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deletar(lista);
                deletarItensDeLista(lista);
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
    public void deletarItensDeLista(Lista lista){
        DAOItemLista dao = new DAOItemLista(getActivity());
        List<ItemLista>itens = new ArrayList<>();
        /*
        lista os  as lista compara o do item com da lista
        se for compativel  ele adinar o item ao array

         */
        for(ItemLista item: dao.listar()){
            if(item.getIdLista() == lista.getId()){
                itens.add(item);

            }
        }
        /*
        vai pecorrer o novo array e depois vai dele um por um da lista de itens na lista excluida
         */
        for(ItemLista ite: itens){
            if(dao.deletar(ite)){
                Toast.makeText(getActivity(), "Item deletado: "+ite.getId(), Toast.LENGTH_SHORT).show();
            }
        }



    }
    public void deletar(Lista lista){
        DAOLista dao = new DAOLista(getActivity());

        if(dao.deletar(lista)){
            recerrgarLista();

//            adapter.notifyDataSetChanged();
            Toast.makeText(getActivity(), "Excluido so sucesso", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(getActivity(), "Erro ao Excluido ", Toast.LENGTH_SHORT).show();

        }

    }

    @Override
    public void onStart() {
        super.onStart();
        recerrgarLista();

    }
}