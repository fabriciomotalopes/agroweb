package br.com.gerenciamentoveterinario.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.br.CPF;

@Entity
@Table(name = "tbl_usuario")
@NamedQueries({
		@NamedQuery(name = "Usuario.listar", query = "SELECT usuario FROM Usuario usuario"),
		@NamedQuery(name = "Usuario.buscarPorId", query = "SELECT usuario FROM Usuario usuario WHERE usuario.idUsuario = :idUsuario"),
		@NamedQuery(name = "Usuario.autenticar", query = "SELECT usuario FROM Usuario usuario WHERE usuario.cpf = :cpf AND usuario.senha = :senha")
})
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_usuario")
	private Long idUsuario;
	
	@NotEmpty(message = "O campo nome é Obrigatório.")
	@Size(min = 1, max = 100, message = "Quanitidade de caracteres Inválido.")
	@Column(name = "nome_usuario", length = 100, nullable = false)
	private String nome;

	@NotEmpty(message = "O campo cpf é Obrigatório.")
	@CPF(message = "CPF Inválido")
	@Column(name = "cpf", length = 14, nullable = false)
	private String cpf;
	
	@NotEmpty(message = "O campo senha é Obrigatório.")
	@Size(min = 1, max = 32, message = "Quanitidade de caracteres Inválido.")
	@Column(name = "senha", length = 32, nullable = false)
	private String senha;
	
	@NotEmpty(message = "O campo e-mail é Obrigatório.")
	@Size(min = 1, max = 100, message = "Quanitidade de caracteres Inválido.")
	@Column(name = "email", length = 100, nullable = false)
	private String email;

	@NotEmpty(message = "O campo endereço é Obrigatório.")
	@Size(min = 1, max = 150, message = "Quanitidade de caracteres Inválido.")
	@Column(name = "endereco", length = 150, nullable = false)
	private String endereco;

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	@Override
	public String toString() {
		return "Usuario [idUsuario=" + idUsuario + ", nome=" + nome + ", cpf=" + cpf + ", senha=" + senha + ", email="
				+ email + ", endereco=" + endereco + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idUsuario == null) ? 0 : idUsuario.hashCode());
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
		Usuario other = (Usuario) obj;
		if (idUsuario == null) {
			if (other.idUsuario != null)
				return false;
		} else if (!idUsuario.equals(other.idUsuario))
			return false;
		return true;
	}

	

}
