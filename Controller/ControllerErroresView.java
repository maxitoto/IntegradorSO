package Controller;

import javax.swing.JOptionPane;
import View.*;

public class ControllerErroresView {
    private static InicioView iv;

    public ControllerErroresView(InicioView iv) {
        this.iv = iv;
    }

    public boolean validarTextField() {
        // comprobar TextField de ruta input
        if (iv.getInputRutaText().getText().isEmpty() || iv.getInputRutaText().getText().isBlank()) {
            JOptionPane.showMessageDialog(iv, "La ruta de entrada está vacía", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // comprobar TextField de ruta output
        if (iv.getOutputRutaText().getText().isEmpty() || iv.getOutputRutaText().getText().isBlank()) {
            JOptionPane.showMessageDialog(iv, "La ruta de salida está vacía", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // comprobar TextField de TIP
        if (iv.getTIPtext().getText().isEmpty() || iv.getTIPtext().getText().isBlank()) {
            JOptionPane.showMessageDialog(iv, "Tiempo que utiliza el sistema operativo para aceptar los nuevos procesos\n está vacío!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        } else {
            try {
                double tip = Double.parseDouble(iv.getTIPtext().getText());
                if (tip <= 0) {
                    JOptionPane.showMessageDialog(iv, "Tiempo que utiliza el sistema operativo para aceptar los nuevos procesos\n debe ser mayor a 0!", "Advertencia", JOptionPane.WARNING_MESSAGE);
                    return false;
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(iv, "Tiempo que utiliza el sistema operativo para aceptar los nuevos procesos\n probablemente no es un número!", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return false;
            }
        }

        // comprobar TextField de TFP
        if (iv.getTFPtext().getText().isEmpty() || iv.getTFPtext().getText().isBlank()) {
            JOptionPane.showMessageDialog(iv, "Tiempo que utiliza el sistema operativo para terminar los procesos\n está vacío!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        } else {
            try {
                double tfp = Double.parseDouble(iv.getTFPtext().getText());
                if (tfp <= 0) {
                    JOptionPane.showMessageDialog(iv, "Tiempo que utiliza el sistema operativo para terminar los procesos\n debe ser mayor a 0!", "Advertencia", JOptionPane.WARNING_MESSAGE);
                    return false;
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(iv, "Tiempo que utiliza el sistema operativo para terminar los procesos\n probablemente no es un número!", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return false;
            }
        }

        // comprobar TextField de TCP
        if (iv.getTCPtext().getText().isEmpty() || iv.getTCPtext().getText().isBlank()) {
            JOptionPane.showMessageDialog(iv, "Tiempo de conmutación entre procesos\n está vacío!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        } else {
            try {
                double tcp = Double.parseDouble(iv.getTCPtext().getText());
                if (tcp <= 0) {
                    JOptionPane.showMessageDialog(iv, "Tiempo de conmutación entre procesos\n debe ser mayor a 0!", "Advertencia", JOptionPane.WARNING_MESSAGE);
                    return false;
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(iv, "Tiempo de conmutación entre procesos\n probablemente no es un número!", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return false;
            }
        }

        // comprobar TextField de Quantum
        if (iv.getQuantumtext().isVisible()) {
            if (iv.getQuantumtext().getText().isEmpty() || iv.getQuantumtext().getText().isBlank()) {
                JOptionPane.showMessageDialog(iv, "Quantum\n está vacío!", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            } else {
                try {
                    double quantum = Double.parseDouble(iv.getQuantumtext().getText());
                    if (quantum <= 0) {
                        JOptionPane.showMessageDialog(iv, "Quantum\n debe ser mayor a 0!", "Advertencia", JOptionPane.WARNING_MESSAGE);
                        return false;
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(iv, "Quantum\n probablemente no es un número!", "Advertencia", JOptionPane.WARNING_MESSAGE);
                    return false;
                }
            }
        }

        return true;
    }
}
