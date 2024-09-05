package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.util.*;
import View.InicioView;
import Model.*;


public class inicioController implements ActionListener{
	private InicioView iv;
	private static ControllerErroresView CEV;
	private static JFileChooser input,output;
	private static So so;
	
	private static List<StringBuilder> datosDeDocumento = new ArrayList<StringBuilder>();
	private static int indx = 0;
	
	public inicioController(InicioView iv) {
        this.iv = iv;
        this.iv.getBtnInput().addActionListener(this);
        this.iv.getBtnOutput().addActionListener(this);
        this.iv.getBtnSimular().addActionListener(this);
        this.so =  So.getSo();
    }

    public void iniciar() {
        this.iv.setVisible(true);
    }
    
    public void actualizarProgress(int procesosTerminados) {
        Thread progressThread = new Thread(new Runnable() {
            @Override
            public void run() {
                double progress = (double) procesosTerminados / datosDeDocumento.size();
                SwingUtilities.invokeLater(() -> iv.setProgressBar(progress));
                try {
                    Thread.sleep(1000); 
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt(); 
                    e.printStackTrace();
                }
            }
        });
        progressThread.start();
    }
    
    private void buscarRutas(ActionEvent e) {
        Object source = e.getSource();
        
        if (source == iv.getBtnInput()) {
            input = new JFileChooser();
            System.out.println("Soy el botón Input");
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
            System.out.println("Soy el botón Output");
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
                StringBuilder contenido = new StringBuilder();
                String linea;

                while ((linea = reader.readLine()) != null) {
                    linea = linea.trim();  // Eliminar espacios en blanco
                    if (linea.contains("{")) {
                        contenido.setLength(0);  // Resetea contenido al encontrar un nuevo bloque
                        linea = linea.replace("{", ""); // Eliminar '{'
                    }
                    if (!linea.isEmpty() && !linea.contains("}")) {
                        contenido.append(linea).append("\n");
                    } else if (linea.contains("}")) {
                        datosDeDocumento.add(new StringBuilder(contenido));  // Agregar contenido
                    }
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(iv, "La ruta de entrada es errónea o el archivo no se encuentra", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void setOS() {
    	//datos del usuario
		this.so.setTimeXsoParaAceptarNuevosProcesos(Double.parseDouble(iv.getTIPtext().getText()));
		this.so.setTimeXsoParaTerminarProcesos(Double.parseDouble(iv.getTFPtext().getText()));
		this.so.settimeConmutaciónEntreprocesos(Double.parseDouble((iv.getTCPtext().getText())));
		if(iv.getQuantumtext().isVisible()) {
			this.so.setQuantum(Double.parseDouble((iv.getQuantumtext().getText())));
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
   
    public void mandarANuevo() {
		 So so = So.getSo();
		    StringBuilder content = datosDeDocumento.get(indx);
		    String[] datos = content.toString().trim().split("\n");
		    
		    if (datos.length >= 5) {
		        try {
		            Proceso p = new Proceso(
		                Integer.parseInt(datos[0].trim()),  // Asegúrate de eliminar espacios en blanco
		                Integer.parseInt(datos[1].trim()),
		                Integer.parseInt(datos[2].trim()),
		                Integer.parseInt(datos[3].trim()),
		                Integer.parseInt(datos[4].trim())
		            );
		            so.getNuevos().offer(p);
		        } catch (NumberFormatException e) {
		            System.out.println("Error al convertir los datos: " + e.getMessage());
		        }
		    } else {
		        System.out.println("Datos insuficientes para crear el proceso.");
		    }
		}
    
    @Override
    public void actionPerformed(ActionEvent e) {
    	CEV = new ControllerErroresView(iv);
    	buscarRutas(e);
        if (e.getSource() == iv.getBtnSimular()) {
        	if(CEV.validarTextField()) {
        		leerArchivoTxt();
        		setOS();
        		
        		while(indx!=datosDeDocumento.size()){//alamcena procesos nuevos
        			mandarANuevo();
        			indx++;
    			}
        		
        		int click = -1;
        		while(So.getTerminados().size()==datosDeDocumento.size()) {
        			click++;
        			so.getPolitica().controlarCpu();
        		}
        		
        		
            }

        		
            } 
        }
    }

