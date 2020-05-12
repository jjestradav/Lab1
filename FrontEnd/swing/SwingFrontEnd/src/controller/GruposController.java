/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import data.Session;
import dto.GrupoAlumnoDTO;
import entity.Curso;
import entity.Grupo;
import java.awt.Point;
import java.util.List;
import javax.swing.JOptionPane;
import service.ServiceGrupoAlumno;
import view.GruposView;
import viewmodel.GruposViewModel;

/**
 *
 * @author jonathan
 */
public class GruposController {

    private final ServiceGrupoAlumno grupoAlumnoRepo = ServiceGrupoAlumno.getInstance();
    private final Session session = Session.getInstance();
    private GruposViewModel model;
    private GruposView view;

    public GruposController(GruposViewModel model, GruposView view) {
        this.model = model;
        this.view = view;
        this.view.setController(this);
        this.view.setModel(model);
    }

    public GruposViewModel getModel() {
        return model;
    }

    public void setModel(GruposViewModel model) {
        this.model = model;
    }

    public GruposView getView() {
        return view;
    }

    public void setView(GruposView view) {
        this.view = view;
    }

    public void hide() {
        view.setVisible(false);
    }

    public void show() {
        view.setVisible(true);
    }

    public void show(Point position) {
        view.setLocation(position);
        this.show();
    }
    
    public void setComboBox() {

        try {

            List<Grupo> result = (List<Grupo>) this.session.getAttribute("grupos");

            if(result==null)
              throw new Exception();
            
            this.model.setGrupos(result);

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this.view,
                    "Ha Ocurrido un error al cargar los datos", "Error!", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void goToNotas(Grupo grupo) {

        try {
            List<GrupoAlumnoDTO> result = this.grupoAlumnoRepo.buscarGrupoPorProfesor(grupo);
            System.out.println(result);
            session.setAttibute("estudiantes", result);
            session.setAttibute("selectedGrupo", grupo);
            NotasController nextView= (NotasController)session.getAttribute("notasController");
            nextView.setTableModel();
            nextView.getView().updateTable();
            nextView.setTitle("Grupo "+grupo.getNumeroGrupo());
            this.hide();
            nextView.show(this.view.getLocation());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    public void goBack(Point point){
        CursosController before=(CursosController) session.getAttribute("cursosController");
        this.hide();
        before.show(point);
    }

}
