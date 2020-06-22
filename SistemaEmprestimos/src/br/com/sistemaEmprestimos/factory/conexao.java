package br.com.testecoperalfa.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexao {
	private static final String USUARIO = "root";
	private static final String SENHA = "";
	private static final String URL = "jdbc:mysql://localhost:3306/sistema";
	
	public static Connection conectar() throws SQLException{
		
		
		DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		
		Connection conexao = DriverManager.getConnection(URL,USUARIO,SENHA);
			return conexao;
	}
	
	 public static void main(String[] args) {
		try{
			Connection cone = conexao.conectar();
			
			System.out.println("conexão estabelecida com sucesso!");
		
		}catch(SQLException e){
			System.out.println(e);
	  }
	 }		
}
