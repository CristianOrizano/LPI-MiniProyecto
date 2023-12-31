package com.utils;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

public class   GeneradorReporte {
	
    //el metodo(genera) me devuelve un jasperPrint 
	                                  //nombredel reporte ,origen de dats ,parametro para el filtro pero mo usamos(null)
	public static JasperPrint genera(String fileName, JRBeanCollectionDataSource dataSource, HashMap<String, Object> parameters) {
		JasperPrint jasperPrint =null;
		try {
			FileInputStream fis = new FileInputStream(fileName);
			BufferedInputStream bufferedInputStream = new BufferedInputStream(fis);

			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(bufferedInputStream);
			jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

		} catch (JRException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return jasperPrint;
	}
}
