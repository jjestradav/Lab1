package service;


import entity.Carrera;
import exception.GlobalException;
import exception.NoDataException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collection;



public class ServiceCarrera  {
    
    
    private Service service= Service.getInstance();
    
    private static final String INSERTAR_CARRERA = "{call INSERTAR_CARRERA(?,?,?)}";
    private static final String MODIFICAR_CARRERA = "{call MODIFICAR_CARRERA(?,?,?)}";
    private static final String BUSCAR_CARRERA = "{?=call BUSCAR_CARRERA(?)}";
    private static final String LISTAR_CARRERA = "{?=call LISTAR_CARRERA()}";
    private static final String ELIMINAR_CARRERA = "{call ELIMINAR_CARRERA(?)}";
    
    private static ServiceCarrera instance=null;

    private ServiceCarrera() {

    }
    
    public static ServiceCarrera getInstance(){
     
        if(instance==null)
            instance=new ServiceCarrera();
        
        return instance;
    }

    /*Insertar profesores*/
    public void insertar_carrera(Carrera laCarrera) throws GlobalException, NoDataException {
        try {
            this.service.conectar();
        } catch (ClassNotFoundException e) {
            throw new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }
        CallableStatement pstmt = null;

        try {
            pstmt = this.service.getConnection().prepareCall(INSERTAR_CARRERA);
//            pstmt.setString(1, laCarrera.getCodigo_carrera());
//            pstmt.setString(2, laCarrera.getNombre());
//            pstmt.setString(3, laCarrera.getTitulo());
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
    public void modificar_carrera(Carrera laCarrera) throws GlobalException, NoDataException {
        try {
            this.service.conectar();
        } catch (ClassNotFoundException e) {
            throw new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }
        PreparedStatement pstmt = null;
        try {
            pstmt = this.service.getConnection().prepareStatement(MODIFICAR_CARRERA);
//            pstmt.setString(1, laCarrera.getCodigo_carrera());
//            pstmt.setString(2, laCarrera.getNombre());
//            pstmt.setString(3, laCarrera.getTitulo());
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
    public Carrera buscar_carrera(String id) throws GlobalException, NoDataException {

        try {
            this.service.conectar();
        } catch (ClassNotFoundException e) {
            throw new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }
        ResultSet rs = null;
        ArrayList coleccion = new ArrayList();
        Carrera laCarrera = null;
        CallableStatement pstmt = null;
        try {
            pstmt = this.service.getConnection().prepareCall(BUSCAR_CARRERA);
            pstmt.registerOutParameter(1, Types.OTHER);
            pstmt.setString(2, id);
            pstmt.execute();
            rs = (ResultSet) pstmt.getObject(1);
            while (rs.next()) {
//                laCarrera = new Carrera(rs.getString("CODIGO_CARRERA"),
//                        rs.getString("NOMBRE"),
//                        rs.getString("TITULO"));
//                coleccion.add(laCarrera);
            }
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
        return laCarrera;
    }

    /*Listar profesores*/
    public Collection listar_carrera() throws GlobalException, NoDataException {
        try {
            this.service.conectar();
        } catch (ClassNotFoundException ex) {
            throw new GlobalException("No se ha localizado el Driver");
        } catch (SQLException e) {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }

        ResultSet rs = null;
        ArrayList coleccion = new ArrayList();
        Carrera laCarrera = null;
        CallableStatement pstmt = null;
        try {
            pstmt = this.service.getConnection().prepareCall(LISTAR_CARRERA);
           // pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.execute();
            rs = (ResultSet) pstmt.getObject(1);
            while (rs.next()) {
//                laCarrera = new Carrera(rs.getString("CODIGO_CARRERA"),
//                        rs.getString("NOMBRE"),
//                        rs.getString("TITULO"));
//                coleccion.add(laCarrera);
            }
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
    public void eliminar_carrera(String id) throws GlobalException, NoDataException {
        try {
            this.service.conectar();
        } catch (ClassNotFoundException e) {
            throw new GlobalException("No se ha localizado el driver");
        } catch (SQLException e) {
            throw new NoDataException("La base de datos no se encuentra disponible");
        }
        PreparedStatement pstmt = null;
        try {
            pstmt = this.service.getConnection().prepareStatement(ELIMINAR_CARRERA);
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
}
