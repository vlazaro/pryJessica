package actividad1.views;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import actividad1.AppConfig;
import actividad1.servicios.OperacionesService;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;

public class ConsultaOperaciones extends JFrame {

	private JPanel contentPane;
	private JTextField textNroCuenta;
	private JTable tblOperaciones;
	private JCheckBox chcxNumRojo;
	
	private OperacionesService operacionesService ;
	private JButton btnCancelar;

	/**
	 * Create the frame.
	 */
	public ConsultaOperaciones() {
		AppConfig gestor = new AppConfig();
		operacionesService = new OperacionesService(gestor.getConn());
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 643, 462);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNroDeCuenta = new JLabel("Nro de Cuenta:");
		lblNroDeCuenta.setBounds(10, 24, 83, 14);
		contentPane.add(lblNroDeCuenta);

		textNroCuenta = new JTextField();
		textNroCuenta.setBounds(97, 21, 139, 20);
		contentPane.add(textNroCuenta);
		textNroCuenta.setColumns(10);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					if (chcxNumRojo.isSelected()) {
					 tblOperaciones.setModel(filtrarOperacionesEnRojo(textNroCuenta.getText()));
					}else {
					  tblOperaciones.setModel(filtrarOperaciones(textNroCuenta.getText()));
					}
					
					
					
				} catch (SQLException j) {
				
					j.printStackTrace();
				}
				
				
				
			}
		});
		btnBuscar.setBounds(255, 20, 89, 23);
		contentPane.add(btnBuscar);
		
		
		String data[][] = { { "Row1/1", "Row1/2", "Row1/3" },
	            { "Row2/1", "Row2/2", "Row2/3" },
	            { "Row3/1", "Row3/2", "Row3/3" },
	            { "Row4/1", "Row4/2", "Row4/3" }, };

	    String header[] = { "Column 1", "Column 2", "Column 3" };
		
		
			tblOperaciones = new JTable(data,header);
		
			try {
				tblOperaciones.setModel(filtrarOperaciones("0"));
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
			tblOperaciones.setFillsViewportHeight(true);
			tblOperaciones.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			tblOperaciones.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
			tblOperaciones.setBounds(50, 180, 490, 216);
			JScrollPane jscrollPane = new JScrollPane(tblOperaciones);
			jscrollPane.setBounds(28, 82, 558, 275);
			getContentPane().add(jscrollPane);
			
			btnCancelar = new JButton("Cancelar");
			btnCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			btnCancelar.setBounds(493, 389, 89, 23);
			contentPane.add(btnCancelar);
			
			chcxNumRojo = new JCheckBox("Operaciones que han generado numeros rojos");
			chcxNumRojo.setBounds(20, 48, 343, 23);
			contentPane.add(chcxNumRojo);
			
		
		
		
		
	}

	private DefaultTableModel filtrarOperaciones(String nroCuenta) throws SQLException{
		ResultSet rs;
        rs = operacionesService.listarOperaciones(nroCuenta);
        DefaultTableModel dm = buildTableModel(rs);
        rs.close();
     	return dm;
		
	}
	private DefaultTableModel filtrarOperacionesEnRojo(String nroCuenta) throws SQLException{
		ResultSet rs;
        rs = operacionesService.listarOperacionesRojo(nroCuenta);
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
}
