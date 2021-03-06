package cursojsf.practica;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.http.HttpServletRequest;

import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.PetType;
import org.springframework.samples.petclinic.service.ClinicService;

@ManagedBean(name = "detalleMascotaBean")
public class DetalleMascotaBean {

	private Pet mascota;

	private List<PetType> tiposMascota;

	private Boolean modoConsulta;

	private Boolean modoConsultaPropietario = Boolean.FALSE;

	private Integer idPropietario;

	private Boolean formularioModificado = Boolean.FALSE;

	public Pet getMascota() {
		return mascota;
	}

	public void setMascota(Pet mascota) {
		this.mascota = mascota;
	}

	public List<PetType> getTiposMascota() {
		return tiposMascota;
	}

	public void setTiposMascota(List<PetType> tiposMascota) {
		this.tiposMascota = tiposMascota;
	}

	public Boolean getModoConsulta() {
		return modoConsulta;
	}

	public void setModoConsulta(Boolean modoConsulta) {
		this.modoConsulta = modoConsulta;
	}

	public ClinicService getClinicService() {
		return clinicService;
	}

	public void setClinicService(ClinicService clinicService) {
		this.clinicService = clinicService;
	}

	public Boolean getModoConsultaPropietario() {
		return modoConsultaPropietario;
	}

	public void setModoConsultaPropietario(Boolean modoConsultaPropietario) {
		this.modoConsultaPropietario = modoConsultaPropietario;
	}

	public Integer getIdPropietario() {
		return idPropietario;
	}

	public void setIdPropietario(Integer idPropietario) {
		this.idPropietario = idPropietario;
	}

	public Boolean getFormularioModificado() {
		return formularioModificado;
	}

	public void setFormularioModificado(Boolean formularioModificado) {
		this.formularioModificado = formularioModificado;
	}

	@ManagedProperty(value = "#{clinicServiceImpl}")
	private ClinicService clinicService;

	public DetalleMascotaBean() {
		this.mascota = new Pet();
	}

	public List<PetType> getPetTypes() {
		tiposMascota = new ArrayList<>(clinicService.findPetTypes());
		return tiposMascota;
	}

	public String nuevo(Integer idPropietario) {
		this.mascota = new Pet();
		Owner owner = this.clinicService.findOwnerById(idPropietario);
		owner.addPet(mascota);
		this.modoConsulta = Boolean.FALSE;
		this.modoConsultaPropietario = Boolean.FALSE;
		this.idPropietario = idPropietario;
		return "nuevo";
	}

	public String editar(Integer idMascota, Boolean modoConsultaPropietario) {
		if (idMascota != null) {
			this.mascota = clinicService.findPetById(idMascota);
		}
		this.modoConsultaPropietario = modoConsultaPropietario;
		this.modoConsulta = Boolean.FALSE;
		this.idPropietario = this.mascota.getOwner().getId();
		return "editar";
	}

	public String consultar(Integer idMascota, Boolean modoConsultaPropietario) {
		if (idMascota != null) {
			this.mascota = clinicService.findPetById(idMascota);
		}
		this.modoConsultaPropietario = modoConsultaPropietario;
		this.modoConsulta = Boolean.TRUE;
		this.idPropietario = this.mascota.getOwner().getId();
		return "consultar";
	}

	public String guardar() {

		// Por defecto tomcat manda un 0 en un entero null
		// se pude habilitar la opción
		// -Dorg.apache.el.parser.COERCE_TO_ZERO=false
		// para evitar esto
		if (mascota.getId() != null && mascota.getId() == 0) {
			mascota.setId(null);
		}

		// Se busca el propietario
		Owner owner = clinicService.findOwnerById(idPropietario);
		owner.addPet(mascota);

		// Guardamos el propietario
		clinicService.savePet(this.mascota);
		this.formularioModificado = Boolean.FALSE;

		return null;
	}

	public String volverEditar() {
		FacesContext context = FacesContext.getCurrentInstance();
		DetallePropietarioBean detallePropietarioBean = (DetallePropietarioBean) context.getELContext().getELResolver()
		        .getValue(context.getELContext(), null, "detallePropietarioBean");
		HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
		this.idPropietario = Integer.valueOf(request.getParameter("idPropietario"));
		return detallePropietarioBean.editar(this.idPropietario);
	}

	public String volverConsultar() {
		FacesContext context = FacesContext.getCurrentInstance();
		DetallePropietarioBean detallePropietarioBean = (DetallePropietarioBean) context.getELContext().getELResolver()
		        .getValue(context.getELContext(), null, "detallePropietarioBean");
		return detallePropietarioBean.consultar(this.idPropietario);
	}

	public void valueChangeListener(ValueChangeEvent event) {

		// Actualizamos el modelo de forma manual
		idPropietario = Integer.valueOf(event.getNewValue().toString());

		// Previene que el setter sea llamado en futuras phases
		((UIInput) event.getComponent()).setLocalValueSet(false);
	}

	public void valueChangeListenerModoConsulta(ValueChangeEvent event) {

		// Actualizamos el modelo de forma manual
		modoConsultaPropietario = Boolean.valueOf(event.getNewValue().toString());

		// Previene que el setter sea llamado en futuras phases
		((UIInput) event.getComponent()).setLocalValueSet(false);
	}

}
