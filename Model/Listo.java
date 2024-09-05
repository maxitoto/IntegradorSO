package Model;

public class Listo extends Estados {
	
	public Proceso proceso;
	
    public Listo(Proceso proceso){
		this.proceso=proceso;
	}

	@Override
	public void NuevoAListo() {}

	@Override
	public void ListoAEjecutando() {
		Cpu cpu = Cpu.getCpu();
		
		this.proceso.cambiarEstado(new Ejecutando(this.proceso));
		
		if(cpu.getEjecutando()==null){
			cpu.setEjecutando(this.proceso);
		}
		
	}
	
	@Override
	public void EjecutandoAListo() {}

	@Override
	public void EjecutandoABloqueado() {}

	@Override
	public void EjecutandoATerminado() {}

	@Override
	public void BloqueadoAListo() {}





}