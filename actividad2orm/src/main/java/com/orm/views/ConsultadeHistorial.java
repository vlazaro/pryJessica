package com.orm.views;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.orm.configuracion.AppConfig;
import com.orm.service.CuentaBancariaService;
import com.orm.service.HistorialService;
import com.orm.service.OperacionesService;

import javax.swing.JLabel;

import javax.swing.ListSelectionModel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;

public class ConsultadeHistorial extends JFrame {

	private JPanel contentPane;
	private JTable tblHistorial;
	private HistorialService hservice;
	private OperacionesService opService ;
	private CuentaBancariaService ctaService;
	private JTextField textDato;
	private JLabel lblDato;
	private JComboBox cbxTipoConsulta;

	public ConsultadeHistorial() {
		AppConfig conf = new AppConfig();
		
		hservice = new HistorialService(conf.buildSessionFactory().openSession());
		opService = new OperacionesService(conf.buildSessionFactory().openSession());
		ctaService = new CuentaBancariaService(conf.buildSessionFactory().openSession());
		
		
		

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 620, 476);
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
		btnCancelar.setBounds(494, 396, 89, 23);
		contentPane.add(btnCancelar);

		JLabel lblNewLabel = new JLabel("Tipo Consulta");
		lblNewLabel.setBounds(23, 39, 89, 14);
		contentPane.add(lblNewLabel);

		cbxTipoConsulta = new JComboBox();
		cbxTipoConsulta.setSelectedIndex(-1);
		cbxTipoConsulta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int tipoSelected = cbxTipoConsulta.getSelectedIndex();
				switch (tipoSelected) {
				case 1:
					lblDato.setVisible(true);
					lblDato.setText("Propietario");
					textDato.setVisible(true);
					
					break;
				case 2:
					lblDato.setVisible(false);
					textDato.setVisible(false);
					break;
				case 3:
					lblDato.setVisible(true);
					lblDato.setText("NroCuenta");
					textDato.setVisible(true);
					break;	
				}
			}
		});
		cbxTipoConsulta.setModel(new DefaultComboBoxModel(new String[] {"------ Seleccione una Opcion -----------", "[1] Obtener fecha-hora de la ultima sesion", "[2] Obtener Rankig de cuentas en numero Rojos.", "[3] Obtener posicion de una cuenta "}));
		cbxTipoConsulta.setBounds(105, 36, 375, 20);
		contentPane.add(cbxTipoConsulta);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int tipoSelected = cbxTipoConsulta.getSelectedIndex();
				try {
					switch (tipoSelected) {
					case 1:
						tblHistorial.setModel(filtrarUltimoAcceso(textDato.getText()));
						textDato.setText("");
						break;
					case 2:
						textDato.setVisible(false);
						tblHistorial.setModel(filtrarRankingCuentasRojo());
						break;
					case 3:
						textDato.setVisible(true);
						tblHistorial.setModel(filtarPosicionCuenta(textDato.getText()));
						textDato.setText("");
						break;
					default:
						break;
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

			}

		});
		btnBuscar.setBounds(494, 35, 75, 23);
		contentPane.add(btnBuscar);

		String data[][] = { { "Row1/1", "Row1/2", "Row1/3" }, { "Row2/1", "Row2/2", "Row2/3" },
				{ "Row3/1", "Row3/2", "Row3/3" }, { "Row4/1", "Row4/2", "Row4/3" }, };

		String header[] = { "Column 1", "Column 2", "Column 3" };

		tblHistorial = new JTable(data, header);

		try {
			tblHistorial.setModel(filtrarUltimoAcceso("0"));
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		tblHistorial.setFillsViewportHeight(true);
		tblHistorial.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblHistorial.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		tblHistorial.setBounds(50, 180, 490, 216);
		JScrollPane jscrollPane = new JScrollPane(tblHistorial);
		jscrollPane.setBounds(23, 110, 558, 275);
		getContentPane().add(jscrollPane);

		lblDato = new JLabel("Propietario");
		lblDato.setBounds(23, 64, 75, 14);
		lblDato.setVisible(false);
		
		contentPane.add(lblDato);

		textDato = new JTextField();
		textDato.setVisible(false);
		textDato.setBounds(105, 64, 164, 20);
		contentPane.add(textDato);
		textDato.setColumns(10);

	}

	private TableModel filtrarUltimoAcceso(String text) throws SQLException {
		ResultSet rs;
		rs = hservice.consultaFechaHoraUltimoInicioSesio(text);
		DefaultTableModel dm = buildTableModel(rs);
		rs.close();
		return dm;

	}

	private DefaultTableModel buildTableModel(ResultSet rs) throws SQLException {
		ResultSetMetaData metaData = rs.getMetaData();
		// nombres de columnas
		Vector<String> columnNames = new Vector<String>();
		int columnCount = metaData.getColumnCount();
		for (int column = 1; column <= columnCount; column++) {
			columnNames.add(metaData.getColumnName(column));
		}
		// data de la tabla
		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		while (rs.next()) {
			Vector<Object> vector = new Vector<Object>();
			for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
				vector.add(rs.getObject(columnIndex));
			}
			data.add(vector);
		}

		return new DefaultTableModel(data, columnNames);

	}
	private TableModel filtrarRankingCuentasRojo() throws SQLException {
		ResultSet rs;
		rs = opService.listarCuentasEnRojo();
		DefaultTableModel dm = buildTableModel(rs);
		rs.close();
		return dm;
		

	}
	private TableModel filtarPosicionCuenta(String text) throws SQLException {
		ResultSet rs;
		rs = ctaService.consultarCuentaResultSet(text);
		DefaultTableModel dm = buildTableModel(rs);
		rs.close();
		return dm;


	}

	
	
}
