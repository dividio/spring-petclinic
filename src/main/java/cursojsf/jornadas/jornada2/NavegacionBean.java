package cursojsf.jornadas.jornada2;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

@ManagedBean
@SessionScoped
public class NavegacionBean {

	private String titulo = "Menú de navegación";

	private String destino;

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public String navegar() {
		return "navegacion";
	}

	public String invalidarYNavegar() {
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
		        .getRequest();
		request.getSession().invalidate();
		return "navegacion";
	}
}
