package cursojsf.practica;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.service.ClinicService;

@ManagedBean(name = "detallePropietarioBean")
public class DetallePropietarioBean {
	
    @ManagedProperty(value = "#{clinicServiceImpl}")
    private ClinicService clinicService;
    
    private Owner propietario;
    
    private List<Pet> listaMascotas;
    
    private Boolean modoConsulta = Boolean.FALSE;
    
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

	public List<Pet> getListaMascotas() {
		return listaMascotas;
	}

	public void setListaMascotas(List<Pet> listaMascotas) {
		this.listaMascotas = listaMascotas;
	}

	public Boolean getModoConsulta() {
		return modoConsulta;
	}

	public void setModoConsulta(Boolean modoConsulta) {
		this.modoConsulta = modoConsulta;
	}

	public DetallePropietarioBean() {
		this.propietario = getOwnerDummy(1);
	}
	
	private Owner getOwnerDummy(int index) {
		Owner resultado = new Owner();

		resultado.setAddress("Dirección " + index);
		resultado.setCity("Ciudad " + index);
		resultado.setFirstName("Nombre " + index);
		resultado.setId(index);
		resultado.setLastName("Apellido " + index);
		resultado.setTelephone("+" + index + " 999999999");
		
		return resultado;
	}
}
