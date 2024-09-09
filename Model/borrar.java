package Model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import javax.swing.JFileChooser;

import Controller.inicioController;

public class borrar {


	
	public static void main(String[] args) {
		So.getSo();
		
		Proceso p13 = new Proceso("Proceso13", 1, 1, 2, 3, 72);
		Proceso p11 = new Proceso("Proceso11", 2, 1, 2, 2, 91);
		Proceso p17 = new Proceso("Proceso17", 3, 1, 2, 1, 94);

		
		So.getNuevos().add(p13);
		So.getNuevos().add(p11);
		So.getNuevos().add(p17);

		So.setPolitica(new FCFS());
		So.setTcp(1);
		So.setTip(2);
		So.setTfp(1);
		
		
		while(So.getTerminados().size()<3) {
			So.CLK++;
			System.out.println(
					So.CLK-1+"\n"+
					"Nuevos: " + So.getNuevos() + "\n"+
					"Listos: " + So.getListos() + "\n"+
					"Block: " + So.getBloqueados() + "\n"+
					"Ejec: " + (So.getCpu().getEjecutando() == null ? "null" : So.getCpu().getEjecutando())  + "\n"+
					"Term: " + So.getTerminados() + "\n"
					);

			Cpu.getAuditor().aumentarContadores(); 
			
	             
			if(So.getPolitica().cuandoPasarDeEjecutandoATerminado()){}
			if(So.getPolitica().cuandoPasarDeEjecutandoABloqueado()) { }
			if(So.getPolitica().cuandoPasarDeEjecutandoAListo()){So.getPolitica().ordenar();  }
			if(So.getPolitica().cuandoPasarDeBloqueadoAListo()) {So.getPolitica().ordenar();  }
			if(So.getPolitica().cuandoPasarDeNuevoAListo()) {So.getPolitica().ordenar();}
			if(So.getPolitica().cuandoPasarDeListoAEjecutando()){}
			
		}
		

		
		for (String string : Cpu.getAuditor().contabilidadFinal()) {
			System.out.println(string);
		}
		
		
		}
		
	}


