package cursojsf.jornadas.jornada3;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("emailValidator")
public class EmailValidator implements Validator {

	@Override
	public void validate(FacesContext arg0, UIComponent arg1, Object arg2) throws ValidatorException {
		// Se comprueba si está informado
		if (arg2 == null) {
			return;
		}

		if (arg2 instanceof String) {
			boolean valid = org.apache.commons.validator.routines.EmailValidator.getInstance().isValid(arg2.toString());
			if (!valid) {
				FacesMessage msg = new FacesMessage("El correo electrónico no tiene un formato correcto");
				throw new ValidatorException(msg);
			}
		} else {
			
			FacesMessage msg = new FacesMessage("No es posible validar un correo que no es una cadena de texto");
			throw new ValidatorException(msg);
		}

		
	}

}
