package co.empresa.imc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexionPostgreSQL extends ConexionBase implements Conexion {
	
	private Connection con=null;
	private static ConexionPostgreSQL db;
	private PreparedStatement preparedStatement;
	
	private final static String url= "database-1.ct3gev1bipds.us-east-2.rds.amazonaws.com";
    private final static String dbName = "testpweb";
    private final static String driver = "org.postgresql.Driver";
    private final static String userName = "student";
    private final static String password = "student22";

	public ConexionPostgreSQL() {
		super(driver, url, dbName, userName, password);
	}
	public static ConexionPostgreSQL getConexion(){
		if ( db == null ) {
            db = new ConexionPostgreSQL();
        }
		return db;
	}
	
	public PreparedStatement setPreparedStatement(String sql) throws SQLException {
		if ( db == null ) {
            db = new ConexionPostgreSQL();
        }
		
		this.preparedStatement = con.prepareStatement(sql);
		return this.preparedStatement;
	}
	

}
