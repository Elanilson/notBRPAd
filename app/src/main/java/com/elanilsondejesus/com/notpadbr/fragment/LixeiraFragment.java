package com.elanilsondejesus.com.notpadbr.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.elanilsondejesus.com.notpadbr.R;
import com.elanilsondejesus.com.notpadbr.adapter.AdapterNota;
import com.elanilsondejesus.com.notpadbr.helper.DAONota;
import com.elanilsondejesus.com.notpadbr.helper.RecyclerItemClickListener;
import com.elanilsondejesus.com.notpadbr.model.Nota;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LixeiraFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LixeiraFragment extends Fragment {

    private View view;
    private Nota nota = new Nota();
    private List<Nota> notas = new ArrayList<>();
    private AdapterNota adapter;
    private Nota notaSelecionada = null;


    private RecyclerView recyclerView;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";



    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LixeiraFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LixeiraFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LixeiraFragment newInstance(String param1, String param2) {
        LixeiraFragment fragment = new LixeiraFragment();
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
        view= inflater.inflate(R.layout.fragment_lixeira, container, false);
        carregarNotas();
        iniciarRecycleviewEdefinirLayout(notas);
        configurandoClickRecycleview();


    return view;
    }
    public void iniciarRecycleviewEdefinirLayout(List<Nota> notas) {

        recyclerView = view.findViewById(R.id.recyclerviewLixeira);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new AdapterNota(notas, getActivity());
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
                notaSelecionada = notas.get(position);
              //  enviarDadosSelecionado(notaSelecionada);
//

            }

            @Override
            public void onLongItemClick(View view, int position) {
                //Recuperar tarefa para edicao
                notaSelecionada = notas.get(position);
//                        dialogCustom(notaSelecionada);
                //teste(notaSelecionada);
                //opcoes(notaSelecionada);
                Toast.makeText(getActivity(), "Status: "+notaSelecionada.getStatus(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        }
        ));
    }
    public void carregarNotas() {
        DAONota dao = new DAONota(getActivity());
        for (Nota not : dao.listarInativo()) {
            notas.add(not);
            nota.setCaminhoImg(not.getCaminhoImg());

        }

    }

}