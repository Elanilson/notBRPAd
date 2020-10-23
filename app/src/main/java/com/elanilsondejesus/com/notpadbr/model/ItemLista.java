package com.elanilsondejesus.com.notpadbr.model;

public class ItemLista {
    private Long id;
    private Long idLista;
    private String titulo;
    private Integer marcado =0;
    private Boolean status;
    public ItemLista() {
    }

    public ItemLista(Long id, String titulo, Integer marcado) {
        this.id = id;
        this.titulo = titulo;
        this.marcado = marcado;
    }

    public Long getIdLista() {
        return idLista;
    }

    public void setIdLista(Long idLista) {
        this.idLista = idLista;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Integer getMarcado() {
        return marcado;
    }

    public void setMarcado(Integer marcado) {
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
