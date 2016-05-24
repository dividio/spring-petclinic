package cursojsf.jornadas.jornada1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "componentesBean")
@SessionScoped
public class ComponentesBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<String> tabla = new ArrayList<>();

	private HashMap<String, Object> entradas = new HashMap<String, Object>();

	public List<String> getTabla() {
		return tabla;
	}

	public void setTabla(List<String> tabla) {
		this.tabla = tabla;
	}

	public HashMap<String, Object> getEntradas() {
		return entradas;
	}

	public void setEntradas(HashMap<String, Object> entradas) {
		this.entradas = entradas;
	}

	public ComponentesBean() {
		tabla.add("Elemento 1");
		tabla.add("Elemento 2");
		tabla.add("Elemento 3");
		tabla.add("Elemento 4");
		tabla.add("Elemento 5");
		tabla.add("Elemento 6");
	}

	public String accion() {
		String resultado = null;
		System.out.println("{");
		for (Entry<String, Object> entry : entradas.entrySet()) {
			System.out.println(entry.getKey() + "," + printValue(entry.getValue()) + "("
					+ (entry.getValue() != null ? entry.getValue().getClass().getName() : "") + ")");
		}
		System.out.println("}");
		return resultado;
	}
	
	private String printValue(Object object) {
		if (object == null) {
			return "null";
		}
		
		if (object instanceof String[]) {
			
			if (((String[]) object).length == 0) {
				return "[]";
			}
			
			String resultado = "[";
			for (String o : (String[]) object) {
				resultado += o + ",";
			}
			resultado +="]";
			return resultado;
		}
		
		return object.toString();
	}

}
