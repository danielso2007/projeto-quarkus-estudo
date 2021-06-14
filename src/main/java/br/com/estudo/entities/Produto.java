package br.com.estudo.entities;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonRootName;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

@JsonRootName(value = "produto")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name= "produto")
@NamedQuery(name = "Produto.findAll", query = "SELECT p FROM Produto p ORDER BY p.nome")
public class Produto extends PanacheEntityBase {

    @Id
    @SequenceGenerator(name = "produtoSequence", sequenceName = "produto_id_seq", allocationSize = 1, initialValue = 8)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "produtoSequence")
    private Long id;

    private String nome;

    private BigDecimal valor;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss", timezone = "America/Sao_Paulo")
    @CreationTimestamp
    private Date dataCriacao;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss", timezone = "America/Sao_Paulo")
    @UpdateTimestamp
    private Date dataAtualicao;

    public Produto() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public Date getDataAtualicao() {
        return dataAtualicao;
    }

    public void setDataAtualicao(Date dataAtualicao) {
        this.dataAtualicao = dataAtualicao;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
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

    public Produto(Long id, String nome, BigDecimal valor, Date dataCriacao, Date dataAtualicao) {
        this.id = id;
        this.nome = nome;
        this.valor = valor;
        this.dataCriacao = dataCriacao;
        this.dataAtualicao = dataAtualicao;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Produto)) {
            return false;
        }
        Produto produto = (Produto) o;
        return Objects.equals(id, produto.id) && Objects.equals(nome, produto.nome)
                && Objects.equals(valor, produto.valor) && Objects.equals(dataCriacao, produto.dataCriacao)
                && Objects.equals(dataAtualicao, produto.dataAtualicao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, valor, dataCriacao, dataAtualicao);
    }

    @Override
    public String toString() {
        return "{" + " id='" + getId() + "'" + ", nome='" + getNome() + "'" + ", valor='" + getValor() + "'"
                + ", dataCriacao='" + getDataCriacao() + "'" + ", dataAtualicao='" + getDataAtualicao() + "'" + "}";
    }

    public static ProdutoBuilder builder() {
        return new ProdutoBuilder();
    }

    public static class ProdutoBuilder {
        private Long id;
        private String nome;
        private BigDecimal valor;

        public ProdutoBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public ProdutoBuilder nome(String nome) {
            this.nome = nome;
            return this;
        }

        public ProdutoBuilder valor(BigDecimal valor) {
            this.valor = valor;
            return this;
        }

        public Produto build() {
            Produto p = new Produto();
            p.id = this.id;
            p.nome = this.nome;
            p.valor = this.valor;
            return p;
        }
    }

}
