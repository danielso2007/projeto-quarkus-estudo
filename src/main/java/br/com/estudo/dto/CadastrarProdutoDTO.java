package br.com.estudo.dto;

import java.math.BigDecimal;

public class CadastrarProdutoDTO {

    private String nome;
    private BigDecimal valor;

    public String getNome() {
        return nome;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
