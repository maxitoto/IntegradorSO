package Model;

public class Bloqueado extends Estados {

	public Proceso proceso;
	
    public Bloqueado(Proceso proceso){
		this.proceso=proceso;
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

	@Override
	public void BloqueadoAListo() {
		So so = So.getSo();
		this.proceso.cambiarEstado(new Listo(this.proceso));
		So.getListos().offer(this.proceso);	

		so.getPolitica().OrdenamientoSegúnPolitica(So.getListos());
	}




	
}