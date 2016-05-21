package cursojsf.jornadas.jornada3;

import java.util.HashMap;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "decorateBean")
@SessionScoped
public class DecorateBean {

	private HashMap<String, Object> entradas= new HashMap<String, Object>();

	public DecorateBean() {

	
	}

	public HashMap<String, Object> getEntradas() {
		return entradas;
	}

	public void setEntradas(HashMap<String, Object> entradas) {
		this.entradas = entradas;
	}
	
	public String accion() {
		String resultado = null;
		
		System.out.println("Llamamos a la acción del decorate: " + entradas);
		
		return resultado;
	}


}
