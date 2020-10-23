package com.elanilsondejesus.com.notpadbr.model;

public class Lista  extends Nota{
    private  boolean adicionar;


    public Lista() {
    }

    public Lista(String titulo, String texto, boolean adicionar) {
        super(titulo, texto);
        this.adicionar = adicionar;
    }

    public boolean isAdicionar() {
        return adicionar;
    }

    public void setAdicionar(boolean adicionar) {
        this.adicionar = adicionar;
    }
}
