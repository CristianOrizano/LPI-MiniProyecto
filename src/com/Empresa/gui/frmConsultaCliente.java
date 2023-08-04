package com.Empresa.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;

import com.Empresa.controlador.MySqlClienteDAO;
import com.Empresa.entidades.Cliente;
import com.utils.GeneradorReporte;

import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.swing.JRViewer;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JSlider;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import javax.swing.border.BevelBorder;

public class frmConsultaCliente extends JDialog implements ActionListener, KeyListener {
	MySqlClienteDAO cliente = new MySqlClienteDAO();
  
	ArrayList<Cliente> lista=null;
	
	private final JPanel contentPanel = new JPanel();
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField txtBusacr;
	private JTable tblTabla;
	private JButton btnProcesar;
	private JRadioButton rbtNombre;
	private JRadioButton rbtSexo;
	private JPanel panel_1;
	private JComboBox cboGenero;
	private JButton btnReporte;
	private JPanel panelrepo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			frmConsultaCliente dialog = new frmConsultaCliente();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public frmConsultaCliente() {
		setBounds(100, 100, 837, 852);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(35, 11, 321, 74);
		panel.setBorder(new TitledBorder(null, "Consulta por ", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPanel.add(panel);
		panel.setLayout(null);
		
		rbtNombre = new JRadioButton("Nombre");
		rbtNombre.addActionListener(this);
		buttonGroup.add(rbtNombre);
		rbtNombre.setSelected(true);
		rbtNombre.setBounds(6, 27, 99, 23);
		panel.add(rbtNombre);
		
		rbtSexo = new JRadioButton("Sexo");
		rbtSexo.setBounds(116, 27, 83, 23);
		panel.add(rbtSexo);
		rbtSexo.addActionListener(this);
		buttonGroup.add(rbtSexo);
		
		panel_1 = new JPanel();
		panel_1.setBounds(35, 96, 383, 80);
		panel_1.setBorder(new TitledBorder(null, "Criterio", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPanel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblData = new JLabel("Nombre:");
		lblData.setBounds(10, 34, 65, 14);
		panel_1.add(lblData);
		
		cboGenero = new JComboBox();
		cboGenero.setModel(new DefaultComboBoxModel(new String[] {"Masculino", "Femenino"}));
		cboGenero.setBounds(85, 31, 201, 20);
		panel_1.add(cboGenero);
		
		txtBusacr = new JTextField();
		txtBusacr.addKeyListener(this);
		txtBusacr.setBounds(85, 31, 113, 20);
		panel_1.add(txtBusacr);
		txtBusacr.setColumns(10);
		
		btnProcesar = new JButton("Procesar");
		btnProcesar.setBounds(442, 117, 89, 23);
		btnProcesar.addActionListener(this);
		contentPanel.add(btnProcesar);
		
		panelrepo = new JPanel();
		panelrepo.setBounds(35, 363, 751, 422);
		panelrepo.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		contentPanel.add(panelrepo);
		panelrepo.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(35, 187, 628, 165);
		contentPanel.add(scrollPane);
		
		tblTabla = new JTable();
		tblTabla.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Codigo", "Nombre", "Apellido", "dni", "sexo"
			}
		));
		tblTabla.setFillsViewportHeight(true);
		scrollPane.setViewportView(tblTabla);
		
		btnReporte = new JButton("Reporte");
		btnReporte.setBounds(540, 117, 89, 23);
		btnReporte.addActionListener(this);
		contentPanel.add(btnReporte);
		cboGenero.setVisible(false);
	}
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnReporte) {
			actionPerformedBtnReporte(arg0);
		}
		if (arg0.getSource() == rbtSexo) {
			actionPerformedRbtSexo(arg0);
		}
		if (arg0.getSource() == rbtNombre) {
			actionPerformedRbtNombre(arg0);
		}
		if (arg0.getSource() == btnProcesar) {
			actionPerformedBtnProcesar(arg0);
		}
	}
	protected void actionPerformedBtnProcesar(ActionEvent arg0) {
		
		
		if(rbtNombre.isSelected()==true) {
			
			String sa= txtBusacr.getText();
			lista = cliente.consulta(1, sa);
			System.out.println("obt "+sa);
		}else if(rbtSexo.isSelected()==true){
			
			String s = cboGenero.getSelectedItem().toString();
			System.out.println("cbo "+s);
			lista = cliente.consulta(2, s);
		}
		
		DefaultTableModel modelo = (DefaultTableModel) tblTabla.getModel();
		modelo.setRowCount(0);
		for(Cliente c:lista) {
			Object row[]= {c.getCodi(),c.getNombre(),c.getApellido(),c.getDni(),c.getSexo()};
			modelo.addRow(row);
		}
		//cliente.consulta(, criterio)
		
	}
	protected void actionPerformedRbtNombre(ActionEvent arg0) {
		
		if(rbtNombre.isSelected()==true) {
			txtBusacr.setVisible(true);
			cboGenero.setVisible(false);
		}
	}
	protected void actionPerformedRbtSexo(ActionEvent arg0) {
		
		if(rbtSexo.isSelected()==true) {
			cboGenero.setVisible(true);
			txtBusacr.setVisible(false);
		}
	}
	public void keyPressed(KeyEvent arg0) {
	}
	public void keyReleased(KeyEvent arg0) {
		if (arg0.getSource() == txtBusacr) {
			keyReleasedTxtBusacr(arg0);
		}
	}
	public void keyTyped(KeyEvent arg0) {
	}
	protected void keyReleasedTxtBusacr(KeyEvent arg0) {
		
		

		
		
	}
	protected void actionPerformedBtnReporte(ActionEvent arg0) {

		if (rbtNombre.isSelected() == true) {

			String sa = txtBusacr.getText();
			lista = cliente.consulta(1, sa);
			System.out.println("obt " + sa);
		} else if (rbtSexo.isSelected() == true) {

			String s = cboGenero.getSelectedItem().toString();
			System.out.println("cbo " + s);
			lista = cliente.consulta(2, s);
		}
		
		DefaultTableModel modelo = (DefaultTableModel) tblTabla.getModel();
		modelo.setRowCount(0);
		for(Cliente c:lista) {
			Object row[]= {c.getCodi(),c.getNombre(),c.getApellido(),c.getDni(),c.getSexo()};
			modelo.addRow(row);
		}
		
		//1 necesito en nombre del archivo primerpaarametro
		String name="reporteCliente.jasper";
		//2 necesito el origen de datos a listar
		//convertir ese array en un JRBeanCollectionDataSource
		JRBeanCollectionDataSource data= new JRBeanCollectionDataSource(lista);
		//llamar al metodo generar reporte y pasar sus parametros
		//ese metodo genera me devuelve un jasperPrint por eso lo almaceno ahi
		JasperPrint jasper =GeneradorReporte.genera(name, data, null);
		
		//crear visor y adicionar el objeto jasper 
		//esto es para botom de opciones
			JRViewer viewer=new JRViewer(jasper);
			
			panelrepo.removeAll();
			//adicionar al panelReporte el objeto viewer
			panelrepo.add(viewer);
			//volver a generar el panelReporte
			panelrepo.repaint();
			//revalidar panel
			panelrepo.revalidate();
		    
	}
}
