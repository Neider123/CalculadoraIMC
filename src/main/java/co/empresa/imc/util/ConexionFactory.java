package co.empresa.imc.util;

public class ConexionFactory {
	public static Conexion getConexion(String type) {
		
		switch(type) {
			case "mysql":
				return ConexionMySQL.getConexion();
			case "postgresql":
				return ConexionPostgreSQL.getConexion();
			default:
				return ConexionMySQL.getConexion();
		}
		
	}
}
