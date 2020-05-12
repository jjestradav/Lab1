/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Profesor;
import entity.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import service.ServiceProfesor;
import service.ServiceUsuario;

/**
 *
 * @author jonathan
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet/login", "/login", "/logout"})
public class LoginServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
          {
        if (request.getServletPath().equals("/LoginServlet/login")) {
            login(request, response);
        }
        else
             if (request.getServletPath().equals("/logout")) {
          logout(request, response);
        }
    }

    private final ServiceUsuario usuarioRepo = ServiceUsuario.getInstance();
    private final ServiceProfesor profeRepo = ServiceProfesor.getInstance();

    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            String username = request.getParameter("username");
            String password = request.getParameter("password");
           
            Usuario us = new Usuario(username, password, "");
             System.out.println(us);
            Optional<Usuario> optUs = this.usuarioRepo.getUsuario(us);
            if (!optUs.isPresent()) {
                throw new Exception();
            }
            if(optUs.get().getRol().equals("superuser")){
                 HttpSession session=request.getSession(true);
            session.setAttribute("superuser", optUs.get());
            }
            else{
            Profesor profe=new Profesor();
            profe.setCedula(username);
            Optional<Profesor> optProfe = this.profeRepo.getProfesor(profe);
            if (!optProfe.isPresent() ) 
                throw new Exception();
            
            HttpSession session=request.getSession(true);
            session.setAttribute("profesor", optProfe.get());
            }
            request.getRequestDispatcher("/ciclosServlet/goToCiclos").forward(request, response);
            return;
            


        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(500);
            request.getSession(true).setAttribute("msgError", "Usuario o contraseña incorrectos");
            response.sendRedirect("/Lab1/login.jsp");
            return;


            //throw new ServletException("Error al Agregar acuerdo");
        }
        
            

    }
    
    private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
    try{
        HttpSession session=request.getSession(true);
        session.invalidate();
        response.sendRedirect("/Lab1/login.jsp");
        
    }
    catch(Exception e){
         response.sendRedirect("/Lab1/login.jsp");
    }
    
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */


    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */


    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    
        @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    
        @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
