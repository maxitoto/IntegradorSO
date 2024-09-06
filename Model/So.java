package Model;

import java.util.*;

public class So {
    private static Queue<Proceso> Listos = new LinkedList<Proceso>();
    private static Queue<Proceso> Bloqueados = new LinkedList<Proceso>();
    private static Queue<Proceso> Nuevos = new LinkedList<Proceso>();
    private static Queue<Proceso> Terminados = new LinkedList<Proceso>();
    private static Cpu cpu =  Cpu.getCpu();
    public static int click = -1;
    private static So so;
    private Politica politica;
    private double timeRetornoXtanda = 0;
    private double timeRetornoMedioXtanda = 0;
   
    private static int timeXsoParaAceptarNuevosProcesos = 0;
    private static int timeXsoParaTerminarProcesos = 0;
    private static int timeConmutaciónEntreprocesos = 0;
    private static int quantum = 0;
    
    private So() {}

    public static So getSo() {
        if(so == null) {
        	so = new So();
        }       
        return so;
    }

 
	public Politica getPolitica() {
		return politica;
	}

	public void setPolitica(Politica politica) {
		this.politica = politica;
	}

	public static Queue<Proceso> getListos() {
		return Listos;
	}

	public static void setListos(Queue<Proceso> listos) {
		Listos = listos;
	}

	public static Queue<Proceso> getBloqueados() {
		return Bloqueados;
	}

	public static void setBloqueados(Queue<Proceso> bloqueados) {
		Bloqueados = bloqueados;
	}

	public static Queue<Proceso> getNuevos() {
		return Nuevos;
	}

	public static void setNuevos(Queue<Proceso> nuevos) {
		Nuevos = nuevos;
	}

	public static Queue<Proceso> getTerminados() {
		return Terminados;
	}

	public static void setTerminados(Queue<Proceso> terminados) {
		Terminados = terminados;
	}

	public static Cpu getCpu() {
		return cpu;
	}

	public static void setCpu(Cpu cpu) {
		So.cpu = cpu;
	}

	public double getTimeRetornoXtanda() {
		return timeRetornoXtanda;
	}

	public void setTimeRetornoXtanda(double timeRetornoXtanda) {
		this.timeRetornoXtanda = timeRetornoXtanda;
	}

	public double getTimeRetornoMedioXtanda() {
		return timeRetornoMedioXtanda;
	}

	public void setTimeRetornoMedioXtanda(double timeRetornoMedioXtanda) {
		this.timeRetornoMedioXtanda = timeRetornoMedioXtanda;
	}

	public static int getTimeXsoParaAceptarNuevosProcesos() {
		return timeXsoParaAceptarNuevosProcesos;
	}

	public static void setTimeXsoParaAceptarNuevosProcesos(int timeXsoParaAceptarNuevosProcesos) {
		So.timeXsoParaAceptarNuevosProcesos = timeXsoParaAceptarNuevosProcesos;
	}

	public static int getTimeXsoParaTerminarProcesos() {
		return timeXsoParaTerminarProcesos;
	}

	public static void setTimeXsoParaTerminarProcesos(int timeXsoParaTerminarProcesos) {
		So.timeXsoParaTerminarProcesos = timeXsoParaTerminarProcesos;
	}

	public static int getTimeConmutaciónEntreprocesos() {
		return timeConmutaciónEntreprocesos;
	}

	public static void setTimeConmutaciónEntreprocesos(int timeConmutaciónEntreprocesos) {
		So.timeConmutaciónEntreprocesos = timeConmutaciónEntreprocesos;
	}

	public static int getQuantum() {
		return quantum;
	}

	public static void setQuantum(int quantum) {
		So.quantum = quantum;
	}







}