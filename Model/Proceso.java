package Model;

public class Proceso {
	
    private static int conteoIdentificador = 0;
    private Estados estado;
    private int prioridad = 0;
    private double timeRetorno = 0;
    private int id = conteoIdentificador;
    private double timeRetornoNormalizado = 0;
    private double timeEnListo = 0;

    public Proceso() {
    	this.conteoIdentificador++;
    }

    public void cambiarEstado() {
        // TODO implement here
    }

    public void modificarEstadoActual(Estados estado) {
        // TODO implement here
    }

}