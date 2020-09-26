package com.elanilsondejesus.com.notpadbr.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.elanilsondejesus.com.notpadbr.R;
import com.elanilsondejesus.com.notpadbr.helper.DAONota;
import com.elanilsondejesus.com.notpadbr.model.Nota;
import com.scrat.app.richtext.RichEditText;

public class EditorActivity extends AppCompatActivity {
    private EditText campoTitulo , campoTexto ;
    private String titulo="";
    private String texto="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);



        inicializarComponentes();
        atribuicao();
        //configurando toolbar
        Toolbar toolbar = findViewById(R.id.toolbarEditor);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_editar,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.salvarNota:
                // primeiro ele verefica e depois salva
                verificarOsCampos();

                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void verificarOsCampos(){
        // vereficar se os campos foram preenchidos
        inicializarComponentes();
        if(!titulo.isEmpty()){
            if(!texto.isEmpty()){
                //chama o metodo para salvar
                salvarNota();
            }else{
                Toast.makeText(this, "O campo texto tem que ser preenchido", Toast.LENGTH_SHORT).show();

            }
        }else{
            Toast.makeText(this, "O campo titulo tem que ser preenchido", Toast.LENGTH_SHORT).show();

        }

    }
    public void atribuicao(){
        titulo = campoTitulo.getText().toString();
        texto = campoTexto.getText().toString();
    }
    public void inicializarComponentes(){
        campoTitulo = findViewById(R.id.editTitulo);
        campoTexto = findViewById(R.id.editTextoEditar);
        atribuicao();
    }
    public void salvarNota(){


        DAONota dao = new DAONota(getApplicationContext());
        Nota nota = new Nota();
        nota.setTitulo(titulo);
        nota.setTexto(texto);
        if(dao.salvar(nota)){
            Toast.makeText(this, "Salvo", Toast.LENGTH_SHORT).show();
            finish();
        }else{
            Toast.makeText(this, "Erro ao Salva", Toast.LENGTH_SHORT).show();

        }

    }
}