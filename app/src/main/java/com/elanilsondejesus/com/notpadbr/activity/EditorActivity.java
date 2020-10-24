package com.elanilsondejesus.com.notpadbr.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.elanilsondejesus.com.notpadbr.R;
import com.elanilsondejesus.com.notpadbr.fragment.NotasFragment;
import com.elanilsondejesus.com.notpadbr.helper.DAONota;
import com.elanilsondejesus.com.notpadbr.helper.DataUtils;
import com.elanilsondejesus.com.notpadbr.model.Nota;

import java.util.ArrayList;
import java.util.List;

public class EditorActivity extends AppCompatActivity {
    private EditText campoTitulo , campoTexto ;
    private String titulo="";
    private String texto="";
    private Nota nota = new Nota();
    private Boolean editarNota = false;
    private boolean dadosenviados = false;
    private Long idpasta =null;
    private  List<Integer> cor = new ArrayList<>();




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
       // toolbar.setBackgroundColor(getResources().getColor(R.color.azul));
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
            case R.id.coresEditar:
              //  startActivity(new Intent(EditorActivity.this,CoresActivity.class));
                Intent intent = new Intent(EditorActivity.this,CoresActivity.class);
                String etitulo =campoTitulo.getText().toString();
                String etexto =campoTexto.getText().toString();
                intent.putExtra("titulo",etitulo);
                intent.putExtra("texto",etexto);
                startActivity(intent);
                finish();
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
        campoTexto.setBackgroundColor(nota.getCordeFundo());
        Toast.makeText(this, "corE"+nota.getCordeFundo(), Toast.LENGTH_SHORT).show();


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
        nota.setTitulo(titulo);
        nota.setTexto(texto);
        nota.setIdPasta(idpasta);
      //  nota.setCordeFundo(R.color.black);
        nota.setStatus(1);
        nota.setData(DataUtils.getDataAtual());
        if(editarNota){
            this.nota.setTitulo(titulo);
            this.nota.setTexto(texto);
            this.nota.setIdPasta(idpasta);
            this.nota.setStatus(1);
           // this.nota.setCordeFundo(nota.getCordeFundo());
            campoTitulo.setText(titulo);
            campoTexto.setText(texto);
            if(dao.atualizar(this.nota)){// atualizar com os dados do escopo global
                Toast.makeText(this, "Atualizado cord"+nota.getCordeFundo(), Toast.LENGTH_SHORT).show();

                finish();

            }else{
                Toast.makeText(this, "Erro ao atualizar", Toast.LENGTH_SHORT).show();

            }

        }else{


            if(dao.salvar(nota)){
                Toast.makeText(this, "Salvo cod"+nota.getCordeFundo(), Toast.LENGTH_SHORT).show();

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
            int cor = bundle.getInt("cor");
        if(dadosenviados || editarnota) {
            Long id = bundle.getLong("id");
            String titulo = bundle.getString("titulo");
            String texto = bundle.getString("texto");
            String data = bundle.getString("data");
            int caminhoImg = bundle.getInt("caminhoImg");
            if(idpasta != null){ /// se for differentte de  null vai salvar o id da pasta
                nota.setIdPasta(idpasta);
            }
            nota.setId(id);
            nota.setCordeFundo(cor);
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
        intent.putExtra("cor",nota.getCordeFundo());
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