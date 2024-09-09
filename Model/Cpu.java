package Model;


public class Cpu {
	   private static Cpu cpu;
	   private static Proceso ejecutando;
	   private static double timeOcioso = 0;
	   private static double timeUsoXprocesos = 0;
	   private static double tUsadaPorSO = 0;
	   
	   private static Auditor auditor = new Auditor();
	   
	   private Cpu() {}
	    
	   public static Cpu getCpu() {
	           if(Cpu.cpu == null) {
	        	   Cpu.cpu = new Cpu();
	           	}     
	           return Cpu.cpu;
	    }	   
	
    public static Proceso getEjecutando() {
		return ejecutando;
	}
	public static void setEjecutando(Proceso ejecutando) {
		Cpu.ejecutando = ejecutando;
	}
	public static double getTimeOcioso() {
		return timeOcioso;
	}
	public static void setTimeOcioso(double timeOcioso) {
		Cpu.timeOcioso = timeOcioso;
	}
	public static double getTimeUsoXprocesos() {
		return timeUsoXprocesos;
	}
	public static void setTimeUsoXprocesos(double timeUsoXprocesos) {
		Cpu.timeUsoXprocesos = timeUsoXprocesos;
	}
	public static double gettUsadaPorSO() {
		return tUsadaPorSO;
	}
	public static void settUsadaPorSO(double tUsadaPorSO) {
		Cpu.tUsadaPorSO = tUsadaPorSO;
	}

	public static Auditor getAuditor() {
		return auditor;
	}

	public static void setAuditor(Auditor auditor) {
		Cpu.auditor = auditor;
	}
	
	

    
}