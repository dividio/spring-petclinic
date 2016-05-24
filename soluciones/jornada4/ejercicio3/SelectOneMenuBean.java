package cursojsf.jornadas.jornada4;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;

import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.service.ClinicService;

@ManagedBean(name = "selectOneMenuBean")
@RequestScoped
public class SelectOneMenuBean implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 6492426175462095648L;
	    
    @ManagedProperty(value = "#{clinicServiceImpl}")
    private ClinicService clinicService;
    
    private Owner propietario;
    
    private Pet mascota;
    
    public ClinicService getClinicService() {
		return clinicService;
	}

	public void setClinicService(ClinicService clinicService) {
		this.clinicService = clinicService;
	}
    
	public Owner getPropietario() {
		return propietario;
	}

	public void setPropietario(Owner propietario) {
		this.propietario = propietario;
	}

	public Pet getMascota() {
		return mascota;
	}

	public void setMascota(Pet mascota) {
		this.mascota = mascota;
	}

	public SelectOneMenuBean() {
    }
	
	public List<SelectItem> getSelectItemsPropietarios() {
		List<SelectItem> lista = new ArrayList<SelectItem>();
		for (Owner owner : clinicService.findOwnerByLastName("%")) {
			lista.add(new SelectItem(owner, owner.getFirstName() + " " + owner.getLastName()));
		}
        return lista;
    }
	
	public List<SelectItem> getSelectItemsMascotas() {
		List<SelectItem> lista = new ArrayList<SelectItem>();
		if (propietario != null && propietario.getPets() != null) {
			for (Pet pet : propietario.getPets()) {
				lista.add(new SelectItem(pet, pet.getName()));
			}
		}
        return lista;
    }
}
