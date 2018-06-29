package converter;

import java.time.format.DateTimeParseException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

@FacesConverter("localTimeFacesConverter")
public class LocalTimeConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) throws ConverterException {
		if (value != null && value.isEmpty())
			return null;

		LocalTime localTime = null;
		try {
			localTime = LocalTime.parse(value.trim(),
					DateTimeFormat.forPattern("HH:mm")
					);

		} catch (DateTimeParseException e) {

			throw new ConverterException("O formato da hora deve ser 12:00.");
		}
		return localTime;

	}

	public String getAsString(FacesContext context, UIComponent component, Object value) throws ConverterException {
		if (value == null)
			return "";
		LocalTime time = (LocalTime) value;
		DateTimeFormatter dtf = DateTimeFormat.forPattern("HH:mm");
		return time.toString(dtf);
	}
}