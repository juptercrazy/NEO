package converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import entity.Usuario;
import negocio.UsuarioNegocio;

@FacesConverter("usuarioConverter")
public class UsuarioConverter implements Converter {
	public Object getAsObject(FacesContext context, UIComponent component, String value) throws ConverterException {
		if (value != null && value.isEmpty())
			return null;

		Usuario usuario = null;
		UsuarioNegocio negocio = new UsuarioNegocio();
		Long id = Long.valueOf(value);
		try {

			usuario = negocio.obterUsuarioPorID(id);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return usuario;
	}

	public String getAsString(FacesContext context, UIComponent component, Object value) throws ConverterException {
		if (value == null)
			return "";

		Usuario usuario = (Usuario) value;
		return usuario.getId().toString();
	}
}
