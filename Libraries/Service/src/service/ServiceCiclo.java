/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Ciclo;
import entity.Profesor;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author jonathan
 */
public class ServiceCiclo {
    
    
        
     private Service service= Service.getInstance();
    private static final String LISTAR_Ciclos = "{?=call getCiclos()}";


      private static ServiceCiclo instance=null;

    private ServiceCiclo() {
        
    }
    
    public static ServiceCiclo getInstance(){
     
        if(instance==null)
            instance=new ServiceCiclo();
        
        return instance;
    }
    
    public List<Ciclo> getCiclos() throws Exception{
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
        
                
               call= this.service.getConnection().prepareCall(LISTAR_Ciclos);
               call.registerOutParameter(1, Types.OTHER);
              

               call.execute();
               rs=(ResultSet) call.getObject(1);
               List<Ciclo> result=new ArrayList<>();
               while(rs.next()){
                Ciclo cic= new Ciclo(rs.getInt("codigo"),rs.getString("numeroCiclo").charAt(0),rs.getInt("anho"));
                result.add(cic);
                   
               }
               return result;

           
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
