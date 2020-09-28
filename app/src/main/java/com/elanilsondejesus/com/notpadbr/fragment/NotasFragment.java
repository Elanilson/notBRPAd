package com.elanilsondejesus.com.notpadbr.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.elanilsondejesus.com.notpadbr.R;
import com.elanilsondejesus.com.notpadbr.activity.EditorActivity;
import com.elanilsondejesus.com.notpadbr.activity.VisualizacaoActivity;
import com.elanilsondejesus.com.notpadbr.adapter.AdapterNota;
import com.elanilsondejesus.com.notpadbr.helper.DAONota;
import com.elanilsondejesus.com.notpadbr.helper.RecyclerItemClickListener;
import com.elanilsondejesus.com.notpadbr.model.Nota;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.melnykov.fab.FloatingActionButton;

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
    private AdapterNota adapter ;


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



        floatingActionButton();
        carregarNotas();
        iniciarRecycleviewEdefinirLayout();
        configurandoClickRecycleview();
        return view;
    }

    public void inicializarComponentes(){

    }
    public void iniciarRecycleviewEdefinirLayout(){

        recyclerView = view.findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new AdapterNota(notas);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

    }
    public  void criarNotasTeste(){
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
    public void configurandoClickRecycleview(){
        //Adicionar evento de clique
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(
                getActivity(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //Recuperar tarefa para edicao
                Nota notaSelecionada =notas.get(position);
                enviarDadosSelecionado(notaSelecionada);

            }

            @Override
            public void onLongItemClick(View view, int position) {
                //Recuperar tarefa para edicao
                  Nota notaSelecionada =notas.get(position);
                        dialogCustom(notaSelecionada);
            }

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        }
        ));
    }
    public void dialogCustom (final Nota nota){
        final BottomSheetDialog sheetDialog = new BottomSheetDialog( getActivity(),
                R.style.Theme_Design_BottomSheetDialog);

        View sheetView = LayoutInflater.from(getActivity())
                .inflate(R.layout.dialogopcoes,(LinearLayout)view.findViewById(R.id.layoutSheet));

        TextView titulo = sheetView.findViewById(R.id.textViewOpcoesNome);
            titulo.setText(nota.getTitulo());

        sheetView.findViewById(R.id.textViewDeletarNota).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            deletar(nota);
            configurandoClickRecycleview();
                sheetDialog.dismiss();
            }
        });

        sheetView.findViewById(R.id.textViewCompartilhar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Compartilhando.....", Toast.LENGTH_SHORT).show();
            }
        });

        sheetView.findViewById(R.id.textViewFavorites).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Favoritando....", Toast.LENGTH_SHORT).show();
            }
        });

        sheetView.findViewById(R.id.buttonclose).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sheetDialog.dismiss();
            }
        });

        sheetDialog.setContentView(sheetView);
        sheetDialog.show();
    }
    public void floatingActionButton(){
        recyclerView = view.findViewById(R.id.recyclerView);
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fabButton);
        fab.attachToRecyclerView(recyclerView);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),EditorActivity.class);
                intent.putExtra("enviarDados",false);
                startActivity(intent);
            }
        });
    }
    public void enviarDadosSelecionado(Nota nota){
        /*
        quando o usuario clicar na nota essa nota selecionada vai enviar os seus dados para outra tela
        para que seja apresentada ao usuario
         */
        Intent intent = new Intent(getActivity(),VisualizacaoActivity.class);
        intent.putExtra("id",nota.getId());
        intent.putExtra("titulo",nota.getTitulo());
        intent.putExtra("texto",nota.getTexto());
        startActivity(intent);
    }
    public void carregarNotas(){
    DAONota dao = new DAONota(getActivity());
    for(Nota not: dao.listar()){
        notas.add(not);

    }

}
    public void deletar(Nota notaSelecionada){
        DAONota dao = new DAONota(getActivity());

        if(dao.deletar(notaSelecionada)){
            Toast.makeText(getActivity(), "Excluido so sucesso", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(getActivity(), "Erro ao Excluido ", Toast.LENGTH_SHORT).show();

        }

    }

    @Override
    public void onStart() {
        super.onStart();

    }
}