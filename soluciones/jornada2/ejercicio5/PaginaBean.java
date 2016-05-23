package cursojsf.jornadas.jornada2;

public class PaginaBean {

	private String nombrePagina;

	public String getNombrePagina() {
		return nombrePagina;
	}

	public void setNombrePagina(String nombrePagina) {
		this.nombrePagina = nombrePagina;
	}

	public String volver() {
		return "navegacion";
	}
}
