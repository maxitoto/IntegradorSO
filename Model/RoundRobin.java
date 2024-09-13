package Model;

import Controller.inicioController;

public class RoundRobin extends Politica{

	@Override
	public boolean cuandoPasarDeEjecutandoAListo() {
		if(Cpu.getEjecutando()!=null && 	!So.getListos().isEmpty() &&So.getQ()==So.getContquantum()) {
				inicioController.pv( Cpu.getEjecutando().getId()+" pasa de ejecutando a listo por Quamtum "+" \n");
				Cpu.getEjecutando().EjecutandoAListo();
				return true;
			}
		return false;
	}


	@Override
	public void ordenar() {
		// fifo
		
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Round Robin";
	}

}