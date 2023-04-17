package co.empresa.imc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Paciente {
	
	private Integer id;
	private String documento;
	private String nombre;
	private String apellido;
	private String email;
	private String genero;
	private String fechanacimiento;
	private String telefono;
	private String direccion;
	private Integer peso;
	private Integer estatura;

}
