package br.com.drogaria.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.drogaria.dao.FabricanteDAO;
import br.com.drogaria.entity.Fabricante;
import br.com.drogaria.util.FacesUtil;

@ManagedBean
@ViewScoped
public class FabricanteBean {

	private Fabricante fabricanteCadastro;
	private List<Fabricante> listafabricantes;
	private List<Fabricante> listafabricantesFiltrados;
	private String acao;
	private Long codigo;

	public Fabricante getFabricanteCadastro() {
		return fabricanteCadastro;
	}

	public void setFabricanteCadastro(Fabricante fabricanteCadastro) {
		this.fabricanteCadastro = fabricanteCadastro;
	}

	public List<Fabricante> getListafabricantes() {
		return listafabricantes;
	}

	public void setListafabricantes(List<Fabricante> listafabricantes) {
		this.listafabricantes = listafabricantes;
	}

	public List<Fabricante> getListafabricantesFiltrados() {
		return listafabricantesFiltrados;
	}

	public void setListafabricantesFiltrados(
			List<Fabricante> listafabricantesFiltrados) {
		this.listafabricantesFiltrados = listafabricantesFiltrados;
	}
	
	public String getAcao() {
		return acao;
	}

	public void setAcao(String acao) {
		this.acao = acao;
	}
	
	public Long getCodigo() {
		return codigo;
	}
	
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public void salvar() {

		try {
			FabricanteDAO fabricanteDAO = new FabricanteDAO();
			fabricanteDAO.salvar(fabricanteCadastro);
			limpar();
			FacesUtil.addMsgInfo("Fabricante salvo com sucesso");
		} catch (RuntimeException ex) {
			FacesUtil.addMsgErro("Erro ao tentar salvar fabricante: "
					+ ex.getMessage());
		}
	}
	
	public void excluir() {

		try {
			FabricanteDAO fabricanteDAO = new FabricanteDAO();
			fabricanteDAO.excluir(fabricanteCadastro);
			
			FacesUtil.addMsgInfo("Fabricante excluido com sucesso");
		} catch (RuntimeException ex) {
			FacesUtil.addMsgErro("Erro ao tentar excluir fabricante: "
					+ ex.getMessage());
		}
	}
	
	public void editar() {

		try {
			FabricanteDAO fabricanteDAO = new FabricanteDAO();
			fabricanteDAO.alterar(fabricanteCadastro);
			
			FacesUtil.addMsgInfo("Fabricante alterado com sucesso");
		} catch (RuntimeException ex) {
			FacesUtil.addMsgErro("Erro ao tentar alterar fabricante: "
					+ ex.getMessage());
		}
	}
	
	public void carregar(){
		try {
			FabricanteDAO fabricanteDAO = new FabricanteDAO();
			listafabricantes = fabricanteDAO.listar();
			
		} catch (RuntimeException ex) {
			FacesUtil.addMsgErro("Erro ao tentar listar fabricantes: "+ ex.getMessage());
		}
	}

	public void limpar() {
		fabricanteCadastro = new Fabricante();
	}
	
	public void carregarCadastro(){
		try {
			if(codigo != null){
				FabricanteDAO fabricanteDAO = new FabricanteDAO();
				fabricanteCadastro = fabricanteDAO.buscarPorCodigo(codigo);
			}else{
				limpar();
			}
			
		} catch (RuntimeException ex) {
			FacesUtil.addMsgErro("Erro ao tentar obter os dados do fabricante: "+ ex.getMessage());
		}
	}

}
