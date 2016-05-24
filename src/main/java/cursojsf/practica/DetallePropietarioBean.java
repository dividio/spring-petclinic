package cursojsf.practica;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.service.ClinicService;

@ManagedBean(name = "detallePropietarioBean")
@RequestScoped
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
		this.propietario = new Owner();
	}
	
	@PostConstruct
	public void postConstruct() {
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		String idPropietario = request.getParameter("mainForm:propietario");
		if (idPropietario == null) {
			this.propietario = new Owner();
		} else {
			this.propietario = clinicService.findOwnerById(Integer.valueOf(idPropietario));
		}
	}
	
	public String nuevo() {
		this.modoConsulta = Boolean.FALSE;
		return "nuevo";
	}
	
	public String editar(Integer idOwner) {
		if (idOwner != null) {
			this.propietario = clinicService.findOwnerById(idOwner);
		}
		this.modoConsulta = Boolean.FALSE;
		return "editar";
	}
	
	public String consultar(Integer idOwner) {
		if (idOwner != null) {
			this.propietario = clinicService.findOwnerById(idOwner);
		}
		this.modoConsulta = Boolean.TRUE;
		return "consultar";
	}
	
	public String guardar() {
		
		// Guardamos el propietario
		clinicService.saveOwner(propietario);
		
		return null;
	}
}
