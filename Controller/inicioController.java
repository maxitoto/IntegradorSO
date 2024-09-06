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
	
	List<String> datosDeDocumento = new ArrayList<String>();
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
    
    
    public void actualizarProgress() {
        double progress = (double) (So.getTerminados().size() / datosDeDocumento.size() ) * 100;
        SwingUtilities.invokeLater(() -> iv.setProgressBar(progress));
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
                String linea;

                while ((linea = reader.readLine()) != null) {
                    linea = linea.trim();
                    if (!linea.isEmpty()) {
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
    	So.setTimeXsoParaAceptarNuevosProcesos(Integer.parseInt(iv.getTIPtext().getText()));
		So.setTimeXsoParaTerminarProcesos(Integer.parseInt(iv.getTFPtext().getText()));
		So.setTimeConmutaciónEntreprocesos(Integer.parseInt((iv.getTCPtext().getText())));
		if(iv.getQuantumtext().isVisible()) {
			So.setQuantum(Integer.parseInt((iv.getQuantumtext().getText())));
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

        if (indx < 0 || indx >= datosDeDocumento.size()) {
            System.out.println("Índice fuera de rango.");
            return;
        }

        String content = datosDeDocumento.get(indx);  // Obtener el registro de proceso en el índice dado
        String[] datos = content.trim().split(",");  // Dividir el contenido por comas

        // Verificar que haya suficientes datos para crear un proceso
        if (datos.length >= 6) {  // Cambiado a >= 6 ya que hay 6 campos esperados
            try {
                // Crear una nueva instancia de Proceso con los datos convertidos y procesados
                Proceso p = new Proceso(
                    datos[0].trim(),  // Nombre del proceso
                    Integer.parseInt(datos[1].trim()),  // Tiempo de arribo
                    Integer.parseInt(datos[2].trim()),  // Ráfagas de CPU
                    Integer.parseInt(datos[3].trim()),  // Duración de ráfagas de CPU
                    Integer.parseInt(datos[4].trim()),  // Duración de ráfagas de I/O
                    Integer.parseInt(datos[5].trim())   // Prioridad
                );

                // Insertar el proceso en la cola 'Nuevos'
                So.getNuevos().offer(p);

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
            if (CEV.validarTextField()) {
                leerArchivoTxt();
                setOS();

                
                while (indx != datosDeDocumento.size()) {
                    mandarANuevo();
                    indx++;
                }

                


                while (So.getTerminados().size() < datosDeDocumento.size()) {
                   So.click++;
                    so.getPolitica().accionDePolitica();
                    System.out.println(
                    		"Reloj: " + So.click +  "\n" +
                    	    "Nuevos: " + So.getNuevos().toString() + "\n" +
                    	    "Listos: " + So.getListos().toString() + "\n" +
                    	    "Bloqueados: " + So.getBloqueados().toString() + "\n" +
                    	    "Ejecutando: " + (So.getCpu().getEjecutando() == null ? "null" : So.getCpu().getEjecutando().toString()) + "\n" +
                    	    "Terminados: " + So.getTerminados().toString() + "\n"
                    	);
                    
                    try {
						Thread.sleep(3000);
					} catch (Exception e2) {
						// TODO: handle exception
					}
                    
                }
            }
        }
    }
    }

