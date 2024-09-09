package Model;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SPN extends Politica {

	@Override
	public void ordenar() {
		Collections.sort((List) So.getListos(), new Comparator<Proceso>() {
	        @Override
	        public int compare(Proceso p1, Proceso p2) {
	            return Integer.compare(p1.getTiempoActualDeRafaga(), p2.getTiempoActualDeRafaga());
	        }
	    });
		
	}

	@Override
	public boolean cuandoPasarDeListoAEjecutando() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean cuandoPasarDeEjecutandoAListo() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean cuandoPasarDeEjecutandoABloqueado() {
		// TODO Auto-generated method stub
		return false;
	}


}