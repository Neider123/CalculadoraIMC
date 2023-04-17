package co.empresa.imc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.edu.ufps.crud.model.User;
import co.edu.ufps.crud.util.Conexion;
import co.edu.ufps.crud.util.ConexionMySQL;
import co.empresa.imc.model.Paciente;

public class PacienteDAOJdbc implements PacienteDAO {
	
	private String jdbcURL = " database-1.ct3gev1bipds.us-east-2.rds.amazonaws.com/testpweb";
	
    private String jdbcUsername = "student";
    
    private String jdbcPassword = "Student22";
    
    private Conexion conexion;
    
    private static final String INSERT_PACIENTES_SQL = "INSERT INT paciente (documento, nombre, apellido,email,genero,fechanacimiento,telefono,direccion,peso,estatura) VALUES (?, ?, ?,?,?,?,?,?,?,?);";
    private static final String SELECT_PACIENTE_BY_ID = "SELECT id,documento,nombre,apellido,email,genero,fechanacimiento,telefono,direccion,peso,estatura FROM paciente WHERE id =?";
    private static final String SELECT_ALL_PACIENTES = "SELECT * FROM pacientes";
    private static final String DELETE_PACIENTES_SQL = "DELETE FROM pacientes WHERE id = ?;";
    private static final String UPDATE_PACIENTES_SQL = "UPDATE pacientes SET documento = ?,nombre= ?, apellido =? , email = ? ,genero = ? ,fechanacimiento = ? , telefono = ? ,direccion = ? , peso = ? , estatura = ? WHERE id = ?;";
    
    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }
    
    public void insertUser(Paciente paciente) throws SQLException {
        System.out.println(INSERT_PACIENTES_SQL);

        // try-with-resource statement will auto close the connection.
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PACIENTES_SQL)) {
            preparedStatement.setString(1, paciente.getDocumento());
            preparedStatement.setString(2, paciente.getNombre());
            preparedStatement.setString(4, paciente.getApellido());
            preparedStatement.setString(5, paciente.getEmail());
            preparedStatement.setString(6, paciente.getGenero());
            preparedStatement.setString(7, paciente.getFechanacimiento());
            preparedStatement.setString(8, paciente.getTelefono());
            preparedStatement.setString(9, paciente.getDireccion());
            preparedStatement.setInt(10, paciente.getPeso());
            preparedStatement.setInt(3, paciente.getEstatura());
        
            
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            //printSQLException(e);
        }
    }
    
    public Paciente selectUser(Integer id) {
        Paciente paciente = null;
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PACIENTE_BY_ID);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
            	String documento = rs.getString("documento");;
            	String nombre = rs.getString("nombre");
            	String apellido = rs.getString("apellido");
            	String email = rs.getString("email");
            	String genero = rs.getString("genero");
            	String fechanacimiento = rs.getString("fechanacimiento");
            	String telefono = rs.getString("telefono");;
            	String direccion =  rs.getString("direccion");
                Integer peso = rs.getInt("peso");
            	Integer estatura = rs.getInt("estatura");
                paciente = new Paciente(id, documento,nombre, apellido, email , genero ,fechanacimiento , telefono ,direccion , peso ,estatura);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return paciente;
    }

    public List <Paciente> selectAllUsers() {

        // using try-with-resources to avoid closing resources (boiler plate code)
        List < User > users = new ArrayList < > ();
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();

            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PACIENTES);) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String country = rs.getString("country");
                users.add(new User(id, name, email, country));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return users;
    }

    public boolean deleteUser(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE_PACIENTES_SQL);) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public boolean updateUser(User user) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_PACIENTES_SQL);) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getCountry());
            statement.setInt(4, user.getId());

            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }
    
    private void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
