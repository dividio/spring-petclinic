package cursojsf.jornadas.jornada1;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "componentesBean")
@SessionScoped
public class ComponentesBean {

	private List<String> tabla = new ArrayList<>();

	public List<String> getTabla() {
		return tabla;
	}

	public void setTabla(List<String> tabla) {
		this.tabla = tabla;
	}
	
	public ComponentesBean() {
		tabla.add("Elemento 1");
		tabla.add("Elemento 2");
		tabla.add("Elemento 3");
		tabla.add("Elemento 4");
		tabla.add("Elemento 5");
		tabla.add("Elemento 6");
	}
			
			
}
