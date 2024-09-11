package Model;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import Controller.inicioController;

public class SRTN extends Politica{

	@Override
	public boolean cuandoPasarDeEjecutandoAListo() {
		if(Cpu.getEjecutando()!=null && !So.getListos().isEmpty() && (So.getListos().peek().getDuracionDeCadaRafaga()-So.getListos().peek().getTiempoActualDeRafaga())<(Cpu.getEjecutando().getDuracionDeCadaRafaga()-Cpu.getEjecutando().getTiempoActualDeRafaga())) {
			inicioController.pv( Cpu.getEjecutando().getId()+" pasa de ejecutando a listo (Cambio De Contexto) 'TCP' porque "+So.getListos().peek().getId()+" tiene una rafaga restante más corta "+" \n");
			Cpu.getEjecutando().EjecutandoAListo();
			return true;
		}
		return false;
	}

	@Override
	public void ordenar() {//por duración de la rafaga restante más corta
		Collections.sort((List) So.getListos(), new Comparator<Proceso>() {
	        @Override
	        public int compare(Proceso p1, Proceso p2) {   
	            return Integer.compare(p1.getDuracionDeCadaRafaga()-p1.getTiempoActualDeRafaga(), p2.getDuracionDeCadaRafaga()-p2.getTiempoActualDeRafaga());
	        }
	    });
	}

@Override
public String toString() {
	// TODO Auto-generated method stub
	return "SRTN";
}


}