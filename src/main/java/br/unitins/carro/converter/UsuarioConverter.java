package br.unitins.carro.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.unitins.carro.DAO.UsuarioDAO;
import br.unitins.model.Usuario;
@FacesConverter(forClass = Usuario.class)
public class UsuarioConverter implements Converter<Usuario>  {

	@Override
	public Usuario getAsObject(FacesContext context, UIComponent component, String value) {
		UsuarioDAO dao = new UsuarioDAO();
		
		return dao.bucarPorId(Integer.parseInt(value));
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Usuario value) {
		return value.getId().toString();
	}

}
