package com.elanilsondejesus.com.notpadbr.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.elanilsondejesus.com.notpadbr.R;
import com.elanilsondejesus.com.notpadbr.helper.DAONota;
import com.elanilsondejesus.com.notpadbr.helper.DaoPasta;
import com.elanilsondejesus.com.notpadbr.model.Nota;
import com.elanilsondejesus.com.notpadbr.model.Pasta;

public class LixeiraActivity extends AppCompatActivity {
    private TextView titulo, texto;
    private String camptitulo, camptexto;
    private Nota nota = new Nota();
    private ImageView img;
    private ConstraintLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lixeira);
        titulo = findViewById(R.id.textViewTituloLixo);
        texto = findViewById(R.id.textViewTextoLixo);
        img = findViewById(R.id.imageViewLixo);
        layout = findViewById(R.id.constraintLayoutImg);
//        camptitulo = titulo.getText().toString();
//        camptexto = texto.getText().toString();
        recebendoDados();
        associandoValores();
    }
    public void associandoValores(){
        titulo.setText(nota.getTitulo());
        texto.setText(nota.getTexto());
       if(nota.getCaminhoImg() != 0 ){
           img.setImageResource(nota.getCaminhoImg());
       }else{
           img.setImageResource(R.drawable.imagem4);

       }
    }
    public void recebendoDados(){
        Bundle bundle = getIntent().getExtras();
        Long id = null ;
        Long idpasta = null ;

        id = bundle.getLong("id") ;
        idpasta = bundle.getLong("idpasta") ;
        String titulo = bundle.getString("titulo");
        String texto = bundle.getString("texto");
        String data = bundle.getString("data");
        int caminhoImg = bundle.getInt("caminhoImg");
        nota.setId(id);
        nota.setIdPasta(idpasta);
        nota.setTitulo(titulo);
        nota.setTexto(texto);
        nota.setData(data);
        nota.setCaminhoImg(caminhoImg);

//        Toast.makeText(this, "id: "+nota.getId()+" titulo: "+nota.getTitulo()+" texto: "+nota.getTexto(), Toast.LENGTH_SHORT).show();
    }

    public void deletar(View view){
        DAONota dao = new DAONota(getApplicationContext());
        if(dao.deletar(nota)){
            finish();
            Toast.makeText(this, "Excluido com sucesso", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Erro ao Excluir", Toast.LENGTH_SHORT).show();

        }

    }
    public void recuperar(View view){

        DAONota dao = new DAONota(getApplicationContext());
        DaoPasta daoPasta = new DaoPasta(getApplicationContext());
        nota.setStatus(1);
        if(dao.atualizar(nota)){
            finish();
            Toast.makeText(this, "nota teste atualizado", Toast.LENGTH_SHORT).show();
        }


//        for(Pasta pasta: daoPasta.listar()){
//            if(pasta.getId() == nota.getIdPasta()){
//                /*
//                se a pasta ainda exitir vai retorna para pagina
//                 */
//                nota.setStatus(1);
//                if(dao.atualizar(nota)){
//                    finish();
//                    Toast.makeText(this, "Restaurado com sucesso", Toast.LENGTH_SHORT).show();
//
//                }else{
//                    Toast.makeText(this, "Erro ao restaurar", Toast.LENGTH_SHORT).show();
//
//                }
//
//            }else{
//                /*
//                caso a pasta nao exista vai para notas
//                 */
////                String valor = "0";
////                Long padrao = Long.parseLong(valor);
////                nota.setIdPasta(padrao);
//                nota.setStatus(1);
//                if(dao.atualizar(nota)){
//                    finish();
//
//                    Toast.makeText(this, "Restaurado com sucesso", Toast.LENGTH_SHORT).show();
//
//                }else{
//                    Toast.makeText(this, "Erro ao restaurar", Toast.LENGTH_SHORT).show();
//
//                }
//
//            }
//        }
    }
}