package Controller;

import org.json.simple.JSONObject;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

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

                    double progress = (procesosTerminados / (double) 6) * 2;
                    SwingUtilities.invokeLater(() -> iv.setProgressBar(progress));
                    System.out.println(procesosTerminados);//ACA HAY QUE EN LUGAR DE ACTUALIZAR POR NOMBRE DE LA LISTA, SERÁ CUANDO CADA PROCESO TERMINE
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e3) {
                        e3.printStackTrace();
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
         	input.setFileFilter(new FileNameExtensionFilter("JSON Files", "json"));
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

    @Override
    public void actionPerformed(ActionEvent e) {
    	CEV = new ControllerErroresView(iv);
    	buscarRutas(e);
        if (e.getSource() == iv.getBtnSimular()) {
        	if(CEV.validarTextField()) {
        		
                //encontrar y abrir archivo mediante la ruta SI LA RUTA NO COINCIDE O NO SE ENCUENTRA MOSTAR EXCEPCION CON JOptionPane.ERROR_MESSAGE
        		JSONParser parser = new JSONParser();

                try (FileReader reader = new FileReader(iv.getInputRutaText().getText())) {
                    // Parsear el archivo JSON
                    JSONArray jsonArray = (JSONArray) parser.parse(reader);

                    // Iterar sobre el arreglo JSON
                    for (Object obj : jsonArray) {
                        JSONObject jsonObject = (JSONObject) obj;

                        // Obtener los valores
                        String nombre = (String) jsonObject.get("nombre");
         

                        // Crear una instancia de Persona y agregarla a la lista
                        String n = nombre;
                    }
                    //acciones de cada proceso o acciones del cpu 

                    //actualizar la barra
                    actualizarProgress(6);

                 } catch (IOException | ParseException e1) {
                	 JOptionPane.showMessageDialog(iv, "La ruta de entrada es erronea", "Error", JOptionPane.ERROR_MESSAGE);
                 }
		
            } 
        	
        }
    }
}

