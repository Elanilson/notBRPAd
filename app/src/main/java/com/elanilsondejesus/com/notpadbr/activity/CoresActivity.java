package com.elanilsondejesus.com.notpadbr.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.elanilsondejesus.com.notpadbr.R;
import com.elanilsondejesus.com.notpadbr.adapter.Adaptercor;
import com.elanilsondejesus.com.notpadbr.helper.RecyclerItemClickListener;
import com.elanilsondejesus.com.notpadbr.model.ItemLista;

import java.util.ArrayList;
import java.util.List;

public class CoresActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private Adaptercor adapter;
    private List<Integer> cores = new ArrayList<>();
    private String titulo ="";
    private String texto ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cores);
        recebendoDados();
        geradorDeCor();
        iniciarRecycleviewEdefinirLayout();
        configurandoClickRecycleview();

    }
    public void iniciarRecycleviewEdefinirLayout() {

        recyclerView = findViewById(R.id.recyclerviewCores);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(CoresActivity.this,5);
        recyclerView.setLayoutManager(gridLayoutManager);
        adapter = new Adaptercor(cores);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

    }
    public void recebendoDados(){
        Bundle bundle = getIntent().getExtras();
         titulo = bundle.getString("titulo");
        texto = bundle.getString("texto");



//        Toast.makeText(this, "id: "+nota.getId()+" titulo: "+nota.getTitulo()+" texto: "+nota.getTexto(), Toast.LENGTH_SHORT).show();
    }
    public void configurandoClickRecycleview(){
        //Adicionar evento de clique
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(
                getApplicationContext(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //Recuperar tarefa para edicao
                Integer cor = cores.get(position);

                Intent intent = new Intent(CoresActivity.this,EditorActivity.class);
                intent.putExtra("titulo",titulo);
                intent.putExtra("texto",texto);
                intent.putExtra("cor",cor.intValue());
                intent.putExtra("editar",true);
                startActivity(intent);
                Toast.makeText(CoresActivity.this, "cor"+cor.intValue(), Toast.LENGTH_SHORT).show();
                finish();


            }

            @Override
            public void onLongItemClick(View view, int position) {
                //Recuperar tarefa para edicao

                //ItemLista item = itens.get(position);
                //opcoes(item);

            }

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        }
        ));
    }

    public void geradorDeCor(){

        for(Integer cor: CoresActivity.this.getResources().getIntArray(R.array.cores)) {

            cores.add(cor);

        }

    }
}