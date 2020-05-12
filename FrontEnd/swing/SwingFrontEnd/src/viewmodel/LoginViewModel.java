/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewmodel;

import entity.Usuario;

/**
 *
 * @author jonathan
 */
public class LoginViewModel extends java.util.Observable {

 private Usuario current;

    public LoginViewModel() {
        this.reset();
    }

    public LoginViewModel(Usuario current) {
        this.current = current;
    }

    public Usuario getCurrent() {
        return current;
    }

    public void setCurrent(Usuario current) {
        this.current = current;
    }

    public void reset(){
        this.setCurrent(new Usuario());
        this.commit();
    }
    
    @Override
    public void addObserver(java.util.Observer o) {
        super.addObserver(o);
        setChanged();
        notifyObservers();
    }

    public void commit(){
        setChanged();
        notifyObservers();       
    }   
    
    
    @Override
    public String toString() {
        return "LoginViewModel{" + "current=" + current + '}';
    }
 
 
}
