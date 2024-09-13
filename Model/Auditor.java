package Model;
import java.util.*;

import Controller.inicioController;

public class Auditor {
	public void aumentarContadores() {
		
		if(So.getPolitica().toString()=="Round Robin") {
			if(So.getContquantum()==So.getQ()) {So.setContquantum(0); So.setCLK(So.getCLK()-1); return;}else {So.setContquantum(So.getContquantum()+1);}
		}
		
		if(So.getCpu().getEjecutando()!=null) {//incrementa el tiempo de la rafaga actual del proceso en estado de ejecucion.
			Proceso p = So.getCpu().getEjecutando();
			p.setTiempoActualDeRafaga(p.getTiempoActualDeRafaga()+1);//aumento la rafaga actual, para luego comparar con el limite (duracion de la rafaga)
			Cpu.setTimeUsoXprocesos(Cpu.getTimeUsoXprocesos()+1);//como el cpu esta siendo usado por un proceso  entonces no esta ocioso
			inicioController.pv("Cpu ejecuta el P:"+p.getId());
					if(p.getTiempoActualDeRafaga()==p.getDuracionDeCadaRafaga()) {//aumentar la rafaga cada vez que la rafaga actual sea igual al limite X rafaga de cpu			            	            	   
							p.setContRafagasActuales(p.getContRafagasActuales()+1);	  
							inicioController.pv("El "+p.getId()+" ha terminado una rafaga completan "+"\n");
					}
		}else {
				      Cpu.setTimeOcioso(Cpu.getTimeOcioso()+1);
				      inicioController.pv(" Cpu esta Ocioson "+"\n");
				   }//si no hay proceso ejecutando entonces el cpu esta ocioso
					 
	         		
		if(!So.getListos().isEmpty()) {
			for (Proceso p : So.getListos()) {//incrementa el contador en estado de listo de cada proceso
				if(!p.isTengoLosRecursos()) {// si no tengo los recursos entocens fui recien creado y no debo aumentar el tiempo en estado de listo en 1, por lo menos la primera vez, tampo debo ingresar a estado de Ejectuando
					p.setTengoLosRecursos(true);
				}else {p.setTimeEnListo(p.getTimeEnListo()+1);}
			}
		}
			 
		if(!So.getBloqueados().isEmpty()) {
			for (Proceso p : So.getBloqueados()) {//incrementa el contador de en estado de bloqueado de cada proceso
				p.setTiempoEnEstadoBloqueado(p.getTiempoEnEstadoBloqueado()+1);
			}
		}
		
}
			 

	public List<String> contabilidadFinalXProceso() {
	    List<String> listaResultados = new ArrayList<>();
	    if(!So.getTerminados().isEmpty()) {
	    	for (Proceso p : So.getTerminados()) {
	        	// Calcula el tiempo de retorno desde que fue creado hasta que finalizó
	        	double tiempoRetorno = So.getTip() + // tiempo de nuevo a listo (creación)
	                            	p.getTimeEnListo() + // tiempo en estado de listo
	                            	(p.getDuracionDePeriodoEnBloqueado() * (p.getRafagasDeCpuParaTerminar() - 1)) + // duración en estado de bloqueado * cantidad de veces que estuvo ahí menos la última que terminó
	                            	(p.getDuracionDeCadaRafaga() * p.getRafagasDeCpuParaTerminar()) + // duración de cada ráfaga de CPU
	                            	p.getSumaDeTCPDesdeQueFuiCreado() + // suma de TCP desde que fue creado
	                            	So.getTfp(); // tiempo final de procesamiento

	        	p.setTimeRetorno(tiempoRetorno);
	        	double tiempoRetornoNormalizado = tiempoRetorno / (p.getDuracionDeCadaRafaga()*p.getRafagasDeCpuParaTerminar());

	        	p.setTimeRetornoNormalizado(tiempoRetornoNormalizado);
	        
	        	// Añade los resultados a la lista
	        	listaResultados.add(
	        		p.getId() + " Tiempo De Retorno = "+p.getTimeRetorno() +" Tiempo de Retorno Normalizado = "+p.getTimeRetornoNormalizado() 
	        		);
	    	}
	    	listaResultados.add("\n" );
	    }
	    return listaResultados;
	}
	
	public List<String> contabilidadFinalXTanda() {
		 List<String> listaResultados = new ArrayList<>();
		double mediaNormalizadaXtanda = 0;
		double retornoXtanda= 0;
		double medioXtanda = 0;
		
		if(!So.getTerminados().isEmpty()) {
		          for (Proceso p : So.getTerminados()) {
			          mediaNormalizadaXtanda=mediaNormalizadaXtanda+p.getTimeRetornoNormalizado();
			          retornoXtanda=retornoXtanda+p.getTimeRetorno();
		          }	
		          
		          mediaNormalizadaXtanda=mediaNormalizadaXtanda/So.getTerminados().size();
		          medioXtanda=retornoXtanda/So.getTerminados().size();
		          
		          listaResultados.add(
				          "Timepo de retorno por Tanda = "+retornoXtanda+"\n"+
				        		 "Tiempo medio de retorno por tanda = "+medioXtanda+"\n"+
				        		  		" Tiempo medio de Retorno Normalizado por tanda = "+mediaNormalizadaXtanda+"\n"
				          );	          
	   }
		return listaResultados;
	}
	
}
