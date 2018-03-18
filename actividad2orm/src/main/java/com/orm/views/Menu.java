package com.orm.views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Menu extends JFrame {

	private JPanel contentPane;
	public Menu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 534, 314);
		getContentPane().setLayout(null);
		
		
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnMantenimientoCuenta = new JButton("Mantenimiento de  Cuentas");
		btnMantenimientoCuenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				MantenimientodeCuentas p = new MantenimientodeCuentas();
				p.setVisible(true);
				p.setLocationRelativeTo(null);
				
				
			}
		});
		btnMantenimientoCuenta.setBounds(126, 33, 257, 23);
		contentPane.add(btnMantenimientoCuenta);
		
		JButton btnMantenimientoDePropietarios = new JButton("Mantenimiento de Propietarios");
		btnMantenimientoDePropietarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MantenimientodePropietarios p = new MantenimientodePropietarios();
				p.setVisible(true);
				p.setLocationRelativeTo(null);
			}
		});
		btnMantenimientoDePropietarios.setBounds(126, 67, 257, 23);
		contentPane.add(btnMantenimientoDePropietarios);
		
		JButton btnMantenimientoDeOperaciones = new JButton("Mantenimiento de Operaciones");
		btnMantenimientoDeOperaciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MantenimientodeOperaciones p = new MantenimientodeOperaciones();
				p.setVisible(true);
				p.setLocationRelativeTo(null);
				
			}
		});
		btnMantenimientoDeOperaciones.setBounds(126, 101, 257, 23);
		contentPane.add(btnMantenimientoDeOperaciones);
		
		JButton btnConsultaDeHistorial = new JButton("Consulta de Historial");
		btnConsultaDeHistorial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConsultadeHistorial p = new ConsultadeHistorial();
				p.setVisible(true);
				p.setLocationRelativeTo(null);
			}
		});
		btnConsultaDeHistorial.setBounds(126, 186, 257, 23);
		contentPane.add(btnConsultaDeHistorial);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(395, 241, 89, 23);
		contentPane.add(btnCancelar);
		
		JButton btnConsultarOperaciones = new JButton("Consultar Operaciones");
		btnConsultarOperaciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ConsultaOperaciones p = new ConsultaOperaciones();
				p.setVisible(true);
				p.setLocationRelativeTo(null);
			}
		});
		btnConsultarOperaciones.setBounds(126, 158, 257, 23);
		contentPane.add(btnConsultarOperaciones);
	}
}
