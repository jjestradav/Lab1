/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Curso;
import entity.Profesor;
import entity.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import service.ServiceGrupo;

/**
 *
 * @author jonathan
 */
@WebServlet(name = "CursosServlet", urlPatterns = {"/CursosServlet/goToGrupos"})
public class CursosServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private final ServiceGrupo grupoRepo = ServiceGrupo.getInstance();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getServletPath().equals("/CursosServlet/goToGrupos")) {
            goToGrupos(request, response);
        }
    }

    private void goToGrupos(HttpServletRequest request, HttpServletResponse response) {
        try {
            int curso = Integer.parseInt(request.getParameter("cursosSelect"));
            Curso cur = new Curso();
            cur.setCodigo(curso);
            HttpSession session = request.getSession(true);

            Usuario us = (Usuario) session.getAttribute("superuser");
            if (us != null) {
                session.setAttribute("grupos", this.grupoRepo.buscarGrupoSuperUser(cur));
            } else {
                Profesor profe = (Profesor) session.getAttribute("profesor");
                session.setAttribute("Selectedcurso", cur);
                session.setAttribute("grupos", this.grupoRepo.buscarGrupoPorCurso(cur, profe));
            }
            response.sendRedirect("/Lab1/grupos.jsp");

        } catch (Exception e) {

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
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
