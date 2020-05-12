/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import controller.CiclosController;
import controller.CursosController;
import controller.EditController;
import controller.GruposController;
import controller.LoginController;
import controller.NotasController;
import data.Session;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.HashMap;
import java.util.Map;
import java.io.*;
import javax.swing.JOptionPane;
import view.CiclosView;
import view.CursosView;
import view.EditarView;
import view.GruposView;
import view.LoginView;
import view.NotasView;
import viewmodel.CicloViewModel;
import viewmodel.CursosViewModel;
import viewmodel.GruposViewModel;
import viewmodel.LoginViewModel;
import viewmodel.NotasViewModel;
/**
 *
 * @author jonathan
 */
public class Main {

    public static void main(String[] args){
        
     try{
         System.out.println("HOLA");
         
         Session session=Session.getInstance();
         
         LoginViewModel loginViewModel= new LoginViewModel();
         LoginView loginView= new LoginView();
         LoginController loginController= new LoginController(loginView,loginViewModel);
         Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
         loginController.setLocation(dim.width/2-loginController.getViewSize().width/2,
                 dim.height/2-loginController.getViewSize().height/2);
         loginController.show();
         session.setAttibute("loginController", loginController);
         
         CicloViewModel ciclosViewModel=new CicloViewModel();
         CiclosView ciclosView= new CiclosView();
         CiclosController ciclosController= new CiclosController(ciclosViewModel,ciclosView);
         ciclosController.hide();
         session.setAttibute("ciclosController", ciclosController);
         
         
         CursosViewModel cursosViewModel= new CursosViewModel();
         CursosView cursosView= new CursosView();
         CursosController cursosController= new CursosController(cursosViewModel,cursosView);
         cursosController.hide();
         session.setAttibute("cursosController", cursosController);
         
         
         
         GruposViewModel gruposViewModel= new GruposViewModel();
         GruposView gruposView = new GruposView();
         GruposController gruposController= new GruposController(gruposViewModel,gruposView);
         gruposController.hide();
         session.setAttibute("gruposController", gruposController);
         
         
         NotasViewModel notasViewModel=new NotasViewModel();
         NotasView notasView= new NotasView();
         NotasController notasController = new NotasController(notasView,notasViewModel);
         notasController.hide();
         session.setAttibute("notasController", notasController);
         
         EditarView editarView= new EditarView();
         EditController editController= new EditController(editarView);
         editController.hide();
         session.setAttibute("editController", editController);
         
         
     }
     
     catch(Exception e){
         e.printStackTrace();
                                  JOptionPane.showMessageDialog(null,
  "Ha Ocurrido un error al cargar los datos", "Error!", JOptionPane.ERROR_MESSAGE);
     }
    }
    
    
}
