package cursojsf.jornadas.jornada4;

import java.io.Serializable;
import java.util.Collection;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.event.AjaxBehaviorEvent;

import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.service.ClinicService;

@ManagedBean(name = "propietariosBean")
@RequestScoped
public class PropietariosBean implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 6492426175462095648L;
	    
    @ManagedProperty(value = "#{clinicServiceImpl}")
    private ClinicService clinicService;

    private Collection<Owner> listaPropietarios;;
    
    private String filtro;
    
    public ClinicService getClinicService() {
		return clinicService;
	}

	public void setClinicService(ClinicService clinicService) {
		this.clinicService = clinicService;
	}

	public Collection<Owner> getListaPropietarios() {
		return listaPropietarios;
	}

	public void setListaPropietarios(Collection<Owner> listaPropietarios) {
		this.listaPropietarios = listaPropietarios;
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

	public PropietariosBean() {
    }
	
	public String buscar() {
		buscar(filtro);
		return null;
	}
	
	public void buscarAjax(AjaxBehaviorEvent event) {
		buscar(filtro);
	}

	private void buscar(String filtro) {
		listaPropietarios = clinicService.findOwnerByLastName(filtro);
	}
}
