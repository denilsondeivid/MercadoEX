package com.ufcg.psoft.mercadofacil.service;

import com.ufcg.psoft.mercadofacil.models.Produto;
import com.ufcg.psoft.mercadofacil.repositories.ProdutoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@DisplayName("teste do serviço alteração do produto")
class ProdutoAlterarServiceTests {

    @Autowired
    ProdutoAlterarService driver;

    @MockBean
    ProdutoRepository<Produto, Long> produtoRepository;

    Produto produto;

    @BeforeEach
    void setup(){
        Mockito.when(produtoRepository.find(10L))
                .thenReturn(Produto.builder()
                        .id(10L)
                        .codigoBarra("9145")
                        .nome("xoxo")
                        .fabricante("laura")
                        .preco(0.6)
                        .build()
                );

        produto = produtoRepository.find(10L);
    }
    @Test
    @DisplayName("novo nome válido para o produto")
    void quandoNovoNomeValido(){
        //arrange
        produto.setNome("Produto Dez Atualizado");
        Mockito.when(produtoRepository.update(produto))
                .thenReturn(Produto.builder()
                        .id(10L)
                        .codigoBarra("9145")
                        .nome("Produto Dez Atualizado")
                        .fabricante("laura")
                        .preco(0.6)
                        .build()
                );

        //act
        Produto resultado = driver.alterar(produto);
        assertEquals("Produto Dez Atualizado", resultado.getNome());
    }

    @Test
    @DisplayName("Adicionando preco negativo")
    void deveRetornarUmNullQuandoPrecoNegativoForPassado(){
        produto.setPreco(-0.9);
        //act
        Assertions.assertNull(driver.alterar(produto));
    }

    @Test
    @DisplayName("Adicionando codigo de barras menor que 13")
    void deveRetornarUmNullQuandoCodigoDeBarrasMenorQue13(){
        produto.setCodigoBarra("78983474001");
        Assertions.assertTrue(produto.getCodigoBarra().length() < 13);
        //act
        Assertions.assertNull(driver.alterar(produto));
    }


    @Test
    @DisplayName("Adicionando codigo de barras maior que 13")
    void deveRetornarUmNullQuandoCodigoDeBarrasMaiorQue13(){
        produto.setCodigoBarra("7898347400012344");
        Assertions.assertTrue(produto.getCodigoBarra().length() > 13);
        //act
        Assertions.assertNull(driver.alterar(produto));
    }
}
