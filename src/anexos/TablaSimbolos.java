/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anexos;

import clases.*;

/**
 *
 * @author por_s
 */
public class TablaSimbolos {
    
    //Inicio de la declaración de variables 
    private String lexema;
    private int token;
    private Object valor;
    private String tipoDato;
    private String estado;
    private String tipo;
    private int tamanio;
    private int index;
    private String alcance;

    /**
     * Get the value of alcance
     *
     * @return the value of alcance
     */
    public String getAlcance() {
        return alcance;
    }

    /**
     * Set the value of alcance
     *
     * @param alcance new value of alcance
     */
    public void setAlcance(String alcance) {
        this.alcance = alcance;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

   

    /**
     * Get the value of tamanio
     *
     * @return the value of tamanio
     */
    public int getTamanio() {
        return tamanio;
    }

    /**
     * Set the value of tamanio
     *
     * @param tamanio new value of tamanio
     */
    public void setTamanio(int tamanio) {
        this.tamanio = tamanio;
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

    //Fin de la declaración de variables 

    /**
     * Get the value of tipoDato
     *
     * @return the value of tipoDato
     */
    public String getTipoDato() {
        return tipoDato;
    }

    /**
     * Set the value of tipoDato
     *
     * @param tipoDato new value of tipoDato
     */
    public void setTipoDato(String tipoDato) {
        this.tipoDato = tipoDato;
    }

    /**
     * Get the value of valor
     *
     * @return the value of valor
     */
    public Object getValor() {
        return valor;
    }

    /**
     * Set the value of valor
     *
     * @param valor new value of valor
     */
    public void setValor(Object valor) {
        this.valor = valor;
    }

    /**
     * Get the value of token
     *
     * @return the value of token
     */
    public int getToken() {
        return token;
    }

    /**
     * Set the value of token
     *
     * @param token new value of token
     */
    public void setToken(int token) {
        this.token = token;
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

    
    
}
