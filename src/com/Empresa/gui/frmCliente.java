package com.Empresa.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.Empresa.controlador.MySqlClienteDAO;
import com.Empresa.entidades.Cliente;
import com.utils.Validaciones;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class frmCliente extends JInternalFrame implements ActionListener, MouseListener {
	
	//global 
	MySqlClienteDAO cliente= new MySqlClienteDAO();

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCodigo;
	private JTextField txtDni;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTable tblTabla;
	private JButton btnNuevo;
	private JButton btnAdicionar;
	private JButton btnActualizar;
	private JButton btnEliminar;
	private JComboBox cboSexo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			frmCliente dialog = new frmCliente();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public frmCliente() {
		setClosable(true);
		setMaximizable(true);
		setIconifiable(true);
		
		setBackground(Color.CYAN);
		setForeground(Color.RED);
		setBounds(100, 100, 674, 560);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblMantenimientoCliente = new JLabel("Mantenimiento Cliente");
		lblMantenimientoCliente.setHorizontalAlignment(SwingConstants.CENTER);
		lblMantenimientoCliente.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblMantenimientoCliente.setBounds(86, 28, 407, 31);
		contentPanel.add(lblMantenimientoCliente);
		
		JLabel lblCodigo = new JLabel("Codigo");
		lblCodigo.setBounds(28, 100, 64, 14);
		contentPanel.add(lblCodigo);
		
		JLabel lblDni = new JLabel("Dni");
		lblDni.setBounds(251, 100, 46, 14);
		contentPanel.add(lblDni);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(28, 161, 64, 14);
		contentPanel.add(lblNombre);
		
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(28, 220, 46, 14);
		contentPanel.add(lblApellido);
		
		JLabel lblSexo = new JLabel("Sexo");
		lblSexo.setBounds(246, 161, 58, 14);
		contentPanel.add(lblSexo);
		
		txtCodigo = new JTextField();
		txtCodigo.setBounds(86, 97, 116, 20);
		contentPanel.add(txtCodigo);
		txtCodigo.setColumns(10);
		
		txtDni = new JTextField();
		txtDni.setBounds(307, 97, 116, 20);
		contentPanel.add(txtDni);
		txtDni.setColumns(10);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(86, 158, 116, 20);
		contentPanel.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtApellido = new JTextField();
		txtApellido.setBounds(86, 217, 105, 20);
		contentPanel.add(txtApellido);
		txtApellido.setColumns(10);
		
		cboSexo = new JComboBox();
		cboSexo.setModel(new DefaultComboBoxModel(new String[] {"Masculino", "Femenino", "Ninguno"}));
		cboSexo.setBounds(302, 158, 105, 20);
		contentPanel.add(cboSexo);
		
		btnNuevo = new JButton("Nuevo");
		btnNuevo.addActionListener(this);
		btnNuevo.setBounds(467, 70, 106, 31);
		contentPanel.add(btnNuevo);
		
		btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(this);
		btnAdicionar.setBounds(467, 112, 106, 34);
		contentPanel.add(btnAdicionar);
		
		btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(this);
		btnActualizar.setBounds(467, 161, 106, 38);
		contentPanel.add(btnActualizar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(this);
		btnEliminar.setBounds(467, 208, 106, 38);
		contentPanel.add(btnEliminar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(24, 268, 583, 242);
		contentPanel.add(scrollPane);
		
		tblTabla = new JTable();
		tblTabla.addMouseListener(this);
		tblTabla.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Codigo", "Nombre", "Apellido", "Dni", "Sexo"
			}
		));
		tblTabla.getColumnModel().getColumn(0).setPreferredWidth(87);
		tblTabla.getColumnModel().getColumn(1).setPreferredWidth(143);
		tblTabla.getColumnModel().getColumn(2).setPreferredWidth(144);
		tblTabla.getColumnModel().getColumn(3).setPreferredWidth(106);
		tblTabla.getColumnModel().getColumn(4).setPreferredWidth(143);
		tblTabla.setFillsViewportHeight(true);
		scrollPane.setViewportView(tblTabla);
		
		//Para Seleccionar un fila
		tblTabla.setRowSelectionAllowed(true);
		tblTabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		//desabilita mover columna
		tblTabla.getTableHeader().setReorderingAllowed(false);
		//color 
		tblTabla.setSelectionBackground(Color.GREEN);
		//no se puede editar
		tblTabla.setDefaultEditor(Object.class, null);
		
		
		listado();
		corre();
	}
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnEliminar) {
			actionPerformedBtnEliminar(arg0);
		}
		if (arg0.getSource() == btnActualizar) {
			actionPerformedBtnActualizar(arg0);
		}
		if (arg0.getSource() == btnAdicionar) {
			actionPerformedBtnAdicionar(arg0);
		}
		if (arg0.getSource() == btnNuevo) {
			actionPerformedBtnNuevo(arg0);
		}
	}
	public void mensaje(String s) {
		JOptionPane.showMessageDialog(null, s);
	}
	protected void actionPerformedBtnNuevo(ActionEvent arg0) {
		txtNombre.setText("");
		txtApellido.setText("");
		txtDni.setText("");
		txtNombre.requestFocus();
		corre();
		
	}
	protected void actionPerformedBtnAdicionar(ActionEvent arg0) {
		
		int cod= Integer.parseInt(txtCodigo.getText());
		  Cliente re=cliente.buscar(cod);
		if(re==null) {
			String nom,ape,dni;
			nom=txtNombre.getText();
			ape= txtApellido.getText();
			dni= txtDni.getText();
			int codi=Integer.parseInt(txtCodigo.getText());
		   if(nom.trim().length()==0) {
			   mensaje("Campo Nombre Obligatorio");
		   }else if(nom.matches(Validaciones.TEXTO)==false){
			   mensaje("Nombre Min:3 y Max:10 caracteres");
		   }else if(ape.trim().length()==0) {
			   mensaje("Campo Apellido Obligatorio");
		   }else if(ape.matches("[a-zA-Z\\é\\á\\í\\ó\\ú\\s\\Ñ]{1,20}")==false) {
			   mensaje("Apellido Min:3 y Max:20 caracteres");
		   }else if(dni.trim().length()==0) {
			   mensaje("Campo DNI Obligatorio");
		   }else if(dni.matches("\\d{8}")==false) {
			   mensaje("DNI 8 digitos");
		   }else {
			   int dnd=Integer.parseInt(txtDni.getText());
			   String s= cboSexo.getSelectedItem().toString();
			   
			   Cliente bean=new Cliente();
			   bean.setCodi(codi);
			   bean.setNombre(nom);
			   bean.setApellido(ape);
			   bean.setDni(dnd);
			   bean.setSexo(s);
	           //
			   int salida= cliente.insert(bean);
			  
			   if(salida==-1) {
				   mensaje("Error de insertar");
			   }else {
				   mensaje("Agregado");
				   listado();
			   }
		}
		  
		   
	  }else {
		  mensaje("Ya existe codigo");
	  }
		
		
	}
	  public void listado() {
		         ArrayList<Cliente> data  =cliente.lisALl();
		  DefaultTableModel modelo= (DefaultTableModel) tblTabla.getModel();
		  modelo.setRowCount(0);
		  for(Cliente c:data) {
			  Object row[]= {c.getCodi(),c.getNombre(),c.getApellido(),c.getDni(),c.getSexo()};
			  modelo.addRow(row);
		  }
		  
		  
	  }
	  public void corre() {
		  int co=cliente.correla();
		  txtCodigo.setText(""+co);
	  }
	
	
	
	protected void actionPerformedBtnActualizar(ActionEvent arg0) {
		String nom,ape,dni;
		
		nom=txtNombre.getText();
		ape= txtApellido.getText();
		dni= txtDni.getText();
	    String s= cboSexo.getSelectedItem().toString();
	    String codi=txtCodigo.getText();
	    
	    //obtener fila selecionada
	    int fil = tblTabla.getSelectedRow();
	    
	    if(fil==-1) {
			   mensaje("selecione fila meco");
	    } else {
		
	    	
	   if(nom.trim().length()==0) {
		   mensaje("Campo Nombre Obligatorio");
		   
	   }else if(nom.matches("[a-zA-Z\\é\\á\\í\\ó\\ú\\s\\Ñ]{1,10}")==false){
		   mensaje("Nombre Min:3 y Max:10 caracteres");
	   }else if(ape.trim().length()==0) {
		   mensaje("Campo Apellido Obligatorio");
	   }else if(ape.matches("[a-zA-Z\\é\\á\\í\\ó\\ú\\s\\Ñ]{1,20}")==false) {
		   mensaje("Apellido Min:3 y Max:20 caracteres");
	   }else if(dni.trim().length()==0) {
		   mensaje("Campo DNI Obligatorio");
	   }else if(dni.matches("\\d{8}")==false) {
		   mensaje("DNI 8 digitos");
	   }else {
		   
		   Cliente bean=new Cliente();
		   bean.setCodi(Integer.parseInt(codi));
		   bean.setNombre(nom);
		   bean.setApellido(ape);
		   bean.setDni(Integer.parseInt(dni));
		   bean.setSexo(s);
           
		   int salida= cliente.update(bean);
			  
		   if(salida>0) {
			   mensaje("Actualizado");
			   listado();
		   }else {
			   mensaje("Error de Actualizar");
			   
		   }
	   }
		   
		  			  
     }

	}
	protected void actionPerformedBtnEliminar(ActionEvent arg0) {
		int co= Integer.parseInt(txtCodigo.getText());
		int fi=JOptionPane.showConfirmDialog(this, "¿Seguro de eliminar?", "eliminar", 0, 1,null);
		if(fi==0) {
			int ko=cliente.delete(co);
			if(ko>0) {
				mensaje("Eliminado");
				listado();
			}else {
				mensaje("Error en eliminacion");
			}
			 
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
		String no= tblTabla.getValueAt(fila, 1).toString();
		String ape= tblTabla.getValueAt(fila, 2).toString();
		String dni= tblTabla.getValueAt(fila, 3).toString();
		String se= tblTabla.getValueAt(fila, 4).toString();
		
		txtCodigo.setText(co);
		txtNombre.setText(no);
		txtApellido.setText(ape);
		txtDni.setText(dni);
		cboSexo.setSelectedItem(se);
		
	}
	public void select() {
int fila=tblTabla.getSelectedRow();
		
		String co= tblTabla.getValueAt(fila, 0).toString();
		String no= tblTabla.getValueAt(fila, 1).toString();
		String ape= tblTabla.getValueAt(fila, 2).toString();
		String dni= tblTabla.getValueAt(fila, 3).toString();
		String se= tblTabla.getValueAt(fila, 4).toString();
		
		txtCodigo.setText(co);
		txtNombre.setText(no);
		txtApellido.setText(ape);
		txtDni.setText(dni);
		cboSexo.setSelectedItem(se);
	}
}
