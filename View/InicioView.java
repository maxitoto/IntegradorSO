package View;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Cursor;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Dimension;
import javax.swing.JTextField;
import java.awt.Point;
import javax.swing.JProgressBar;
import javax.swing.ButtonGroup;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;



public class InicioView extends JFrame {
	private JButton btnSimular, btnInput, btnOutput;
	private JRadioButton rdbtnNewRadioButton, rdbtnNewRadioButton_1, rdbtnNewRadioButton_2, rdbtnNewRadioButton_3, rdbtnNewRadioButton_4;
	private JLabel lblPolticaDePlanificacin,lblQuantum;
	private static final long serialVersionUID = 1L;
	private JTextField InputRutaText;
	private JTextField OutputRutaText;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JProgressBar progressBar;
	private JTextField TIPtext;
	private JTextField TFPtext;
	private JTextField TCPtext;
	private JTextField Quantumtext;

	public InicioView() {
		setType(Type.UTILITY);
		setLocation(new Point(500, 500));
		setSize(new Dimension(450, 300));
		setTitle("Simulador de Bloque de Control de Procesos");
		
		btnSimular = new JButton("Simular");
		btnSimular.setBounds(64, 227, 86, 23);
		btnSimular.setSelected(true);
		
		rdbtnNewRadioButton = new JRadioButton("First Come First Served");
		rdbtnNewRadioButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(Quantumtext.isVisible()){Quantumtext.setVisible(false); Quantumtext.setFocusable(false); lblQuantum.setVisible(false); lblQuantum.setFocusable(false);}
			}
		});
		buttonGroup.add(rdbtnNewRadioButton);
		rdbtnNewRadioButton.setSelected(true);
		rdbtnNewRadioButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		rdbtnNewRadioButton.setBounds(6, 107, 159, 23);
		
		rdbtnNewRadioButton_1 = new JRadioButton("Prioridad Externa");
		rdbtnNewRadioButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(Quantumtext.isVisible()){Quantumtext.setVisible(false); Quantumtext.setFocusable(false); lblQuantum.setVisible(false); lblQuantum.setFocusable(false);}
			}
		});
		buttonGroup.add(rdbtnNewRadioButton_1);
		rdbtnNewRadioButton_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		rdbtnNewRadioButton_1.setBounds(167, 107, 137, 23);
		
		rdbtnNewRadioButton_2 = new JRadioButton("Round-Robin");
		rdbtnNewRadioButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(!Quantumtext.isVisible()){Quantumtext.setVisible(true); Quantumtext.setFocusable(true); lblQuantum.setVisible(true); lblQuantum.setFocusable(true);}
			}
		});
		buttonGroup.add(rdbtnNewRadioButton_2);
		rdbtnNewRadioButton_2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		rdbtnNewRadioButton_2.setBounds(315, 107, 105, 23);
		
		rdbtnNewRadioButton_3 = new JRadioButton("Shortest Process Next");
		rdbtnNewRadioButton_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(Quantumtext.isVisible()){Quantumtext.setVisible(false); Quantumtext.setFocusable(false); lblQuantum.setVisible(false); lblQuantum.setFocusable(false);}
			}
		});
		buttonGroup.add(rdbtnNewRadioButton_3);
		rdbtnNewRadioButton_3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		rdbtnNewRadioButton_3.setBounds(60, 133, 159, 23);
		
		lblPolticaDePlanificacin = new JLabel("Política de Planificación");
		lblPolticaDePlanificacin.setBounds(6, 84, 414, 16);
		lblPolticaDePlanificacin.setHorizontalAlignment(SwingConstants.CENTER);
		lblPolticaDePlanificacin.setFont(new Font("Tahoma", Font.BOLD, 13));
		getContentPane().setLayout(null);
		getContentPane().add(btnSimular);
		getContentPane().add(rdbtnNewRadioButton);
		getContentPane().add(rdbtnNewRadioButton_1);
		getContentPane().add(rdbtnNewRadioButton_2);
		getContentPane().add(rdbtnNewRadioButton_3);
		getContentPane().add(lblPolticaDePlanificacin);
		
		InputRutaText = new JTextField();
		InputRutaText.setBounds(93, 23, 268, 20);
		getContentPane().add(InputRutaText);
		InputRutaText.setColumns(10);
		
		btnInput = new JButton("Input");
		btnInput.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnInput.setSelected(true);
		btnInput.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnInput.setBounds(16, 20, 67, 23);
		getContentPane().add(btnInput);
		
		btnOutput = new JButton("Output");
		btnOutput.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnOutput.setSelected(true);
		btnOutput.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnOutput.setBounds(16, 54, 67, 23);
		getContentPane().add(btnOutput);
		
		OutputRutaText = new JTextField();
		OutputRutaText.setBounds(93, 55, 268, 20);
		getContentPane().add(OutputRutaText);
		OutputRutaText.setColumns(10);
		
		rdbtnNewRadioButton_4 = new JRadioButton("Shortest Remaining Time Next");
		rdbtnNewRadioButton_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(Quantumtext.isVisible()){Quantumtext.setVisible(false); Quantumtext.setFocusable(false); lblQuantum.setVisible(false); lblQuantum.setFocusable(false);}
			}
		});
		buttonGroup.add(rdbtnNewRadioButton_4);
		rdbtnNewRadioButton_4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		rdbtnNewRadioButton_4.setBounds(221, 133, 199, 23);
		getContentPane().add(rdbtnNewRadioButton_4);
		
		progressBar = new JProgressBar();
		progressBar.setStringPainted(true);
		progressBar.setValue(0);
		progressBar.setBounds(171, 227, 201, 20);
		getContentPane().add(progressBar);
		
		JLabel lblTIP = new JLabel("TIP");
		lblTIP.setBounds(70, 186, 24, 14);
		getContentPane().add(lblTIP);
		
		TIPtext = new JTextField();
		TIPtext.setBounds(93, 183, 24, 20);
		getContentPane().add(TIPtext);
		TIPtext.setColumns(10);
		
		JLabel lblTFP = new JLabel("TFP");
		lblTFP.setBounds(127, 186, 24, 14);
		getContentPane().add(lblTFP);
		
		TFPtext = new JTextField();
		TFPtext.setBounds(151, 183, 24, 20);
		getContentPane().add(TFPtext);
		TFPtext.setColumns(10);
		
		JLabel lblTCP = new JLabel("TCP");
		lblTCP.setBounds(190, 186, 24, 14);
		getContentPane().add(lblTCP);
		
		TCPtext = new JTextField();
		TCPtext.setBounds(213, 183, 24, 20);
		getContentPane().add(TCPtext);
		TCPtext.setColumns(10);
		
		Quantumtext = new JTextField();
		Quantumtext.setFocusable(false);
		Quantumtext.setVisible(false);
		Quantumtext.setColumns(10);
		Quantumtext.setBounds(337, 183, 24, 20);
		getContentPane().add(Quantumtext);
		
		lblQuantum = new JLabel("Quantum");
		lblQuantum.setFocusable(false);
		lblQuantum.setVisible(false);
		lblQuantum.setBounds(271, 186, 67, 14);
		getContentPane().add(lblQuantum);
	}

	public JTextField getTIPtext() {
		return TIPtext;
	}

	public JTextField getTFPtext() {
		return TFPtext;
	}

	public JTextField getTCPtext() {
		return TCPtext;
	}

	public JTextField getQuantumtext() {
		return Quantumtext;
	}

	public JButton getBtnSimular() {
		return btnSimular;
	}

	public JButton getBtnInput() {
		return btnInput;
	}

	public JButton getBtnOutput() {
		return btnOutput;
	}

	public JRadioButton getRdbtnNewRadioButton() {
		return rdbtnNewRadioButton;
	}

	public JRadioButton getRdbtnNewRadioButton_1() {
		return rdbtnNewRadioButton_1;
	}

	public JRadioButton getRdbtnNewRadioButton_2() {
		return rdbtnNewRadioButton_2;
	}

	public JRadioButton getRdbtnNewRadioButton_3() {
		return rdbtnNewRadioButton_3;
	}
	
	public JRadioButton getRdbtnNewRadioButton_4() {
		return rdbtnNewRadioButton_4;
	}

	public JTextField getInputRutaText() {
		return InputRutaText;
	}

	public JTextField getOutputRutaText() {
		return OutputRutaText;
	}

	public ButtonGroup getButtonGroup() {
		return buttonGroup;
	}

	public JProgressBar getProgressBar() {
		return progressBar;
	}
	
	public void setProgressBar(double progress) {
		this.progressBar.setValue((int) progress);
	}
}
