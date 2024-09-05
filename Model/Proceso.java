package Model;

public class Proceso {
	
    private Estados estado;
    private int id;
    private int cantRafagasParaTerminar;
    private int duracionDeRafaga;
    private int duracionDeBloqueado;
    
    private int prioridad;
    
    private double timeRetorno = 0;
    private double timeRetornoNormalizado = 0;
    private double timeEnListo = 0;
    
    public Proceso(int id, int cantRafagasParaTerminar, int duracionDeRafaga, int duracionDeBloqueado, int prioridad) {
		this.id = id;
		this.cantRafagasParaTerminar = cantRafagasParaTerminar;
		this.duracionDeRafaga = duracionDeRafaga;
		this.duracionDeBloqueado = duracionDeBloqueado;
		this.prioridad = prioridad;
		this.estado = new Nuevo(this);
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

    
    
}