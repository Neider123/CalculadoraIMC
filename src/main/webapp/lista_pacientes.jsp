<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
        <html>

        <head>
            <title>Lista de Pacientes</title>
            <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        </head>

        <body>

            <header>
                <nav class="navbar navbar-expand-md navbar-dark" style="background-color: tomato">
                    <div>
                        <a href="https://www.javaguides.net" class="navbar-brand"> Lista de pacientes </a>
                    </div>

                    <ul class="navbar-nav">
                        <li><a href="<%=request.getContextPath()%>/list" class="nav-link">Pacientes</a></li>
                    </ul>
                </nav>
            </header>
            <br>

            <div class="row">
                <!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

                <div class="container">
                    <h3 class="text-center">Pacientes</h3>
                    <hr>
                    <div class="container text-left">

                        <a href="<%=request.getContextPath()%>/new" class="btn btn-success">Add
     New User</a>
                    </div>
                    <br>
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th>id</th>
                                <th>documento</th>
                                <th>nombre</th>
                                <th>email</th>
                                <th>genero</th>
                                <th>fechadenacimiento</th>
                                <th>telefono</th>
                                <th>Direccion</th>
                                <th>Peso</th>
                                <th>Altura</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <!--   for (Todo todo: todos) {  -->
                            <c:forEach var="pacientes" items="${listPacientes}">

                                <tr>
                                    <td>
                                        <c:out value="${pacientes.id}" />
                                    </td>
                                    <td>
                                        <c:out value="${pacientes.documento}" />
                                    </td>
                                    <td>
                                        <c:out value="${paciente.email}" />
                                    </td>
                                    <td>
                                        <c:out value="${paciente.genero}" />
                                    </td>
                                     <td>
                                        <c:out value="${paciente.fechadenacimiento}" />
                                    </td>
                                     <td>
                                        <c:out value="${paciente.telefono}" />
                                    </td>
                                     <td>
                                        <c:out value="${paciente.direccion}" />
                                    </td>
                                     <td>
                                        <c:out value="${paciente.peso}" />
                                    </td>
                                     <td>
                                        <c:out value="${paciente.altura}" />
                                    </td>
                                    <td><a href="edit?id=<c:out value='${pacientes.id}' />">Edit</a> &nbsp;&nbsp;&nbsp;&nbsp; <a href="delete?id=<c:out value='${pacientes.id}' />">Delete</a></td>
                                </tr>
                            </c:forEach>
                            <!-- } -->
                        </tbody>

                    </table>
                </div>
            </div>
        </body>

        </html>