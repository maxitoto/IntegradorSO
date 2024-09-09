package Model;

import Controller.inicioController;

public class Nuevo extends Estados {
	
	private Proceso proceso;
	
	public Nuevo(Proceso proceso){
		this.proceso=proceso;
	}

	@Override
	public void NuevoAListo() {
		So.getListos().offer(this.proceso);
		So.getNuevos().remove(this.proceso);
		proceso.cambiarEstado(new Listo(this.proceso));
		
		So.setBitDarRecursosAProceso(true);
		inicioController.pv("Cpu ocupadado Asignando Recursos al "+ this.proceso.getId()+" 'TIP'"+" \n");
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