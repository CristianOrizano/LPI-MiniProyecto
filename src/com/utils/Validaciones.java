package com.utils;

public class Validaciones {
	public static final String TEXTO = "[a-zA-Z��������������\\s]{3,10}";
	public static final String DNI = "[0-9]{8}";
	public static final String NUM_HIJOS = "[0-9]||[1][0]";
	public static final String SUELDO = "(\\d+)|(\\d+[.]\\d{1,2})";
	public static final String PREMIO = "(\\d+)|(\\d+[.]\\d{1})";
	public static final String PLACA = "[A-Z]{2}\\d{4}";
	public static final String STOCK = "\\d+";
	public static final String FECHA = "(0?[1-9]|[12][0-9]|3[01])[-/](0?[1-9]|1[012])[-/]((19|20)\\d\\d)";
	public static final String CORREO = "[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})"; 
	public static final String DIRECCION ="[a-zA-Z��������������\\s\\d.]{2,100}";
	public static final String EDAD="[0-9]{2}";
	public static final String TELEFONO="[0-9]{9}";
	//public static final String SEXO = "[a-zA-Z��������������\\s]{8,9}";
	public static final String USUARIO = "[a-zA-Z��������������\\s.\\d]{8,10}";
	public static final String PASSWORD = "[.a-zA-Z��������������\\s\\d]{6,15}";

}
