package com.elanilsondejesus.com.notpadbr.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.palette.graphics.Palette;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.elanilsondejesus.com.notpadbr.R;
import com.elanilsondejesus.com.notpadbr.helper.DAONota;
import com.elanilsondejesus.com.notpadbr.model.Nota;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.scrat.app.richtext.RichEditText;

import java.util.ArrayList;
import java.util.List;

public class VisualizacaoActivity extends AppCompatActivity {
    private Nota nota = new Nota();
    private List<Nota> notas = new ArrayList<>();
    private TextView  campoTexto;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizacao);
        recebendoDados();
        inicializarComponentes();



        // atribuindo valor aos campos
        campoTexto.setText(nota.getTexto());



        //configurando toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(nota.getTitulo());
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

               enviarDados();
            }
        });


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
}