package Model;

public class Bloqueado extends Estados {

	public Proceso proceso;
	
    public Bloqueado(Proceso proceso){
		this.proceso=proceso;
	}


	@Override
	public void BloqueadoAListo() {
		So.getListos().offer(this.proceso);
		So.getBloqueados().remove(this.proceso);
		proceso.cambiarEstado(new Listo(this.proceso));
	}

	
	@Override
	public void NuevoAListo() {}
	@Override
	public void ListoAEjecutando() {}
	@Override
	public void EjecutandoAListo() {}
	@Override
	public void EjecutandoABloqueado() {}
	@Override
	public void EjecutandoATerminado() {}
}