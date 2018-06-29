package converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

@FacesConverter("dateTimeFacesConverter")
public class DateTimeConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) throws ConverterException {
		if (value != null && value.isEmpty())
			return null;

		DateTime datelTime = DateTime.parse(value.trim(),
					DateTimeFormat.forPattern("dd/MM/yyyy")
					);

		return datelTime;

	}

	public String getAsString(FacesContext context, UIComponent component, Object value) throws ConverterException {
		if (value == null)
			return "";
		DateTime time = (DateTime) value;
		DateTimeFormatter dtf = DateTimeFormat.forPattern("dd/mm/yyyy");
		return time.toString(dtf);
	}
}