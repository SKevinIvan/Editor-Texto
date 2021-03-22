/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.util.ArrayList;

/**
 *
 * @author por_s
 */
public class Errores {

    private String idError;
    private String error;
    private String descripcion;
    private String tipo;
    private int lineaCodigo;

    /**
     * Get the value of lineaCodigo
     *
     * @return the value of lineaCodigo
     */
    public int getLineaCodigo() {
        return lineaCodigo;
    }

    /**
     * Set the value of lineaCodigo
     *
     * @param lineaCodigo new value of lineaCodigo
     */
    public void setLineaCodigo(int lineaCodigo) {
        this.lineaCodigo = lineaCodigo;
    }

    /**
     * Get the value of tipo
     *
     * @return the value of tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Set the value of tipo
     *
     * @param tipo new value of tipo
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * Get the value of descripcion
     *
     * @return the value of descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Set the value of descripcion
     *
     * @param descripcion new value of descripcion
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Get the value of error
     *
     * @return the value of error
     */
    public String getError() {
        return error;
    }

    /**
     * Set the value of error
     *
     * @param error new value of error
     */
    public void setError(String error) {
        this.error = error;
    }

    /**
     * Get the value of idError
     *
     * @return the value of idError
     */
    public String getIdError() {
        return idError;
    }

    /**
     * Set the value of idError
     *
     * @param idError new value of idError
     */
    public void setIdError(String idError) {
        this.idError = idError;
    }

    public static void insertaError(ArrayList<Errores> tablaErrores, String idError, String error, String descripcion, String tipo, int lineaCodigo) {
        Errores e = new Errores();
        e.setIdError(idError);
        e.setError(error);
        e.setDescripcion(descripcion);
        e.setTipo(tipo);
        e.setLineaCodigo(lineaCodigo);
        tablaErrores.add(e);
    }
    
  
}
