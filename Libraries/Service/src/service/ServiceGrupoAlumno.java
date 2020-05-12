/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dto.AlumnoDTO;
import dto.GrupoAlumnoDTO;
import entity.Alumno;
import entity.Grupo;
import entity.Profesor;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jonathan
 */
public class ServiceGrupoAlumno {
    
     private Service service= Service.getInstance();
    


    private static final String GRUPO_ALUMNO_CURSO = "{?=call grupoAlumnoPorGrupo(?)}";
    private static final String ACTUALIZAR_NOTA = "{?=call actualizarNota(?,?,?)}";

    
        private static ServiceGrupoAlumno instance=null;

    private ServiceGrupoAlumno() {

    }
    
    public static ServiceGrupoAlumno getInstance(){
     
        if(instance==null)
            instance=new ServiceGrupoAlumno();
        
        return instance;
    }
    
  public List<GrupoAlumnoDTO> buscarGrupoPorProfesor(Grupo grupo) throws Exception{
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
        
                
               call= this.service.getConnection().prepareCall(GRUPO_ALUMNO_CURSO);
               
               call.setInt(2,grupo.getCodigo());
               call.registerOutParameter(1, Types.OTHER);
              

               call.execute();
               rs=(ResultSet) call.getObject(1);
               List<GrupoAlumnoDTO> result=new ArrayList<>();
               while(rs.next()){
                   GrupoAlumnoDTO gruAl= new GrupoAlumnoDTO(rs.getInt("codigo"),new AlumnoDTO(rs.getString("cedula"),rs.getString("nombre")),
                   rs.getFloat("nota"));
                   result.add(gruAl);
                   
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
  
  
   public boolean ActualizarNota(GrupoAlumnoDTO model) throws Exception{
        try{
          this.service.conectar();
          this.service.getConnection().setAutoCommit(true);
        }
        catch(Exception e){
            e.printStackTrace();
            throw e;
        }
        
        
            CallableStatement call=null;
        try{
        
                
               call= this.service.getConnection().prepareCall(ACTUALIZAR_NOTA);
               
                 call.registerOutParameter(1, Types.BOOLEAN);
               call.setString(2,model.getAlumno().getCedula());
               call.setFloat(3,model.getNota());
               call.setInt(4, model.getGrupo());
             
              

               call.execute();
               Boolean b= (Boolean) call.getObject(1);
               System.out.println(b.booleanValue());
               return b.booleanValue();
               

           
        }
        catch(Exception e){
             e.printStackTrace();
                throw e;
        }
        finally{
          
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
