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
public class Lexema {

    private int numToken;
    private String lexema;
    private String nombreToken;
    private int renglon;

    /**
     * Get the value of renglon
     *
     * @return the value of renglon
     */
    public int getRenglon() {
        return renglon;
    }

    /**
     * Set the value of renglon
     *
     * @param renglon new value of renglon
     */
    public void setRenglon(int renglon) {
        this.renglon = renglon;
    }

    /**
     * Get the value of nombreToken
     *
     * @return the value of nombreToken
     */
    public String getNombreToken() {
        return nombreToken;
    }

    /**
     * Set the value of nombreToken
     *
     * @param nombreToken new value of nombreToken
     */
    public void setNombreToken(String nombreToken) {
        this.nombreToken = nombreToken;
    }

    /**
     * Get the value of lexema
     *
     * @return the value of lexema
     */
    public String getLexema() {
        return lexema;
    }

    /**
     * Set the value of lexema
     *
     * @param lexema new value of lexema
     */
    public void setLexema(String lexema) {
        this.lexema = lexema;
    }

    /**
     * Get the value of numToken
     *
     * @return the value of numToken
     */
    public int getNumToken() {
        return numToken;
    }

    /**
     * Set the value of numToken
     *
     * @param numToken new value of numToken
     */
    public void setNumToken(int numToken) {
        this.numToken = numToken;
    }

}
