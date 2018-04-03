package br.com.gerenciamentoveterinario.test;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

import br.com.gerenciamentoveterinario.dao.UsuarioDAO;
import br.com.gerenciamentoveterinario.domain.Usuario;

public class UsuarioPadraoTest {
	
	@Test
	public void criarUsuarioPadrao(){
		
		Usuario usuario = new Usuario();
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		
		usuario.setNome("Admin");
		usuario.setCpf("963.771.451-01");
		
		//Criptografando a senha para MD5
		usuario.setSenha(DigestUtils.md5Hex("123456"));
		
		usuario.setEmail("admin@agroweb.com.br");
		usuario.setEndereco("Av. Governador Valadares, 300 - B. Centro - Unai - MG");
		
		usuarioDAO.salvar(usuario);
		
	}
}
