package com.elanilsondejesus.com.notpadbr.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import androidx.palette.graphics.Palette;


import android.content.Intent;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.elanilsondejesus.com.notpadbr.R;

import com.elanilsondejesus.com.notpadbr.model.Nota;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class VisualizacaoActivity extends AppCompatActivity {
    private Nota nota = new Nota();
    private List<Nota> notas = new ArrayList<>();
    private TextView  campoTexto;
    private ImageView imagemTema ;
    private int imagemteste =0;





    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizacao);
        receberdadosTema();
        recebendoDados();
        inicializarComponentes();



        // atribuindo valor aos campos
        campoTexto.setText(nota.getTexto());



        //configurando toolbar
        final Toolbar toolbar = findViewById(R.id.toolbar);
        //toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        toolbar.setTitle(nota.getTitulo());
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //configulando CollapsingToolbarLayout

        final CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapse);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.imagem1);
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(@Nullable Palette palette) {
                if(palette !=null){
                    collapsingToolbarLayout.setContentScrimColor(palette.getDarkMutedColor(R.attr.color));

                }
            }
        });
        // inicia com a imagegm padrao fornecida abaixo
        imagemTema = findViewById(R.id.imagemTema);
        imagemTema.setImageResource(R.drawable.imagem4);
        FloatingActionButton floatingActionButton = findViewById(R.id.fabButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               enviarDados();
            }
        });


    }
    public void receberdadosTema(){
        Bundle bundle = getIntent().getExtras();
        imagemteste = bundle.getInt("imagem");
        // da uma olhada depois nisso
        if(imagemTema != null){
            imagemTema.setImageResource(imagemteste);
        }


    }
    public void recebendoDados(){
        Bundle bundle = getIntent().getExtras();
        Long id = bundle.getLong("id");
        String titulo = bundle.getString("titulo");
        String texto = bundle.getString("texto");
        nota.setId(id);
        nota.setTitulo(titulo);
        nota.setTexto(texto);
//        Toast.makeText(this, "id: "+nota.getId()+" titulo: "+nota.getTitulo()+" texto: "+nota.getTexto(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_visualizacao,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.tema:
                startActivity(new Intent(VisualizacaoActivity.this,TemaActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void enviarDados(){
        // enviar dados para serem editados/alterados
        Intent intent = new Intent(VisualizacaoActivity.this,EditorActivity.class);
        intent.putExtra("id",nota.getId());
        intent.putExtra("titulo",nota.getTitulo());
        intent.putExtra("texto",nota.getTexto());
        intent.putExtra("editar",true);
        startActivity(intent);
    }




    public void inicializarComponentes(){
//        campoTitulo = findViewById(R.id.editTitulo);
        campoTexto = findViewById(R.id.visuTexto);

    }

    @Override
    protected void onStart() {
        super.onStart();
    // carregando as imagens
          if(imagemteste != 0){
              imagemTema.setImageResource(imagemteste);
          }else{
              imagemTema.setImageResource(R.drawable.imagem1);
          }


    }
}