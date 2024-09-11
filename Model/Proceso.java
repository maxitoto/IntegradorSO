package Model;

import java.util.Collection;

public class Proceso {
	
	//limites
    private Estados estado;
    private String id;
    private int tiempoDeArribo;
    private int rafagasDeCpuParaTerminar;
    private int duracionDeCadaRafaga;
    private int duracionDePeriodoEnBloqueado;
    private int prioridad;
    
    //para comparar con los limites y hacer cosas
    private int contRafagasActuales=0;
    private int tiempoActualDeRafaga=0;
    private int tiempoEnEstadoBloqueado=0;
    
    //contabilidad durante y despues de simular
	private double timeRetorno = 0;
    private double timeRetornoNormalizado = 0;
    private int timeEnListo = 0;
    
    //tiempo de cambios de contexte desde que fue creado
    private int SumaDeTCPDesdeQueFuiCreado = 0;
    
    //bit x proceso para saber si me dieron los recursos, para no contar el estado de listo, por lo menos la primera vez va a estar en falso.
    private boolean tengoLosRecursos = false;

    public Proceso(String id, int tiempoDeArribo , int rafagasDeCpuParaTerminar, int duracionDeCadaRafaga, int duracionDePeriodoEnBloqueado, int prioridad) {
		this.id = id;
		this.tiempoDeArribo = tiempoDeArribo;
		this.rafagasDeCpuParaTerminar = rafagasDeCpuParaTerminar;
		this.duracionDeCadaRafaga = duracionDeCadaRafaga;
		this.duracionDePeriodoEnBloqueado = duracionDePeriodoEnBloqueado;
		this.prioridad = prioridad;
		this.estado = new Nuevo(this);
		
		this.SumaDeTCPDesdeQueFuiCreado = 0;
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
	    return 
	            "{"+ '\n'  + id + '\n' +
	            ", tiempoDeArribo=" + tiempoDeArribo + '\n' +
	            ", rafagasDeCpuParaTerminar=" + rafagasDeCpuParaTerminar + '\n' +
	            ", duracionDeCadaRafaga=" + duracionDeCadaRafaga + '\n' +
	            ", duracionDePeriodoEnBloqueado=" + duracionDePeriodoEnBloqueado + '\n' +
	            ", prioridad=" + prioridad + '\n' +
	            ", contRafagasActuales=" + contRafagasActuales + '\n' +
	            ", tiempoActualDeRafaga=" + tiempoActualDeRafaga + '\n' +
	            ", tiempoEnEstadoBloqueado=" + tiempoEnEstadoBloqueado + '\n' +
	            ", timeRetorno=" + timeRetorno + '\n' +
	            ", SumaDeTCPDesdeQueFuiCreado=" + SumaDeTCPDesdeQueFuiCreado + '\n' +
	            ", tengoLosRecursos=" + tengoLosRecursos + '\n' +
	            '}';
	}
	
	
	public int getTiempoDeArribo() {
		return tiempoDeArribo;
	}
	public void setTiempoDeArribo(int tiempoDeArribo) {
		this.tiempoDeArribo = tiempoDeArribo;
	}
	public int getRafagasDeCpuParaTerminar() {
		return rafagasDeCpuParaTerminar;
	}
	public void setRafagasDeCpuParaTerminar(int rafagasDeCpuParaTerminar) {
		this.rafagasDeCpuParaTerminar = rafagasDeCpuParaTerminar;
	}
	public int getDuracionDeCadaRafaga() {
		return duracionDeCadaRafaga;
	}
	public void setDuracionDeCadaRafaga(int duracionDeCadaRafaga) {
		this.duracionDeCadaRafaga = duracionDeCadaRafaga;
	}
	public int getDuracionDePeriodoEnBloqueado() {
		return duracionDePeriodoEnBloqueado;
	}
	public void setDuracionDePeriodoEnBloqueado(int duracionDePeriodoEnBloqueado) {
		this.duracionDePeriodoEnBloqueado = duracionDePeriodoEnBloqueado;
	}
	public int getContRafagasActuales() {
		return contRafagasActuales;
	}
	public void setContRafagasActuales(int contRafagasActuales) {
		this.contRafagasActuales = contRafagasActuales;
	}
	public int getTiempoActualDeRafaga() {
		return tiempoActualDeRafaga;
	}
	public void setTiempoActualDeRafaga(int tiempoActualDeRafaga) {
		this.tiempoActualDeRafaga = tiempoActualDeRafaga;
	}
	public int getTiempoEnEstadoBloqueado() {
		return tiempoEnEstadoBloqueado;
	}
	public void setTiempoEnEstadoBloqueado(int tiempoEnEstadoBloqueado) {
		this.tiempoEnEstadoBloqueado = tiempoEnEstadoBloqueado;
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
	public int getTimeEnListo() {
		return timeEnListo;
	}
	public void setTimeEnListo(int timeEnListo) {
		this.timeEnListo = timeEnListo;
	}

	public void INCREMSumaDeTCPDesdeQueFuiCreado() {
		 SumaDeTCPDesdeQueFuiCreado=SumaDeTCPDesdeQueFuiCreado+So.getTcp();
	}
	
	public int getSumaDeTCPDesdeQueFuiCreado() {
		 return SumaDeTCPDesdeQueFuiCreado;
	}

	public boolean isTengoLosRecursos() {
		return tengoLosRecursos;
	}

	public void setTengoLosRecursos(boolean tengoLosRecursos) {
		this.tengoLosRecursos = tengoLosRecursos;
	}

}