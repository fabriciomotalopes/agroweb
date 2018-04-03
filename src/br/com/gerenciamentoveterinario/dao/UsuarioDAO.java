package br.com.gerenciamentoveterinario.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.gerenciamentoveterinario.domain.Usuario;
import br.com.gerenciamentoveterinario.util.HibernateUtil;

public class UsuarioDAO {

	public void salvar(Usuario objeto) {
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
	public List<Usuario> listar() {

		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Usuario> objetos = null;

		try {
			
			Query query = session.getNamedQuery("Usuario.listar");			
			objetos = query.list();
			
		} catch (RuntimeException ex) {
			throw ex;
		} finally {
			session.close();
		}

		return objetos;
	}

	public Usuario buscarPorId(Long idObjeto) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Usuario objeto = null;

		try {
			
			Query query = session.getNamedQuery("Usuario.buscarPorId");			
			query.setLong("idUsuario", idObjeto);			
			objeto = (Usuario) query.uniqueResult();
			
		} catch (RuntimeException ex) {
			throw ex;
		} finally {
			session.close();
		}

		return objeto;
	}

	public void excluir(Usuario objeto) {
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

	public void editar(Usuario objeto) {
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
	
	public Usuario autenticar(String cpf, String senha){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Usuario objeto = null;

		try {
			
			Query query = session.getNamedQuery("Usuario.autenticar");			
			query.setString("cpf", cpf);	
			query.setString("senha", senha);	
			objeto = (Usuario) query.uniqueResult();
			
		} catch (RuntimeException ex) {
			throw ex;
		} finally {
			session.close();
		}
		
		return objeto;
		
	}

}
