package com.elanilsondejesus.com.notpadbr.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.elanilsondejesus.com.notpadbr.model.Lista;
import com.elanilsondejesus.com.notpadbr.model.Nota;

import java.util.ArrayList;
import java.util.List;

public class DAOLista implements ListaDAO {
    private SQLiteDatabase escrever;
    private SQLiteDatabase ler;

    public DAOLista(Context context) {
        Banco_DB db = new Banco_DB(context);
        escrever = db.getWritableDatabase();
        ler = db.getReadableDatabase();
    }

    @Override
    public Boolean salvar(Lista lista) {
        ContentValues cv = new ContentValues();
        cv.put("titulo",lista.getTitulo());
        cv.put("texto",lista.getTexto());
        cv.put("cordefundo",lista.getCordeFundo());
        cv.put("data",lista.getData());
        cv.put("caminhoImg",lista.getCaminhoImg());

        try {
            escrever.insert(Banco_DB.TABELA_LISTA,null,cv);
            Log.i("INFO", "Dados salva com sucesso!");
        }catch (Exception e){
            e.printStackTrace();
            Log.e("INFO", "Erro ao salvar Dados " + e.getMessage() );
            return false;
        }
        return true;
    }

    @Override
    public Boolean atualizar(Lista lista) {
        ContentValues cv = new ContentValues();
        cv.put("titulo",lista.getTitulo());
        cv.put("texto",lista.getTexto());
        cv.put("cordefundo",lista.getCordeFundo());
        cv.put("data",lista.getData());
        cv.put("caminhoImg",lista.getCaminhoImg());

        try {
            String [] args ={lista.getId().toString()};
            escrever.update(Banco_DB.TABELA_LISTA,cv,"id=?",args);
            Log.i("INFO", "Dados Atualizado com sucesso!");
        }catch (Exception e){
            e.printStackTrace();
            Log.e("INFO", "Erro ao Atualizar Dados " + e.getMessage() );
            return false;
        }
        return true;
    }

    @Override
    public Boolean deletar(Lista lista) {
        try{
            String [] args = {lista.getId().toString()};
            escrever.delete(Banco_DB.TABELA_LISTA,"id=?",args);
            Log.i("INFO", "Dados deletado com sucesso!");

        }catch (Exception e){
            e.printStackTrace();
            Log.e("INFO", "Erro ao deletar dados" + e.getMessage() );
            return  false;

        }
        return true;
    }

    @Override
    public List<Lista> listar() {
        List<Lista> listas =new ArrayList<>();
        String sql ="SELECT * FROM "+Banco_DB.TABELA_LISTA +"";
        Cursor c = ler.rawQuery(sql,null);

        while (c.moveToNext()) {
           Lista lista = new Lista();

            Long id = c.getLong(c.getColumnIndex("id"));
            String titulo = c.getString(c.getColumnIndex("titulo"));
            String texto = c.getString(c.getColumnIndex("texto"));
            int cordefundo = c.getInt(c.getColumnIndex("cordefundo"));
            String data = c.getString(c.getColumnIndex("data"));
            int caminhoImg = c.getInt(c.getColumnIndex("caminhoImg"));


            lista.setId(id);
            lista.setTitulo(titulo);
            lista.setTexto(texto);
            lista.setCordeFundo(cordefundo);
            lista.setData(data);
            lista.setCaminhoImg(caminhoImg);

            listas.add(lista);
            Log.i("Lista:", "AS notas estão sendo listadas" );

        }
        return listas;
    }
}
