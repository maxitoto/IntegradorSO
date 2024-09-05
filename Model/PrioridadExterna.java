package Model;

import java.util.*;
import Model.*; 

public class PrioridadExterna extends Politica {

	@Override
	public void OrdenamientoSegúnPolitica(Queue<Proceso> colaDeProcesos) {//una vez encolado se reordena la cola de listos en orden de proridades desde el 100 - 1
		PriorityQueue<Proceso> queue = new PriorityQueue<>((Proceso a, Proceso b) -> Integer.compare(b.getPrioridad(), a.getPrioridad()));
	    queue.addAll(So.getNuevos());
	    So.setNuevos(queue);
	}

	@Override
	public void aplicarPolitica() {
		// TODO Auto-generated method stub
		
	}
}
	
