package com.Empresa.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.Empresa.controlador.MySqlFacturaDAO;
import com.Empresa.entidades.Detalle_factura;
import com.Empresa.entidades.Factura;
import com.mysql.cj.jdbc.ha.MultiHostMySQLConnection;
import com.utils.Libreria;

import java.awt.event.ActionListener;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;

public class frmFactura extends JDialog implements ActionListener {
	
	//global
	MySqlFacturaDAO factu= new MySqlFacturaDAO();

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNumBoleta;
	private JTextField txtFecha;
	public static JTextField txtcodPro;
	public static JTextField txtNombrepro;
	public static JTextField txtPrecioPro;
	private JTextField txtCantidad;
	private JTable tblTabla;
	public static JTextField txtCodiCli;
	public static JTextField txtNombreCli;
	public static JTextField txtApellidoCli;
	public static JTextField txtdni;
	public static JTextField txtSexo;
	private JButton button;
	private JButton btnBuscar;
	private JButton btnAgregar;
	private JButton btnEliminar;
	private JTextField txtTotal;
	private JButton btnRegistrar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			frmFactura dialog = new frmFactura();
			dialog.setLocationRelativeTo(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public frmFactura() {
		setModal(true);
		setBounds(100, 100, 748, 631);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 99, 71));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblFactura = new JLabel("Factura");
		lblFactura.setOpaque(true);
		lblFactura.setHorizontalAlignment(SwingConstants.CENTER);
		lblFactura.setForeground(Color.WHITE);
		lblFactura.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblFactura.setBackground(Color.BLACK);
		lblFactura.setBounds(10, 11, 708, 47);
		contentPanel.add(lblFactura);
		
		JLabel lblNro = new JLabel("Nro");
		lblNro.setFont(new Font("Dialog", Font.BOLD, 13));
		lblNro.setBounds(461, 84, 46, 14);
		contentPanel.add(lblNro);
		
		txtNumBoleta = new JTextField();
		txtNumBoleta.setText("1000");
		txtNumBoleta.setBounds(517, 81, 140, 20);
		contentPanel.add(txtNumBoleta);
		txtNumBoleta.setColumns(10);
		
