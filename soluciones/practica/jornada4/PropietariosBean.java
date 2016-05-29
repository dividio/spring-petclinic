package cursojsf.practica;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;

import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.service.ClinicService;

@ManagedBean(name = "propietariosBean")
@ViewScoped
public class PropietariosBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Owner> propietarios;

	private String filtro;
	
	@ManagedProperty(value = "#{clinicServiceImpl}")
    private ClinicService clinicService;

	public List<Owner> getPropietarios() {
		return propietarios;
	}

	public void setPropietarios(List<Owner> propietarios) {
		this.propietarios = propietarios;
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}
	
	public ClinicService getClinicService() {
		return clinicService;
	}

	public void setClinicService(ClinicService clinicService) {
		this.clinicService = clinicService;
	}

	public PropietariosBean() {
	}
	
	public String filtrar() {
		this.propietarios = new ArrayList<>(clinicService.findOwnerByLastName(this.filtro));
		return null;
	}
	
	public void buscarAjax(AjaxBehaviorEvent event) {
		this.filtrar();
	}
	

}
