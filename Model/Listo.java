package Model;

public class Listo extends Estados {
	
	public Proceso proceso;
	
    public Listo(Proceso proceso){
		this.proceso=proceso;
	}

	@Override
	public void ListoAEjecutando() {		
		Cpu.setEjecutando(null);
		Cpu.setEjecutando(So.getListos().poll());
		So.getListos().remove(this.proceso);
		proceso.cambiarEstado(new Ejecutando(this.proceso));
	}
	
	@Override
	public void NuevoAListo() {}
	@Override
	public void EjecutandoAListo() {}
	@Override
	public void EjecutandoABloqueado() {}
	@Override
	public void EjecutandoATerminado() {}
	@Override
	public void BloqueadoAListo() {}





}