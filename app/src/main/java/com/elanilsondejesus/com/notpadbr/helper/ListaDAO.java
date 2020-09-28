package com.elanilsondejesus.com.notpadbr.helper;

import com.elanilsondejesus.com.notpadbr.model.Lista;
import com.elanilsondejesus.com.notpadbr.model.Nota;

import java.util.List;

public interface ListaDAO {

    public Boolean salvar(Lista lista);
    public Boolean atualizar(Lista lista);
    public Boolean deletar(Lista lista);
    public List<Lista> listar();
}
