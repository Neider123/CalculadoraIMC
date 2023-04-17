package co.empresa.imc.model;

import lombok.Data;

@Data
public class Estudiante {
	
	private Integer id;
	private String documento;
	private String nombre;
	private String apellido;
	private String genero;
	private String fechanacimiento;
	private String telefono;
	private String direccion;
	private Integer peso;
	private Integer estatura;

}
