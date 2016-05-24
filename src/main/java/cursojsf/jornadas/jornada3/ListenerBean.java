package cursojsf.jornadas.jornada3;

import java.util.HashMap;
import java.util.Map.Entry;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

@ManagedBean(name = "listenerBean")
@SessionScoped
public class ListenerBean {
	private HashMap<String, Object> entradas = new HashMap<String, Object>();
	
	private String valor;

	public HashMap<String, Object> getEntradas() {
		return entradas;
	}

	public void setEntradas(HashMap<String, Object> entradas) {
		this.entradas = entradas;
	}
	
	
	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		System.out.println("Se estable el valor: [" + valor + "]");
		this.valor = valor;
	}

	public ListenerBean() {
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
	
	public void changeListener(ValueChangeEvent event) {
		System.out.println("Se ha cambiado el valor del componente ["
				+ event.getComponent().getAttributes().get("inputAdicional") + "]");
		
	}
	
	public void actionListener(ActionEvent event) {
		System.out.println(
				"Se ejecuta el action Listener [" + event.getComponent().getAttributes().get("botonAdicional") + "]");
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
