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
        inicializarComponentes();
        atribuicao();

        recebendoDados();

        // atribuindo valor aos campos
        campoTexto.setText(nota.getTexto());



        //configurando toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(nota.getTitulo());
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //configulando CollapsingToolbarLayout

        final CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapse);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.testefoto);
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(@Nullable Palette palette) {
                if (palette != null) {
                    collapsingToolbarLayout.setContentScrimColor(palette.getDarkMutedColor(R.attr.colorPrimary));
                }
            }
        });
        FloatingActionButton floatingActionButton = findViewById(R.id.fabButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(VisualizacaoActivity.this, EditorActivity.class));
            }
        });


    }
    public void recebendoDados(){
        Bundle bundle = getIntent().getExtras();
        String titulo = bundle.getString("titulo");
        String texto = bundle.getString("texto");
        nota.setTitulo(titulo);
        nota.setTexto(texto);
    }



    public void atribuicao(){
//        titulo = campoTitulo.getText().toString();
     //   texto = campoTexto.getText().toString();

    }
    public void inicializarComponentes(){
//        campoTitulo = findViewById(R.id.editTitulo);
        campoTexto = findViewById(R.id.visuTexto);

    }
}