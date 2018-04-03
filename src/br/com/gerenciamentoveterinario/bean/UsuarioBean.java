package br.com.gerenciamentoveterinario.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.codec.digest.DigestUtils;

import br.com.gerenciamentoveterinario.dao.UsuarioDAO;
import br.com.gerenciamentoveterinario.domain.Usuario;
import br.com.gerenciamentoveterinario.util.FacesUtil;

@ManagedBean
@ViewScoped
public class UsuarioBean {

	private Usuario usuario;
	private List<Usuario> usuarios;
	private List<Usuario> usuariosFilter;

	private String acao = "Novo";
	private Long codigo;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public List<Usuario> getUsuariosFilter() {
		return usuariosFilter;
	}

	public void setUsuariosFilter(List<Usuario> usuariosFilter) {
		this.usuariosFilter = usuariosFilter;
	}

	public String getAcao() {
		return acao;
	}

	public void setAcao(String acao) {
		this.acao = acao;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public void salvar() {

		try {

			UsuarioDAO dao = new UsuarioDAO();
			
			//Criptografando a senha para MD5
			usuario.setSenha(DigestUtils.md5Hex(usuario.getSenha()));
			
			dao.salvar(usuario);

			usuario = new Usuario();

			FacesUtil.adicionarMsgInfo("Usuário CADASTRADO com sucesso!");

		} catch (RuntimeException ex) {

			ex.printStackTrace();
			FacesUtil.adicionarMsgErro("Erro ao tentar CADASTRAR um novo Usuário: " + ex.getMessage());

		}
	}

	public void pesquisar() {

		try {

			UsuarioDAO dao = new UsuarioDAO();
			usuarios = dao.listar();

		} catch (RuntimeException ex) {

			ex.printStackTrace();
			FacesUtil.adicionarMsgErro("Erro ao tentar LISTAR os Usuário: " + ex.getMessage());

		}
	}

	public void carregarUsuario() {

		try {

			if (codigo != null) {

				UsuarioDAO dao = new UsuarioDAO();
				usuario = dao.buscarPorId(codigo);

			} else {
				usuario = new Usuario();
			}

		} catch (RuntimeException ex) {

			ex.printStackTrace();
			FacesUtil.adicionarMsgErro("Erro ao tentar CARREGAR os dados do Usuário: " + ex.getMessage());

		}
	}

	public String excluir() {

		try {

			UsuarioDAO dao = new UsuarioDAO();
			dao.excluir(usuario);

			FacesUtil.adicionarMsgInfo("Usuário EXCLUIDO com sucesso!");

			return "/pages/usuarioPesquisa.xhtml?faces-redirect=true";

		} catch (RuntimeException ex) {

			ex.printStackTrace();
			FacesUtil.adicionarMsgErro("Erro ao tentar EXCLUIR Usuário: " + ex.getMessage());

			return null;

		}
	}

	public String editar() {

		try {

			UsuarioDAO dao = new UsuarioDAO();
			
			//Criptografando a senha para MD5
			usuario.setSenha(DigestUtils.md5Hex(usuario.getSenha()));
			
			dao.editar(usuario);

			FacesUtil.adicionarMsgInfo("Usuário EDITADO com sucesso!");

			return "/pages/usuarioPesquisa.xhtml?faces-redirect=true";

		} catch (RuntimeException ex) {

			ex.printStackTrace();
			FacesUtil.adicionarMsgErro("Erro ao tentar EDITAR um Usuário: " + ex.getMessage());

			return null;

		}
	}

}
