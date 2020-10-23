package com.elanilsondejesus.com.notpadbr.helper;

import com.elanilsondejesus.com.notpadbr.model.Nota;
import com.elanilsondejesus.com.notpadbr.model.Pasta;

import java.util.List;

public interface PastaDAO {

    public Boolean salvar(Pasta pasta);
    public Boolean atualizar(Pasta pasta);
    public Boolean deletar(Pasta pasta);
    public List<Pasta> listar();

}
