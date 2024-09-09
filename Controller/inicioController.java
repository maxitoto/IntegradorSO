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

    public inicioController(InicioView iv) {
        this.iv = iv;
        this.iv.getBtnInput().addActionListener(this);
        this.iv.getBtnOutput().addActionListener(this);
        this.iv.getBtnSimular().addActionListener(this);
        this.so = So.getSo();
        datosDeDocumento = new ArrayList<String>();
    }

    public void iniciar() {
        this.iv.setVisible(true);
    }

    public void actualizarProgress() {
        double progress = (double) (So.getTerminados().size() / datosDeDocumento.size()) * 100;
        SwingUtilities.invokeLater(() -> iv.setProgressBar(progress));
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
		}
		
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
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(output.getSelectedFile() + "/registro.txt", true))) {
            writer.write(texto); // Escribe el texto
            writer.newLine(); // Añade una línea en blanco
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        CEV = new ControllerErroresView(iv);
        buscarRutas(e);

        if (e.getSource() == iv.getBtnSimular()) {
            if (CEV.validarTextField()) {
                leerArchivoTxt();
                setOS();
                cargarProcesosEnNuevos();
                while(So.getTerminados().size()<datosDeDocumento.size()) {
        			So.CLK++;
        			//inicioController.pv(So.CLK-1+"\n");
        			inicioController.pv(
        			       "  Quan: "+So.getContquantum()+"\n"+
        					(So.CLK-1)+"\n"+
        					"Nuevos: " + So.getNuevos() + "\n"+
        					"Listos: " + So.getListos() + "\n"+
        					"Block: " + So.getBloqueados() + "\n"+
        					"Ejec: " + (So.getCpu().getEjecutando() == null ? "null" : So.getCpu().getEjecutando())  + "\n"+
        					"Term: " + So.getTerminados() + "\n"
        					);

        			System.out.println(
        					"Clock: "+(So.CLK-1)+"  Quan: "+So.getContquantum()+"\n"+
        					"Nuevos: " + So.getNuevos() + "\n"+
        					"Listos: " + So.getListos() + "\n"+
        					"Block: " + So.getBloqueados() + "\n"+
        					"Ejec: " + (So.getCpu().getEjecutando() == null ? "null" : So.getCpu().getEjecutando())  + "\n"+
        					"Term: " + So.getTerminados() + "\n"
        					);
        			
        			Cpu.getAuditor().aumentarContadores(); 
        			
        	             
        			if(So.getPolitica().cuandoPasarDeEjecutandoATerminado()){}
        			if(So.getPolitica().cuandoPasarDeEjecutandoABloqueado()) { }
        			if(So.getPolitica().cuandoPasarDeEjecutandoAListo()){So.getPolitica().ordenar();  }
        			if(So.getPolitica().cuandoPasarDeBloqueadoAListo()) {So.getPolitica().ordenar();  }
        			if(So.getPolitica().cuandoPasarDeNuevoAListo()) {So.getPolitica().ordenar();}
        			if(So.getPolitica().cuandoPasarDeListoAEjecutando()){}
        			actualizarProgress();
        		}
        		    		
        		for (String string : Cpu.getAuditor().contabilidadFinal()) {
        			inicioController.pv(string);
        		}
        		
        		inicioController.pv("\n"+"T Cpu Ocioso: "+Cpu.getTimeOcioso()+"\n");
        		inicioController.pv("T Cpu UsoxProcesos: "+Cpu.getTimeUsoXprocesos()+"\n");
        		inicioController.pv("T Cpu UsoxSo: "+Cpu.gettUsadaPorSO()+"\n");
                         
       }
     }
   }
 }

