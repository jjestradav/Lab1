/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dto.AlumnoDTO;
import dto.GrupoAlumnoDTO;
import entity.Curso;
import entity.Grupo;
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
import service.ServiceGrupoAlumno;

/**
 *
 * @author jonathan
 */
@WebServlet(name = "GruposServlet", urlPatterns = {"/GruposServlet","/GruposServlet/goToNotas",
"/GruposServlet/updateNota","/GruposServlet/updatePage"})
public class GruposServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private final ServiceGrupoAlumno grupoAlumnoRepo=ServiceGrupoAlumno.getInstance();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                            if(request.getServletPath().equals("/GruposServlet/goToNotas"))
            goToNotas(request,response);
                            else 
                                if(request.getServletPath().equals("/GruposServlet/updateNota"))
                                    updateNota(request,response);
                            else
                                   if(request.getServletPath().equals("/GruposServlet/updatePage"))
                                       updatePage(request,response);
    }
    
    private void goToNotas(HttpServletRequest request, HttpServletResponse response){
        try{
            int grupo=Integer.parseInt(request.getParameter("gruposSelect"));
            HttpSession session=request.getSession(true);
                        Grupo gru=new Grupo();
            gru.setCodigo(grupo);
            Usuario us= (Usuario) session.getAttribute("seperuser");
            if(us!=null){
                
            }
            else{

            session.setAttribute("grupo",gru);
            session.setAttribute("notas", this.grupoAlumnoRepo.buscarGrupoPorProfesor(gru));
            }
            response.sendRedirect("/Lab1/notas.jsp");
            
        }
        catch(Exception e){
            
        }
        
        
    }
    
    private void updateNota(HttpServletRequest request, HttpServletResponse response) throws IOException{
        try{
            
           AlumnoDTO alumno= new AlumnoDTO();
           GrupoAlumnoDTO grupoAlumno=new GrupoAlumnoDTO();
           String nombre=request.getParameter("nombre");
           String cedula=request.getParameter("cedula");
           float nota=Float.parseFloat(request.getParameter("nota"));
           int grupo=Integer.parseInt(request.getParameter("grupo"));
           
           alumno.setCedula(cedula);
           alumno.setNombre(nombre);
           grupoAlumno.setAlumno(alumno);
           grupoAlumno.setNota(nota);
           grupoAlumno.setGrupo(grupo);
           System.out.println(grupoAlumno);
          boolean result= this.grupoAlumnoRepo.ActualizarNota(grupoAlumno);
          if(!result)
              throw new Exception();
           
        }
        catch(Exception e){
            response.sendError(500,"Ha ocurrido un error");
        }
        
    }
    
    private void updatePage(HttpServletRequest request, HttpServletResponse response) throws IOException{
        try{
            HttpSession session=request.getSession(true);
            Grupo grupo=(Grupo)session.getAttribute("grupo");
            
            if(grupo==null)
                throw new Exception();
        
                        session.setAttribute("notas", this.grupoAlumnoRepo.buscarGrupoPorProfesor(grupo));
                         response.sendRedirect("/Lab1/notas.jsp");
        }
        catch(Exception e){
            e.printStackTrace();
            response.sendError(500);
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
