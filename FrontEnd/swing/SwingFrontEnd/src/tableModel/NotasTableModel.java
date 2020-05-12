/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tableModel;

import dto.GrupoAlumnoDTO;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author jonathan
 */
public class NotasTableModel extends AbstractTableModel {
    List<GrupoAlumnoDTO> rows;
    int[] cols;
    
    public NotasTableModel(int[]cols, List<GrupoAlumnoDTO> rows){
        this.cols=cols;
        this.rows=rows;
        this.initColNames();
    }
    public NotasTableModel(int[] cols){
        this.cols=cols;
        this.rows=new ArrayList();
        this.initColNames();
     
    }
    @Override
    public int getRowCount() {
        return rows.size();
    }
    @Override
  public String getColumnName(int col){
        return colNames[cols[col]];
    }
    @Override
    public int getColumnCount() {
        return cols.length;
    }

    @Override
    public Object getValueAt(int row, int col) {
        GrupoAlumnoDTO lab= rows.get(row);
        switch(cols[col]){
            case CEDULA_EST: return lab.getAlumno().getCedula();
            case NOMBRE_EST: return lab.getAlumno().getNombre();
            case NOTA: return lab.getNota();
            default: return " ";
        }
    }
  
     public GrupoAlumnoDTO getRowAt(int row) {
        return rows.get(row);
    }
    
  
    public static final int CEDULA_EST=0;
    public static final int NOMBRE_EST=1;
    public static final int NOTA=2;
    
    
    String[] colNames= new String[3];
    private void initColNames() {
        colNames[CEDULA_EST]="Cedula";
        colNames[NOMBRE_EST]= "Nombre";
         colNames[NOTA]="Nota";
      
    }
}
