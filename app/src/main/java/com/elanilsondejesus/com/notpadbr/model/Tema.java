package com.elanilsondejesus.com.notpadbr.model;

public class Tema {
    private Long id;
    private int imagem;

    public Tema() {
    }

    public Tema(int imagem) {
        this.imagem = imagem;
    }

    public Tema(Long id, int imagem) {
        this.id = id;
        this.imagem = imagem;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getImagem() {
        return imagem;
    }

    public void setImagem(int imagem) {
        this.imagem = imagem;
    }
}
