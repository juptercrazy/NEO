package converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import entity.ModeloAula;
import entity.TipoServico;
import negocio.ModeloAulaNegocio;

@FacesConverter("modeloAulaConverter") 
public class ModeloAulaConverter implements Converter {
		 public Object getAsObject(FacesContext context, UIComponent component, String value) throws ConverterException {
			 if (value!= null &&  value.isEmpty() )
		        	 return null;

			 ModeloAula modelo = null;
		         ModeloAulaNegocio negocio = new ModeloAulaNegocio();
		         Long id = Long.valueOf(value);
		         try {
					
		        	 modelo = negocio.obterServicoPorID(id); 
				} catch (Exception e) {
					throw new ConverterException("Não foi possível encontrar o Modelo de Aula com ID " + value + ", " + e.getMessage());
				}
		         return modelo;
		    }

		    public String getAsString(FacesContext context, UIComponent component, Object value) throws ConverterException {
		    	if (value == null )
		    		return "";

		    	ModeloAula tipo = (ModeloAula)value;
		    	return tipo.getId().toString();
		    }
}
