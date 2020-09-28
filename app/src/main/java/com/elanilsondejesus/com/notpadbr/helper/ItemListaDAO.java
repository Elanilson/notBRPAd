package com.elanilsondejesus.com.notpadbr.helper;

import com.elanilsondejesus.com.notpadbr.model.ItemLista;
import com.elanilsondejesus.com.notpadbr.model.Lista;

import java.util.List;

public interface ItemListaDAO {

    public Boolean salvar(ItemLista itemLista);
    public Boolean atualizar(ItemLista itemLista);
    public Boolean deletar(ItemLista itemLista);
    public List<ItemLista> listar();
}
