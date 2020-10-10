package com.elanilsondejesus.com.notpadbr.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.palette.graphics.Palette;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.elanilsondejesus.com.notpadbr.R;
import com.elanilsondejesus.com.notpadbr.adapter.AdapterItemLista;
import com.elanilsondejesus.com.notpadbr.helper.DAOItemLista;
import com.elanilsondejesus.com.notpadbr.helper.DAOLista;
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
    private Dialog dialOpcoes;
    private  Long idLista=null;
    Boolean ativo = false;
    private  Lista lista = new Lista();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_lista);
        recebendoDados();
        carregarItens();
        iniciarRecycleviewEdefinirLayout(itens);
       // configurandoClickRecycleview();
            carregarLista();
        dialox = new Dialog(this);
        //configurando toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(lista.getTitulo());
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
        floatingActionButton.setBackgroundColor(getResources().getColor(R.color.colorAccent));

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
               // ItemLista item = itens.get(position);

              //  Toast.makeText(VisualizarListaActivity.this, "id"+item.getIdLista(), Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onLongItemClick(View view, int position) {
                //Recuperar tarefa para edicao

                ItemLista item = itens.get(position);
                //opcoes(item);

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
    public void opcoes(final ItemLista item , Context context){
        dialOpcoes = new Dialog(context);
        dialOpcoes.setContentView(R.layout.opcoes_itens);
        dialOpcoes.getWindow().setBackgroundDrawable( new ColorDrawable(Color.TRANSPARENT));
        final TextView campoCriar = dialOpcoes.findViewById(R.id.textViewciraritem);
        final TextView campoEditar = dialOpcoes.findViewById(R.id.textViewEditarItem);
        final TextView campoDeletar = dialOpcoes.findViewById(R.id.textViewDeletarlistitem);
        Button cancelar = dialOpcoes.findViewById(R.id.buttoncancelaropcoes);
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                deletar(nota);
                dialOpcoes.dismiss();
            }
        });

        campoCriar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adicionarItem();

            }


        });
        campoEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // String titulo = campoTitulo.getText().toString();

            }


        });
        campoDeletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // String titulo = campoTitulo.getText().toString();

            }


        });

        dialOpcoes.show();


    }
    public  void ativatOpcoes(Boolean ativo, Context context,ItemLista item){
       this.ativo = ativo;
        if(ativo){
//            Toast.makeText(context, "ok", Toast.LENGTH_SHORT).show();

        }
    }
    public void adicionarItem(){
        dialox.setContentView(R.layout.adicionar_item);
        dialox.getWindow().setBackgroundDrawable( new ColorDrawable(Color.TRANSPARENT));
       final EditText campoTitulo = dialox.findViewById(R.id.editnovoItem);
        ImageView close = dialox.findViewById(R.id.imageViewCloseItem);
       close.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               dialox.dismiss();
           }
       });
       Button salvarnovoPeso = dialox.findViewById(R.id.buttonSalvarNovoPEsoAtual);
        itens.clear();
        salvarnovoPeso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            String titulo = campoTitulo.getText().toString();
            salvarItem(titulo,itemLista.getIdLista());
            campoTitulo.setText("");

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

//            Toast.makeText(this, "Salvo com sucesso", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Erro ao salvar item", Toast.LENGTH_SHORT).show();
        }
    }
    public void recebendoDados(){
        Bundle bundle = getIntent().getExtras();
        Long id = bundle.getLong("idlista");

        itemLista.setIdLista(id);
        idLista=id;
        lista.setId(id);
//        Toast.makeText(this, "TEste:"+teste, Toast.LENGTH_SHORT).show();
//        Toast.makeText(this, "TEste:"+teste, Toast.LENGTH_SHORT).show();

//        Toast.makeText(this, "id: "+nota.getId()+" titulo: "+nota.getTitulo()+" texto: "+nota.getTexto(), Toast.LENGTH_SHORT).show();
    }
    public void carregarLista(){
        /*
        pega somente o titulo da nome selecionada
         */

        DAOLista dao = new DAOLista(getApplicationContext());
        for(Lista list: dao.listar()){
            if(list.getId() == idLista){
                lista.setTitulo(list.getTitulo());

            }

        }
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