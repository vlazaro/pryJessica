package actividad1.views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import actividad1.AppConfig;
import actividad1.domain.CuentaBancaria;
import actividad1.domain.Propietario;
import actividad1.servicios.CuentaBancariaService;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class MantenimientodeCuentas extends JFrame {

	private JPanel contentPane;
	private JTextField textNumCuenta;
	private JTextField textSaldo;
	private JTextField textDni_Propietario;
	JButton btnModificar;
	JButton btnEliminar;
	
	private CuentaBancariaService cta;
	
	private AppConfig gestor = new AppConfig();
	
		/**
	 * Create the frame.
	 */
	public MantenimientodeCuentas() {
		
		cta = new CuentaBancariaService(gestor.getConn());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 479, 239);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Numero de Cuenta: ");
		lblNewLabel.setBounds(7, 26, 111, 20);
		contentPane.add(lblNewLabel);
		
		textNumCuenta = new JTextField();
		textNumCuenta.setBounds(134, 26, 205, 20);
		contentPane.add(textNumCuenta);
		textNumCuenta.setColumns(10);
		
		JLabel lblSaldo = new JLabel("Saldo :");
		lblSaldo.setBounds(7, 69, 65, 20);
		contentPane.add(lblSaldo);
		
		JLabel lblDnipropietario = new JLabel("Dni_Propietario : ");
		lblDnipropietario.setBounds(10, 115, 89, 14);
		contentPane.add(lblDnipropietario);
		
		textSaldo = new JTextField();
		textSaldo.setBounds(134, 69, 86, 20);
		contentPane.add(textSaldo);
		textSaldo.setColumns(10);
		
		textDni_Propietario = new JTextField();
		textDni_Propietario.setBounds(134, 112, 111, 20);
		contentPane.add(textDni_Propietario);
		textDni_Propietario.setColumns(10);
		
		JButton btnNuevo = new JButton("Nuevo");
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if ("Nuevo".equals(btnNuevo.getText())) {
					
					limpiarPantalla();
					
					btnNuevo.setText("Grabar");
					btnModificar.setEnabled(false);
					btnEliminar.setEnabled(false);
					
					
				
				}else {
					CuentaBancaria cuenta = new CuentaBancaria();
					cuenta.setNum_cuenta(textNumCuenta.getText());
					Double saldo =  Double.parseDouble(textSaldo.getText());
					cuenta.setId_propietario(textDni_Propietario.getText());
					cuenta.setSaldo(saldo);
					cta.insertarCuenta(cuenta);
					limpiarPantalla();
					btnNuevo.setText("Nuevo");
					
				}
				
				
							
			}

			
		});
		btnNuevo.setBounds(7, 158, 89, 23);
		contentPane.add(btnNuevo);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(319, 158, 89, 23);
		contentPane.add(btnCancelar);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				CuentaBancaria cuenta = new CuentaBancaria();
				try {
				cuenta=cta.consultarCuenta(textNumCuenta.getText());
				String s = String.valueOf(cuenta.getSaldo());
				textSaldo.setText(s);
				textDni_Propietario.setText(cuenta.getId_propietario());
				
				btnModificar.setEnabled(true);
				btnEliminar.setEnabled(true);
				btnNuevo.setText("Nuevo");
						   
				
				
			}catch(SQLException ex) {
				ex.printStackTrace();
			}
			}
		});
		btnBuscar.setBounds(349, 25, 89, 23);
		contentPane.add(btnBuscar);
		
		btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				CuentaBancaria cuenta= new CuentaBancaria();
				cuenta.setNum_cuenta(textNumCuenta.getText());
				double saldo= Double.parseDouble(textSaldo.getText());
				cuenta.setSaldo(saldo);
				cuenta.setId_propietario(textDni_Propietario.getText());
								
				
				try {
					cta.modificarCuenta(cuenta);
					btnModificar.setEnabled(false);
					limpiarPantalla();
					
				}catch(SQLException e1) {
				
					e1.printStackTrace();
				}
				
				
				
				
			}
		});
		btnModificar.setBounds(111, 158, 89, 23);
		contentPane.add(btnModificar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CuentaBancaria cuenta =new CuentaBancaria();
				
				cuenta.setId_propietario(textDni_Propietario.getText());
				try {
					cuenta.setNum_cuenta(textNumCuenta.getText());
					cta.eliminarCuenta(cuenta);
					btnModificar.setEnabled(false);
					btnEliminar.setEnabled(false);
					limpiarPantalla();
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
				
				
			}
		});
		btnEliminar.setBounds(215, 158, 89, 23);
		contentPane.add(btnEliminar);
	}
	
	private void limpiarPantalla() {
		
		textNumCuenta.setText("");
		textSaldo.setText("");
		textDni_Propietario.setText("");
		
		
	}
}
