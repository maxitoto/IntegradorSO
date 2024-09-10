package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.util.*;
import View.InicioView;
import Model.*;
import java.io.*;
import javax.swing.*;

public class inicioController implements ActionListener {
    private InicioView iv;
    private static ControllerErroresView CEV;
    public static JFileChooser input, output;
    private static So so;
    private List<String> datosDeDocumento;
    private static int textCont=0;

    public inicioController(InicioView iv) {
        this.iv = iv;
        this.CEV = new ControllerErroresView(iv);
        this.iv.getBtnInput().addActionListener(this);
        this.iv.getBtnOutput().addActionListener(this);
        this.iv.getBtnSimular().addActionListener(this);
        datosDeDocumento = new ArrayList<String>();
    }

    public void iniciar() {
        this.iv.setVisible(true);
    }

    public void actualizarProgress(boolean reset) {
    	if (reset) {
    		double progress =0;
            SwingUtilities.invokeLater(() -> iv.setProgressBar(progress));
    	}
    	else {
        double progress = (double) (So.getTerminados().size() / datosDeDocumento.size()) * 100;
        SwingUtilities.invokeLater(() -> iv.setProgressBar(progress));
    	}
    }

    private void buscarRutas(ActionEvent e) {
        Object source = e.getSource();

        if (source == iv.getBtnInput()) {
            input = new JFileChooser();
            input.setFileSelectionMode(JFileChooser.FILES_ONLY);
            input.setFileFilter(new FileNameExtensionFilter("Text Files", "txt"));
            int result = input.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = input.getSelectedFile();
                iv.getInputRutaText().setText(selectedFile.getAbsolutePath());
            }
        }

