package Model;


import java.util.*;

public class So {
    private static Queue<Proceso> Listos;
    private static Queue<Proceso> Bloqueados;
    private static Queue<Proceso> Nuevos;
    private static Queue<Proceso> Terminados;
    private static Cpu cpu;
    public static int CLK = 0;
    private static So so;
    private static Politica politica;
    
    private static int timeRetornoXtanda;
    private static double timeRetornoMedioXtanda;
   
    private static int tip;
    private static int tfp;
    private static int tcp;
    private static int q;
    private static int contquantum = 0;
      
    private So() {    	
    	So.Listos = new LinkedList<Proceso>();
    	So.Nuevos = new LinkedList<Proceso>();
    	So.Bloqueados = new LinkedList<Proceso>();
    	So.Terminados = new LinkedList<Proceso>();
    	So.cpu = Cpu.getCpu();
    	So.timeRetornoXtanda=0;
    	So.timeRetornoMedioXtanda=0;
    	So.tip=0;//se usa para no incrementar procesos que apenas llegaron al estado de listo y se sumara al tiempo inicial de cada proceso una unica vez "puede hacerse cuando el simuladro termine"
    	So.tfp=0;//se sumara al tiempo final de cada proceso una unica vez "se puede hacer cuando el simulador termine"
    	So.tcp=0;//se sumara al contador de la cpu siendo usada por el So
    	So.q=0;
    	So.contquantum=0;
    }

    public static void reset() {
        Listos = new LinkedList<>();
        Bloqueados = new LinkedList<>();
        Nuevos = new LinkedList<>();
        Terminados = new LinkedList<>();
        cpu = Cpu.resetCpu();
        CLK = 0;
        timeRetornoXtanda = 0;
        timeRetornoMedioXtanda = 0.0;
        tip = 0;
        tfp = 0;
        tcp = 0;
        q = 0;
        contquantum = 0;
        politica = null;  
    }
    
    public static So getSo() {
        if(So.so == null) {
        	So.so = new So();
        }       
        return So.so;
    }
    
	public static Politica getPolitica() {
		return politica;
	}
	public static void setPolitica(Politica politica) {
		So.politica = politica;
	}
	public static int getTimeRetornoXtanda() {
		return timeRetornoXtanda;
	}
	public static void setTimeRetornoXtanda(int timeRetornoXtanda) {
		So.timeRetornoXtanda = timeRetornoXtanda;
	}
	public static double getTimeRetornoMedioXtanda() {
		return timeRetornoMedioXtanda;
	}
	public static void setTimeRetornoMedioXtanda(double timeRetornoMedioXtanda) {
		So.timeRetornoMedioXtanda = timeRetornoMedioXtanda;
	}
	public static int getTip() {
		return tip;
	}
	public static void setTip(int tip) {
		So.tip = tip;
	}
	public static int getTfp() {
		return tfp;
	}
	public static void setTfp(int tfp) {
		So.tfp = tfp;
	}
	public static int getTcp() {
		return tcp;
	}
	public static void setTcp(int tcp) {
		So.tcp = tcp;
	}
	public static int getQ() {
		return q;
	}
	public static void setQ(int q) {
		So.q = q;
	}
	public static Queue<Proceso> getListos() {
		return Listos;
	}
	public static Queue<Proceso> getBloqueados() {
		return Bloqueados;
	}
	public static Queue<Proceso> getNuevos() {
		return Nuevos;
	}
	public static Queue<Proceso> getTerminados() {
		return Terminados;
	}
	public static Cpu getCpu() {
		return cpu;
	}
	public static int getCLK() {
		return CLK;
	}
	public static void setCLK(int CLK) {
		 So.CLK=CLK;
	}
	public static int getContquantum() {
		return contquantum;
	}
	public static void setContquantum(int contquantum) {
		So.contquantum = contquantum;
	}


}