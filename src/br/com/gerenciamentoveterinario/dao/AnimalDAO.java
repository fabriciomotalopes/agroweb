package br.com.gerenciamentoveterinario.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.gerenciamentoveterinario.domain.Animal;
import br.com.gerenciamentoveterinario.util.HibernateUtil;

public class AnimalDAO {
	
	public void salvar(Animal objeto) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;

		try {

			transaction = session.beginTransaction();
			session.save(objeto);
			transaction.commit();

		} catch (RuntimeException ex) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw ex;
		} finally {
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Animal> listar() {

		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Animal> objetos = null;

		try {

			Query query = session.getNamedQuery("Animal.listar");
			objetos = query.list();

		} catch (RuntimeException ex) {
			throw ex;
		} finally {
			session.close();
		}

		return objetos;
	}

	public Animal buscarPorId(Long idObjeto) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Animal objeto = null;

		try {

			Query query = session.getNamedQuery("Animal.buscarPorId");
			query.setLong("idAnimal", idObjeto);
			objeto = (Animal) query.uniqueResult();

		} catch (RuntimeException ex) {
			throw ex;
		} finally {
			session.close();
		}

		return objeto;
	}

	public void excluir(Animal objeto) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;

		try {

			transaction = session.beginTransaction();
			session.delete(objeto);
			transaction.commit();

		} catch (RuntimeException ex) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw ex;
		} finally {
			session.close();
		}
	}

	public void editar(Animal objeto) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;

		try {

			transaction = session.beginTransaction();
			session.update(objeto);
			transaction.commit();

		} catch (RuntimeException ex) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw ex;
		} finally {
			session.close();
		}
	}
}
