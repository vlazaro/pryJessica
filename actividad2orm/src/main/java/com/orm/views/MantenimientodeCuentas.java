package com.orm.views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.orm.configuracion.AppConfig;
import com.orm.entity.CuentasBancarias;
import com.orm.entity.Propietario;
import com.orm.service.CuentaBancariaService;
import com.orm.service.PropietariosService;

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
	JButton btnNuevo;

	private CuentaBancariaService cta;
	private PropietariosService propservice;

	/**
	 * Create the frame.
	 */
	public MantenimientodeCuentas() {
		AppConfig conf = new AppConfig();
		cta = new CuentaBancariaService(conf.buildSessionFactory().openSession());
		propservice = new PropietariosService(conf.buildSessionFactory().openSession());

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

		btnNuevo = new JButton("Nuevo");
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if ("Nuevo".equals(btnNuevo.getText())) {
					limpiarPantalla();
					btnNuevo.setText("Grabar");
					btnModificar.setEnabled(false);
					btnEliminar.setEnabled(false);

				} else {
					CuentasBancarias cuenta = new CuentasBancarias();
					cuenta.setNumcuenta(textNumCuenta.getText());
					Double saldo = Double.parseDouble(textSaldo.getText());
					Propietario prop = new Propietario();
					prop.setDni(textDni_Propietario.getText());
					prop = propservice.consultarPropietarioByDni(prop);
					cuenta.setPropietario(prop);
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

				CuentasBancarias cuenta = new CuentasBancarias();

				cuenta = cta.consultarCuenta(textNumCuenta.getText());
				String s = String.valueOf(cuenta.getSaldo());
				textSaldo.setText(s);
				textDni_Propietario.setText(cuenta.getPropietario().getDni());

				btnModificar.setEnabled(true);
				btnEliminar.setEnabled(true);
				btnNuevo.setText("Nuevo");

			}
		});
		btnBuscar.setBounds(349, 25, 89, 23);
		contentPane.add(btnBuscar);

		btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				CuentasBancarias cuenta = new CuentasBancarias();
				cuenta.setNumcuenta(textNumCuenta.getText());
				double saldo = Double.parseDouble(textSaldo.getText());
				cuenta.setSaldo(saldo);
				// cuenta.setPropietario(propietario);(textDni_Propietario.getText());
				cta.modificarCuenta(cuenta);
				btnModificar.setEnabled(false);
				limpiarPantalla();

			}
		});
		btnModificar.setBounds(111, 158, 89, 23);
		contentPane.add(btnModificar);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CuentasBancarias cuenta = new CuentasBancarias();
				cuenta.setNumcuenta(textNumCuenta.getText());
				cta.eliminarCuenta(cuenta);
				btnModificar.setEnabled(false);
				btnEliminar.setEnabled(false);
				limpiarPantalla();

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
