<%@page import="dto.GrupoAlumnoDTO"%>
<%@page import="entity.GrupoAlumno"%>
<%@page import="entity.Curso"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <base href="http://localhost:9090/Lab1/" > 
        <title id="prinTitle">Cursos</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="css/datatables.min.css" rel="stylesheet" type="text/css"/>
        <link href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css"
              rel="stylesheet">

        <link
            href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/css/bootstrap.css"
            rel="stylesheet">

        <link
            href="https://cdn.datatables.net/1.10.20/css/dataTables.bootstrap4.min.css"
            rel="stylesheet">

        <link href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css"
              rel="stylesheet">
        <link href="css/body.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        
                     <%@include file="/header.jsp" %>
                     
        <div class="container">
            <div class="d-flex justify-content-center h-100">
                <div class="d-flex justify-content-center form_container" style="width:40%; height:50%">
                    <div class="card" style="margin-top: 30%; width: 100%">
                        <div class="card-body">
                            <h5 class="card-title" style="text-align: center">Alumnos</h5>
                            <table id="tableAlumnos"
                                   class="table table-striped table-bordered border-info"
                                   style="width: 100%">
                                <thead>
                                    <tr>
                                        <th style="text-align: center">Grupo</th>
                                        <th style="text-align: center">Cédula</th>
                                        <th style="text-align: center">Nombre</th>
                                        <th style="text-align: center">Nota</th>
                                    </tr>
                                </thead>
                                <tbody id='tableBody'>
                                    <%
                                        for (GrupoAlumnoDTO grupoAlmnno : (List<GrupoAlumnoDTO>) session.getAttribute("notas")) {


                                    %>
                                    <tr>
                                        <td><%= grupoAlmnno.getGrupo() %></td>
                                        <td><%= grupoAlmnno.getAlumno().getCedula()%></td>
                                        <td><%= grupoAlmnno.getAlumno().getNombre()%></td>
                                        <td><%= grupoAlmnno.getNota()%></td>

                                    </tr>
                                    <% }%>
                                </tbody>
                            </table>

                            <!-- Modal -->
                            <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                <div class="modal-dialog" role="document">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="exampleModalLabel">Editar Estudinate</h5>
                                            <button type="button" class="close" onclick="closeModal()" aria-label="Close">
                                                <span aria-hidden="true">&times;</span>
                                            </button>
                                        </div>
                                        <div class="modal-body">
                                            <form method="POST" action="GruposServlet/updateNota" id="modalForm">
                                                <div class="form-group">
                                                    <label for="cedula" class="form-label"> Cedula: </label>
                                                    <input type="text" class="form-control" id="cedula" name="cedula" readonly>
                                                </div>
                                                <div class="form-group">
                                                    <label for="name" class="form-label"> Nombre: </label>
                                                    <input type="text" class="form-control" id="name" name="nombre" readonly>
                                                </div>
                                                <div class="form-group">
                                                    <label for="nota" class="form-label"> Nota: </label>
                                                    <input type="text" class="form-control" id="nota" name="nota" >
                                                     <input type="hidden" class="form-control" id="grupo" name="grupo" >
                                                </div>


                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-secondary" onclick="closeModal()">Cerrar</button>
                                            <button type="submit" class="btn btn-primary">Guardar Cambios</button>
                                        </div>
                                        </form>

                                    </div>
                                </div>
                            </div>


                        </div>
                    </div>

                </div>
            </div>
        </div>
        <script src="js/jquery-3.4.1.min.js" type="text/javascript"></script>
        <script src="js/bootstrap.min.js" type="text/javascript"></script>
        <script
        src="https://cdnjs.cloudflare.com/ajax/libs/bootbox.js/5.4.0/bootbox.min.js"></script>
        <!-- 	<script th:src="@{/js/dataTables.bootstrap4.min.js}"></script> -->
        <!-- <script th:src="@{/js/jquery.dataTables.min.js}"></script> -->
        <script
        src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js"></script>
        <script
        src="https://cdn.datatables.net/1.10.20/js/dataTables.bootstrap4.min.js"></script>
        <script src="js/notas.js" type="text/javascript"></script>
    </body>
</html>
