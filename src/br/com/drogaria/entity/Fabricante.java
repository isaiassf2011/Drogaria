package br.com.drogaria.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "tbl_frabricantes")
@NamedQueries({
	@NamedQuery(name = "Fabricante.listar", query = "SELECT fabricante FROM Fabricante fabricante"),
	@NamedQuery(name = "Fabricante.buscarPorCodigo", query = "SELECT fabricante FROM Fabricante fabricante where fabricante.codigo = :codigo")
})
public class Fabricante {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "fab_codigo", nullable = false, unique = true)
	private Long codigo;

	@NotEmpty(message = "O campo descrição é obrigatório")
	@Size(min = 5, max = 50, message="Tamanho invalido para o campo descrição (5 - 50)")
	@Column(name = "fab_descricao", nullable = false, length = 50)
	private String descricao;

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

	@Override
	public String toString() {
		return "Fabricante [codigo=" + codigo + ", descricao=" + descricao
				+ "]";
	}
	
}
