package com.elanilsondejesus.com.notpadbr.helper;



import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataUtils {

    public static  String getDataAtual(){
        DateFormat dataformatada = new SimpleDateFormat("dd/MM/yyyy");
        Date data = new Date();
        String dataAtual = dataformatada.format(data);
        return dataAtual;
    }
}
