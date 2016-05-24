package cursojsf.jornadas.jornada4;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.service.ClinicService;

@FacesConverter("petConverter")
public class PetConverter implements Converter {
	
	@Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0) {
            return null;
        }
        ClinicService clinicService = (ClinicService) facesContext.getApplication().getELResolver()
        		.getValue(facesContext.getELContext(), null, "clinicServiceImpl");
        return clinicService.findPetById(getKey(value));
    }

    java.lang.Integer getKey(String value) {
        java.lang.Integer key;
        key = Integer.valueOf(value);
        return key;
    }

    String getStringKey(java.lang.Integer value) {
        StringBuilder sb = new StringBuilder();
        sb.append(value);
        return sb.toString();
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object instanceof Pet) {
        	Pet o = (Pet) object;
        	return getStringKey(o.getId());
        }
        return null;
    }

}
