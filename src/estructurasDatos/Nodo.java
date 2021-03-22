/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estructurasDatos;

/**
 *
 * @author por_Sa
 */
public class Nodo {

    private String s;
    private int obj;

    private Nodo sig = null;
    private Nodo ant = null;
    private Nodo abj = null;
    private Nodo arr = null;
    private ColaD c = null;
    private PilaD p = null;
    private String tipo;
    private int renglon;

    public Nodo(String s, String tipoDato) {
        this.s = s;
        this.tipo = tipoDato;
    }

    public int getRenglon() {
        return renglon;
    }

    public void setRenglon(int renglon) {
        this.renglon = renglon;
    }

    /**
     * Get the value of tipoDato
     *
     * @return the value of tipoDato
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Set the value of tipoDato
     *
     * @param tipoDato new value of tipoDato
     */
    public void setTipo(String tipoDato) {
        this.tipo = tipoDato;
    }

    public Nodo(String s, int obj) {
        this.s = s;
        this.obj = obj;
    }

    /**
     * @return the s
     */
    public String getS() {
        return s;
    }

    /**
     * @param s the s to set
     */
    public void setS(String s) {
        this.s = s;
    }

    /**
     * @return the obj
     */
    public Object getObj() {
        return obj;
    }

    /**
     * @param obj the obj to set
     */
    public void setObj(int obj) {
        this.obj = obj;
    }

    /**
     * @return the sig
     */
    public Nodo getSig() {
        return sig;
    }

    /**
     * @param sig the sig to set
     */
    public void setSig(Nodo sig) {
        this.sig = sig;
    }

    /**
     * @return the ant
     */
    public Nodo getAnt() {
        return ant;
    }

    /**
     * @param ant the ant to set
     */
    public void setAnt(Nodo ant) {
        this.ant = ant;
    }

    /**
     * @return the abj
     */
    public Nodo getAbj() {
        return abj;
    }

    /**
     * @param abj the abj to set
     */
    public void setAbj(Nodo abj) {
        this.abj = abj;
    }

    /**
     * @return the arr
     */
    public Nodo getArr() {
        return arr;
    }

    /**
     * @param arr the arr to set
     */
    public void setArr(Nodo arr) {
        this.arr = arr;
    }

    /**
     * @return the c
     */
    public ColaD getC() {
        return c;
    }

    /**
     * @param c the c to set
     */
    public void setC(ColaD c) {
        this.c = c;
    }

    /**
     * @return the p
     */
    public PilaD getP() {
        return p;
    }

    /**
     * @param p the p to set
     */
    public void setP(PilaD p) {
        this.p = p;
    }

    public String desp() {
        return s;
    }
}
