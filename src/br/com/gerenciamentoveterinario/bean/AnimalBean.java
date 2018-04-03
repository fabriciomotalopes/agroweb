package br.com.gerenciamentoveterinario.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import br.com.gerenciamentoveterinario.dao.AnimalDAO;
import br.com.gerenciamentoveterinario.dao.UsuarioDAO;
import br.com.gerenciamentoveterinario.domain.Animal;
import br.com.gerenciamentoveterinario.domain.Usuario;
import br.com.gerenciamentoveterinario.util.FacesUtil;

@ManagedBean
@ViewScoped
public class AnimalBean {

	private Animal animal;
	private List<Animal> animais;
	private List<Animal> animaisFilter;
	
	@ManagedProperty(value = "#{autenticacaoBean}")
	private AutenticacaoBean autenticacaoBean;

	private String acao;
	private Long codigo;

	public Animal getAnimal() {
		return animal;
	}

	public void setAnimal(Animal animal) {
		this.animal = animal;
	}

	public List<Animal> getAnimais() {
		return animais;
	}

	public void setAnimais(List<Animal> animais) {
		this.animais = animais;
	}

	public List<Animal> getAnimaisFilter() {
		return animaisFilter;
	}

	public void setAnimaisFilter(List<Animal> animaisFilter) {
		this.animaisFilter = animaisFilter;
	}

	public AutenticacaoBean getAutenticacaoBean() {
		return autenticacaoBean;
	}

	public void setAutenticacaoBean(AutenticacaoBean autenticacaoBean) {
		this.autenticacaoBean = autenticacaoBean;
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

			Usuario usuario = new Usuario();
			UsuarioDAO usuarioDAO = new UsuarioDAO();

			usuario = usuarioDAO.buscarPorId(autenticacaoBean.getUsuarioLogado().getIdUsuario());

			animal.setUsuario(usuario);

			AnimalDAO dao = new AnimalDAO();
			dao.salvar(animal);

			animal = new Animal();

			FacesUtil.adicionarMsgInfo("Animal CADASTRADO com sucesso!");

		} catch (RuntimeException ex) {

			ex.printStackTrace();
			FacesUtil.adicionarMsgErro("Erro ao tentar CADASTRAR um novo Animal: " + ex.getMessage());

		}
	}

	public void pesquisar() {

		try {

			AnimalDAO dao = new AnimalDAO();
			animais = dao.listar();

		} catch (RuntimeException ex) {

			ex.printStackTrace();
			FacesUtil.adicionarMsgErro("Erro ao tentar LISTAR os Animais: " + ex.getMessage());

		}
	}

	public void carregarAnimal() {

		try {

			if (codigo != null) {

				AnimalDAO dao = new AnimalDAO();
				animal = dao.buscarPorId(codigo);

			} else {
				animal = new Animal();
			}

		} catch (RuntimeException ex) {

			ex.printStackTrace();
			FacesUtil.adicionarMsgErro("Erro ao tentar CARREGAR os dados dos Animais: " + ex.getMessage());

		}
	}

	public String excluir() {

		try {

			AnimalDAO dao = new AnimalDAO();
			dao.excluir(animal);

			FacesUtil.adicionarMsgInfo("Animal EXCLUIDO com sucesso!");

			return "/pages/animalPesquisa.xhtml?faces-redirect=true";

		} catch (RuntimeException ex) {

			ex.printStackTrace();
			FacesUtil.adicionarMsgErro("Erro ao tentar EXCLUIR Animal: " + ex.getMessage());

			return null;
		}
	}

	public String editar() {

		try {

			AnimalDAO dao = new AnimalDAO();
			dao.editar(animal);

			FacesUtil.adicionarMsgInfo("Animal EDITADO com sucesso!");

			return "/pages/animalPesquisa.xhtml?faces-redirect=true";

		} catch (RuntimeException ex) {

			ex.printStackTrace();
			FacesUtil.adicionarMsgErro("Erro ao tentar EDITAR um Animal: " + ex.getMessage());

			return null;

		}
	}

}
