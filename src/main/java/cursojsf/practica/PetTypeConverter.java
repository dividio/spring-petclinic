package cursojsf.practica;

import java.util.Collection;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.springframework.samples.petclinic.model.PetType;
import org.springframework.samples.petclinic.service.ClinicService;

@FacesConverter("petTypeConverter")
public class PetTypeConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent arg1, String arg2) {

		if (arg2 == null) {
			return null;
		}

		try {
			Integer petTypeId = Integer.valueOf(arg2);
			
			ClinicService clinicService = (ClinicService) facesContext.getApplication().getELResolver()
	        		.getValue(facesContext.getELContext(), null, "clinicServiceImpl");
	        Collection<PetType> petTypes = clinicService.findPetTypes();
	        for (PetType petType : petTypes) {
	        	if (petType.getId().equals(petTypeId)) {
	        		return petType;
	        	}
	        }
			
		} catch (Exception e) {
			return null;
		}

		// Se devuelve vacio
		return null;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		if (arg2 == null) {
			return null;
		}

		if (arg2 instanceof PetType) {
			return ((PetType) arg2).getId().toString();
		}

		return arg2.toString();
	}

}
