package br.com.gerenciamentoveterinario.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import br.com.gerenciamentoveterinario.dao.UsuarioDAO;
import br.com.gerenciamentoveterinario.dao.VacinaDAO;
import br.com.gerenciamentoveterinario.domain.Usuario;
import br.com.gerenciamentoveterinario.domain.Vacina;
import br.com.gerenciamentoveterinario.util.FacesUtil;

@ManagedBean
@ViewScoped
public class VacinaBean {

	private Vacina vacina;
	private List<Vacina> vacinas;
	private List<Vacina> vacinasFilter;

	@ManagedProperty(value = "#{autenticacaoBean}")
	private AutenticacaoBean autenticacaoBean;

	private String acao = "Novo";
	private Long codigo;

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

			vacina.setUsuario(usuario);

			VacinaDAO dao = new VacinaDAO();
			dao.salvar(vacina);

			vacina = new Vacina();

			FacesUtil.adicionarMsgInfo("Vacina CADASTRADO com sucesso!");

		} catch (RuntimeException ex) {

			ex.printStackTrace();
			FacesUtil.adicionarMsgErro("Erro ao tentar CADASTRAR uma nova Vacina: " + ex.getMessage());

		}
	}

	public void pesquisar() {

		try {

			VacinaDAO dao = new VacinaDAO();
			vacinas = dao.listar();

		} catch (RuntimeException ex) {

			ex.printStackTrace();
			FacesUtil.adicionarMsgErro("Erro ao tentar LISTAR as Vacinas: " + ex.getMessage());

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
			FacesUtil.adicionarMsgErro("Erro ao tentar CARREGAR os dados das Vacinas: " + ex.getMessage());

		}
	}

	public String excluir() {

		try {

			VacinaDAO dao = new VacinaDAO();
			dao.excluir(vacina);

			FacesUtil.adicionarMsgInfo("Vacina EXCLUIDA com sucesso!");

			return "/pages/vacinaPesquisa.xhtml?faces-redirect=true";

		} catch (RuntimeException ex) {

			ex.printStackTrace();
			FacesUtil.adicionarMsgErro("Erro ao tentar EXCLUIR Vacina: " + ex.getMessage());

			return null;

		}
	}

	public String editar() {

		try {

			VacinaDAO dao = new VacinaDAO();
			dao.editar(vacina);

			FacesUtil.adicionarMsgInfo("Vacina EDITADA com sucesso!");

			return "/pages/vacinaPesquisa.xhtml?faces-redirect=true";

		} catch (RuntimeException ex) {

			ex.printStackTrace();
			FacesUtil.adicionarMsgErro("Erro ao tentar EDITAR uma Vacina: " + ex.getMessage());

			return null;

		}
	}

}
