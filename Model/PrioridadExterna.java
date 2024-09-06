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
	public void accionDePolitica() {
		
		if(!So.getNuevos().isEmpty()) {
			for (Proceso p : So.getNuevos()) {//busco quien ya cumplio su tiempo bloqueado
				if(p.getTimePreparativosNuevoAListo()==So.getTimeXsoParaAceptarNuevosProcesos()) {
					p.NuevoAListo();
					So.getNuevos().remove(p);
				}else {p.setTimePreparativosNuevoAListo(p.getTimePreparativosNuevoAListo()+1);}
			}
		}
		
		if(!So.getBloqueados().isEmpty()) {
			for (Proceso p : So.getBloqueados()) {//busco quien ya cumplio su tiempo bloqueado
				if(p.getDuracionDeBloqueado()==p.getTimeEnBloqueado()) {
					p.BloqueadoAListo();
					So.getBloqueados().remove(p);
					p.setTimeEnBloqueado(0);//reset de tiempo en bloqueado porque ya lo cumplio
				}else {p.setDuracionDeBloqueado(p.getDuracionDeBloqueado()+1);}
			}
		}
		
		if(!So.getListos().isEmpty()) {
			for (Proceso p : So.getListos()) {
				p.setTimeEnListo(p.getTimeEnListo()+1);
			}
			
			if(So.getCpu().getEjecutando()!=null) {
				if(So.getListos().peek().getPrioridad()>So.getCpu().getEjecutando().getPrioridad()) {//si el proceso de la cola de listos tiene más prioridad entonces cambio el estado
					So.getCpu().getEjecutando().EjecutandoAListo();//el proceso en el cpu pasa a listos
					So.getListos().poll().ListoAEjecutando();//el proceso de listos pasa a ejecutando
				}
			}
			
		}
		
		if(So.getCpu().getEjecutando()!=null){
			if(So.getCpu().getEjecutando().getCantidadDeRafagasRestantesParaTerminar()==0) {
				if(So.getCpu().getEjecutando().getTiemporestanteParaFinalizar()==So.getTimeXsoParaTerminarProcesos()) {
					So.getCpu().getEjecutando().EjecutandoATerminado();
				}else {So.getCpu().getEjecutando().setTiemporestanteParaFinalizar(So.getCpu().getEjecutando().getTiemporestanteParaFinalizar()+1);}
					
				
			}else {
				if(So.getCpu().getEjecutando().getRafagaActual()==So.getCpu().getEjecutando().getDuracionDeRafaga()){
					So.getCpu().getEjecutando().EjecutandoABloqueado();
				}else {So.getCpu().getEjecutando().setRafagaActual(So.getCpu().getEjecutando().getRafagaActual()+1);}
				
			}
		}else {
			if(!So.getListos().isEmpty()) {So.getListos().poll().ListoAEjecutando();}
		}
		
	}


}
	
