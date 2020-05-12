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
import entity.Profesor;
import entity.Usuario;
import java.awt.Point;
import java.util.List;
import javax.swing.JOptionPane;
import service.ServiceGrupo;
import view.CursosView;
import viewmodel.CursosViewModel;

/**
 *
 * @author jonathan
 */
public class CursosController {

    private final ServiceGrupo gruposRepo = ServiceGrupo.getInstance();
    private final Session session = Session.getInstance();
    private CursosViewModel model;
    private CursosView view;

    public CursosController(CursosViewModel model, CursosView view) {
        this.model = model;
        this.view = view;
        this.view.setController(this);
        this.view.setModel(model);
        // this.setComboBox();
    }

    public CursosViewModel getModel() {
        return model;
    }

    public void setModel(CursosViewModel model) {
        this.model = model;
    }

    public CursosView getView() {
        return view;
    }

    public void setView(CursosView view) {
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

            List<Curso> result = (List<Curso>) this.session.getAttribute("cursos");

            //if(result==null)
            //  throw new Exception();
            this.model.setCursos(result);

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this.view,
                    "Ha Ocurrido un error al cargar los datos", "Error!", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void goToGrupos(Curso cur) {

        try {
            Usuario us = (Usuario) session.getAttribute("superuser");

            if (us != null) {
                List<Grupo> result = this.gruposRepo.buscarGrupoSuperUser(cur);
                session.setAttibute("grupos", result);
                GruposController nextView = (GruposController) session.getAttribute("gruposController");
                this.hide();
                nextView.show(this.view.getLocation());

            } else {

                Profesor profe = (Profesor) session.getAttribute("user");
                List<Grupo> result = this.gruposRepo.buscarGrupoPorCurso(cur, profe);
                session.setAttibute("grupos", result);
                GruposController nextView = (GruposController) session.getAttribute("gruposController");
                this.hide();
                nextView.show(this.view.getLocation());
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this.view,
                    "Ha Ocurrido un error al cargar los datos", "Error!", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void goBack(Point point) {
        CiclosController before = (CiclosController) session.getAttribute("ciclosController");
        this.hide();
        before.show(point);
    }

}
