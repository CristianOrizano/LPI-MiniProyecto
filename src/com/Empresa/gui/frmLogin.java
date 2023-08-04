package com.Empresa.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.metal.MetalPopupMenuSeparatorUI;

import com.Empresa.controlador.MySqlTrabajadorDAO;
import com.Empresa.entidades.Trabajador;
import com.mysql.cj.exceptions.StatementIsClosedException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class frmLogin extends JFrame implements ActionListener {
	
	MySqlTrabajadorDAO trabajador = new MySqlTrabajadorDAO();

	private JPanel contentPane;
	private JTextField txtUsuario;
	private JPasswordField txtContraseña;
	private JButton btnInicia;
	public static  int co;
	private JButton btnCerra;
	  
	  
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmLogin frame = new frmLogin();
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
	public frmLogin() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 418, 506);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setBounds(49, 155, 65, 14);
		contentPane.add(lblUsuario);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a");
		lblContrasea.setBounds(53, 250, 76, 14);
		contentPane.add(lblContrasea);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(44, 192, 307, 27);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		btnInicia = new JButton("Iniciar");
		btnInicia.addActionListener(this);
		btnInicia.setBounds(105, 333, 178, 46);
		contentPane.add(btnInicia);
		
		btnCerra = new JButton("Cerrar");
		btnCerra.addActionListener(this);
		btnCerra.setBounds(105, 390, 178, 44);
		contentPane.add(btnCerra);
		
		txtContraseña = new JPasswordField();
		txtContraseña.setBounds(48, 275, 303, 27);
		contentPane.add(txtContraseña);
	}
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnCerra) {
			actionPerformedBtnCerra(arg0);
		}
		if (arg0.getSource() == btnInicia) {
			actionPerformedBtnInicia(arg0);
		}
	}
	protected void actionPerformedBtnInicia(ActionEvent arg0) {
		String login,contra;
		
		login = txtUsuario.getText();
		contra= new String(txtContraseña.getPassword());
		
		  Trabajador tra=trabajador.Iniciarsecion(login, contra);
		  if(tra==null) {
			  mensaje("Usuario Y Contraseña incorrectos");
			  txtUsuario.setText("");
			  txtContraseña.setText("");
			  txtUsuario.requestFocus();
			  
		  }else {
			  frmPrincipalinterno pri = new frmPrincipalinterno();
			  pri.lbldata.setText("Bienvenido: "+tra.getNombre()+" "+tra.getApellido());
			  String n= tra.getNombre();
			  String a=tra.getApellido();
			 
			     co= tra.getCodEmpl();
				 mensaje("Bienvenido: "+n+" "+a);
				 pri.setLocationRelativeTo(this);
			     pri.setVisible(true);
			  
			  dispose();
		  }
		  
	}
	
	public  void mensaje(String s) {
		JOptionPane.showMessageDialog(null, s);
	}
	
	protected void actionPerformedBtnCerra(ActionEvent arg0) {
		mensaje("hasta luego");
		dispose();
	}
}
