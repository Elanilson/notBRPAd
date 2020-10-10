package com.elanilsondejesus.com.notpadbr.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.elanilsondejesus.com.notpadbr.R;
import com.elanilsondejesus.com.notpadbr.model.Nota;

public class VisualizarTemaMainActivity extends AppCompatActivity {
    private ImageView imagem ;
    private Nota nota  = new Nota();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_tema_main);
        imagem = findViewById(R.id.imageViewVisualizarTema);
        recebendoDados();
    }
    public void recebendoDados(){
        Bundle bundle = getIntent().getExtras();
        int caminhoImg = bundle.getInt("imagem");
        nota.setCaminhoImg(caminhoImg);
        // exibindo a imagem
        imagem.setImageResource(nota.getCaminhoImg());
//        Toast.makeText(this, "id: "+nota.getId()+" titulo: "+nota.getTitulo()+" texto: "+nota.getTexto(), Toast.LENGTH_SHORT).show();
    }
}