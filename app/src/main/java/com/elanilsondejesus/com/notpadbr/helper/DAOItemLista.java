package com.elanilsondejesus.com.notpadbr.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.elanilsondejesus.com.notpadbr.model.ItemLista;
import com.elanilsondejesus.com.notpadbr.model.Lista;

import java.util.ArrayList;
import java.util.List;

public class DAOItemLista implements ItemListaDAO {
    private SQLiteDatabase escrever;
    private SQLiteDatabase ler;

    public DAOItemLista(Context context) {
        Banco_DB db = new Banco_DB(context);
        escrever = db.getWritableDatabase();
        ler = db.getReadableDatabase();
    }

    @Override
    public Boolean salvar(ItemLista itemLista) {
        ContentValues cv = new ContentValues();
        cv.put("titulo",itemLista.getTitulo());
        cv.put("marcado",itemLista.getMarcado());
        cv.put("idlista",itemLista.getIdLista());

        try {
            escrever.insert(Banco_DB.TABELA_ITENSLISTA,null,cv);
            Log.i("INFO", "Dados salva com sucesso!");
        }catch (Exception e){
            e.printStackTrace();
            Log.e("INFO", "Erro ao salvar Dados " + e.getMessage() );
            return false;
        }
        return true;
    }

    @Override
    public Boolean atualizar(ItemLista itemLista) {
        ContentValues cv = new ContentValues();
        cv.put("titulo",itemLista.getTitulo());
        cv.put("marcado",itemLista.getMarcado());
        cv.put("idlista",itemLista.getIdLista());

        try {
            String [] args ={itemLista.getId().toString()};
            escrever.update(Banco_DB.TABELA_ITENSLISTA,cv,"id=?",args);
            Log.i("INFO", "Dados Atualizado com sucesso!");
        }catch (Exception e){
            e.printStackTrace();
            Log.e("INFO", "Erro ao Atualizar Dados " + e.getMessage() );
            return false;
        }
        return true;
    }

    @Override
    public Boolean deletar(ItemLista itemLista) {
        try{
            String [] args = {itemLista.getId().toString()};
            escrever.delete(Banco_DB.TABELA_ITENSLISTA,"id=?",args);
            Log.i("INFO", "Dados deletado com sucesso!");

        }catch (Exception e){
            e.printStackTrace();
            Log.e("INFO", "Erro ao deletar dados" + e.getMessage() );
            return  false;

        }
        return true;
    }

    @Override
    public List<ItemLista> listar() {
        List<ItemLista> itens =new ArrayList<>();
        String sql ="SELECT * FROM "+Banco_DB.TABELA_ITENSLISTA +"";
        Cursor c = ler.rawQuery(sql,null);

        while (c.moveToNext()) {
           ItemLista itemLista = new ItemLista();

            Long id = c.getLong(c.getColumnIndex("id"));
            Long idlista = c.getLong(c.getColumnIndex("idlista"));
            String titulo = c.getString(c.getColumnIndex("titulo"));
            // o boolean vai verificar se o retorno é 1 se for retorna true se nao false
            Boolean marcado = (c.getInt(c.getColumnIndex("marcado"))==1);



            itemLista.setId(id);
            itemLista.setTitulo(titulo);
            itemLista.setStatus(marcado);
            itemLista.setIdLista(idlista);

            itens.add(itemLista);
            Log.i("Lista:", "AS notas estão sendo listadas" );

        }
        return itens;
    }
}
