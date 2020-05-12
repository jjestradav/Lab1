/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import data.Session;
import dto.GrupoAlumnoDTO;
import entity.Grupo;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.swing.text.Position;
import service.ServiceGrupoAlumno;
import view.NotasView;
import viewmodel.NotasViewModel;

/**
 *
 * @author jonathan
 */
public class NotasController {

    private final ServiceGrupoAlumno grupoAlumnoRepo=ServiceGrupoAlumno.getInstance();
    private final Session session = Session.getInstance();
    private NotasView view;
    private NotasViewModel model;

    public NotasController(NotasView view, NotasViewModel model) {
        this.view = view;
        this.model = model;
        this.view.setController(this);
        this.view.setModel(model);
    }

    public NotasView getView() {
        return view;
    }

    public void setView(NotasView view) {
        this.view = view;
    }

    public NotasViewModel getModel() {
        return model;
    }

    public void setModel(NotasViewModel model) {
        this.model = model;
    }

    public void reset() {
        model.reset();
    }

    public void show() {
        this.view.setVisible(true);
    }

    public void show(Point position) {
        view.setLocation(position);
        this.show();
    }

    public void hide() {
        view.setVisible(false);
    }
    
    public void updateTable(){
        try{
            
        Grupo grupo=(Grupo)session.getAttribute("selectedGrupo");
        List<GrupoAlumnoDTO> result=this.grupoAlumnoRepo.buscarGrupoPorProfesor(grupo);
        session.setAttibute("estudiantes", result);
        this.setTableModel();
        
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
    }
    
    public void setTableModel(){
              // System.out.println("NO NULL");
         List<GrupoAlumnoDTO> result = (List<GrupoAlumnoDTO>) session.getAttribute("estudiantes");
    
         if(result == null)
                  System.out.println("NULL");
                  
                  
                  
             this.model.setNotas(result);
        // }
//         else
//             this.model.setNotas(new ArrayList<>());
    }

    public void setTitle(String text){
        this.view.setTitleLbl(text);
    }
    
    
    public void Editar(int row, Point point){
        GrupoAlumnoDTO current= model.getNotas().getRowAt(row);
        EditController nextView= (EditController) session.getAttribute("editController");
        nextView.setViewModel(current);
        this.hide();
        nextView.show(point);
        
        
    }
    
    public void  goBack(Point pos){
        
        GruposController before=(GruposController) session.getAttribute("gruposController");
        this.hide();
        before.show(pos);
        
    }
}
