package converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import entity.TipoServico;
import negocio.TipoServicoNegocio;

@FacesConverter("tipoServicoConverter")
public class TipoServicoConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) throws ConverterException {
		if (value != null && value.isEmpty())
			return null;

		TipoServico tipoServico = null;
		TipoServicoNegocio negocio = new TipoServicoNegocio();
		Long id = Long.valueOf(value);
		try {

			tipoServico = negocio.obterServicoPorID(id);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return tipoServico;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) throws ConverterException {
		if (value == null)
			return "";

		TipoServico tipo = (TipoServico) value;
		return tipo.getId().toString();
	}
}
