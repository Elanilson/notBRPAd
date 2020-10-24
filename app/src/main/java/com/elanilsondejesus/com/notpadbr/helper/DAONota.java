package com.elanilsondejesus.com.notpadbr.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.elanilsondejesus.com.notpadbr.model.Nota;

import java.util.ArrayList;
import java.util.List;

public class DAONota implements NotaDAO {
    private SQLiteDatabase escrever;
    private SQLiteDatabase ler;
    private Context context;

    public DAONota(Context context) {
        Banco_DB db = new Banco_DB(context);
        escrever = db.getWritableDatabase();
        ler = db.getReadableDatabase();
        this.context = context;
    }


    @Override
    public Boolean salvar(Nota nota) {
        ContentValues cv = new ContentValues();
        cv.put("idpasta",nota.getIdPasta());
        cv.put("titulo",nota.getTitulo());
        cv.put("texto",nota.getTexto());
        cv.put("cordefundo",nota.getCordeFundo());
        cv.put("data",nota.getData());
        cv.put("caminhoImg",nota.getCaminhoImg());
        cv.put("status",nota.getStatus());

        try {
            escrever.insert(Banco_DB.TABELA_NOTA,null,cv);
            Log.i("INFO", "Dados salva com sucesso!");
        }catch (Exception e){
            e.printStackTrace();
            Log.e("INFO", "Erro ao salvar Dados " + e.getMessage() );
            return false;
        }
        return true;
    }

    @Override
    public Boolean atualizar(Nota nota) {
        ContentValues cv = new ContentValues();
        cv.put("idpasta",nota.getIdPasta());
        cv.put("titulo",nota.getTitulo());
        cv.put("texto",nota.getTexto());
        cv.put("cordefundo",nota.getCordeFundo());
        cv.put("data",nota.getData());
        cv.put("caminhoImg",nota.getCaminhoImg());
        cv.put("status",nota.getStatus());

        try {
            String [] args ={nota.getId().toString()};
            escrever.update(Banco_DB.TABELA_NOTA,cv,"id=?",args);
            Log.i("INFO", "Dados Atualizado com sucesso!");
        }catch (Exception e){
            e.printStackTrace();
            Log.e("INFO", "Erro ao Atualizar Dados " + e.getMessage() );
            return false;
        }
        return true;
    }

    @Override
    public Boolean deletar(Nota nota) {
        try{
            String [] args = {nota.getId().toString()};
            escrever.delete(Banco_DB.TABELA_NOTA,"id=?",args);
            Log.i("INFO", "Dados deletado com sucesso!");

        }catch (Exception e){
            e.printStackTrace();
            Log.e("INFO", "Erro ao deletar dados" + e.getMessage() );
            return  false;

        }
        return true;
    }

    @Override
    public List<Nota> listar() {
        /*
        1 = ativo
        0 = inativo
         */
        List<Nota> notas =new ArrayList<>();
        String sql ="SELECT * FROM "+Banco_DB.TABELA_NOTA +" WHERE status=1 AND idpasta=0;";
        Cursor c = ler.rawQuery(sql,null);

        while (c.moveToNext()) {
            Nota nota = new Nota();

            Long id = c.getLong(c.getColumnIndex("id"));
            String titulo = c.getString(c.getColumnIndex("titulo"));
            String texto = c.getString(c.getColumnIndex("texto"));
            int status = c.getInt(c.getColumnIndex("status"));
            int cordefundo = c.getInt(c.getColumnIndex("cordefundo"));
            int caminhoImg = c.getInt(c.getColumnIndex("caminhoImg"));
            String data = c.getString(c.getColumnIndex("data"));


            nota.setId(id);
            nota.setTitulo(titulo);
            nota.setTexto(texto);
            nota.setCordeFundo(cordefundo);
            nota.setData(data);
            nota.setCaminhoImg(caminhoImg);
            nota.setStatus(status);

            notas.add(nota);
            Log.i("Lista:", "AS notas estão sendo listadas" );

        }
        return notas;
    }

    @Override
    public List<Nota> listarInativo() {
        List<Nota> notas =new ArrayList<>();
        String sql ="SELECT * FROM "+Banco_DB.TABELA_NOTA +" WHERE status=0;";
        Cursor c = ler.rawQuery(sql,null);

        while (c.moveToNext()) {
            Nota nota = new Nota();

            Long id = c.getLong(c.getColumnIndex("id"));
            String titulo = c.getString(c.getColumnIndex("titulo"));
            String texto = c.getString(c.getColumnIndex("texto"));
            int status = c.getInt(c.getColumnIndex("status"));
            int cordefundo = c.getInt(c.getColumnIndex("cordefundo"));
            int caminhoImg = c.getInt(c.getColumnIndex("caminhoImg"));
            String data = c.getString(c.getColumnIndex("data"));


            nota.setId(id);
            nota.setTitulo(titulo);
            nota.setTexto(texto);
            nota.setCordeFundo(cordefundo);
            nota.setData(data);
            nota.setCaminhoImg(caminhoImg);
            nota.setStatus(status);

            notas.add(nota);
            Log.i("Lista:", "AS notas estão sendo listadas" );

        }
        return notas;
    }


    @Override
    public List<Nota> listarItemPasta(Long idpast) {
        List<Nota> notas =new ArrayList<>();
        String sql ="SELECT * FROM "+Banco_DB.TABELA_NOTA +" WHERE idpasta="+idpast+" AND status=1;";
        Cursor c = ler.rawQuery(sql,null);

        while (c.moveToNext()) {
            Nota nota = new Nota();

            Long id = c.getLong(c.getColumnIndex("id"));
            Long idpasta = c.getLong(c.getColumnIndex("idpasta"));
            String titulo = c.getString(c.getColumnIndex("titulo"));
            String texto = c.getString(c.getColumnIndex("texto"));
            int status = c.getInt(c.getColumnIndex("status"));
            int cordefundo = c.getInt(c.getColumnIndex("cordefundo"));
            int caminhoImg = c.getInt(c.getColumnIndex("caminhoImg"));
            String data = c.getString(c.getColumnIndex("data"));


            nota.setId(id);
            nota.setIdPasta(idpasta);
            nota.setTitulo(titulo);
            nota.setTexto(texto);
            nota.setCordeFundo(cordefundo);
            nota.setData(data);
            nota.setCaminhoImg(caminhoImg);
            nota.setStatus(status);

            notas.add(nota);
            Log.i("Lista:", "AS notas estão sendo listadas id="+idpast );

        }
        return notas;
    }
}
