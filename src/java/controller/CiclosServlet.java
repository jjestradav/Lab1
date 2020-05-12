/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Ciclo;
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
import service.ServiceCiclo;
import service.ServiceCurso;

/**
 *
 * @author jonathan
 */
@WebServlet(name = "ciclosServlet", urlPatterns = {"/ciclosServlet/goToCiclos","/ciclosServlet/goToCursos"})
public class CiclosServlet extends HttpServlet {

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
            throws ServletException, IOException {
        if(request.getServletPath().equals("/ciclosServlet/goToCiclos"))
            goToCiclos(request,response);
        else
              if(request.getServletPath().equals("/ciclosServlet/goToCursos"))
                  goToCursos(request,response);
    }
   
    private final ServiceCiclo ciclosRepo=ServiceCiclo.getInstance();
    private final ServiceCurso cursosRepo=ServiceCurso.getInstance();
    private void goToCiclos(HttpServletRequest request, HttpServletResponse response){
        try{
            
            request.getSession(true).setAttribute("ciclos",this.ciclosRepo.getCiclos());
            response.sendRedirect("/Lab1/ciclos.jsp");
            
            //request.getRequestDispatcher("/ciclos.jsp").forward(request, response);
        }
        
        catch(Exception e){
            System.out.println(e.getMessage());
            request.setAttribute("msgError", "Ha ocurrido un error");
        }
    }
    
    private void goToCursos(HttpServletRequest request, HttpServletResponse response){
        try{
            
            int ciclo=Integer.parseInt(request.getParameter("ciclosSelect"));
             Ciclo cic= new Ciclo();
            cic.setCodigo(ciclo);
          
            HttpSession session=request.getSession(true);
              Usuario us=(Usuario)session.getAttribute("superuser");
              if(us != null){
                  session.setAttribute("cursos", this.cursosRepo.getCursosSuperUser(cic));
              }
              else{
            Profesor profe=(Profesor)session.getAttribute("profesor");
            if(profe==null)
                throw new Exception();
            
           
            
            session.setAttribute("cursos", this.cursosRepo.getCursosPorProfesor(profe, cic));
              }
            response.sendRedirect("/Lab1/cursos.jsp");
                    
        }
        catch(Exception e){
            e.printStackTrace();
            
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
