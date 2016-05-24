package cursojsf.practica;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.Pet;

@ManagedBean(name = "propietariosBean")
public class PropietariosBean {

	private List<Owner> propietarios;

	private String filtro;

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

	public PropietariosBean() {
		this.propietarios = this.getDummy();
	}

	private List<Owner> getDummy() {
		List<Owner> resultado = new ArrayList<>();

		for (int i = 0; i < 10; i++) {
			resultado.add(getOwnerDummy(i));
		}

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
		
		// Se añade una mascota
		Pet pet = new Pet();
		pet.setName("Mascota " + index);
		resultado.addPet(pet);

		return resultado;
	}

}
