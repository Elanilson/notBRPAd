package com.elanilsondejesus.com.notpadbr.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.elanilsondejesus.com.notpadbr.R;
import com.elanilsondejesus.com.notpadbr.adapter.AdapterNota;
import com.elanilsondejesus.com.notpadbr.adapter.AdapterTema;
import com.elanilsondejesus.com.notpadbr.model.Nota;
import com.elanilsondejesus.com.notpadbr.model.Tema;


import java.util.ArrayList;
import java.util.List;

public class TemaActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private AdapterTema adapter;
    private Tema tema = new Tema();
    private List<Tema> temas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tema);
        carregarTemas();
        iniciarRecycleviewEdefinirLayout(temas);




    }

    public void iniciarRecycleviewEdefinirLayout(List<Tema> temas){

        recyclerView = findViewById(R.id.recyclerviewTema);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new AdapterTema(temas,TemaActivity.this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

    }
    public void carregarTemas(){
        Tema tema = new Tema(R.drawable.imagem1);
        temas.add(tema);
        tema = new Tema(R.drawable.imagem2);
        temas.add(tema);
        tema = new Tema(R.drawable.imagem3);
        temas.add(tema);
        tema = new Tema(R.drawable.imagem4);
        temas.add(tema);
        tema = new Tema(R.drawable.imagem5);
        temas.add(tema);
    }

}