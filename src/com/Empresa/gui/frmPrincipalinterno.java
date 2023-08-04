package com.Empresa.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.Empresa.entidades.ConsultaXEmple;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JDesktopPane;
import javax.swing.JToolBar;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import java.awt.Color;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.Font;

public class frmPrincipalinterno extends JFrame implements ActionListener, WindowListener {

	private JPanel contentPane;
	private JMenuItem mntmTrabajador;
	private JMenuItem mntmConsultaTrabajdor;
	private JMenuItem mntmFactura;
	private JMenuItem mntmCliente;
	private JMenuItem mntmConsultaNombre;
	private JMenuItem mntmConsultaUsuario;
	private JMenuItem mntmConsultaCliente;
	private JDesktopPane dpContenedor;
	private JPanel panel;
	public JLabel lbldata;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmPrincipalinterno frame = new frmPrincipalinterno();
					frame.setLocationRelativeTo(null);
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
	public frmPrincipalinterno() {
		addWindowListener(this);
		setForeground(Color.RED);
		setBackground(Color.BLACK);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1028, 589);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnInicio = new JMenu("Inicio");
		menuBar.add(mnInicio);
		
		JMenuItem mntmSalir = new JMenuItem("Salir");
		mnInicio.add(mntmSalir);
		
		JMenu mnMantenimientos = new JMenu("Mantenimientos");
		menuBar.add(mnMantenimientos);
		
		mntmTrabajador = new JMenuItem("Trabajador");
		mntmTrabajador.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));
		mntmTrabajador.addActionListener(this);
		mnMantenimientos.add(mntmTrabajador);
		
		mntmCliente = new JMenuItem("Cliente");
		mntmCliente.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, InputEvent.CTRL_MASK));
		mntmCliente.addActionListener(this);
		mnMantenimientos.add(mntmCliente);
		
		JMenu mnConsultas = new JMenu("Consultas");
		menuBar.add(mnConsultas);
		
		mntmConsultaTrabajdor = new JMenuItem("Consulta Trabajdor");
		mntmConsultaTrabajdor.addActionListener(this);
		mnConsultas.add(mntmConsultaTrabajdor);
		
		mntmConsultaNombre = new JMenuItem("Consulta Nombre");
		mntmConsultaNombre.addActionListener(this);
		mnConsultas.add(mntmConsultaNombre);
		
		mntmConsultaUsuario = new JMenuItem("Consulta Usuario");
		mntmConsultaUsuario.addActionListener(this);
		mnConsultas.add(mntmConsultaUsuario);
		
		mntmConsultaCliente = new JMenuItem("Consulta Cliente");
		mntmConsultaCliente.addActionListener(this);
		mnConsultas.add(mntmConsultaCliente);
		
		JMenu mnTransacciones = new JMenu("Transacciones");
		menuBar.add(mnTransacciones);
		
		mntmFactura = new JMenuItem("Factura");
		mntmFactura.addActionListener(this);
		mnTransacciones.add(mntmFactura);
		
		JMenu mnReportes = new JMenu("Reportes");
		menuBar.add(mnReportes);
		
		JMenuItem mntmFactura_1 = new JMenuItem("Factura");
		mnReportes.add(mntmFactura_1);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		dpContenedor = new JDesktopPane();
		dpContenedor.setAutoscrolls(true);
		contentPane.add(dpContenedor, BorderLayout.CENTER);
		
		panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		
		lbldata = new JLabel("BIENVENIDO: ");
		lbldata.setFont(new Font("Verdana", Font.BOLD, 14));
		panel.add(lbldata);
	}
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == mntmConsultaCliente) {
			actionPerformedMntmConsultaCliente(arg0);
		}
		if (arg0.getSource() == mntmConsultaUsuario) {
			actionPerformedMntmConsultaUsuario(arg0);
		}
		if (arg0.getSource() == mntmConsultaNombre) {
			actionPerformedMntmConsultaNombre(arg0);
		}
		if (arg0.getSource() == mntmCliente) {
			actionPerformedMntmCliente(arg0);
		}
		if (arg0.getSource() == mntmFactura) {
			actionPerformedMntmFactura(arg0);
		}
		if (arg0.getSource() == mntmConsultaTrabajdor) {
			actionPerformedMntmConsultaTrabajdor(arg0);
		}
		if (arg0.getSource() == mntmTrabajador) {
			actionPerformedMntmTrabajador(arg0);
		}
	}
	protected void actionPerformedMntmTrabajador(ActionEvent arg0) {
		frmTrabajadorPru tr = new frmTrabajadorPru();
		tr.setLocationRelativeTo(this);
		tr.setVisible(true);
		
	}
	protected void actionPerformedMntmConsultaTrabajdor(ActionEvent arg0) {
		
		frmConsultaTrabajdor tr =new frmConsultaTrabajdor();
		tr.setLocationRelativeTo(this);
		tr.setVisible(true);
		
	}
	protected void actionPerformedMntmFactura(ActionEvent arg0) {
		frmFactura fa=new frmFactura();
		fa.setVisible(true);
		
	}
	protected void actionPerformedMntmCliente(ActionEvent arg0) {
		frmCliente cl= new frmCliente();
		dpContenedor.add(cl);
		cl.setVisible(true);
		
	}
	protected void actionPerformedMntmConsultaNombre(ActionEvent arg0) {
		frmConsultaXnombre co= new frmConsultaXnombre();
		co.setLocationRelativeTo(this);
		co.setVisible(true);
	}
	public void windowActivated(WindowEvent arg0) {
		if (arg0.getSource() == this) {
			windowActivatedThis(arg0);
		}
	}
	public void mensaje(String s) {
		JOptionPane.showMessageDialog(null,s);
	}
	public void windowClosed(WindowEvent arg0) {
	}
	public void windowClosing(WindowEvent arg0) {
	}
	public void windowDeactivated(WindowEvent arg0) {
	}
	public void windowDeiconified(WindowEvent arg0) {
	}
	public void windowIconified(WindowEvent arg0) {
	}
	public void windowOpened(WindowEvent arg0) {
		
	}
	protected void windowActivatedThis(WindowEvent arg0) {

		
	}
	protected void actionPerformedMntmConsultaUsuario(ActionEvent arg0) {
		frmconsultaJOIN ec= new frmconsultaJOIN();
		ec.setVisible(true);
		
	}
	protected void actionPerformedMntmConsultaCliente(ActionEvent arg0) {
		frmConsultaCliente cl= new frmConsultaCliente();
		cl.setLocationRelativeTo(this);
		cl.setVisible(true);
		
	}
}
