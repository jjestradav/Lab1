/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewmodel;

import entity.Curso;
import entity.Grupo;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author jonathan
 */
public class GruposViewModel extends java.util.Observable {

    private Grupo current;
    private ComboBoxModel grupos;

    public GruposViewModel() {
        this.reset();
    }

    public GruposViewModel(Grupo grupo, ComboBoxModel grupos) {
        this.current = grupo;
        this.grupos = grupos;
    }

    public Grupo getGrupo() {
        return current;
    }

    public void setGrupo(Grupo grupo) {
        this.current = grupo;
    }

    public ComboBoxModel getGrupos() {
        return grupos;
    }

    public void setGrupos(List<Grupo> grupos) {
        this.grupos = new DefaultComboBoxModel(grupos.toArray());
        this.commit();
    }

    public void reset() {
        this.current = new Grupo();
        this.setGrupos(new ArrayList<>());
    }

    @Override
    public void addObserver(java.util.Observer o) {
        super.addObserver(o);
        this.commit();
    }

    public void commit() {
        setChanged();
        notifyObservers();
    }
}
