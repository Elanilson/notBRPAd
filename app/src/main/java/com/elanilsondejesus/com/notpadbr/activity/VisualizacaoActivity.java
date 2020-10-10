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

import com.elanilsondejesus.com.notpadbr.helper.DAONota;
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
       // receberdadosTema();
//        carregartema();
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
        carregarFotoTema();


        FloatingActionButton floatingActionButton = findViewById(R.id.fabButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               enviarDados();
            }
        });


    }
//    public void receberdadosTema(){
//        Bundle bundle = getIntent().getExtras();
//        imagemteste = bundle.getInt("imagem");
//        // da uma olhada depois nisso
//        if(imagemTema != null){
//            imagemTema.setImageResource(imagemteste);
//        }
//
//
//    }
//    public void carregartema(){
//        /*
//        verifica se e diferente de 0 se for ele vai carregar o caminho da img
//         */
//       int caminho =  0;
//       caminho = nota.getCaminhoImg();
//        if(caminho != 0 ){
//            imagemTema.setImageResource(nota.getCaminhoImg());
//        }else{
//           // imagemTema.setImageResource(imagemteste);
//        }
//    }
    public void carregarFotoTema(){
        if(nota.getCaminhoImg() != 0){
            /*
            se o caminho da imagem existir no banco de dados ele sera exibida
             */
            imagemTema.setImageResource(nota.getCaminhoImg());
        }else{
            imagemTema.setImageResource(R.drawable.imagem4);
        }
    }
    public void recebendoDados(){
        Bundle bundle = getIntent().getExtras();
        Long id = null ;

          id = bundle.getLong("id") ;
           String titulo = bundle.getString("titulo");
           String texto = bundle.getString("texto");
           String data = bundle.getString("data");
           int caminhoImg = bundle.getInt("caminhoImg");
           nota.setId(id);
           nota.setTitulo(titulo);
           nota.setTexto(texto);
           nota.setData(data);
           nota.setCaminhoImg(caminhoImg);

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

                Intent intent = new Intent(VisualizacaoActivity.this,TemaActivity.class);
                intent.putExtra("id",nota.getId());
                startActivity(intent);
//                finish();
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
        intent.putExtra("data",nota.getData());
        intent.putExtra("caminhoImg",nota.getCaminhoImg());
        intent.putExtra("editar",true);
        startActivity(intent);
        finish();
    }




    public void inicializarComponentes(){
//        campoTitulo = findViewById(R.id.editTitulo);
        campoTexto = findViewById(R.id.visuTexto);
        imagemTema = findViewById(R.id.imagemTema);

    }
    @Override
    protected void onResume() {
        super.onResume();

       // carregarFotoTema();



    }




    public void recarregarTema(){
        DAONota dao = new DAONota(getApplicationContext());
        for(Nota n: dao.listar()){
            if(n.getId() == nota.getId()){
                imagemTema.setImageResource(n.getCaminhoImg());
            }

        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        recarregarTema();

    }
}