package br.com.gerenciamentoveterinario.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.gerenciamentoveterinario.domain.Animal;
import br.com.gerenciamentoveterinario.domain.CartaoDeVacina;
import br.com.gerenciamentoveterinario.util.HibernateUtil;

public class CartaoDeVacinaDAO {
	
	public void salvar(CartaoDeVacina objeto) {
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
	public List<CartaoDeVacina> listar() {

		Session session = HibernateUtil.getSessionFactory().openSession();
		List<CartaoDeVacina> objetos = null;

		try {

			Query query = session.getNamedQuery("CartaoDeVacina.listar");
			objetos = query.list();

		} catch (RuntimeException ex) {
			throw ex;
		} finally {
			session.close();
		}

		return objetos;
	}

	public CartaoDeVacina buscarPorId(Long idObjeto) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		CartaoDeVacina objeto = null;

		try {

			Query query = session.getNamedQuery("CartaoDeVacina.buscarPorId");
			query.setLong("idAnimalVacinacao", idObjeto);
			objeto = (CartaoDeVacina) query.uniqueResult();

		} catch (RuntimeException ex) {
			throw ex;
		} finally {
			session.close();
		}

		return objeto;
	}

	public void excluir(CartaoDeVacina objeto) {
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

	public void editar(CartaoDeVacina objeto) {
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
	
	@SuppressWarnings("unchecked")
	public List<CartaoDeVacina> buscarAnimal(Animal idObjeto) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		List<CartaoDeVacina> objetos = null;

		try {

			Query query = session.getNamedQuery("CartaoDeVacina.buscarAnimal");
			query.setEntity("animal", idObjeto);
			objetos = query.list();

		} catch (RuntimeException ex) {
			throw ex;
		} finally {
			session.close();
		}

		return objetos;
	}

}
