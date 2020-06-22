package br.com.testecoperalfa.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Ignore;
import org.junit.Test;

import br.com.testecoperalfa.DAO.EmprestimosDAO;

import br.com.testecoperalfa.domain.Emprestimos;
import br.com.testecoperalfa.domain.Funcionarios;
import br.com.testecoperalfa.factory.conexao;

public class EmprestimosDAOTeste {

	@Test
	@Ignore
	public void salvar() throws SQLException, ParseException {
		Emprestimos e2 = new Emprestimos();
		e2.setparcela(5l);
		e2.setvalorparc(700.00);
		e2.setvalorpag(700.00);

		Funcionarios f = new Funcionarios();
		f.setIdfunc(6l);
		e2.setFuncionarios(f);

		EmprestimosDAO fdao = new EmprestimosDAO();

		fdao.salvar(e2);

	}
	@Test
	@Ignore
	public void check() throws SQLException, ParseException{
		StringBuilder sql = new StringBuilder();
		sql.append("select * from emprestimos where idfunc=? ");
		
		Connection cone = conexao.conectar();
		PreparedStatement comando = cone.prepareStatement(sql.toString());
		ResultSet rs = null;
		comando.setLong(1, 33);
		
		rs = comando.executeQuery();
		
		if (rs.next()){
			System.out.println("ja existe");
		
		}else{
			salvar();
		}
	}

	@Test
	@Ignore

	public void listar() throws SQLException, ParseException {

		EmprestimosDAO fdao = new EmprestimosDAO();
		ArrayList<Emprestimos> lista = fdao.listar();

		for (Emprestimos e : lista) {
			
			while(e.getidfunc()!= 33){
				System.out.println("teste");
			}
			
			
			//System.out.println( e.getFuncionarios().getIdfunc());
			
			
		}

	}


	@Test
	@Ignore
	public void editar() throws SQLException {

		Emprestimos e = new Emprestimos();

		e.setIdemp(1l);
		e.setparcela(3l);
		e.setvalorparc(900.00);
		e.setvalorpag(900.00);

		Funcionarios f = new Funcionarios();
		f.setIdfunc(7l);
		e.setFuncionarios(f);

		EmprestimosDAO dao = new EmprestimosDAO();
		dao.editar(e);

	}

	@Test
	@Ignore
	public void teste2() throws SQLException, ParseException {
		GregorianCalendar gc = new GregorianCalendar();
		String vencimento = ("15/11/1983");
		Long numPar = (8l);
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date diaAtual = (java.util.Date) formatter.parse(vencimento);

		Double valEmp = (100.00);
		valEmp = (valEmp * 5 / 100 + valEmp);
		for (int p = 1; p - 1 < numPar; p++) {
			gc.setTime(diaAtual);
			gc.add(GregorianCalendar.MONTH, p);
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Date d = gc.getTime();

			String dia = df.format(d).substring(0, 2);
			String mes = df.format(d).substring(3, 5);
			String ano = df.format(d).substring(6);
			String data = ano + "-" + mes + "-" + dia;

			System.out.println("Parcela:" + p);
			System.out.println(data);
			System.out.println(valEmp);
			System.out.println();
		}

	}

	@Test
	@Ignore
	public void teste3() throws SQLException, ParseException {
		Calendar c = Calendar.getInstance();
		Calendar o = Calendar.getInstance();
		Date venciment = new Date();
		Date dataoperacao = new Date();
		Long numParc = (3l);
		Double valEmpr = (100.00);
		
		
		

		valEmpr = ((valEmpr * 5 / 100 + valEmpr) / numParc);
		for (int p = 1; p - 1 < numParc; p++) {
			c.setTime(venciment);
			o.setTime(dataoperacao);
			c.add(Calendar.MONTH, p);
			o.add(Calendar.MONTH, p);
			
			

			Date d = c.getTime();
			Date op = o.getTime();

			System.out.println(p);
			System.out.println(valEmpr);
			System.out.println(d);
			System.out.println(op);

			// String data = ("15/11/1983");
			// Calendar c = Calendar.getInstance();
		}
		
		}
	@Test	
	public  ArrayList<Emprestimos> pesquisa(Emprestimos e) throws SQLException, ParseException {
		Funcionarios f = new Funcionarios();
		ArrayList<Emprestimos> lista = new ArrayList<Emprestimos>();
        
		
		
		e.setIdemp(16l);
		e.setparcela(2l);
		f.setNome("bivalvo");
		
		lista.add(e);
		
		for (int i = 0; i < lista.size(); i++){
			System.out.println(lista.get(i).getIdemp());
			System.out.println(lista.get(i).getparcela());
			System.out.println(lista.get(i).getFuncionarios().getNome());
			
		}
		return lista;
		
	}
}
