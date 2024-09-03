package Controller;

import javax.swing.JOptionPane;
import View.*;

public class ControllerErroresView {
	private static InicioView iv;
public ControllerErroresView(InicioView iv) { this.iv=iv;}
	
	public boolean validarTextField(){
		
		//comprobar TextField de ruta intput
		if(iv.getInputRutaText().getText().isEmpty() | iv.getInputRutaText().getText().isBlank()) {
			JOptionPane.showMessageDialog(iv, "La ruta de entrada está vacía", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		//comprobar TextField de ruta output
				if(iv.getOutputRutaText().getText().isBlank() | iv.getOutputRutaText().getText().isBlank()) {
					JOptionPane.showMessageDialog(iv, "La ruta de salida está vacía", "Error", JOptionPane.ERROR_MESSAGE);
					return false;
				}
		
		//comprobar TextField de TIP
		if(iv.getTIPtext().getText().isEmpty() | iv.getTIPtext().getText().isBlank()) {
			JOptionPane.showMessageDialog(iv, "Tiempo que utiliza el sistema operativo para aceptar los nuevos procesos\n esta vacio!", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}else {
			try {Double.parseDouble(iv.getTIPtext().getText());}
			catch(Exception e) {
					JOptionPane.showMessageDialog(iv, "Tiempo que utiliza el sistema operativo para aceptar los nuevos procesos\n probablemente no es un número!", "Advertencia", JOptionPane.WARNING_MESSAGE);
					return false;
		        }
			
		}
		
		//comprobar TextField de ruta TFP
		if(iv.getTFPtext().getText().isEmpty() | iv.getTFPtext().getText().isBlank()) {
			JOptionPane.showMessageDialog(iv, "Tiempo que utiliza el sistema operativo para aceptar los nuevos procesos\n esta vacio!", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}else {
			try {Double.parseDouble(iv.getTFPtext().getText());}
			catch(Exception e) {
					JOptionPane.showMessageDialog(iv, "Tiempo que utiliza el sistema operativo para terminar los procesos\n probablemente no es un número!", "Advertencia", JOptionPane.WARNING_MESSAGE);
					return false;
				}
					
				}
		
		//comprobar TextField de ruta TFP
		if(iv.getTCPtext().getText().isEmpty() | iv.getTCPtext().getText().isBlank()) {
			JOptionPane.showMessageDialog(iv, "Tiempo que utiliza el sistema operativo para aceptar los nuevos procesos\n esta vacio!", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}else {
			try {Double.parseDouble(iv.getTCPtext().getText());}
			catch(Exception e) {
					JOptionPane.showMessageDialog(iv, "Tiempo de conmutación entre procesos \n probablemente no es un número!", "Advertencia", JOptionPane.WARNING_MESSAGE);
					return false;
				}
					
				}
		
		
		//comprobar TextField de ruta TFP
		if(iv.getQuantumtext().getText().isEmpty() | iv.getQuantumtext().getText().isBlank()) {
			JOptionPane.showMessageDialog(iv, "Tiempo que utiliza el sistema operativo para aceptar los nuevos procesos\n esta vacio!", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}else {
			try {Double.parseDouble(iv.getQuantumtext().getText());}
			catch(Exception e) {
					JOptionPane.showMessageDialog(iv, "Quantum \n probablemente no es un número!", "Advertencia", JOptionPane.WARNING_MESSAGE);
					return false;
				}
					
				}
		
		return true;
	}
	
}
