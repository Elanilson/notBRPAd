package com.elanilsondejesus.com.notpadbr.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.elanilsondejesus.com.notpadbr.R;
import com.elanilsondejesus.com.notpadbr.activity.MainActivity;
import com.elanilsondejesus.com.notpadbr.adapter.AdapterNota;
import com.elanilsondejesus.com.notpadbr.helper.DAONota;
import com.elanilsondejesus.com.notpadbr.model.Nota;
import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PrincipalFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PrincipalFragment extends Fragment {
    private  View view;
    private SmartTabLayout smartTabLayout;
    private ViewPager viewPager;
    private Context context;
//    private MaterialSearchView searchView;
    private FragmentPagerItemAdapter adapter;



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



        ///configuração da toolbar

//        Toolbar toolbar = view.findViewById(R.id.toolbar);
//        toolbar.setTitle("");
//        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
//        ((AppCompatActivity)getActivity()).getSupportActionBar().setElevation(0);
//        toolbar.setVisibility(View.GONE);
// pesquisar
//        searchView = view.findViewById(R.id.searchPesquisa);






        smartTabLayout = view.findViewById(R.id.viewPagerTab);
        viewPager = view.findViewById(R.id.viewpager);

        //configurar adapter para abas

        adapter = new FragmentPagerItemAdapter(
               getChildFragmentManager(), FragmentPagerItems.with(getActivity())
                .add("Notas", NotasFragment.class)
                .add("Listas", ListasFragment.class)
                .create()
        );


        viewPager.setAdapter(adapter);
        smartTabLayout.setViewPager(viewPager);




        return view;
    }
//public void pesquisatest(){
//
//    if(searchView != null){
//        //Listener para o search view
//        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
//            @Override
//            public void onSearchViewShown() {
//
//            }
//
//            @Override
//            public void onSearchViewClosed() {
//
//                NotasFragment fragment = (NotasFragment) adapter.getPage(0);
//                fragment.recarregar();
//
//            }
//        });
////Listener para caixa de texto
//        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                //Log.d("evento", "onQueryTextSubmit");
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                //Log.d("evento", "onQueryTextChange");
//
//                // Verifica se esta pesquisando Conversas ou Contatos
//                // a partir da tab que esta ativa
//                switch ( viewPager.getCurrentItem() ){
//                    case 0:
//                        NotasFragment notasFragment = (NotasFragment) adapter.getPage(0);
//                        if( newText != null && !newText.isEmpty() ){
//                            notasFragment.pesquisarNotas( newText.toLowerCase() );
//                        }else {
//                            notasFragment.recarregar();
//                        }
//                        break;
//                    case 1 :
//                        ListasFragment listasFragment = (ListasFragment) adapter.getPage(1);
//                        if( newText != null && !newText.isEmpty() ){
//                            listasFragment.pesquisarListas ( newText.toLowerCase() );
//                        }else {
//                            listasFragment.recerrgarLista();
//                        }
//                        break;
//                }
//
//
//
//                return true;
//            }
//        });
//
//
//    }
//}


//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        super.onCreateOptionsMenu(menu, inflater);
//        inflater.inflate(R.menu.menu_main, menu);
//        //configurar bottom pesquisar
//        MenuItem item = menu.findItem(R.id.pesquisa);
//        searchView.setMenuItem(item);
//    }

    //    public void pesquisarNotas(String texto, FragmentPagerAdapter adapter){
//        DAONota notas = new DAONota(getActivity());
//        List<Nota> notaspesquisabusca = new ArrayList<>();
//        for(Nota not: notas.listar()){
//            String titulo = not.getTitulo();
//            String mensagem = not.getTexto();
//            if(titulo.contains(texto) || mensagem.contains(texto)){
//                notaspesquisabusca.add(not);
//            }
//
//        }
//
//
//    }
    @Override
    public void onStart() {
        super.onStart();
    }
}