package Model;

public class Ejecutando extends Estados {
	
    public Proceso proceso;

    public Ejecutando(Proceso proceso){
		this.proceso=proceso;
	}

	@Override
	public void NuevoAListo() {}

	@Override
	public void ListoAEjecutando() {}

	@Override
	public void EjecutandoAListo() {
		Cpu cpu = Cpu.getCpu();
		
		if(cpu.getEjecutando()!=null) {
			this.proceso.cambiarEstado(new Listo(this.proceso));
			cpu.setEjecutando(null);
			So so = So.getSo();
			So.getListos().offer(this.proceso);
		
			so.getPolitica().OrdenamientoSegúnPolitica(So.getListos());
		}
	}

	@Override
	public void EjecutandoABloqueado() {
		Cpu cpu = Cpu.getCpu();
		
		if(cpu.getEjecutando()!=null) {
			this.proceso.cambiarEstado(new Bloqueado(this.proceso));
			cpu.setEjecutando(null);
			So so = So.getSo();
			So.getBloqueados().offer(this.proceso);
		}
	}

	@Override
	public void EjecutandoATerminado() {
		Cpu cpu = Cpu.getCpu();
		
		if(cpu.getEjecutando()!=null) {
			this.proceso.cambiarEstado(new Terminado(this.proceso));
			cpu.setEjecutando(null);
			So so = So.getSo();
			So.getTerminados().offer(this.proceso);
		}
	}

	@Override
	public void BloqueadoAListo() {}



}