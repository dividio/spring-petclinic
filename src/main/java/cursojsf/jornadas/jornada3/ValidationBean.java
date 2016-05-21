package cursojsf.jornadas.jornada3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "validationBean")
@SessionScoped
public class ValidationBean {

	private HashMap<String, Object> entradas = new HashMap<String, Object>();

	public HashMap<String, Object> getEntradas() {
		return entradas;
	}

	public void setEntradas(HashMap<String, Object> entradas) {
		this.entradas = entradas;
	}

	public ValidationBean() {
	}

	public String accion() {
		String resultado = null;
		System.out.println("{");
		for (Entry<String, Object> entry : entradas.entrySet()) {
			System.out.println(entry.getKey() + "," + printValue(entry.getValue()) + "("
					+ (entry.getValue() != null ? entry.getValue().getClass().toGenericString() : "") + ")");
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
