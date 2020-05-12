/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import data.Session;
import dto.GrupoAlumnoDTO;
import java.awt.Point;
import javax.swing.JOptionPane;
import service.ServiceGrupoAlumno;
import view.EditarView;

/**
 *
 * @author jonathan
 */
public class EditController {
    
    private final ServiceGrupoAlumno grupoAlumnoRepo=ServiceGrupoAlumno.getInstance();
    private final Session session= Session.getInstance();
    private EditarView view;

    public EditController(EditarView view) {
        this.view = view;
        this.view.setController(this);
        
    }
    
    public void show(){
        view.setVisible(true);
    }

    public void show(Point position){
        view.setLocation(position);
        this.show();
    }   
    
    public void hide(){
        view.setVisible(false);
    }  
    
    public void setViewModel(GrupoAlumnoDTO model){
        
        this.view.setModel(model);
        this.view.FromModel();
    }
    
    public void goBack(){
        NotasController before= (NotasController) session.getAttribute("notasController");
        this.hide();
        before.show(this.view.getLocation());
        
    }
    
    public void Save(GrupoAlumnoDTO model, Point position){
        
        try{
        
        if(!this.grupoAlumnoRepo.ActualizarNota(model))
            throw new Exception();
        

          NotasController before= (NotasController) session.getAttribute("notasController");
          before.updateTable();
          before.getView().updateTable();
          this.hide();
          before.show(position);
                   JOptionPane.showMessageDialog(before.getView(), 
  "Nota Actualizada con Exito", "Exito!", JOptionPane.INFORMATION_MESSAGE);
          
          
        
        }
        catch(Exception e){
            e.printStackTrace();
                               JOptionPane.showMessageDialog(this.view, 
  "Ocurrio un Error al Actualizar la Nota", "Error!", JOptionPane.ERROR_MESSAGE);
        }
        
        
    }
    
}
