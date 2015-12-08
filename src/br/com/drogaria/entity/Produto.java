package br.com.drogaria.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "tbl_produtos")
@NamedQueries({
		@NamedQuery(name = "Produto.listar", query = "SELECT produto FROM Produto produto"),
		@NamedQuery(name = "Produto.buscarPorCodigo", query = "SELECT produto FROM Produto produto where produto.codigo = :codigo") })
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "pro_codigo", nullable = false, unique = true)
	private Long codigo;

	@NotEmpty(message = "O campo descrição é obrigatório")
	@Column(name = "pro_descricao", nullable = false, length = 50)
	private String descricao;

	@NotNull(message = "O campo preço é obrigatório")
	@DecimalMin(value = "0.0", message = "Informe um valor maior ou igual a zero para o campo preço")
	@DecimalMax(value = "99999.99", message = "Informe um valor menor que 100 mil para o campo preço")
	@Column(name = "pro_preco", nullable = false, precision = 7, scale = 2)
	private BigDecimal preco;

	@NotEmpty(message = "O campo quantidade é obrigatório")
	@Column(name = "pro_quantidade", nullable = false)
	private Integer quantidade;

	@NotEmpty(message = "O campo fabricante é obrigatório")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "tbl_fabricantes_fab_codigo", referencedColumnName = "fab_codigo", nullable = false)
	private Fabricante fabricante;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Fabricante getFabricante() {
		return fabricante;
	}

	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}

	@Override
	public String toString() {
		return "Produto [codigo=" + codigo + ", descricao=" + descricao
				+ ", preco=" + preco + ", quantidade=" + quantidade
				+ ", fabricante=" + fabricante + "]";
	}

}
