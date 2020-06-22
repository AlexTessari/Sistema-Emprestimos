package br.com.testecoperalfa.bean;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.testecoperalfa.DAO.EmprestimosDAO;
import br.com.testecoperalfa.DAO.FuncionariosDAO;
import br.com.testecoperalfa.domain.Emprestimos;
import br.com.testecoperalfa.domain.Funcionarios;
import br.com.testecoperalfa.util.JSFUtil;

@ManagedBean(name = "MBEmprestimos")
@ViewScoped


public class EmprestimosBean {

	private Emprestimos emprestimos;
	private ArrayList<Emprestimos> itens;
	private Emprestimos filtro;
	private List<Emprestimos> lista;
	private ArrayList<Funcionarios> comboFuncionarios;
	

	
	
	
	
	public List<Emprestimos> getLista() {
		return lista;
	}

	public void setLista(List<Emprestimos> lista) {
		this.lista = lista;
	}

	public Emprestimos getFiltro() {
		if(filtro == null){
			filtro = new Emprestimos();
		}
		return filtro;
	}

	public void setFiltro(Emprestimos filtro) {
		this.filtro = filtro;
	}

	public ArrayList<Funcionarios> getComboFuncionarios() {
		return comboFuncionarios;
	}

	public void setComboFuncionarios(ArrayList<Funcionarios> comboFuncionarios) {
		this.comboFuncionarios = comboFuncionarios;
	}

	public Emprestimos getEmprestimos() {
		return emprestimos;
	}

	public void setEmprestimos(Emprestimos emprestimos) {
		this.emprestimos = emprestimos;
	}

	public ArrayList<Emprestimos> getItens() {
		return itens;
	}

	public void setItens(ArrayList<Emprestimos> itens) {
		this.itens = itens;
	}



	@PostConstruct

	public void  prepararPesquisa()  {

		try {
			
			
			EmprestimosDAO edao = new EmprestimosDAO();
			

			itens = edao.listar();

			

		} catch (SQLException e) {
			JSFUtil.MensagemErro(e.getMessage());
			e.printStackTrace();
		} catch (ParseException e) {
			JSFUtil.MensagemErro(e.getMessage());
			e.printStackTrace();
		}

	}
	

	public void prepararnovo() {

		try {
			
			emprestimos = new Emprestimos();

			FuncionariosDAO dao = new FuncionariosDAO();
			comboFuncionarios = dao.listar();
		} catch (SQLException e) {
			JSFUtil.MensagemErro(e.getMessage());
			e.printStackTrace();
		}
	}
	

	public void  cadastro ()  {

		try {
			EmprestimosDAO edao = new EmprestimosDAO();
			edao.check(emprestimos);

			itens = edao.listar();

			

		} catch (SQLException e) {
			JSFUtil.MensagemErro("Funcionário já possui emprestimo ativo! ");
			e.printStackTrace();
		} catch (ParseException e) {
			JSFUtil.MensagemErro(e.getMessage());
			e.printStackTrace();
		}

	}
	
	public void  buscar()  {

		try {
			
			
			EmprestimosDAO edao = new EmprestimosDAO();
			

			itens = edao.pesquisa(filtro);

			

		} catch (SQLException e) {
			JSFUtil.MensagemErro("Preencha os campos de pesquisa corretamente! ");
			e.printStackTrace();
		} catch (ParseException e) {
			JSFUtil.MensagemErro(e.getMessage());
			e.printStackTrace();
		}

	}

		public void Baixar() throws ParseException {

			try {
				
				EmprestimosDAO edao = new EmprestimosDAO();
				edao.baixar(emprestimos);

				
				itens = edao.pesquisa(filtro);
				
				

				JSFUtil.MensagemSucesso("Parcela baixada com sucesso!");

			} catch (SQLException e) {
				JSFUtil.MensagemErro(e.getMessage());
				e.printStackTrace();
			}
		}
			
			}