		JLabel lblFecha = new JLabel("Fecha de emision:");
		lblFecha.setForeground(new Color(255, 255, 255));
		lblFecha.setBackground(new Color(173, 255, 47));
		lblFecha.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 14));
		lblFecha.setBounds(20, 75, 140, 30);
		contentPanel.add(lblFecha);
		
		txtFecha = new JTextField();
		txtFecha.setEditable(false);
		txtFecha.setBounds(189, 82, 117, 20);
		contentPanel.add(txtFecha);
		txtFecha.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(64, 224, 208));
		panel.setBorder(new TitledBorder(new LineBorder(new Color(65, 105, 225), 2, true), "Datos del Producto", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(220, 20, 60)));
		panel.setBounds(25, 237, 677, 101);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblCodigo = new JLabel("Codigo:");
		lblCodigo.setFont(new Font("Dialog", Font.BOLD, 13));
		lblCodigo.setBounds(17, 30, 59, 20);
		panel.add(lblCodigo);
		
		txtcodPro = new JTextField();
		txtcodPro.setEditable(false);
		txtcodPro.setBounds(86, 28, 117, 20);
		panel.add(txtcodPro);
		txtcodPro.setColumns(10);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(this);
		btnBuscar.setBounds(225, 14, 89, 40);
		panel.add(btnBuscar);
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(this);
		btnAgregar.setBounds(324, 14, 89, 40);
		panel.add(btnAgregar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(this);
		btnEliminar.setBounds(436, 14, 89, 34);
		panel.add(btnEliminar);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Dialog", Font.BOLD, 13));
		lblNombre.setBounds(17, 65, 59, 14);
		panel.add(lblNombre);
		
		txtNombrepro = new JTextField();
		txtNombrepro.setEditable(false);
		txtNombrepro.setBounds(83, 65, 155, 20);
		panel.add(txtNombrepro);
		txtNombrepro.setColumns(10);
		
		JLabel lblPrecio = new JLabel("Precio:");
		lblPrecio.setFont(new Font("Dialog", Font.BOLD, 13));
		lblPrecio.setBounds(248, 65, 46, 14);
		panel.add(lblPrecio);
		
		txtPrecioPro = new JTextField();
		txtPrecioPro.setEditable(false);
		txtPrecioPro.setBounds(312, 65, 128, 20);
		panel.add(txtPrecioPro);
		txtPrecioPro.setColumns(10);
		
		JLabel lblCantidad = new JLabel("Cantidad:");
		lblCantidad.setFont(new Font("Dialog", Font.BOLD, 13));
		lblCantidad.setBounds(464, 71, 65, 14);
		panel.add(lblCantidad);
		
		txtCantidad = new JTextField();
		txtCantidad.setText("1");
		txtCantidad.setBounds(538, 63, 101, 20);
		panel.add(txtCantidad);
		txtCantidad.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(new Color(147, 112, 219));
		scrollPane.setBounds(25, 349, 677, 155);
		contentPanel.add(scrollPane);
		
		tblTabla = new JTable();
		tblTabla.setBackground(new Color(240, 255, 255));
		tblTabla.setBorder(new LineBorder(new Color(255, 255, 0), 2));
		tblTabla.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Codigo", "Nombre", "Precio", "Cantidad", "Importe"
			}
		));
		tblTabla.getColumnModel().getColumn(0).setPreferredWidth(96);
		tblTabla.getColumnModel().getColumn(1).setPreferredWidth(151);
		tblTabla.getColumnModel().getColumn(2).setPreferredWidth(107);
		tblTabla.getColumnModel().getColumn(3).setPreferredWidth(107);
		tblTabla.getColumnModel().getColumn(4).setPreferredWidth(158);
		tblTabla.setFillsViewportHeight(true);
		scrollPane.setViewportView(tblTabla);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(64, 224, 208));
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 2), "Datos del Cliente", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 0, 0)));
		panel_1.setBounds(25, 125, 677, 101);
		contentPanel.add(panel_1);
		
		JLabel label = new JLabel("Codigo:");
		label.setFont(new Font("Dialog", Font.BOLD, 13));
		label.setBounds(17, 33, 59, 18);
		panel_1.add(label);
		
		txtCodiCli = new JTextField();
		txtCodiCli.setEditable(false);
		txtCodiCli.setColumns(10);
		txtCodiCli.setBounds(86, 33, 117, 20);
		panel_1.add(txtCodiCli);
		
		button = new JButton("Buscar");
		button.addActionListener(this);
		button.setBounds(227, 24, 89, 38);
		panel_1.add(button);
		
		JLabel label_1 = new JLabel("Nombre:");
		label_1.setFont(new Font("Dialog", Font.BOLD, 13));
		label_1.setBounds(17, 72, 59, 14);
		panel_1.add(label_1);
		
		txtNombreCli = new JTextField();
		txtNombreCli.setEditable(false);
		txtNombreCli.setColumns(10);
		txtNombreCli.setBounds(85, 70, 151, 20);
		panel_1.add(txtNombreCli);
		
		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setFont(new Font("Dialog", Font.BOLD, 13));
		lblApellido.setBounds(246, 73, 74, 14);
		panel_1.add(lblApellido);
		
		txtApellidoCli = new JTextField();
		txtApellidoCli.setEditable(false);
		txtApellidoCli.setColumns(10);
		txtApellidoCli.setBounds(320, 73, 144, 20);
		panel_1.add(txtApellidoCli);
		
		JLabel lblDni = new JLabel("DNI:");
		lblDni.setFont(new Font("Dialog", Font.BOLD, 13));
		lblDni.setBounds(485, 72, 65, 14);
		panel_1.add(lblDni);
		
		txtdni = new JTextField();
		txtdni.setEditable(false);
		txtdni.setColumns(10);
		txtdni.setBounds(537, 70, 101, 20);
		panel_1.add(txtdni);
		
		JLabel lblSexo = new JLabel("Sexo:");
		lblSexo.setFont(new Font("Dialog", Font.BOLD, 13));
		lblSexo.setBounds(364, 35, 69, 14);
		panel_1.add(lblSexo);
		
		txtSexo = new JTextField();
		txtSexo.setEditable(false);
		txtSexo.setBounds(408, 33, 122, 20);
		panel_1.add(txtSexo);
		txtSexo.setColumns(10);
		
		JButton btnNuevo = new JButton("Nuevo");
		btnNuevo.setBounds(38, 530, 89, 38);
		contentPanel.add(btnNuevo);
		
		btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(this);
		btnRegistrar.setBounds(148, 530, 89, 38);
		contentPanel.add(btnRegistrar);
		
		JButton btnNewButton = new JButton("Salir");
		btnNewButton.setBounds(253, 530, 89, 38);
		contentPanel.add(btnNewButton);
		
		JLabel lblTotal = new JLabel("Total");
		lblTotal.setBounds(490, 521, 46, 14);
		contentPanel.add(lblTotal);
		
		txtTotal = new JTextField();
		txtTotal.setBounds(546, 518, 127, 20);
		contentPanel.add(txtTotal);
		txtTotal.setColumns(10);
		
		//damos formato a la fecha del sistema
		SimpleDateFormat ndf= new SimpleDateFormat("yyyy-MM-dd");
		Date fe = new Date();
		ndf.format(fe);
		txtFecha.setText(ndf.format(fe));
		
		codiFa();
		
	}
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnRegistrar) {
			actionPerformedBtnRegistrar(arg0);
		}
		if (arg0.getSource() == btnEliminar) {
			actionPerformedBtnEliminar(arg0);
		}
		if (arg0.getSource() == btnAgregar) {
			actionPerformedBtnAgregar(arg0);
		}
		if (arg0.getSource() == btnBuscar) {
			actionPerformedBtnBuscar(arg0);
		}
		if (arg0.getSource() == button) {
			actionPerformedButton(arg0);
		}
	}
	protected void actionPerformedButton(ActionEvent arg0) {
		frmBuscarCliente  te = new frmBuscarCliente();
		te.setLocationRelativeTo(this);
		te.setVisible(true);
		
	}
	protected void actionPerformedBtnBuscar(ActionEvent arg0) {
		frmBuscarProducto pro = new frmBuscarProducto();
		pro.setLocationRelativeTo(this);
		pro.setVisible(true);
		
	}
	protected void actionPerformedBtnAgregar(ActionEvent arg0) {
		
		//
		double c;
		boolean estado=false;
	    String cti= txtCantidad.getText();
	    
	    if(cti.trim().length()==0) {
	    	JOptionPane.showMessageDialog(null, "Ingrese Cantidad");
	    }else if(cti.matches("[1-9]")==false) {
	    	JOptionPane.showMessageDialog(null, "cantidad Max:9");
	    }else {

		int ca= Integer.parseInt(txtCantidad.getText());
		double pr= Double.parseDouble(txtPrecioPro.getText());
		String co= txtcodPro.getText();
		String no= txtNombrepro.getText();
		int codigo;
		for(int a=0;a<tblTabla.getRowCount();a++) {
			 codigo=Integer.parseInt(tblTabla.getValueAt(a,0).toString());
			if(codigo == Integer.parseInt(co)) {
				estado=true;
				break;
			}
		}
		
		//---------
		if(estado==false) {
			DefaultTableModel modelo= (DefaultTableModel) tblTabla.getModel();
			Object row[]= {co,no,pr,ca,(ca*pr)};
			modelo.addRow(row);
			c= sumar();
			txtTotal.setText(""+c);			
		}else {
			JOptionPane.showMessageDialog(null, "Ya esta Registrado");
		}
		

		
	  }
	    //------------
	    
	}
	public double sumar() {
		double acu=0;
	
		for(int a=0;a<tblTabla.getRowCount();a++) {
			acu+= Double.parseDouble(tblTabla.getValueAt(a,4).toString());
		}
		return acu;
	}
	
	protected void actionPerformedBtnEliminar(ActionEvent arg0) {
		
		int fila= tblTabla.getSelectedRow();
		DefaultTableModel modelo= (DefaultTableModel) tblTabla.getModel();
		modelo.removeRow(fila);
		txtTotal.setText(""+sumar());
		
	}
	
	public void codiFa() {
		
	   int co=	factu.numboleta();
	   txtNumBoleta.setText(""+co);
	}
	
	protected void actionPerformedBtnRegistrar(ActionEvent arg0) {
		
		//primer insert
		//luego invoco al metodo
		Factura f= new Factura();
		int co=Integer.parseInt(txtNumBoleta.getText());
		
		int cli= Integer.parseInt(txtCodiCli.getText());
		int dn= Integer.parseInt(txtdni.getText());
		
		f.setCodifactura(co);
		f.setCodiCliente(cli);
		f.setFecha(txtFecha.getText());
		
		f.setCodiEmpl(frmLogin.co);
		f.setTotal(Double.parseDouble(txtTotal.getText()));
		//segundo insert 
		//como el parametro es un arraylis
		//tengo que dar vueltas sobre el arraylist y pasar los valores
		//ala entidad detalle
		ArrayList<Detalle_factura> deta= new ArrayList<Detalle_factura>();
		for(int a=0;a<tblTabla.getRowCount();a++) {
			// crar un objeto
			Detalle_factura d = new Detalle_factura();
			d.setNumfactura(co);
			d.setCodiProducto(Integer.parseInt(tblTabla.getValueAt(a,0).toString()));
			d.setCantidad(Integer.parseInt(tblTabla.getValueAt(a,3).toString()));
			
			deta.add(d); 
		}
		
		  int salida=factu.factura(f, deta);
		if(salida ==-1) {

			JOptionPane.showMessageDialog(null, "ERror");
		}else {
			JOptionPane.showMessageDialog(null,"Registrado");
		}
		
		
	}
}
