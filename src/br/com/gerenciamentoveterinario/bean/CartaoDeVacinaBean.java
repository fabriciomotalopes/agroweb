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
public class CartaoDeVacinaBean {

	private CartaoDeVacina cartaoDeVacina;
	private List<CartaoDeVacina> cartaoDeVacinas;
	private List<CartaoDeVacina> cartaoDeVacinasFilter;

	private Animal animal;
	private List<Animal> animais;
	private List<Animal> animaisFilter;

	private List<Vacina> vacinas;
	private Vacina vacina;

	private String acao;
	private Long codigo;

	public CartaoDeVacina getCartaoDeVacina() {
		if (cartaoDeVacina == null) {
			cartaoDeVacina = new CartaoDeVacina();
		}
		return cartaoDeVacina;
	}

	public void setCartaoDeVacina(CartaoDeVacina cartaoDeVacina) {
		this.cartaoDeVacina = cartaoDeVacina;
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

	public List<Vacina> getVacinas() {
		return vacinas;
	}

	public void setVacinas(List<Vacina> vacinas) {
		this.vacinas = vacinas;
	}

	public Vacina getVacina() {
		return vacina;
	}

	public void setVacina(Vacina vacina) {
		this.vacina = vacina;
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

	public void carregarAnimalVacinar() {

		try {

			if (codigo != null) {

				AnimalDAO dao = new AnimalDAO();
				animal = dao.buscarPorId(codigo);

			} else {
				animal = new Animal();
			}

			VacinaDAO vacinaDAO = new VacinaDAO();
			vacinas = vacinaDAO.listar();

		} catch (RuntimeException ex) {

			ex.printStackTrace();
			FacesUtil.adicionarMsgErro("Erro ao tentar CARREGAR os dados dos Animais: " + ex.getMessage());

		}
	}

	public String vacinarGado() {

		try {

			CartaoDeVacinaDAO cartaoDeVacinaDAO = new CartaoDeVacinaDAO();
			VacinaDAO vacinaDAO = new VacinaDAO();

			cartaoDeVacina.setAnimal(animal);
			cartaoDeVacina.setVacina(vacina);

			if (cartaoDeVacina.getVacina().getQuantidadeDoses() > 0) {
				
				cartaoDeVacinaDAO.salvar(cartaoDeVacina);
				
				vacina.setQuantidadeDoses(vacina.getQuantidadeDoses() -1);
				vacinaDAO.editar(vacina);
				
				FacesUtil.adicionarMsgInfo("Animal VACINADO com sucesso.");
				return "/pages/animalPesquisa.xhtml?faces-redirect=true";
				
			}else{
				FacesUtil.adicionarMsgErro("Quantidade de Doses insuficiente da vacina selecionada.");
				return null;
			}

		} catch (RuntimeException ex) {

			ex.printStackTrace();
			FacesUtil.adicionarMsgErro("Erro ao tentar VACINAR um Animal: " + ex.getMessage());
			return null;

		}

	}

}
