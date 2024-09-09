package Model;

public class Terminado extends Estados {
	
    public Proceso proceso;

    public Terminado(Proceso proceso){
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
	public void BloqueadoAListo() {}

}