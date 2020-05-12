/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewmodel;

import entity.Ciclo;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author jonathan
 */
public class CicloViewModel  extends java.util.Observable {
    
    private Ciclo current;
    private ComboBoxModel<Ciclo> ciclos;

    public CicloViewModel() {
        this.reset();
    }

    public CicloViewModel(Ciclo current, ComboBoxModel<Ciclo> ciclos) {
        this.current = current;
        this.ciclos = ciclos;
    }

    public Ciclo getCurrent() {
        return current;
    }

    public void setCurrent(Ciclo current) {
        this.current = current;
    }

    public ComboBoxModel<Ciclo> getCiclos() {
        return ciclos;
    }

    public void setCiclos(List<Ciclo> ciclos) {
        this.ciclos = new DefaultComboBoxModel(ciclos.toArray());
       this.commit();
        
        
    }

    public void reset(){
        this.current=new Ciclo();
        this.setCiclos(new ArrayList<>());
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
