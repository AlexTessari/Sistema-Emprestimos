package br.com.testecoperalfa.test;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JOptionPane;

import org.junit.Ignore;
import org.junit.Test;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.testecoperalfa.DAO.FuncionariosDAO;
import br.com.testecoperalfa.domain.Funcionarios;
import br.com.testecoperalfa.factory.conexao;

public class FuncionariosDAOTeste {

	@Test
	@Ignore
	public void salvar() throws SQLException {
		Funcionarios e2 = new Funcionarios();

		e2.setNome("Gilberto");
		e2.setCpf(773893984l);
		e2.setSetor("Manutenção");
		e2.setCargo("Técnico");

		String data = new String();
		data = ("05/05/1983");
		String dia = data.substring(0, 2);
		String mes = data.substring(3, 5);
		String ano = data.substring(6);
		String dataa = ano + "-" + mes + "-" + dia;

		e2.setDataadm(dataa);

		FuncionariosDAO fdao = new FuncionariosDAO();

		fdao.salvar(e2);

	}

	@Test
	@Ignore
	public void listar() throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("select idfunc,nome,cpf,setor,cargo,dataadm ");
		sql.append("from funcionarios ");
		sql.append("order by nome");

		Connection cone = conexao.conectar();

		PreparedStatement comando = cone.prepareStatement(sql.toString());

		ResultSet resultado = comando.executeQuery();

		ArrayList<Funcionarios> lista = new ArrayList<Funcionarios>();

		while (resultado.next()) {
			Funcionarios f = new Funcionarios();
			f.setIdfunc(resultado.getLong("idfunc"));
			f.setNome(resultado.getString("nome"));
			f.setCpf(resultado.getLong("cpf"));
			f.setSetor(resultado.getString("setor"));
			f.setCargo(resultado.getString("cargo"));
			f.setDataadm(resultado.getString("dataadm"));

			lista.add(f);
		}

		System.out.println(lista);
	}

	@Test
	public void teste() throws SQLException {
		Funcionarios f = new Funcionarios();
		Calendar c = Calendar.getInstance();
		int numPar = Integer.parseInt(f.getDataadm());
		Double valEmp = Double.parseDouble(JOptionPane.showInputDialog("Valor do emprestimo"));
		Date diaAtual = new Date();
		
		valEmp = (valEmp*5/100+valEmp);
		for (int e = 1; e <= numPar; e++) {
			c.setTime(diaAtual);
			c.roll(Calendar.MONTH, e);
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Date d = (Date) c.getTime();

			System.out.print("Parcela " + e + ": ");
			System.out.print(df.format(d)+ " ");
			System.out.printf( "%.2f \n", valEmp);

		}
	}
}
