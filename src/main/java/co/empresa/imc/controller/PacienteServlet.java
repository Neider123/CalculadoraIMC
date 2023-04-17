package co.empresa.imc.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.edu.ufps.crud.dao.UserDAO;
import co.edu.ufps.crud.dao.UserDAOFactory;
import co.edu.ufps.crud.dao.UserDAOJdbc;
import co.edu.ufps.crud.dao.UserDAOSingleton;
import co.edu.ufps.crud.model.User;
import co.empresa.imc.dao.PacienteDAO;
import co.empresa.imc.model.Paciente;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/")
public class PacienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private PacienteDAO pacienteDAO;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PacienteServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void init() {

		String patron = getServletContext().getInitParameter("base");
		String motor = getServletContext().getInitParameter("motor");
		//pacienteDAO = UserDAOFactory.getUserDAO(patron, motor);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getServletPath();

		System.out.println(action);
		try {
			switch (action) {
			case "/new":
				showNewForm(request, response);
				break;
			case "/insert":
				insertUser(request, response);
				break;
			case "/delete":
				deleteUser(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
				break;
			case "/update":
				updateUser(request, response);
				break;
			default:
				listUser(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
		dispatcher.forward(request, response);
	}

	private void insertUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String documento = request.getParameter("documento");
		String nombre = request.getParameter("nombre");
		String apellido = request.getParameter("apellido");
		String email = request.getParameter("email");
		String genero = request.getParameter("genero");
		String fechanacimiento = request.getParameter("fechanacimiento");
		String telefono = request.getParameter("telefono");
		;
		String direccion = request.getParameter("direccion");
		Integer peso = Integer.parseInt(request.getParameter("peso"));
		Integer estatura = Integer.parseInt(request.getParameter("estatura"));
		Paciente newPaciente = new Paciente(id, documento, nombre, apellido, email, genero, fechanacimiento, telefono,
				direccion, peso, estatura);
		pacienteDAO.insertUser(newPaciente);
		response.sendRedirect("list");
	}

	private void listUser(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Paciente> listUser = pacienteDAO.selectAllUsers();
		request.setAttribute("listPaciente", listUser);
		RequestDispatcher dispatcher = request.getRequestDispatcher("lista_pacientes.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Paciente existingUser = pacienteDAO.selectUser(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
		request.setAttribute("user", existingUser);
		dispatcher.forward(request, response);

	}

	private void updateUser(HttpServletRequest request, HttpServletResponse response)
		    throws SQLException, IOException {
		        int id = Integer.parseInt(request.getParameter("id"));
		        String documento = request.getParameter("documento");;
            	String nombre = request.getParameter("nombre");
            	String apellido = request.getParameter("apellido");
            	String email = request.getParameter("email");
            	String genero = request.getParameter("genero");
            	String fechanacimiento =request.getParameter("fechanacimiento");
            	String telefono = request.getParameter("telefono");;
            	String direccion =  request.getParameter("direccion");
                Integer peso = Integer.parseInt(request.getParameter("peso"));
            	Integer estatura = Integer.parseInt(request.getParameter("estatura"));
		        Paciente paciente = new Paciente(id, documento,nombre, apellido, email , genero ,fechanacimiento , telefono ,direccion , peso ,estatura);
		        pacienteDAO.updateUser(paciente);
		        response.sendRedirect("list");
		    }

	private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		pacienteDAO.deleteUser(id);
		response.sendRedirect("list");

	}

}
