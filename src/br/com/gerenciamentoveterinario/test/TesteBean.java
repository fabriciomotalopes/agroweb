package br.com.gerenciamentoveterinario.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.gerenciamentoveterinario.domain.Animal;
import br.com.gerenciamentoveterinario.domain.CartaoDeVacina;
import br.com.gerenciamentoveterinario.domain.Usuario;
import br.com.gerenciamentoveterinario.domain.Vacina;

@ManagedBean
@SessionScoped
public class TesteBean {

	private List<Animal> animais;
	private List<CartaoDeVacina> cartoesDeVacinas;
	private List<Usuario> usuarios;
	private List<Vacina> vacinas;

	private List<Animal> animaisFilter;
	private List<CartaoDeVacina> cartoesDeVacinasFilter;
	private List<Usuario> usuariosFilter;
	private List<Vacina> vacinasFilter;

	private Animal animal;
	private CartaoDeVacina cartaoDeVacina;
	private Usuario usuario;
	private Vacina vacina;

	public List<Animal> getAnimais() {
		return animais;
	}

	public void setAnimais(List<Animal> animais) {
		this.animais = animais;
	}

	public List<CartaoDeVacina> getCartoesDeVacinas() {
		return cartoesDeVacinas;
	}

	public void setCartoesDeVacinas(List<CartaoDeVacina> cartoesDeVacinas) {
		this.cartoesDeVacinas = cartoesDeVacinas;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public List<Vacina> getVacinas() {
		return vacinas;
	}

	public void setVacinas(List<Vacina> vacinas) {
		this.vacinas = vacinas;
	}

	public List<Animal> getAnimaisFilter() {
		return animaisFilter;
	}

	public void setAnimaisFilter(List<Animal> animaisFilter) {
		this.animaisFilter = animaisFilter;
	}

	public List<CartaoDeVacina> getCartoesDeVacinasFilter() {
		return cartoesDeVacinasFilter;
	}

	public void setCartoesDeVacinasFilter(List<CartaoDeVacina> cartoesDeVacinasFilter) {
		this.cartoesDeVacinasFilter = cartoesDeVacinasFilter;
	}

	public List<Usuario> getUsuariosFilter() {
		return usuariosFilter;
	}

	public void setUsuariosFilter(List<Usuario> usuariosFilter) {
		this.usuariosFilter = usuariosFilter;
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

	public CartaoDeVacina getCartaoDeVacina() {
		return cartaoDeVacina;
	}

	public void setCartaoDeVacina(CartaoDeVacina cartaoDeVacina) {
		this.cartaoDeVacina = cartaoDeVacina;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Vacina getVacina() {
		return vacina;
	}

	public void setVacina(Vacina vacina) {
		this.vacina = vacina;
	}

	public void teste() {
		
		animais = new ArrayList<>();
		cartoesDeVacinas = new ArrayList<>();
		usuarios = new ArrayList<>();
		vacinas = new ArrayList<>();
		
		animal = new Animal();
		cartaoDeVacina = new CartaoDeVacina();
		usuario = new Usuario();
		vacina = new Vacina();

		Animal a1 = new Animal();
		Animal a2 = new Animal();

		CartaoDeVacina c1 = new CartaoDeVacina();
		CartaoDeVacina c2 = new CartaoDeVacina();

		Usuario u1 = new Usuario();
		Usuario u2 = new Usuario();

		Vacina v1 = new Vacina();
		Vacina v2 = new Vacina();

		u1.setIdUsuario(1L);
		u1.setNome("João da Silva");
		u1.setCpf("111-111-111-11");
		u1.setSenha("123123123");
		u1.setEmail("joao@silva.com.br");
		u1.setEndereco("Avenida Brasil, 68 - centro - São Paulo");

		u2.setIdUsuario(2L);
		u2.setNome("Maria Pereira");
		u2.setCpf("222-222-222-22");
		u2.setSenha("123123123");
		u2.setEmail("maira@pereira.com.br");
		u2.setEndereco("Rua João de Barros, 70 - centro - Unai");

		a1.setIdAnimal(1L);
		a1.setNomeAnimal("Minosa");
		a1.setDataNascimento(new Date());
		a1.setGenero("F");
		a1.setRaca("Nelore");
		a1.setPeso(300.00D);
		a1.setClassificacao("Corte");
		a1.setUsuario(u1);

		a2.setIdAnimal(1L);
		a2.setNomeAnimal("Trovão");
		a2.setDataNascimento(new Date());
		a2.setGenero("M");
		a2.setRaca("Nelore");
		a2.setPeso(500.00D);
		a2.setClassificacao("Corte");
		a2.setUsuario(u1);

		v1.setIdVacina(1L);
		v1.setNomeVacina("Imunovet");
		v1.setDescricaoVacina("Vacina contra frebre aftosa");
		v1.setFabricante("Bayer");
		v1.setDosagem("10ml");
		v1.setQuantidadeDoses(100L);
		v1.setUsuario(u2);

		v2.setIdVacina(2L);
		v2.setNomeVacina("Acura");
		v2.setDescricaoVacina("Vacina contra brucelose");
		v2.setFabricante("FMC");
		v2.setDosagem("100ml");
		v2.setQuantidadeDoses(150L);
		v2.setUsuario(u2);

		c1.setIdCartaoDeVacina(1L);
		c1.setDataVacina(new Date());
		c1.setAnimal(a1);
		c1.setVacina(v2);

		c2.setIdCartaoDeVacina(1L);
		c2.setDataVacina(new Date());
		c2.setAnimal(a2);
		c2.setVacina(v1);

		animal = a1;
		usuario = u1;
		vacina = v1;
		cartaoDeVacina = c1;

		animais.add(a1);
		animais.add(a2);

		vacinas.add(v1);
		vacinas.add(v2);

		usuarios.add(u1);
		usuarios.add(u2);

		cartoesDeVacinas.add(c1);
		cartoesDeVacinas.add(c2);

	}

}
