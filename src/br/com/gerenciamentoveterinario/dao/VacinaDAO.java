package br.com.gerenciamentoveterinario.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.gerenciamentoveterinario.domain.Vacina;
import br.com.gerenciamentoveterinario.util.HibernateUtil;

public class VacinaDAO {
	
	public void salvar(Vacina objeto) {
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
	public List<Vacina> listar() {

		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Vacina> objetos = null;

		try {
			
			Query query = session.getNamedQuery("Vacina.listar");			
			objetos = query.list();
			
		} catch (RuntimeException ex) {
			throw ex;
		} finally {
			session.close();
		}

		return objetos;
	}

	public Vacina buscarPorId(Long idObjeto) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Vacina objeto = null;

		try {
			
			Query query = session.getNamedQuery("Vacina.buscarPorId");			
			query.setLong("idVacina", idObjeto);			
			objeto = (Vacina) query.uniqueResult();
			
		} catch (RuntimeException ex) {
			throw ex;
		} finally {
			session.close();
		}

		return objeto;
	}

	public void excluir(Vacina objeto) {
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

	public void editar(Vacina objeto) {
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
