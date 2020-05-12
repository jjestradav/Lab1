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
        <link href="css/body.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        
                     <%@include file="/header.jsp" %>
                     
                     
        <div class="container">
            <div class="d-flex justify-content-center h-100">
                <div class="d-flex justify-content-center form_container" style="width:40%; height:50%">
                    <div class="card" style="margin-top: 30%; width: 100%">
                        <div class="card-body">
                            <h5 class="card-title" style="text-align: center">Cursos Disponibles</h5>
                            <form method="POST" id="loginForm" action="CursosServlet/goToGrupos">
                                <!--                        action="/Kronos/LoginServlet"-->
                                <div class="row">


                                    <div class="col">
                                        <div class="form-group">
                                            <select class="form-control" name="cursosSelect">
                                                <%
                                                    for (Curso curso : (List<Curso>) session.getAttribute("cursos")) {
                                                %>   
                                                <option value="<%=curso.getCodigo()%>"><%= curso.getNombre() %></option>     
                                                <% }
                                                %>

                                            </select>
                                        </div>
                                    </div>

                                </div>         
                                <div class="row">
                                    <div class="col-5"></div>
                                    <div class="col">
                                    <button type="submit" class="btn btn-info">Ver</button>
                                    </div>
                                </div>



                            </form>

                        </div>
                    </div>

                </div>
            </div>
        </div>
        <script src="js/jquery-3.4.1.min.js" type="text/javascript"></script>
        <script src="js/bootstrap.min.js" type="text/javascript"></script>
    </body>
</html>
