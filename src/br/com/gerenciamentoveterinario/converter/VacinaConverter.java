package br.com.gerenciamentoveterinario.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.gerenciamentoveterinario.dao.VacinaDAO;
import br.com.gerenciamentoveterinario.domain.Vacina;

@FacesConverter("vacinaConverter")
public class VacinaConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent componente, String valor) {
		try {
			
			Long codigo = Long.parseLong(valor);
			
			VacinaDAO vacinaDAO = new VacinaDAO();
			Vacina vacina = vacinaDAO.buscarPorId(codigo);
			
			return vacina;
			
		} catch (RuntimeException ex) {
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent componente, Object objeto) {
		
		try {
			
			Vacina vacina = (Vacina) objeto;
			Long codigo = vacina.getIdVacina();
			return codigo.toString();
			
		} catch (RuntimeException ex) {
			return null;
		}
	}

}
