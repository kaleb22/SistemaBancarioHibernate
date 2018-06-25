package br.com.ufabc.sistemabancariohibernate.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.ufabc.sistemabancariohibernate.jdbc.ConnectionFactory;
import br.com.ufabc.sistemabancariohibernate.model.ContaCorrente;

public class ContaCorrenteDao {

	public void save(ContaCorrente cc) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("sistemabancario");
		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();
		try {
			if(cc.getId() > 0) { // ja existe no banco, so altera
				manager.merge(cc);
				manager.getTransaction().commit();
				System.out.println("Conta atualizada!");
			} else {
				manager.persist(cc);
				manager.getTransaction().commit();
				System.out.println("Conta inserida!");
			}
		} finally {
			if (manager.getTransaction().isActive())
				manager.getTransaction().rollback();
		}
		manager.close();
	}

	public ContaCorrente buscaPorDescricao(String descricao){
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("sistemabancario");
		EntityManager manager = factory.createEntityManager();
		ContaCorrente cc = manager.createQuery("select c from ContaCorrente c where c.descricao like :descricao", ContaCorrente.class)
				.setParameter("descricao", descricao)
				.getSingleResult();
		manager.close();
		return cc;
	}

	public ContaCorrente buscaPeloNumeroEAgencia(String numero, String agencia){
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("sistemabancario");
		EntityManager manager = factory.createEntityManager();
		ContaCorrente cc = manager.createQuery("select c from ContaCorrente c where c.numero = :numeroContaCorrente and c.agencia = :agencia", ContaCorrente.class)
				.setParameter("numeroContaCorrente", numero)
				.setParameter("agencia", agencia)
				.getSingleResult();
		manager.close();
		return cc;
	}

	public ContaCorrente buscaPeloNumero(String numeroContaCorrente) {

		try{
			EntityManagerFactory factory = Persistence.createEntityManagerFactory("sistemabancario");
			EntityManager manager = factory.createEntityManager();
			ContaCorrente cc = manager.createQuery("select c from ContaCorrente c where c.numero = :numeroContaCorrente", ContaCorrente.class).setParameter("numeroContaCorrente", numeroContaCorrente).getSingleResult();
			manager.close();
			return cc;
		} catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}

	public void remove(ContaCorrente cc) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("sistemabancario");
		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();
		cc = manager.find(ContaCorrente.class, cc.getId());
		try {
			manager.remove(cc);
			manager.getTransaction().commit();
		} finally {
			if (manager.getTransaction().isActive())
				manager.getTransaction().rollback();
		}
		manager.close();
	}

	public void altera(ContaCorrente cc) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("sistemabancario");
		EntityManager manager = factory.createEntityManager();
		try {
			manager.getTransaction().begin();
			manager.merge(cc);
			manager.getTransaction().commit();
		} finally {
			if (manager.getTransaction().isActive())
				manager.getTransaction().rollback();
		}
		manager.close();
	}

	public List<ContaCorrente> consultaBanco() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("sistemabancario");
		EntityManager manager = factory.createEntityManager();
		@SuppressWarnings("unchecked")
		List<ContaCorrente> contas = manager.createQuery("select c from	ContaCorrente c").getResultList();
		manager.close();
		return contas;
	}
}