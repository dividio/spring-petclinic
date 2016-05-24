package cursojsf.jornadas.jornada3;

import java.io.Serializable;
import java.util.HashMap;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "includeBean")
@SessionScoped
public class IncludeBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private HashMap<String, Object> entradas= new HashMap<String, Object>();

	public IncludeBean() {

	
	}

	public HashMap<String, Object> getEntradas() {
		return entradas;
	}

	public void setEntradas(HashMap<String, Object> entradas) {
		this.entradas = entradas;
	}
	
	public String accion() {
		String resultado = null;
		
		System.out.println("Llamamos a la acción del include: " + entradas);
		
		return resultado;
	}


}
