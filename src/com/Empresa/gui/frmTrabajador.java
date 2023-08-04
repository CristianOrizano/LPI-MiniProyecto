package com.Empresa.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import com.Empresa.Componente.JComboBoxDB;
import com.Empresa.controlador.MySqlTrabajadorDAO;
import com.Empresa.entidades.Trabajador;
import com.utils.Validaciones;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.Color;
import javax.swing.border.LineBorder;
import java.awt.Cursor;
import javax.swing.DefaultComboBoxModel;

public class frmTrabajador extends JFrame implements ActionListener, MouseListener, KeyListener, WindowListener {
	
	public MySqlTrabajadorDAO trabajdorDao = new MySqlTrabajadorDAO();

	private JPanel contentPane;
	private JTextField txtCodigo;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtSueldo;
	private JTextField txtHijos;
	private JTable tblTabla;
	private JButton btnNuevo;
	private JButton btnAdicionar;
	private JButton btnActualizar;
	private JButton btnEliminar;
	private JComboBox cboDistrito;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmTrabajador frame = new frmTrabajador();
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
	public frmTrabajador() {
		addWindowListener(this);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 746, 583);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 206, 209));
		contentPane.setForeground(new Color(32, 178, 170));
		contentPane.setBorder(new LineBorder(new Color(255, 255, 0), 3, true));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblMantenimientoTrabajador = new JLabel("Mantenimiento Trabajador");
		lblMantenimientoTrabajador.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblMantenimientoTrabajador.setHorizontalAlignment(SwingConstants.CENTER);
		lblMantenimientoTrabajador.setBounds(126, 11, 407, 31);
		contentPane.add(lblMantenimientoTrabajador);
		
		JLabel lblDataaa = new JLabel("Codigo");
		lblDataaa.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDataaa.setBounds(38, 75, 74, 22);
		contentPane.add(lblDataaa);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(38, 138, 86, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblApellido.setBounds(38, 190, 86, 22);
		contentPane.add(lblApellido);
		
		JLabel lblSueldo = new JLabel("Sueldo");
		lblSueldo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSueldo.setBounds(265, 79, 72, 14);
		contentPane.add(lblSueldo);
		
		JLabel lblHijos = new JLabel("Hijos");
		lblHijos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblHijos.setBounds(265, 134, 61, 22);
		contentPane.add(lblHijos);
		
		JLabel lblCodigoDistrito = new JLabel("Cod Distrito");
		lblCodigoDistrito.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCodigoDistrito.setBounds(238, 190, 106, 22);
		contentPane.add(lblCodigoDistrito);
		
		txtCodigo = new JTextField();
		txtCodigo.setBounds(103, 78, 105, 20);
		contentPane.add(txtCodigo);
		txtCodigo.setColumns(10);
		
		txtNombre = new JTextField();
		txtNombre.addKeyListener(this);
		txtNombre.setBounds(102, 137, 106, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtApellido = new JTextField();
		txtApellido.setBounds(103, 193, 105, 20);
		contentPane.add(txtApellido);
		txtApellido.setColumns(10);
		
		txtSueldo = new JTextField();
		txtSueldo.setBounds(336, 78, 106, 20);
		contentPane.add(txtSueldo);
		txtSueldo.setColumns(10);
		
		txtHijos = new JTextField();
		txtHijos.setBounds(336, 137, 106, 20);
		contentPane.add(txtHijos);
		txtHijos.setColumns(10);
		
		cboDistrito = new JComboBoxDB("select * from Distrito");
		cboDistrito.setModel(new DefaultComboBoxModel(new String[] {"seleccione", "100-San Borja", "101-San Juan", "102-La Victoria", "103-LOs olivos", "104-Bre\u00F1a"}));
		cboDistrito.setBounds(336, 193, 111, 20);
		contentPane.add(cboDistrito);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(new Color(255, 0, 255));
		scrollPane.setForeground(new Color(255, 0, 255));
		scrollPane.setBounds(10, 298, 710, 235);
		contentPane.add(scrollPane);
		
		tblTabla = new JTable();
		tblTabla.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		tblTabla.setFont(new Font("Yu Gothic", Font.BOLD, 12));
		tblTabla.setSelectionForeground(new Color(255, 255, 0));
		tblTabla.setGridColor(new Color(0, 0, 255));
		tblTabla.setForeground(new Color(0, 0, 0));
		tblTabla.setBackground(new Color(255, 250, 250));
		tblTabla.setSelectionBackground(new Color(30, 144, 255));
		tblTabla.addMouseListener(this);
		tblTabla.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Codigo", "Nombre", "Apellido", "Sueldo", "Hijos", "Cod Distrito"
			}
		));
		tblTabla.getColumnModel().getColumn(0).setPreferredWidth(63);
		tblTabla.getColumnModel().getColumn(1).setPreferredWidth(106);
		tblTabla.getColumnModel().getColumn(2).setPreferredWidth(164);
		tblTabla.getColumnModel().getColumn(3).setPreferredWidth(97);
		tblTabla.getColumnModel().getColumn(4).setPreferredWidth(83);
		tblTabla.getColumnModel().getColumn(5).setPreferredWidth(100);
		tblTabla.setFillsViewportHeight(true);
		scrollPane.setViewportView(tblTabla);
		
		btnNuevo = new JButton("Nuevo");
		btnNuevo.addActionListener(this);
		btnNuevo.setBounds(519, 73, 106, 31);
		contentPane.add(btnNuevo);
		
		btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(this);
		btnAdicionar.setBounds(519, 115, 106, 34);
		contentPane.add(btnAdicionar);
		
		btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(this);
		btnActualizar.setBounds(519, 160, 106, 38);
		contentPane.add(btnActualizar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(this);
		btnEliminar.setBounds(519, 212, 106, 38);
		contentPane.add(btnEliminar);
		
		//metodos
		corre();
		Listado();
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
	protected void actionPerformedBtnNuevo(ActionEvent arg0) {
		int num = trabajdorDao.codiEmple();
		txtCodigo.setText(""+num);
		Limpiar();
	}
	protected void actionPerformedBtnAdicionar(ActionEvent arg0) {
		try {
			String nombre,apellido,sueldo,hijos,codi,codDis;
			nombre = txtNombre.getText();
			apellido = txtApellido.getText();
			sueldo = txtSueldo.getText();
			hijos = txtHijos.getText();
			codi = txtCodigo.getText();
			codDis = cboDistrito.getSelectedItem().toString();
			
			if(nombre.trim().length()==0) {
				mensaje("Campo Nombre Obligatorio");
				txtNombre.requestFocus();
			}else if(nombre.matches("[a-zA-Z\\é\\á\\ú\\í\\ó\\s\\Ñ\\ñ]{3,20}")==false) {
				txtNombre.requestFocus();
				mensaje("Nombre Min:3 y Max:20 caracteres");
			}else if(codDis.equals("seleccione")) {
				mensaje("debe selecionar dis");
			}
			else if(apellido.trim().length()==0) {
					mensaje("Campo apellido obligatorio");
				}
			else if(apellido.matches("[a-zA-Z\\é\\á\\ú\\í\\ó\\s\\Ñ\\ñ]{5,30}")==false) {
						mensaje("Campo Apellido Min:5 y Max:30 Caracteres");
		            
			}else if(sueldo.trim().length()==0) {
				mensaje("Campo Sueldo Obligatorio");
				txtSueldo.requestFocus();
			}else if(sueldo.matches("([2-9]\\d||[1-2]\\d{2}||[3][0]{2})||([2-9]\\d[.]\\d{1,2}||[1-2]\\d{2}[.]\\d{1,2})")==false) {
				mensaje("Sueldo MIn:20  Max:300");
				txtSueldo.requestFocus();
			}else if(hijos.trim().length()==0) {
				mensaje("Campo Hijos Obligatorio");
				txtHijos.requestFocus();
			}else if(hijos.matches(Validaciones.NUM_HIJOS)==false) {
				mensaje("Hijos Min:1 y Max:10");
				txtHijos.requestFocus();;
			}else {
				
				int c= Integer.parseInt(txtCodigo.getText());
				 Trabajador t= trabajdorDao.ConsultaCodi(c);
				
				if(t == null) {
					   
					//crear objeto de la clase Trabajador
					Trabajador bean = new Trabajador();
					//asignar valor
					
					bean.setCodEmpl(Integer.parseInt(codi));
					bean.setNombre(nombre);
					bean.setApellido(apellido);
					bean.setSueldo(Double.parseDouble(sueldo));
					bean.setHijos(Integer.parseInt(hijos));
					//separar el codigo Dis del cbo
					String sp[]= codDis.split("-");
					//sp[0]=100
					//sp[1]=san borja
					//invocar al metodo insert
					bean.setCodDistrito(Integer.parseInt(sp[0]));
					int salida;
					salida=trabajdorDao.insert(bean);
					//lo almaceno en varible para validar
					if(salida>0) {
					mensaje("Registrado Correctamente");
					Listado();
					corre();
					Limpiar();
					}
					else {
						mensaje("Error en registro");
					 }
				
				
			}else {
				mensaje("Codigo ya existe");
			 }
				
		 }
			
		} catch (Exception e) {
			System.out.print("mal"+e);
		}
	
		
		
	}
	protected void actionPerformedBtnActualizar(ActionEvent arg0) {
		
		String nombre,apellido,sueldo,hijos,codi,codDis;
		nombre = txtNombre.getText();
		apellido = txtApellido.getText();
		sueldo = txtSueldo.getText();
		hijos = txtHijos.getText();
		codi= txtCodigo.getText();
		codDis= cboDistrito.getSelectedItem().toString();
		
		if(nombre.trim().length()==0) {
			mensaje("Campo Nombre Obligatorio");
			txtNombre.requestFocus();
		}else if(nombre.matches("[a-zA-Z\\é\\á\\ú\\í\\ó\\s\\Ñ\\ñ]{3,20}")==false) {
			txtNombre.requestFocus();
			mensaje("Nombre Min:3 y Max:20 caracteres");
		}else if(apellido.trim().length()==0) {
			mensaje("Campo Apellido Obligatorio");
			txtApellido.requestFocus();
		}else if(apellido.matches("[a-zA-Z\\é\\á\\ú\\í\\ó\\s\\Ñ\\ñ]{5,30}")==false) {
			mensaje("Campo Apellido Min:5 y Max:30 Caracteres");
			txtApellido.requestFocus();
		}else if(sueldo.trim().length()==0) {
			mensaje("Campo Sueldo Obligatorio");
			txtSueldo.requestFocus();
		}else if(sueldo.matches("([2-9]\\d||[1-2]\\d{2}||[3][0]{2})||([2-9]\\d[.]\\d{1,2}||[1-2]\\d{2}[.]\\d{1,2})")==false) {
			mensaje("Sueldo MIn:20  Max:300");
			txtSueldo.requestFocus();
		}else if(hijos.trim().length()==0) {
			mensaje("Campo Hijos Obligatorio");
			txtHijos.requestFocus();
		}else if(hijos.matches("[1-9]||[1][0]")== false) {
			mensaje("Hijos Min:1 y Max:10");
			txtHijos.requestFocus();
		}else {
			
			Trabajador bean = new Trabajador();
			bean.setNombre(nombre);
			bean.setApellido(apellido);
			bean.setCodEmpl(Integer.parseInt(codi));
			bean.setSueldo(Double.parseDouble(sueldo));
			bean.setHijos(Integer.parseInt(hijos));
			//separar
			String sp[]= codDis.split("-");
			bean.setCodDistrito(Integer.parseInt(sp[0]));
			
			int salida= trabajdorDao.update(bean);
			if(salida>0) {
				mensaje("Actualizado Correctamente");
			    Listado();
			}else
				mensaje("Error al ACtualizar");
		}
	  }
	protected void actionPerformedBtnEliminar(ActionEvent arg0) {
		int cody = Integer.parseInt(txtCodigo.getText());
		int a = JOptionPane.showConfirmDialog(this, "¿Seguro de Eliminar?", "Alerta", 0, 1, null);
		if(a==0) {
			
			int e=trabajdorDao.delet(cody);
			if(e>0) {
				mensaje("Eliminado");
	            Limpiar();
	            Listado();
	            corre();
			}else
				mensaje("Error en eliminacion");
			
		}
	}

	
	//metodo listado
	public void Listado() {
		
		//invocar al metodo lisAll
		//luego almacenar en un arraylist para dar vueltas
		ArrayList<Trabajador> tra= trabajdorDao.listAll();
		//obtener el modelo de la tabla
		DefaultTableModel modelo = (DefaultTableModel) tblTabla.getModel();
	
		//limpiamos la tabla a 0
		modelo.setRowCount(0);
		//hacemos un buble forech para dar vuelta sobre la tabla
		for(Trabajador tb:tra) {
			Object row[]= {tb.getCodEmpl(),tb.getNombre(),tb.getApellido(),tb.getSueldo(),tb.getHijos(),tb.getNomDistrito()};
			//adicionar 
			modelo.addRow(row);
			
		}
		
	}
	
	public void select() {
		
		int fila=tblTabla.getSelectedRow();
		String nombre,apellido,sueldo,hijos,codi,codDis;
		
		codi= tblTabla.getValueAt(fila, 0).toString();
		nombre= tblTabla.getValueAt(fila, 1).toString();
		apellido= tblTabla.getValueAt(fila, 2).toString();
		sueldo= tblTabla.getValueAt(fila, 3).toString();
		hijos= tblTabla.getValueAt(fila, 4).toString();
		codDis= tblTabla.getValueAt(fila, 5).toString();
		
		txtCodigo.setText(codi);
		txtNombre.setText(nombre);
		txtApellido.setText(apellido);
		txtSueldo.setText(sueldo);
		txtHijos.setText(hijos);
		cboDistrito.setSelectedItem(codDis);
	}
	
	public void mensaje(String s) {
		JOptionPane.showMessageDialog(null, s);
	}
	public void corre() {
		int num = trabajdorDao.codiEmple();
		txtCodigo.setText(""+num);
	}
	public void Limpiar() {
		
		txtNombre.setText("");
		txtApellido.setText("");
		txtSueldo.setText("");
		txtHijos.setText("");
		txtNombre.requestFocus();
		cboDistrito.setSelectedIndex(0);
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
		select();
		
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
	protected void keyReleasedTxtNombre(KeyEvent arg0) {
	}
	public void windowActivated(WindowEvent arg0) {
		if (arg0.getSource() == this) {
			windowActivatedThis(arg0);
		}
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
}
