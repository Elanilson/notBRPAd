package com.elanilsondejesus.com.notpadbr.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.elanilsondejesus.com.notpadbr.model.Nota;
import com.elanilsondejesus.com.notpadbr.model.Pasta;

import java.util.ArrayList;
import java.util.List;

public class DaoPasta implements PastaDAO {
    private SQLiteDatabase escrever;
    private SQLiteDatabase ler;

    public DaoPasta(Context context) {
        Banco_DB db = new Banco_DB(context);
        escrever = db.getWritableDatabase();
        ler = db.getReadableDatabase();
    }

    @Override
    public Boolean salvar(Pasta pasta) {
        ContentValues cv = new ContentValues();
        cv.put("titulo",pasta.getTitulo());
        cv.put("data",pasta.getData());
        try {
            escrever.insert(Banco_DB.TABELA_PASTA,null,cv);
            Log.i("INFO", "Dados salva com sucesso!");
        }catch (Exception e){
            e.printStackTrace();
            Log.e("INFO", "Erro ao salvar Dados " + e.getMessage() );
            return false;
        }
        return true;
    }

    @Override
    public Boolean atualizar(Pasta pasta) {
        ContentValues cv = new ContentValues();
        cv.put("titulo",pasta.getTitulo());
        cv.put("data",pasta.getData());
        try {
            String [] args ={pasta.getId().toString()};
            escrever.update(Banco_DB.TABELA_PASTA,cv,"id=?",args);
            Log.i("INFO", "Dados Atualizado com sucesso!");
        }catch (Exception e){
            e.printStackTrace();
            Log.e("INFO", "Erro ao Atualizar Dados " + e.getMessage() );
            return false;
        }
        return true;
    }

    @Override
    public Boolean deletar(Pasta pasta) {
        try{
            String [] args = {pasta.getId().toString()};
            escrever.delete(Banco_DB.TABELA_PASTA,"id=?",args);
            Log.i("INFO", "Dados deletado com sucesso!");

        }catch (Exception e){
            e.printStackTrace();
            Log.e("INFO", "Erro ao deletar dados" + e.getMessage() );
            return  false;

        }
        return true;
    }

    @Override
    public List<Pasta> listar() {
        List<Pasta> pastas =new ArrayList<>();
        String sql ="SELECT * FROM "+Banco_DB.TABELA_PASTA +";";
        Cursor c = ler.rawQuery(sql,null);

        while (c.moveToNext()) {
            Pasta pasta = new Pasta();

            Long id = c.getLong(c.getColumnIndex("id"));
            String titulo = c.getString(c.getColumnIndex("titulo"));
            String data = c.getString(c.getColumnIndex("data"));


            pasta.setId(id);
            pasta.setTitulo(titulo);
            pasta.setData(data);


            pastas.add(pasta);
            Log.i("Lista:", "AS notas estão sendo listadas" );

        }
        return pastas;
    }
}
