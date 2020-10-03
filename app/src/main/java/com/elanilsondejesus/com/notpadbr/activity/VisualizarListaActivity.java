package com.elanilsondejesus.com.notpadbr.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.palette.graphics.Palette;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.elanilsondejesus.com.notpadbr.R;
import com.elanilsondejesus.com.notpadbr.adapter.AdapterItemLista;
import com.elanilsondejesus.com.notpadbr.helper.DAOItemLista;
import com.elanilsondejesus.com.notpadbr.helper.DAONota;
import com.elanilsondejesus.com.notpadbr.helper.RecyclerItemClickListener;
import com.elanilsondejesus.com.notpadbr.model.ItemLista;
import com.elanilsondejesus.com.notpadbr.model.Lista;
import com.elanilsondejesus.com.notpadbr.model.Nota;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class VisualizarListaActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ItemLista itemLista = new ItemLista();
    private List<ItemLista> itens = new ArrayList<>();
    private AdapterItemLista adapter;
    private Dialog dialox;
    private  Long idLista=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_lista);
        recebendoDados();
        carregarItens();
        iniciarRecycleviewEdefinirLayout(itens);


        dialox = new Dialog(this);
        //configurando toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
      //  toolbar.setTitle(nota.getTitulo());
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //configulando CollapsingToolbarLayout

        final CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapse);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.fundo1);
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(@Nullable Palette palette) {
                if(palette !=null){
                    collapsingToolbarLayout.setContentScrimColor(palette.getDarkMutedColor(R.attr.colorPrimary));
                }
            }
        });
        FloatingActionButton floatingActionButton = findViewById(R.id.fabButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               adicionarItem();
            }
        });
    }
    public void iniciarRecycleviewEdefinirLayout(List<ItemLista> itens){

        recyclerView = findViewById(R.id.recyclerviewItemLista);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new AdapterItemLista(itens,getApplicationContext());
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
                ItemLista item = itens.get(position);

                Toast.makeText(VisualizarListaActivity.this, "id"+item.getIdLista(), Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onLongItemClick(View view, int position) {
                //Recuperar tarefa para edicao

            }

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        }
        ));
    }
    public void carregarItens(){
        DAOItemLista dao = new DAOItemLista(getApplicationContext());
        for(ItemLista not: dao.listar()){

            if(not.getIdLista() == idLista ){
                itens.add(not);
            }

        }


    }
    public void adicionarItem(){
        dialox.setContentView(R.layout.adicionar_item);
        dialox.getWindow().setBackgroundDrawable( new ColorDrawable(Color.TRANSPARENT));
       final EditText campoTitulo = dialox.findViewById(R.id.editnovoItem);
       Button salvarnovoPeso = dialox.findViewById(R.id.buttonSalvarNovoPEsoAtual);
        itens.clear();
        salvarnovoPeso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            String titulo = campoTitulo.getText().toString();
            salvarItem(titulo,itemLista.getIdLista());

            }


        });

        dialox.show();


    }
    public void salvarItem(String titulo, Long idlista){
        DAOItemLista dao = new DAOItemLista(getApplicationContext());
        ItemLista item = new ItemLista();
        if(titulo != null){
            item.setTitulo(titulo);
            item.setIdLista(idlista);
        }
        if(dao.salvar(item)){
            recarregar();

            Toast.makeText(this, "Salvo com sucesso", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Erro ao salvar item", Toast.LENGTH_SHORT).show();
        }
    }
    public void recebendoDados(){
        Bundle bundle = getIntent().getExtras();
        Long id = bundle.getLong("idlista");
        itemLista.setIdLista(id);
        idLista=id;

//        Toast.makeText(this, "id: "+nota.getId()+" titulo: "+nota.getTitulo()+" texto: "+nota.getTexto(), Toast.LENGTH_SHORT).show();
    }

    public void recarregar(){
       // carregarItens();
        List<ItemLista> item = new ArrayList<>();
        DAOItemLista dao = new DAOItemLista(getApplicationContext());
        for(ItemLista  ite: dao.listar()){
            if(ite.getIdLista() == idLista ){
            item.add(ite);

            }
        }
        iniciarRecycleviewEdefinirLayout(item);
        adapter.notifyDataSetChanged();
    }
    public void tess(){
        if(itens.size() !=0){
            itens.clear();
            carregarItens();
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();

    }
}