package com.Empresa.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.Empresa.controlador.MySqlTrabajadorDAO;
import com.Empresa.entidades.Trabajador;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import javax.swing.JLabel;

public class frmConsultaXnombre extends JDialog implements ActionListener, KeyListener {
	
	//global
	
	MySqlTrabajadorDAO trabajador = new MySqlTrabajadorDAO();

	private final JPanel contentPanel = new JPanel();
	private JButton btnConsultar;
	private JTextField txtNombre;
	private JTable tblTabla;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			frmConsultaXnombre dialog = new frmConsultaXnombre();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public frmConsultaXnombre() {
		setBounds(100, 100, 727, 409);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		btnConsultar = new JButton("Consultar");
		btnConsultar.addActionListener(this);
		btnConsultar.setBounds(536, 56, 89, 33);
		contentPanel.add(btnConsultar);
		
		txtNombre = new JTextField();
		txtNombre.addActionListener(this);
		txtNombre.addKeyListener(this);
		txtNombre.setBounds(158, 62, 336, 20);
		contentPanel.add(txtNombre);
		txtNombre.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 111, 689, 248);
		contentPanel.add(scrollPane);
		
		tblTabla = new JTable();
		tblTabla.addKeyListener(this);
		tblTabla.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Codigo", "Nombre", "Apellido", "Sueldo", "Hijos", "Distrito"
			}
		));
		tblTabla.setFillsViewportHeight(true);
		scrollPane.setViewportView(tblTabla);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(67, 65, 81, 14);
		contentPanel.add(lblNombre);
	}
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == txtNombre) {
			actionPerformedTxtNombre(arg0);
		}
		if (arg0.getSource() == btnConsultar) {
			actionPerformedBtnConsultar(arg0);
		}
	}
	protected void actionPerformedBtnConsultar(ActionEvent arg0) {
		
		String no = txtNombre.getText();
		ArrayList<Trabajador> tr=  trabajador.ConsultaXNombre(no);
		//listar
		DefaultTableModel modelo= (DefaultTableModel) tblTabla.getModel();
		 modelo.setRowCount(0);
		 
		for(Trabajador tra :tr) {
			 Object row[]= {tra.getCodEmpl(),tra.getNombre(),tra.getApellido(),tra.getSueldo(),tra.getSueldo(),tra.getNomDistrito()};
			 modelo.addRow(row);
		}
		
		
	}
	public void listado(String no) {
		
		 ArrayList<Trabajador> tr=trabajador.ConsultaXNombre(no);
		DefaultTableModel modelo= (DefaultTableModel) tblTabla.getModel();
		 modelo.setRowCount(0);
		 for(Trabajador tra:tr) {
			 Object row[]= {tra.getCodEmpl(),tra.getNombre(),tra.getApellido(),tra.getSueldo(),tra.getSueldo(),tra.getNomDistrito()};
			 modelo.addRow(row);
		 }
		
	}
	
	
	public void keyPressed(KeyEvent arg0) {
	}
	public void keyReleased(KeyEvent arg0) {
		if (arg0.getSource() == txtNombre) {
			keyReleasedTxtNombre(arg0);
		}
		if (arg0.getSource() == tblTabla) {
			keyReleasedTblTabla(arg0);
		}
	}
	public void keyTyped(KeyEvent arg0) {
	}
	protected void keyReleasedTblTabla(KeyEvent arg0) {

	}
	protected void keyReleasedTxtNombre(KeyEvent arg0) {
		//
		listado(txtNombre.getText());
		
	}
	protected void actionPerformedTxtNombre(ActionEvent arg0) {
	}
}
