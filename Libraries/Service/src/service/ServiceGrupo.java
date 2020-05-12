/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dto.AlumnoDTO;
import dto.GrupoAlumnoDTO;
import entity.Alumno;
import entity.Ciclo;
import entity.Curso;
import entity.Grupo;
import entity.GrupoAlumno;
import entity.Profesor;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jonathan
 */
public class ServiceGrupo {
    
    private Service service= Service.getInstance();
    private static final String LISTAR_PROFESOR_POR_GRUPO="{?=call grupoProfesor(?,?)}";
    private static final String GRUPOS_POR_CURSO="{?=call grupoPorCurso(?,?)}";
    private static final String GRUPOS_SUPERUSER="{?=call grupoSuperUser(?)}";
    
        private static ServiceGrupo instance=null;

    private ServiceGrupo() {

    }
    
    public static ServiceGrupo getInstance(){
     
        if(instance==null)
            instance=new ServiceGrupo();
        
        return instance;
    }
    
    public List<GrupoAlumnoDTO> buscarGrupoPorProfesor(Profesor profesor, Curso cur) throws Exception{
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
        
                
               call= this.service.getConnection().prepareCall(LISTAR_PROFESOR_POR_GRUPO);
               call.setString(2,profesor.getCedula() );
                call.setInt(3, cur.getCodigo() );
               call.registerOutParameter(1, Types.OTHER);
              

               call.execute();
               rs=(ResultSet) call.getObject(1);
               List<GrupoAlumnoDTO> result=new ArrayList<>();
               while(rs.next()){
                   GrupoAlumnoDTO gruAl= new GrupoAlumnoDTO(rs.getInt("grupo"),new AlumnoDTO(rs.getString("cedula"),rs.getString("nombreAlumno")),
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
    
      public List<Grupo> buscarGrupoPorCurso(Curso cur,Profesor profe) throws Exception{
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
        
                
               call= this.service.getConnection().prepareCall(GRUPOS_POR_CURSO);
                call.setInt(2, cur.getCodigo() );
                call.setString(3, profe.getCedula() );
               call.registerOutParameter(1, Types.OTHER);
              

               call.execute();
               rs=(ResultSet) call.getObject(1);
               List<Grupo> result=new ArrayList<>();
               while(rs.next()){
                   Grupo grup= new Grupo(rs.getInt("codigo"),new Ciclo(rs.getInt("ciclo")), new Curso(rs.getInt("curso")),
                   new Profesor(rs.getString("profesor")),rs.getString("horario"),rs.getInt("numerogrupo"));
                   result.add(grup);
                   
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
    
      
      public List<Grupo> buscarGrupoSuperUser(Curso cur) throws Exception{
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
        
                
               call= this.service.getConnection().prepareCall(GRUPOS_SUPERUSER);
                call.setInt(2, cur.getCodigo() );
               call.registerOutParameter(1, Types.OTHER);
              

               call.execute();
               rs=(ResultSet) call.getObject(1);
               List<Grupo> result=new ArrayList<>();
               while(rs.next()){
                   Grupo grup= new Grupo(rs.getInt("codigo"),new Ciclo(rs.getInt("ciclo")), new Curso(rs.getInt("curso")),
                   new Profesor(rs.getString("profesor")),rs.getString("horario"),rs.getInt("numerogrupo"));
                   result.add(grup);
                   
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
