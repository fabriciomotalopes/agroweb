package br.com.gerenciamentoveterinario.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.gerenciamentoveterinario.dao.AnimalDAO;
import br.com.gerenciamentoveterinario.dao.CartaoDeVacinaDAO;
import br.com.gerenciamentoveterinario.dao.VacinaDAO;
import br.com.gerenciamentoveterinario.domain.Animal;
import br.com.gerenciamentoveterinario.domain.CartaoDeVacina;
import br.com.gerenciamentoveterinario.domain.Vacina;
import br.com.gerenciamentoveterinario.util.FacesUtil;

@ManagedBean
@ViewScoped
public class RelatorioBean {

	private Vacina vacina;
	private List<Vacina> vacinas;
	private List<Vacina> vacinasFilter;

	private Animal animal;
	private List<Animal> animais;
	private List<Animal> animaisFilter;

	private List<CartaoDeVacina> cartaoDeVacinas;
	private List<CartaoDeVacina> cartaoDeVacinasFilter;

	private String acao = "Comprar";
	private Long codigo;

	private Long quatidadeDeDosesCompradas;

	public Vacina getVacina() {
		return vacina;
	}

	public void setVacina(Vacina vacina) {
		this.vacina = vacina;
	}

	public List<Vacina> getVacinas() {
		return vacinas;
	}

	public void setVacinas(List<Vacina> vacinas) {
		this.vacinas = vacinas;
	}

	public List<Vacina> getVacinasFilter() {
		return vacinasFilter;
	}

	public void setVacinasFilter(List<Vacina> vacinasFilter) {
		this.vacinasFilter = vacinasFilter;
	}

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

	public List<CartaoDeVacina> getCartaoDeVacinas() {
		return cartaoDeVacinas;
	}

	public void setCartaoDeVacinas(List<CartaoDeVacina> cartaoDeVacinas) {
		this.cartaoDeVacinas = cartaoDeVacinas;
	}

	public List<CartaoDeVacina> getCartaoDeVacinasFilter() {
		return cartaoDeVacinasFilter;
	}

	public void setCartaoDeVacinasFilter(List<CartaoDeVacina> cartaoDeVacinasFilter) {
		this.cartaoDeVacinasFilter = cartaoDeVacinasFilter;
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

	public Long getQuatidadeDeDosesCompradas() {
		return quatidadeDeDosesCompradas;
	}

	public void setQuatidadeDeDosesCompradas(Long quatidadeDeDosesCompradas) {
		this.quatidadeDeDosesCompradas = quatidadeDeDosesCompradas;
	}

	public void estoqueVacinas() {

		try {

			VacinaDAO dao = new VacinaDAO();
			vacinas = dao.listar();

		} catch (RuntimeException ex) {

			ex.printStackTrace();
			FacesUtil.adicionarMsgErro("Erro ao tentar LISTAR o Estoque de Vacinas: " + ex.getMessage());

		}
	}

	public void carregarVacina() {

		try {

			if (codigo != null) {

				VacinaDAO dao = new VacinaDAO();
				vacina = dao.buscarPorId(codigo);

			} else {
				vacina = new Vacina();
			}

		} catch (RuntimeException ex) {

			ex.printStackTrace();
			FacesUtil.adicionarMsgErro("Erro ao tentar CARREGAR os dados da Vacina: " + ex.getMessage());

		}
	}

	public String registrarCompra() {

		try {

			vacina.setQuantidadeDoses(vacina.getQuantidadeDoses() + quatidadeDeDosesCompradas);

			VacinaDAO dao = new VacinaDAO();
			dao.editar(vacina);

			FacesUtil.adicionarMsgInfo("COMPRA de vacinas REGISTRADA com sucesso!");

			return "/pages/relatorioEstoqueVacina.xhtml?faces-redirect=true";

		} catch (RuntimeException ex) {

			ex.printStackTrace();
			FacesUtil.adicionarMsgErro("Erro ao tentar REGISTRA COMPRA de novas vacinas: " + ex.getMessage());

			return null;

		}
	}

	public void cartaoDeVacina() {

		try {

			AnimalDAO dao = new AnimalDAO();
			animais = dao.listar();

		} catch (RuntimeException ex) {

			ex.printStackTrace();
			FacesUtil.adicionarMsgErro("Erro ao tentar LISTAR os Animais: " + ex.getMessage());

		}
	}

	public void visualizarCartaoDeVacina() {

		try {
			
			CartaoDeVacinaDAO cartaoDeVacinaDAO = new CartaoDeVacinaDAO();
			AnimalDAO animalDAO = new AnimalDAO();
			
			animal = animalDAO.buscarPorId(codigo);
			cartaoDeVacinas = cartaoDeVacinaDAO.buscarAnimal(animal);			

		} catch (RuntimeException ex) {

			ex.printStackTrace();
			FacesUtil.adicionarMsgErro("Erro ao tentar LISTAR o Cartão de Vacina: " + ex.getMessage());

		}
	}

}
