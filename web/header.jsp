<%-- 
    Document   : header
    Created on : Apr 9, 2020, 7:25:58 PM
    Author     : jonathan
--%>

<%@page import="entity.Profesor"%>
<%@page import="entity.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
   
       <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <a class="navbar-brand" href="#">SISTEMA DE MATRICULA</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
        <a class="nav-link" href="ciclos.jsp">Registro de Notas <span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item">
          <% Profesor profe= (Profesor)session.getAttribute("profesor"); %>
           <a class="nav-link" ><%=(profe != null) ? profe.getNombre(): "SUPERUSER"  %></a>
      </li>
      <% if(session.getAttribute("superuser")!=null || session.getAttribute("profesor")!=null){ %>
           <li class="nav-item">
 
           <a class="nav-link" href="/Lab1/logout" >LOGOUT</a>
      </li>
      <%} %>
    </ul>
  </div>
</nav>
    </body>
</html>
