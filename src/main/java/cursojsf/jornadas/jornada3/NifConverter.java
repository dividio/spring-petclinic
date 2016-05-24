package cursojsf.jornadas.jornada3;

import java.text.DecimalFormat;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter("nifConverter")
public class NifConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		if (arg2 != null) {
			DecimalFormat df = new DecimalFormat("00000000");
			try {
				if (arg2.length() > 1) {
					// Sacamos el último carácter
					String letra = arg2.substring(arg2.length() - 1, arg2.length());
					String numero = arg2.substring(0, arg2.length() - 1);
					return df.format(Integer.valueOf(numero)) + letra.toUpperCase();
				} else {
					throw new ConverterException("La cadena no tiene el formato correcto");
				}
			} catch (Exception e) {
				throw new ConverterException("La cadena no tiene el formato correcto");
			}
		}
		
		// Se devuelve vacio
		return null;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		if (arg2 == null) {
			return null;
		}
		return arg2.toString();
	}

}
