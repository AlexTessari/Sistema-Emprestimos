package br.com.testecoperalfa.test;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import org.junit.Test;

public class FuncionariosDAOTeste {


	@Test
	public void Parcelas() throws SQLException {

		Calendar c = Calendar.getInstance();
		int numPar = Integer.parseInt(JOptionPane.showInputDialog("Quantidade de parcelas"));
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
