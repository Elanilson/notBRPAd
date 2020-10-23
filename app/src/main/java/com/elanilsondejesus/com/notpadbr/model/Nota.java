package com.elanilsondejesus.com.notpadbr.model;

import java.io.Serializable;

public class Nota implements Serializable {
    private Long id;
    private Long idPasta;
    private String titulo;
    private String texto;
    private int cordeFundo;
    private Nota nota;
    private String data;
    private int caminhoImg;
    private int status;
    private  boolean adicionar;

    public Nota() {
    }

    public Nota(String titulo, String texto, boolean adicionar) {
        this.titulo = titulo;
        this.texto = texto;
        this.adicionar = adicionar;
    }

    public Nota(String titulo, String texto) {
        this.titulo = titulo;
        this.texto = texto;
    }

    public boolean isAdicionar() {
        return adicionar;
    }

    public void setAdicionar(boolean adicionar) {
        this.adicionar = adicionar;
    }

    public Long getIdPasta() {
        return idPasta;
    }

    public void setIdPasta(Long idPasta) {
        this.idPasta = idPasta;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCaminhoImg() {
        return caminhoImg;
    }

    public void setCaminhoImg(int caminhoImg) {
        this.caminhoImg = caminhoImg;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Nota(String titulo) {
        this.titulo = titulo;
    }

    public Nota getNota() {
        return nota;
    }

    public void setNota(Nota nota) {
        this.nota = nota;
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
