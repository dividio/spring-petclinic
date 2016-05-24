package cursojsf.practica;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("telephoneValidator")
public class TelephoneValidator implements Validator {

	@Override
	public void validate(FacesContext arg0, UIComponent arg1, Object arg2) throws ValidatorException {
		// Se comprueba si está informado
		if (arg2 == null) {
			return;
		}

		if (arg2 instanceof String) {
			boolean valid = ((String) arg2).length() == 10;
			if (!valid) {
				FacesMessage msg = new FacesMessage("El número de teléfono debe estar compuesto por 10 carácteres.");
				throw new ValidatorException(msg);
			}
		} else {

			FacesMessage msg = new FacesMessage("No es posible validar un teléfono que no es una cadena de texto");
			throw new ValidatorException(msg);
		}

	}

}
