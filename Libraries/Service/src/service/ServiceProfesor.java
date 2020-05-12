/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Profesor;
import entity.Usuario;
import exception.GlobalException;
import exception.NoDataException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Optional;

/**
 *
 * @author jonathan
 */
public class ServiceProfesor  {

     private Service service= Service.getInstance();
     
    private static final String INSERTAR_PROFESOR = "{call INSERTAR_PROFESOR(?,?,?,?)}";
    private static final String MODIFICAR_PROFESOR = "{call MODIFICAR_PROFESOR(?,?,?,?)}";
    private static final String BUSCAR_PROFESOR = "{?=call getProfesor(?)}";
    private static final String LISTAR_PROFESOR = "{?=call LISTAR_PROFESOR()}";
    private static final String ELIMINAR_PROFESOR = "{call ELIMINAR_PROFESOR(?)}";

       private static ServiceProfesor instance=null;

    private ServiceProfesor() {

    }
    
    public static ServiceProfesor getInstance(){
     
        if(instance==null)
            instance=new ServiceProfesor();
        
        return instance;
    }


    /*Insertar profesores*/
    public void insertar_profesor(Profesor elProfesor) throws GlobalException, NoDataException, SQLException {
        try {
            this.service.conectar();
        } catch (ClassNotFoundException e) {
            throw new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }
        CallableStatement pstmt = null;

        try {
//            pstmt = conexion.prepareCall(INSERTAR_PROFESOR);
//            pstmt.setString(1, elProfesor.getCedula_profesor());
//            pstmt.setString(2, elProfesor.getNombre());
//            pstmt.setString(3, elProfesor.getTelefono());
//            pstmt.setString(4, elProfesor.getEmail());
            boolean resultado = pstmt.execute();
            if (resultado == true) {
                throw new NoDataException("No se realizo la insercion");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new GlobalException("Llave duplicada");
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                this.service.desconectar();
            } catch (SQLException e) {
                throw new GlobalException("Estatutos invalidos o nulos");
            }
        }
    }

    /*Modificar profesores*/
    public void modificar_profesor(Profesor elProfesor) throws GlobalException, NoDataException {
        try {
            this.service.conectar();
        } catch (ClassNotFoundException e) {
            throw new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }
        PreparedStatement pstmt = null;
        try {
//            pstmt = conexion.prepareStatement(MODIFICAR_PROFESOR);
//            pstmt.setString(1, elProfesor.getCedula_profesor());
//            pstmt.setString(2, elProfesor.getNombre());
//            pstmt.setString(3, elProfesor.getTelefono());
//            pstmt.setString(4, elProfesor.getEmail());
            int resultado = pstmt.executeUpdate();

            if (resultado == 0) {
                throw new NoDataException("No se realizo la actualización");
            } else {
                System.out.println("\nModificación Satisfactoria!");
            }
        } catch (SQLException e) {
            throw new GlobalException("Sentencia no valida");
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                this.service.desconectar();
            } catch (SQLException e) {
                throw new GlobalException("Estatutos invalidos o nulos");
            }
        }
    }

    /*Buscar profesores*/
    public Profesor buscar_profesor(String id) throws GlobalException, NoDataException {

        try {
            this.service.conectar();
        } catch (ClassNotFoundException e) {
            throw new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }
        ResultSet rs = null;
        ArrayList coleccion = new ArrayList();
        Profesor elProfesor = null;
        CallableStatement pstmt = null;
        try {
            pstmt = this.service.getConnection().prepareCall(BUSCAR_PROFESOR);
          //  pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.setString(2, id);
            pstmt.execute();
            rs = (ResultSet) pstmt.getObject(1);
//            while (rs.next()) {
//                elProfesor = new Profesor(rs.getString("CEDULA_PROFESOR"),
//                        rs.getString("NOMBRE"),
//                        rs.getString("TELEFONO"),
//                        rs.getString("EMAIL"));
//                coleccion.add(elProfesor);
//            }
        } catch (SQLException e) {
            e.printStackTrace();

            throw new GlobalException("Sentencia no valida");
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                this.service.desconectar();
            } catch (SQLException e) {
                throw new GlobalException("Estatutos invalidos o nulos");
            }
        }
        if (coleccion.isEmpty()) {
            throw new NoDataException("No hay datos");
        }
        return elProfesor;
    }

    /*Listar profesores*/
    public ArrayList<Profesor> listar_profesor() throws GlobalException, NoDataException {
        try {
            this.service.conectar();
        } catch (ClassNotFoundException ex) {
            throw new GlobalException("No se ha localizado el Driver");
        } catch (SQLException e) {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }

        ResultSet rs = null;
        ArrayList coleccion = new ArrayList();
        Profesor elProfesor = null;
        CallableStatement pstmt = null;
        try {
            pstmt = this.service.getConnection().prepareCall(LISTAR_PROFESOR);
           // pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.execute();
            rs = (ResultSet) pstmt.getObject(1);
//            while (rs.next()) {
//                elProfesor = new Profesor(rs.getString("CEDULA_PROFESOR"),
//                        rs.getString("NOMBRE"),
//                        rs.getString("TELEFONO"),
//                        rs.getString("EMAIL"));
//                coleccion.add(elProfesor);
//            }
        } catch (SQLException e) {
            e.printStackTrace();

            throw new GlobalException("Sentencia no valida");
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                this.service.desconectar();
            } catch (SQLException e) {
                throw new GlobalException("Estatutos invalidos o nulos");
            }
        }
        if (coleccion == null || coleccion.size() == 0) {
            throw new NoDataException("No hay datos");
        }
        return coleccion;
    }

    /*Eliminar profesores*/
    public void eliminar_profesor(String id) throws GlobalException, NoDataException {
        try {
            this.service.conectar();
        } catch (ClassNotFoundException e) {
            throw new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }
        PreparedStatement pstmt = null;
        try {
            pstmt = this.service.getConnection().prepareStatement(ELIMINAR_PROFESOR);
            pstmt.setString(1, id);

            int resultado = pstmt.executeUpdate();

            if (resultado == 0) {
                throw new NoDataException("No se realizo el borrado");
            } else {
                System.out.println("\nEliminación Satisfactoria!");
            }
        } catch (SQLException e) {
            throw new GlobalException("Sentencia no valida");
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                this.service.desconectar();
            } catch (SQLException e) {
                throw new GlobalException("Estatutos invalidos o nulos");
            }
        }
    }
    
    
    
     public Optional<Profesor> getProfesor(Profesor profe) throws Exception{
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
        
                
               call= this.service.getConnection().prepareCall(BUSCAR_PROFESOR);
               call.registerOutParameter(1, Types.OTHER);
               call.setString(2, profe.getCedula());

               call.execute();
               rs=(ResultSet) call.getObject(1);
               
               if(rs.next()){
                   Profesor opt= new Profesor(rs.getString("cedula"),rs.getString("nombre"),rs.getInt("telefono"),rs.getString("email"));
                   return Optional.of(opt);
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
