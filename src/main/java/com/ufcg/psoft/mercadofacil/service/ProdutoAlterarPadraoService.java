package com.ufcg.psoft.mercadofacil.service;

import com.ufcg.psoft.mercadofacil.models.Produto;
import com.ufcg.psoft.mercadofacil.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
class ProdutoAlterarPadraoService  implements ProdutoAlterarService{

    @Autowired
    ProdutoRepository <Produto, Long> produtoRepository;
    Produto produto;
    @Override
    public Produto alterar(Produto produto) {
        if(produto.getPreco() > 0 && produto.getCodigoBarra().length() == 13) {
            return produtoRepository.update(produto);
        }
        return null;
    }
}
