package com.elanilsondejesus.com.notpadbr.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Banco_DB extends SQLiteOpenHelper {
    public static int VERSION= 1;
    public static String NOME_BD = "BANCO_NOTA_BD";
    public static String TABELA_NOTA = "NOTA";
    public static String TABELA_LISTA = "LISTA";
    public static String TABELA_ITENSLISTA= "ITEM";
    public static String TABELA_PASTA= "PASTA";

    public Banco_DB( Context context) {
        super(context, NOME_BD,null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = " CREATE TABLE IF NOT EXISTS "+TABELA_NOTA
                +"(id INTEGER PRIMARY KEY AUTOINCREMENT, titulo VARCHAR(50), texto TEXT,cordefundo INT, data varchar(15),caminhoImg INT,status INTEGER DEFAULT 1,idpasta int);";
        //pesorestante é o resultado da subtracao do peso autal com a meta


        String sql2 = "CREATE TABLE IF NOT EXISTS "+TABELA_LISTA+"(id INTEGER PRIMARY KEY AUTOINCREMENT, titulo VARCHAR(50), texto TEXT,cordefundo INT, data varchar(15),caminhoImg INT);";

        String sql3 = "CREATE TABLE IF NOT EXISTS "+TABELA_ITENSLISTA+"(id INTEGER PRIMARY KEY AUTOINCREMENT, titulo VARCHAR(50), marcado INTEGER DEFAULT 0, idlista int);";
        String sql4 = "CREATE TABLE IF NOT EXISTS "+TABELA_PASTA+"(id INTEGER PRIMARY KEY AUTOINCREMENT, titulo VARCHAR(50),data varchar(15));";
        try{
            db.execSQL(sql);
            db.execSQL(sql2);
            db.execSQL(sql3);
            db.execSQL(sql4);
            Log.i("INFO DB", "Sucesso ao criar TABELA" );
        }catch (Exception e){
            Log.i("INFO DB", "Erro ao criar TABELA" );

            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
