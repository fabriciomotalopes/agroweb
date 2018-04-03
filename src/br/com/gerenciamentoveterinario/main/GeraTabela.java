package br.com.gerenciamentoveterinario.main;

import br.com.gerenciamentoveterinario.util.HibernateUtil;

public class GeraTabela {

	public static void main(String[] args) {
		HibernateUtil.getSessionFactory();
		HibernateUtil.getSessionFactory().close();	

	}

}
