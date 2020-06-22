package br.com.testecoperalfa.DAO;

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
import br.com.testecoperalfa.domain.Emprestimos;
import br.com.testecoperalfa.domain.Funcionarios;
import br.com.testecoperalfa.factory.conexao;
import br.com.testecoperalfa.util.JSFUtil;

public class EmprestimosDAO {
	
	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	
	public void check(Emprestimos e) throws SQLException, ParseException{
		StringBuilder sql = new StringBuilder();
		sql.append("select * from emprestimos where idfunc=? ");
		
		Connection cone = conexao.conectar();
		PreparedStatement comando = cone.prepareStatement(sql.toString());
		ResultSet rs = null;
		comando.setLong(1, e.getFuncionarios().getIdfunc());
		
		rs = comando.executeQuery();
		
		if (rs.next()){
			JSFUtil.MensagemErro("Funcionário já possui emprestimo ativo! ");
		
		}else{
			salvar(e);
			JSFUtil.MensagemSucesso("Salvo com sucesso!");
		}
	}

	public void salvar(Emprestimos e) throws SQLException, ParseException {
		StringBuilder sql = new StringBuilder();
		sql.append("insert into emprestimos ");
		sql.append("(parcela,vencimento, valorparc, dataoperacao, observacao, idfunc) ");
		sql.append("values (?,?,?,?,?,?) ");

		Connection cone = conexao.conectar();

		PreparedStatement comando = cone.prepareStatement(sql.toString());

		Calendar c = Calendar.getInstance(); 
		Calendar o = Calendar.getInstance();
        Date vencimento = (e.getvencimento());
        Date dataoperacao = (e.getDataoperacao());
        Long numPar = (e.getparcela());
        Double valEmp = (e.getvalorparc());
       
		
		valEmp = ((valEmp * 5 / 100 + valEmp)/numPar);
        for (int p = 1; p <= numPar; p++) {
            c.setTime(vencimento);
            o.setTime(dataoperacao);
            c.add(Calendar.MONTH, p-1);
            o.add(Calendar.MONTH, p-1);
                
            Date d = c.getTime();
            Date op = o.getTime();
			
			comando.setLong(1, p);
			comando.setString(2, df.format(d));
			comando.setDouble(3, valEmp);
			comando.setString(4, df.format(op));
			comando.setString(5, e.getObservacao());
			comando.setLong(6, e.getFuncionarios().getIdfunc());
			comando.executeUpdate();
			
		}
	}

	
	
	public void baixar(Emprestimos e) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("update emprestimos ");
		sql.append("set valorpag=? ");
		sql.append("where idemp=?");

		Connection cone = conexao.conectar();

		PreparedStatement comando = cone.prepareStatement(sql.toString());

		
		comando.setDouble(1, e.getvalorparc());
		comando.setLong(2, e.getIdemp());
		comando.executeUpdate();

	}

	public void editar(Emprestimos e) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("update emprestimos ");
		sql.append("set parcela=?, valorparc=?, valorpag=?, idfunc=? ");
		sql.append("where idemp=?");

		Connection cone = conexao.conectar();

		PreparedStatement comando = cone.prepareStatement(sql.toString());

		comando.setLong(1, e.getparcela());
		comando.setDouble(2, e.getvalorparc());
		comando.setDouble(3, e.getvalorpag());
		comando.setLong(4, e.getFuncionarios().getIdfunc());
		comando.setLong(5, e.getIdemp());
		comando.executeUpdate();

	}
	
	
	public ArrayList<Emprestimos> listar() throws SQLException, ParseException {
		StringBuilder sql = new StringBuilder();
		sql.append("select e.idemp, e.parcela, e.vencimento, e.valorparc, e.valorpag, e.idfunc, f.nome ");
		sql.append("from emprestimos e ");
		sql.append("inner join funcionarios f on f.idfunc = e.idfunc order by e.idemp");

		Connection cone = conexao.conectar();

		PreparedStatement comando = cone.prepareStatement(sql.toString());

		ResultSet resultado = comando.executeQuery();

		ArrayList<Emprestimos> lista = new ArrayList<Emprestimos>();

		while (resultado.next()) {
			Funcionarios f = new Funcionarios();	
			f.setNome(resultado.getString("f.nome"));
			
			Emprestimos e = new Emprestimos();
			e.setIdemp(resultado.getLong("e.idemp"));
			e.setparcela(resultado.getLong("e.parcela"));
			e.setvencimento(resultado.getDate("e.vencimento"));
			e.setvalorparc(resultado.getDouble("e.valorparc"));
			e.setvalorpag(resultado.getDouble("e.valorpag"));
			e.setFuncionarios(f);

			lista.add(e);
		}
		return lista;
	}
	public ArrayList<Emprestimos> pesquisa(Emprestimos filtro) throws SQLException, ParseException {
		StringBuilder sql = new StringBuilder();
		sql.append("select e.idemp, e.parcela, e.vencimento, e.valorparc, e.valorpag, e.idfunc,"
				+ " f.nome from emprestimos e inner join funcionarios f on f.idfunc = e.idfunc ");
		sql.append("where e.vencimento between ? and ? ");
		sql.append("order by e.vencimento ");
		

		Connection cone = conexao.conectar();

		PreparedStatement comando = cone.prepareStatement(sql.toString());
		Date di = filtro.getDatainicial();
		Date da = filtro.getDatafinal();
		
		comando.setString(1,df.format(di));
		comando.setString(2,df.format(da));
		

		ResultSet resultado = comando.executeQuery();
		
		ArrayList<Emprestimos> lista = new ArrayList<Emprestimos>();

		while (resultado.next()) {
			Emprestimos e = new Emprestimos();
			Funcionarios f = new Funcionarios();	
			f.setNome(resultado.getString("f.nome"));
			e.setIdemp(resultado.getLong("e.idemp"));
			e.setparcela(resultado.getLong("e.parcela"));
			e.setvencimento(resultado.getDate("e.vencimento"));
			e.setvalorparc(resultado.getDouble("e.valorparc"));
			e.setvalorpag(resultado.getDouble("e.valorpag"));
			e.setFuncionarios(f);

			lista.add(e);
		}
		return lista;
	}
	


}
