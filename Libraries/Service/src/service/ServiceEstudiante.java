/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

/**
 *
 * @author jonathan
 */
public class ServiceEstudiante  {
    
    
     private Service service= Service.getInstance();
     
    private static final String INSERTAR_CURSO = "{call INSERTAR_CURSO(?,?,?,?,?,?)}";
    private static final String MODIFICAR_CURSO = "{call MODIFICAR_CURSO(?,?,?,?,?,?)}";
    private static final String BUSCAR_CURSO = "{?=call BUSCAR_CURSO(?)}";
    private static final String LISTAR_CURSO = "{?=call LISTAR_CURSO()}";
    private static final String ELIMINAR_CURSO = "{call ELIMINAR_CURSO(?)}";
    
        private static ServiceEstudiante instance=null;

    private ServiceEstudiante() {

    }
    
    public static ServiceEstudiante getInstance(){
     
        if(instance==null)
            instance=new ServiceEstudiante();
        
        return instance;
    }
    
    

}
