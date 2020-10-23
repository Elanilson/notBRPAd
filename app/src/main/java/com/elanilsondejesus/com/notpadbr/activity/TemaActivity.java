package com.elanilsondejesus.com.notpadbr.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

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
    private Long id;
    private Long idpasta;
    private String lista ="";
    private Dialog dialox;
    private Boolean temaselecionado =false;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tema);
        carregarTemas();
        iniciarRecycleviewEdefinirLayout(temas);


           dialox = new Dialog(this);





}


    public void test(Context context , Boolean selecioonado){
      if(selecioonado){
         temaselecionado = selecioonado;

      }



    }
    public void confirmacaoTemaSelecionado(Context context){

    if(temaselecionado){
    dialox.setContentView(R.layout.confirmarcao);
    dialox.getWindow().setBackgroundDrawable( new ColorDrawable(Color.TRANSPARENT));


    dialox.show();
}


    }





    public void iniciarRecycleviewEdefinirLayout(List<Tema> temas){
        recebendoIdParaTEma();
        recyclerView = findViewById(R.id.recyclerviewTema);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new AdapterTema(temas,TemaActivity.this,id,dialox,idpasta,lista);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

    }
    public void recebendoIdParaTEma(){
        Bundle bundle = getIntent().getExtras();
        id = bundle.getLong("id");
        idpasta = bundle.getLong("idpasta");
        lista = bundle.getString("lista");
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