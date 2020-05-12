/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewmodel;

import entity.Curso;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author jonathan
 */
public class CursosViewModel extends java.util.Observable {
    
    private Curso current;
    private ComboBoxModel cursos;

    public CursosViewModel() {
        this.reset();
    }

    public CursosViewModel(Curso current, ComboBoxModel cursos) {
        this.current = current;
        this.cursos = cursos;
    }
    
    
    public void reset(){
        this.current= new Curso();
        this.setCursos(new ArrayList<>());
    }

    public Curso getCurrent() {
        return current;
    }

    public void setCurrent(Curso current) {
        this.current = current;
    }

    public ComboBoxModel getCursos() {
        return cursos;
    }

    public void setCursos(List<Curso> cursos) {
        if (cursos != null){
         this.cursos = new DefaultComboBoxModel(cursos.toArray());
        this.commit();
        }
        
    }
    
        @Override
    public void addObserver(java.util.Observer o) {
        super.addObserver(o);
        this.commit();
    }

    public void commit(){
        setChanged();
        notifyObservers();       
    }  
    
}
