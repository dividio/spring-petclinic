package cursojsf.practica;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.view.ViewScoped;

import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.service.ClinicService;

@ManagedBean(name = "detallePropietarioBean")
@ViewScoped
public class DetallePropietarioBean implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 7369408853829327176L;

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
		System.out.println("Constructor");
	}
	
	public String nuevo() {
		this.propietario = new Owner();
		return "nuevo";
	}
	
	public String editar() {
		this.propietario = getOwnerDummy(1);
		return "editar";
	}
	
	public String consultar() {
		this.propietario = getOwnerDummy(1);
		return "consultar";
	}
	
	public String guardar() {
		
		// Guardamos el propietario
		clinicService.saveOwner(propietario);
		
		return null;
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
