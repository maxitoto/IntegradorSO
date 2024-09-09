package Model;
import java.util.*;

import Controller.inicioController;

public class Auditor {

	public void aumentarContadores() {
		
		if(So.getContquantum()==So.getQ()) {So.setContquantum(0);}else {So.setContquantum(So.getContquantum()+1);}
		
		if(So.isBitDarRecursosAProceso()) {//si llego un proceso nuevo el Cpu no esta ejecutando nada que no sea un servicio So
			Cpu.settUsadaPorSO(Cpu.gettUsadaPorSO()+So.getTip());
			So.setBitDarRecursosAProceso(false);
			
		}
			if(So.isBitQuitarRecursosAProceos()) {//si un proceso se fue de Ejecutando a Terminado entonces hay que quitarle los recursos y esto conlleva un TFP de tiempo
				 Cpu.settUsadaPorSO(Cpu.gettUsadaPorSO()+So.getTfp());
				 So.setBitQuitarRecursosAProceos(false);
				 
			 }
				 if(So.isBitCambioDeContexto()) {//si ubo un cambio de contesto incremento el time de uso por SO, segun el tiempo que ingreso el usuario (TCP)
						Cpu.settUsadaPorSO(Cpu.gettUsadaPorSO()+So.getTcp());//cuando un proceso se mueve de listo a ejecutando da paso a incrementar aqui el cpu usado por el So
						So.setBitCambioDeContexto(false);//cada proceso que se mueve de Listo a Ejecutando lo pone en true y ademas incrementera su contador personal de la suma de los TPC desde que fue creado
						
					 }	                           
						 if(So.getCpu().getEjecutando()!=null) {//incrementa el tiempo de la rafaga actual del proceso en estado de ejecucion.
		          	   Proceso p = So.getCpu().getEjecutando();
			               p.setTiempoActualDeRafaga(p.getTiempoActualDeRafaga()+1);
			               Cpu.setTimeUsoXprocesos(Cpu.getTimeUsoXprocesos()+1);//como el cpu esta siendo usado por un proceso  entonces no esta ocioso
			               inicioController.pv("Cpu ejecuta el P:"+p.getId());
			               if(p.getTiempoActualDeRafaga()==p.getDuracionDeCadaRafaga()) {//aumentar la rafaga cada vez que la rafaga actual sea igual al limite de x rafaga de cpu			            	            	   
			            	   p.setContRafagasActuales(p.getContRafagasActuales()+1);	  
			            	   inicioController.pv("El P:"+p.getId()+" ha terminado una rafaga completan\n");
			               }
			
		             }else {
				                 Cpu.setTimeOcioso(Cpu.getTimeOcioso()+1);
				                 inicioController.pv("Cpu esta Ocioson\n");
				                 }//si no hay proceso ejecutando entonces el cpu esta ocioso
					 
			 
		
			 
			
			 
		
			                         
		            
		
		if(!So.getListos().isEmpty()) {
			for (Proceso p : So.getListos()) {//incrementa el tiempo en estado de listo
				if(!p.isTengoLosRecursos()) {// si no tengo los recursos entocens fui recien creado y no debo aumentar el tiempo en estado de listo en 1, por lo menos la primera vez
					p.setTengoLosRecursos(true);
				}else {p.setTimeEnListo(p.getTimeEnListo()+1);}
			}
		}
			 
		if(!So.getBloqueados().isEmpty()) {
			for (Proceso p : So.getBloqueados()) {//incrementa el tiempo en estado de bloqueado
				p.setTiempoEnEstadoBloqueado(p.getTiempoEnEstadoBloqueado()+1);
			}
		}
		
}
			 

	public List<String> contabilidadFinal() {
	    List<String> listaResultados = new ArrayList<>();

	    for (Proceso p : So.getTerminados()) {
	        // Calcula el tiempo de retorno desde que fue creado hasta que finalizó
	        double tiempoRetorno = So.getTip() + // tiempo de nuevo a listo (creación)
	                            p.getTimeEnListo() + // tiempo en estado de listo
	                            (p.getDuracionDePeriodoEnBloqueado() * (p.getRafagasDeCpuParaTerminar() - 1)) + // duración en estado de bloqueado * cantidad de veces que estuvo ahí menos la última que terminó
	                            (p.getDuracionDeCadaRafaga() * p.getRafagasDeCpuParaTerminar()) + // duración de cada ráfaga de CPU
	                            p.getSumaDeTCPDesdeQueFuiCreado() + // suma de TCP desde que fue creado
	                            So.getTfp(); // tiempo final de procesamiento

	        p.setTimeRetorno(tiempoRetorno);

	        // Añade los resultados a la lista
	        listaResultados.add(
	            p.getId() + "  tip:" + So.getTip() +
	            " TListo:" + p.getTimeEnListo() +
	            " Tblock:" + p.getDuracionDePeriodoEnBloqueado() +
	            " TTermProc-1:" + (p.getRafagasDeCpuParaTerminar() - 1) +
	            " durRA:" + p.getDuracionDeCadaRafaga() +
	            " TtermRaf:" + p.getRafagasDeCpuParaTerminar() +
	            " SumTCP: " + p.getSumaDeTCPDesdeQueFuiCreado() +
	            " tfp:" + So.getTfp() +
	            " Resultado:" + p.getTimeRetorno()
	        );
	    }

	    return listaResultados;
	}

}
