/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewmodel;

import dto.GrupoAlumnoDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.Observer;
import tableModel.NotasTableModel;

/**
 *
 * @author jonathan
 */
public class NotasViewModel extends java.util.Observable {

    private GrupoAlumnoDTO current;
    private NotasTableModel notas;

    public NotasViewModel() {
        //this.reset();
    }

    public NotasViewModel(GrupoAlumnoDTO current, NotasTableModel notas) {
        this.current = current;
        this.notas = notas;
    }

    public GrupoAlumnoDTO getCurrent() {
        return current;
    }

    public void setCurrent(GrupoAlumnoDTO current) {
        this.current = current;
    }

    public NotasTableModel getNotas() {
        return notas;
    }

    public void setNotas(List<GrupoAlumnoDTO> notas) {

        int[] cols = {NotasTableModel.CEDULA_EST, NotasTableModel.NOMBRE_EST, NotasTableModel.NOTA};
        this.notas = new NotasTableModel(cols, notas);
        this.commit();
    }

    public void reset() {

        List<GrupoAlumnoDTO> rows = new ArrayList<>();
        current = new GrupoAlumnoDTO();
        this.setNotas(rows);
        this.commit();
    }

    @Override
    public void addObserver(Observer o) {
        super.addObserver(o);
        this.commit();
    }

    public void commit() {
        setChanged();
        notifyObservers();
    }

}
