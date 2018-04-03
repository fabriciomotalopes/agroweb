package br.com.gerenciamentoveterinario.domain;

import java.util.Date;

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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "tbl_cartao_de_vacina")
@NamedQueries({
	@NamedQuery(name = "CartaoDeVacina.listar", query = "SELECT cartaoDeVacina FROM CartaoDeVacina cartaoDeVacina"),
	@NamedQuery(name = "CartaoDeVacina.buscarPorId", query = "SELECT cartaoDeVacina FROM CartaoDeVacina cartaoDeVacina WHERE cartaoDeVacina.idCartaoDeVacina = :idCartaoDeVacina"),
	@NamedQuery(name = "CartaoDeVacina.buscarAnimal", query = "SELECT cartaoDeVacina FROM CartaoDeVacina cartaoDeVacina WHERE cartaoDeVacina.animal = :animal")
})
public class CartaoDeVacina {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_cartao_de_vacina")
	private Long idCartaoDeVacina;
	
	@NotNull(message = "O campo data da vacinação é Obrigatório.")
	@Temporal(value = TemporalType.DATE)
	@Column(name = "data_vacina", nullable = false)
	private Date dataVacina;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="tbl_animal_id_animal",insertable=true, updatable=true)
    @Fetch(FetchMode.JOIN)
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
	private Animal animal;
	
	@NotNull(message = "O campo vacina é obrigatório.")
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="tbl_vacina_id_vacina",insertable=true, updatable=true)
    @Fetch(FetchMode.JOIN)
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
	private Vacina vacina;

	public Long getIdCartaoDeVacina() {
		return idCartaoDeVacina;
	}

	public void setIdCartaoDeVacina(Long idCartaoDeVacina) {
		this.idCartaoDeVacina = idCartaoDeVacina;
	}

	public Date getDataVacina() {
		return dataVacina;
	}

	public void setDataVacina(Date dataVacina) {
		this.dataVacina = dataVacina;
	}

	public Animal getAnimal() {
		return animal;
	}

	public void setAnimal(Animal animal) {
		this.animal = animal;
	}

	public Vacina getVacina() {
		return vacina;
	}

	public void setVacina(Vacina vacina) {
		this.vacina = vacina;
	}

	@Override
	public String toString() {
		return "CartaoDeVacina [idCartaoDeVacina=" + idCartaoDeVacina + ", dataVacina=" + dataVacina + ", animal="
				+ animal + ", vacina=" + vacina + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idCartaoDeVacina == null) ? 0 : idCartaoDeVacina.hashCode());
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
		CartaoDeVacina other = (CartaoDeVacina) obj;
		if (idCartaoDeVacina == null) {
			if (other.idCartaoDeVacina != null)
				return false;
		} else if (!idCartaoDeVacina.equals(other.idCartaoDeVacina))
			return false;
		return true;
	}
	
}
