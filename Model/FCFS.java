package Model;

import Controller.inicioController;

public class FCFS extends Politica{

	@Override
	public boolean cuandoPasarDeListoAEjecutando() {
		if(Cpu.getEjecutando()==null && !So.getListos().isEmpty() && So.getListos().peek().isTengoLosRecursos()) {
			inicioController.pv(So.getListos().peek().getId()+" paso de listo  a ejectuando"+"\n");
			So.getListos().peek().ListoAEjecutando();
			return true;
		}else {return false;}
	}

	@Override
	public boolean cuandoPasarDeEjecutandoAListo() {
		// Nunca
		return false;
	}


	@Override
	public void ordenar() {
		// fifo
		
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "FCFS";
	}
}