package com.orm.views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.orm.configuracion.AppConfig;
import com.orm.entity.Propietario;
import com.orm.service.PropietariosService;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MantenimientodePropietarios extends JFrame {

	private JPanel contentPane;
	private JTextField textDni;
	private JTextField textUsuario;
	private JTextField textNombre;
	private JTextField textPrimerApellido;
	private JTextField textSegundoApellido;
	private JTextField textNumeroSecreto;
	private JButton btnModificar;
	private JButton btnEliminar;
	JButton btnNuevo;

	private PropietariosService prop;


	/**
	 * Create the frame.
	 */
	public MantenimientodePropietarios() {
		AppConfig conf = new AppConfig();

		prop = new PropietariosService(conf.buildSessionFactory().openSession());

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 517, 407);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblUsuario = new JLabel("Dni :");
		lblUsuario.setBounds(21, 36, 46, 14);
		contentPane.add(lblUsuario);

		textDni = new JTextField();
		textDni.setBounds(162, 30, 123, 20);
		contentPane.add(textDni);
		textDni.setColumns(10);

		JLabel lblUsuario_1 = new JLabel("Usuario :");
		lblUsuario_1.setBounds(21, 73, 46, 14);
		contentPane.add(lblUsuario_1);

		textUsuario = new JTextField();
		textUsuario.setBounds(162, 67, 123, 20);
		contentPane.add(textUsuario);
		textUsuario.setColumns(10);

		JLabel lblNombre = new JLabel("Nombre: ");
		lblNombre.setBounds(21, 118, 46, 14);
		contentPane.add(lblNombre);

		textNombre = new JTextField();
		textNombre.setBounds(162, 112, 123, 20);
		contentPane.add(textNombre);
		textNombre.setColumns(10);

		JLabel lblPrimerApellido = new JLabel("Primer Apellido: ");
		lblPrimerApellido.setBounds(21, 158, 96, 14);
		contentPane.add(lblPrimerApellido);

		textPrimerApellido = new JTextField();
		textPrimerApellido.setBounds(162, 152, 123, 20);
		contentPane.add(textPrimerApellido);
		textPrimerApellido.setColumns(10);

		JLabel lblSegundoApellido = new JLabel("Segundo Apellido: ");
		lblSegundoApellido.setBounds(21, 197, 96, 20);
		contentPane.add(lblSegundoApellido);

		textSegundoApellido = new JTextField();
		textSegundoApellido.setBounds(162, 194, 123, 20);
		contentPane.add(textSegundoApellido);
		textSegundoApellido.setColumns(10);

		JLabel lblNewLabel = new JLabel("N\u00FAmero Secreto :");
		lblNewLabel.setBounds(21, 245, 96, 14);
		contentPane.add(lblNewLabel);

		textNumeroSecreto = new JTextField();
		textNumeroSecreto.setColumns(10);
		textNumeroSecreto.setBounds(162, 239, 123, 20);
		contentPane.add(textNumeroSecreto);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(402, 313, 89, 23);
		contentPane.add(btnCancelar);

		btnNuevo = new JButton("Nuevo");
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if ("Nuevo".equals(btnNuevo.getText())) {
					limpiarPantalla();
					btnNuevo.setText("Grabar");
					btnModificar.setEnabled(false);
					btnEliminar.setEnabled(false);
					textDni.setEnabled(true);

				} else {
					Propietario pro = new Propietario();
					pro.setDni(textDni.getText());
					pro.setUsuario(textUsuario.getText());
					pro.setNombre(textNombre.getText());
					pro.setNumerosecreto(textNumeroSecreto.getText());
					pro.setPrimerapellido(textPrimerApellido.getText());
					pro.setSegundoapellido(textSegundoApellido.getText());
					prop.insertarPropietario(pro);
					limpiarPantalla();
					btnNuevo.setText("Nuevo");

				}

			}
		});
		btnNuevo.setBounds(57, 313, 89, 23);
		contentPane.add(btnNuevo);

		btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Propietario pro = new Propietario();
				pro.setDni(textDni.getText());
				pro.setUsuario(textUsuario.getText());
				pro.setNombre(textNombre.getText());
				pro.setNumerosecreto(textNumeroSecreto.getText());
				pro.setPrimerapellido(textPrimerApellido.getText());
				pro.setSegundoapellido(textSegundoApellido.getText());
				prop.modificarPropietario(pro);
				limpiarPantalla();
				btnModificar.setEnabled(false);
			}
		});
		btnModificar.setBounds(156, 313, 89, 23);
		btnModificar.setEnabled(false);
		contentPane.add(btnModificar);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Propietario pro = new Propietario();
				pro.setDni(textDni.getText());

				prop.eliminarPropietario(pro);
				limpiarPantalla();
				btnModificar.setEnabled(false);
				btnEliminar.setEnabled(false);
			}
		});
		btnEliminar.setBounds(261, 313, 89, 23);
		btnEliminar.setEnabled(false);
		contentPane.add(btnEliminar);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Propietario propietario = new Propietario();
				propietario.setDni(textDni.getText());
				propietario = prop.consultarPropietarioByDni(propietario);
				textUsuario.setText(propietario.getUsuario());
				textNombre.setText(propietario.getNombre());
				textPrimerApellido.setText(propietario.getPrimerapellido());
				textSegundoApellido.setText(propietario.getSegundoapellido());
				textNumeroSecreto.setText(propietario.getNumerosecreto());
				btnModificar.setEnabled(true);
				btnEliminar.setEnabled(true);
				btnNuevo.setText("Nuevo");
				textDni.setEnabled(false);

			}
		});
		btnBuscar.setBounds(295, 29, 89, 23);
		contentPane.add(btnBuscar);
	}

	private void limpiarPantalla() {
		textUsuario.setText("");
		textNombre.setText("");
		textPrimerApellido.setText("");
		textSegundoApellido.setText("");
		textNumeroSecreto.setText("");
		textDni.setText("");

	}

}
