package br.com.testecoperalfa.bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.testecoperalfa.DAO.FuncionariosDAO;
import br.com.testecoperalfa.domain.Funcionarios;
import br.com.testecoperalfa.util.JSFUtil;

@ManagedBean(name = "MBFuncionarios")
@ViewScoped

public class FuncionariosBean {

	private Funcionarios funcionarios;
	private ArrayList<Funcionarios> itens;
	private java.util.Date data;

	public java.util.Date getData() {
		return data;
	}

	public void setData(java.util.Date data) {
		this.data = data;
	}

	public Funcionarios getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(Funcionarios funcionarios) {
		this.funcionarios = funcionarios;
	}

	public ArrayList<Funcionarios> getItens() {
		return itens;
	}

	public void setItens(ArrayList<Funcionarios> itens) {
		this.itens = itens;
	}


	@PostConstruct
	public void prepararPesquisa() {

		try {
			FuncionariosDAO fdao = new FuncionariosDAO();

			itens = fdao.listar();

		} catch (SQLException e) {
			JSFUtil.MensagemErro(e.getMessage());
			e.printStackTrace();
		}

	}

	public void instacia() {
		funcionarios = new Funcionarios();
	}

	public void cadastro() {

		try {
			FuncionariosDAO fdao = new FuncionariosDAO();
			fdao.salvar(funcionarios);

			itens = fdao.listar();

			JSFUtil.MensagemSucesso("Salvo com sucesso!");

		} catch (SQLException e) {
			JSFUtil.MensagemErro("CPF já cadastrado por favor digite outro CPF!");
			e.printStackTrace();
		}

	}

	public void Excluir() {

		try {
			FuncionariosDAO fdao = new FuncionariosDAO();
			fdao.excluir(funcionarios);

			itens = fdao.listar();

			JSFUtil.MensagemSucesso("Excluido com sucesso!");

		} catch (SQLException e) {
			JSFUtil.MensagemErro("Não é possível excluir um funcionário associado a um emprestimo");
			e.printStackTrace();
		}

	}

	public void Editar() {

		try {
			FuncionariosDAO fdao = new FuncionariosDAO();
			fdao.editar(funcionarios);

			itens = fdao.listar();

			JSFUtil.MensagemSucesso("Editado com sucesso!");

		} catch (SQLException e) {
			JSFUtil.MensagemErro(e.getMessage());
			e.printStackTrace();
		}

	}
}
