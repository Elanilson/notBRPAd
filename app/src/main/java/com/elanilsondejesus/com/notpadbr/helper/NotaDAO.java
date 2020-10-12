package com.elanilsondejesus.com.notpadbr.helper;

import com.elanilsondejesus.com.notpadbr.model.Nota;

import java.util.List;

public interface NotaDAO {

    public Boolean salvar(Nota nota);
    public Boolean atualizar(Nota nota);
    public Boolean deletar(Nota nota);
    public List<Nota> listar();
    public List<Nota> listarInativo();
    public List<Nota> listarItemPasta(Long id);
}
