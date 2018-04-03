package br.com.gerenciamentoveterinario.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cascade;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "tbl_animal")
@NamedQueries({
	@NamedQuery(name = "Animal.listar", query = "SELECT animal FROM Animal animal"),
	@NamedQuery(name = "Animal.buscarPorId", query = "SELECT animal FROM Animal animal WHERE animal.idAnimal = :idAnimal")
})
public class Animal {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_animal")
	private Long idAnimal;
	
	@Size(max = 40, message = "Quanitidade de caracteres Inválido.")
	@Column(name = "nome_animal", length = 40, nullable = true)
	private String nomeAnimal;
	
	@NotEmpty(message = "O campo raça é Obrigatório.")
	@Size(min = 1, max = 40, message = "Quanitidade de caracteres Inválido.")
	@Column(name = "raca_animal", length = 40, nullable = false)
	private String raca;
	
	@NotNull(message = "O campo data de nascimento é Obrigatório.")
	@Temporal(value = TemporalType.DATE)
	@Column(name = "data_nasc", nullable = false)
	private Date dataNascimento;
	
	@NotNull(message = "O campo peso é Obrigatório.")
	@DecimalMin(value = "0.0", message = "Informe um peso maior que 0 para o peso.")
	@DecimalMax(value = "5000.000", message = "Informe um peso maior que 5000kg para o peso.")
	@Column(name = "peso_animal", precision = 7, scale = 3, nullable = false)
	private Double peso;
	
	@NotEmpty(message = "O campo genero é Obrigatório.")
	@Size(min = 1, max = 1, message = "Quanitidade de caracteres Inválido.")
	@Column(name = "genero_animal", length = 4, nullable = false)
	private String genero;
	
	@NotEmpty(message = "O campo classificação é Obrigatório.")
	@Size(min = 1, max = 8, message = "Quanitidade de caracteres Inválido.")
	@Column(name = "classificacao_animal", length = 8, nullable = false)
	private String classificacao;
	

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "tbl_usuario_id_usuario", referencedColumnName = "id_usuario")
	private Usuario usuario;
	
	@OneToMany(mappedBy = "animal", fetch = FetchType.LAZY)
	@Cascade(org.hibernate.annotations.CascadeType.ALL)
	private List<CartaoDeVacina> cartaoDeVacina;
	
	public Long getIdAnimal() {
		return idAnimal;
	}

	public void setIdAnimal(Long idAnimal) {
		this.idAnimal = idAnimal;
	}

	public String getNomeAnimal() {
		return nomeAnimal;
	}

	public void setNomeAnimal(String nomeAnimal) {
		this.nomeAnimal = nomeAnimal;
	}

	public String getRaca() {
		return raca;
	}

	public void setRaca(String raca) {
		this.raca = raca;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Double getPeso() {
		return peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getClassificacao() {
		return classificacao;
	}

	public void setClassificacao(String classificacao) {
		this.classificacao = classificacao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<CartaoDeVacina> getCartaoDeVacina() {
		return cartaoDeVacina;
	}

	public void setCartaoDeVacina(List<CartaoDeVacina> cartaoDeVacina) {
		this.cartaoDeVacina = cartaoDeVacina;
	}

	@Override
	public String toString() {
		return "Animal [idAnimal=" + idAnimal + ", nomeAnimal=" + nomeAnimal + ", raca=" + raca + ", dataNascimento="
				+ dataNascimento + ", peso=" + peso + ", genero=" + genero + ", classificacao=" + classificacao
				+ ", usuario=" + usuario + ", cartaoDeVacina=" + cartaoDeVacina + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idAnimal == null) ? 0 : idAnimal.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Animal other = (Animal) obj;
		if (idAnimal == null) {
			if (other.idAnimal != null)
				return false;
		} else if (!idAnimal.equals(other.idAnimal))
			return false;
		return true;
	}
		
}
