package Model;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import Controller.inicioController;

public class SPN extends Politica {

	@Override
	public void ordenar() {
		Collections.sort((List) So.getListos(), new Comparator<Proceso>() {
	        @Override
	        public int compare(Proceso p1, Proceso p2) {   
	            return Integer.compare(p1.getDuracionDeCadaRafaga(), p2.getDuracionDeCadaRafaga());
	        }
	    });
	}

	@Override
	public boolean cuandoPasarDeEjecutandoAListo() {
		if(Cpu.getEjecutando()!=null && !So.getListos().isEmpty() && So.getListos().peek().getDuracionDeCadaRafaga()<Cpu.getEjecutando().getDuracionDeCadaRafaga()) {
			if(So.getListos().peek().isTengoLosRecursos()) {
				inicioController.pv( Cpu.getEjecutando().getId()+" pasa de ejecutando a listo (Cambio De Contexto) 'TCP' porque "+So.getListos().peek().getId()+" tiene una rafaga más corta "+" \n");
				Cpu.getEjecutando().EjecutandoAListo();
				return true;
			}else {
				inicioController.pv( Cpu.getEjecutando().getId()+" NO pasará de ejecutando a listo porque el "+So.getListos().peek().getId()+" Aún no tiene los Recursos " +" \n");
				}
		}
		return false;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "SPN";
	}

}