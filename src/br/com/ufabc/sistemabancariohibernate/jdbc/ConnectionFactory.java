package br.com.ufabc.sistemabancariohibernate.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
	public Connection getConnection() {
		
		System.out.println("Conectando ao banco de dados");
		
		try {
			String url = "jdbc:postgresql://localhost/SistemaBancarioHibernate";
			return DriverManager.getConnection(url, "postgres", "ufabc");
			// para o H2: descomente a parte abaixo e comente as duas linhas
			// acima
			// String url = "jdbc:h2:tcp://localhost/~/progweb";
			// return DriverManager.getConnection(url,"admin","admin");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
