package com.elanilsondejesus.com.notpadbr.model;

public class ItemLista {
    private Long id;
    private String titulo;
    private Boolean marcado;

    public ItemLista() {
    }

    public ItemLista(Long id, String titulo, Boolean marcado) {
        this.id = id;
        this.titulo = titulo;
        this.marcado = marcado;
    }

    public Boolean getMarcado() {
        return marcado;
    }

    public void setMarcado(Boolean marcado) {
        this.marcado = marcado;
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


}
