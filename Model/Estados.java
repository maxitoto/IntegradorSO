package Model;

public abstract class Estados {
	public abstract void NuevoAListo();
	public abstract void ListoAEjecutando();
	public abstract void EjecutandoAListo();
	public abstract void EjecutandoABloqueado();
	public abstract void EjecutandoATerminado();
	public abstract void BloqueadoAListo();
}