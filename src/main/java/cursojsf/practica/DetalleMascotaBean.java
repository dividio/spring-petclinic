package cursojsf.practica;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.model.SelectItem;

import org.joda.time.LocalDate;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.PetType;
import org.springframework.samples.petclinic.service.ClinicService;

@ManagedBean(name = "detalleMascotaBean")
public class DetalleMascotaBean {

	private Pet mascota;

	private List<PetType> tiposMascota;

	private Boolean modoConsulta;

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

	@ManagedProperty(value = "#{clinicServiceImpl}")
	private ClinicService clinicService;

	public DetalleMascotaBean() {
		this.mascota = getPetDummy();
	}
	
	public  List<PetType> getPetTypes() {
		tiposMascota = new ArrayList<>(clinicService.findPetTypes());
		return tiposMascota;
	}

	private Pet getPetDummy() {
		
		Pet resultado = new Pet();
		Owner owner = getOwnerDummy(0);

		resultado.setName("Mascota ");
		resultado.setBirthDate(LocalDate.fromCalendarFields(Calendar.getInstance()));
		resultado.setId(1);
		owner.addPet(resultado);
		
		return resultado;
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
