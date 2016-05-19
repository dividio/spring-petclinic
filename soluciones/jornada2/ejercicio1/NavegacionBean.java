package cursojsf.jornadas.jornada2;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class NavegacionBean {

	private String titulo = "Menú de navegación";

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

}
