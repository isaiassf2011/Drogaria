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

	public Fabricante getFabricanteCadastro() {
		if (fabricanteCadastro == null) {
			fabricanteCadastro = new Fabricante();
		}
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

	public void salvar() {

		try {
			FabricanteDAO fabricanteDAO = new FabricanteDAO();
			fabricanteDAO.salvar(fabricanteCadastro);
			limpar();
			FacesUtil.addMsgInfo("Fabricante salvo com sucesso");
		} catch (RuntimeException ex) {
			FacesUtil.addMsgErro("Erro ao tentar incluir fabricante: "
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

}
