package pe.edu.vallegrande.prueba.goods;

import java.sql.Connection;

import java.sql.SQLException;

import pe.edu.vallegrande.db.AccesoDB;

public class Conexion {

	public static void main(String[] args) {
		try {
			Connection cn = AccesoDB.getConnection();
			System.out.println("Conexion ok.");
			cn.close();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}

}
