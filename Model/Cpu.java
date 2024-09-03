package Model;


public class Cpu {
	   private static Cpu cpu;
	   private Proceso ejecutando;
	   private double timeUso = 0;
	   private double timeUsoXprocesos = 0;
	   private double tUsadaPorSO = 0;
	
    private Cpu() {
    }
    
    public static Cpu getCpu() {
            if(cpu == null) {
            	Cpu cpu = new Cpu();
            }     
            return cpu;
    }

    
}