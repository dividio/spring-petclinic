package cursojsf.jornadas.jornada3;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "faceletsBean")
@SessionScoped
public class FaceletsBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private List<String> repeticiones = new ArrayList<>();

	public FaceletsBean() {
		repeticiones.add("Repetición 1");
		repeticiones.add("Repetición 2");
		repeticiones.add("Repetición 3");
		repeticiones.add("Repetición 4");
	}

	public List<String> getRepeticiones() {
		return repeticiones;
	}

	public void setRepeticiones(List<String> repeticiones) {
		this.repeticiones = repeticiones;
	}

}
