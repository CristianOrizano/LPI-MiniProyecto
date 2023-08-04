package com.Empresa.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.Empresa.controlador.MySqlTrabajadorDAO;
import com.Empresa.entidades.ConsultaXEmple;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import java.awt.Font;
import javax.swing.UIManager;

public class frmconsultaJOIN extends JDialog implements ActionListener {
	//global
	MySqlTrabajadorDAO trabajador= new MySqlTrabajadorDAO();

	private final JPanel contentPanel = new JPanel();
	private JTable tblTabla;
	private JButton btnConsultar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			frmconsultaJOIN dialog = new frmconsultaJOIN();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public frmconsultaJOIN() {
		setBounds(100, 100, 577, 377);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(UIManager.getBorder("Menu.border"));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setForeground(Color.YELLOW);
		scrollPane.setBackground(Color.PINK);
		scrollPane.setFont(new Font("TeamViewer15", Font.BOLD, 13));
		scrollPane.setBorder(new LineBorder(new Color(32, 178, 170), 3, true));
		scrollPane.setBounds(10, 137, 541, 190);
		contentPanel.add(scrollPane);
		
		tblTabla = new JTable();
		tblTabla.setSelectionBackground(new Color(64, 224, 208));
		tblTabla.setSelectionForeground(new Color(173, 216, 230));
		tblTabla.setFillsViewportHeight(true);
		tblTabla.setForeground(new Color(255, 0, 0));
		tblTabla.setGridColor(new Color(30, 144, 255));
		tblTabla.setBackground(new Color(255, 250, 240));
		tblTabla.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 12));
		tblTabla.setBorder(new LineBorder(new Color(32, 178, 170), 2, true));
		tblTabla.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Codigo", "Nombre", "Apellido", "Sueldo", "Login", "Clave"
			}
		));
		tblTabla.getColumnModel().getColumn(0).setMinWidth(16);
		tblTabla.getColumnModel().getColumn(1).setPreferredWidth(129);
		tblTabla.getColumnModel().getColumn(2).setPreferredWidth(116);
		tblTabla.getColumnModel().getColumn(3).setPreferredWidth(103);
		tblTabla.getColumnModel().getColumn(4).setPreferredWidth(103);
		tblTabla.getColumnModel().getColumn(5).setPreferredWidth(95);
		scrollPane.setViewportView(tblTabla);
		
		btnConsultar = new JButton("Consultar");
		btnConsultar.addActionListener(this);
		btnConsultar.setBounds(203, 52, 89, 23);
		contentPanel.add(btnConsultar);
	}
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnConsultar) {
			actionPerformedBtnConsultar(arg0);
		}
	}
	protected void actionPerformedBtnConsultar(ActionEvent arg0) {
		
		ArrayList<ConsultaXEmple> data=trabajador.lisAll();
		//
		DefaultTableModel modelo= (DefaultTableModel) tblTabla.getModel();
		modelo.setRowCount(0);
		for(ConsultaXEmple c:data ) {
			Object row[]= {c.getCodi(),c.getNombre(),c.getApellddo(),c.getSueldo(),c.getLogin(),c.getClave()};
			modelo.addRow(row);
		}
		
		
	}
}
