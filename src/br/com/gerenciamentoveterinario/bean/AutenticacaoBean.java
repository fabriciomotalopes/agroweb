package br.com.gerenciamentoveterinario.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.codec.digest.DigestUtils;

import br.com.gerenciamentoveterinario.dao.UsuarioDAO;
import br.com.gerenciamentoveterinario.domain.Usuario;
import br.com.gerenciamentoveterinario.util.FacesUtil;

@ManagedBean
@SessionScoped
public class AutenticacaoBean {
	
	private Usuario usuarioLogado;

	public Usuario getUsuarioLogado() {
		if(usuarioLogado == null){
			usuarioLogado = new Usuario();
		}
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}
	
	public String autenticar(){
		try {
			
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			
					
			//O DigestUtils.md5Hex criptografa a senha para o formato MD5
			usuarioLogado = usuarioDAO.autenticar(usuarioLogado.getCpf(), DigestUtils.md5Hex(usuarioLogado.getSenha()));
			
			if(usuarioLogado == null){
				FacesUtil.adicionarMsgErro("CPF e/ou senha inválidos.");
				return null;
			}else{
				FacesUtil.adicionarMsgInfo("Usuário autenticado com sucesso.");
				return "/pages/principal.xhtml?faces-redirect=true";
			}
			
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			FacesUtil.adicionarMsgErro("Erro ao tentar autenticar no sistema: " + ex.getMessage());
			return null;
		}
	}
	
	public String sair(){
		usuarioLogado = null;	
		return "/pages/index.xhtml?faces-redirect=true";
	}

}
