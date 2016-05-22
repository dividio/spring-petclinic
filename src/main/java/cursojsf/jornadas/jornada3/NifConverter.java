package cursojsf.jornadas.jornada3;

import java.text.DecimalFormat;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("nifConverter")
public class NifConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		if (arg2 != null) {
			DecimalFormat df = new DecimalFormat("00000009");
			return df.format(Integer.valueOf(arg2));
		}
		// TODO Auto-generated method stub
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
