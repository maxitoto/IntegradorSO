package Model;

public class Nuevo extends Estados {
	private Proceso proceso;
	
	public Nuevo(Proceso proceso){
		this.proceso=proceso;
	}

	@Override
	public void NuevoAListo() {
		So so = So.getSo();
		this.proceso.cambiarEstado(new Listo(this.proceso));
		So.getListos().offer(this.proceso);	

		so.getPolitica().OrdenamientoSegúnPolitica(So.getListos());
	}


	@Override
	public void ListoAEjecutando() {}

	@Override
	public void EjecutandoAListo() {}

	@Override
	public void EjecutandoABloqueado() {}

	@Override
	public void EjecutandoATerminado() {}

	@Override
	public void BloqueadoAListo() {}


}