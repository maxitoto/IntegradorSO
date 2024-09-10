package Model;

import java.util.*;

import Controller.inicioController;

public class PrioridadExterna extends Politica{
	
	@Override
	public void ordenar() {
		Collections.sort((List) So.getListos(), new Comparator<Proceso>() {
	        @Override
	        public int compare(Proceso p1, Proceso p2) {
	            return Integer.compare(p2.getPrioridad(), p1.getPrioridad());
	        }
	    });
		
	}

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
		if(Cpu.getEjecutando()!=null && !So.getListos().isEmpty() && So.getListos().peek().getPrioridad()>Cpu.getEjecutando().getPrioridad()) {
				So.setBitCambioDeContexto(true);//permito que se incremente el contador de Cpu usado por So
				Cpu.getEjecutando().INCREMSumaDeTCPDesdeQueFuiCreado();
				inicioController.pv( Cpu.getEjecutando().getId()+"pasa de ejecutando a listo (Cambio De Contexto) 'TCP' por Prioridad con el "+So.getListos().peek().getId()+" \n");
				Cpu.getEjecutando().EjecutandoAListo();
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Prioridad Externa";
	}
}

	
	
