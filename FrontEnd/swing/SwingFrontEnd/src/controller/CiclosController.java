/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import data.Session;
import entity.Ciclo;
import entity.Curso;
import entity.Profesor;
import entity.Usuario;
import java.awt.Point;
import java.util.List;
import javax.swing.JOptionPane;
import service.ServiceCiclo;
import service.ServiceCurso;
import view.CiclosView;
import viewmodel.CicloViewModel;

/**
 *
 * @author jonathan
 */
public class CiclosController {

    private final ServiceCurso cursosRepo = ServiceCurso.getInstance();
    private final ServiceCiclo ciclosRepo = ServiceCiclo.getInstance();
    private final Session session = Session.getInstance();
    private CicloViewModel model;
    private CiclosView view;

    public CiclosController() {

    }

    public CiclosController(CicloViewModel model, CiclosView view) {
        this.model = model;
        this.view = view;
        this.view.setController(this);
        this.view.setModel(model);
        this.setComboBox();
    }

    public void reset() {
        model.reset();
    }

    public CicloViewModel getModel() {
        return model;
    }

    public void setModel(CicloViewModel model) {
        this.model = model;
    }

    public CiclosView getView() {
        return view;
    }

    public void setView(CiclosView view) {
        this.view = view;
    }

    public void setComboBox() {

        try {
            List<Ciclo> result = this.ciclosRepo.getCiclos();
            this.model.setCiclos(result);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this.view,
                    "Ha Ocurrido un error al cargar los ciclos disponibles", "Error!", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void getGruposPorCiclo(Ciclo ciclo) {

        try {

            Usuario us = (Usuario) session.getAttribute("superuser");
            if (us != null) {

                List<Curso> cursos = cursosRepo.getCursosSuperUser(ciclo);
                session.setAttibute("cursos", cursos);
                CursosController nextView = (CursosController) session.getAttribute("cursosController");
                if (nextView == null) {
                    throw new Exception();
                }

                this.hide();
                nextView.show(this.view.getLocation());
            } else {
                Profesor profe = (Profesor) session.getAttribute("user");
                if (profe == null) {
                    throw new Exception();
                }

                List<Curso> cursos = cursosRepo.getCursosPorProfesor(profe, ciclo);
                System.out.println(cursos);
                session.setAttibute("cursos", cursos);
                CursosController nextView = (CursosController) session.getAttribute("cursosController");
                if (nextView == null) {
                    throw new Exception();
                }

                this.hide();
                nextView.show(this.view.getLocation());
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this.view,
                    "Ha Ocurrido un error al cargar los datos", "Error!", JOptionPane.ERROR_MESSAGE);
        }
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

}
