package com.Empresa.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Frame;
import java.awt.GridLayout;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class frmReporte extends JFrame {
	
	public static JPanel panelReporte;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmReporte frame = new frmReporte();
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
	public frmReporte() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 667, 389);
		getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
		
		panelReporte = new JPanel();
		panelReporte.setBorder(new LineBorder(Color.RED, 4));
		getContentPane().add(panelReporte);
		panelReporte.setLayout(new BorderLayout(0, 0));
	}
}
