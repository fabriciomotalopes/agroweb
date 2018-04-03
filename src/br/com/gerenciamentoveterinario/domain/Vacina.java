package br.com.gerenciamentoveterinario.domain;

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
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cascade;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "tbl_vacina")
@NamedQueries({ @NamedQuery(name = "Vacina.listar", query = "SELECT vacina FROM Vacina vacina"),
		@NamedQuery(name = "Vacina.buscarPorId", query = "SELECT vacina FROM Vacina vacina WHERE vacina.idVacina = :idVacina") 
})
public class Vacina {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_vacina")
	private Long idVacina;

	@NotEmpty(message = "O campo nome da vacina é Obrigatório.")
	@Size(min = 1, max = 60, message = "Quanitidade de caracteres Inválido.")
	@Column(name = "nome_vaciana", length = 60, nullable = false)
	private String nomeVacina;
	
	@NotEmpty(message = "O campo descrição da vacina é Obrigatório.")
	@Size(min = 1, max = 60, message = "Quanitidade de caracteres Inválido.")
	@Column(name = "descricao_vaciana", length = 60, nullable = false)
	private String descricaoVacina;

	@NotEmpty(message = "O campo fabricante da vacina é Obrigatório.")
	@Size(min = 1, max = 60, message = "Quanitidade de caracteres Inválido.")
	@Column(name = "fabricante_vaciana", length = 60, nullable = false)
	private String fabricante;
	
	@NotEmpty(message = "O campo dosagem é Obrigatório.")
	@Size(min = 1, max = 6, message = "Quanitidade de caracteres Inválido.")
	@Column(name = "dosagem", length = 6, nullable = false)
	private String dosagem;
	
	@Column(name = "quantidade_doses", length = 10, nullable = true)
	private Long quantidadeDoses;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "tbl_usuario_id_usuario", referencedColumnName = "id_usuario")
	private Usuario usuario;

	@OneToMany(mappedBy = "vacina", fetch = FetchType.LAZY)
	@Cascade(org.hibernate.annotations.CascadeType.ALL)
	private List<CartaoDeVacina> cartaoDeVacina;

	public Long getIdVacina() {
		return idVacina;
	}

	public void setIdVacina(Long idVacina) {
		this.idVacina = idVacina;
	}

	public String getNomeVacina() {
		return nomeVacina;
	}

	public void setNomeVacina(String nomeVaciana) {
		this.nomeVacina = nomeVaciana;
	}

	public String getDescricaoVacina() {
		return descricaoVacina;
	}

	public void setDescricaoVacina(String descricaoVacina) {
		this.descricaoVacina = descricaoVacina;
	}

	public String getFabricante() {
		return fabricante;
	}

	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}

	public String getDosagem() {
		return dosagem;
	}

	public void setDosagem(String dosagem) {
		this.dosagem = dosagem;
	}

	public Long getQuantidadeDoses() {
		return quantidadeDoses;
	}

	public void setQuantidadeDoses(Long quantidadeDoses) {
		this.quantidadeDoses = quantidadeDoses;
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
		return "Vacina [idVacina=" + idVacina + ", nomeVacina=" + nomeVacina + ", descricaoVacina=" + descricaoVacina
				+ ", fabricante=" + fabricante + ", dosagem=" + dosagem + ", quantidadeDoses=" + quantidadeDoses
				+ ", usuario=" + usuario + ", cartaoDeVacina=" + cartaoDeVacina + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idVacina == null) ? 0 : idVacina.hashCode());
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
		Vacina other = (Vacina) obj;
		if (idVacina == null) {
			if (other.idVacina != null)
				return false;
		} else if (!idVacina.equals(other.idVacina))
			return false;
		return true;
	}

}
