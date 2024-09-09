package Model;

import Controller.inicioController;

public class Ejecutando extends Estados {
	
    public Proceso proceso;

    public Ejecutando(Proceso proceso){
		this.proceso=proceso;
	}

	@Override
	public void EjecutandoAListo() {
		Cpu.setEjecutando(null);
		So.getListos().offer(this.proceso);
		proceso.cambiarEstado(new Listo(this.proceso));
	}

	@Override
	public void EjecutandoABloqueado() {
		Cpu.setEjecutando(null);
		So.getBloqueados().offer(this.proceso);
		proceso.cambiarEstado(new Bloqueado(this.proceso));
	}

	@Override
	public void EjecutandoATerminado() {
		Cpu.setEjecutando(null);
		So.getTerminados().offer(this.proceso);
		proceso.cambiarEstado(new Terminado(this.proceso));
		
		So.setBitQuitarRecursosAProceos(true);//si un proceso realiza este cambio de estado entonces debe sumar al tiempo de cpu usado por So un TFP de tiempo, esta llave le permite al Auditor hacerlo
		inicioController.pv("Cpu ocupadado Quitando Recursos al "+ this.proceso.getId()+" 'TFP' "+" \n");
	}

	@Override
	public void BloqueadoAListo() {}
	@Override
	public void NuevoAListo() {}
	@Override
	public void ListoAEjecutando() {}

}