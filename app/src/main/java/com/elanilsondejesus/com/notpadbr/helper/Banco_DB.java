package com.elanilsondejesus.com.notpadbr.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Banco_DB extends SQLiteOpenHelper {
    public static int VERSION= 1;
    public static String NOME_BD = "BANCO_NOTA_BD";
    public static String TABELA_NOTA = "NOTA";
//    public static String TABELA_PROGRESSO = "PROGRESSO";
//    public static String TABELA_HISTORICO = "HISTORICO";

    public Banco_DB( Context context) {
        super(context, NOME_BD,null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = " CREATE TABLE IF NOT EXISTS "+TABELA_NOTA
                +"(id INTEGER PRIMARY KEY AUTOINCREMENT, titulo VARCHAR(50), texto TEXT,cordefundo INT);";
        //pesorestante é o resultado da subtracao do peso autal com a meta


//        String sql2 = "CREATE TABLE IF NOT EXISTS "+TABELA_PROGRESSO+"(id INTEGER PRIMARY KEY AUTOINCREMENT, dia int, mes int,ano int, peso float);";

//        String sql3 = "CREATE TABLE IF NOT EXISTS "+TABELA_HISTORICO+"(id INTEGER PRIMARY KEY AUTOINCREMENT, data varchar(10), novopeso float, mudanca varchar(8));";
        try{
            db.execSQL(sql);
//            db.execSQL(sql2);
//            db.execSQL(sql3);
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
