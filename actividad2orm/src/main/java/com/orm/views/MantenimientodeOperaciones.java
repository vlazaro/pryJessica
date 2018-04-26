package com.orm.views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import javax.swing.table.DefaultTableModel;

import com.orm.configuracion.AppConfig;
import com.orm.entity.CuentasBancarias;
import com.orm.entity.Operaciones;
import com.orm.service.CuentaBancariaService;
import com.orm.service.OperacionesService;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class MantenimientodeOperaciones extends JFrame {

	private JPanel contentPane;
	private JTextField textCantidad;
	private JTextField textNumCuenta;

	private OperacionesService ope;
	private CuentaBancariaService ctaservice;

	/**
	 * Create the frame.
	 */
	public MantenimientodeOperaciones() {
		AppConfig conf = new AppConfig();
		ope = new OperacionesService(conf.buildSessionFactory().openSession());
		ctaservice = new CuentaBancariaService(conf.buildSessionFactory().openSession());

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 398, 218);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(274, 135, 89, 23);
		contentPane.add(btnCancelar);

		JLabel lblNroCuenta = new JLabel("Nro Cuenta");
		lblNroCuenta.setBounds(33, 22, 75, 14);
		contentPane.add(lblNroCuenta);

		JLabel lblCantidad = new JLabel("Cantidad");
		lblCantidad.setBounds(33, 76, 46, 14);
		contentPane.add(lblCantidad);

		textCantidad = new JTextField();
		textCantidad.setBounds(118, 73, 86, 20);
		contentPane.add(textCantidad);
		textCantidad.setColumns(10);

		JButton btnIngresar = new JButton("Ingresar");
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Operaciones op = new Operaciones();

				CuentasBancarias cta = new CuentasBancarias();

				cta = ctaservice.consultarCuenta(textNumCuenta.getText());
				op.setCuentasbancaria(cta);
				op.setCantidad(Double.parseDouble(textCantidad.getText()));
				ope.insertarOperacion(op);
				limpiarPantalla();

			}
		});
		btnIngresar.setBounds(118, 135, 89, 23);
		contentPane.add(btnIngresar);

		textNumCuenta = new JTextField();
		textNumCuenta.setBounds(118, 19, 117, 20);
		contentPane.add(textNumCuenta);
		textNumCuenta.setColumns(10);
	}

	protected void limpiarPantalla() {
		this.textCantidad.setText("");
		this.textNumCuenta.setText("");

	}
}
