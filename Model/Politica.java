package Model;

import java.util.ArrayList;
import java.util.List;

import Controller.inicioController;

public abstract class Politica  {
	public abstract boolean cuandoPasarDeListoAEjecutando();
	public abstract boolean cuandoPasarDeEjecutandoAListo();
	
	public  boolean cuandoPasarDeEjecutandoABloqueado() {
		if(Cpu.getEjecutando()!=null && Cpu.getEjecutando().getTiempoActualDeRafaga()==Cpu.getEjecutando().getDuracionDeCadaRafaga()) {
			inicioController.pv(Cpu.getEjecutando().getId()+" paso de  ejectuando a bloqueado"+"\n");				
				Cpu.getEjecutando().setTiempoEnEstadoBloqueado(0);
				Cpu.getEjecutando().EjecutandoABloqueado();
			return true;
		}
		return false;
	}
	
	public boolean cuandoPasarDeBloqueadoAListo() {
		if(!So.getBloqueados().isEmpty()) {
			
			List<Proceso> aux = new ArrayList<Proceso>();
			for (Proceso p : So.getBloqueados()) {
				if(p.getTiempoEnEstadoBloqueado()==p.getDuracionDePeriodoEnBloqueado()) {
					aux.add(p);
				}
			}
			if(!aux.isEmpty()) {
				for (Proceso p : aux) {
					p.setTiempoActualDeRafaga(0);
					p.BloqueadoAListo();
					inicioController.pv(p.getId()+" paso de bloqueado a listo"+"\n");
				}
				return true;
			}
			
		}
		return false;
	}
	
	public boolean cuandoPasarDeNuevoAListo() {
		if(!So.getNuevos().isEmpty()) {
			List<Proceso> aux = new ArrayList<Proceso>();
			for (Proceso  p : So.getNuevos()) {
				if(p.getTiempoDeArribo()==So.getCLK()) {
					aux.add(p);
				}
			}
			
			if(!aux.isEmpty()) {
				for (Proceso p : aux) {
					p.NuevoAListo();		
					inicioController.pv(p.getId()+" paso de nuevo a listo"+"\n");
				}
				
				return true;
			}
			return false;
		}
		return false;
	}
	
	public boolean cuandoPasarDeEjecutandoATerminado() {
		if(Cpu.getEjecutando()!=null && Cpu.getEjecutando().getContRafagasActuales()==Cpu.getEjecutando().getRafagasDeCpuParaTerminar()) {
				inicioController.pv(Cpu.getEjecutando().getId()+" paso de ejecutando a terminado"+"\n");
				Cpu.getEjecutando().EjecutandoATerminado();		
				return true;
		}
		return false;
	}
	
	public abstract void ordenar();
}