package Model;

import Controller.inicioController;

public class Ejecutando extends Estados {
	
    public Proceso proceso;

    public Ejecutando(Proceso proceso){
		this.proceso=proceso;
	}

	@Override
	public void EjecutandoAListo() {
		Cpu.settUsadaPorSO(Cpu.gettUsadaPorSO()+So.getTcp());//incremento el contador GLOBAL de CPU usada por SO
		this.proceso.INCREMSumaDeTCPDesdeQueFuiCreado();//incremento el contador de Sumatoria de cambios de contexto X proceso (Local)
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
		Cpu.settUsadaPorSO(Cpu.gettUsadaPorSO()+So.getTfp());//incrementa el contador global de Cpu usado X SO
		inicioController.pv("Cpu ocupadado Quitando Recursos al "+ this.proceso.getId()+" 'TFP' "+" \n");
	}

	@Override
	public void BloqueadoAListo() {}
	@Override
	public void NuevoAListo() {}
	@Override
	public void ListoAEjecutando() {}

}