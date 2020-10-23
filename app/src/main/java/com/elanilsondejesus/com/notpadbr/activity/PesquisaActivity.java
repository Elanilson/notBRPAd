package com.elanilsondejesus.com.notpadbr.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.elanilsondejesus.com.notpadbr.R;
import com.elanilsondejesus.com.notpadbr.adapter.AdapterLista;
import com.elanilsondejesus.com.notpadbr.adapter.AdapterNota;
import com.elanilsondejesus.com.notpadbr.helper.DAOLista;
import com.elanilsondejesus.com.notpadbr.helper.DAONota;
import com.elanilsondejesus.com.notpadbr.helper.RecyclerItemClickListener;
import com.elanilsondejesus.com.notpadbr.model.Lista;
import com.elanilsondejesus.com.notpadbr.model.Nota;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.List;

public class PesquisaActivity extends AppCompatActivity {
    private MaterialSearchView searchView;
    private AdapterNota adapterNota;
    private List<Nota> notas = new ArrayList<>();
    private RecyclerView recyclerView;
    private AdapterLista adapterLista;
    private List<Lista> listas = new ArrayList<>();
    private Boolean exibir = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisa);
        recebendoDados();
        // pesquisar
        searchView = findViewById(R.id.searchPesquisa);
//        ///configuraçzão da toolbar
        Toolbar toolbar = findViewById(R.id.toolbarPesquisa);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setElevation(0);
        if(exibir){
        carregarListas();
        iniciarRecycleviewEdefinirLayoutLista(listas);
        configurandoClickRecycleviewLista();
//            Toast.makeText(this, "?"+exibir, Toast.LENGTH_SHORT).show();
//            Toast.makeText(this, "?"+exibir, Toast.LENGTH_SHORT).show();
        }else{
            carregarNotas();
            iniciarRecycleviewEdefinirLayout(notas);
            configurandoClickRecycleview();
        }




        //Listener para o search view
        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {

            }

            @Override
            public void onSearchViewClosed() {
                Toast.makeText(PesquisaActivity.this, "close", Toast.LENGTH_SHORT).show();

                if(exibir){

                recerrgarLista();
                }else{
                    recarregar();
                }

            }
        });

//Listener para caixa de texto
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //Log.d("evento", "onQueryTextSubmit");
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {



                        if( newText != null && !newText.isEmpty() ){

                            if(exibir){
                            pesquisarListas( newText.toLowerCase() );

                            }else{
                                pesquisarNotas( newText.toLowerCase() );
                            }
                        }





                return true;
            }
        });
    }
    public void recebendoDados(){
        Bundle bundle = getIntent().getExtras();
        exibir = bundle.getBoolean("exibir");
        // se exibir for true quer dizer que e lista
        // se for false e nota

        if(exibir) {
            exibir= true;

        }


    }
    public void configurandoClickRecycleview(){
        //Adicionar evento de clique
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(

                PesquisaActivity.this, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //Recuperar tarefa para edicao
                Nota notaSelecionada =notas.get(position);
                //enviarDadosSelecionado(notaSelecionada);

            }

            @Override
            public void onLongItemClick(View view, int position) {
                //Recuperar tarefa para edicao
                List<Nota> notasAtualizada =adapterNota.getNotas();
                Nota notaSelecionada =notasAtualizada.get(position);
//                        dialogCustom(notaSelecionada);

                Toast.makeText(PesquisaActivity.this, "Titulo: "+notaSelecionada.getTitulo(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        }
        ));
    }
    public void configurandoClickRecycleviewLista(){
        //Adicionar evento de clique
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(
                PesquisaActivity.this, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //Recuperar tarefa para edicao
                Lista lista = listas.get(position);
                Intent intent = new Intent(PesquisaActivity.this, VisualizarListaActivity.class);
                intent.putExtra("idlista",lista.getId());
                startActivity(intent);

            }

            @Override
            public void onLongItemClick(View view, int position) {
                //Recuperar tarefa para edicao
                List<Lista> listaAtualizada = adapterLista.getListas();
                Lista lista = listaAtualizada.get(position);
              //  opcoes(lista);
                Toast.makeText(PesquisaActivity.this, "Lista:"+lista.getTitulo(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        }
        ));
    }



    public void recarregar(){
        notas.clear();
        carregarNotas();
        List<Nota> notas = new ArrayList<>();
        DAONota dao = new DAONota(this);
        for(Nota  nota: dao.listar()){
            notas.add(nota);
        }
        iniciarRecycleviewEdefinirLayout(notas);
        adapterNota.notifyDataSetChanged();
    }
    public void recerrgarLista(){
        DAOLista dao = new DAOLista(PesquisaActivity.this);
        carregarListas();
        List<Lista> lista = new ArrayList<>();
        for(Lista  list: dao.listar()){
            lista.add(list);
        }
        iniciarRecycleviewEdefinirLayoutLista(lista);
        adapterLista.notifyDataSetChanged();
    }
    public void pesquisarNotas(String texto){
        //Log.d("pesquisa",  texto );

        List<Nota> listarNostasBusca = new ArrayList<>();

        for ( Nota not : notas ){

            if( not.getTitulo() != null ){
                String nome = not.getTitulo().toLowerCase();
                String mensagem = not.getTexto().toLowerCase();

                if( nome.contains( texto ) || mensagem.contains( texto ) ){
                    listarNostasBusca.add( not );
                }
            }else {
                String nome = not.getTitulo().toLowerCase();
                String mensagem = not.getTexto().toLowerCase();

                if( nome.contains( texto ) || mensagem.contains( texto ) ){
                    listarNostasBusca.add( not );
                }
            }



        }

        adapterNota = new AdapterNota(listarNostasBusca, this);
        recyclerView.setAdapter(adapterNota);
        adapterNota.notifyDataSetChanged();

    }
    public void pesquisarListas(String texto){
        //Log.d("pesquisa",  texto );

        List<Lista> listarListasBusca = new ArrayList<>();

        for ( Lista list : listas ){

            if( list.getTitulo() != null ){
                String nome = list.getTitulo().toLowerCase();

                if( nome.contains( texto )  ){
                    listarListasBusca.add( list );
                }
            }else {
                String nome = list.getTitulo().toLowerCase();


                if( nome.contains( texto )  ){
                    listarListasBusca.add( list);
                }
            }



        }

        adapterLista = new AdapterLista( listarListasBusca, PesquisaActivity.this);
        recyclerView.setAdapter(adapterLista);
        adapterLista.notifyDataSetChanged();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main,menu);
        //configurar bottom pesquisar
        MenuItem item = menu.findItem(R.id.opcoes);
        searchView.setMenuItem(item);
        return super.onCreateOptionsMenu(menu);
    }
    public void carregarListas(){
        DAOLista dao = new DAOLista(PesquisaActivity.this);
        for(Lista not: dao.listar()){
            listas.add(not);

        }

    }
    public void carregarNotas(){
        DAONota dao = new DAONota(getApplicationContext());
        for(Nota not: dao.listar()){
            notas.add(not);
        }

    }
    public void iniciarRecycleviewEdefinirLayoutLista(List<Lista> listas){

        recyclerView = findViewById(R.id.recyclerviewPesquisa);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(PesquisaActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        adapterLista = new AdapterLista(listas,PesquisaActivity.this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapterLista);

    }
    public void iniciarRecycleviewEdefinirLayout(List<Nota> notas){

        recyclerView = findViewById(R.id.recyclerviewPesquisa);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        adapterNota = new AdapterNota(notas,this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapterNota);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.opcoes:
                notas.clear();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}