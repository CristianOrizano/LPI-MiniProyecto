package com.Empresa.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.Empresa.controlador.MySqlClienteDAO;
import com.Empresa.entidades.Cliente;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class frmBuscarCliente extends JDialog implements ActionListener, KeyListener, MouseListener {
	
	//global
	MySqlClienteDAO cliente = new MySqlClienteDAO();

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNombre;
	private JTable tblTabla;
	private JButton btnBuscar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			frmBuscarCliente dialog = new frmBuscarCliente();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public frmBuscarCliente() {
		setModal(true);
		setBounds(100, 100, 638, 410);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNombre = new JLabel("Nombre Cliente:");
			lblNombre.setBounds(24, 46, 96, 14);
			contentPanel.add(lblNombre);
		}
		{
			txtNombre = new JTextField();
			txtNombre.addKeyListener(this);
			txtNombre.setBounds(119, 43, 290, 20);
			contentPanel.add(txtNombre);
			txtNombre.setColumns(10);
		}
		{
			btnBuscar = new JButton("Obtener");
			btnBuscar.addActionListener(this);
			btnBuscar.setBounds(447, 36, 89, 35);
			contentPanel.add(btnBuscar);
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(24, 95, 570, 233);
			contentPanel.add(scrollPane);
			{
				tblTabla = new JTable();
				tblTabla.addMouseListener(this);
				tblTabla.setModel(new DefaultTableModel(
					new Object[][] {
					},
					new String[] {
						"Codigo", "Nombre", "Apellido", "Dni", "Sexo"
					}
				));
				tblTabla.getColumnModel().getColumn(0).setPreferredWidth(91);
				tblTabla.getColumnModel().getColumn(1).setPreferredWidth(150);
				tblTabla.getColumnModel().getColumn(2).setPreferredWidth(151);
				tblTabla.getColumnModel().getColumn(3).setPreferredWidth(116);
				tblTabla.getColumnModel().getColumn(4).setPreferredWidth(113);
				tblTabla.setFillsViewportHeight(true);
				scrollPane.setViewportView(tblTabla);
			}
		}
	}

	
	public void keyPressed(KeyEvent arg0) {
	}
	public void keyReleased(KeyEvent arg0) {
		if (arg0.getSource() == txtNombre) {
			keyReleasedTxtNombre(arg0);
		}
	}
	public void keyTyped(KeyEvent arg0) {
		
	}
	public void listado(String no) {
      	ArrayList<Cliente> bean=cliente.BuscarCliente(no);
		//
      	DefaultTableModel modelo = (DefaultTableModel) tblTabla.getModel();
      	modelo.setRowCount(0);
      	for(Cliente c:bean){
      		 Object row[]= {c.getCodi(),c.getNombre(),c.getApellido(),c.getDni(),c.getSexo()};
      		 modelo.addRow(row);
      	}
       
      	
	}
	//ala hora de escribir invocar al listado
	protected void keyReleasedTxtNombre(KeyEvent arg0) {
		
		listado(txtNombre.getText());
		
		
	}
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnBuscar) {
			actionPerformedBtnBuscar(arg0);
		}
	}
	protected void actionPerformedBtnBuscar(ActionEvent arg0) {
		
		int fila = tblTabla.getSelectedRow();
		if(fila>=0) {
			 String codi=tblTabla.getValueAt(fila, 0).toString();
			  String nom= tblTabla.getValueAt(fila, 1).toString();
			  String ape= tblTabla.getValueAt(fila, 2).toString();
			  String dni= tblTabla.getValueAt(fila, 3).toString();
			  String sexo= tblTabla.getValueAt(fila, 4).toString();
			  
			
			  //no debemos crear un objeto porque si creamos a eso objeto en memoria le 
			  //vamos a enviar valores, y lo que queremos es enviarlo directo al label
			  
			   frmFactura.txtCodiCli.setText(codi);
			   frmFactura.txtNombreCli.setText(nom);
			   frmFactura.txtApellidoCli.setText(ape);
			   frmFactura.txtdni.setText(dni);
			   frmFactura.txtSexo.setText(sexo);
			   
			  dispose();
		}else {
			JOptionPane.showMessageDialog(null, "selecciona un registro meco");
		}

	    
	}
	public void mouseClicked(MouseEvent arg0) {
		if (arg0.getSource() == tblTabla) {
			mouseClickedTblTabla(arg0);
		}
	}
	public void mouseEntered(MouseEvent arg0) {
	}
	public void mouseExited(MouseEvent arg0) {
	}
	public void mousePressed(MouseEvent arg0) {
	}
	public void mouseReleased(MouseEvent arg0) {
	}
	protected void mouseClickedTblTabla(MouseEvent arg0) {
	}
}
