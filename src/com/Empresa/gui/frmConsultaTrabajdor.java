package com.Empresa.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import com.Empresa.Componente.JComboBoxDB;
import com.Empresa.controlador.MySqlTrabajadorDAO;
import com.Empresa.entidades.Distrito;
import com.Empresa.entidades.Trabajador;
import com.utils.GeneradorReporte;

import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.swing.JRViewer;

import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.ButtonGroup;
import javax.swing.table.DefaultTableModel;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import javax.swing.border.LineBorder;

public class frmConsultaTrabajdor extends JDialog implements ActionListener, KeyListener {
	
	//global
	MySqlTrabajadorDAO trabajadorD = new MySqlTrabajadorDAO();
	
	//me combiene que sea global para llamarlo desde otro botom
	ArrayList<Trabajador> lista = null;

	private final JPanel contentPanel = new JPanel();
	private JTable tblTabla;
	private JTextField txtBuscar;
	private JButton btnProceasr;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton rbtNombre;
	private JRadioButton rbtnSueldo;
	private JRadioButton rbtnHijos;
	private JRadioButton rbtDistrito;
	private JLabel lblData;
	private JComboBox cboDistrito;
	private JButton Reporte;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			frmConsultaTrabajdor dialog = new frmConsultaTrabajdor();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public frmConsultaTrabajdor() {
		setBounds(100, 100, 853, 607);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblConsultaDeTrabajadores = new JLabel("CONSULTA DE TRABAJADORES");
			lblConsultaDeTrabajadores.setBounds(10, 11, 714, 45);
			lblConsultaDeTrabajadores.setOpaque(true);
			lblConsultaDeTrabajadores.setHorizontalAlignment(SwingConstants.CENTER);
			lblConsultaDeTrabajadores.setForeground(Color.WHITE);
			lblConsultaDeTrabajadores.setFont(new Font("Tahoma", Font.BOLD, 22));
			lblConsultaDeTrabajadores.setBackground(Color.BLACK);
			contentPanel.add(lblConsultaDeTrabajadores);
		}
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(245, 245, 245));
		panel.setForeground(new Color(255, 0, 0));
		panel.setBounds(20, 90, 571, 82);
		panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 206, 209), 4, true), "Consultas por", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 0, 0)));
		contentPanel.add(panel);
		panel.setLayout(null);
		
		rbtNombre = new JRadioButton("Nombre");
		rbtNombre.setSelected(true);
		buttonGroup.add(rbtNombre);
		rbtNombre.addActionListener(this);
		rbtNombre.setBounds(30, 30, 91, 23);
		panel.add(rbtNombre);
		
		rbtnSueldo = new JRadioButton("Sueldo");
		rbtnSueldo.addActionListener(this);
		buttonGroup.add(rbtnSueldo);
		rbtnSueldo.setBounds(141, 30, 85, 23);
		panel.add(rbtnSueldo);
		
		rbtnHijos = new JRadioButton("Hijos");
		rbtnHijos.addActionListener(this);
		buttonGroup.add(rbtnHijos);
		rbtnHijos.setBounds(252, 30, 85, 23);
		panel.add(rbtnHijos);
		
		rbtDistrito = new JRadioButton("Distrito");
		rbtDistrito.addActionListener(this);
		buttonGroup.add(rbtDistrito);
		rbtDistrito.setBounds(361, 30, 85, 23);
		panel.add(rbtDistrito);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Criterio", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(20, 194, 547, 82);
		contentPanel.add(panel_1);
		panel_1.setLayout(null);
		
		lblData = new JLabel("Nombre:");
		lblData.setBounds(23, 40, 67, 14);
		panel_1.add(lblData);
		
		//metodo listado en el combo
		cboDistrito = new JComboBoxDB("select * from Distrito");
		cboDistrito.setBounds(100, 37, 265, 20);
		panel_1.add(cboDistrito);
		
		txtBuscar = new JTextField();
		txtBuscar.addKeyListener(this);
		txtBuscar.setBounds(100, 37, 142, 20);
		panel_1.add(txtBuscar);
		txtBuscar.setColumns(10);
		
		btnProceasr = new JButton("Procesar");
		btnProceasr.addActionListener(this);
		btnProceasr.setBounds(577, 208, 103, 64);
		contentPanel.add(btnProceasr);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 301, 706, 256);
		contentPanel.add(scrollPane);
		
		tblTabla = new JTable();
		tblTabla.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Codigo", "Nombre", "Apellido", "Sueldo", "Hijos", "Distrito"
			}
		));
		tblTabla.getColumnModel().getColumn(0).setPreferredWidth(73);
		tblTabla.getColumnModel().getColumn(1).setPreferredWidth(124);
		tblTabla.getColumnModel().getColumn(2).setPreferredWidth(142);
		tblTabla.getColumnModel().getColumn(3).setPreferredWidth(99);
		tblTabla.getColumnModel().getColumn(5).setPreferredWidth(118);
		tblTabla.setFillsViewportHeight(true);
		scrollPane.setViewportView(tblTabla);
		
		Reporte = new JButton("Reporte");
		Reporte.addActionListener(this);
		Reporte.setBounds(706, 208, 90, 64);
		contentPanel.add(Reporte);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(614, 102, 213, 64);
		contentPanel.add(panel_2);
		panel_2.setLayout(null);
		
		//
		cboDistrito.setVisible(false);
	}
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == Reporte) {
			actionPerformedReporte(arg0);
		}
		if (arg0.getSource() == rbtDistrito) {
			actionPerformedRbtDistrito(arg0);
		}
		if (arg0.getSource() == rbtnHijos) {
			actionPerformedRbtnHijos(arg0);
		}
		if (arg0.getSource() == rbtnSueldo) {
			actionPerformedRbtnSueldo(arg0);
		}
		if (arg0.getSource() == rbtNombre) {
			actionPerformedRbtNombre(arg0);
		}
		if (arg0.getSource() == btnProceasr) {
			actionPerformedBtnProceasr(arg0);
		}
	}
	protected void actionPerformedBtnProceasr(ActionEvent arg0) {
		
		
		
		if (rbtNombre.isSelected()) {
			lista = trabajadorD.ConTrabajador(1, txtBuscar.getText());
		} else if (rbtnSueldo.isSelected()) {
			lista = trabajadorD.ConTrabajador(2, txtBuscar.getText());
		} else if (rbtnHijos.isSelected()) {
			lista = trabajadorD.ConTrabajador(3, txtBuscar.getText());
		} else if (rbtDistrito.isSelected()) {
			String c = cboDistrito.getSelectedItem().toString();
			System.out.println("cbo  " + c);
	         
	         // String ar[]=c.split("-");
			//lista= trabajadorD.ConTrabajador(4, ar[0]);
	         ArrayList<Distrito> lisdi= trabajadorD.listDis();
			    
			    int codydis = 0;
			    for (Distrito d : lisdi) {
					if(d.getDescr().equals(c)) {
						codydis= d.getCodigo();
						break;
					}
				}
	         lista= trabajadorD.ConTrabajador(4, codydis+"");
		}
		
		listado(lista);
	}
	public void listado(ArrayList<Trabajador> data) {
		
		DefaultTableModel modelo = (DefaultTableModel) tblTabla.getModel();
		modelo.setRowCount(0);
		for(Trabajador tr: data) {
	    	Object row[]= {tr.getCodEmpl(),tr.getNombre(),tr.getApellido(),tr.getSueldo(),tr.getHijos(),tr.getNomDistrito()};
	    	modelo.addRow(row);
		}
		
	}
	
	protected void actionPerformedRbtNombre(ActionEvent arg0) {
		
		if(rbtNombre.isSelected()) {
			lblData.setText("NOMBRE: ");
			cboDistrito.setVisible(false);
			txtBuscar.setVisible(true);
			
		}
		
	}
	protected void actionPerformedRbtnSueldo(ActionEvent arg0) {
		if(rbtnSueldo.isSelected()) {
			lblData.setText("SUELDO: ");
			cboDistrito.setVisible(false);
			txtBuscar.setVisible(true);
			
		}
		
		
	}
	protected void actionPerformedRbtnHijos(ActionEvent arg0) {
		if(rbtnHijos.isSelected()) {
			lblData.setText("HIJOS: ");
			cboDistrito.setVisible(false);
			txtBuscar.setVisible(true);
			
		}
		
	}
	protected void actionPerformedRbtDistrito(ActionEvent arg0) {
		if(rbtDistrito.isSelected()) {
			lblData.setText("DISTRITO: ");
			cboDistrito.setVisible(true);
			txtBuscar.setVisible(false);
			
		}
		
	}
	
	public void keyPressed(KeyEvent arg0) {
	}
	public void keyReleased(KeyEvent arg0) {
		if (arg0.getSource() == txtBuscar) {
			keyReleasedTxtBuscar(arg0);
		}
	}
	public void keyTyped(KeyEvent arg0){
		
	}
	protected void keyReleasedTxtBuscar(KeyEvent arg0) {
		
	}
	protected void actionPerformedReporte(ActionEvent arg0) {
		
		frmReport2 re= new frmReport2();
		//1 necesito en nombre del archivo primerpaarametro
		String name="reporteTrabaj.jasper";
		//2 necesito el origen de datos a listar
		//convertir ese array en un JRBeanCollectionDataSource
		JRBeanCollectionDataSource data= new JRBeanCollectionDataSource(lista);
		//llamar al metodo generar reporte y pasar sus parametros
		//ese metodo genera me devuelve un jasperPrint por eso lo almaceno ahi
		JasperPrint jasper =GeneradorReporte.genera(name, data, null);
		
		//crear visor y adicionar el objeto jasper 
		//esto es para botom de opciones
			JRViewer viewer=new JRViewer(jasper);
		//adicionar al panelReporte el objeto viewer
			re.panelrepor.add(viewer);
		
		    re.setVisible(true);
	
		
	}
}
