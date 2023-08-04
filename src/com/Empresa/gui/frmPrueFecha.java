package com.Empresa.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.html.parser.DTD;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.SwingConstants;

import com.Empresa.controlador.MySqlEmpleadoDAO;
import com.Empresa.entidades.Empleado;
import com.toedter.calendar.JDateChooser;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class frmPrueFecha extends JDialog implements ActionListener, MouseListener {
	//global
	MySqlEmpleadoDAO empleado= new MySqlEmpleadoDAO();

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCodigo;
	private JTextField txtNombre;
	private JTable tblTabla;
	private JButton btnAgregar;
	private JDateChooser dtFecha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			frmPrueFecha dialog = new frmPrueFecha();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public frmPrueFecha() {
		setBounds(100, 100, 569, 462);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblMantenimientoEmpleado = new JLabel("Mantenimiento Empleado");
		lblMantenimientoEmpleado.setHorizontalAlignment(SwingConstants.CENTER);
		lblMantenimientoEmpleado.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblMantenimientoEmpleado.setBounds(69, 11, 407, 31);
		contentPanel.add(lblMantenimientoEmpleado);
		
		dtFecha = new JDateChooser();
		dtFecha.setDateFormatString("yyyy/MM/dd");
		dtFecha.setBounds(93, 108, 141, 20);
		contentPanel.add(dtFecha);
		
		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setBounds(37, 114, 46, 14);
		contentPanel.add(lblFecha);
		
		JLabel lblCodigo = new JLabel("Codigo");
		lblCodigo.setBounds(37, 67, 46, 14);
		contentPanel.add(lblCodigo);
		
		txtCodigo = new JTextField();
		txtCodigo.setBounds(93, 65, 141, 20);
		contentPanel.add(txtCodigo);
		txtCodigo.setColumns(10);
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(this);
		btnAgregar.setBounds(330, 83, 89, 23);
		contentPanel.add(btnAgregar);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(37, 169, 46, 14);
		contentPanel.add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(93, 166, 141, 20);
		contentPanel.add(txtNombre);
		txtNombre.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(47, 220, 458, 169);
		contentPanel.add(scrollPane);
		
		tblTabla = new JTable();
		tblTabla.addMouseListener(this);
		tblTabla.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Codigo", "Fecha", "Nombre"
			}
		));
		tblTabla.getColumnModel().getColumn(0).setPreferredWidth(111);
		tblTabla.getColumnModel().getColumn(1).setPreferredWidth(148);
		tblTabla.getColumnModel().getColumn(2).setPreferredWidth(147);
		tblTabla.setFillsViewportHeight(true);
		scrollPane.setViewportView(tblTabla);
		//
		dtFecha.setDateFormatString("yyyy/MM/dd");
	   listado();
	}
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnAgregar) {
			actionPerformedBtnAgregar(arg0);
		}
	}
	public void mensaje(String s) {
		JOptionPane.showMessageDialog(null, s);
	}
	protected void actionPerformedBtnAgregar(ActionEvent arg0) {
		
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy/MM/dd");
		
		String fech, nombre;
		fech= dtFecha.getDate()+"";
		System.out.println("fecha===== "+fech);
		nombre= txtNombre.getText();
		
		if(fech==null) {
			JOptionPane.showMessageDialog(null, "ingrese fecha");
			System.out.println("fechaen if===== "+fech);
		}else {
			
			System.out.println("entro===== "+fech);
		
		
		Empleado e= new Empleado();
		e.setCodigo(Integer.parseInt(txtCodigo.getText()));
		e.setFecha(sdf.format(dtFecha.getDate()));
		e.setNombre(txtNombre.getText());
		
		// 
		       int res=empleado.insert(e);
		 if(res==-1) {
			 mensaje("Error");
		 }else {
			 mensaje("Ingresado");
			 listado();
		 }
		}
		
	}
	public void listado() {
		
		    ArrayList<Empleado> data=empleado.lisAll();
		DefaultTableModel modelo= (DefaultTableModel) tblTabla.getModel();
		modelo.setRowCount(0);
        for(Empleado e:data) {
        	Object row[]= {e.getCodigo(),e.getFecha(),e.getNombre()};
        	modelo.addRow(row);
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
		
		int fila=tblTabla.getSelectedRow();
		String co= tblTabla.getValueAt(fila, 0).toString();
		String fe= tblTabla.getValueAt(fila, 1).toString();
		String no= tblTabla.getValueAt(fila, 2).toString();
		
		txtCodigo.setText(co);
		
		SimpleDateFormat formatodetexto= new SimpleDateFormat("yyyy-MM-dd");
		Date a=null;
		try {
			a= formatodetexto.parse(fe);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dtFecha.setDate(a);
		txtNombre.setText(no);
		
	}
}
