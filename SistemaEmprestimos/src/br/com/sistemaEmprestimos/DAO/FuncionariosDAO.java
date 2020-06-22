package br.com.testecoperalfa.DAO;


import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.testecoperalfa.domain.Funcionarios;
import br.com.testecoperalfa.factory.conexao;


public class FuncionariosDAO {
	
	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	
	public void salvar(Funcionarios f) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("insert into funcionarios ");
		sql.append("(nome, cpf, setor, cargo,dataadm ) ");
		sql.append("values (?,?,?,?,?) ");

		Connection cone = conexao.conectar();

		PreparedStatement comando = cone.prepareStatement(sql.toString());
		
		Date dataadm = (f.getDataadm());
		
		comando.setString(1, f.getNome());
		comando.setString(2, f.getCpf());
		comando.setString(3, f.getSetor());
		comando.setString(4, f.getCargo());	
		comando.setString(5, df.format(dataadm));
		
		comando.executeUpdate();

	}

	public void excluir(Funcionarios f) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("delete from funcionarios ");
		sql.append("where idfunc=?");

		Connection cone = conexao.conectar();

		PreparedStatement comando = cone.prepareStatement(sql.toString());
		comando.setLong(1, f.getIdfunc());
		comando.executeUpdate();

	}

	public void editar(Funcionarios f) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("update funcionarios ");
		sql.append("set nome=?, cpf=?, setor=?, cargo=?, dataadm=?  ");
		sql.append("where idfunc=?");

		Connection cone = conexao.conectar();

		PreparedStatement comando = cone.prepareStatement(sql.toString());
		
		Date dataadm = (f.getDataadm());

		comando.setString(1, f.getNome());
		comando.setString(2, f.getCpf());
		comando.setString(3, f.getSetor());
		comando.setString(4, f.getCargo());
		comando.setString(5, df.format(dataadm));
		
		comando.setLong(6, f.getIdfunc());
		comando.executeUpdate();

	}

	public ArrayList<Funcionarios> listar() throws SQLException {
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
			f.setCpf(resultado.getString("cpf"));
			f.setSetor(resultado.getString("setor"));
			f.setCargo(resultado.getString("cargo"));
			f.setDataadm(resultado.getDate("dataadm"));
			
			
			

			lista.add(f);
		}
		return lista;
	}

}
