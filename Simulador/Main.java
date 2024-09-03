package Simulador;

import View.InicioView;
import Controller.inicioController;
import Model.So;

public class Main {
	public static void main(String[] args) {
		InicioView iv = new InicioView();
		inicioController ic = new inicioController(iv);
		ic.iniciar();
	}

}
