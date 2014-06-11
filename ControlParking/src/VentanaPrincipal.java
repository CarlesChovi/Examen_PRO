import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class VentanaPrincipal extends JFrame {

	//Componentes visuales
	private JPanel contentPane;
	private JTextField matriculaTxt;
	private JTextField horaEntradaTxt;
	private JTextField horaSalidaTxt;
	private JTextField precioPagarTxt;
	private Coche coche;
	
	private JComboBox<Coche> coches;
	private DB baseDatos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal frame = new VentanaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	
	/**
	 * Create the frame.
	 */
	public VentanaPrincipal() {
		setTitle("Control de Parking");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 241);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		coches =  new JComboBox<Coche>();
		coches.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			//seleccionar la posicion para devolver los datos
			coche=coches.getItemAt(coches.getSelectedIndex());
			if(coche!=null)
			{					
				//coche=coches.getItemAt(coches.getSelectedIndex());
				// Muestre los datos
				matriculaTxt.setText(String.valueOf(coche.getmatr()));
				horaEntradaTxt.setText(String.valueOf(coche.getentrada()));
				horaSalidaTxt.setText(String.valueOf(coche.getsalida()));
				precioPagarTxt.setText(String.valueOf(coche.getprecio()));
				
			}
		}
		});
		coches.setBounds(10, 29, 414, 20);
		contentPane.add(coches);
		
		JLabel lblListadoDeCoches = new JLabel("Listado de coches");
		lblListadoDeCoches.setBounds(10, 4, 151, 14);
		contentPane.add(lblListadoDeCoches);
		
		JLabel lblMatrcula = new JLabel("Matr\u00EDcula");
		lblMatrcula.setBounds(10, 67, 89, 14);
		contentPane.add(lblMatrcula);
		
		JLabel lblHoraDeEntrada = new JLabel("Hora de entrada");
		lblHoraDeEntrada.setBounds(10, 100, 101, 14);
		contentPane.add(lblHoraDeEntrada);
		
		JLabel lblHoraDeSalida = new JLabel("Hora de salida");
		lblHoraDeSalida.setBounds(10, 136, 101, 14);
		contentPane.add(lblHoraDeSalida);
		
		JLabel lblPrecioAPagar = new JLabel("Precio a pagar");
		lblPrecioAPagar.setBounds(10, 172, 101, 14);
		contentPane.add(lblPrecioAPagar);
		
		matriculaTxt = new JTextField();
		matriculaTxt.setBounds(119, 60, 86, 20);
		contentPane.add(matriculaTxt);
		matriculaTxt.setColumns(10);
		
		horaEntradaTxt = new JTextField();
		horaEntradaTxt.setColumns(10);
		horaEntradaTxt.setBounds(119, 97, 86, 20);
		contentPane.add(horaEntradaTxt);
		
		horaSalidaTxt = new JTextField();
		horaSalidaTxt.setColumns(10);
		horaSalidaTxt.setBounds(119, 133, 86, 20);
		contentPane.add(horaSalidaTxt);
		
		precioPagarTxt = new JTextField();
		precioPagarTxt.setEditable(false);
		precioPagarTxt.setColumns(10);
		precioPagarTxt.setBounds(119, 169, 86, 20);
		contentPane.add(precioPagarTxt);
		
		JButton btnNewButton = new JButton("A PAGAR");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Aquí realizaremos los siguientes pasos
				//1.- Comprobaremos que todos los campos están completados

				if((matriculaTxt.getText()).equals("")){
					JOptionPane.showMessageDialog(null,"No has indicado la matricula");
				}
				else if ((horaEntradaTxt.getText()).equals("")){
					JOptionPane.showMessageDialog(null,"La hora de entrada debe estar comprendida entre 0 y 23");
				}
				else if ((horaSalidaTxt.getText()).equals("")){
					JOptionPane.showMessageDialog(null,"La hora de salida debe estar comprendida entre 0 y 23");
				}else  {
					//2.- Creamos un nuevo objeto
					
					Coche coch=new Coche();
					int ID=1;
					//ID=baseDatos.insertarCoche((matriculaTxt.getText()), Integer.parseInt(horaEntradaTxt.getText()), Integer.parseInt(horaSalidaTxt.getText()), Integer.parseInt(precioPagarTxt.getText()));
					coch.setmatr(matriculaTxt.getText());
					coch.setentrada(Integer.parseInt(horaEntradaTxt.getText()));
					coch.setsalida(Integer.parseInt(horaSalidaTxt.getText()));
					
				
				//3.- Lo almacenaremos en el ComboBox
				coches.addItem(coch);
				
				
				//Hacemos la operación
					try{
					
					Object aTxt = horaEntradaTxt.getText();
					float a = Float.parseFloat((String) aTxt);
					
					Object bTxt = horaSalidaTxt.getText();
					float b = Float.parseFloat((String) bTxt);
					
					Object operacion = (b-a)*2;
					
					String opTxt = String.valueOf(operacion);
					
					precioPagarTxt.setText(opTxt);
					
				} // fin de try
				catch( NumberFormatException excepcion ) // excepción lanzada en lanzaExcepcion
				{
								
				System.err.println("Información no valida");
				
				}	// fin de catch
				
				}
			}
		});
				
		btnNewButton.setBounds(230, 58, 194, 128);
		contentPane.add(btnNewButton);
		
		baseDatos=new DB(coches);
	}
}
