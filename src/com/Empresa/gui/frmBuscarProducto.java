package com.Empresa.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.Empresa.controlador.MySqlProductoDAO;
import com.Empresa.entidades.Producto;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.awt.event.KeyEvent;

public class frmBuscarProducto extends JDialog implements ActionListener, KeyListener {
	
	//global
	MySqlProductoDAO producto= new MySqlProductoDAO();
	

	private final JPanel contentPanel = new JPanel();
	private JTextField txtProducto;
	private JTable tblTabla;
	private JButton btnObtener;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			frmBuscarProducto dialog = new frmBuscarProducto();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public frmBuscarProducto() {
		setModal(true);
		setBounds(100, 100, 618, 369);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNombreProducto = new JLabel("Nombre producto:");
		lblNombreProducto.setBounds(23, 44, 107, 14);
		contentPanel.add(lblNombreProducto);
		
		txtProducto = new JTextField();
		txtProducto.addKeyListener(this);
		txtProducto.setBounds(133, 41, 265, 20);
		contentPanel.add(txtProducto);
		txtProducto.setColumns(10);
		
		btnObtener = new JButton("Obtener");
		btnObtener.addActionListener(this);
		btnObtener.setBounds(445, 33, 89, 36);
		contentPanel.add(btnObtener);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 92, 555, 227);
		contentPanel.add(scrollPane);
		
		tblTabla = new JTable();
		tblTabla.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Codigo", "Nombre Producto", "Precio"
			}
		));
		tblTabla.getColumnModel().getColumn(0).setPreferredWidth(141);
		tblTabla.getColumnModel().getColumn(1).setPreferredWidth(189);
		tblTabla.getColumnModel().getColumn(2).setPreferredWidth(198);
		tblTabla.setFillsViewportHeight(true);
		scrollPane.setViewportView(tblTabla);
	}
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnObtener) {
			actionPerformedBtnObtener(arg0);
		}
	}
	
	
	
	
	protected void actionPerformedBtnObtener(ActionEvent arg0) {
		
		int fila= tblTabla.getSelectedRow();
	     String codi=tblTabla.getValueAt(fila, 0).toString();
		  String nom= tblTabla.getValueAt(fila, 1).toString();
		  String pre= tblTabla.getValueAt(fila, 2).toString();
		
		   frmFactura.txtcodPro.setText(codi);
		   frmFactura.txtNombrepro.setText(nom);
		   frmFactura.txtPrecioPro.setText(pre);
		  
		   
		  dispose();
		
		
	}
	public void keyPressed(KeyEvent arg0) {
	}
	public void keyReleased(KeyEvent arg0) {
		if (arg0.getSource() == txtProducto) {
			keyReleasedTextField(arg0);
		}
	}
	public void keyTyped(KeyEvent arg0) {
	}
	
	public void listado(String pro) {
		
		  ArrayList<Producto> bean =producto.BuscarProducto(pro);
		DefaultTableModel modelo= (DefaultTableModel) tblTabla.getModel();
		modelo.setRowCount(0);
          for(Producto pr:bean) {
        	  Object row[]= {pr.getCodigo(),pr.getNombre(),pr.getPrecio()};
        	  modelo.addRow(row);
          }
		  
	}
	
	protected void keyReleasedTextField(KeyEvent arg0) {
		
		listado(txtProducto.getText());
	}
}
