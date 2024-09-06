package Model;

public class Proceso {
	
    private Estados estado;
    private String id;
    private int timeArribo;
    private int cantRafagasParaTerminar;
    private int duracionDeRafaga;
    private int duracionDeBloqueado;
    private int prioridad;
    
	private double timeRetorno = 0;
    private double timeRetornoNormalizado = 0;
    private double timeEnListo = 0;
    
    private int rafagaActual = 0;
    private int timePreparativosNuevoAListo = 0;
    private int cantidadDeRafagasRestantesParaTerminar;
    private int tiemporestanteParaFinalizar = 0;
    
    private int timeFaltanteParaTerminarRafaga = 0;
    private int timeEnBloqueado = 0;
    private int cantidadDeRafagasActuales = 0;
    

    public Proceso(String id, int timeArribo , int cantRafagasParaTerminar, int duracionDeRafaga, int duracionDeBloqueado, int prioridad) {
		this.id = id;
		this.timeArribo = timeArribo;
		this.cantRafagasParaTerminar = cantRafagasParaTerminar;
		this.duracionDeRafaga = duracionDeRafaga;
		this.duracionDeBloqueado = duracionDeBloqueado;
		this.prioridad = prioridad;
		this.estado = new Nuevo(this);
		this.cantidadDeRafagasRestantesParaTerminar = cantRafagasParaTerminar;//cuando es cero, puede ir a terminado
	}
	
	public void cambiarEstado(Estados estado) {
		this.estado=estado;
	}

	public  void NuevoAListo(){this.estado.NuevoAListo();}
	public  void ListoAEjecutando(){this.estado.ListoAEjecutando();}
	public  void EjecutandoAListo(){this.estado.EjecutandoAListo();}
	public  void EjecutandoABloqueado(){this.estado.EjecutandoABloqueado();}
	public  void EjecutandoATerminado(){this.estado.EjecutandoATerminado();}
	public  void BloqueadoAListo(){this.estado.BloqueadoAListo();}

	@Override
	public String toString() {
	    return " {\n" +
	           "  id=" + id +
	           "  prioridad=" + prioridad+
	           "  timeArribo=" + timeArribo +
	           "  timePreparativosNuevoAListo=" + timePreparativosNuevoAListo + "\n" +
	           "  timeEnListo=" + timeEnListo + 
	           "  cantidadDeRafagasRestantesParaTerminar=" + cantidadDeRafagasRestantesParaTerminar + 
	           "  tiemporestanteParaFinalizar=" + tiemporestanteParaFinalizar + 
	           "  timeFaltanteParaTerminarRafaga=" + timeFaltanteParaTerminarRafaga + "\n" +
	           "  timeEnBloqueado=" + timeEnBloqueado +
	           "  cantidadDeRafagasActuales=" + cantidadDeRafagasActuales +
	           "}";
	}


	
	public int getTimePreparativosNuevoAListo() {
		return timePreparativosNuevoAListo;
	}

	public void setTimePreparativosNuevoAListo(int timePreparativosNuevoAListo) {
		this.timePreparativosNuevoAListo = timePreparativosNuevoAListo;
	}

	public int getPrioridad() {
		return prioridad;
	}

	public void setPrioridad(int prioridad) {
		this.prioridad = prioridad;
	}

	public double getTimeRetorno() {
		return timeRetorno;
	}

	public void setTimeRetorno(double timeRetorno) {
		this.timeRetorno = timeRetorno;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getTimeRetornoNormalizado() {
		return timeRetornoNormalizado;
	}

	public void setTimeRetornoNormalizado(double timeRetornoNormalizado) {
		this.timeRetornoNormalizado = timeRetornoNormalizado;
	}

	public double getTimeEnListo() {
		return timeEnListo;
	}

	public void setTimeEnListo(double timeEnListo) {
		this.timeEnListo = timeEnListo;
	}

    public int getTimeArribo() {
		return timeArribo;
	}

	public void setTimeArribo(int timeArribo) {
		this.timeArribo = timeArribo;
	}

	public int getCantRafagasParaTerminar() {
		return cantRafagasParaTerminar;
	}

	public void setCantRafagasParaTerminar(int cantRafagasParaTerminar) {
		this.cantRafagasParaTerminar = cantRafagasParaTerminar;
	}

	public int getDuracionDeRafaga() {
		return duracionDeRafaga;
	}

	public void setDuracionDeRafaga(int duracionDeRafaga) {
		this.duracionDeRafaga = duracionDeRafaga;
	}

	public int getDuracionDeBloqueado() {
		return duracionDeBloqueado;
	}

	public void setDuracionDeBloqueado(int duracionDeBloqueado) {
		this.duracionDeBloqueado = duracionDeBloqueado;
	}

	public int getTimeFaltanteParaTerminarRafaga() {
		return timeFaltanteParaTerminarRafaga;
	}

	public void setTimeFaltanteParaTerminarRafaga(int timeFaltanteParaTerminarRafaga) {
		this.timeFaltanteParaTerminarRafaga = timeFaltanteParaTerminarRafaga;
	}

	public int getTimeEnBloqueado() {
		return timeEnBloqueado;
	}

	public void setTimeEnBloqueado(int timeEnBloqueado) {
		this.timeEnBloqueado = timeEnBloqueado;
	}

	public int getCantidadDeRafagasActuales() {
		return cantidadDeRafagasActuales;
	}

	public void setCantidadDeRafagasActuales(int cantidadDeRafagasActuales) {
		this.cantidadDeRafagasActuales = cantidadDeRafagasActuales;
	}

	public int getCantidadDeRafagasRestantesParaTerminar() {
		return cantidadDeRafagasRestantesParaTerminar;
	}

	public void setCantidadDeRafagasRestantesParaTerminar(int cantidadDeRafagasRestantesParaTerminar) {
		this.cantidadDeRafagasRestantesParaTerminar = cantidadDeRafagasRestantesParaTerminar;
	}

	public int getRafagaActual() {
		return rafagaActual;
	}

	public void setRafagaActual(int rafagaActual) {
		this.rafagaActual = rafagaActual;
	}

	public int getTiemporestanteParaFinalizar() {
		return tiemporestanteParaFinalizar;
	}

	public void setTiemporestanteParaFinalizar(int tiemporestanteParaFinalizar) {
		this.tiemporestanteParaFinalizar = tiemporestanteParaFinalizar;
	}    
    
	
}