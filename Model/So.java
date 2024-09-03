package Model;

import java.util.*;

public class So {
    private static List<Proceso> Listos = new ArrayList<Proceso>();
    private static List<Proceso> Bloqueados = new ArrayList<Proceso>();
    private static List<Proceso> Nuevos = new ArrayList<Proceso>();
    private static List<Proceso> Terminados = new ArrayList<Proceso>();
    private static Cpu cpu =  Cpu.getCpu();
    private static So so;
    private Despachador despachador;
    private double timeRetornoXtanda = 0;
    private double timeRetornoMedioXtanda = 0;

    private So() {}

    public static So getSo() {
        if(so == null) {
        	So so = new So();
        }       
        return so;
    }

    public void despachar() {
        // TODO implement here
    }

    public void cambiarPoliticaDeDespacho(Despachador politica) {
        // TODO implement here
    }

}