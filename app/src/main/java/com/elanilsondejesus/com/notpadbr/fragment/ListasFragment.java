package com.elanilsondejesus.com.notpadbr.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.elanilsondejesus.com.notpadbr.R;
import com.elanilsondejesus.com.notpadbr.activity.EditorActivity;
import com.elanilsondejesus.com.notpadbr.adapter.AdapterLista;
import com.elanilsondejesus.com.notpadbr.adapter.AdapterNota;
import com.elanilsondejesus.com.notpadbr.helper.DAOLista;
import com.elanilsondejesus.com.notpadbr.helper.DAONota;
import com.elanilsondejesus.com.notpadbr.helper.DataUtils;
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
            iniciarRecycleviewEdefinirLayout();
        return view;
    }
    public void dialogCustom (){
        final BottomSheetDialog sheetDialog = new BottomSheetDialog( getActivity(),
                R.style.Theme_Design_BottomSheetDialog);

        View sheetView = LayoutInflater.from(getActivity())
                .inflate(R.layout.dialogadiconarlista,(LinearLayout)view.findViewById(R.id.layoutSheet));

        final EditText nome;
        nome = sheetView.findViewById(R.id.editTextTextNomeLista);


        sheetView.findViewById(R.id.buttonsalvarLista).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // recebe o nome da lista
                // passa o nome da lista e salva
                String nomeLista ="";

                nomeLista =nome.getText().toString();
                salvar(nomeLista);
                sheetDialog.dismiss();
            }
        });

        sheetDialog.setContentView(sheetView);
        sheetDialog.show();
    }
    public void salvar(String nome){
        DAOLista dao = new DAOLista(getActivity());
        Lista lista = new Lista();
       if(nome != null || !nome.isEmpty()){
           lista.setTitulo(nome);
       }
       lista.setData(DataUtils.getDataAtual());
        if(dao.salvar(lista)){
            Toast.makeText(getActivity(), "Sucesso ao salvar", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getActivity(), "Erro ao salvar", Toast.LENGTH_SHORT).show();

        }
    }
    public void iniciarRecycleviewEdefinirLayout(){

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
                dialogCustom();
            }
        });
    }
}