package cursojsf.jornadas.jornada3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "facetBean")
@SessionScoped
public class FacetBean {

	private List<String> tabla = new ArrayList<>();


	public List<String> getTabla() {
		return tabla;
	}

	public void setTabla(List<String> tabla) {
		this.tabla = tabla;
	}

	public FacetBean() {
		tabla.add("Elemento 1");
		tabla.add("Elemento 2");
		tabla.add("Elemento 3");
		tabla.add("Elemento 4");
		tabla.add("Elemento 5");
		tabla.add("Elemento 6");
	}

}
