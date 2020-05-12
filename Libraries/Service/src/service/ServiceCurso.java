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
import entity.Ciclo;
import entity.Curso;
import entity.Profesor;
import exception.GlobalException;
import exception.NoDataException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class ServiceCurso  {
    
     private Service service= Service.getInstance();
     

    private static final String CURSO_PROFESOR = "{?=call cursoProfesor(?,?)}";

    private static final String CURSO_SUPERUSER = "{?=call cursosSuperUser(?)}";
      private static ServiceCurso instance=null;

    private ServiceCurso() {

    }
    
    public static ServiceCurso getInstance(){
     
        if(instance==null)
            instance=new ServiceCurso();
        
        return instance;
    }

    public List<Curso> getCursosPorProfesor(Profesor profe,Ciclo ciclo) throws Exception{
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
        
                
               call= this.service.getConnection().prepareCall(CURSO_PROFESOR);
             
               call.registerOutParameter(1, Types.OTHER);
                call.setString(2, profe.getCedula());
                 call.setInt(3, ciclo.getCodigo());
               call.execute();
               rs=(ResultSet) call.getObject(1);
               List<Curso> result=new ArrayList<>();
               while(rs.next()){
                Curso cur=new Curso(rs.getInt("codigo"),rs.getString("nombre"));
                result.add(cur);
                   
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
    
    
    
    
    
     public List<Curso> getCursosSuperUser(Ciclo ciclo) throws Exception{
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
        
                
               call= this.service.getConnection().prepareCall(CURSO_SUPERUSER);
             
               call.registerOutParameter(1, Types.OTHER);
                 call.setInt(2, ciclo.getCodigo());
               call.execute();
               rs=(ResultSet) call.getObject(1);
               List<Curso> result=new ArrayList<>();
               while(rs.next()){
                Curso cur=new Curso(rs.getInt("codigo"),rs.getString("nombre"));
                result.add(cur);
                   
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
