package Model;

public class FCFS extends Politica{

	@Override
	public boolean cuandoPasarDeEjecutandoAListo() {
		// Nunca
		return false;
	}


	@Override
	public void ordenar() {
		// fifo
		
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "FCFS";
	}
}