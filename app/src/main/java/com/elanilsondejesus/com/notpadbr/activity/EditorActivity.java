package com.elanilsondejesus.com.notpadbr.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
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
import com.elanilsondejesus.com.notpadbr.adapter.AdapterNota;
import com.elanilsondejesus.com.notpadbr.fragment.NotasFragment;
import com.elanilsondejesus.com.notpadbr.helper.DAONota;
import com.elanilsondejesus.com.notpadbr.helper.DataUtils;
import com.elanilsondejesus.com.notpadbr.model.Nota;
import com.scrat.app.richtext.RichEditText;

public class EditorActivity extends AppCompatActivity {
    private EditText campoTitulo , campoTexto ;
    private String titulo="";
    private String texto="";
    private Nota nota = new Nota();
    private Boolean editarNota = false;
    private boolean dadosenviados = false;
    private Long idpasta =null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        recebendoDados();
        inicializarComponentes();
        atribuicao();
        //configurando toolbar
        Toolbar toolbar = findViewById(R.id.toolbarEditor);
        toolbar.setTitle("");
        toolbar.setBackgroundColor(getResources().getColor(R.color.azul));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Toast.makeText(this, "id: "+idpasta, Toast.LENGTH_SHORT).show();

        /*
        verificar se tem dados antes de sair da tela implmentar isso
         */



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

        if(nota.getTitulo() != null){
            if(nota.getTexto()!= null){
                campoTitulo.setText(nota.getTitulo());
                campoTexto.setText(nota.getTexto());
            }
        }

    }
    public void inicializarComponentes(){
        campoTitulo = findViewById(R.id.editTitulo);
        campoTexto = findViewById(R.id.editTextoEditar);
        atribuicao();
    }
    public void salvarNota(){


        DAONota dao = new DAONota(getApplicationContext());
        Nota nota = new Nota();
//        if(titulo == null){
//            titulo ="rascunho";
//
//        }
//        if(texto == null){
//            texto ="rascunho";
//        }
        nota.setTitulo(titulo);
        nota.setTexto(texto);
        nota.setIdPasta(idpasta);
        nota.setStatus(1);
        nota.setData(DataUtils.getDataAtual());
        if(editarNota){
            this.nota.setTitulo(titulo);
            this.nota.setTexto(texto);
            this.nota.setIdPasta(idpasta);
            this.nota.setStatus(1);
            campoTitulo.setText(titulo);
            campoTexto.setText(texto);
            if(dao.atualizar(this.nota)){// atualizar com os dados do escopo global
                Toast.makeText(this, "Atualizado"+idpasta, Toast.LENGTH_SHORT).show();

                finish();

            }else{
                Toast.makeText(this, "Erro ao atualizar", Toast.LENGTH_SHORT).show();

            }

        }else{


            if(dao.salvar(nota)){
                Toast.makeText(this, "Salvo", Toast.LENGTH_SHORT).show();

                finish();
            }else{
                Toast.makeText(this, "Erro ao Salva", Toast.LENGTH_SHORT).show();

            }


        }

    }
    public void recebendoDados(){
        Bundle bundle = getIntent().getExtras();
        dadosenviados = bundle.getBoolean("enviarDados");
        idpasta = bundle.getLong("idPasta");
        boolean editarnota = bundle.getBoolean("editar");
        if(dadosenviados || editarnota) {
            Long id = bundle.getLong("id");
            String titulo = bundle.getString("titulo");
            String texto = bundle.getString("texto");
            String data = bundle.getString("data");
            int caminhoImg = bundle.getInt("caminhoImg");
            this.editarNota = editarnota;
            if(idpasta != null){ /// se for differentte de  null vai salvar o id da pasta
                nota.setIdPasta(idpasta);
            }
            nota.setId(id);
            nota.setTitulo(titulo);
            nota.setTexto(texto);
            nota.setData(data);
            nota.setCaminhoImg(caminhoImg);
        }


    }
    public void enviarDados(){
        // enviar dados para serem editados/alterados
        Intent intent = new Intent(EditorActivity.this,VisualizacaoActivity.class);
        intent.putExtra("id",nota.getId());
        intent.putExtra("titulo",nota.getTitulo());
        intent.putExtra("texto",nota.getTexto());
        startActivity(intent);
    }

//    public void vereficarSeOsDadosForamSalvos(){
//        /*
//        vereficar dados se foram salvos antes de fecha a janela
//         */
//        String titulo = campoTitulo.getText().toString();
//        String texto = campoTexto.getText().toString();
//        DAONota dao = new DAONota(getApplicationContext());
//        for(Nota not: dao.listar()){
//            if(not.getId() == this.nota.getId()){
//                if(not.getTitulo().contains(titulo) && not.getTexto().contains(texto)){
//                    Toast.makeText(this, "Pode sair", Toast.LENGTH_SHORT).show();
//                }
//                if(!not.getTitulo().contains(titulo) || !not.getTexto().contains(texto)){
//                    Toast.makeText(this, "Saindo sem salvar", Toast.LENGTH_SHORT).show();
//
//                      salvarNota();
//
//                }
//
//            }
//        }
//    }
public void enviarSolicitacaoDeRecarregamento(){
        /*
        envia uma  referencia  para que seja recarregado nas notas
         */
        Intent intent = new Intent(getApplicationContext(), NotasFragment.class);
        intent.putExtra("recarregarNotas",true);
        startActivity(intent);
}



    @Override
    protected void onPause() {
        super.onPause();
        if(dadosenviados || editarNota){

//       vereficarSeOsDadosForamSalvos();

            enviarDados();
        }
    }


}