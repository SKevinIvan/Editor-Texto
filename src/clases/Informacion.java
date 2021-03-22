/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

/**
 *
 * @author por_s
 */
public class Informacion {

    private String idInfo;
    private String accion;
    private String estado;
    private String noTransaccion;
    private String fechaTransaccion;

    /**
     * Get the value of fechaTransaccion
     *
     * @return the value of fechaTransaccion
     */
    public String getFechaTransaccion() {
        return fechaTransaccion;
    }

    /**
     * Set the value of fechaTransaccion
     *
     * @param fechaTransaccion new value of fechaTransaccion
     */
    public void setFechaTransaccion(String fechaTransaccion) {
        this.fechaTransaccion = fechaTransaccion;
    }

    /**
     * Get the value of estado
     *
     * @return the value of estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Set the value of estado
     *
     * @param estado new value of estado
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Get the value of accion
     *
     * @return the value of accion
     */
    public String getAccion() {
        return accion;
    }

    /**
     * Set the value of accion
     *
     * @param accion new value of accion
     */
    public void setAccion(String accion) {
        this.accion = accion;
    }

    /**
     * Get the value of noTransaccion
     *
     * @return the value of noTransaccion
     */
    public String getNoTransaccion() {
        return noTransaccion;
    }

    /**
     * Set the value of noTransaccion
     *
     * @param noTransaccion new value of noTransaccion
     */
    public void setNoTransaccion(String noTransaccion) {
        this.noTransaccion = noTransaccion;
    }

    /**
     * Get the value of idInfo
     *
     * @return the value of idInfo
     */
    public String getIdInfo() {
        return idInfo;
    }

    /**
     * Set the value of idInfo
     *
     * @param idInfo new value of idInfo
     */
    public void setIdInfo(String idInfo) {
        this.idInfo = idInfo;
    }

}
