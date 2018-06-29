package converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import dao.TipoEmpresaDaoHibernate;
import entity.TipoEmpresa;

@FacesConverter(forClass=TipoEmpresa.class) 
public class TipoEmpresaConverter implements Converter {
	 public Object getAsObject(FacesContext context, UIComponent component, String value) throws ConverterException {
		 System.out.println("testeststs");
		 if (value!= null &&  value.isEmpty() )
	        	 return null;

		 TipoEmpresa tipoEmpresa = null;
	         TipoEmpresaDaoHibernate tipoEmpresaDao = new TipoEmpresaDaoHibernate();
	         Long id = Long.valueOf(value);
	         try {
				
	        	 tipoEmpresa = tipoEmpresaDao.find(id); 
			} catch (Exception e) {
				// TODO: handle exception
			}
	         return tipoEmpresa;
		 
//		 return (Object)value;
	    }

	    public String getAsString(FacesContext context, UIComponent component, Object value) throws ConverterException {
	    	if (value == null )
	    		return "";

	    	TipoEmpresa tipo = (TipoEmpresa)value;
//	         return String.valueOf(value);
	    	return tipo.getId().toString();
	    }
}