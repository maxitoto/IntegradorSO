package Model;


public class Cpu {
	   private static Cpu cpu;
	   private Proceso ejecutando;
	   private double timeUso = 0;
	   private double timeUsoXprocesos = 0;
	   private double tUsadaPorSO = 0;
	   
	   
	
    public Proceso getEjecutando() {
		return ejecutando;
	}

	public void setEjecutando(Proceso ejecutando) {
		this.ejecutando = ejecutando;
	}

	public double getTimeUso() {
		return timeUso;
	}

	public void setTimeUso(double timeUso) {
		this.timeUso = timeUso;
	}

	public double getTimeUsoXprocesos() {
		return timeUsoXprocesos;
	}

	public void setTimeUsoXprocesos(double timeUsoXprocesos) {
		this.timeUsoXprocesos = timeUsoXprocesos;
	}

	public double gettUsadaPorSO() {
		return tUsadaPorSO;
	}

	public void settUsadaPorSO(double tUsadaPorSO) {
		this.tUsadaPorSO = tUsadaPorSO;
	}

	private Cpu() {
    }
    
    public static Cpu getCpu() {
            if(cpu == null) {
            	cpu = new Cpu();
            }     
            return cpu;
    }

    
}