        if (source == iv.getBtnOutput()) {
            output = new JFileChooser();
            output.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int result = output.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedDirectory = output.getSelectedFile();
                iv.getOutputRutaText().setText(selectedDirectory.getAbsolutePath());
            }
        }
    }

    public void leerArchivoTxt() {
    	datosDeDocumento.clear();
        try {
            File selectedFile = this.input.getSelectedFile();
            if (selectedFile == null) {
                throw new IOException("No se ha seleccionado ningún archivo.");
            }

            try (BufferedReader reader = new BufferedReader(new FileReader(selectedFile))) {
                String linea;

                while ((linea = reader.readLine()) != null) {
                    linea = linea.trim();
                    if (!linea.isEmpty()) {
                        // Agregamos cada línea a la cola de prioridad
                        datosDeDocumento.add(linea);
                    }
                }
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(iv, "Error al leer el archivo o en el formato de los datos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void setOS() {
    	//datos del usuario
    	So.setTip(Integer.parseInt(iv.getTIPtext().getText()));
		So.setTfp(Integer.parseInt(iv.getTFPtext().getText()));
		So.setTcp(Integer.parseInt((iv.getTCPtext().getText())));
		if(iv.getQuantumtext().isVisible()) {
			So.setQ(Integer.parseInt((iv.getQuantumtext().getText())));
		}else {So.setQ(0);}
		
		//set politica
		if (iv.getRdbtnNewRadioButton().isSelected()) {
		    this.so.setPolitica(new FCFS());
		} else if (iv.getRdbtnNewRadioButton_1().isSelected()) {
		    this.so.setPolitica(new PrioridadExterna());
		} else if (iv.getRdbtnNewRadioButton_2().isSelected()) {
		    this.so.setPolitica(new RoundRobin());
		} else if (iv.getRdbtnNewRadioButton_3().isSelected()) {
		    this.so.setPolitica(new SPN());
		} else if (iv.getRdbtnNewRadioButton_4().isSelected()) {
		    this.so.setPolitica(new SRTN());
		} 
    }
   
    public void reset() {
        So.reset();  
        System.gc();  
    }
    
    public void cargarProcesosEnNuevos() {
    	for (String string : datosDeDocumento) {
    		String[] datos = string.split(",");
    		Proceso proceso = new Proceso(
                    datos[0],
                    Integer.parseInt(datos[1]),
                    Integer.parseInt(datos[2]),
                    Integer.parseInt(datos[3]),
                    Integer.parseInt(datos[4]),
                    Integer.parseInt(datos[5])
            );
    		So.getNuevos().offer(proceso);
		}
    	
    }
    
    public static void pv(String texto) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(output.getSelectedFile() + "/registro"+textCont+".txt", true))) {
            writer.write(texto); 
            writer.newLine(); 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String formatProcessList(List<Proceso> processes) {
        StringBuilder formatted = new StringBuilder();
        int spacing = 30; // Ajusta el valor del espacio para alinear correctamente
        
        if (processes.isEmpty()) {
            formatted.append("[]");
        } else {
            formatted.append("[{\n");

            // Formateo para los encabezados
            formatted.append(String.format("%-" + spacing + "s", "Proceso"));
            formatted.append(String.format("%-" + spacing + "s", "Tiempo Arribo"));
            formatted.append(String.format("%-" + spacing + "s", "Rafagas de CPU"));
            formatted.append(String.format("%-" + spacing + "s", "Duración Rafaga"));
            formatted.append(String.format("%-" + spacing + "s", "Duración Bloqueo"));
            formatted.append(String.format("%-" + spacing + "s", "Prioridad"));
            formatted.append(String.format("%-" + spacing + "s", "Rafagas Actuales"));
            formatted.append(String.format("%-" + spacing + "s", "Tiempo Actual Rafaga"));
            formatted.append(String.format("%-" + spacing + "s", "Tiempo Bloqueado"));
            formatted.append(String.format("%-" + spacing + "s", "Tiempo Retorno"));
            formatted.append(String.format("%-" + spacing + "s", "Retorno Normalizado"));
            formatted.append(String.format("%-" + spacing + "s", "Tiempo en Listo"));
            formatted.append(String.format("%-" + spacing + "s", "Suma TCP"));
            formatted.append(String.format("%-" + spacing + "s", "Recursos"));
            formatted.append("\n");

            // Formateo para cada proceso
            for (Proceso p : processes) {
                formatted.append(String.format("%-" + spacing + "s", "            " + p.getId()));
                formatted.append(String.format("%-" + spacing + "s", "            " + p.getTiempoDeArribo()));
                formatted.append(String.format("%-" + spacing + "s", "            " + p.getRafagasDeCpuParaTerminar()));
                formatted.append(String.format("%-" + spacing + "s", "            " + p.getDuracionDeCadaRafaga()));
                formatted.append(String.format("%-" + spacing + "s","            " +  p.getDuracionDePeriodoEnBloqueado()));
                formatted.append(String.format("%-" + spacing + "s", "            " + p.getPrioridad()));
                formatted.append(String.format("%-" + spacing + "s", "            " + p.getContRafagasActuales()));
                formatted.append(String.format("%-" + spacing + "s","            " +  p.getTiempoActualDeRafaga()));
                formatted.append(String.format("%-" + spacing + "s", "            " + p.getTiempoEnEstadoBloqueado()));
                formatted.append(String.format("%-" + spacing + "s", "            " + p.getTimeRetorno()));
                formatted.append(String.format("%-" + spacing + "s", "            " + p.getTimeRetornoNormalizado()));
                formatted.append(String.format("%-" + spacing + "s", "            " + p.getTimeEnListo()));
                formatted.append(String.format("%-" + spacing + "s", "            " + p.getSumaDeTCPDesdeQueFuiCreado()));
                formatted.append(String.format("%-" + spacing + "s", "            " + p.isTengoLosRecursos()));
                formatted.append("\n");
            }

            formatted.append("}]");
        }
        return formatted.toString();
    }
    public static String formatSingleProcess(Proceso p) {
        if (p == null) {
            return "null";
        }
        
        StringBuilder formatted = new StringBuilder();
        int spacing = 30; // Ajusta el valor del espacio para alinear correctamente
        
     // Formateo para los encabezados
        formatted.append(String.format("%-" + spacing + "s", "Proceso"));
        formatted.append(String.format("%-" + spacing + "s", "Tiempo Arribo"));
        formatted.append(String.format("%-" + spacing + "s", "Rafagas de CPU"));
        formatted.append(String.format("%-" + spacing + "s", "Duración Rafaga"));
        formatted.append(String.format("%-" + spacing + "s", "Duración Bloqueo"));
        formatted.append(String.format("%-" + spacing + "s", "Prioridad"));
        formatted.append(String.format("%-" + spacing + "s", "Rafagas Actuales"));
        formatted.append(String.format("%-" + spacing + "s", "Tiempo Actual Rafaga"));
        formatted.append(String.format("%-" + spacing + "s", "Tiempo Bloqueado"));
        formatted.append(String.format("%-" + spacing + "s", "Tiempo Retorno"));
        formatted.append(String.format("%-" + spacing + "s", "Retorno Normalizado"));
        formatted.append(String.format("%-" + spacing + "s", "Tiempo en Listo"));
        formatted.append(String.format("%-" + spacing + "s", "Suma TCP"));
        formatted.append(String.format("%-" + spacing + "s", "Recursos"));
        formatted.append("\n");
        
        // Formateo para un solo proceso
        formatted.append(String.format("%-" + spacing + "s", "            " + p.getId()));
        formatted.append(String.format("%-" + spacing + "s", "            " + p.getTiempoDeArribo()));
        formatted.append(String.format("%-" + spacing + "s", "            " + p.getRafagasDeCpuParaTerminar()));
        formatted.append(String.format("%-" + spacing + "s", "            " + p.getDuracionDeCadaRafaga()));
        formatted.append(String.format("%-" + spacing + "s", "            " + p.getDuracionDePeriodoEnBloqueado()));
        formatted.append(String.format("%-" + spacing + "s", "            " + p.getPrioridad()));
        formatted.append(String.format("%-" + spacing + "s", "            " + p.getContRafagasActuales()));
        formatted.append(String.format("%-" + spacing + "s", "            " + p.getTiempoActualDeRafaga()));
        formatted.append(String.format("%-" + spacing + "s", "            " + p.getTiempoEnEstadoBloqueado()));
        formatted.append(String.format("%-" + spacing + "s", "            " + p.getTimeRetorno()));
        formatted.append(String.format("%-" + spacing + "s", "            " + p.getTimeRetornoNormalizado()));
        formatted.append(String.format("%-" + spacing + "s", "            " + p.getTimeEnListo()));
        formatted.append(String.format("%-" + spacing + "s","            " +  p.getSumaDeTCPDesdeQueFuiCreado()));
        formatted.append(String.format("%-" + spacing + "s", "            " + p.isTengoLosRecursos()));
        
        return formatted.toString();
    }


    public static void registrarEstado() {
        Proceso ejec = So.getCpu().getEjecutando();
        String logEntry = 
            "Quan: " + So.getContquantum() + "\n" +
            "Clk: " + (So.CLK - 1) + "\n" +
            "Nuevos: " + formatProcessList((List)So.getNuevos()) + "\n" +
            "Listos: " + formatProcessList((List)So.getListos()) + "\n" +
            "Block: " + formatProcessList((List)So.getBloqueados()) + "\n" +
            "Ejec: " + formatSingleProcess(ejec) + "\n" +
            "Term: " + formatProcessList((List)So.getTerminados()) + "\n";

        pv(logEntry); 
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        buscarRutas(e);
        if (e.getSource() == iv.getBtnSimular()) {
            if (CEV.validarTextField()) {
                leerArchivoTxt();
                if (this.so != null) { reset(); } else { this.so = So.getSo(); }
                setOS();
                cargarProcesosEnNuevos();
                textCont++;
                actualizarProgress(true);
                

                inicioController.pv("Politica: " + So.getPolitica().toString() + "\n");
                

                while (So.getTerminados().size() < datosDeDocumento.size()) {
                    So.CLK++;
                    
     
                    registrarEstado();
                    

                    Cpu.getAuditor().aumentarContadores();
                    
 
                    if (So.getPolitica().cuandoPasarDeEjecutandoATerminado()) {}
                    if (So.getPolitica().cuandoPasarDeEjecutandoABloqueado()) {}
                    if (So.getPolitica().cuandoPasarDeEjecutandoAListo()) { So.getPolitica().ordenar(); }
                    if (So.getPolitica().cuandoPasarDeBloqueadoAListo()) { So.getPolitica().ordenar(); }
                    if (So.getPolitica().cuandoPasarDeNuevoAListo()) { So.getPolitica().ordenar(); }
                    if (So.getPolitica().cuandoPasarDeListoAEjecutando()) {}
                    

                    actualizarProgress(false);
                }
                
 
                registrarEstado();
                
 
                for (String string : Cpu.getAuditor().contabilidadFinal()) {
                    inicioController.pv(string);
                }
                
       
                inicioController.pv("\nT Cpu Ocioso: " + Cpu.getTimeOcioso() + "\n");
                inicioController.pv("T Cpu UsoxProcesos: " + Cpu.getTimeUsoXprocesos() + "\n");
                inicioController.pv("T Cpu UsoxSo: " + Cpu.gettUsadaPorSO() + "\n");
            }
        }
    }
}


