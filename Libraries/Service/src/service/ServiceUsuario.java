/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Usuario;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.Optional;

/**
 *
 * @author jonathan
 */
public class ServiceUsuario {
    
    private Service service= Service.getInstance();
     

    private static final String BUSCAR_USUARIO = "{?=call getUsuario(?,?)}";


       private static ServiceUsuario instance=null;

    private ServiceUsuario() {

    }
    
    public static ServiceUsuario getInstance(){
     
        if(instance==null)
            instance=new ServiceUsuario();
        
        return instance;
    }
    
    
    public Optional<Usuario> getUsuario(Usuario us) throws Exception{
        try{
          this.service.conectar();
          this.service.getConnection().setAutoCommit(false);
        }
        catch(Exception e){
            e.printStackTrace();
            throw e;
        }
        
        
            CallableStatement call=null;
        ResultSet rs=null;
        try{
        
                
               call= this.service.getConnection().prepareCall(BUSCAR_USUARIO);
               call.registerOutParameter(1, Types.OTHER);
               call.setString(2, us.getCedula());
               call.setString(3, us.getPassword());
               call.setCursorName("refcursor");
               call.execute();
               rs=(ResultSet) call.getObject(1);
               
               if(rs.next()){
                   Usuario user= new Usuario(rs.getString("cedula"),rs.getString("rol"));
                   return Optional.of(user);
               }
               else
                   return Optional.empty();
           
        }
        catch(Exception e){
             e.printStackTrace();
                throw e;
        }
        finally{
             try{
                if(rs != null)
                    rs.close();
            }
            catch (Exception e){
                e.printStackTrace();
                throw e;
            }
              try{
                if(call != null)
                    call.close();
            }
            catch (Exception e){
                e.printStackTrace();
                throw e;
            }
               try{
                this.service.desconectar();
            }
            catch (Exception e){
                e.printStackTrace();
                throw e;
                
            }
        }
        
    }
}
