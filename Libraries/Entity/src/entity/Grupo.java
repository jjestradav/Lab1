/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author jonathan
 */
public class Grupo {
    
    private int codigo;
    private Ciclo ciclo;
    private Curso curso;
    private Profesor profesor;
    private String horario;
    private int numeroGrupo;

    public Grupo() {
    }
    
    public Grupo(int codigo) {
        this.codigo = codigo;

    }

    public Grupo(int codigo, Ciclo ciclo, Curso curso, Profesor profesor, String horario, int numeroGrupo) {
        this.codigo = codigo;
        this.ciclo = ciclo;
        this.curso = curso;
        this.profesor = profesor;
        this.horario = horario;
        this.numeroGrupo = numeroGrupo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Ciclo getCiclo() {
        return ciclo;
    }

    public void setCiclo(Ciclo ciclo) {
        this.ciclo = ciclo;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public int getNumeroGrupo() {
        return numeroGrupo;
    }

    public void setNumeroGrupo(int numeroGrupo) {
        this.numeroGrupo = numeroGrupo;
    }

    @Override
    public String toString() {
        return "Grupo "+this.numeroGrupo+" ( "+this.horario+" )";
    }
    
    
    
}
