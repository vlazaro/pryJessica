package actividad1.views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;

import actividad1.AppConfig;
import actividad1.domain.Propietario;
import actividad1.servicios.HistorialService;
import actividad1.servicios.PropietariosService;

import javax.swing.JLabel;
import javax.swing.JPasswordField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class TestJDBC extends JFrame {

	private JFrame frame;
	private JTextField txtUsuario;
	private JPasswordField pswPassword;
	private PropietariosService props;
	private AppConfig conn = new AppConfig();
	private JLabel lblMensaje ;
	private HistorialService hservice ;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestJDBC window = new TestJDBC();
					window.frame.setVisible(true);
					window.frame.setVisible(true);
					window.frame.setResizable(false);
					window.frame.setLocationRelativeTo(null);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TestJDBC() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		props = new PropietariosService(conn.getConn());
		
		hservice = new HistorialService(conn.getConn());
		
		frame = new JFrame();
		frame.setBounds(100, 100, 393, 275);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(194, 41, 86, 20);
		frame.getContentPane().add(txtUsuario);
		txtUsuario.setColumns(10);
		
		JLabel lblUsuario = new JLabel("Usuario : ");
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblUsuario.setBounds(76, 44, 63, 17);
		frame.getContentPane().add(lblUsuario);
		
		JLabel lblNewLabel = new JLabel("Password :");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(76, 88, 76, 20);
		frame.getContentPane().add(lblNewLabel);
		
		pswPassword = new JPasswordField();
		pswPassword.setBounds(194, 88, 86, 20);
		frame.getContentPane().add(pswPassword);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(-1);
			}
		});
		btnCancelar.setBounds(194, 155, 89, 23);
		frame.getContentPane().add(btnCancelar);
		
		JButton btnIngresar = new JButton("Ingresar");
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					char[] clave = pswPassword.getPassword();
					String claveFinal = new String(clave);
					
					boolean valido = props.validarUsuarioPassword(txtUsuario.getText(), claveFinal);
					if (valido) {
						Propietario prop = new Propietario();
						prop.setUsuario(txtUsuario.getText());
						prop.setNumerosecreto(claveFinal);
						prop = props.consultarPropietarioByUsuarioPassword(prop);
						hservice.insertarHistorialLogin(prop);
						Menu p = new Menu();
						p.setVisible(true);
						p.setLocationRelativeTo(null);
						
					}else {
						lblMensaje.setText("Usuario o password incorrecto");
						txtUsuario.setText("");
						pswPassword.setText("");
						
					}
					
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
				
				
			}
		});
		btnIngresar.setBounds(76, 155, 89, 23);
		frame.getContentPane().add(btnIngresar);
		
		lblMensaje = new JLabel("");
		lblMensaje.setForeground(Color.RED);
		lblMensaje.setBounds(23, 196, 344, 14);
		frame.getContentPane().add(lblMensaje);
	}
}
