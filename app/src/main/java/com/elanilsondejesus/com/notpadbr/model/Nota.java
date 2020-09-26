package com.elanilsondejesus.com.notpadbr.model;

public class Nota {
    private Long id;
    private String titulo;
    private String texto;
    private int cordeFundo;

    public Nota() {
    }

    public Nota(Long id, String titulo, String texto, int cordeFundo) {
        this.id = id;
        this.titulo = titulo;
        this.texto = texto;
        this.cordeFundo = cordeFundo;
    }

    public Nota(String titulo) {
        this.titulo = titulo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public int getCordeFundo() {
        return cordeFundo;
    }

    public void setCordeFundo(int cordeFundo) {
        this.cordeFundo = cordeFundo;
    }
}
